<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品配置设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Html;
use App\Facades\LangConfig;
use App\Facades\ShopConfig;
use App\Repositories\Admin\GoodsRepository;
use Illuminate\Http\Request;

class GoodsConfigController extends CommonController
{
    protected $goodsRepository;

    public function __construct(GoodsRepository $goodsRepository)
    {
        parent::__construct();
        $this->checkPrivilege('goodssetup');
        $this->goodsRepository = $goodsRepository;
    }

    /**
     * Display a listing of the resource.f
     *f
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $configs = $this->goodsRepository->getGroupsConfig('goods');
        $lang = LangConfig::LangAdminConf();
        $lang += LangConfig::LangAdminShopConf();
        $conf = Html::shopConfHtml($configs, $lang);
        return view('shop.admin.goodsConf', compact('lang', 'conf'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        ShopConfig::setConf($request->all(), 'goods');
        return view('shop.admin.success');
    }
}
