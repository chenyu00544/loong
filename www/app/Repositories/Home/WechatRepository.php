<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Home;

use App\Contracts\WechatRepositoryInterface;
use App\Http\Models\Shop\WechatCustomMessageModel;
use App\Http\Models\Shop\WechatMassageHistoryModel;
use App\Http\Models\Shop\WechatMediaModel;
use App\Http\Models\Shop\WechatMessageLogModel;
use App\Http\Models\Shop\WechatReplyModel;
use App\Http\Models\Shop\WechatTemplateLogModel;
use App\Http\Models\Shop\WechatTemplateModel;
use App\Http\Models\Shop\WechatUserModel;
use App\Http\Models\Shop\WechatUserTagModel;
use Illuminate\Support\Facades\Storage;

class WechatRepository implements WechatRepositoryInterface
{

    private $wechatMessageLogModel;
    private $wechatUserModel;
    private $wechatUserTagModel;
    private $wechatMassageHistoryModel;
    private $wechatReplyModel;
    private $wechatTemplateModel;
    private $wechatTemplateLogModel;
    private $wechatMediaModel;
    private $wechatCustomMessageModel;

    public function __construct(
        WechatMessageLogModel $wechatMessageLogModel,
        WechatUserModel $wechatUserModel,
        WechatUserTagModel $wechatUserTagModel,
        WechatMassageHistoryModel $wechatMassageHistoryModel,
        WechatReplyModel $wechatReplyModel,
        WechatTemplateModel $wechatTemplateModel,
        WechatTemplateLogModel $wechatTemplateLogModel,
        WechatMediaModel $wechatMediaModel,
        WechatCustomMessageModel $wechatCustomMessageModel
    )
    {
        $this->wechatMessageLogModel = $wechatMessageLogModel;
        $this->wechatUserModel = $wechatUserModel;
        $this->wechatUserTagModel = $wechatUserTagModel;
        $this->wechatMassageHistoryModel = $wechatMassageHistoryModel;
        $this->wechatReplyModel = $wechatReplyModel;
        $this->wechatTemplateModel = $wechatTemplateModel;
        $this->wechatTemplateLogModel = $wechatTemplateLogModel;
        $this->wechatMediaModel = $wechatMediaModel;
        $this->wechatCustomMessageModel = $wechatCustomMessageModel;
    }

    /**
     * 微信消息日志队列之存入数据库
     * @param  integer $wechat_id
     * @param  array $message
     */
    public function addWechatMessageLog($wechat_id = 0, $message = [])
    {
        //判断菜单点击事件
        if ($message['MsgType'] == 'event') {
            $data = [
                'wechat_id' => $wechat_id,
                'fromusername' => $message['FromUserName'],
                'createtime' => $message['CreateTime'],
                'msgtype' => $message['MsgType'],
                'keywords' => $message['EventKey'],
            ];
            // 使用FromUserName + CreateTime + keywords 排重
            $where = [
                'wechat_id' => $wechat_id,
                'fromusername' => $message['FromUserName'],
                'createtime' => $message['CreateTime'],
                'keywords' => $data['keywords'],
            ];
        } else {
            $data = [
                'wechat_id' => $wechat_id,
                'fromusername' => $message['FromUserName'],
                'createtime' => $message['CreateTime'],
                'msgtype' => $message['MsgType'],
                'keywords' => $message['Content'],
                'msgid' => $message['MsgId'],
            ];
            // 使用msgid + keywords排重
            $where = [
                'wechat_id' => $wechat_id,
                'msgid' => $data['msgid'],
                'keywords' => $data['keywords'],
            ];
        }
        // 插入
        $rs = $this->wechatMessageLogModel->getWechatMessageLog($where);
        if (is_null($rs)) {
            $this->wechatMessageLogModel->addWechatMessageLog($data);
        }
    }

    /**
     * 兼容更新用户关注状态（未配置微信通之前关注的粉丝）
     * @param string $wechat_id
     * @param array $user
     */
    public function updateWechatUserSubscribeAction($wechat_id = 0, $user = [])
    {
        if ($user && !empty($user['unionid'])) {
            $user_data = [
                'subscribe' => $user['subscribe'],
                'subscribe_time' => $user['subscribe_time'],
            ];
            $this->wechatUserModel->getWechat(['unionid' => $user['unionid'], 'wechat_id' => $wechat_id], ['wechat_openid', 'unionid']);
            $res = $this->wechatUserModel->getWechat(['unionid' => $user['unionid'], 'wechat_id' => $wechat_id], ['wechat_openid', 'unionid']);
            $res = $res ? $res->toArray() : [];
            if ($res) {
                $this->wechatUserModel->setWechat(['unionid' => $user['unionid'], 'wechat_id' => $wechat_id], $user_data);
            }
        }
    }

