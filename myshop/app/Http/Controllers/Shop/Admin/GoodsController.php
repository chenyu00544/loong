<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\ComCateRepository;
use App\Repositories\GoodsRepository;
use Illuminate\Http\Request;

class GoodsController extends CommonController
{

    private $goodsRepository;
    private $comCateRepository;

    public function __construct(
        GoodsRepository $goodsRepository,
        ComCateRepository $comCateRepository
    )
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
        $this->comCateRepository = $comCateRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $navType = 'normal';
        $keywords = '';
        $goodsList = $this->goodsRepository->getGoodsPage($request->get('keywords'));
        $goodsNav = $this->goodsRepository->getGoodsNav();
        return view('shop.admin.goods.goods', compact('goodsList', 'goodsNav', 'navType', 'keywords'));
    }

    public function change(Request $request)
    {
        return $this->goodsRepository->setGoods($request->except('_token'));
    }

    public function changes(Request $request)
    {
        return $this->goodsRepository->setGoodsMore($request->except('_token'));
    }

    //商品审核窗口
    public function examine($id)
    {
        $goods = $this->goodsRepository->getGoods($id);
        return view('shop.admin.goods.modal.examine', compact('goods'));
    }

    //商品权重排序页
    public function weightOrder($id)
    {
        $goods = $this->goodsRepository->getGoods($id);
        $goodsWeight = $this->goodsRepository->getGoodsByWeightOrder($id);
        return view('shop.admin.goods.modal.weightOrder', compact('goodsWeight', 'goods'));
    }

    //商品扩展分类窗口
    public function cateExtend($id)
    {
        $comCates =$this->comCateRepository->getComCates();
        return view('shop.admin.goods.modal.goodsCateExtend', compact('comCates', 'id'));
    }

    public function addCateExtend(Request $request)
    {
        return $this->goodsRepository->addCateExtend($request->except('_token'));
    }

    //删除回收站里的商品
    public function thoroughDel($id)
    {
        return view('shop.admin.success');
    }

    public function backTo($id)
    {
        $data['id'] = $id;
        $data['type'] = 'is_delete';
        $data['val'] = 0;
        $this->goodsRepository->setGoods($data);
        return view('shop.admin.success');
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $comCates =$this->comCateRepository->getComCates();
        return view('shop.admin.goods.goodsAdd', compact('comCates'));
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
    public function show($id, Request $request)
    {
        $navType = $id;
        $keywords = $request->get('keywords');
        $goodsList = $this->goodsRepository->getGoodsPage($keywords, [$id]);
        $goodsNav = $this->goodsRepository->getGoodsNav();
        return view('shop.admin.goods.goods', compact('goodsList', 'goodsNav', 'navType', 'keywords'));
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
        $data['id'] = $id;
        $data['type'] = 'is_delete';
        $data['val'] = 1;
        return $this->goodsRepository->setGoods($data);
    }
}
