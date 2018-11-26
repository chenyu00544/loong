<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\GoodsRepository;
use App\Repositories\App\OrderRepository;
use Illuminate\Http\Request;

class OrderController extends CommonController
{

    private $goodsRepository;
    private $orderRepository;

    public function __construct(
        GoodsRepository $goodsRepository,
        OrderRepository $orderRepository
    )
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
        $this->orderRepository = $orderRepository;
    }

    public function index(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->orderRepository->getOrders($request->all(), $uid);
            return ['code' => 0, 'msg' => '', 'data' => $data];
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function getOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->orderRepository->getOrder($request->all(), $uid);
            if($data){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function addOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->orderRepository->addOrder($request->all(), $uid);
            if($data){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function cancelOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->orderRepository->setOrder($request->all(), $uid);
            if($data){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => '参数错误', 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }
}
