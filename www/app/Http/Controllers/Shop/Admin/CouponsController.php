<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 优惠券设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
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
        $this->checkPrivilege('coupons');
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

        $ver = Verifiable::Validator($request->all(), ["cou_name" => 'required', "cou_title" => 'required', "start_end_date" => 'required', "cou_total" => 'required', "cou_money" => 'required', "cou_man" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->couponsRepository->addCoupons($request->except('_token'), $this->user);
        return view('shop.admin.success');
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
        $userRanks = $this->userRankRepository->getUserRanks();
        $coupons = $this->couponsRepository->getCoupons($id);
        return view('shop.admin.faat.couponsEdit', compact('userRanks', 'coupons'));
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
        $ver = Verifiable::Validator($request->all(), ["cou_name" => 'required', "cou_title" => 'required', "start_end_date" => 'required', "cou_total" => 'required', "cou_money" => 'required', "cou_man" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->couponsRepository->setCoupons($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
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
