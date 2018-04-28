<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\GoodsTypeRepository;
use Illuminate\Http\Request;

class GoodsTypeController extends CommonController
{
    protected $goodsTypeRepository;

    public function __construct(GoodsTypeRepository $goodsTypeRepository)
    {
        parent::__construct();
        $this->goodsTypeRepository = $goodsTypeRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $typeNav = 'goodsType';
        $goodsTypes = $this->goodsTypeRepository->getGoodsTypesPage();
        return view('shop.admin.goodstype.goodsType', compact('typeNav', 'goodsTypes'));
    }

    public function getCates($id)
    {
        $re = $this->goodsTypeRepository->getTypeCates($id);
        if ($re->toArray()) {
            return ['code' => 1, 'data' => $re];
        } else {
            return ['code' => 0];
        }
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        return view('shop.admin.goodstype.goodsTypeAdd', compact('typeCates'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["cat_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->goodsTypeRepository->addtGoodsType($request->except('_token'));
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
        $goodsType = $this->goodsTypeRepository->getGoodsType($id);
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        $parentCates = $this->goodsTypeRepository->getParentCate($goodsType->c_id);
        return view('shop.admin.goodstype.goodsTypeEdit', compact('goodsType', 'typeCates', 'parentCates'));
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
        $re = $this->goodsTypeRepository->setGoodsType($request->except('_token','_method'), $id);
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
        //
    }
}
