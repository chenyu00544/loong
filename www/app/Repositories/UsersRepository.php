<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UsersRepositoryInterface;
use App\Http\Models\Shop\UsersModel;

class UsersRepository implements UsersRepositoryInterface
{

    private $usersModel;
    public function __construct(
        UsersModel $usersModel
    )
    {
        $this->usersModel = $usersModel;
    }

    public function getUsers()
    {
        return $this->usersModel->getUserByPage();
    }


    //会员菜单
    public function getUsersNav()
    {
        $nav['list'] = ['title' => '会员列表', 'navType' => 'list'];
        $nav['rank'] = ['title' => '会员等级', 'navType' => 'rank'];
        $nav['auth'] = ['title' => '实名认证', 'navType' => 'auth'];
        $nav['reg'] = ['title' => '注册项设置', 'navType' => 'reg'];
        return $nav;
    }

}