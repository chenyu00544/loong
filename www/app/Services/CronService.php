<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;


use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Test\CronModel;

class CronService
{
    private static $cronModel;
    private static $orderInfoModel;

    public function __construct()
    {
        if (!isset(self::$cronModel)) {
            self::$cronModel = new CronModel;
        }
        return self::$cronModel;
    }

    //更新未收货到期订单
    public static function orderConfirmTake($limit = 50)
    {
        if (!isset(self::$orderInfoModel)) {
            self::$orderInfoModel = new OrderInfoModel();
        }
        $time = time();
        $where = [
            ['shipping_status', '=', 1],
            ['shipping_time', '>', '`auto_delivery_time`*86400+'.$time]
        ];
        $orders = self::$orderInfoModel->getOrderInfos($where, ['*'], $limit);
        $whereIn = [];
        foreach ($orders as $order) {
            $whereIn[] = $order->order_id;
        }
        $updata['shipping_status'] = 2;
        $updata['confirm_take_time'] = $time;
        self::$orderInfoModel->setOrderInfo([], $updata, $whereIn);
    }
}