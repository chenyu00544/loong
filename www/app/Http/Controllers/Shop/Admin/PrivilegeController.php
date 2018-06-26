<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\AdminUserRepository;
use Illuminate\Http\Request;

class PrivilegeController extends CommonController
{
    private $adminUserRepository;

    public function __construct(
        AdminUserRepository $adminUserRepository
    )
    {
        parent::__construct();
        $this->adminUserRepository = $adminUserRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $usersList = $this->adminUserRepository->getAdminUserByPage($this->user->ru_id);
        return view('shop.admin.privilege.privilege', compact('usersList'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.privilege.privilegeAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["user_name" => 'required', "new_password" => 'required', "email" => 'required', "confirm_password" => 'required']);
        if (!$ver->passes()) {
            $error = $ver->customMessages;
            return view('shop.admin.failed', compact('error'));
        }
        $error = $this->adminUserRepository->addAdminUser($request->except('_token'), $this->user);
        if (!empty($error['code']) && $error['code'] == 1) {
            return view('shop.admin.failed', compact('error'));
        } else {
            return view('shop.admin.success', compact('error'));
        }
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
        $user = $this->adminUserRepository->getAdminUser(['user_id' => $id]);
        return view('shop.admin.privilege.privilegeEdit', compact('user'));
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
        $ver = Verifiable::Validator($request->all(), ["user_name" => 'required', "email" => 'required']);
        if (!$ver->passes()) {
            $error = $ver->customMessages;
            return view('shop.admin.failed', compact('error'));
        }
        $error = $this->adminUserRepository->setAdminUser($request->except('_token', '_method'), $id, $this->user);
        if (!empty($error['code']) && $error['code'] == 1) {
            return view('shop.admin.failed', compact('error'));
        } else {
            return view('shop.admin.success', compact('error'));
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->adminUserRepository->delAdminUser($id, $this->user);
    }
}
