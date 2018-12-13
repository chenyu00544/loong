<?php

namespace App\Http\Controllers\Shop\Home;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    public $user;
    public $seo;

    public function __construct()
    {
        View::share('v', time());
        $this->seo = [
            'title' => '',
            'keywords' => '',
            'description' => ''
        ];
        $this->seo();
        $this->isMobile();
    }

    public function isMobile()
    {
        $ua = strtolower($_SERVER['HTTP_USER_AGENT']);
        $uachar = "/(nokia|sony|ericsson|mot|samsung|sgh|lg|philips|panasonic|alcatel|lenovo|cldc|midp|mobile)/i";
        if($ua == '' || preg_match($uachar, $ua)){
            if (!strpos(strtolower($_SERVER['REQUEST_URI']), 'mobile')) {
                $Loaction = '/mobile'.$_SERVER['REQUEST_URI'];
                if (!empty($Loaction)) {
                    header("Location: $Loaction\n");
                }
            }
        }
    }

    public function seo($seo = [])
    {
        if (empty($seo)) {
            View::share('seo', $this->seo);
        } else {
            View::share('seo', $seo);
        }
    }
}
