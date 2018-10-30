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
use App\Http\Models\App\UsersModel;

class UsersRepository implements UsersRepositoryInterface
{
    private $usersModel;

    public function __construct(
        UsersModel $usersModel
    )
    {
        $this->usersModel = $usersModel;
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
}