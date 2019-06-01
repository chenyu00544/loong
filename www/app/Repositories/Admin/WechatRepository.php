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

    public function getWechatMaterialByPage()
    {
        $newses = $this->wechatMediaModel->getWechatMediaByPage(['wechat_id' => $this->wechat_id]);
        $morenewses = [];
        foreach ($newses as $news) {
            $news->file = FileHandle::getImgByOssUrl($news->file);
            if ($news->article_id != '') {
                $newIds = explode(',', $news->article_id);
                $morenews = $this->wechatMediaModel->getWechatMedias($newIds);
                $news->morenewses = $morenews;
                $morenewses[] = $news;
            }
        }
        $return['news'] = $newses;
        $return['newses'] = $morenewses;
        return $return;
    }
}