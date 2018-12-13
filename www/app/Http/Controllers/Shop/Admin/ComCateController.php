<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品分类设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Facades\Verifiable;
use App\Repositories\Admin\ComCateRepository;
use Illuminate\Http\Request;

class ComCateController extends CommonController
{

    protected $comCateRepository;

    public function __construct(ComCateRepository $comCateRepository)
    {
        parent::__construct();
        $this->checkPrivilege('comcate');
        $this->comCateRepository = $comCateRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cates = $this->comCateRepository->getComCates();
        $rank = ['一级', 10];
        return view('shop.admin.commoditycate.category', compact('cates', 'rank'));
    }

    public function change(Request $request)
    {
        return $this->comCateRepository->setComCate($request->except('_token'));
    }

    public function addCate($id)
    {
        $icons = LangConfig::LangAdminIconsConf();
        $cates = $this->comCateRepository->getComCates();
        $parentCates = $this->comCateRepository->getParentCate($id);
        return view('shop.admin.commoditycate.subCateAdd', compact('icons', 'cates', 'parentCates'));
    }

    public function getCates($id)
    {
        $re = $this->comCateRepository->getComCates($id);
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
        $icons = LangConfig::LangAdminIconsConf();
        $cates = $this->comCateRepository->getComCates();
        return view('shop.admin.commoditycate.cateAdd', compact('icons', 'cates'));
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
        $re = $this->comCateRepository->addCate($request->except('_token'));
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
        $cates = $this->comCateRepository->getComCates($id);
        $rank = $this->comCateRepository->getRank($id);
        return view('shop.admin.commoditycate.category', compact('cates', 'rank'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $icons = LangConfig::LangAdminIconsConf();
        $cates = $this->comCateRepository->getComCates();
        $cate = $this->comCateRepository->getComCate($id);
        $parentCates = $this->comCateRepository->getParentCate($id);
        unset($parentCates[0]);
        return view('shop.admin.commoditycate.cateEdit', compact('icons', 'cates', 'cate', 'parentCates'));
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
        $ver = Verifiable::Validator($request->all(), ["cat_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->comCateRepository->upDateCate($request->except('_token', '_method', 'category_name'), $id);
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
        return $this->comCateRepository->delete($id);
    }
}
