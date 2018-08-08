<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 店铺功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\SellerRepository;
use App\Repositories\StoreListRepository;
use Illuminate\Http\Request;

class StoreListController extends CommonController
{
    private $storeListRepository;
    private $sellerRepository;

    public function __construct(
        StoreListRepository $storeListRepository,
        SellerRepository $sellerRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('storelist');
        $this->storeListRepository = $storeListRepository;
        $this->sellerRepository = $sellerRepository;
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

    public function change(Request $request)
    {
        return $this->storeListRepository->setStore($request->except('_token'));
    }

    public function info($id)
    {
        $nav = 'info';
        $info = $this->sellerRepository->getSellerShopInfo(['ru_id' => $id]);
        dd($info);
        return view('shop.admin.merchants.shopSetup', compact('id', 'nav', 'info'));
    }

    public function setShopInfo()
    {

    }

    public function privilegeEdit($id)
    {
        $nav = 'priv';
        return view('shop.admin.merchants.shopSetup', compact('id', 'nav'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
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
        //
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
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
