<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CouponsRepositoryInterface;
use App\Facades\Common;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\CouponsModel;
use App\Http\Models\Shop\CouponsUserModel;
use App\Http\Models\Shop\GoodsModel;

class CouponsRepository implements CouponsRepositoryInterface
{
    private $couponsModel;
    private $couponsUserModel;
    private $goodsModel;
    private $categoryModel;

    public function __construct(
        CouponsModel $couponsModel,
        CouponsUserModel $couponsUserModel,
        GoodsModel $goodsModel,
        CategoryModel $categoryModel
    )
    {
        $this->couponsModel = $couponsModel;
        $this->couponsUserModel = $couponsUserModel;
        $this->goodsModel = $goodsModel;
        $this->categoryModel = $categoryModel;
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

    public function getCoupons($id)
    {
        $where['cou_id'] = $id;
        $re = $this->couponsModel->getCoupons($where);
        if (!empty($re->cou_goods)) {
            $cou_goods_arr = explode(',', $re->cou_goods);
            $re->cou_goods = $this->goodsModel->getGoodsesByIn($cou_goods_arr);
        }

        if (!empty($re->cou_cate)) {
            $cou_cate_arr = explode(',', $re->cou_cate);
            $re->cou_cate = $this->categoryModel->getComCatesByIn($cou_cate_arr);
        }
        if (!empty($re->cou_ok_user)) {
            $re->cou_ok_user = explode(',', $re->cou_ok_user);
        }

        if (!empty($re->cou_ok_goods)) {
            $cou_ok_goods_arr = explode(',', $re->cou_ok_goods);
            $re->cou_ok_goods = $this->goodsModel->getGoodsesByIn($cou_ok_goods_arr);
        }

        if (!empty($re->cou_ok_cate)) {
            $cou_ok_cate_arr = explode(',', $re->cou_ok_cate);
            $re->cou_ok_cate = $this->categoryModel->getComCatesByIn($cou_ok_cate_arr);
        }
        return $re;
    }

    public function setCoupons($data, $id)
    {
        $where['cou_id'] = $id;
        if ($data['cou_type'] == 1 || $data['cou_type'] == 3) {
            unset($data['cou_get_man']);
            unset($data['cou_ok_user']);
            unset($data['act_type']);
            unset($data['cou_gift_1']);
        } elseif ($data['cou_type'] == 4) {
            unset($data['cou_get_man']);
            unset($data['act_type']);
            unset($data['cou_gift_1']);
        }
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'cou_gift':
                    break;
                case 'cou_gift_1':
                    break;
                case 'start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['cou_start_time'] = strtotime($date_arr[0]);
                        $updata['cou_end_time'] = strtotime($date_arr[1]);
                    }
                    break;
                case 'act_range':
                    if ($value != 0) {
                        if(!empty($data['cou_gift'])){
                            $gift_str = implode(',', $data['cou_gift']);
                            if ($value == 1) {
                                $updata['cou_cate'] = $gift_str;
                            } elseif ($value == 3) {
                                $updata['cou_goods'] = $gift_str;
                            }
                            unset($data['cou_gift']);
                        }
                    }
                    unset($data[$key]);
                    break;
                case 'cou_ok_user':
                    $updata['cou_ok_user'] = implode(',', $value);
                    break;
                case 'act_type':
                    if ($value != 0) {
                        if(!empty($data['cou_gift_1'])){
                            $gift_str = implode(',', $data['cou_gift_1']);
                            if ($value == 1) {
                                $updata['cou_ok_cate'] = $gift_str;
                            } elseif ($value == 3) {
                                $updata['cou_ok_goods'] = $gift_str;
                            }
                            unset($data['cou_gift_1']);
                        }
                    }
                    unset($data[$key]);
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $cuCount = $this->couponsUserModel->countCouponsUser($where);
        $re = $this->couponsModel->setCoupons($where, $updata);
        if ($re) {
            $cudata['cou_id'] = $id;
            $cudata['user_id'] = 0;
            $cudata['cou_money'] = 0;
            $cudata['is_use'] = 0;
            $cudata['order_id'] = 0;
            $cudata['is_use_time'] = 0;
            if (!empty($updata['cou_total']) && $updata['cou_total'] > $cuCount) {
                for ($i = 0; $i < ($updata['cou_total'] - $cuCount); $i++) {
                    $cudata['uc_sn'] = Common::randStr(11);
                    $this->couponsUserModel->addCouponsUser($cudata);
                }
            }
            return true;
        }
        return false;
    }

    public function addCoupons($data, $admin)
    {
        if ($data['cou_type'] == 1 || $data['cou_type'] == 3) {
            unset($data['cou_get_man']);
            unset($data['cou_ok_user']);
            unset($data['act_type']);
            unset($data['cou_gift_1']);
        } elseif ($data['cou_type'] == 4) {
            unset($data['cou_get_man']);
            unset($data['act_type']);
            unset($data['cou_gift_1']);
        }
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'cou_gift':
                    break;
                case 'cou_gift_1':
                    break;
                case 'start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['cou_start_time'] = strtotime($date_arr[0]);
                        $updata['cou_end_time'] = strtotime($date_arr[1]);
                    }
                    break;
                case 'act_range':
                    if ($value != 0) {
                        if(!empty($data['cou_gift'])){
                            $gift_str = implode(',', $data['cou_gift']);
                            if ($value == 1) {
                                $updata['cou_cate'] = $gift_str;
                            } elseif ($value == 3) {
                                $updata['cou_goods'] = $gift_str;
                            }
                            unset($data['cou_gift']);
                        }
                    }
                    unset($data[$key]);
                    break;
                case 'cou_ok_user':
                    $updata['cou_ok_user'] = implode(',', $value);
                    break;
                case 'act_type':
                    if ($value != 0) {
                        if(!empty($data['cou_gift_1'])){
                            $gift_str = implode(',', $data['cou_gift_1']);
                            if ($value == 1) {
                                $updata['cou_ok_cate'] = $gift_str;
                            } elseif ($value == 3) {
                                $updata['cou_ok_goods'] = $gift_str;
                            }
                            unset($data['cou_gift_1']);
                        }
                    }
                    unset($data[$key]);
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $updata['ru_id'] = $admin->ru_id;
        $updata['review_status'] = 3;
        $updata['cou_add_time'] = time();
        $re = $this->couponsModel->addCoupons($updata);
        if ($re) {
            $cudata['cou_id'] = $re->cou_id;
            $cudata['user_id'] = 0;
            $cudata['cou_money'] = 0;
            $cudata['is_use'] = 0;
            $cudata['order_id'] = 0;
            $cudata['is_use_time'] = 0;
            if (!empty($updata['cou_total'])) {
                for ($i = 0; $i < $updata['cou_total']; $i++) {
                    $cudata['uc_sn'] = Common::randStr(11);
                    $this->couponsUserModel->addCouponsUser($cudata);
                }
            }
            return true;
        }
        return false;
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        switch ($data['type']) {
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
        $this->couponsUserModel->delCouponsUser($where);
        $re = $this->couponsModel->delCoupons($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}