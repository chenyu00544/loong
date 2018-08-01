<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\WechatRepositoryInterface;
use App\Http\Models\Shop\WechatModel;

class WechatRepository implements WechatRepositoryInterface
{

    private $wechatModel;

    public function __construct(
        WechatModel $wechatModel
    )
    {
        $this->wechatModel = $wechatModel;
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
        return $this->wechatModel->setWechat($where, $data);
    }

    public function addWechat($data, $user)
    {
        $data['ru_id'] = $user->ru_id;
        $data['time'] = time();
        return $this->wechatModel->addWechat($data);
    }
}