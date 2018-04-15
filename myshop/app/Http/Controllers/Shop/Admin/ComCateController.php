<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Facades\Verifiable;
use App\Repositories\ComCateRepository;
use Illuminate\Http\Request;

class ComCateController extends CommonController
{

    protected $comCateRepository;

    public function __construct(ComCateRepository $comCateRepository)
    {
        parent::__construct();
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

    public function chang(Request $request)
    {
        return $this->comCateRepository->setComCate($request->except('_token'));
    }

    public function addCate($id)
    {
        dd($id);
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
        return view('shop.admin.commoditycate.cateadd', compact('icons', 'cates'));
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
        return view('shop.admin.commoditycate.cateedit', compact('icons', 'cates', 'cate','parentCates'));
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
        return $this->comCateRepository->delete($id);
    }
}
