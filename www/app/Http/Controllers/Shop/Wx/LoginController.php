<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Common;
use App\Facades\Verifiable;
use App\Repositories\Wxapp\UsersRepository;
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
        $ver = Verifiable::Validator($request->all(), ["userinfo" => 'required', "code" => 'required']);
        if (!$ver->passes()) {
            return ['code' => 1, 'msg' => '账号密码错误', 'data' => ''];
        }
        $userinfo = $request->get('userinfo');
        $code = $request->get('code');
        $type = $request->get('qrtype');
        $device_id = $request->get('device_id');
        $ip = $request->getClientIp();
        $re = $this->usersRepository->login($userinfo, $code, $type, $ip, $device_id);
        return $this->apiReturn($re);
    }

    /**
     * 验证静默用户cyc
     * @param Request $request
     * @return array
     */
    public function silent(Request $request)
    {
        $this->validate($request, [
            'code' => 'required'
        ]);
        // 用户登录
        if (false === ($result = $this->usersRepository->loginSilent($request->all()))) {
            return ['登录失败', 1];
        }
        //登录成功
        return $this->apiReturn($result);
    }

    public function bindPhone(Request $request)
    {
        
    }

    public function getDeviceId(Request $request)
    {
        return ['code' => 0, 'msg' => '', 'data' => Common::md5Encrypt(time(), Common::randStr(10))];
    }
}
