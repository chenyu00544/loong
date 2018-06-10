<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;

class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;
    //检查跳转
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
