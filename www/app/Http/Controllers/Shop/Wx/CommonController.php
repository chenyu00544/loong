<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Config;

class CommonController extends Controller
{

    public function __construct()
    {

    }

    public function apiReturn($data = array(), $msg = '', $code = '10000')
    {
        if ($msg != '') {
            return (['code' => $code, 'msg' => $msg, 'data' => $data, 'time' => time()]);
        } else {
            return (['code' => 0, 'msg' => Config::get('define')[$code], 'data' => $data, 'time' => time()]);
        }
    }
}
