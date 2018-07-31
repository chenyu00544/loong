<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 导航设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\MobileNavRepository;
use Illuminate\Http\Request;

class MobileNavigationController extends CommonController
{

    private $mobileNavRepository;

    public function __construct(
        MobileNavRepository $mobileNavRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('mobilenav');
        $this->mobileNavRepository = $mobileNavRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navs = $this->mobileNavRepository->getMoveNavByPage();
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
        //
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
        return $this->mobileNavRepository->delMoveNav($id);
    }
}
