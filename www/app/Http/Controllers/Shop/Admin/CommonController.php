<?php

namespace App\Http\Controllers\Shop\Admin;

use Illuminate\Http\Request;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    public $user;
    public $now_date;

    public function __construct()
    {
        View::share('v', time());
        $this->now_date = date('Y-m-d', time()).' 00:00:00～'.date('Y-m-d', time()).' 23:59:59';
        $this->middleware(function ($request, $next) {
            $uid = $request->cookie('user_id');
            $ip = $request->getClientIp();
            $this->user = Cache::get('adminUser' . md5($ip) . $uid);
            return $next($request);
        });
    }
}
