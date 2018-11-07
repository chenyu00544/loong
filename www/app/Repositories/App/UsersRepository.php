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
use App\Http\Models\App\UserAddressModel;
use App\Http\Models\App\UsersModel;

class UsersRepository implements UsersRepositoryInterface
{
    private $usersModel;
    private $userAddressModel;

    public function __construct(
        UsersModel $usersModel,
        UserAddressModel $userAddressModel
    )
    {
        $this->usersModel = $usersModel;
        $this->userAddressModel = $userAddressModel;
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
            }
        }
        return $req;
    }

    public function userAddresses($uid)
    {
        $where['user_id'] = $uid;
        $res = $this->userAddressModel->userAddresses($where);
        $user = $this->usersModel->getUser($uid);
        foreach ($res as $re){
            if($re->address_id == $user->address_id){
                $re->def = 1;
            }else{
                $re->def = 0;
            }
        }
        return $res;
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
        return $this->userAddressModel->setAddresses($where, $updata);
    }

    public function addAddress($data, $uid)
    {
        $updata = [];
        return $this->userAddressModel->addAddresses($updata);
    }

    public function delAddress($uid)
    {
        $where['user_id'] = $uid;
        return $this->userAddressModel->delAddresses($where);
    }
}