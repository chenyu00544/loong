<?php

namespace App\Http\Controllers\Shop\Api;

use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Wxapp\WxappServerSessionModel;
use App\Repositories\Home\WechatRepository;
use EasyWeChat\Factory;
use EasyWeChat\Kernel\Messages\Image;
use EasyWeChat\Kernel\Messages\Music;
use EasyWeChat\Kernel\Messages\News;
use EasyWeChat\Kernel\Messages\NewsItem;
use EasyWeChat\Kernel\Messages\Text;
use EasyWeChat\Kernel\Messages\Transfer;
use EasyWeChat\Kernel\Messages\Video;
use EasyWeChat\Kernel\Messages\Voice;
use function foo\func;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class WxController extends CommonController
{

    protected $weObj = null;
    protected $wechat_id = 0;
    protected $wechat_ru_id = 0;

    private $wxappServerSessionModel;
    private $wechatRepository;

    public function __construct(
        WxappServerSessionModel $wxappServerSessionModel,
        WechatRepository $wechatRepository
    )
    {
        $this->wxappServerSessionModel = $wxappServerSessionModel;
        $this->wechatRepository = $wechatRepository;
    }

    public function initParameter()
    {
        $wxinfo = RedisCache::get('wechat_config');
        if ($wxinfo && $wxinfo['status'] == 1) {
            $config = [
//                'app_id' => $wxinfo['appid'],
                'app_id' => 'wx14d5cacd5ac18343',
//                'secret' => $wxinfo['appsecret'],
                'secret' => '7ccc896191cae24d38932c7628974553',
                'token' => $wxinfo['token'],
                'aes_key' => $wxinfo['encodingaeskey'],
                'response_type' => 'array',
            ];
            if (config('app.debug')) {
                $config['log'] = [
                    'level' => 'debug',
                    'file' => storage_path('logs/wechat_' . date('Ymd') . '.log')
                ];
            }
            $this->weObj = Factory::officialAccount($config);

            $this->wechat_id = $wxinfo['id'];
            $this->wechat_ru_id = $wxinfo['ru_id'];
        }
    }

    public function wxappServer(Request $request)
    {
        $re = $this->checkSignature($request->all());
        if ($re) {
            $encryptMsg = $request->all();
            $openid = $encryptMsg['openid'];
            $fromUserName = $encryptMsg['ToUserName'];

            $wxUser = $this->wxappServerSessionModel->getWxappServerSession(['open_id' => $openid]);
            if (!$wxUser) {
                $this->wxappServerSessionModel->addWxappServerSession(['open_id' => $openid, 'update_time' => time(), 'count_msg' => 0, 'create_time' => time()]);
            } else {
                if ($wxUser->update_time > (time() - 48 * 24 * 3600)) {
                    $this->wxappServerSessionModel->setWxappServerSession(['open_id' => $openid], ['update_time' => time(), 'count_msg' => 0]);
                }
            }

            $send = [
                'ToUserName' => $openid,
                'FromUserName' => $fromUserName,
                'CreateTime' => time(),
                'MsgType' => 'transfer_customer_service'
            ];
            return $send;
        } else {
            return 'failed';
        }
    }

    private function checkSignature($data, $_token = WXAPP_SERVER)
    {
        $signature = $data['signature'];
        $timestamp = $data['timestamp'];
        $nonce = $data['nonce'];

        $token = $_token;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode($tmpArr);
        $tmpStr = sha1($tmpStr);

        if ($tmpStr == $signature) {
            return true;
        } else {
            return false;
        }
    }

    public function wxchatServer(Request $request)
    {
        $this->initParameter();
        if (request()->isMethod('GET') && !request()->input('echostr')) {
            return 'no access';
        }
        $data = $request->all();
        $re = $this->checkSignature($data, WXCHAT_SERVER);
        if ($re) {
            $this->weObj->server->push(function ($message) {
//                Storage::put('public/wxchat.txt', json_encode($message));
                // 微信消息日志队列之存入数据库
                if ((isset($message['EventKey']) && !empty($message['EventKey'])) || (isset($message['Content']) && !empty($message['Content']))) {
                    $this->wechatRepository->addWechatMessageLog($this->wechat_id, $message);
                }

                // 兼容更新用户关注状态（未配置微信通之前关注的粉丝）
                $this->updateWechatuserSubscribe($this->wechat_id, $message['FromUserName']);
                $keywords = '';
                $scene_id = '';

                // 事件类型
                switch ($message['MsgType']) {
                    // 收到事件消息
                    case 'event':
                        switch (strtolower($message['Event'])) {
                            // 关注事件
                            case 'subscribe':
                                if (isset($message['EventKey'])) {
                                    // 用户扫描带参数二维码(未关注)
                                    $scene_id = $this->wechatRepository->getRevSceneId($message['EventKey']);
                                    $this->subscribe($message['FromUserName'], $scene_id);
                                } else {
                                    $this->subscribe($message['FromUserName']);
                                }
                                // 关注自动回复信息
                                return $this->msgReply('subscribe', $message);
                                break;
                            case 'unsubscribe':
                                // 取消关注事件
                                $this->unsubscribe($message['FromUserName']);
                                return '';
                                break;
                            case 'scan':
                                // 扫描带参数二维码(用户已关注)
                                $scene_id = $this->wechatRepository->getRevSceneId($message['EventKey']);
                                break;
                            case 'location':
                                // 上报地理位置事件
                                $this->wechatRepository->updateLocation($this->wechat_id, $message);
                                return '';
                                break;
                            case 'click':
                                // 自定义菜单事件 点击菜单拉取消息
                                $keywords = $message['EventKey'];
                                break;
                            case 'view':
                                // 自定义菜单事件 点击菜单跳转链接
                                redirect()->to($message['EventKey'])->send();
                                break;
                            case 'kf_create_session':
                                return '';
                                break;
                            case 'kf_close_session':
                                return '';
                                break;
                            case 'kf_switch_session':
                                return '';
                                break;
                            case 'masssendjobfinish':
                                // 更新群发消息结果
                                $this->wechatRepository->updateWechatMassHistory($this->wechat_id, $message);
                                return '';
                                break;
                            case 'templatesendjobfinish':
                                // 更新模板消息通知结果
                                $this->wechatRepository->updateWechatTeamplateLog($this->wechat_id, $message);
                                return '';
                                break;
                        }
                        break;
                    case 'text':
                        $keywords = $message['Content']; // '收到文本消息'
                        break;
                    case 'image':
                        return '';// '收到图片消息'
                        break;
                    case 'voice':
                        return '';// '收到语音消息'
                        break;
                    case 'video':
                        return '';// '收到视频消息'
                        break;
                    case 'location':
                        return '';// '收到坐标消息'
                        break;
                    case 'link':
                        return '';// '收到链接消息'
                        break;
                    case 'file':
                        return '';// '收到文件消息'
                        break;
                    // ... 其它消息
                    default:
                        return $this->msgReply('msg', $message); // 消息自动回复
                        break;
                }

                // 回复消息
                if ($scene_id) {
                    $this->scanReply($scene_id, $message);
                }

                // 查询发送状态
                if ($message['MsgType'] == 'event') {
                    $where = [
                        'wechat_id' => $this->wechat_id,
                        'fromusername' => $message['FromUserName'],
                        'createtime' => $message['CreateTime'],
                        'keywords' => $keywords,
                        'is_send' => 0
                    ];
                } else {
                    $where = [
                        'wechat_id' => $this->wechat_id,
                        'msgid' => $message['MsgId'],
                        'keywords' => $keywords,
                        'is_send' => 0
                    ];
                }
                $contents = $this->wechatRepository->getWechatMessageLog($where);
                $contents = $contents ? $contents->toArray() : [];

                if (!empty($contents) && !empty($contents['keywords'])) {
                    $message['Content'] = html_in($contents['keywords']);
                    $message['FromUserName'] = $contents['fromusername'];

                    // 记录用户操作信息
                    $this->recordMsg($message);

                    // 微信消息日志队列之处理发送状态
                    $this->wechatRepository->messageLogAlignmentSend($this->wechat_id, $contents);

                    // 多客服
                    $rs = $this->customerService($message);
                    if ($rs) {
                        return $rs;
                    }
                    // 功能插件
                    $rs1 = $this->getFunction($message);
                    if ($rs1) {
                        return $rs1;
                    }
                    // 微信营销
                    $rs2 = $this->getMarketing($message);
                    if ($rs2) {
                        return $rs2;
                    }
                    // 关键词回复
                    $rs3 = $this->keywordsReply($message);
                    if ($rs3) {
                        return $rs3;
                    }
                    // 消息自动回复
                    return $this->msgReply('msg', $message);
                }
            });

            return $this->weObj->server->serve();
//            return $data['echostr'];
//            return 'success';
        } else {
            return 'failed';
        }
    }

    /**
     * 兼容更新用户关注状态（未配置微信通之前关注的粉丝）
     */
    public function updateWechatuserSubscribe($wechat_id = 0, $openid = '')
    {
        if (!empty($openid)) {
            $user = $this->weObj->user->get($openid);
            if ($user) {
                $this->wechatRepository->updateWechatUserSubscribeAction($wechat_id, $user);
            }
        }
    }

    /**
     * 关注处理
     *
     * @param string $openid
     * @param string $scene_id
     */
    protected function subscribe($openid = '', $scene_id = '')
    {
        if (!empty($openid)) {
            // 获取微信用户信息
            $user = $this->weObj->user->get($openid);
            // 关注更新
            $this->wechatRepository->subscribeAction($this->wechat_id, $user, $scene_id, $this->wechat_ru_id);

            // 检测是否有模板消息待发送
            $this->checkTemplateLog($this->wechat_id, $user['openid']);
        }
    }

    /**
     * 取消关注处理
     *
     * @param string $openid
     */
    protected function unsubscribe($openid = '')
    {
        $this->wechatRepository->unsubscribeAction($this->wechat_id, $openid);
    }

    /**
     * 被动关注、没有关键字回复的消息回复
     *
     * @param string $type
     * @param array $message
     * @return Image|Text|Video|Voice
     */
    protected function msgReply($type = '', $message = [])
    {
        $replyInfo = $this->wechatRepository->getWechatReply(['type' => $type, 'wechat_id' => $this->wechat_id]);
        $replyInfo = $replyInfo ? $replyInfo->toArray() : [];

        if ($replyInfo) {
            // 记录微信回复信息
            $this->recordMsg($message, 1);

            if (!empty($replyInfo['media_id'])) {
                $media = $this->wechatRepository->getWechatMedia(['id' => $replyInfo['media_id']]);
                $media = $media ? $media->toArray() : [];
                if ($media && isset($media['type'])) {
                    if ($media['type'] == 'news') {
                        $media['type'] = 'image';
                    }
                    // 上传多媒体文件
                    $filename = storage_public($media['file']);

                    // 开启OSS 且本地没有图片的处理
//                if (C('shop.open_oss') == 1 && !file_exists($filename)) {
//                    $image = basename($filename);
//                    $imglist = ['0' => $image];
//                    $file = str_replace(dirname(ROOT_PATH) . '/', '', $filename);
//                    $path = str_replace($image, '', $file);
//                    $this->BatchDownloadOss($imglist, $path);
//                }

                    $rs = $this->weObj->media->upload($media['type'], $filename);
                    if ($rs) {
                        // 删除从oss下载的本地图片
                        // delete_local_oss_image($filename);

                        // 回复
                        if ($rs['type'] == 'image') {
                            // 图片回复
                            return new Image($rs['media_id']);
                        } elseif ($rs['type'] == 'voice') {
                            // 声音回复
                            return new Voice($rs['media_id']);
                        } elseif ($rs['type'] == 'video') {
                            // 视频回复
                            return new Video($rs['media_id'], [
                                'title' => $media['title'],
                                'description' => strip_tags($media['content']),
                            ]);
                        }
                    }
                }
            } else {
                // 文本回复
                if ($replyInfo['content']) {
                    $replyInfo['content'] = html_out($replyInfo['content']);
                    return new Text($replyInfo['content']);
                }
            }
        }
    }

    /**
     * 扫描二维码回复消息
     *
     * @param int $scene_id
     * @param array $message
     * @param bool $flag
     * @return bool
     */
    protected function scanReply($scene_id = 0, $message = [], $flag = true)
    {
        // 扫描二维码
        if (!empty($scene_id)) {
            $qr_keyword = $this->wechatService->qrcodeSubscribeAction($scene_id);
            if (!empty($qr_keyword)) {
                // 功能插件
                $message['Content'] = $qr_keyword;

                $rs1 = $this->get_function($message, $flag);
                if ($rs1) {
                    return $rs1;
                } else {
                    // 关键词回复
                    return $this->keywords_reply($message, $flag);
                }
            }
        }
    }

    /**
     * 记录用户操作信息/ 微信回复消息
     *
     * @param array $message
     * @param int $is_wechat_admin
     */
    public function recordMsg($message = [], $is_wechat_admin = 0)
    {
        $user = $this->weObj->user->get($message['FromUserName']);
        if ($user) {
            if ($message['MsgType'] == 'text') {
                $msg = $message['Content'];
            } elseif ($message['MsgType'] == 'image') {
                $msg = $message['MediaId'];
            } elseif ($message['MsgType'] == 'voice') {
                $msg = $message['MediaId'];
            } elseif ($message['MsgType'] == 'video') {
                $msg = $message['MediaId'];
            } elseif ($message['MsgType'] == 'shortvideo') {
                $msg = $message['MediaId'];
            } elseif ($message['MsgType'] == 'event') {
                $msg = ($message['Event'] == 'CLICK') ? $message['EventKey'] : $message['Event'];
            } elseif ($message['MsgType'] == 'location') {
                $msg = $message['Location_X'] . ',' . $message['Location_Y'];
            } elseif ($message['MsgType'] == 'link') {
                $msg = $message['Url'];
            } elseif ($message['MsgType'] == 'file') {
                $msg = $message['Title'];
            }
            $content = [
                'msg' => $msg,
                'msgtype' => $message['MsgType'],
                'createtime' => $message['CreateTime']
            ];
            $this->wechatRepository->recordMsgAction($this->wechat_id, $user, $content, $is_wechat_admin);
        }
    }

    /**
     * 多客服
     *
     * @param array $message
     * @return bool|Transfer
     */
    public function customerService($message = [])
    {
//        $result = false;

//        // 是否处在多客服流程
//        $openid = $message['FromUserName'];
//        $keywords = $message['Content'];
//        $kfsession = $this->weObj->customer_service_session->get($openid);
//        if (empty($kfsession) || empty($kfsession['kf_account'])) {
//            $kefu = WechatUser::where(['openid' => $openid, 'wechat_id' => $this->wechat_id])->value('openid');
//            if ($kefu && $keywords == 'kefu') {
//                $rs = WechatExtend::where(['command' => 'kefu', 'enable' => 1, 'wechat_id' => $this->wechat_id])->value('config');
//                if (!empty($rs)) {
//                    $msg = new Text('欢迎进入多客服系统');
//                    // $this->weObj->sendCustomMessage($msg);
//                    $this->weObj->customer_service->message($msg)->to($openid)->send();
//
//                    // 在线客服列表
//                    $online_list = $this->weObj->customer_service->online();
//                    $customer = '';
//                    $config = unserialize($rs);
//                    if ($online_list['kf_online_list']) {
//                        foreach ($online_list['kf_online_list'] as $key => $val) {
//                            if ($config['customer'] == $val['kf_account'] || ($val['status'] > 0 && $val['accepted_case'] < $val['auto_accept'])) {
//                                $customer = $config['kf_account'];
//                            }
//                        }
//                    }
//                    // 转发客服消息
//                    if ($customer) {
//                        $result = new Transfer($customer);
//                    } else {
//                        $result = new Transfer();
//                    }
//                }
//            }
//        }
//
//        return $result;
    }

    /**
     * 功能变量查询
     *
     * @param array $message
     * @param bool $flag
     * @return boolean|Text|Image|Video|Voice|News
     */
    public function getFunction($message = [], $flag = false)
    {
//        $return = false;
//
//        $keywords = '';
//        if (isset($message['Content']) || $message['MsgType'] == 'text') {
//            $keywords = $message['Content'];
//        } elseif ($message['MsgType'] == 'event' && $message['Event'] == 'CLICK') {
//            // 自定义菜单事件 点击菜单拉取消息
//            $keywords = $message['EventKey'];
//        }
//
//        $rs = WechatExtend::select('name', 'keywords', 'command', 'config')
//            ->where('enable', 1)
//            ->where('wechat_id', $this->wechat_id)
//            ->where(function ($query) use ($keywords) {
//                $query->where('keywords', 'like', '%' . $keywords . '%')
//                    ->orWhere('command', 'like', '%' . $keywords . '%');
//            })
//            ->orderBy('id', 'ASC')
//            ->get();
//        $rs = $rs ? $rs->toArray() : [];
//
//        if (empty($rs)) {
//            $rs = WechatExtend::select('name', 'keywords', 'command', 'config')
//                ->where('enable', 1)
//                ->where('wechat_id', $this->wechat_id)
//                ->where('command', 'search')
//                ->get();
//            $rs = $rs ? $rs->toArray() : [];
//        }
//        $info = reset($rs);
//        if ($info) {
//            $info['user_keywords'] = $keywords;
//
//            $file = plugin_path('Wechat/' . ucfirst($info['command']) . '/' . ucfirst($info['command']) . '.php');
//            if (file_exists($file)) {
//                $plugin = '\\App\\Plugins\\Wechat\\' . ucfirst($info['command']) . '\\' . ucfirst($info['command']);
//                $config = [
//                    'wechat_id' => $this->wechat_id,
//                    'wechat_ru_id' => $this->wechat_ru_id
//                ];
//                $obj = new $plugin($config);
//                $data = $obj->returnData($message['FromUserName'], $info);
//                if ($data) {
//                    //记录用户操作信息
//                    $this->record_msg($message, 1);
//
//                    // 数据回复类型
//                    if (in_array($data['type'], ['image', 'voice', 'video'])) {
//                        // 上传多媒体文件
//                        $filename = storage_public($data['path']);
//
//                        // 开启OSS 且本地没有图片的处理
//                        /*                    if (C('shop.open_oss') == 1 && !file_exists($filename)) {
//                                                $image = basename($filename);
//                                                $imglist = ['0' => $image];
//                                                $file = str_replace(dirname(ROOT_PATH) . '/', '', $filename);
//                                                $path = str_replace($image, '', $file);
//                                                $this->BatchDownloadOss($imglist, $path);
//                                            }*/
//
//                        $rs = $this->weObj->media->upload($data['type'], $filename);
//
//                        if ($rs) {
//                            // 删除从oss下载的本地图片
//                            // delete_local_oss_image($filename);
//
//                            if ($data['type'] == 'image') {
//                                if ($flag == true) {
//                                    // 发送普通客服消息
//                                    $this->send_custom_message($message['FromUserName'], 'image', $rs['media_id']);
//                                    return true;
//                                } else {
//                                    // 图片回复
//                                    return new Image($rs['media_id']);
//                                }
//                            } elseif ($data['type'] == 'voice') {
//                                if ($flag == true) {
//                                    // 发送普通客服消息
//                                    $this->send_custom_message($message['FromUserName'], 'voice', $rs['media_id']);
//                                    return true;
//                                } else {
//                                    // 声音回复
//                                    return new Voice($rs['media_id']);
//                                }
//                            } elseif ($data['type'] == 'video') {
//                                if ($flag == true) {
//                                    // 发送普通客服消息
//                                    $replyData = [
//                                        'media_id' => $rs['media_id'],
//                                        'title' => $data['title'],
//                                        'description' => strip_tags($data['content']),
//                                    ];
//                                    $this->send_custom_message($message['FromUserName'], 'video', $replyData);
//                                    return true;
//                                } else {
//                                    // 视频回复
//                                    return new Video($rs['media_id'], [
//                                        'title' => $data['title'],
//                                        'description' => strip_tags($data['content']),
//                                    ]);
//                                }
//                            }
//                        }
//                    } elseif ($data['type'] == 'news') {
//
//                        $replyData = [];
//                        foreach ($data['content'] as $val) {
//                            $replyData[] = [
//                                'title' => $val['Title'],
//                                'description' => isset($val['Description']) ? $val['Description'] : '',
//                                'url' => isset($val['Url']) ? $val['Url'] : '',
//                                'image' => isset($val['PicUrl']) ? $val['PicUrl'] : '',
//                            ];
//                        }
//                        if ($flag == true) {
//                            $this->send_custom_message($message['FromUserName'], 'news', $replyData);
//                            return true;
//                        } else {
//                            // 图文回复
//                            $replyData = $this->news_item($replyData);
//                            return new News($replyData);
//                        }
//                    } elseif ($data['type'] == 'text') {
//                        //$data['content'] = html_out($data['content']);
//                        if ($flag == true) {
//                            // 发送普通客服消息
//                            $this->send_custom_message($message['FromUserName'], 'text', $data['content']);
//                            return true;
//                        } else {
//                            // 文本回复
//                            return new Text($data['content']);
//                        }
//                    }
//                }
//            }
//        }
//
//        return $return;
    }

    /**
     * 微信营销功能查询
     *
     * @param array $message
     * @return bool|News|Text
     */
    public function getMarketing($message = [])
    {
//        $return = false;
//
//        $keywords = '';
//        if (isset($message['Content']) || $message['MsgType'] == 'text') {
//            $keywords = $message['Content'];
//        } elseif ($message['MsgType'] == 'event' && $message['Event'] == 'CLICK') {
//            // 自定义菜单事件 点击菜单拉取消息
//            $keywords = $message['EventKey'];
//        }
//
//        $now = $this->timeRepositorie->getGmTime();
//
//        $rs = WechatMarketing::select('id', 'name', 'command', 'background', 'description', 'status', 'url')
//            ->where('wechat_id', $this->wechat_id)
//            ->where(function ($query) use ($keywords) {
//                $query->where('marketing_type', 'like', '%' . $keywords . '%')
//                    ->orWhere('command', $keywords);
//            })
//            ->where('starttime', '<', $now)
//            ->where('endtime', '>', $now)
//            ->orderBy('id', 'DESC')
//            ->first();
//        $rs = $rs ? $rs->toArray() : [];
//
//        if ($rs) {
//            $replyData = ['type' => 'text', 'content' => '活动未开始或未启用'];
//            if ($rs['status'] == 1) {
//                $replyData = [];
//                // 数据
//                $replyData['type'] = 'news';
//                $replyData['content'][0]['Title'] = $rs['name'];
//                $replyData['content'][0]['Description'] = isset($rs['description']) ? $rs['description'] : '';
//                $replyData['content'][0]['PicUrl'] = isset($rs['background']) ? get_wechat_image_path($rs['background']) : '';
//                $replyData['content'][0]['Url'] = isset($rs['url']) ? strip_tags(html_out($rs['url'])) : '';
//            }
//
//            //记录用户操作信息
//            $this->record_msg($message, 1);
//
//            // 数据回复类型
//            if ($replyData['type'] == 'text') {
//                // $replyData['content'] = html_out($replyData['content']);
//                return new Text($replyData['content']);
//
//            } elseif ($replyData['type'] == 'news') {
//                $items = [];
//                foreach ($replyData['content'] as $item) {
//                    $items[] = new NewsItem([
//                        'title' => $item['Title'],
//                        'description' => $item['Description'],
//                        'url' => $item['Url'],
//                        'image' => $item['PicUrl'],
//                    ]);
//                }
//                return new News($items);
//            }
//        }
//
//        return $return;
    }

    /**
     * 关键词回复
     *
     * @param array $message
     * @param bool $flag 是否普通消息
     * @return bool|Text|Image|Video|Voice|News
     */
    protected function keywordsReply($message = [], $flag = false)
    {
        $endrs = false;

        $keywords = '';
        if (isset($message['Content']) || $message['MsgType'] == 'text') {
            $keywords = $message['Content'];
        } elseif ($message['MsgType'] == 'event' && $message['Event'] == 'CLICK') {
            // 自定义菜单事件 点击菜单拉取消息
            $keywords = $message['EventKey'];
        }
        $result = $this->wechatRepository->getWechatReply(['wechat_id' => $this->wechat_id], $keywords);
        $result = $result ? $result->toArray() : [];
        if (!empty($result)) {
            //记录微信回复信息
            $this->recordMsg($message, 1);

            // 素材回复
            if (!empty($result['media_id'])) {
                $mediaInfo = $this->wechatRepository->getWechatMedia(['id' => $result['media_id']]);
                $mediaInfo = $mediaInfo ? $mediaInfo->toArray() : [];

                if ($mediaInfo) {
                    if (in_array($result['reply_type'], ['image', 'voice', 'video'])) {
                        // 上传多媒体文件
                        $filename = storage_public($mediaInfo['file']);

                        // 开启OSS 且本地没有图片的处理
//                    if (C('shop.open_oss') == 1 && !file_exists($filename)) {
//                        $image = basename($filename);
//                        $imglist = ['0' => $image];
//                        $file = str_replace(dirname(ROOT_PATH) . '/', '', $filename);
//                        $path = str_replace($image, '', $file);
//                        $this->BatchDownloadOss($imglist, $path);
//                    }

                        $rs = $this->weObj->media->upload($mediaInfo['type'], $filename);

                        if ($rs) {
                            // 删除从oss下载的本地图片
                            // delete_local_oss_image($filename);

                            if ($result['reply_type'] == 'image') {
                                if ($flag == true) {
                                    // 发送普通客服消息
                                    $this->sendCustomMessage($message['FromUserName'], 'image', $rs['media_id']);
                                    return true;
                                } else {
                                    // 图片回复
                                    return new Image($rs['media_id']);
                                }

                            } elseif ($result['reply_type'] == 'voice') {
                                if ($flag == true) {
                                    // 发送普通客服消息
                                    $this->sendCustomMessage($message['FromUserName'], 'voice', $rs['media_id']);
                                    return true;
                                } else {
                                    // 声音回复
                                    return new Voice($rs['media_id']);
                                }

                            } elseif ($result['reply_type'] == 'video') {
                                if ($flag == true) {
                                    // 发送普通客服消息
                                    $replyData = [
                                        'media_id' => $rs['media_id'],
                                        'title' => $mediaInfo['title'],
                                        'description' => strip_tags($mediaInfo['content']),
                                    ];
                                    $this->sendCustomMessage($message['FromUserName'], 'video', $replyData);
                                    return true;
                                } else {
                                    // 视频回复
                                    return new Video($rs['media_id'], [
                                        'title' => $mediaInfo['title'],
                                        'description' => strip_tags($mediaInfo['content']),
                                    ]);
                                }
                            }
                        }

                    } elseif ($result['reply_type'] == 'news') {
                        // 图文素材
                        $replyData = [];
                        if (!empty($mediaInfo['article_id'])) {
                            $article_ids = explode(',', $mediaInfo['article_id']);
                            foreach ($article_ids as $key => $val) {
                                $artinfo = $this->wechatRepository->getWechatMedia(['id' => $val]);
                                $artinfo = $artinfo ? $artinfo->toArray() : [];

                                $replyData[$key] = [
                                    'title' => $artinfo['title'],
                                    'description' => isset($artinfo['digest']) && !empty($artinfo['digest']) ? $artinfo['digest'] : str_limit(strip_tags(html_out($artinfo['content'])), 100),
                                    'url' => isset($artinfo['link']) ? strip_tags(html_out($artinfo['link'])) : __HOST__ . url('article/index/wechat', ['id' => $artinfo['id']]),
                                    'image' => isset($artinfo['file']) ? FileHandle::getImgByOssUrl($artinfo['file']) : '',
                                ];
                            }
                        } else {
                            $flag = true;
                            $replyData[] = [
                                'title' => $mediaInfo['title'],
                                'description' => isset($mediaInfo['digest']) && !empty($mediaInfo['digest']) ? $mediaInfo['digest'] : str_limit(strip_tags(html_out($mediaInfo['content'])), 100),
                                'url' => isset($mediaInfo['link']) ? strip_tags(html_out($mediaInfo['link'])) : __HOST__ . url('article/index/wechat', ['id' => $mediaInfo['id']]),
                                'image' => isset($mediaInfo['file']) ? FileHandle::getImgByOssUrl($mediaInfo['file']) : '',
                            ];
                        }

                        if ($flag == true) {
                            $this->sendCustomMessage($message['FromUserName'], 'news', $replyData);
                            return true;
                        } else {
                            // 图文回复
                            $replyData = $this->newsItem($replyData);
                            return new News($replyData);
                        }
                    }
                }
            } else {
                // 文本回复
                if ($result['content']) {
                    $result['content'] = html_out($result['content']);
                    if ($flag == true) {
                        $this->sendCustomMessage($message['FromUserName'], 'text', $result['content']);
                        return true;
                    } else {
                        return new Text($result['content']);
                    }
                }
            }
        }

        return $endrs;
    }

    /**
     * 检测是否有模板消息待发送(最新一条记录)
     *
     * @param int $wechat_id 微信通ID
     * @param string $openid 微信用户标识
     */
    public function checkTemplateLog($wechat_id = 0, $openid = '')
    {
        $logs = $this->wechatRepository->getWechatTemplateLog($openid, $wechat_id);
        $logs = $logs ? $logs->toArray() : [];

        if ($logs) {
            // 组合发送数据
            $template = $this->wechatRepository->getWechatTemplate(['code' => $logs['code']]);

            $message = [
                'touser' => $logs['openid'],
                'template_id' => $template->template_id,
                'url' => $logs['url'],
                'data' => unserialize($logs['data'])
            ];
            $rs = $this->weObj->template_message->send($message);
            if ($rs) {
                // 更新记录模板消息ID
                $this->wechatRepository->setWechatTemplateLog(['code' => $logs['code'], 'openid' => $logs['openid'], 'wechat_id' => $wechat_id], ['msgid' => $rs['msgid']]);
            }
        }
    }


    /**
     * 主动发送消息给用户 统一方法
     *
     * @param string $openid
     * @param string $msgtype
     * @param string|array $replyData
     */
    public function sendCustomMessage($openid = '', $msgtype = '', $replyData)
    {
        $msg = [];
        if ($msgtype == 'text') {
            $msg = new Text($replyData);
        } elseif ($msgtype == 'image') {
            $msg = new Image($replyData);
        } elseif ($msgtype == 'voice') {
            $msg = new Voice($replyData);
        } elseif ($msgtype == 'video') {
            $msg = new Video($replyData['media_id'], [
                'title' => $replyData['title'],
                'description' => $replyData['description'],
            ]);
        } elseif ($msgtype == 'music') {
            $msg = new Music([
                'title' => $replyData['title'],
                'description' => $replyData['description'],
                'url' => $replyData['musicurl'],
                'hq_url' => $replyData['hqmusicurl'],
            ]);
        } elseif ($msgtype == 'news') {
            $items = $this->newsItem($replyData);
            $msg = new News($items);
        }
        $this->weObj->customer_service->message($msg)->to($openid)->send();
    }

    /**
     * 图文消息数据组装
     * @param array $replyData
     * @return array
     */
    public function newsItem($replyData = [])
    {
        $items = [];
        foreach ($replyData as $item) {
            $items[] = new NewsItem([
                'title' => $item['title'],
                'description' => $item['description'],
                'url' => $item['url'],
                'image' => $item['image'],
            ]);
        }

        return $items;
    }
}
