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
            ['count_msg', '<=', '5'],
            ['update_time', '>', time() - 48 * 3600],
        ];
        return $this->wxappServerSessionModel->getWxappServerSessionByPage($where, $keywords, ['*'], $size);
    }
}