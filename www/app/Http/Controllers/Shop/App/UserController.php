<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Common;
use App\Facades\Verifiable;
use App\Repositories\App\UsersRepository;
use Illuminate\Http\Request;

class UserController extends CommonController
{

    private $usersRepository;

    public function __construct(
        UsersRepository $usersRepository
    )
    {
        parent::__construct();
        $this->usersRepository = $usersRepository;
    }

    public function index(Request $request)
    {

    }

    public function addresses(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->userAddresses($uid);
            return ['code' => 0, 'msg' => '', 'data' => $re];
        }
        return ['code' => 1, 'msg' => '', 'data' => []];
    }

    public function addAddress(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->addAddress($request->all(), $uid);
            return ['code' => 0, 'msg' => '', 'data' => $re];
        }
        return ['code' => 1, 'msg' => '', 'data' => []];
    }

    public function setDefaultAddress(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->setDefaultAddress($request->all(), $uid);
            return ['code' => 0, 'msg' => '', 'data' => $re];
        }
    }

    public function setAddress(Request $request)
    {
        $re = $this->usersRepository->setAddress($request->all());
        return ['code' => 0, 'msg' => '', 'data' => $re];
    }

    public function delAddress(Request $request)
    {
        $re = $this->usersRepository->delAddress($request->all());
        return ['code' => 0, 'msg' => '', 'data' => $re];
    }
}
