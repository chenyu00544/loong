<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;


use App\Http\Models\Test\CronModel;

class CronService
{
    private static $cronModel;

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
        self::$cronModel->incOrderCron();
    }
}