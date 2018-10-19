<?php

namespace App\Http\Controllers\Shop\Home;

use App\Facades\RedisCache;
use Illuminate\Http\Request;

class IndexController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {
        return view('shop.home.index.index');
    }

    public function test(Request $request)
    {
//        RedisCache::set("test", "jkl;asdfjklasdfasdfjklasdfjkaflaksadfasdf");
        echo RedisCache::get("test");
    }
}
