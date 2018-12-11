<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\NotifyRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\NotifyModel;

class NotifyRepository implements NotifyRepositoryInterface
{

    private $notifyModel;

    public function __construct(
        NotifyModel $notifyModel
    )
    {
        $this->notifyModel = $notifyModel;
    }

    public function getNotifiesByPage($type)
    {
        $where['terminal'] = $type;
        $res = $this->notifyModel->getNotifiesByPage($where);
        foreach ($res as $re) {
            $re->img_oss = FileHandle::getImgByOssUrl($re->img);
        }
        return $res;
    }

    public function getNotify($id)
    {
        $where['id'] = $id;
        $re = $this->notifyModel->getNotify($where);
        $re->img_oss = FileHandle::getImgByOssUrl($re->img);
        return $re;
    }

    public function setNotify($data, $id)
    {
        $where['id'] = $id;
        $updata = [];
        foreach ($data as $key => $val) {
            if ($key == 'img') {
                $path = 'notify';
                $updata[$key] = FileHandle::upLoadImage($val, $path);
                if(!empty($data['img_old'])){
                    FileHandle::deleteFile($data['img_old']);
                }
            } elseif ($key == 'img_old') {
                continue;
            } else {
                $updata[$key] = $val;
            }
        }
        return $this->notifyModel->setNotify($where, $updata);
    }

    public function addNotify($data)
    {
        $updata = [];
        foreach ($data as $key => $val) {
            if ($key == 'img') {
                $path = 'notify';
                $updata[$key] = FileHandle::upLoadImage($val, $path);
            } else {
                $updata[$key] = $val;
            }
        }
        $updata['add_time'] = time();
        return $this->notifyModel->addNotify($updata);
    }

    public function delNotify($img, $id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->notifyModel->delNotify($where);
        if ($re) {
            FileHandle::deleteFile($img);
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $data['id'];
        $updata['is_notify'] = $data['val'];
        $re = $this->notifyModel->setNotify($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}