<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UserRankRepositoryInterface;
use App\Http\Models\shop\UserRankModel;

class UserRankRepository implements UserRankRepositoryInterface
{

    protected $userRankModel;

    public function __construct(UserRankModel $userRankModel)
    {
        $this->userRankModel = $userRankModel;
    }

    public function getUserRanks()
    {
        return $this->userRankModel->getUserRanks([]);
    }

    public function setUserRanks($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['rank_id'] = $data['id'];
        $updata['show_price'] = $data['val'];
        $re = $this->userRankModel->setUserRanks($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    public function addUserRanks($data)
    {
        return $this->userRankModel->addUserRanks($data);
    }

    public function delUserRanks($id)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['rank_id'] = $id;
        $re = $this->userRankModel->delUserRanks($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

}