<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 会员认证设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\UsersRealRepository;
use App\Repositories\Admin\UsersRepository;
use Illuminate\Http\Request;

class UsersRealController extends CommonController
{
    private $usersRepository;
    private $usersRealRepository;

    public function __construct(
        UsersRepository $usersRepository,
        UsersRealRepository $usersRealRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('members');
        $this->usersRepository = $usersRepository;
        $this->usersRealRepository = $usersRealRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $navType = 'usersreal';
        $keywords = $request->get('keywords');
        $review_status = $request->get('review_status') != -1 ? $request->get('review_status') : -1;
        $usersNav = $this->usersRepository->getUsersNav();
        $userReals = $this->usersRealRepository->getUserRealsByPage($request->except('_token'));
        return view('shop.admin.userreal.real', compact('usersNav', 'userReals', 'navType', 'keywords', 'review_status'));
    }

    public function changes(Request $request)
    {
        return $this->usersRealRepository->changes($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {

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
        $userReal = $this->usersRealRepository->getUserReal($id);
        return view('shop.admin.userreal.realEdit', compact('userReal'));
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
        $ver = Verifiable::Validator($request->all(), ["real_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->usersRealRepository->setUserReal($request->except('_token', '_method'), $id);
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
        return $this->usersRealRepository->delUserReal($id);
    }
}
