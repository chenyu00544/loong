<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\WxappRepositoryInterface;
use App\Http\Models\Shop\WxappModel;

class WxappRepository implements WxappRepositoryInterface
{

    private $wxappModel;

    public function __construct(
        WxappModel $wxappModel
    )
    {
        $this->wxappModel = $wxappModel;
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
        return $this->wxappModel->setWxapp($where, $data);
    }

    public function addWxapp($data, $user)
    {
        $data['ru_id'] = $user->ru_id;
        $data['add_time'] = time();
        return $this->wxappModel->addWxapp($data);
    }
}