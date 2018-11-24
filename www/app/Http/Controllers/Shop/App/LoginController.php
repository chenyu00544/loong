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
        $type = $request->get('type');
        $ip = $request->getClientIp();
        return $this->usersRepository->login($username, $password, $type, $ip);
    }

    public function register(Request $request)
    {

    }

    public function getDeviceId(Request $request)
    {
        return ['code' => 0, 'msg' => '', 'data' => Common::md5Encrypt(time(), Common::randStr(10))];
    }
}
