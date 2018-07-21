<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CouponsRepositoryInterface;
use App\Http\Models\Shop\CouponsModel;
use App\Http\Models\Shop\CouponsUserModel;

class CouponsRepository implements CouponsRepositoryInterface
{
    private $couponsModel;
    private $couponsUserModel;

    public function __construct(
        CouponsModel $couponsModel,
        CouponsUserModel $couponsUserModel
    )
    {
        $this->couponsModel = $couponsModel;
        $this->couponsUserModel = $couponsUserModel;
    }

    public function getCouponsByPage($search, $seller = 'selfsale')
    {
        $where = [];
        if ($seller == 'selfsale') {
            $where[] = ['ru_id', '=', '0'];
        } else {
            $where[] = ['ru_id', '<>', '0'];
        }
        return $this->couponsModel->getCouponsByPage($where, $search);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        switch ($data['type']){
            case 'delete':
                $whereIn = $data['id'];
                $this->couponsUserModel->delCouponsUserByIn($whereIn);
                $re = $this->couponsModel->delCouponsByIn($whereIn);
                if (!empty($re)) {
                    $req = ['code' => 1, 'msg' => '操作成功'];
                }
                return $req;
                break;
            default:
                break;
        }
    }

    public function delCoupons($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['cou_id'] = $id;
        $res = $this->couponsUserModel->delCouponsUser($where);
        if($res){
            $re = $this->couponsModel->delCoupons($where);
        }
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}