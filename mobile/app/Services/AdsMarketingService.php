<?php

namespace App\Services;

use App\Extensions\Wxapp;
use App\Repositories\Wechat\WechatUserRepository;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\DB;


class AdsMarketingService
{

    public function __construct(WechatUserRepository $WechatUserRepository)
    {
        $this->WechatUserRepository = $WechatUserRepository;
    }

    public function userActionGet($tokenBool = false)
    {
        $data = null;
        $token = $this->getToken($tokenBool);

        $url = 'https://api.weixin.qq.com/marketing/user_action_sets/get?version=v1.0&access_token=' . $token;
        $re = $this->curlRequest($url, $data);
        $result = json_decode($re, true);
        if ($result['errcode'] == '40001') {
            $this->userActionGet(true);
        }
        return $re;
    }

    public function userActionAdd($name, $desc = '普通广告')
    {
        $data = null;
        $token = $this->getToken();
        $url = 'https://api.weixin.qq.com/marketing/user_action_sets/add?version=v1.0&access_token=' . $token;

        $data['type'] = 'WEB';
        $data['name'] = $name;
        $data['description'] = $desc;
        $data = json_encode($data);
        $re = $this->curlRequest($url, $data);
        return json_encode([$re, 1]);
    }

    public function userActionReportsGet()
    {
        $data = null;
        $token = $this->getToken();
        $url = 'https://api.weixin.qq.com/marketing/user_action_set_reports/get?version=v1.0&access_token=' . $token;
        $data['user_action_set_id'] = 'WEB';
        $data['date_range'] = 'wxadtest';
        $data['start_date'] = 'test';
        $data['end_date'] = 'test';
        $data['time_granularity'] = 'test';
        $data['aggregation'] = 'test';
        $data = json_encode($data);
        $re = $this->curlRequest($url, $data);
        return json_encode([$re, 1]);
    }

    public function userActionReportsAdd($ad_data, $click_id, $gzid, $action_type = 'COMPLETE_ORDER', $tokenBool = false, $te = 'RESERVATION')
    {
        $data = null;
        $token = $this->getToken($tokenBool);

        $url = 'https://api.weixin.qq.com/marketing/user_actions/add?version=v1.0&access_token=' . $token;

        $data['user_action_set_id'] = $ad_data->user_action_set_id;
        $data['url'] = 'http://www.' . $ad_data->ad_url . '&gzid=' . $gzid;
        $data['action_time'] = time();
        $data['action_type'] = $action_type;
        $data['trace'] = ['click_id' => $click_id];
//        $data['action_param'] = '1';
//        $data['value'] = '1';
        $post['actions'] = [$data];
        $post = json_encode($post);
        $re = $this->curlRequest($url, $post);
        $result = json_decode($re, true);
        if ($result['errcode'] == '40001') {
            return $this->userActionReportsAdd($ad_data, $click_id, $action_type, true);
        }else{
            $time = time();
            $save_log['re'] = $re;
            $save_log['gdt_vid'] = $click_id;
            $save_log['gzid'] = $gzid;
            $save_log['action_type'] = $action_type;
            $save_log['user_action_set_id'] = $ad_data->user_action_set_id;
            DB::insert("INSERT INTO dsc_zz_test_log (`content`) VALUES ('" . serialize($save_log) . "')");
        }
        return $re;
    }

    /**cyc
     * 获取AccessToken
     * @return
     */

    public function getToken($bool = false)
    {
        $weChatConfig = $this->WechatUserRepository->getWechatConfig();
        $config = [
            'appid' => $weChatConfig['appid'],
            'secret' => $weChatConfig['appsecret'],
        ];

        $wxapp = new Wxapp($config);
        if (Cache::get('token')) {
            $token = Cache::get('token');
        } else {
            $token = $wxapp->getAccessToken();
            Cache::put('token', $token, 90);
        }
        if ($bool) {
            $token = $wxapp->getAccessToken();
            Cache::put('token', $token, 90);
        }
        return $token;
    }

    private function curlRequest($url, $data = null)
    {

        $header[] = 'Content-Type:application/json;';
        $curl = curl_init();
        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl, CURLOPT_HTTP_VERSION, 1);
        if (stripos($url, "https://") !== false) {
            curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
            curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, false);
            curl_setopt($curl, CURLOPT_SSLVERSION, 1); //CURL_SSLVERSION_TLSv1
        }
        curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
        if (!empty($data)) {
            curl_setopt($curl, CURLOPT_POST, 1);
            curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
        }
        $output = curl_exec($curl);
        curl_close($curl);
        return $output;
    }
}
