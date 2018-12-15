<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\NotifyRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\App\NotifyModel;
use App\Http\Models\App\UsersModel;

class NotifyRepository implements NotifyRepositoryInterface
{

    private $notifyModel;
    private $usersModel;

    public function __construct(
        NotifyModel $notifyModel,
        UsersModel $usersModel
    )
    {
        $this->notifyModel = $notifyModel;
        $this->usersModel = $usersModel;
    }

    public function getNotifies($data, $uid = 0)
    {
        $return = [];
        $where['terminal'] = 'app';
        $where['type'] = '4';
        if ($uid > 0) {
            if (!empty($data['server_id']) && $data['server_id'] > 0) {
                $swhere['id'] = $data['server_id'];
                $server = $this->notifyModel->getNotify($swhere);
                $server->img = FileHandle::getImgByOssUrl($server->img);
                $server->id_str = $server->id . '';
                $server->add_time_format = date('Y-m-d', $server->add_time);
                $return['server'] = $server;
            } else {
                $servers = $this->notifyModel->getNotifies($where);
                $rand = rand(0, $servers->count() - 1);
                $where['user_id'] = $uid;
                $uData['server'] = $servers[$rand]->id;
                $this->usersModel->setUsers($where, $uData);
                $servers[$rand]->img = FileHandle::getImgByOssUrl($servers[$rand]->img);
                $servers[$rand]->id_str = $servers[$rand]->id . '';
                $servers[$rand]->add_time_format = date('Y-m-d', $servers[$rand]->add_time);
                $return['server'] = $servers[$rand];
            }
        } else {
            $server = $this->notifyModel->getNotify($where, ['*'], 'ASC');
            if ($server) {
                $server->img = FileHandle::getImgByOssUrl($server->img);
                $server->id_str = $server->id . '';
                $server->add_time_format = date('Y-m-d', $server->add_time);
            }
            $return['server'] = $server;
        }

        $where['terminal'] = 'app';
        $where['type'] = '3';
        $where['is_notify'] = '1';
        if (!empty($data['notify_faat_id']) && $data['notify_faat_id'] > 0) {
            $faat = $this->notifyModel->getNotifyGT($where, $data['notify_faat_id']);
            if ($faat) {
                $faat->img = FileHandle::getImgByOssUrl($faat->img);
                $faat->id_str = $faat->id . '';
                $faat->add_time_format = date('Y-m-d', $faat->add_time);
            }
            $return['notify_faat'] = $faat;
        } else {
            $faat = $this->notifyModel->getNotify($where);
            if ($faat) {
                $faat->img = FileHandle::getImgByOssUrl($faat->img);
                $faat->id_str = $faat->id . '';
                $faat->add_time_format = date('Y-m-d', $faat->add_time);
            }
            $return['notify_faat'] = $faat;
        }

        $where['terminal'] = 'app';
        $where['type'] = '2';
        if (!empty($data['notify_event_id']) && $data['notify_event_id'] > 0) {
            $event = $this->notifyModel->getNotifyGT($where, $data['notify_event_id']);
            if ($event) {
                $event->img = FileHandle::getImgByOssUrl($event->img);
                $event->id_str = $event->id . '';
                $event->add_time_format = date('Y-m-d', $event->add_time);
            }
            $return['notify_event'] = $event;
        } else {
            $event = $this->notifyModel->getNotify($where);
            if ($event) {
                $event->img = FileHandle::getImgByOssUrl($event->img);
                $event->id_str = $event->id . '';
                $event->add_time_format = date('Y-m-d', $event->add_time);
            }
            $return['notify_event'] = $event;
        }

        $where['terminal'] = 'app';
        $where['type'] = '1';
        if (!empty($data['notify_acticle_id']) && $data['notify_acticle_id'] > 0) {
            $acticle = $this->notifyModel->getNotifyGT($where, $data['notify_acticle_id']);
            if ($acticle) {
                $acticle->img = FileHandle::getImgByOssUrl($acticle->img);
                $acticle->id_str = $acticle->id . '';
                $acticle->add_time_format = date('Y-m-d', $acticle->add_time);
            }
            $return['notify_acticle'] = $acticle;
        } else {
            $acticle = $this->notifyModel->getNotify($where);
            if ($acticle) {
                $acticle->img = FileHandle::getImgByOssUrl($acticle->img);
                $acticle->id_str = $acticle->id . '';
                $acticle->add_time_format = date('Y-m-d', $acticle->add_time);
            }
            $return['notify_acticle'] = $acticle;
        }
        return $return;
    }

    public function getOneNotify($data)
    {
        $where['terminal'] = 'app';
        $where['is_notify'] = 1;
        $re = $this->notifyModel->getNotify($where);
        $re->img = FileHandle::getImgByOssUrl($re->img);
        return $re;
    }

    public function getNotifiesForPid($data)
    {

    }
}