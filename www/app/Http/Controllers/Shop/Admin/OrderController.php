<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\OrderRepository;
use App\Repositories\PaymentRepository;
use App\Repositories\RegionsRepository;
use App\Repositories\ShippingRepository;
use App\Repositories\TransportRepository;
use App\Repositories\UserAddressRepository;
use App\Repositories\UsersRepository;
use Illuminate\Http\Request;

class OrderController extends CommonController
{
    private $orderRepository;
    private $usersRepository;
    private $regionsRepository;
    private $paymentRepository;
    private $transportRepository;
    private $userAddressRepository;

    public function __construct(
        OrderRepository $orderRepository,
        UsersRepository $usersRepository,
        RegionsRepository $regionsRepository,
        PaymentRepository $paymentRepository,
        TransportRepository $transportRepository,
        UserAddressRepository $userAddressRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('order');
        $this->orderRepository = $orderRepository;
        $this->usersRepository = $usersRepository;
        $this->regionsRepository = $regionsRepository;
        $this->paymentRepository = $paymentRepository;
        $this->transportRepository = $transportRepository;
        $this->userAddressRepository = $userAddressRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seller = 'selfsale';
        $navType = '0';
        $search['keywords'] = $request->get('keywords');
        $searchNav = $this->orderRepository->getSearchNav($seller);
        $orders = $this->orderRepository->getOrdersByPage($search, [$seller, $navType]);
        return view('shop.admin.order.order', compact('seller', 'navType', 'searchNav', 'search', 'orders'));
    }

    public function changes(Request $request)
    {
        return $this->orderRepository->changes($request->except('_token'));
    }

    public function change(Request $request)
    {
        return $this->orderRepository->change($request->except('_token'), $this->user);
    }

    public function paymentEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        $payment = $this->paymentRepository->getPaymentAll();
        return view('shop.admin.order.paymentEdit', compact('order', 'payment'));
    }

    public function expressEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        $expresses = $this->transportRepository->getTransportExpressByRuId($this->user->ru_id);
        return view('shop.admin.order.expressEdit', compact('order', 'expresses'));
    }

    public function consigneeEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        $addresses = $this->userAddressRepository->getUserAddresses($order->user_id);
        $regions = $this->regionsRepository->getRegions();
        $provinces = $this->regionsRepository->getRegions(1, $order->country);
        $citys = $this->regionsRepository->getRegions(2, $order->province);
        $districts = $this->regionsRepository->getRegions(3, $order->city);
        return view('shop.admin.order.consigneeEdit', compact('order', 'addresses', 'regions', 'provinces', 'citys', 'districts'));
    }

    public function otherEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        return view('shop.admin.order.otherEdit', compact('order'));
    }

    public function moneyEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        return view('shop.admin.order.moneyEdit', compact('order'));
    }

    public function feeEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        return view('shop.admin.order.feeEdit', compact('order'));
    }

    public function nopayEdit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        return view('shop.admin.order.nopayEdit', compact('order'));
    }

    //发货单列表
    public function orderDelivery(Request $request, $id)
    {
        $seller = $id;
        $search['keywords'] = $request->get('keywords');;
        $orders = $this->orderRepository->getDeliveryOrdersByPage($search, [$seller]);
        return view('shop.admin.order.orderDelivery', compact('orders', 'seller', 'search'));
    }

    public function deliveryInfo($id)
    {
        $dorder = $this->orderRepository->getDeliveryOrder($id);
        $order = $this->orderRepository->getOrder($dorder->order_id);
        $province = $this->regionsRepository->getArea($dorder->province);
        $city = $this->regionsRepository->getArea($dorder->city);
        $district = $this->regionsRepository->getArea($dorder->district);
        $user = $this->usersRepository->getUser($dorder->user_id);
        $deliveryGoodses = $this->orderRepository->getDeliveryGoodses($dorder->delivery_id);
        return view('shop.admin.order.orderDeliveryInfo', compact('order', 'dorder', 'province', 'city', 'district', 'user', 'deliveryGoodses'));
    }

    public function deliveryChange(Request $request)
    {
        return $this->orderRepository->deliveryChange($request->except('_token'));
    }

    public function deliveryDel($id)
    {
        return $this->orderRepository->delDelivery($id);
    }

    //退货单列表
    public function orderReturn(Request $request, $id)
    {
        $seller = $id;
        $search['keywords'] = $request->get('keywords');;
        $orders = $this->orderRepository->getReturnOrdersByPage($search, [$seller]);
        return view('shop.admin.order.orderReturn', compact('orders', 'seller', 'search'));
    }

    public function returnInfo($id)
    {
        $rorder = $this->orderRepository->getReturnOrder($id);
        $order = $this->orderRepository->getOrder($rorder->order_id);
        $dorder = $this->orderRepository->getDeliveryOrder($rorder->order_id);
        $orderGoodses = $this->orderRepository->getOrderGoodses($rorder->order_id);
        $province = $this->regionsRepository->getArea($rorder->province);
        $city = $this->regionsRepository->getArea($rorder->city);
        $district = $this->regionsRepository->getArea($rorder->district);
        $user = $this->usersRepository->getUser($rorder->user_id);
        $returnGoodses = $this->orderRepository->getOrderReturnGoodses($rorder->ret_id);
        return view('shop.admin.order.orderReturnInfo', compact('order', 'rorder', 'dorder', 'orderGoodses', 'province', 'city', 'district', 'user', 'returnGoodses'));
    }

    public function returnRefound($id)
    {
        $rorder = $this->orderRepository->getReturnOrder($id);
        $dorder = $this->orderRepository->getDeliveryOrder($rorder->order_id);
        return view('shop.admin.order.orderReturnRefound', compact('rorder', 'dorder'));
    }

    public function returnChange(Request $request)
    {
        return $this->orderRepository->returnChange($request->except('_token'), $this->user);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id, Request $request)
    {
        $seller = $request->get('seller');
        if ($id == 'selfsale' || $id == 'seller') {
            $seller = $id;
            $navType = '0';
        } else {
            $navType = $id;
        }
        $search['keywords'] = $request->get('keywords');
        $searchNav = $this->orderRepository->getSearchNav($seller);
        $orders = $this->orderRepository->getOrdersByPage($search, [$seller, $navType]);
        $orders->appends(['keywords' => $search['keywords']]);
        return view('shop.admin.order.order', compact('seller', 'navType', 'searchNav', 'search', 'orders', 'regions'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $order = $this->orderRepository->getOrder($id);
        $orderGoodses = $this->orderRepository->getOrderGoodses($id);
        $province = $this->regionsRepository->getArea($order->province);
        $city = $this->regionsRepository->getArea($order->city);
        $district = $this->regionsRepository->getArea($order->district);
        $user = $this->usersRepository->getUser($order->user_id);
        return view('shop.admin.order.orderEdit', compact('order', 'orderGoodses', 'user', 'province', 'city', 'district'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->orderRepository->delOrder($id);
    }
}
