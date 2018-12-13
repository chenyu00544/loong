<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\UserAddressRepositoryInterface;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\UserAddressModel;

class UserAddressRepository implements UserAddressRepositoryInterface
{
    private $userAddressModel;
    private $regionsModel;

    public function __construct(
        UserAddressModel $userAddressModel,
        RegionsModel $regionsModel
    )
    {
        $this->userAddressModel = $userAddressModel;
        $this->regionsModel = $regionsModel;
    }

    public function getUserAddresses($id)
    {
        $where['user_id'] = $id;
        $res = $this->userAddressModel->getUserAddresses($where);
        foreach ($res as $value){
            $value->province = $this->regionsModel->getRegion($value->province)->region_name;
            $value->city = $this->regionsModel->getRegion($value->city)->region_name;
            $value->district = $this->regionsModel->getRegion($value->district)->region_name;
        }
        return $res;
    }

    public function getUserAddress($id)
    {
        $where['address_id'] = $id;
        return $this->userAddressModel->getUserAddress($where);
    }

    public function setUserAddress($data, $id)
    {
        $where['address_id'] = $id;
        return $this->userAddressModel->setUserAddress($where, $data);
    }

    public function delUserAddress($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $re = $this->userAddressModel->delUserAddress(['address_id' => $id]);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;

    }
}