<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UserRankRepositoryInterface;
use App\Http\Models\Shop\UserRankModel;

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

    public function getUserRank($id)
    {
        $where['rank_id'] = $id;
        return $this->userRankModel->getUserRank($where);
    }

    public function change($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['rank_id'] = $data['id'];
        $updata['show_price'] = $data['val'];
        $re = $this->userRankModel->setUserRank($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    public function setUserRank($data, $id)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['rank_id'] = $id;
        $data['show_price'] = !empty($data['show_price'])?$data['show_price']:1;
        $data['special_rank'] = !empty($data['special_rank'])?$data['special_rank']:0;
        $re = $this->userRankModel->setUserRank($where, $data);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    public function addUserRanks($data)
    {
        $data['show_price'] = !empty($data['show_price'])?$data['show_price']:1;
        $data['special_rank'] = !empty($data['special_rank'])?$data['special_rank']:0;
        return $this->userRankModel->addUserRank($data);
    }

    public function delUserRank($id)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['rank_id'] = $id;
        $re = $this->userRankModel->delUserRank($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

}