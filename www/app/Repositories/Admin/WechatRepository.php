<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\WechatRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Shop\WechatMediaModel;
use App\Http\Models\Shop\WechatModel;
use App\Http\Models\Shop\WechatReplyModel;
use App\Http\Models\Shop\WechatRuleKeywordsModel;

class WechatRepository implements WechatRepositoryInterface
{

    protected $wechat_id = 1;

    private $wechatModel;
    private $wechatReplyModel;
    private $wechatMediaModel;
    private $wechatRuleKeywordsModel;

    public function __construct(
        WechatModel $wechatModel,
        WechatReplyModel $wechatReplyModel,
        WechatMediaModel $wechatMediaModel,
        WechatRuleKeywordsModel $wechatRuleKeywordsModel
    )
    {
        $this->wechatModel = $wechatModel;
        $this->wechatReplyModel = $wechatReplyModel;
        $this->wechatMediaModel = $wechatMediaModel;
        $this->wechatRuleKeywordsModel = $wechatRuleKeywordsModel;
    }

    public function initParam($user)
    {
        $where['ru_id'] = $user->ru_id;
        $req = $this->wechatModel->getWechat($where);
        return $req->id;
    }

    public function getWechat($user)
    {
        $where['ru_id'] = $user->ru_id;
        $req = $this->wechatModel->getWechat($where);
        if ($req) {
            $str = '';
            $start = 3;
            for ($i = 0; $i < strlen($req->appsecret); $i++) {
                if ($i < $start) {
                    $str .= $req->appsecret[$i];
                } elseif ($i >= strlen($req->appsecret) - $start) {
                    $str .= $req->appsecret[$i];
                } else {
                    $str .= '*';
                }
            }
            $req->appsecret = $str;
        }
        return $req;
    }

    public function setWechat($data, $id)
    {
        $where['ru_id'] = $id;
        if (strpos($data['appsecret'], '******') !== false) {
            unset($data['appsecret']);
        }
        $re = $this->wechatModel->setWechat($where, $data);
        $conf = $this->wechatModel->getWechat($where);
        if ($conf) {
            RedisCache::set('wechat_config', $conf->toArray());
        }
        return $re;
    }

    public function addWechat($data, $user)
    {
        $data['ru_id'] = $user->ru_id;
        $data['time'] = time();
        return $this->wechatModel->addWechat($data);
    }

    public function getWechatReplyAutos($type)
    {
        $subscribes = $this->wechatReplyModel->getWechatReplys(['type' => $type, 'wechat_id' => $this->wechat_id]);
        foreach ($subscribes as $subscribe) {
            $media = $this->wechatMediaModel->getWechatMedia(['id' => $subscribe->media_id, 'wechat_id' => $this->wechat_id], ['file', 'type', 'file_name', 'article_id', 'title', 'content']);
            if ($media) {
                if ($media->article_id != '') {
                    $ids = explode(',', $media->article_id);
                    $medias = $this->wechatMediaModel->getWechatMediasIn($ids);
                    foreach ($medias as $media) {
                        $media->file = FileHandle::getImgByOssUrl($media->file);
                    }
                    $subscribe->medias = $medias;
                }
                $media->file = FileHandle::getImgByOssUrl($media->file);
                $subscribe->media = $media;
            }
        }
        return $subscribes;
    }

    public function getWechatReplyAuto($id)
    {
        $subscribe = $this->wechatReplyModel->getWechatReply(['id' => $id]);
        $media = $this->wechatMediaModel->getWechatMedia(['id' => $subscribe->media_id, 'wechat_id' => $this->wechat_id], ['file', 'type', 'file_name', 'article_id', 'title', 'content']);
        $ruleKey = $this->wechatRuleKeywordsModel->getWechatRuleKeywords(['rid' => $id]);
        $subscribe->rule = $ruleKey;
        if ($media) {
            if ($media->article_id != '') {
                $ids = explode(',', $media->article_id);
                $medias = $this->wechatMediaModel->getWechatMediasIn($ids);
                foreach ($medias as $media) {
                    $media->file = FileHandle::getImgByOssUrl($media->file);
                }
                $subscribe->medias = $medias;
            } else {
                $media->file = FileHandle::getImgByOssUrl($media->file);
            }
            $subscribe->media = $media;
        }
        return $subscribe;
    }

