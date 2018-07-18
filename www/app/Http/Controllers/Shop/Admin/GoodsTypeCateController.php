<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品类型分类设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\GoodsTypeRepository;
use Illuminate\Http\Request;

class GoodsTypeCateController extends CommonController
{

    protected $goodsTypeRepository;

    public function __construct(GoodsTypeRepository $goodsTypeRepository)
    {
        parent::__construct();
        $this->checkPrivilege('goodstype');
        $this->goodsTypeRepository = $goodsTypeRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $typeNav = 'typeCate';
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        $rank = ['一级', 10];
        return view('shop.admin.goodstype.typeCate', compact('typeNav', 'typeCates', 'rank'));
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

    public function change(Request $request)
    {
        return $this->goodsTypeRepository->setTypeCate($request->except('_token'));
    }

    public function goodsTypeCateByModal()
    {
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        return view('shop.admin.goodstype.modal.typeCateAddModal', compact('typeCates'));
    }

    public function addGoodsTypeCate(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["cat_name" => 'required']);
        if (!$ver->passes()) {
            return [];
        }
        return $this->goodsTypeRepository->addTypeCate($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        return view('shop.admin.goodstype.typeCateAdd', compact('typeCates'));
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
        $re = $this->goodsTypeRepository->addTypeCate($request->except('_token'));
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
        $ranks = ['0级','1级','二级', '三级', '四级', '五级', '六级', '七级', '八级', '九级', '十级'];
        $rank = ['0级'];
        $typeNav = 'typeCate';
        $typeCates = $this->goodsTypeRepository->getTypeCates($id);
        foreach($typeCates as $item){
            $rank = [$ranks[$item->level]];
        }
        return view('shop.admin.goodstype.typeCate', compact('typeNav', 'typeCates', 'rank'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $typeCate = $this->goodsTypeRepository->getTypeCate($id);
        $typeCates = $this->goodsTypeRepository->getTypeCates(0);
        $parentCates = $this->goodsTypeRepository->getParentCate($typeCate->parent_id);
        return view('shop.admin.goodstype.typeCateEdit', compact('typeCate','typeCates', 'parentCates'));
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
        $re = $this->goodsTypeRepository->upDateTypeCate($request->except('_token','_method'), $id);
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
        return $this->goodsTypeRepository->deleteCate($id);
    }
}
