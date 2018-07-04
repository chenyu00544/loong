<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\OrderRepository;
use App\Repositories\RegionsRepository;
use App\Repositories\UsersRepository;
use Illuminate\Http\Request;

class OrderController extends CommonController
{
    private $orderRepository;
    private $usersRepository;
    private $regionsRepository;

    public function __construct(
        OrderRepository $orderRepository,
        UsersRepository $usersRepository,
        RegionsRepository $regionsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('order');
        $this->orderRepository = $orderRepository;
        $this->usersRepository = $usersRepository;
        $this->regionsRepository = $regionsRepository;
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
        $search['keywords'] = '';
        $searchNav = $this->orderRepository->getSearchNav($seller);
        $orders = $this->orderRepository->getOrdersByPage($request->get('keywords'), [$seller, $navType]);
        return view('shop.admin.order.order', compact('seller', 'navType', 'searchNav', 'search', 'orders'));
    }

    public function changes(Request $request)
    {
        return $this->orderRepository->changes($request->except('_token'));
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
        $search['keywords'] = $request->get('keywords');;
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
        //
    }
}