    public function setWechatReplyAuto($data, $user)
    {
        $updata['type'] = $data['type'];
        $updata['wechat_id'] = $this->wechat_id;
        $media_id = 0;
        $reply_type = '';
        $re = [];
        switch ($data['type']) {
            case 'subscribe':
                if (!empty($data['content'])) {
                    $updata['content'] = $data['content'];
                    $updata['reply_type'] = 'text';
                } elseif (!empty($data['media_id'])) {
                    $mediaIds = explode(',', $data['media_id']);
                    $mediaWhere['wechat_id'] = $this->wechat_id;
                    if (count($mediaIds) > 1) {
                        $mediaWhere['article_id'] = $data['media_id'];
                    } else {
                        $mediaWhere['id'] = $data['media_id'];
                    }
                    $media = $this->wechatMediaModel->getWechatMedia($mediaWhere);
                    if ($media) {
                        $media_id = $media->id;
                    } else {
                        $mediaData = [
                            'wechat_id' => $this->wechat_id,
                            'is_show' => 1,
                            'add_time' => time(),
                            'type' => 'news',
                            'article_id' => count($mediaIds) > 1 ? $data['media_id'] : 0,
                        ];
                        $media = $this->wechatMediaModel->addWechatMedia($mediaData);
                        $media_id = $media->id;
                    }
                    $updata['reply_type'] = $media->type;
                }
                if ($data['id'] > 0) {
                    $updata['media_id'] = $media_id;
                    $re = $this->wechatReplyModel->setWechatReply(['id' => $data['id']], $updata);
                } else {
                    $updata['media_id'] = $media_id;
                    $updata['add_time'] = time();
                    $re = $this->wechatReplyModel->addWechatReply($updata);
                }
                break;
            case 'autoreply':
                if (!empty($data['content'])) {
                    $updata['content'] = $data['content'];
                } elseif (!empty($data['media_id'])) {
                    $mediaIds = explode(',', $data['media_id']);
                    $mediaWhere['wechat_id'] = $this->wechat_id;
                    if (count($mediaIds) > 1) {
                        $mediaWhere['article_id'] = $data['media_id'];
                    } else {
                        $mediaWhere['id'] = $data['media_id'];
                    }
                    $media = $this->wechatMediaModel->getWechatMedia($mediaWhere);
                    if ($media) {
                        $media_id = $media->id;
                    } else {
                        $mediaData = [
                            'wechat_id' => $this->wechat_id,
                            'is_show' => 1,
                            'add_time' => time(),
                            'type' => 'news',
                            'article_id' => count($mediaIds) > 1 ? $data['media_id'] : 0,
                        ];
                        $media = $this->wechatMediaModel->addWechatMedia($mediaData);
                        $media_id = $media->id;
                    }
                }
                if ($data['id'] > 0) {
                    $updata['media_id'] = $media_id;
                    $re = $this->wechatReplyModel->setWechatReply(['id' => $data['id']], $updata);
                } else {
                    $updata['media_id'] = $media_id;
                    $updata['add_time'] = time();
                    $re = $this->wechatReplyModel->addWechatReply($updata);
                }
                break;
            case 'keywords':
                $rule_keywords = !empty($data['rule_keywords']) ? $data['rule_keywords'] : '';
                $rule_name = !empty($data['rule_name']) ? $data['rule_name'] : '';
                if ($rule_name == '') {
                    return false;
                }
                if (!empty($data['content'])) {
                    $updata['content'] = $data['content'];
                    $updata['reply_type'] = 'text';
                } elseif (!empty($data['media_id'])) {
                    $mediaIds = explode(',', $data['media_id']);
                    $mediaWhere['wechat_id'] = $this->wechat_id;
                    if (count($mediaIds) > 1) {
                        $mediaWhere['article_id'] = $data['media_id'];
                    } else {
                        $mediaWhere['id'] = $data['media_id'];
                    }
                    $media = $this->wechatMediaModel->getWechatMedia($mediaWhere);
                    if ($media) {
                        $media_id = $media->id;
                    } else {
                        $mediaData = [
                            'wechat_id' => $this->wechat_id,
                            'is_show' => 1,
                            'add_time' => time(),
                            'type' => 'news',
                            'article_id' => count($mediaIds) > 1 ? $data['media_id'] : 0,
                        ];
                        $media = $this->wechatMediaModel->addWechatMedia($mediaData);
                        $media_id = $media->id;
                    }
                    $updata['reply_type'] = $media->type;
                }
                $updata['rule_name'] = $rule_name;
                if ($data['id'] > 0) {
                    $reply_id = $data['id'];
                    $updata['media_id'] = $media_id;
                    $re = $this->wechatReplyModel->setWechatReply(['id' => $data['id']], $updata);
                } else {
                    $updata['media_id'] = $media_id;
                    $updata['add_time'] = time();
                    $re = $this->wechatReplyModel->addWechatReply($updata);
                    $reply_id = $re->id;
                }
                $ruleWhere = [
                    'rid' => $reply_id,
                ];
                $replyRule = $this->wechatRuleKeywordsModel->getWechatRuleKeywords($ruleWhere);
                if ($replyRule) {
                    $this->wechatRuleKeywordsModel->setWechatRuleKeywords(['rid' => $reply_id], ['rule_keywords' => $rule_keywords]);
                } else {
                    $ruleKeyData = [
                        'rid' => $reply_id,
                        'wechat_id' => $this->wechat_id,
                        'rule_keywords' => $rule_keywords,
                    ];
                    $this->wechatRuleKeywordsModel->addWechatRuleKeywords($ruleKeyData);
                }
                break;
        }
        return $re;
    }

