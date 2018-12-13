<?php

namespace App\Http\Controllers\Shop\Home\Pc;

use App\Facades\RedisCache;
use App\Http\Controllers\Shop\Home\CommonController;
use Illuminate\Http\Request;

class IndexController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {
        echo phpinfo();
    }

    public function test(Request $request)
    {
//        echo RedisCache::incrby("order_id");
//        echo route('articleDetail', ['post' => 1]);
    }
}
