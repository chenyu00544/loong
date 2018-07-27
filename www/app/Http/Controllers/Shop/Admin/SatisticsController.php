<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 广告设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\OrderRepository;
use App\Repositories\SatisticsRepository;
use Illuminate\Http\Request;

class SatisticsController extends CommonController
{
    private $date;
    private $orderRepository;
    private $satisticsRepository;

    public function __construct(
        OrderRepository $orderRepository,
        SatisticsRepository $satisticsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('satistics');
        $this->orderRepository = $orderRepository;
        $this->satisticsRepository = $satisticsRepository;
        $k = 4;
        $data = [];
        for ($i = 0; $i <= $k; $i++) {
            $data[] = date('Y-m', time() - ((86400 * 30) * ($k - $i)));
        }
        $this->date = $data;
    }

    //订单统计
    public function order(Request $request)
    {
        $now_date = $this->now_date;
        $data = $this->date;
        $ordersat = $this->orderRepository->getOrderSatistics();
        return view('shop.admin.satistics.orderSat', compact('now_date', 'data'));
    }

    public function getSatistics(Request $request)
    {
        return $this->satisticsRepository->getSatistics($request->except('_token'));
    }
}