    public function delWechatReplyAuto($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];

        $where['id'] = $id;
        $re = $this->wechatReplyModel->delWechatReply($where);
        $this->wechatRuleKeywordsModel->delWechatRuleKeywords(['rid' => $id]);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getWechatMaterialByPage($type)
    {
        $newses = $this->wechatMediaModel->getWechatMediaByPage(['wechat_id' => $this->wechat_id, 'type' => $type]);
        $morenewses = [];
        foreach ($newses as $news) {
            $news->file = FileHandle::getImgByOssUrl($news->file);
            if ($type == 'news') {
                if ($news->article_id != '') {
                    $newIds = explode(',', $news->article_id);
                    $morenews = $this->wechatMediaModel->getWechatMediasIn($newIds);
                    $news->morenewses = $morenews;
                    $morenewses[] = $news;
                }
            }
        }
        $return['newses'] = $morenewses;
        $return['news'] = $newses;
        return $return;
    }

    public function getWechatMaterial($id)
    {
        $material = $this->wechatMediaModel->getWechatMedia(['id' => $id]);
        $material->file = FileHandle::getImgByOssUrl($material->file);
        return $material;
    }

    public function getWechatMaterials($id)
    {
        $materials = $this->wechatMediaModel->getWechatMedias(['type' => $id, 'article_id' => '']);
        foreach ($materials as $material) {
            $material->file = FileHandle::getImgByOssUrl($material->file);
        }
        return $materials;
    }

    public function modifyWechatMaterial($data, $user, $id = 0)
    {
        if ($id == 0) {
            $wechat = $this->getWechat($user);
            $updata['wechat_id'] = $wechat->id;
            $updata['add_time'] = time();
        } else {
            $where['id'] = $id;
            $media = $this->wechatMediaModel->getWechatMedia($where);
        }
        $updata['edit_time'] = time();
        $path = 'wechat_material';
        switch ($data['type']) {
            case 'news':
            case 'image':
                foreach ($data as $key => $value) {
                    if ($key == 'file') {
                        if ($value && $value->isValid()) {
                            if (!empty($media)) {
                                FileHandle::deleteFile($media->file);
                            }
                            // 验证文件大小
                            if ($value->getSize() > 2 * 1024 * 1024) {
                                return false;
                            }
                            $updata['size'] = $value->getSize();
                            // 验证文件格式
                            if (!in_array($value->getMimeType(), ['image/jpeg', 'image/png'])) {
                                return false;
                            }
                            $updata['file'] = FileHandle::upLoadImage($value, $path);
                            $updata['file_name'] = explode('/', $updata['file'])[2];
                        } else {
                            return false;
                        }
                    } else {
                        $updata[$key] = $value;
                    }
                }
                break;
            case 'voice':
                foreach ($data as $key => $value) {
                    if ($key == 'file') {
                        if ($value && $value->isValid()) {
                            if (!empty($media)) {
                                FileHandle::deleteFile($media->file);
                            }

                            // 验证文件大小
                            if ($value->getSize() > 5 * 1024 * 1024) {
                                return false;
                            }
                            $updata['size'] = $value->getSize();

                            // 验证文件格式
                            if (!in_array($value->getMimeType(), ['audio/amr', 'audio/x-mpeg', 'audio/mpeg', 'audio/mp3'])) {
                                return false;
                            }
                            $updata['file'] = FileHandle::upLoad_file($value, $path);
                            $updata['file_name'] = explode('/', $updata['file'])[2];
                        } else {
                            return false;
                        }
                    } else {
                        $updata[$key] = $value;
                    }
                }
                break;
            case 'video':
                foreach ($data as $key => $value) {
                    if ($key == 'file') {
                        if ($value && $value->isValid()) {
                            if (!empty($media)) {
                                FileHandle::deleteFile($media->file);
                            }

                            // 验证文件大小
                            if ($value->getSize() > 10 * 1024 * 1024) {
                                return false;
                            }
                            $updata['size'] = $value->getSize();

                            // 验证文件格式
                            if (!in_array($value->getMimeType(), ['video/mp4'])) {
                                return false;
                            }
                            $updata['file'] = FileHandle::upLoad_file($value, $path);
                            $updata['file_name'] = explode('/', $updata['file'])[2];
                        } else {
                            return false;
                        }
                    } else {
                        $updata[$key] = $value;
                    }
                }
                break;

        }
        if ($id == 0) {
            $re = $this->wechatMediaModel->addWechatMedia($updata);
        } else {
            $re = $this->wechatMediaModel->setWechatMedia($where, $updata);
        }
        return $re;
    }

    public function delWechatMaterial($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];

        $where['id'] = $id;
        $media = $this->wechatMediaModel->getWechatMedia($where);
        FileHandle::deleteFile($media->file);
        $re = $this->wechatMediaModel->delWechatMedia($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}