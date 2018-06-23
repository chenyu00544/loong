<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UsersRepositoryInterface;
use App\Facades\Common;
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

    public function getUsersByPage()
    {
        return $this->usersModel->getUsersByPage();
    }

    public function getUser($id)
    {
        return $this->usersModel->getUser(['user_id' => $id]);
    }

    public function changes($data)
    {
        $req = ['code' => 5, 'msg' => '修改失败'];
        $where['user_id'] = $data['uid'];
        $updata = [];
        switch ($data['type']) {
            case 'validated':
                $updata['is_validated'] = $data['value'];
                break;
            default:
                break;
        }
        $re = $this->usersModel->setUser($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '修改成功'];
        }
        return $req;
    }

    public function setUser($data, $id)
    {
        $where['user_id'] = $id;
        $updata = [];
        $updata['email'] = !empty($data['email'])?trim($data['email']):'';
        if (!empty($updata['email']) && $data['old_email'] != trim($data['email'])) {
            if ($this->usersModel->countUser(['email' => $data['email']])) {
                return ['code' => 1, 'msg' => '邮件重复'];
            }
        }
        if (!empty($data['password']) && $data['password'] != $data['c_password']) {
            return ['code' => 1, 'msg' => '密码不相同'];
        }
        if(!empty($data['password'])){
            $updata['salt'] = Common::randStr(6);
            $updata['password'] = Common::md5Encrypt(trim($data['password']), $updata['salt']);
        }
        $updata['msn'] = !empty($data['regField1'])?trim($data['regField1']):'';
        $updata['qq'] = !empty($data['regField2'])?trim($data['regField2']):'';
        if (!empty($updata['regField2']) && $data['old_regField2'] != trim($updata['qq'])) {
            if ($this->usersModel->countUser(['qq' => $data['regField2']])) {
                return ['code' => 1, 'msg' => 'qq重复'];
            }
        }
        $updata['office_phone'] = !empty($data['regField3'])?trim($data['regField3']):'';
        $updata['home_phone'] = !empty($data['regField4'])?trim($data['regField4']):'';
        $updata['mobile_phone'] = !empty($data['regField5'])?trim($data['regField5']):'';
        if (!empty($updata['mobile_phone']) && $data['old_regField5'] != trim($updata['mobile_phone'])) {
            if ($this->usersModel->countUser(['mobile_phone' => $data['regField5']])) {
                return ['code' => 1, 'msg' => '手机重复'];
            }
        }
        $updata['user_rank'] = !empty($data['user_rank'])?trim($data['user_rank']):'';
        $updata['sex'] = !empty($data['sex'])?trim($data['sex']):'';
        $updata['birthday'] = !empty($data['birthday'])?trim($data['birthday']):'';
        $updata['credit_line'] = !empty($data['credit_line'])?trim($data['credit_line']):'';
        return $this->usersModel->setUser($where, $updata);
    }

    public function addUser($data)
    {
        $updata = [];
        if ($this->usersModel->countUser(['user_name' => $data['username']])) {
            return ['code' => 1, 'msg' => '会员名称重复'];
        }
        if ($this->usersModel->countUser(['email' => $data['email']])) {
            return ['code' => 1, 'msg' => '邮件重复'];
        }
        foreach ($data as $key => $value) {
            if ($key == 'regField1') {
                $updata['msn'] = trim($value);
            } elseif ($key == 'regField2') {
                if (!empty($value)) {
                    if ($this->usersModel->countUser(['qq' => $data['regField2']])) {
                        return ['code' => 1, 'msg' => 'qq重复'];
                    }
                    $updata['qq'] = trim($value);
                }
            } elseif ($key == 'regField3') {
                $updata['office_phone'] = trim($value);
            } elseif ($key == 'regField4') {
                $updata['home_phone'] = trim($value);
            } elseif ($key == 'regField5') {
                if (!empty($value)) {
                    if ($this->usersModel->countUser(['mobile_phone' => $data['regField5']])) {
                        return ['code' => 1, 'msg' => '手机重复'];
                    }
                    $updata['mobile_phone'] = trim($value);
                }
            } elseif ($key == 'username') {
                $updata['user_name'] = trim($value);
            } elseif ($key == 'password') {
                $updata['salt'] = Common::randStr(6);
                $updata['password'] = Common::md5Encrypt(trim($value), $updata['salt']);
            } else {
                $updata[$key] = trim($value);
            }
        }
        $updata['reg_time'] = time();
        return $this->usersModel->addUser($updata);
    }

    public function delUser($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $re = $this->usersModel->delUser(['user_id' => $id]);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    //会员菜单
    public function getUsersNav()
    {
        $nav['list'] = ['title' => '会员列表', 'navType' => 'users'];
        $nav['rank'] = ['title' => '会员等级', 'navType' => 'userrank'];
        $nav['auth'] = ['title' => '实名认证', 'navType' => 'usersreal'];
        return $nav;
    }

    //会员编辑菜单
    public function getUserEditNav()
    {
        $nav['info'] = ['title' => '基本信息', 'navType' => 'info'];
        $nav['address'] = ['title' => '收货地址', 'navType' => 'address'];
        $nav['userorder'] = ['title' => '查看订单', 'navType' => 'userorder'];
        $nav['baitiao'] = ['title' => '设置白条', 'navType' => 'baitiao'];
        $nav['account'] = ['title' => '账目明细', 'navType' => 'account'];
        return $nav;
    }

}