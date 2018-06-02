<?php

namespace App\Http\Controllers\Shop\Admin;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    //
    public function __construct()
    {
        View::share('v', time());
//        $ua = strtolower($_SERVER['HTTP_USER_AGENT']);
//        $uachar = "/(nokia|sony|ericsson|mot|samsung|sgh|lg|philips|panasonic|alcatel|lenovo|cldc|midp|mobile)/i";
//        if (($ua == '' || preg_match($uachar, $ua)) && !strpos(strtolower($_SERVER['REQUEST_URI']), 'wap')) {
//            $Loaction = 'mobile/';
//
//            if (!empty($Loaction)) {
//                header("Location: $Loaction\n");
//            }
//        }
    }
}
