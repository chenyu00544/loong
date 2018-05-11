<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\FriendRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\shop\FriendModel;

class FriendRepository implements FriendRepositoryInterface
{

    protected $friendModel;

    public function __construct(FriendModel $friendModel)
    {
        $this->friendModel = $friendModel;
    }

    public function getFriends($size = 10)
    {
        return $this->friendModel->getFriends([], ['*'], $size);
    }

    public function getFriend($id)
    {
        $where['link_id'] = $id;
        return $this->friendModel->getFriend($where);
    }

    public function setFriend($data, $id = 0)
    {
        if (!empty($id)) {
            $where['link_id'] = $id;
        }
        $req = ['code' => 5, 'msg' => '修改失败'];
        foreach ($data as $key => $value) {
            if ($key == 'id') {
                $where['link_id'] = $value;
            } elseif ($key == 'link_logo_bak') {
                $updata['link_logo'] = $value;
            } elseif ($key == 'link_logo') {
                $updata['link_logo'] = FileHandle::upLoadImage($value, 'friend_logo');
                $link_logo_bak = $data['link_logo_bak'];
            } else {
                $updata[$key] = $value;
            }
        }
        $re = $this->friendModel->setFriend($where, $updata);
        if ($re) {
            FileHandle::deleteFile($link_logo_bak);
            $req = ['code' => 1, 'msg' => '修改成功'];
        }
        return $req;
    }

    public function addFriend($data)
    {
        $saveData = [];
        foreach ($data as $key => $value) {
            if ($key == 'link_logo') {
                $saveData['link_logo'] = $this->saveFile($value);
            } elseif ($key == 'link_logo_url') {
                if (!empty($value)) {
                    $saveData['link_logo'] = $value;
                }
            } elseif ($key == 'link_logo_bak') {
                if (!empty($value)) {
                    $saveData['link_logo'] = $value;
                }
            } else {
                $saveData[$key] = $value;
            }
        }
        return $this->friendModel->addFriend($saveData);
    }

    public function delFriend($id, $uri)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['link_id'] = $id;
        $re = $this->friendModel->delFriend($where);
        if ($re) {
            FileHandle::deleteFile( $uri['img']);
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}