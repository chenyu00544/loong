<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 会员账号设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\PaymentRepository;
use App\Repositories\Admin\UserAccountRepository;
use App\Repositories\Admin\UsersRepository;
use Illuminate\Http\Request;

class UserAccountController extends CommonController
{
    private $userAccountRepository;
    private $usersRepository;
    private $paymentRepository;

    public function __construct(
        UserAccountRepository $userAccountRepository,
        UsersRepository $usersRepository,
        PaymentRepository $paymentRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('account');
        $this->userAccountRepository = $userAccountRepository;
        $this->usersRepository = $usersRepository;
        $this->paymentRepository = $paymentRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     * 提现记录
     */
    public function index(Request $request)
    {
        $navType = '1';
        $search['date'] = !empty($request->get('date')) ? $request->get('date') : $this->now_date;
        $search['keywords'] = $request->get('keywords');
        $search['is_paid'] = ($request->get('is_paid') != '') ? $request->get('is_paid') : -1;
        $userAccount = $this->userAccountRepository->getUserAccountsByPages($request->except('_token'), $navType);
        $userAccount->appends(['date' => $search['date'],
            'keywords' => $search['keywords'],
            'is_paid' => $search['is_paid'],]);
        return view('shop.admin.useraccount.account', compact('userAccount', 'navType', 'search'));
    }

    //充值记录
    public function recharge(Request $request)
    {
        $navType = '0';
        $search['date'] = !empty($request->get('date')) ? $request->get('date') : $this->now_date;
        $search['keywords'] = $request->get('keywords');
        $search['is_paid'] = ($request->get('is_paid') != '') ? $request->get('is_paid') : -1;
        $userAccount = $this->userAccountRepository->getUserAccountsByPages($request->except('_token'), $navType);
        $userAccount->appends(['date' => $search['date'],
            'keywords' => $search['keywords'],
            'is_paid' => $search['is_paid'],]);
        return view('shop.admin.useraccount.accountRecharge', compact('userAccount', 'navType', 'search'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $payments = $this->paymentRepository->getPaymentAll();
        return view('shop.admin.useraccount.accountAdd', compact('payments'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["username" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->userAccountRepository->addUserAccount($request->except('_token'), $this->user);
        if($re['code'] == 1){
            return view('shop.admin.failed');
        }else{
            return view('shop.admin.success');
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $payments = $this->paymentRepository->getPaymentAll();
        $userAccount = $this->userAccountRepository->getUserAccount($id);
        $user = $this->usersRepository->getUser($userAccount->user_id);
        return view('shop.admin.useraccount.accountEdit', compact('payments', 'userAccount', 'user'));
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
        $ver = Verifiable::Validator($request->all(), ["user_id" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->userAccountRepository->setUserAccount($request->except('_token', '_method'), $this->user, $id);
        if($re['code'] == 1){
            return view('shop.admin.failed');
        }else{
            return view('shop.admin.success');
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->userAccountRepository->delAccount($id);
    }
}
