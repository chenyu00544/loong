<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 首页设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Http\Requests;

class IndexController extends CommonController
{
    public function __construct()
    {
        parent::__construct();
        $this->setConfCache();
    }

    public function index()
    {
        $user = session('user');
        $navs = $this->nav;
        return view('shop.admin.index', compact('user', 'navs'));
    }

    public function info()
    {
        return view('shop.admin.info');
    }
}
