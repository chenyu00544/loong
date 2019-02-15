<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\UsersRepository;
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
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->getUserInfo($uid);
            return ['code' => 0, 'msg' => '', 'data' => $re];
        }
        return ['code' => 1, 'msg' => '', 'data' => []];
    }

    public function addresses(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->userAddresses($uid);
            return ['code' => 0, 'msg' => '', 'data' => $re];
        }
        return ['code' => 1, 'msg' => '失败', 'data' => []];
    }

    public function addAddress(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->addAddress($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            } else {
                return ['code' => 0, 'msg' => $re, 'data' => []];
            }
        }
        return ['code' => 1, 'msg' => '未登陆', 'data' => []];
    }

    public function getAddress(Request $request)
    {
        $re = $this->usersRepository->getAddress($request->all());
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '地址已删除', 'data' => []];
        }
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
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->delAddress($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }
        }
        return ['code' => 1, 'msg' => '', 'data' => []];
    }

    public function real(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->getUsersReal($uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else {
                return ['code' => 1, 'msg' => '', 'data' => []];
            }
        }
        return ['code' => 1, 'msg' => '未登陆', 'data' => []];
    }

    public function setReal(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->setUsersReal($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else {
                return ['code' => 1, 'msg' => '', 'data' => []];
            }
        }
        return ['code' => 1, 'msg' => '未登陆', 'data' => []];
    }

    public function setUser(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->usersRepository->setUserInfo($request->all(), $uid);
            if (!is_string($re)) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else {
                return ['code' => 1, 'msg' => $re, 'data' => []];
            }
        }
        return ['code' => 1, 'msg' => '未登陆', 'data' => []];
    }
}