    /**
     * 获取二维码的场景值
     */
    public function getRevSceneId($eventKey = '')
    {
        if (isset($eventKey)) {
            return str_replace('qrscene_', '', $eventKey);
        } else {
            return false;
        }
    }

    /**
     * 关注处理更新
     * @param $wechat_id
     * @param array $user
     * @param string $scene_id
     * @param int $ru_id
     */
    public function subscribeAction($wechat_id = 0, $user = [], $scene_id = '', $ru_id = 0)
    {
        if ($user && $wechat_id) {
            // 组合数据
            $data['wechat_id'] = $wechat_id;
            $data['subscribe'] = $user['subscribe'];
            $data['wechat_openid'] = $user['openid'];
            $data['nickname'] = $user['nickname'];
            $data['sex'] = $user['sex'];
            $data['language'] = $user['language'];
            $data['city'] = $user['city'];
            $data['province'] = $user['province'];
            $data['country'] = $user['country'];
            $data['headimgurl'] = $user['headimgurl'];
            $data['subscribe_time'] = $user['subscribe_time'];
            $data['remark'] = $user['remark'];
            $data['groupid'] = isset($user['groupid']) ? $user['groupid'] : '';
            $data['unionid'] = isset($user['unionid']) ? $user['unionid'] : '';
            $data['from'] = 0; // 微信粉丝来源 0 关注公众号

            // 公众号启用微信开发者平台，平台检查unionid, 商家不检查unionid
            if ($ru_id == 0 && !empty($data['unionid'])) {
                $condition = ['unionid' => $data['unionid'], 'wechat_id' => $wechat_id];
            } else {
                unset($data['unionid']);
                $condition = ['wechat_openid' => $data['openid'], 'wechat_id' => $wechat_id];
            }
            $result = $this->wechatUserModel->getWechat($condition, ['wechat_openid', 'unionid']);

            // 未关注
            if (empty($result)) {
//                if ($ru_id == 0) {
//                    // 查询推荐人ID
//                    $scenes = $this->return_is_drp($scene_id);
//                    if ($scenes['is_drp'] == true) {
//                        $data['drp_parent_id'] = !empty($scenes['drp_parent_id']) ? $scenes['drp_parent_id'] : 0;
//                        $data['parent_id'] = !empty($scenes['drp_parent_id']) ? $scenes['drp_parent_id'] : 0;
//                    } else {
//                        $data['drp_parent_id'] = !empty($scenes['parent_id']) ? $scenes['parent_id'] : 0;
//                        $data['parent_id'] = !empty($scenes['parent_id']) ? $scenes['parent_id'] : 0;
//                    }
//                    // 更新扫码引荐二维码 推荐扫描量
//                    if ($scenes['is_drp'] == false && $data['parent_id'] > 0) {
//                        $this->shareQrcodeSubscribeAction($this->wechat_id, $data['parent_id']);
//                    }
//                }
//                $data['from'] = 0; // 微信粉丝来源 0 关注公众号
//                // 新增微信粉丝
//                WechatUser::create($data);
            } else {
                // 更新微信用户资料
                $this->wechatUserModel->setWechat($condition, $data);
            }

            // 已关注用户基本信息
            if ($result && $ru_id == 0) {
                $this->updateWechatUserUnionid($wechat_id, $user); //兼容更新平台粉丝unionid
            }
        }
    }

    /**
     * 取消关注处理
     * @param int $wechat_id
     * @param string $openid
     */
    public function unsubscribeAction($wechat_id = 0, $openid = '')
    {
        // 未关注
        $where['wechat_openid'] = $openid;
        $where['wechat_id'] = $wechat_id;

        $rs = $this->wechatUserModel->getWechatCount($where);
        // 修改关注状态
        if ($rs > 0) {
            $this->wechatUserModel->setWechat($where, ['subscribe' => 0]);

            // 同步用户标签 (取消关注 微信端标签也删除了)
            $this->wechatUserTagModel->delWechatUserTag($where);
        }
    }

