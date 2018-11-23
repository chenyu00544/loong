<?php

namespace App\Http\Controllers\Shop\App;

use App\Repositories\App\PayRepository;
use Illuminate\Http\Request;
use App\Facades\Verifiable;

class PayController extends CommonController
{
    private $payRepository;

    public function __construct(
        PayRepository $payRepository
    )
    {
        parent::__construct();
        $this->payRepository = $payRepository;
    }

    public function index(Request $request)
    {

    }

    public function aliPay(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->payRepository->aliPay($request->all(), $uid);
            if(is_array($data)){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => $data, 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function wxPay(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->payRepository->weChatPay($request->all(), $uid);
            if(is_array($data)){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => $data, 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    public function unionPay(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if($uid != ''){
            $data = $this->payRepository->unionPay($request->all(), $uid);
            if(is_array($data)){
                return ['code' => 0, 'msg' => '', 'data' => $data];
            }else{
                return ['code' => 1, 'msg' => $data, 'data' => $data];
            }
        }
        return ['code' => 1, 'msg' => '未登录', 'data' => []];
    }

    /**
     * 支付回调异步通知aliyun
     * @param Request $request
     * @return mixed
     */
    public function asyncAliNotify(Request $request)
    {
        return $this->payRepository->aliNotify($request->all());
    }

    /**
     * 支付回调异步通知wxchat
     * @param Request $request
     * @return mixed
     */
    public function asyncWxNotify(Request $request)
    {
        return $this->payRepository->weChatNotify($request->all());
    }
}
