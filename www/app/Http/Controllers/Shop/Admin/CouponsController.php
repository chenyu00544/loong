<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 优惠券设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\CouponsRepository;
use App\Repositories\UserRankRepository;
use Illuminate\Http\Request;

class CouponsController extends CommonController
{

    private $couponsRepository;
    private $userRankRepository;

    public function __construct(
        CouponsRepository $couponsRepository,
        UserRankRepository $userRankRepository
    )
    {
        parent::__construct();
        $this->couponsRepository = $couponsRepository;
        $this->userRankRepository = $userRankRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seller = 'selfsale';
        $search['keywords'] = $request->get('keywords');
        $couponses = $this->couponsRepository->getCouponsByPage($search, $seller);
        return view('shop.admin.faat.coupons', compact('seller', 'search', 'couponses'));
    }

    public function change(Request $request)
    {
        return $this->couponsRepository->change($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $now_date = $this->now_date;
        $userRanks = $this->userRankRepository->getUserRanks();
        return view('shop.admin.faat.couponsAdd', compact('now_date', 'userRanks'));
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
    public function show(Request $request, $id)
    {
        $seller = $id;
        $search['keywords'] = $request->get('keywords');
        $couponses = $this->couponsRepository->getCouponsByPage($search, $seller);
        return view('shop.admin.faat.coupons', compact('seller', 'search', 'couponses'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
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
        return $this->couponsRepository->delCoupons($id);
    }
}
