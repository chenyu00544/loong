<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 导航设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\MobileNavRepository;
use App\Repositories\ComCateRepository;
use Illuminate\Http\Request;

class MobileNavigationController extends CommonController
{

    private $mobileNavRepository;
    private $comCateRepository;

    public function __construct(
        MobileNavRepository $mobileNavRepository,
        ComCateRepository $comCateRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('mobilenav');
        $this->mobileNavRepository = $mobileNavRepository;
        $this->comCateRepository = $comCateRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navs = $this->mobileNavRepository->getMobileNavByPage();
        return view('shop.admin.mnav.mobileNav', compact('navs'));
    }

    public function change(Request $request)
    {
        return $this->mobileNavRepository->change($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $navsTop = $this->mobileNavRepository->getNavsMenulist();
        $cates = $this->comCateRepository->getComCates();
        return view('shop.admin.mnav.mobileNavAdd', compact('navsTop', 'cates'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->mobileNavRepository->addMobileNav($request->except('_token'));
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
        $field = $this->mobileNavRepository->getMobileNav($id);
        $navsTop = $this->mobileNavRepository->getNavsMenulist();
        $cates = $this->comCateRepository->getComCates();
        $parentCates = $this->comCateRepository->getParentCate($field->cid);
        return view('shop.admin.mnav.mobileNavEdit', compact('field', 'navsTop', 'cates', 'parentCates'));
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
        $ver = Verifiable::Validator($request->all(), ["name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->mobileNavRepository->setMobileNav($request->except('_token', '_method'), $id);
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
        return $this->mobileNavRepository->delMobileNav($id);
    }
}
