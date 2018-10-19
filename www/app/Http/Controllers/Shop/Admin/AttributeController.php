<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品属性设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\RedisCache;
use App\Facades\Verifiable;
use App\Repositories\AttributeRepository;
use App\Repositories\GoodsTypeRepository;
use Illuminate\Http\Request;

class AttributeController extends CommonController
{

    private $attributeRepository;
    private $goodsTypeRepository;

    public function __construct(
        AttributeRepository $attributeRepository,
        GoodsTypeRepository $goodsTypeRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('goodstype');
        $this->attributeRepository = $attributeRepository;
        $this->goodsTypeRepository = $goodsTypeRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

    }

    public function getAttributes($id)
    {
        return $this->attributeRepository->getAttributes($id);
    }

    public function attributeModal(Request $request)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $goodsTypes = $this->goodsTypeRepository->getGoodsTypes(['user_id' => RedisCache::get('adminUser' . md5($ip) . $uid)->ru_id]);
        return view('shop.admin.attribute.modal.attributeAddModal', compact('goodsTypes'));
    }

    public function addAttribute(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["attr_name" => 'required']);
        if (!$ver->passes()) {
            return [];
        }
        return $this->attributeRepository->addAttribute($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $goodsTypes = $this->goodsTypeRepository->getGoodsTypes(['user_id' => RedisCache::get('adminUser' . md5($ip) . $uid)->ru_id]);
        return view('shop.admin.attribute.attributeAdd', compact('goodsTypes'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["attr_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->attributeRepository->addAttribute($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $attributes = $this->attributeRepository->getAttributePage($id);
        $goodsType = $this->goodsTypeRepository->getGoodsTypeAll();
        return view('shop.admin.attribute.attribute', compact('attributes', 'goodsType'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit(Request $request, $id)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $goodsTypes = $this->goodsTypeRepository->getGoodsTypes(['user_id' => RedisCache::get('adminUser' . md5($ip) . $uid)->ru_id]);
        $attribute = $this->attributeRepository->getAttribute($id);
        return view('shop.admin.attribute.attributeEdit', compact('goodsTypes', 'attribute'));
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
        $ver = Verifiable::Validator($request->all(), ["attr_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->attributeRepository->setAttribute($request->except('_token', '_method'), $id);
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
        return $this->attributeRepository->delAttribute($id);
    }
}
