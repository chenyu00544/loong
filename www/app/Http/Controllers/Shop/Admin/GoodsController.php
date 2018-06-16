<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\BrandRepository;
use App\Repositories\ComCateRepository;
use App\Repositories\GalleryRepository;
use App\Repositories\GoodsAttrRepository;
use App\Repositories\GoodsRepository;
use App\Repositories\GoodsTypeRepository;
use App\Repositories\TransportRepository;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Cache;


class GoodsController extends CommonController
{

    private $goodsRepository;
    private $comCateRepository;
    private $brandRepository;
    private $galleryRepository;
    private $transportRepository;
    private $goodsTypeRepository;
    private $goodsAttrRepository;

    public function __construct(
        GoodsRepository $goodsRepository,
        ComCateRepository $comCateRepository,
        BrandRepository $brandRepository,
        GalleryRepository $galleryRepository,
        TransportRepository $transportRepository,
        GoodsTypeRepository $goodsTypeRepository,
        GoodsAttrRepository $goodsAttrRepository
    )
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
        $this->comCateRepository = $comCateRepository;
        $this->brandRepository = $brandRepository;
        $this->galleryRepository = $galleryRepository;
        $this->transportRepository = $transportRepository;
        $this->goodsTypeRepository = $goodsTypeRepository;
        $this->goodsAttrRepository = $goodsAttrRepository;
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
        $comCates = $this->comCateRepository->getComCates();
        $cateExtend = $this->goodsRepository->getCateExtend($id);
        $selectedCates = [];
        foreach ($cateExtend as $key => $value) {
            $selectedCates[] = $this->comCateRepository->getParentCate($value->cat_id);
        }
        return view('shop.admin.goods.modal.goodsCateExtend', compact('comCates', 'id', 'selectedCates'));
    }

    //添加商品扩展分类
    public function addCateExtend(Request $request)
    {
        return $this->goodsRepository->addCateExtend($request->except('_token'));
    }

    //删除商品扩展分类
    public function delCateExtend($id)
    {
        return $this->goodsRepository->delCateExtend($id);
    }

    //删除回收站里的商品
    public function thoroughDel($id)
    {
        return view('shop.admin.success');
    }

    //从回收站还原
    public function backTo($id)
    {
        $data['id'] = $id;
        $data['type'] = 'is_delete';
        $data['val'] = 0;
        $this->goodsRepository->setGoods($data);
        return view('shop.admin.success');
    }

    //商品图册编辑选择窗口
    public function imageLibrary($type, $goods_id)
    {
        $gallerys = $this->galleryRepository->getGallerys();
        $galleryPics = $this->galleryRepository->getGalleryPicsByPage(['album_id' => $gallerys[0]->album_id]);
        return view('shop.admin.goods.modal.imgLibrary', compact('galleryPics', 'gallerys', 'type', 'goods_id'));
    }

    public function addGalleryShow()
    {
        $gallerys = $this->galleryRepository->getGallerys();
        return view('shop.admin.goods.modal.galleryAdd', compact('gallerys'));
    }

    public function addGallery(Request $request)
    {
        return $this->galleryRepository->addGallery($request->except('_token'));
    }

    //添加商品轮播图册api
    public function addGoodsGallery(Request $request)
    {
        return $this->galleryRepository->addGoodsGallery($request->except('_token'));
    }

    //上传商品轮播图api
    public function upGoodsGalleryPic(Request $request)
    {
        return $this->galleryRepository->upGoodsGalleryPic($request->except('_token'));
    }

    //上传商品轮播图api
    public function delGoodsGalleryPic(Request $request)
    {
        return $this->galleryRepository->delGoodsGalleryPic($request->except('_token'));
    }

    //添加自定义属性窗口
    public function customAttrWin($id, $gid)
    {
        return view('shop.admin.goods.modal.customAttr', compact('id', 'gid'));
    }

    //添加属性
    public function addGoodsAttr(Request $request)
    {
        return $this->goodsAttrRepository->addGoodsAttr($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $comCates = $this->comCateRepository->getComCates();
        $brands = $this->brandRepository->search([], true);
        $transports = $this->transportRepository->getTransportExpressByRuId($this->user->ru_id);
        $goodsTypeCates = $this->goodsTypeRepository->getTypeCates();
        $goodsGallerys = $this->galleryRepository->getGoodsGallerys();
        $now_date = date('Y-m-d', time());
        return view('shop.admin.goods.goodsAdd', compact('comCates', 'brands', 'transports', 'goodsTypeCates', 'goodsGallerys', 'now_date'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["goods_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->goodsRepository->addGoods($request->except('_token'), $this->user->ru_id);
        return view('shop.admin.success');
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
        $comCates = $this->comCateRepository->getComCates();
        $brands = $this->brandRepository->search([], true);
        $goodsInfo = $this->goodsRepository->getGoods($id);
        $transports = $this->transportRepository->getTransportExpressByRuId($this->user->ru_id);
        $goodsTypeCates = $this->goodsTypeRepository->getTypeCates();
        $goodsGallerys = $this->galleryRepository->getGoodsGallerys();
        return view('shop.admin.goods.goodsEdit', compact('comCates', 'brands', 'goodsInfo', 'transports', 'goodsTypeCates', 'goodsGallerys'));
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