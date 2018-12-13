<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\MobileOAuthRepositoryInterface;
use App\Http\Models\Shop\MobileAuthModel;

class MobileOAuthRepository implements MobileOAuthRepositoryInterface
{

    private $mobileAuthModel;

    public function __construct(
        MobileAuthModel $mobileAuthModel
    )
    {
        $this->mobileAuthModel = $mobileAuthModel;
    }

    public function getMobileOAuths()
    {
        $res = $this->mobileAuthModel->getMobileAuths(['wechat', 'qq', 'weibo']);
        $req = [];
        foreach ($res as $value){
            $req[$value->type] = $value;
        }
        return $req;
    }

    public function getMobileOAuth($id)
    {
        $where['type'] = $id;
        return $this->mobileAuthModel->getMobileAuth($where);
    }

    public function setMobileOAuth($data, $id)
    {
        $where['id'] = $id;
        return $this->mobileAuthModel->setMobileAuth($where, $data);
    }

    public function addMobileOAuth($data)
    {
        return $this->mobileAuthModel->addMobileAuth($data);
    }

    public function delMobileOAuth($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->mobileAuthModel->delMobileAuth($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }
}