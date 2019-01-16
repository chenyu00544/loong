<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\GroupBuyRepository;
use Illuminate\Http\Request;

class GroupBuyController extends CommonController
{

    private $groupBuyRepository;

    public function __construct(
        GroupBuyRepository $groupBuyRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('groupbuy');
        $this->groupBuyRepository = $groupBuyRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seller = 'selfsale';
        $search['keywords'] = '';
        $groups = $this->groupBuyRepository->getGroupBuyByPage($seller, $search);
        return view('shop.admin.group.group', compact('seller', 'groups', 'search'));
    }

    public function change(Request $request)
    {

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.group.groupAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["xxxxx" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->xxxxxxxxxxxxxx->xxxxxxxxxxxxxx($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $seller = $id;
        $search['keywords'] = $request->get('keywords');
        $groups = $this->groupBuyRepository->getGroupBuyByPage($seller, $search);
        return view('shop.admin.group.group', compact('seller', 'groups', 'search'));
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
        $ver = Verifiable::Validator($request->all(), ["xxxxx" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->xxxxxxxxxxxxxx->XXXXXXXXXXXXXX($request->except('_token', '_method'), $id);
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

    }
}
