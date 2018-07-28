<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SatisticsRepositoryInterface;
use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Shop\UsersModel;

class SatisticsRepository implements SatisticsRepositoryInterface
{

    private $orderInfoModel;
    private $usersModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        UsersModel $usersModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->usersModel = $usersModel;
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
}