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

class SatisticsRepository implements SatisticsRepositoryInterface
{

    private $orderInfoModel;

    public function __construct(
        OrderInfoModel $orderInfoModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
    }

    public function getSatistics($data)
    {
        $rep = ['code' => 5, 'msg' => '没有数据'];
        switch ($data['type']) {
            case 'order':
                if ($data['opt'] == 'part') {
                    foreach ($data['range'] as $range) {

                    }
                } elseif ($data['opt'] == 'range') {
                    $arr = explode('～', $data['range']);
                    $stime = strtotime($arr[0]);
                    $etime = strtotime($arr[1]);
                    $rtime = $etime - $stime;
                    $part = ceil($rtime / 86400);
                    for ($i = 0; $i < $part; $i++) {
                        $where = [['pay_time', '>', $stime + 86400 * $i], ['pay_time', '<', $stime + 86400 * ($i + 1)], ['pay_status', '=', 2]];
                        $count = $this->orderInfoModel->countOrder($where, [], '');
                        $re[] = ['name' => date('Y-m-d', $stime + 86400 * $i), 'value' => $count];
                        $rep['data'] = $re;
                        $rep['msg'] = '';
                        $rep['code'] = 1;
                    }
                }
                return $rep;
                break;
            default:
                break;
        }
    }
}