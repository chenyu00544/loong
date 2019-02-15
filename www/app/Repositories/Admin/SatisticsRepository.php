<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\SatisticsRepositoryInterface;
use App\Http\Models\Shop\CategoryModel;
use App\Http\Models\Shop\OrderGoodsModel;
use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\UserRankModel;
use App\Http\Models\Shop\UsersModel;

class SatisticsRepository implements SatisticsRepositoryInterface
{

    private $orderInfoModel;
    private $usersModel;
    private $regionsModel;
    private $userRankModel;
    private $orderGoodsModel;
    private $categoryModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        UsersModel $usersModel,
        RegionsModel $regionsModel,
        UserRankModel $userRankModel,
        OrderGoodsModel $orderGoodsModel,
        CategoryModel $categoryModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->usersModel = $usersModel;
        $this->regionsModel = $regionsModel;
        $this->userRankModel = $userRankModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->categoryModel = $categoryModel;
    }

    public function getSatistics($data)
    {
        $rep = ['code' => 5, 'msg' => '没有数据'];
        switch ($data['type']) {
            case 'order':
                if ($data['opt'] == 'part') {
                    $re = [];
                    foreach ($data['range'] as $range) {
                        $arr = explode('-', $range);
                        $stime = strtotime($arr[0] . '-' . $arr[1]);
                        $etime = strtotime($arr[0] . '-' . ($arr[1] + 1)) - 1;
                        $where = [['pay_time', '>', $stime], ['pay_time', '<', $etime], ['pay_status', '=', 2]];
                        $count = $this->orderInfoModel->countOrder($where, [], '');
                        $sum = $this->orderInfoModel->sumOrder($where, [], '');
                        $re[] = ['name' => $range, 'value_on' => $count, 'value_st' => $sum];
                    }
                    $rep['data'] = $re;
                    $rep['msg'] = '';
                    $rep['code'] = 1;
                } elseif ($data['opt'] == 'range') {
                    $arr = explode('～', $data['range']);
                    $stime = strtotime($arr[0]);
                    $etime = strtotime($arr[1]);
                    $part = ceil(($etime - $stime) / 86400);
                    $part = $part <= 30 ? $part : 30;
                    $re = [];
                    for ($i = 0; $i < $part; $i++) {
                        $where = [['pay_time', '>', $etime - 86400 * ($part - $i)], ['pay_time', '<', $etime - 86400 * ($part - $i - 1)], ['pay_status', '=', 2]];
                        $count = $this->orderInfoModel->countOrder($where, [], '');
                        $sum = $this->orderInfoModel->sumOrder($where, [], '');
                        $re[] = ['name' => date('Y-m-d', $etime - 86400 * ($part - $i)), 'value_on' => $count, 'value_st' => $sum];
                    }
                    $rep['data'] = $re;
                    $rep['msg'] = '';
                    $rep['code'] = 1;
                }
                return $rep;
                break;
            case 'user':
                if ($data['opt'] == 'range') {
                    $arr = explode('～', $data['range']);
                    $stime = strtotime($arr[0]);
                    $etime = strtotime($arr[1]);
                    $part = ceil(($etime - $stime) / 86400);
                    $part = $part <= 30 ? $part : 30;
                    $re = [];
                    for ($i = 0; $i < $part; $i++) {
                        $where = [['reg_time', '>', $etime - 86400 * ($part - $i)], ['reg_time', '<', $etime - 86400 * ($part - $i - 1)]];
                        $count = $this->usersModel->countUser($where);
                        $re[] = ['name' => date('Y-m-d', $etime - 86400 * ($part - $i)), 'value' => $count];
                    }
                    $rep['data'] = $re;
                    $rep['msg'] = '';
                    $rep['code'] = 1;
                }
                return $rep;
                break;
            case 'userarea':
                $re = [];
                if ($data['opt'] == 'user') {
                    $re = $this->regionsModel->getRegionsByUser();
                    $rep['msg'] = '用户数（个）';
                } elseif ($data['opt'] == 'sale') {
                    $re = $this->regionsModel->getRegionsBySale();
                    foreach ($re as $r) {
                        $sale_count = 0;
                        foreach ($r->order as $sale) {
                            $sale_count += $sale->money_paid;
                        }
                        $r->sale_count = $sale_count;
                    }
                    $rep['msg'] = '销售额（元）';
                } elseif ($data['opt'] == 'order') {
                    $re = $this->regionsModel->getRegionsByOrder();
                    $rep['msg'] = '订单数（单）';
                }
                $rep['data'] = $re;
                $rep['code'] = 1;
                return $rep;
                break;
            case 'userrank':
                $re = $this->userRankModel->getUserRankByUser();
                $rep['msg'] = '';
                $rep['data'] = $re;
                $rep['code'] = 1;
                return $rep;
                break;
            case 'userconsumption':
                $re = $this->orderInfoModel->sumOrderByUser();
                $rep['msg'] = '';
                $rep['data'] = $re;
                $rep['code'] = 1;
                return $rep;
                break;
            case 'industry':
                $cates = $this->categoryModel->getComCates(0, ['id', 'cat_name', 'cat_alias_name']);
                foreach ($cates as $cate) {
                    $ids = $this->categoryModel->getSubComCates([$cate->id]);
                    $re = $this->orderGoodsModel->sumOrderGoodsByCate($ids);
                    $cate->sat = $re;
                }
                $rep['msg'] = '';
                $rep['data'] = $cates;
                $rep['code'] = 1;
                return $rep;
                break;
            default:
                break;
        }
    }

    public function satisticsAmount()
    {
        $where = ['pay_status' => 2];
        return $this->orderInfoModel->sumOrder($where, [], '');
    }

    public function satisticsOrderNum()
    {
        $where = ['pay_status' => 2];
        return $this->orderInfoModel->countOrder($where, [], '');
    }

    public function getOrderTotal($data)
    {
        $dates = $this->getDataArray($data['date']);
        $order = [];
        foreach ($dates['time'] as $k => $date) {
            if (empty($dates['time'][$k + 1])) {
                $where = [['add_time', '>', $date]];
            } else {
                $where = [['add_time', '>', $date], ['add_time', '<', $dates['time'][$k + 1]]];
            }
            $order[] = ['count' => $this->orderInfoModel->countOrder($where), 'date' => $dates['date'][$k]];
        }
        return $order;
    }

    public function getAmountTotal($data)
    {
        $dates = $this->getDataArray($data['date']);
        $order = [];
        foreach ($dates['time'] as $k => $date) {
            if (empty($dates['time'][$k + 1])) {
                $where = [['add_time', '>', $date], ['pay_status', '=', PS_PAYED]];
            } else {
                $where = [['add_time', '>', $date], ['add_time', '<', $dates['time'][$k + 1]], ['pay_status', '=', PS_PAYED]];
            }
            $order[] = ['count' => $this->orderInfoModel->sumOrder($where, [], 'all'), 'date' => $dates['date'][$k]];
        }
        return $order;
    }

    private function getDataArray($date)
    {
        $d = [];
        $time = strtotime(date('Y-m-d', time()));
        switch ($date) {
            case 'month':
                for ($i = 1; $i <= 30; $i++) {
                    $d['time'][] = $time - 86400 * (30 - $i);
                    $d['date'][] = date('m-d', $time - 86400 * (30 - $i));
                }
                break;
            case 'year':
                $n_month = date('m');
                $n_year = date('Y');
                for ($i = 0; $i <= 12; $i++) {
                    if ($n_month - $i < 1) {
                        $d['time'][] = strtotime(($n_year - 1) . '-' . (12 + $n_month - $i));
                        $d['date'][] = date('Y-m', strtotime(date('Y') . '-' . $i));
                    } else {
                        $d['time'][] = strtotime($n_year . '-' . ($n_month - $i));
                        $d['date'][] = date('Y-m', strtotime($n_year . '-' . ($n_month - $i)));
                    }
                }
                break;
            case 'week':
            default:
                for ($i = 1; $i <= 7; $i++) {
                    $d['time'][] = $time - 86400 * (7 - $i);
                    $d['date'][] = date('m-d', $time - 86400 * (7 - $i));
                }
                break;
        }
        return $d;
    }
}