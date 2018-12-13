<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 支付设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\PaymentRepository;
use Illuminate\Http\Request;

class PayConfigController extends CommonController
{

    private $paymentRepository;

    public function __construct(PaymentRepository $paymentRepository)
    {
        parent::__construct();
        $this->checkPrivilege('paysetup');
        $this->paymentRepository = $paymentRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $payConfig = $this->paymentRepository->getPaymentAll();
        return view('shop.admin.payment.pay', compact('payConfig'));
    }

    public function install(Request $request)
    {
        return $this->paymentRepository->addPayment($request->except('_token'));
    }

    public function changes(Request $request)
    {
        return $this->paymentRepository->changes($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $payInfo = $this->paymentRepository->getPayment($id);
        return view('shop.admin.payment.payEdit', compact('payInfo'));
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
        $ver = Verifiable::Validator($request->all(), ["pay_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $this->paymentRepository->setPayment($request->except('_token', '_method'), $id);
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

    }
}
