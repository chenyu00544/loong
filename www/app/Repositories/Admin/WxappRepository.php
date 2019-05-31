<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\WxappRepositoryInterface;
use App\Facades\RedisCache;
use App\Helper\Wxapp;
use App\Http\Models\Shop\WxappModel;
use App\Http\Models\Shop\WxappServerMsgModel;
use App\Http\Models\Shop\WxappServerSessionModel;

class WxappRepository implements WxappRepositoryInterface
{

    private $wxappModel;
    private $wxappServerSessionModel;
    private $wxappServerMsgModel;

    public function __construct(
        WxappModel $wxappModel,
        WxappServerSessionModel $wxappServerSessionModel,
        WxappServerMsgModel $wxappServerMsgModel
    )
    {
        $this->wxappModel = $wxappModel;
        $this->wxappServerSessionModel = $wxappServerSessionModel;
        $this->wxappServerMsgModel = $wxappServerMsgModel;
    }

    public function getWxapp($user)
    {
        $where['ru_id'] = $user->ru_id;
        $req = $this->wxappModel->getWxapp($where);
        if ($req) {
            $str = '';
            $start = 3;
            for ($i = 0; $i < strlen($req->wx_appsecret); $i++) {
                if ($i < $start) {
                    $str .= $req->wx_appsecret[$i];
                } elseif ($i >= strlen($req->wx_appsecret) - $start) {
                    $str .= $req->wx_appsecret[$i];
                } else {
                    $str .= '*';
                }
            }
            $req->wx_appsecret = $str;
        }
        return $req;
    }

    public function setWxapp($data, $id)
    {
        $where['ru_id'] = $id;
        if (strpos($data['wx_appsecret'], '******') !== false) {
            unset($data['wx_appsecret']);
        }
        $re = $this->wxappModel->setWxapp($where, $data);
        $conf = $this->wxappModel->getWxapp(['ru_id' => 0]);
        $_conf = [];
        if ($conf) {
            $_conf = $conf->toArray();
        }
        RedisCache::set('wxapp_config', $_conf);
        return $re;
    }

    public function addWxapp($data, $user)
    {
        $data['ru_id'] = $user->ru_id;
        $data['add_time'] = time();
        return $this->wxappModel->addWxapp($data);
    }

    public function getWxappSessionByPage($data)
    {
        $keywords = empty($data['keywords']) ? '' : $data['keywords'];
        $size = empty($data['page']) ? 10 : $data['page'];
        $where = [
            ['update_time', '>', time() - 48 * 3600],
        ];
        return $this->wxappServerSessionModel->getWxappServerSessionByPage($where, $keywords, ['*'], $size);
    }

    public function sendMsg($data)
    {
        $wxapp_config = RedisCache::get('wxapp_config');
        $wxapp = new Wxapp(['appid' => $wxapp_config['wx_appid'], 'secret' => $wxapp_config['wx_appsecret']]);
        $access_token = $wxapp->getAccessToken();
        $msg = [
            'access_token' => $access_token,
            'msgtype' => 'text'
        ];
        switch ($data['msg_type']) {
            case 1:
                $msg['msgtype'] = 'text';
                $msg['text'] = ['content' => $data['content']];
                break;
            case 2:
                $msg['msgtype'] = 'image';
                $msg['image'] = ['media_id' => $data['media_id']];
                break;
            case 3:
                $msg['msgtype'] = 'link';
                $msg['link'] = [
                    'title' => $data['link_title'],
                    'description' => $data['link_description'],
                    'url' => $data['link_url'],
                    'thumb_url' => $data['link_thumb_url'],
                ];
                break;
            case 4:
                $msg['msgtype'] = 'miniprogrampage';
                $msg['miniprogrampage'] = [
                    'title' => $data['wxapp_title'],
                    'pagepath' => $data['wxapp_pagepath'],
                    'thumb_media_id' => $data['wxapp_thumb_media_id'],
                ];
                break;
        }
        $ids = explode(',', $data['ids']);
        $ids = array_unique($ids);
        $wechats = $this->wxappServerSessionModel->getWxappServerSessions([], $ids);
        $re = false;
        foreach ($wechats as $wechat) {
            $msg['touser'] = $wechat->open_id;
            $re = $wxapp->sendServerMessage($msg);
            $this->wxappServerSessionModel->incrementWxappServerSession(['open_id' => $wechat->open_id]);
        }
        return $re;
    }

    public function uploadMedia($data)
    {
        $wxapp_config = RedisCache::get('wxapp_config');
        $wxapp = new Wxapp(['appid' => $wxapp_config['wx_appid'], 'secret' => $wxapp_config['wx_appsecret']]);
        $access_token = $wxapp->getAccessToken();
        $file = $data['img_file'];
        if ($file->isValid()) {
            $fileextension = $file->getClientOriginalExtension();
            $path = $file->move(base_path() . '/public/upload/tmp' , time().rand(10000,99999).'.'.$fileextension);
            $msg = [
                'access_token' => $access_token,
                'type' => 'image'
            ];
            if (class_exists('\CURLFile')) {
                $msg['media'] = new \CURLFile(realpath($path->getRealPath()));
            } else {
                $msg['media'] = '@' . realpath($path->getRealPath());
            }
            $re = $wxapp->uploadMedia($msg);
            @unlink($path);
            return $re;
        }
    }
}