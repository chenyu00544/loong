<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\ShopConfigModel;
use Illuminate\Http\Request;

class ShopConfController extends CommonController
{
    public function index(Request $req)
    {
        $lang = LangConfig::LangAdminConf();
        $lang += LangConfig::LangAdminShopConf();
        $conf = (new ShopConfigModel)->getConf();
        return view('shop.admin.shopConf',compact('lang','conf'));
    }
}
