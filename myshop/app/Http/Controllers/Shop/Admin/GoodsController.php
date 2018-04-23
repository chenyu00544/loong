<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\GoodsRepository;
use Illuminate\Http\Request;

class GoodsController extends CommonController
{

    protected $goodsRepository;

    public function __construct(GoodsRepository $goodsRepository)
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $goodsList = $this->goodsRepository->getGoodsPage();
        $goodsNav = $this->goodsRepository->getGoodsNav();
        return view('shop.admin.goods.goods', compact('goodsList', 'goodsNav'));
    }

    public function change(Request $request)
    {
        return $this->goodsRepository->setGoods($request->except('_token'));
    }

    //商品权重排序页
    public function weightOrder($id)
    {
        $goods = $this->goodsRepository->getGoods($id);
        $goodsWeight = $this->goodsRepository->getGoodsByWeightOrder($id);
        return view('shop.admin.goods.weightOrder', compact('goodsWeight', 'goods'));
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
    public function show($id)
    {
        //
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
