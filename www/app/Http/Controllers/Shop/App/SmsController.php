<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\AliyunSms;
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
        return $msg->Code;
    }
}
