<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use Illuminate\Http\Request;

use App\Http\Requests;

class IndexController extends CommonController
{

    public function index()
    {
        $user = session('user');
        $navs = LangConfig::LangAdminNavConf();
        return view('shop.admin.index', compact('user', 'navs'));
    }

    public function info()
    {
        return view('shop.admin.info');
    }
}
