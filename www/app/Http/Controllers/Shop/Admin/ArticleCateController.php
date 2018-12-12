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
use App\Repositories\ArticleCateRepository;
use Illuminate\Http\Request;

class ArticleCateController extends CommonController
{

    protected $artCateRepository;

    public function __construct(ArticleCateRepository $artCateRepository)
    {
        parent::__construct();
        $this->checkPrivilege('artcate');
        $this->artCateRepository = $artCateRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cates = $this->artCateRepository->getArticleCates();
        $rank = ['一级', 10];
        return view('shop.admin.article.artCate', compact('cates', 'rank'));
    }

    public function change(Request $request)
    {
        return $this->artCateRepository->setArticleCate($request->except('_token'));
    }

    public function getCates($id)
    {
        $re = $this->artCateRepository->getArticleCates($id);
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
        $cates = $this->artCateRepository->getArticleCates();
        return view('shop.admin.article.artCateAdd', compact('cates'));
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
        $re = $this->artCateRepository->addArticleCate($request->except('_token'));
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
        $cates = $this->artCateRepository->getArticleCates($id);
        $rank = $this->artCateRepository->getRank($id);
        return view('shop.admin.article.artCate', compact('cates', 'rank'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $cates = $this->artCateRepository->getArticleCates();
        $cate = $this->artCateRepository->getArticleCate($id);
        $parentCates = $this->artCateRepository->getParentCate($id);
        unset($parentCates[0]);
        return view('shop.admin.article.artCateEdit', compact('cates', 'cate', 'parentCates'));
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
        $re = $this->artCateRepository->upArticleCate($request->except('_token', '_method'), $id);
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
        return $this->artCateRepository->delArticleCate($id);
    }
}
