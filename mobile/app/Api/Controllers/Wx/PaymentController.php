<?php

namespace App\Api\Controllers\Wx;

use App\Services\AdsMarketingService;
use Illuminate\Http\Request;
use App\Services\AuthService;
use App\Services\PaymentService;
use App\Api\Controllers\Controller;
use Illuminate\Support\Facades\DB;

class PaymentController extends Controller
{
    private $paymentService;
    private $authService;
    private $adsMarketingService;

    public function __construct(PaymentService $paymentService, AuthService $authService, AdsMarketingService $adsMarketingService)
    {
        $this->paymentService = $paymentService;
        $this->authService = $authService;
        $this->adsMarketingService = $adsMarketingService;
    }

    /**
     * 微信支付
     * @param Request $request
     * @return mixed
     */
    public function pay(Request $request)
    {

        // 验证数据
        $this->validate($request, [
            'id' => 'required|integer',
            'open_id' => 'required|string',
            'code' => 'string',
        ]);

        //验证用户
        $uid = $this->authService->authorization();   //返回用户ID
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $args = $request->all();
        $args['uid'] = $uid;

        $res = $this->paymentService->payment($args);

        return $this->apiReturn($res);
    }

    /**
     * 支付回调
     * @param Request $request
     * @return mixed
     */
    public function notify(Request $request)
    {
        // 验证数据
        $this->validate($request, [
            'id' => 'required|integer',
            'code' => 'string',
        ]);

        //验证用户
        $uid = $this->authService->authorization();   //返回用户ID
        if (isset($uid['error']) && $uid['error'] > 0) {
            return $this->apiReturn($uid, 1);
        }

        $args = $request->all();
        $args['uid'] = $uid;
        $res = $this->paymentService->notify($args);


        //////////////////////////////////////////////////////////////////
        //写入广告记录。 获取20分钟内，该用户 该ip的广告id
        $timenow = time();
        $timed = 1800; //20分钟内  1200
        $thissjc = $timenow - $timed;

        $ip = $request->getClientIp(); //real_ip();

        //获取上一次id。  and click_ip='".$ip."'
        $adIdT = $this->paymentService->getDataListBySql
        ("select id,ad_id,gzid from dsc_com_ads_click where click_user_id='" . $uid . "'  and click_timestamp>" . $thissjc . " order by click_time desc limit 0,1 ");

        if (!empty($adIdT)) {
            $AdId = $adIdT[0]->gzid;//$adIdT[0]['ad_id'];

            $targetAdsClickId = $adIdT[0]->id;//$adIdT[0]['id'];

            $time = time();
            $order_id = $args['id'];
            $update1Sql = "insert into dsc_com_ads_order (gzid, order_id, add_time) values ('$AdId', '$order_id', '$time')";

            $update2Sql = "update dsc_com_ads_click set is_buy = 1 where id = '$targetAdsClickId'  ";

            //$GLOBALS['db']->query($update1Sql);
            //$GLOBALS['db']->query($update2Sql);

            $this->paymentService->sqlUpdate($update1Sql);
            $this->paymentService->sqlUpdate($update2Sql);
        }
        //////////////////////////////////////////////////////////////////


        if ($res['code'] > 0) {
            return $this->apiReturn($res['msg'], 1);
        }

        if ($request->get('gzid') != '' && $request->get('gdt_vid') != '') {
            //提交广告行为数据
            $gzid = $request->get('gzid');
            $ads_res = DB::select("select * from dsc_com_ads_conversion where `gzid`='" . $request->get('gzid') . "'");
            if ($ads_res) {
                $ads_re = DB::select('select * from dsc_com_ads_list where `ad_id`=' . $ads_res[0]->ads_id);
                foreach ($ads_re as $value) {
                    if ($value->is_conversion == 1 || $value->is_conversion == 2) {
                        $gdt_vid = $request->get('gdt_vid');
//                        $gdt_vid = 'wx0yif54kjxhg2u600';
                        $this->adsMarketingService->userActionReportsAdd($ads_re[0], $gdt_vid, $gzid);
                    }
                }
            }
        }

        return $this->apiReturn($res);
    }

    /**
     * 支付回调异步通知
     * @param Request $request
     * @return mixed
     */
    public function asyncNotify(Request $request)
    {
        $postStr = $GLOBALS["HTTP_RAW_POST_DATA"];
        libxml_disable_entity_loader(true);
        $postObj = simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);
        return $this->paymentService->asyncNotify($postObj);
    }

}
