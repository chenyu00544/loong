<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\UserRankRepository;
use App\Repositories\UsersRepository;
use Illuminate\Http\Request;

class UserRankController extends CommonController
{

    private $userRankRepository;
    private $usersRepository;

    public function __construct(
        UsersRepository $usersRepository,
        UserRankRepository $userRankRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('members');
        $this->userRankRepository = $userRankRepository;
        $this->usersRepository = $usersRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'userrank';
        $usersNav = $this->usersRepository->getUsersNav();
        $ranks = $this->userRankRepository->getUserRanks();
        return view('shop.admin.userrank.rank', compact('ranks', 'navType', 'usersNav'));
    }

    public function changes(Request $request)
    {
        return $this->userRankRepository->change($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.userrank.rankAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["rank_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->userRankRepository->addUserRanks($request->except('_token'));
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
        $rank = $this->userRankRepository->getUserRank($id);
        return view('shop.admin.userrank.rankEdit', compact('rank'));
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
        $ver = Verifiable::Validator($request->all(), ["rank_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->userRankRepository->setUserRank($request->except('_token', '_method'), $id);
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
        return $this->userRankRepository->delUserRank($id);
    }
}
