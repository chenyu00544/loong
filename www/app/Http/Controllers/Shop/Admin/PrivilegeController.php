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

    public function distribution(Request $request, $id)
    {
        $error = $this->adminUserRepository->setAdminUser($request->except('_token'), $id);
        if (!empty($error['code']) && $error['code'] == 1) {
            return view('shop.admin.failed', compact('error'));
        } else {
            return view('shop.admin.success', compact('error'));
        }
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
        $user = $this->adminUserRepository->getAdminUser(['user_id' => $id]);
        if ($this->user->user_name == $user->user_name || $user->parent_id != $this->user->user_id) {
            $error = ['code' => 1, 'msg' => '你没有权限编辑此管理员'];
            return view('shop.admin.failed', compact('error'));
        }
        $navs = [];
        foreach ($this->nav['index'] as $key => $value) {
            foreach ($this->nav[$key] as $k => $val) {
                $navs[$k]['name'] = $val;
                foreach ($this->nav[$k] as $n => $m) {
                    $navs[$k]['list'][$n] = $m;
                }
            }
        }
        return view('shop.admin.privilege.distribution', compact('navs', 'user'));
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