    /**
     * 兼容更新平台粉丝unionid
     * 原无unionid，现在unionid 通过 openid 更新 unionid
     * @param int $wechat_id
     * @param array $user
     */
    public function updateWechatUserUnionid($wechat_id = 0, $user = [])
    {
        if ($user) {
            // 组合数据
            $data = [
                'wechat_id' => $wechat_id,
                'wechat_openid' => $user['openid'],
                'unionid' => $user['unionid'] ?? ''
            ];
            // unionid 微信开放平台唯一标识
            if (!empty($data['unionid'])) {
                // 兼容查询用户openid
                $where = ['wechat_openid' => $user['openid'], 'wechat_id' => $wechat_id];
                $res = $this->wechatUserModel->getWechat($where, ['unionid', 'ect_uid']);
                $res = $res ? $res->toArray() : [];
                if (empty($res['unionid'])) {
                    $this->wechatUserModel->setWechat($where, $data);
//                    if (!empty($res['ect_uid'])) {
//                        // 更新社会化登录用户信息
//                        $connect_userinfo = $this->connectUserService->getConnectUserinfo($user['unionid'], 'wechat');
//                        if (empty($connect_userinfo)) {
//                            ConnectUser::where(['open_id' => $user['openid']])->update(['open_id' => $user['unionid']]);
//                        }
//                        $user['user_id'] = $res['ect_uid'];
//                        $this->connectUserService->updateConnectUser($user, 'wechat');
//                    }
                }
            }
        }
    }

    /**
     * 返回扫码推荐或分销推荐信息
     * @param $scene_id
     * @return
     */
    public function return_is_drp($scene_id = '')
    {
//        $scenes = [
//            'is_drp' => false,
//            'parent_id' => 0,
//            'drp_parent_id' => 0,
//        ];
//        $drp_file = app_path('Modules/Admin/Controllers/DrpController.php');
//        if (strpos($scene_id, 'u') === 0) {
//            // 推荐uid
//            $scene_uid = str_replace('u=', '', $scene_id);
//            $parent_id = intval($scene_uid);
//
//            $users = Users::select('user_id')->where(['user_id' => $parent_id])->first();
//            $users = $users ? $users->toArray() : [];
//            $parent_id = !empty($users) ? $parent_id : 0;
//
//            $scenes['parent_id'] = $parent_id;
//            $scenes['is_drp'] = false;
//
//        } elseif (strpos($scene_id, 'd') === 0 && file_exists($drp_file)) {
//            // 推荐分销商id
//            $scene_did = str_replace('d=', '', $scene_id);
//            $drp_parent_id = intval($scene_did);
//
//            $drp = DrpShop::select('user_id')->where(['user_id' => $drp_parent_id, 'audit' => 1])->first();
//            $drp = $drp ? $drp->toArray() : [];
//
//            $drp_parent_id = !empty($drp) ? $drp_parent_id : 0;
//
//            $scenes['drp_parent_id'] = $drp_parent_id;
//            $scenes['is_drp'] = true;
//        }
//
//        return $scenes;
    }

    /**
     * 上报地理位置事件
     *
     * @param int $wechat_id
     * @param array $message
     */
    public function updateLocation($wechat_id = 0, $message = [])
    {
        if ($message) {
            if ($message['Latitude'] && $message['Longitude']) {

            }
        }
    }

    /**
     * 更新群发消息结果
     * @param int $wechat_id
     * @param array $message
     */
    public function updateWechatMassHistory($wechat_id = 0, $message = [])
    {
        if ($message) {
            $data = [
                'status' => $message['Status'],
                'totalcount' => $message['TotalCount'],
                'filtercount' => $message['FilterCount'],
                'sentcount' => $message['SentCount'],
                'errorcount' => $message['ErrorCount'],
            ];
            // 更新群发结果
            $this->wechatMassageHistoryModel->setWechatMassageHistory(['msg_id' => $message['MsgID'], 'wechat_id' => $wechat_id], $data);
        }
    }

