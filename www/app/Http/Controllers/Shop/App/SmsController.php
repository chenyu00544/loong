<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\AliyunSms;
use App\Facades\RedisCache;
use Illuminate\Http\Request;

class SmsController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {

    }

    public function send(Request $request)
    {
        $msg = AliyunSms::sendSms($request->get('phone'));
        if (empty($msg) || $msg->Code != "OK") {
            return ['code' => 1, 'msg' => '请勿重复发送验证码', 'data' => []];
        } else {
            return ['code' => 0, 'msg' => '', 'data' => []];
        }
    }
}
