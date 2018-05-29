<?php

namespace App\Api\Controllers\Wx;

use App\Services\AdsMarketingService;
use App\Services\CartService;
use App\Services\UserService;
use Illuminate\Http\Request;
use App\Services\AuthService;
use App\Services\FlowService;
use App\Api\Controllers\Controller;
use Illuminate\Support\Facades\DB;

/**
 * Class FlowController
 * @package App\Api\Controllers\Wx
 */
class FlowController extends Controller
{
    private $flowService;
    private $authService;
    private $cartService;
    private $userService;
    private $adsMarketingService;

    public function __construct(
        FlowService $flowService,
        AuthService $authService,
        CartService $cartService,
        UserService $userService,
        AdsMarketingService $adsMarketingService
    )
    {
        $this->flowService = $flowService;
        $this->authService = $authService;
        $this->cartService = $cartService;
        $this->userService = $userService;
        $this->adsMarketingService = $adsMarketingService;
    }

    /**
     * 订单确认页面 数据 （下订单之前 对应客户端中的checkout）
     * @param Request $request
     * @return array
     */
    public function index(Request $request)
    {
        $this->validate($request, [
            'flow_type' => 'required|integer',  //购物车类型
            'bs_id' => 'required|integer',      //砍价参与id
            't_id' => 'required|integer',       //拼团活动id
            'team_id' => 'required|integer'     //拼团开团id
        ]);

        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        ////////这里直接和前面一样判断即可。
        //如果是拼团开团。  {flow_type: "6", bs_id: "0", team_id: "0", t_id: "7"}

        $flow_type = $request->get('flow_type');

        ////////////////////////////////////////
        $has_free_coupons = false;

        if ($flow_type == 6 && empty($request->get('team_id'))) {
            //是否需要免单。
            $coupGoodsList = $this->flowService->get_user_pintuan_coupons_list_goods($uid, $request->get('goods_id'));

            if (!empty($coupGoodsList)) {
                $has_free_coupons = true;
            }
        }

        $flowInfo = $this->flowService->flowInfo($uid, $request->get('flow_type'), $request->get('bs_id'), $request->get('t_id'), $request->get('team_id'), [$request->get('goods_id')]);

        $flowInfo['has_free_coupons'] = $has_free_coupons;

        return $this->apiReturn($flowInfo);
    }

    /**
     * 选择优惠券
     * @param Request $request
     * @return mixed
     */
    public function changecou(Request $request)
    {
        $this->validate($request, [
            'uc_id' => 'required|integer',
            'flow_type' => 'required|integer',
        ]);

        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $res = $this->flowService->changeCou($request->get('uc_id'), $uid, $request->get('flow_type'));
        return $this->apiReturn($res);
    }

    /**
     * 提交订单
     * @param Request $request
     * @return mixed

    最终这里免单。
     */
    public function down(Request $request)
    {
        $this->validate($request, [
            'consignee' => 'required|integer'
        ]);

        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $args = $request->all();
        $args['uid'] = $uid;
        app('config')->set('uid', $uid);

        $res = $this->flowService->submitOrder($args);

        if ($res['error'] == 1) {
            return $this->apiReturn($res['msg'], 1);
        }

//        if ($res && $request->get('gzid') != '' && $request->get('gdt_vid') != '') {
//            //提交广告行为数据
//            $ads_res = DB::select("select * from dsc_com_ads_conversion where `gzid`='" . $request->get('gzid') . "'");
//            if ($ads_res) {
//                $ads_re = DB::select('select * from dsc_com_ads_list where `ad_id`=' . $ads_res[0]->ads_id);
//                foreach ($ads_re as $value) {
//                    if ($value->is_conversion == 2) {
//                        $gdt_vid = $request->get('gdt_vid');
//                        $this->adsMarketingService->userActionReportsAdd($ads_re[0], $gdt_vid, $request->get('gzid'));
//                    }
//                }
//            }
//        }

        return $this->apiReturn($res);
    }

    /**
     * 配送费
     * @param Request $request
     * @return mixed
     */
    public function shipping(Request $request)
    {
        $this->validate($request, [
            'id' => 'required|integer',
            'ru_id' => 'required|integer',
            'address' => 'required|integer',
            'flow_type' => 'required|integer',
        ]);

        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $args = $request->all();
        $args['uid'] = $uid;
        $res = $this->flowService->shippingFee($args);

        if ($res['error'] == 0) {
            unset($res['error'], $res['message']);
            return $this->apiReturn($res);
        } else {
            return $this->apiReturn($res['message'], 1);
        }
    }

    /**cyc
     * 货到付款提交订单
     * @param Request $request
     * @return mixed
     */
    public function cashon(Request $request)
    {
        $this->validate($request, []);


        //  用户ID
        $uid = $this->authService->authorization();
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $args = $request->all();
        $args = array_merge($args, ['uid' => $uid]);

        $address = $this->userService->userAddressList($uid);
        $addr = [];
        foreach ($address as $add) {
            if ($add['default'] == 1) {
                $addr = $add;
            }
        }

        if (empty($addr)) {
            return $this->apiReturn('noaddress', 2);
        }
        $args['flow_type'] = 0;

//        $this->cartService->deleteAll($uid);
//        $result = $this->cartService->addGoodsToCart($args);

        $flowInfo = $this->flowService->flowInfo($uid, $request->get('flow_type'), $request->get('bs_id'), $request->get('t_id'), $request->get('team_id'));
        $args['consignee'] = $addr['id'];
        $args['bs_id'] = $request->get('bs_id') ? $request->get('bs_id') : 0;
        $args['uc_id'] = 0;
        $args['flow_type'] = $flowInfo['flow_type'];
        $args['team_id'] = 0;
        $args['t_id'] = 0;
        $args['bn_id'] = 0;
        $args['postdata'] = ['inv_payee' => '', 'inv_content' => ''];
        foreach ($flowInfo['cart_goods_list']['list'][0]['shop_info'] as $item) {
            $args['shipping'][] = ['shipping_id' => $item['shipping_id'], 'ru_id' => $item['ru_id']];
        }
        $res = $this->flowService->submitOrder($args);
        if ($res) {
            $resu = $this->flowService->cashOnDelivery($res, $request->get('gzid'));
            //提交广告行为数据
            $ads_res = DB::select("select * from dsc_com_ads_conversion where `gzid`='" . $request->get('gzid') . "'");
            if ($ads_res) {
                $ads_re = DB::select('select * from dsc_com_ads_list where `ad_id`=' . $ads_res[0]->ads_id);
                foreach ($ads_re as $value) {
                    if ($value->is_conversion == 2) {
                        $gdt_vid = $request->get('gdt_vid');
//                        $gdt_vid = 'wx0yif54kjxhg2u600';
                        $this->adsMarketingService->userActionReportsAdd($ads_re[0], $gdt_vid, $request->get('gzid'));
                    }
                }
            }
        }
        if ($resu['error'] == 1) {
            return $this->apiReturn($res['msg'], 1);
        }

        return $this->apiReturn($res);

    }
}