    /**
     * 微信消息日志队列之处理发送状态
     * @param  integer $wechat_id
     * @param  array $message
     */
    public function messageLogAlignmentSend($wechat_id = 0, $message = [])
    {
        // 查询并更新发送状态
        if ($message['msgtype'] == 'event') {
            // 使用FromUserName + CreateTime + keywords 排重
            $where = [
                'wechat_id' => $wechat_id,
                'fromusername' => $message['fromusername'],
                'createtime' => $message['createtime'],
                'keywords' => $message['keywords'],
                'is_send' => 0
            ];
        } else {
            // 使用msgid + keywords 排重
            $where = [
                'wechat_id' => $wechat_id,
                'msgid' => $message['msgid'],
                'keywords' => $message['keywords'],
                'is_send' => 0
            ];
        }
        $this->wechatMessageLogModel->setWechatMessageLog($where, ['is_send' => 1]);

        // 删除已发送的消息记录
        $map[] = ['fromusername', '=', $message['fromusername']];
        $map[] = ['keywords', '=', $message['keywords']];
        $lastId = $this->wechatMessageLogModel->getWechatMessageLog($map);
        $lastId = $lastId->id;
        if (!empty($lastId)) {
            $map[] = ['is_send', '=', 1];
            $map[] = ['id', '<', $lastId];
            $this->wechatMessageLogModel->delWechatMessageLog($map);
        }
    }

    /**
     * 记录操作信息
     * @param int $wechat_id
     * @param array $user
     * @param array $content
     * @param int $is_wechat_admin
     */
    public function recordMsgAction($wechat_id = 0, $user = [], $content = [], $is_wechat_admin = 0)
    {
        if ($user) {
            $time = time();
            if (isset($user['unionid']) && $user['unionid']) {
                $uid = $this->wechatUserModel->getWechat(['openid' => $user['openid'], 'unionid' => $user['unionid'], 'subscribe' => 1, 'wechat_id' => $wechat_id]);
                if(!empty($uid)){
                    $uid = $uid->uid;
                }
            } else {
                $uid = $this->wechatUserModel->getWechat(['openid' => $user['openid'], 'subscribe' => 1, 'wechat_id' => $wechat_id]);
                if(!empty($uid)){
                    $uid = $uid->uid;
                }
            }
            
            if (!empty($uid) && $content['msg']) {
                $data = [
                    'uid' => $uid,
                    'msg' => $content['msg'],
                    'msgtype' => $content['msgtype'],
                    'wechat_id' => $wechat_id,
                    'send_time' => isset($content['createtime']) ? $content['createtime'] : $time
                ];
                // 微信公众号回复标识
                if ($is_wechat_admin > 0) {
                    $data['is_wechat_admin'] = $is_wechat_admin;
                }
                $this->wechatCustomMessageModel->addWechatCustomMessage($data);
            }
        }
    }

    /**
     * 更新模板消息通知结果
     * @param int $wechat_id
     * @param array $message
     */
    public function updateWechatTeamplateLog($wechat_id = 0, $message = [])
    {
        if ($message) {
            // 模板消息发送结束事件
            if ($message['Status'] == 'success') {
                // 推送成功
                $data = ['status' => 1];
            } elseif ($message['Status'] == 'failed:user block') {
                // 用户拒收
                $data = ['status' => 2];
            } else {
                // 发送失败
                $data = ['status' => 0]; // status 0 发送失败，1 发送与接收成功，2 用户拒收
            }
            // 更新模板消息发送状态
            $this->wechatTemplateLogModel->setWechatTemplateLog(['msgid' => $message['MsgID'], 'openid' => $message['FromUserName'], 'wechat_id' => $wechat_id], $data);
        }
    }

    public function getWechatTemplateLog($openid, $wechat_id)
    {
        $column = ['wechat_id', 'code', 'openid', 'data', 'url'];
        return $this->wechatTemplateLogModel->getWechatTemplateLog(['openid' => $openid, 'wechat_id' => $wechat_id, 'status' => 0], $column);
    }

    public function setWechatTemplateLog($where, $data)
    {
        return $this->wechatTemplateLogModel->setWechatTemplateLog($where, $data);
    }

    public function getWechatTemplate($data)
    {
        return $this->wechatTemplateModel->getWechatTemplate($data);
    }

    public function getWechatMessageLog($data)
    {
        $column = ['fromusername', 'createtime', 'keywords', 'msgid', 'msgtype'];
        return $this->wechatMessageLogModel->getWechatMessageLog($data, $column);
    }

    public function getWechatReply($data, $keywords = '')
    {
        return $this->wechatReplyModel->getWechatReplyHas($data, ['content', 'media_id', 'reply_type'], $keywords);
    }

    public function getWechatMedia($data)
    {
        $column = ['id', 'title', 'command', 'digest', 'content', 'file', 'type', 'file_name', 'article_id', 'link'];
        return $this->wechatMediaModel->getWechatMedia($data, $column);
    }

}
