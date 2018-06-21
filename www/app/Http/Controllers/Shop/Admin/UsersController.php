<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\UsersRepository;
use Illuminate\Http\Request;

class UsersController extends CommonController
{
    private $usersRepository;

    public function __construct(
        UsersRepository $usersRepository
    )
    {
        parent::__construct();
        $this->usersRepository = $usersRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'list';
        $keywords = '';
        $usersNav = $this->usersRepository->getUsersNav();
        $usersList = $this->usersRepository->getUsers();
        return view('shop.admin.users.users', compact('usersNav', 'usersList', 'navType', 'keywords'));
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
        return ['code' => 1, 'msg' => '删除成功'];
    }
}
