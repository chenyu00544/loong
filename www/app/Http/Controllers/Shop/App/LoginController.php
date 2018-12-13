<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Common;
use App\Facades\Verifiable;
use App\Repositories\App\UsersRepository;
use Illuminate\Http\Request;

class LoginController extends CommonController
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
        $ver = Verifiable::Validator($request->all(), ["username" => 'required', "password" => 'required']);
        if (!$ver->passes()) {
            return ['code' => 1, 'msg' => '账号密码错误', 'data' => ''];
        }
        $username = $request->get('username');
        $password = $request->get('password');
        $type = $request->get('qrtype');
        $device_id = $request->get('device_id');
        $ip = $request->getClientIp();
        return $this->usersRepository->login($username, $password, $type, $ip, $device_id);
    }

    public function register(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["username" => 'required', "password" => 'required', "code" => 'required']);
        if (!$ver->passes()) {
            return ['code' => 1, 'msg' => '账号密码验证码不能为空', 'data' => ''];
        }
        $username = $request->get('username');
        $password = $request->get('password');
        $code = $request->get('code');
        $ip = $request->getClientIp();
        $device_id = $request->get('device_id');
        return $this->usersRepository->register($username, $password, $ip, $code, $device_id);
    }

    public function getDeviceId(Request $request)
    {
        return ['code' => 0, 'msg' => '', 'data' => Common::md5Encrypt(time(), Common::randStr(10))];
    }
}
