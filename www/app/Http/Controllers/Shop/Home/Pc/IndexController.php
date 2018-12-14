<?php

namespace App\Http\Controllers\Shop\Home\Pc;

use App\Facades\RedisCache;
use App\Http\Controllers\Shop\Home\CommonController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class IndexController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {
        set_time_limit(0);
        $db = DB::table('users_copy');
        for ($i = 1211131; $i < 10000000; $i++) {
            $user = [
                'user_name' => 'user_name'.$i,
                'mobile_phone' => 'mobile_phone'.$i,
                'nick_name' => 'nick_name'.$i,
                'email' => 'email'.$i,
            ];
            $db->insert($user);
            echo $i.'<\br>';
        }
    }

    public function test(Request $request)
    {
//        echo RedisCache::incrby("order_id");
//        echo route('articleDetail', ['post' => 1]);
    }
}
