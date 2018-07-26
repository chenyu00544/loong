<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\OrderRepository;
use Illuminate\Http\Request;

class SatisticsController extends CommonController
{
    private $date;
    private $orderRepository;

    public function __construct(
        OrderRepository $orderRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('satistics');
        $this->orderRepository = $orderRepository;
        $k = 4;
        $data = [];
        for ($i = 0; $i <= $k; $i++) {
            $data[] = date('Y-m', time() - ((86400 * 30) * ($k - $i)));
        }
        $this->data = $data;
    }

    //订单统计
    public function order(Request $request)
    {
        $now_date = $this->now_date;
        $data = $this->data;
        $ordersat = $this->orderRepository->getOrderSatistics();
        return view('shop.admin.satistics.orderSat', compact('now_date', 'data'));
    }
}
