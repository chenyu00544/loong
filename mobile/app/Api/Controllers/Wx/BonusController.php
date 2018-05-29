<?php

namespace App\Api\Controllers\Wx;

use App\Extensions\Wechat;
use App\Services\FlowService;
use Illuminate\Http\Request;
use App\Services\BonusService;
use App\Services\AuthService;
use App\Api\Controllers\Controller;


/**
 * Class IndexController
 * @package App\Api\Controllers\Wx
 */
class BonusController extends Controller
{

    private $bonusService;
    private $authService;
    private $flowService;

    public function __construct(BonusService $bonusService, AuthService $authService, FlowService $flowService)
    {
        $this->bonusService = $bonusService;
        $this->authService = $authService;
        $this->flowService = $flowService;
    }

    /**
     * 获取未领取的红包
     * @param Request $request
     * @return array
     */
    public function receiveBonus(Request $request)
    {
        //验证数据
        $this->validate($request, []);
        $uid = $this->authService->authorization(); //返回用户ID

        $ad_id = $request->get('ad_id');
        $pos_id = $request->get('pos_id');
        $ad_type = $request->get('ad_type');
        if($ad_type == 'check'){
            $bonus = $this->bonusService->checkBonus($uid, $ad_id, $pos_id, $ad_type);
        }else{
            $bonus = $this->bonusService->receiveBonus($uid, $ad_id, $pos_id, $ad_type);
        }


//        if (!empty($bonus)) {
//            $where = ['ru_id' => 0, 'status' => 1];
//            $wxinfo = dao('wechat')->field('token, appid, appsecret')->where($where)->find();
//            $config = [
//                'appid' => $wxinfo['appid'],
//                'appsecret' => $wxinfo['appsecret'],
//                'token' => $wxinfo['token'],
//            ];
//            $weObj = new Wechat($config);
//            $access_token = $weObj->checkAuth();
//            $sendInfo = dao('small_app_fromid')->where(['wxid' => $request->get('open_id'), 'is_user' => 0])->where(['create_time_stamp', '>', time() - 518400])->find();
//
//            $weObj->wxAppOpenTempSend($access_token);
//        }

        return $this->apiReturn($bonus);
    }

    /**
     * 获取红包信息
     * @param Request $request
     * @return array
     */
    public function bonusInfo(Request $request)
    {
        //验证数据
        $this->validate($request, []);
        $uid = $this->authService->authorization(); //返回用户ID
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $ad_id = $request->get('ad_id');
        $pos_id = $request->get('pos_id');
        $ad_type = $request->get('ad_type');
        $bonus = $this->bonusService->getBonusInfo($uid, $ad_id, $pos_id, $ad_type);

        return $this->apiReturn($bonus);
    }

    /**
     * 下单时选择红包
     * @param Request $request
     * @return mixed
     */

    public function flowGetBonus(Request $request)
    {
        $this->validate($request, [
            'bn_id' => 'required|integer',
            'flow_type' => 'required|integer',
        ]);

        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $res = $this->flowService->changeBonus($request->get('bn_id'), $uid, $request->get('flow_type'));
        return $this->apiReturn($res);
    }

}