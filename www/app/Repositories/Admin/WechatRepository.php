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

class WechatRepository implements WechatRepositoryInterface
{

    protected $wechat_id = 1;

    private $wechatModel;
    private $wechatReplyModel;
    private $wechatMediaModel;

    public function __construct(
        WechatModel $wechatModel,
        WechatReplyModel $wechatReplyModel,
        WechatMediaModel $wechatMediaModel
    )
    {
        $this->wechatModel = $wechatModel;
        $this->wechatReplyModel = $wechatReplyModel;
        $this->wechatMediaModel = $wechatMediaModel;
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

    public function getWechatReplyAuto()
    {
        $subscribe = $this->wechatReplyModel->getWechatReply(['type' => 'subscribe', 'wechat_id' => $this->wechat_id]);
        $media = $this->wechatMediaModel->getWechatMedia(['id' => $subscribe->media_id, 'wechat_id' => $this->wechat_id], ['file', 'type', 'file_name']);
        if ($media) {
            $media->file = FileHandle::getImgByOssUrl($media->file);
            $subscribe->media = $media;
        }
        return $subscribe;
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
                    $morenews = $this->wechatMediaModel->getWechatMedias($newIds);
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