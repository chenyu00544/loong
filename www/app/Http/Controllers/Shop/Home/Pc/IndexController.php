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
        ignore_user_abort(true);
        set_time_limit(0);
        $db = DB::table('users_copy');
        for ($i = 2334770; $i < 10000000; $i++) {
            $user = [
                'user_name' => 'user_name'.$i,
                'mobile_phone' => 'phone'.$i,
                'nick_name' => 'nick_name'.$i,
                'email' => 'email'.$i,
                'logo' => 'upload/user_logo/154392704137964890396.jpg',
                'password' => '6965dd27b9a8534a0a063c19b2193fb9',
                'salt' => '8xb5qy',
                'sex' => '1',
                'birthday' => '1980-01-01',
                'user_money' => '1000',
                'reg_time' => '1532593507',
                'last_login' => '1543891322',
                'last_time' => '2018-12-04 20:37:55',
                'last_ip' => '60.181.174.160',
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
