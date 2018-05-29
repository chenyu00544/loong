<?php

namespace App\Api\Controllers\Wx;

use App\Services\AdsMarketingService;
use Illuminate\Http\Request;
use App\Api\Controllers\Controller;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\DB;

class AdsMarketingController extends Controller
{
    private $adsMarketingService;

    public function __construct(AdsMarketingService $adsMarketingService)
    {
        $this->adsMarketingService = $adsMarketingService;
    }

    public function userActionAdd(Request $request)
    {
        $re = $this->adsMarketingService->userActionAdd($request->get('name'));
        echo $re;
    }

    public function userActionGet(Request $request)
    {
        $re = $this->adsMarketingService->userActionGet();
        $data = json_decode($re, true);
        if (empty($data['data']['list'])) {
            $re = $this->adsMarketingService->userActionAdd($request->get('name'));
            $data = json_decode($re, true);
            return $data['data']['user_action_set_id'];
        }
        $user_action_set_id = $data['data']['list'][0]['user_action_set_id'];
        DB::update('update dsc_com_ads_list set `user_action_set_id`=' . $user_action_set_id . ' where `ad_id`=' . $request->get('id'));
        return $user_action_set_id;
    }

    public function userActionReportsAdd(Request $request)
    {
        if (!Cache::get($request->get('gdt_vid')) && $request->get('gdt_vid') != '') {
            $ads_conversion = DB::select("select * from dsc_com_ads_conversion where `gzid`='" . $request->get('gzid') . "'");
            if ($ads_conversion) {
                $ads_res = DB::select('select * from dsc_com_ads_list where `ad_id`=' . $ads_conversion[0]->ads_id);
                foreach ($ads_res as $value) {
                    if ($value->is_conversion == 3) {
                        $this->adsMarketingService->userActionReportsAdd($ads_res[0], $request->get('gdt_vid'), $request->get('gzid'), $request->get('action_type'));
                        Cache::put($request->get('gdt_vid'), $request->get('gdt_vid'), 20);
                    }
                }
            } else {
                $ads_goods = DB::select('select * from dsc_com_ads_list where `goods_id`=' . $request->get('goods_id'));
                if(!empty($ads_goods)){
                    DB::insert("INSERT INTO dsc_com_ads_conversion (`ads_id`, `gzid`) VALUES ('" . $ads_goods[0]->ad_id . "','" . $request->get('gzid') . "')");
                }
            }
        }

        if($request->get('mktag') != ''){
            $tag = DB::select('select * from dsc_marketing_tag where `id`=' . $request->get('mktag'));
        }
    }
}