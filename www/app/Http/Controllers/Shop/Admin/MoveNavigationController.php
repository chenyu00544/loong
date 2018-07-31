<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 导航设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\MoveNavRepository;
use Illuminate\Http\Request;

class MoveNavigationController extends CommonController
{

    private $moveNavRepository;

    public function __construct(
        MoveNavRepository $moveNavRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('movenav');
        $this->moveNavRepository = $moveNavRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navs = $this->moveNavRepository->getMoveNavByPage();
        return view('shop.admin.mnav.moveNav', compact('navs'));
    }

    public function change(Request $request)
    {
        return $this->moveNavRepository->change($request->except('_token'));
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
        return $this->moveNavRepository->delMoveNav($id);
    }
}
