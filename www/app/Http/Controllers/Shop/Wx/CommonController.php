<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Config;

class CommonController extends Controller
{

    public function __construct()
    {

    }

    public function apiReturn($data = array(), $code = '10000')
    {
        return (['code' => 1, 'msg' => Config::get('define')[$code], 'data' => $data, 'time' => time()]);
    }
}
