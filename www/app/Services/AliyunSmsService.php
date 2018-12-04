<?php

namespace App\Services;

use App\Facades\Common;
use App\Helper\AliyunSmsHelper;
use App\Facades\RedisCache;

class AliyunSmsService
{
    private static $smsClient;
    // fixme 必填: 请参阅 https://ak-console.aliyun.com/ 取得您的AK信息
    private static $accessKeyId;
    private static $accessKeySecret;
    // fixme 必填：是否启用https
    private static $security = false;
    // fixme 必填：接口
    private static $domain = "dysmsapi.aliyuncs.com";

    public function __construct()
    {
        if (!isset(self::$smsClient)) {
            self::$smsClient = new AliyunSmsHelper();
            $conf = RedisCache::get('sms_config');
            if ($conf['sms_type'] == 1) {
                $accessKeyId = $conf['ali_appkey'];
                $accessKeySecret = $conf['ali_secretkey'];
            } elseif ($conf['sms_type'] == 0) {
                $accessKeyId = $conf['access_key_id'];
                $accessKeySecret = $conf['access_key_secret'];
            }

            self::$accessKeyId = $accessKeyId;
            self::$accessKeySecret = $accessKeySecret;
        }
        return self::$smsClient;
    }

    /**
     * fixme $type = sms_signin  验证码
     */

    public static function sendSms($phone_number, $param = [], $type = 'sms_signin')
    {
        if (self::$smsClient) {
            $sms = RedisCache::get('sms_type');
            $params = array();
            // *** 需用户填写部分 ***
            // fixme 必填: 短信接收号码
            $params["PhoneNumbers"] = $phone_number;
            // fixme 必填: 短信签名，应严格按"签名名称"填写
            $params["SignName"] = $sms[$type]['set_sign'];
            // fixme 必填: 短信模板Code，应严格按"模板CODE"填写
            $params["TemplateCode"] = $sms[$type]['temp_id'];

            $dir = Common::pregMatchAll($sms[$type]['temp_content']);
            $templateParam = [];
            foreach ($dir as $value) {
                switch ($value) {
                    case 'code': // fixme 验证码,对应${code}
                        if (RedisCache::get('code_' . $phone_number)) {
                            return 0;
                        }
                        $code = Common::randNum(6);
                        RedisCache::setex('code_' . $phone_number, $code, 5);
                        $templateParam[$value] = $code;
                        break;
                    case 'product': // fixme 商品名称,对应${product}
                        $templateParam[$value] = $param['product'];
                        break;
                    case 'app_name': // fixme 应用名称,对应${app_name}
                        $conf = RedisCache::get('shop_config');
                        $templateParam[$value] = $conf['shop_name'];
                        break;
                    case 'order_sn': // fixme 订单号名称,对应${order_sn}
                        $templateParam[$value] = $param['order_sn'];
                        break;
                    case 'username': // fixme 用户名称,对应${username}
                        $templateParam[$value] = $param['username'];
                        break;
                    case 'consignee': // fixme 收货人名称,对应${consignee}
                        $templateParam[$value] = $param['consignee'];
                        break;
                    case 'ordermobile': // fixme 收货人联系方式,对应${ordermobile}
                        $templateParam[$value] = $param['ordermobile'];
                        break;
                    case 'loginname': // fixme 账号,对应${loginname}
                        $templateParam[$value] = $param['loginname'];
                        break;
                    case 'password': // fixme 密码,对应${password}
                        $templateParam[$value] = $param['password'];
                        break;
                    case 'old_time': // fixme 时间,对应${old_time}
                        $templateParam[$value] = $param['old_time'];
                        break;
                    case 'now_time': // fixme 时间,对应${now_time}
                        $templateParam[$value] = $param['now_time'];
                        break;
                    case 'fmtamount': // fixme 金额,对应${fmtamount}
                        $templateParam[$value] = $param['fmtamount'];
                        break;
                    case 'processtype': // fixme 会员充值/提现时,对应${processtype}
                        $templateParam[$value] = $param['processtype'];
                        break;
                    case 'examine': // fixme 审核,对应${examine}
                        $templateParam[$value] = $param['examine'];
                        break;
                    case 'balance': // fixme 余额,对应${balance}
                        $templateParam[$value] = $param['balance'];
                        break;
                    case 'server_phone': // fixme 客服电话,对应${server_phone}
                        $conf = RedisCache::get('shop_config');
                        $templateParam[$value] = $conf['service_phone'];
                        break;
                }
            }

            // fixme 可选: 设置模板参数, 假如模板中存在变量需要替换则为必填项
            $params['TemplateParam'] = $templateParam;
            // fixme 可选: 设置发送短信流水号
//            $params['OutId'] = "12345";
            // fixme 可选: 上行短信扩展码, 扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段
//            $params['SmsUpExtendCode'] = "1234567";

            // *** 需用户填写部分结束, 以下代码若无必要无需更改 ***
            if (!empty($params["TemplateParam"]) && is_array($params["TemplateParam"])) {
                $params["TemplateParam"] = json_encode($params["TemplateParam"], JSON_UNESCAPED_UNICODE);
            }

            // 初始化SignatureHelper实例用于设置参数，签名以及发送请求
            $helper = new AliyunSmsHelper();

            // 此处可能会抛出异常，注意catch
            try {
                $content = $helper->request(
                    self::$accessKeyId,
                    self::$accessKeySecret,
                    self::$domain,
                    array_merge($params, array(
                        "RegionId" => "cn-hangzhou",
                        "Action" => "SendSms",
                        "Version" => "2017-05-25",
                    )),
                    self::$security
                );
                return $content;
            } catch (\Exception $e) {
                return $e;
            }
        }
        return 0;
    }
}
