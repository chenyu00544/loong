<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 店铺功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\AdminUserRepository;
use App\Repositories\Admin\ComCateRepository;
use App\Repositories\Admin\MerchantsRepository;
use App\Repositories\Admin\RegionsRepository;
use App\Repositories\Admin\SellerGradeRepository;
use App\Repositories\Admin\SellerRepository;
use App\Repositories\Admin\StoreListRepository;
use Illuminate\Http\Request;

class StoreListController extends CommonController
{
    private $storeListRepository;
    private $sellerRepository;
    private $regionsRepository;
    private $comCateRepository;
    private $merchantsRepository;
    private $sellerGradeRepository;
    private $adminUserRepository;

    public function __construct(
        StoreListRepository $storeListRepository,
        SellerRepository $sellerRepository,
        RegionsRepository $regionsRepository,
        ComCateRepository $comCateRepository,
        MerchantsRepository $merchantsRepository,
        SellerGradeRepository $sellerGradeRepository,
        AdminUserRepository $adminUserRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('storelist');
        $this->storeListRepository = $storeListRepository;
        $this->sellerRepository = $sellerRepository;
        $this->regionsRepository = $regionsRepository;
        $this->comCateRepository = $comCateRepository;
        $this->merchantsRepository = $merchantsRepository;
        $this->sellerGradeRepository = $sellerGradeRepository;
        $this->adminUserRepository = $adminUserRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $snav = 'storelist';
        $search['keywords'] = '';
        $stores = $this->storeListRepository->getStoresByPage($search);
        return view('shop.admin.merchants.merchants', compact('snav', 'search', 'stores'));
    }

    public function real(Request $request)
    {
        return '开发中。。。。';
    }

    public function change(Request $request)
    {
        return $this->storeListRepository->change($request->except('_token'));
    }

    public function info($id)
    {
        $nav = 'info';
        $info = $this->sellerRepository->getSellerShopInfo(['ru_id' => $id]);
        return view('shop.admin.merchants.shopSetup', compact('id', 'nav', 'info'));
    }

    public function setShopInfo(Request $request, $id)
    {
        $re = $this->sellerRepository->setSellerShopInfo($request->except('_token'), $id);
        return view('shop.admin.success');
    }

    public function geoModal($id)
    {
        $info = $this->sellerRepository->getSellerShopInfo(['ru_id' => $id]);
        return view('shop.admin.merchants.modal.geo');
    }

    public function privilegeEdit($id)
    {
        $nav = 'priv';
        $sellernavs = $this->sellerPrivilege();
        $admin = $this->adminUserRepository->getAdminUser(['parent_id' => 0, 'ru_id' => $id]);
        return view('shop.admin.merchants.privilege', compact('id', 'nav', 'sellernavs', 'admin'));
    }

    public function privAllot(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["login_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adminUserRepository->modifyAdminUser($request->except('_token'));
        if ($re['code'] == 5) {
            return view('shop.admin.failed');
        } else {
            return view('shop.admin.success');
        }
    }

    public function addCate(Request $request)
    {
        return $this->merchantsRepository->addMerchantsCateTemporarydate($request->except('_token'));
    }

    public function delCate($id)
    {
        return $this->merchantsRepository->delMerchantsCateTemporarydate($id);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $shopSteps = $this->storeListRepository->getStepsByShopInfo();
        $regions = $this->regionsRepository->getRegions();
        $cates = $this->comCateRepository->getComCates();
        $brands = $this->merchantsRepository->getMerchantsBrands(0);
        $grades = $this->sellerGradeRepository->getSellerGrades();
        $mcts = $this->merchantsRepository->getMerchantsCateTemporarydates(0);
        return view('shop.admin.merchants.merchantsAdd', compact('shopSteps', 'regions', 'cates', 'brands', 'grades', 'mcts'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["user_id" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->storeListRepository->addStore($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $snav = 'storelist';
        $search['keywords'] = $request->get('keywords');
        $stores = $this->storeListRepository->getStoresByPage($search, [], $id);
        return view('shop.admin.merchants.merchants', compact('snav', 'search', 'stores'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $shopSteps = $this->storeListRepository->getStepsByShopInfo();
        $regions = $this->regionsRepository->getRegions();
        $cates = $this->comCateRepository->getComCates();
        $grades = $this->sellerGradeRepository->getSellerGrades();
        $store = $this->storeListRepository->getStore($id);
        $now = date('Y-m-d', time());
        return view('shop.admin.merchants.merchantsEdit', compact('shopSteps', 'regions', 'cates', 'grades', 'id', 'store', 'now'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $ver = Verifiable::Validator($request->all(), ["user_id" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->storeListRepository->setStore($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
//        $id = isset($_REQUEST['id']) ? intval($_REQUEST['id']) : 0;
//
//        $sql = "delete from " .$ecs->table('merchants_shop_information'). " where user_id = '$id'";
//        $db->query($sql);
//
//        $sql = "delete from " .$ecs->table('merchants_steps_fields'). " where user_id = '$id'";
//        $db->query($sql);
//
//        if($GLOBALS['_CFG']['delete_seller'] && $id){
//            get_delete_seller_info('seller_shopbg', "ru_id = '$id'"); //删除店铺背景
//            get_delete_seller_info('seller_shopwindow', "ru_id = '$id'"); //删除店铺橱窗
//            get_delete_seller_info('seller_shopheader', "ru_id = '$id'"); //删除店铺头部
//            get_delete_seller_info('seller_shopslide', "ru_id = '$id'"); //删除店铺轮播图
//            get_delete_seller_info('seller_shop_info', "ru_id = '$id'"); //删除店铺基本信息
//            get_delete_seller_info('seller_domain', "ru_id = '$id'"); //删除店铺二级域名
//            get_delete_seller_info('admin_user', "ru_id = '$id'"); //删除商家管理员身份
//
//            get_seller_delete_order_list($id); //删除商家订单
//            get_seller_delete_goods_list($id); //删除商家商品
//
//            get_delete_seller_info('merchants_category', "user_id = '$id'"); //删除商家店铺分类
//        }
    }
}
