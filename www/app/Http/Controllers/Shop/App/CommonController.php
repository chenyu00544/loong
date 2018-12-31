<?php

namespace App\Http\Controllers\Shop\App;

use App\Http\Controllers\Controller;

class CommonController extends Controller
{
    public function __construct()
    {

    }

    public function apiReturn($data, $msg = '数据错误')
    {
        if ($data) {
            return (['code' => 0, 'msg' => '', 'data' => $data, 'time' => time()]);
        } else {
            return (['code' => 1, 'msg' => $msg, 'data' => [], 'time' => time()]);
        }
    }
}
