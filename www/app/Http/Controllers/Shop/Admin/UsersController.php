<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\RegFieldsRepository;
use App\Repositories\UserAddressRepository;
use App\Repositories\UserRankRepository;
use App\Repositories\UsersRepository;
use Illuminate\Http\Request;

class UsersController extends CommonController
{
    private $usersRepository;
    private $userRankRepository;
    private $regFieldsRepository;
    private $userAddressRepository;

    public function __construct(
        UsersRepository $usersRepository,
        UserRankRepository $userRankRepository,
        RegFieldsRepository $regFieldsRepository,
        UserAddressRepository $userAddressRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('members');
        $this->usersRepository = $usersRepository;
        $this->userRankRepository = $userRankRepository;
        $this->regFieldsRepository = $regFieldsRepository;
        $this->userAddressRepository = $userAddressRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'users';
        $keywords = '';
        $usersNav = $this->usersRepository->getUsersNav();
        $usersList = $this->usersRepository->getUsersByPage();
        return view('shop.admin.users.users', compact('usersNav', 'usersList', 'navType', 'keywords'));
    }

    public function changes(Request $request)
    {
        return $this->usersRepository->changes($request->except('_token'));
    }

    public function userInfo($id)
    {
        $navType = 'info';
        $now_date = date('Y-m-d');
        $user = $this->usersRepository->getUser($id);
        $userRanks = $this->userRankRepository->getUserRanks();
        $regFields = $this->regFieldsRepository->getRegFields();
        $userNav = $this->usersRepository->getUserEditNav();
        return view('shop.admin.users.userEdit', compact('user', 'regFields', 'userRanks', 'userNav', 'navType', 'now_date'));
    }

    public function userAddress($id)
    {
        $navType = 'address';
        $userNav = $this->usersRepository->getUserEditNav();
        $addressList = $this->userAddressRepository->getUserAddresses($id);
        return view('shop.admin.address.address', compact('navType', 'userNav', 'addressList', 'id'));
    }

    public function userOrder($id)
    {
        $navType = 'userorder';
        $now_date = date('Y-m-d');
        $user = $this->usersRepository->getUser($id);
        $userRanks = $this->userRankRepository->getUserRanks();
        $regFields = $this->regFieldsRepository->getRegFields();
        $userNav = $this->usersRepository->getUserEditNav();
        return view('shop.admin.users.userEdit', compact('user', 'regFields', 'userRanks', 'userNav', 'navType', 'now_date'));
    }

    public function userBaitiao($id)
    {
        $navType = 'baitiao';
        $now_date = date('Y-m-d');
        $user = $this->usersRepository->getUser($id);
        $userRanks = $this->userRankRepository->getUserRanks();
        $regFields = $this->regFieldsRepository->getRegFields();
        $userNav = $this->usersRepository->getUserEditNav();
        return view('shop.admin.users.userEdit', compact('user', 'regFields', 'userRanks', 'userNav', 'navType', 'now_date'));
    }

    public function userAccount($id)
    {
        $navType = 'account';
        $now_date = date('Y-m-d');
        $user = $this->usersRepository->getUser($id);
        $userRanks = $this->userRankRepository->getUserRanks();
        $regFields = $this->regFieldsRepository->getRegFields();
        $userNav = $this->usersRepository->getUserEditNav();
        return view('shop.admin.users.userEdit', compact('user', 'regFields', 'userRanks', 'userNav', 'navType', 'now_date'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $now_date = date('Y-m-d');
        $userRanks = $this->userRankRepository->getUserRanks();
        $regFields = $this->regFieldsRepository->getRegFields();
        return view('shop.admin.users.userAdd', compact('userRanks', 'regFields', 'now_date'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["username" => 'required', "password" => 'required', "email" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->usersRepository->addUser($request->except('_token'));
        if (!empty($re['code']) && $re['code'] == 1) {
            return view('shop.admin.failed');
        } else {
            return view('shop.admin.success');
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
        $ver = Verifiable::Validator($request->all(), []);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->usersRepository->setUser($request->except('_token', '_method'), $id);
        if (!empty($re['code']) && $re['code'] == 1) {
            return view('shop.admin.failed');
        } else {
            return view('shop.admin.success');
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
        return $this->usersRepository->delUser($id);
    }
}
