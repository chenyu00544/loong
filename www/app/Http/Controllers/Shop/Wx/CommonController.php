<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Http\Controllers\Controller;

class CommonController extends Controller
{

    public $errorCode = [
        '10000' => '返回成功',
        '10001' => '系统错误',
        '30001' => '购物车以满',
        '30002' => '商品已下架',
        '30003' => '商品库存不足'
    ];

    public function __construct()
    {

    }

    public function apiReturn($data = array(), $code = '10000')
    {
        return (['code' => 1, 'msg' => $this->errorCode[$code], 'data' => $data, 'time' => time()]);
    }
}
