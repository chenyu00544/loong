<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\UsersRealRepository;
use App\Repositories\UsersRepository;
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
        $this->usersRepository = $usersRepository;
        $this->usersRealRepository = $usersRealRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'usersreal';
        $usersNav = $this->usersRepository->getUsersNav();
        $userReals = $this->usersRealRepository->getUserRealsByPage();
        return view('shop.admin.userreal.real', compact('usersNav', 'userReals', 'navType'));
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
        //
    }
}
