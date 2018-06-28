<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\SeoRepository;
use Illuminate\Http\Request;

class SeoController extends CommonController
{
    private $seoRepository;

    public function __construct(SeoRepository $seoRepository)
    {
        parent::__construct();
        $this->checkPrivilege('seosetup');
        $this->seoRepository = $seoRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $seoNav = 'home';
        $seo = $this->seoRepository->getSeo(['type' => $seoNav]);
        return view('shop.admin.seo.seo', compact('seoNav', 'seo'));
    }

    public function brand()
    {
        $seoNav = 'brand';
        $seo = $this->seoRepository->getSeo(['type' => $seoNav]);
        return view('shop.admin.seo.seo', compact('seoNav', 'seo'));
    }

    public function goods()
    {
        $seoNav = 'goods';
        $seo = $this->seoRepository->getSeo(['type' => $seoNav]);
        return view('shop.admin.seo.seo', compact('seoNav', 'seo'));
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
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->seoRepository->setSeo($request->except('_token', '_method'), $id);
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
