<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\UsersRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Http\Models\App\UserAddressModel;
use App\Http\Models\App\UsersModel;
use App\Http\Models\App\UsersRealModel;

class UsersRepository implements UsersRepositoryInterface
{
    private $usersModel;
    private $userAddressModel;
    private $usersRealModel;

    public function __construct(
        UsersModel $usersModel,
        UserAddressModel $userAddressModel,
        UsersRealModel $usersRealModel
    )
    {
        $this->usersModel = $usersModel;
        $this->userAddressModel = $userAddressModel;
        $this->usersRealModel = $usersRealModel;
    }

    public function login($username, $password, $type)
    {
        $req = ['code' => 1, 'msg' => '账号密码错误', 'data' => '', 'token' => ''];
        $column = ['user_id', 'email', 'user_name', 'nick_name', 'logo', 'password', 'salt', 'mobile_phone', 'user_money'];
        $user = $this->usersModel->getUser($username, $column);
        if ($user) {
            if ($type == 1) {
                //验证码登录
                $req = ['code' => 1, 'msg' => '验证码错误', 'data' => '', 'token' => ''];
            } else {
                //密码登录
                $pass = Common::md5Encrypt($password, $user->salt);
                if ($user->password == $pass) {
                    $req = ['code' => 0, 'msg' => '', 'data' => $user, 'token' => encrypt($user->user_id)];
                } else {
                    $req = ['code' => 1, 'msg' => '密码错误', 'data' => '', 'token' => ''];
                }
                $user->is_real = '0';
                if (!empty($user->real)) {
                    if ($user->real->review_status == 1) {
                        $user->is_real = '1';
                    }
                }
            }
        }
        return $req;
    }

    public function getUserInfo($uid)
    {
        $user = $this->usersModel->getUser($uid);
        $user->is_real = '0';
        if (!empty($user->real)) {
            if ($user->real->review_status == 1) {
                $user->is_real = '1';
            }
        }
        return $user;
    }

    public function userAddresses($uid)
    {
        $where['user_id'] = $uid;
        $res = $this->userAddressModel->userAddresses($where);
        $user = $this->usersModel->getUser($uid);
        foreach ($res as $re) {
            $re->country_name = $re->mapcountry->region_name;
            $re->province_name = $re->mapprovince->region_name;
            $re->city_name = $re->mapcity->region_name;
            $re->district_name = $re->mapdistrict->region_name;
            if ($re->address_id == $user->address_id) {
                $re->def = 1;
            } else {
                $re->def = 0;
            }
        }
        return $res;
    }

    public function getAddress($data)
    {
        $where['address_id'] = $data['address_id'];
        $re = $this->userAddressModel->getAddress($where);
        $re->country_name = $re->mapcountry->region_name;
        $re->province_name = $re->mapprovince->region_name;
        $re->city_name = $re->mapcity->region_name;
        $re->district_name = $re->mapdistrict->region_name;
        return $re;
    }

    public function setDefaultAddress($data, $uid)
    {
        $where['user_id'] = $uid;
        $updata['address_id'] = $data['address_id'];
        return $this->usersModel->setUsers($where, $updata);
    }

    public function setAddress($data)
    {
        $where['address_id'] = $data['address_id'];
        $updata = [];
        return $this->userAddressModel->setAddress($where, $updata);
    }

    public function addAddress($data, $uid)
    {
        $count = $this->userAddressModel->countAddress(['user_id' => $uid]);
        if ($count >= 10) {
            return '限定十个地址，已添加满';
        }
        $updata['user_id'] = $uid;
        $updata['consignee'] = $data['consignee'];
        $updata['mobile'] = $data['phone'];
        $updata['country'] = $data['country'];
        $updata['province'] = $data['province'];
        $updata['city'] = $data['city'];
        $updata['district'] = $data['district'];
        $updata['address'] = $data['address_info'];
        $updata['update_time'] = time();
        if (empty($data['address_id'])) {
            return $this->userAddressModel->addAddress($updata);
        } else {
            $where['address_id'] = $data['address_id'];
            return $this->userAddressModel->setAddress($where, $updata);
        }
    }

    public function delAddress($data, $uid)
    {
        $where['user_id'] = $uid;
        $where['address_id'] = $data['address_id'];
        return $this->userAddressModel->delAddress($where);
    }

    public function getUsersReal($uid)
    {
        $where['user_id'] = $uid;
        $explain = '根据海关规定,任何出入关口的商品都必须实名登记并且缴纳关税,根据海关规定,根据海关规定,根据海关规定根据海关规定根据海关规定，根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定，根据海关规定';
        $re = $this->usersRealModel->getUsersReal($where);
        if ($re) {
            $re->explain = $explain;
        } else {
            $re['explain'] = $explain;
        }
        $re->front_of_id_card = FileHandle::getImgByOssUrl($re->front_of_id_card);
        $re->reverse_of_id_card = FileHandle::getImgByOssUrl($re->reverse_of_id_card);
        return $re;
    }

    public function setUsersReal($data, $uid)
    {
        $path = 'user_card';
        $where['user_id'] = $uid;
        $re = $this->usersRealModel->getUsersReal($where);
        $updata = [];
        foreach ($data as $key => $value) {
            if ($key == 'file_0') {
                if ($value->isValid()) {
                    $uri = FileHandle::upLoadImage($value, $path);
                    $updata['front_of_id_card'] = $uri;
                    if ($re) {
                        FileHandle::deleteFile($re->front_of_id_card);
                    }
                }
            } elseif ($key == 'file_1') {
                if ($value->isValid()) {
                    $uri = FileHandle::upLoadImage($value, $path);
                    $updata['reverse_of_id_card'] = $uri;
                    if ($re) {
                        FileHandle::deleteFile($re->reverse_of_id_card);
                    }
                }
            } elseif ($key == 'card_name') {
                $updata['real_name'] = $value;
            } elseif ($key == 'card_num') {
                $updata['self_num'] = $value;
            }
        }
        $updata['add_time'] = time();
        $updata['review_status'] = 3;
        if ($re) {
            $req = $this->usersRealModel->setUsersReal($where, $updata);
        } else {
            $updata['user_id'] = $uid;
            $req = $this->usersRealModel->addUsersReal($updata);
        }
        return ['review_status' => 3];
    }
}