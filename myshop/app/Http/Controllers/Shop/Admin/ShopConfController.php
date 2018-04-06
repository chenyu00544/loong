<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Html;
use App\Facades\LangConfig;
use App\Facades\ShopConfig;
use Illuminate\Http\Request;

class ShopConfController extends CommonController
{
    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $req)
    {
        $lang = LangConfig::LangAdminConf();
        $lang += LangConfig::LangAdminShopConf();
        $conf = ShopConfig::getConf();
        $conf = Html::shopConfHtml($conf, $lang);
        return view('shop.admin.shopConf', compact('lang', 'conf'));
    }

    //post/config  添加提交的数据
    public function store(Request $req)
    {
        ShopConfig::setConf($req->all());
        return view('shop.admin.success');
    }
}
