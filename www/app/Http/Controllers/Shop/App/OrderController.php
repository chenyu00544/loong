<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\GoodsRepository;
use App\Repositories\App\OrderRepository;
use App\Repositories\App\OrderReturnRepository;
use Illuminate\Http\Request;

class OrderController extends CommonController
{

    private $goodsRepository;
    private $orderRepository;
    private $orderReturnRepository;

    public function __construct(
        GoodsRepository $goodsRepository,
        OrderRepository $orderRepository,
        OrderReturnRepository $orderReturnRepository
    )
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
        $this->orderRepository = $orderRepository;
        $this->orderReturnRepository = $orderReturnRepository;
    }

    public function index(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderRepository->getOrders($request->all(), $uid);
            return ['code' => 0, 'msg' => '', 'data' => $data];
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function getOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderRepository->getOrder($request->all(), $uid);
            if ($data) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function addOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderRepository->addOrder($request->all(), $uid);
            if (is_array($data)) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => $data, 'data' => ''];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function cancelOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderRepository->setOrder($request->all(), $uid);
            if ($data) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    //fixme 售后订单列表
    public function afterSaleOrders(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderReturnRepository->afterSaleOrders($request->all(), $uid);
            if ($data) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    //fixme 售后订单
    public function afterSaleOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderReturnRepository->afterSale($request->all(), $uid);
            if ($data) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    //fixme 售后退换货
    public function returnGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderReturnRepository->returnGoods($request->all(), $uid);
            if (!is_string($data)) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => $data, 'data' => []];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function getLogisticsInfo(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $this->orderRepository->getLogisticsInfo($request->all(), $uid);
            if ($data) {
                return ['code' => 0, 'msg' => '', 'data' => $data];
            } else {
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }
}
