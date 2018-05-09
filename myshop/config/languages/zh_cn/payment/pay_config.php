<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/5/8
 * Time: 21:09
 */

return array(
    'alipay' => array(
        'pay_code' => 'alipay',
        'pay_name' => '支付宝',
        'pay_fee' => '0',
        'enabled' => '1',
        'pay_desc' => '支付宝网站(www.alipay.com) 是国内先进的网上支付平台。<br/>支付宝收款接口：在线即可开通，<font color="red"><b>零预付，免年费</b></font>，单笔阶梯费率，无流量限制。<br/><a href="http://cloud.ecshop.com/payment_apply.php?mod=alipay" target="_blank"><font color="red">立即在线申请</font></a>',
        'is_cod' => '0',
        'is_online' => '1',
        'pay_config' => array(
            [
                "name" => "支付宝帐户",
                "code" => "alipay_account",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "交易安全校验码",
                "code" => "alipay_key",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "合作者身份ID",
                "code" => "alipay_partner",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "选择接口类型",
                "code" => "alipay_pay_method",
                "type" => "select",
                "value" => "",
                "sel_val" => array(
                    '使用标准双接口' => '0', '使用担保交易接口' => '1', '使用即时到帐交易接口' => '2'
                ),
            ],
            [
                "name" => "选择接口模式",
                "code" => "use_sandbox",
                "type" => "select",
                "value" => "",
                "sel_val" => array('生产模式' => '0', '沙盒模式' => '1'),
            ],
            [
                "name" => "应用APPID",
                "code" => "app_id",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "签名类型",
                "code" => "sign_type",
                "type" => "select",
                "value" => "",
                "sel_val" => array('RSA2(推荐)' => 'RSA2', 'RSA' => 'RSA'),
            ],
            [
                "name" => "支付宝证书公钥",
                "code" => "ali_public_key",
                "type" => "textarea",
                "value" => "",
            ],
            [
                "name" => "商户证书私钥",
                "code" => "rsa_private_key",
                "type" => "textarea",
                "value" => "",
            ],
        )
    ),
    'wxpay' => array(
        'pay_code' => 'wxpay',
        'pay_name' => '微信支付',
        'pay_fee' => '0',
        'enabled' => '1',
        'pay_desc' => '微信支付，是基于客户端提供的服务功能。同时向商户提供销售经营分析、<br/>账户和资金管理的功能支持。用户通过扫描二维码、<br/>微信内打开商品页面购买等多种方式调起微信支付模块完成支付。',
        'is_cod' => '0',
        'is_online' => '1',
        'pay_config' => array(
            [
                "name" => "微信公众号AppId",
                "code" => "wxpay_appid",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "微信公众号AppSecret",
                "code" => "wxpay_appsecret",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "商户支付密钥Key",
                "code" => "wxpay_key",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "微信支付商户号",
                "code" => "wxpay_mchid",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "是否开通微信h5支付",
                "code" => "is_h5",
                "type" => "select",
                "value" => "",
                "sel_val" => array('已开通' => '0', '未开通' => '1'),
            ],
            [
                "name" => "支付证书cert",
                "code" => "sslcert",
                "type" => "textarea",
                "value" => "",
            ],
            [
                "name" => "支付证书key",
                "code" => "sslkey",
                "type" => "textarea",
                "value" => "",
            ],
        )
    ),
    'chinabank' => array(
        'pay_code' => 'chinabank',
        'pay_name' => '网银在线',
        'pay_fee' => '1%',
        'enabled' => '1',
        'pay_desc' => '网银在线（www.chinabank.com.cn）与中国工商银行、招商银行、中国建设银行、<br/>农业银行、民生银行等数十家金融机构达成协议。<br/>全面支持全国19家银行的信用卡及借记卡实现网上支付。<br/><a href="http://www.chinabank.com.cn" target="_blank">立即在线申请</a>',
        'is_cod' => '0',
        'is_online' => '1',
        'pay_config' => array(
            [
                "name" => "商户编号",
                "code" => "chinabank_account",
                "type" => "text",
                "value" => "",
            ],
            [
                "name" => "MD5 密钥",
                "code" => "chinabank_key",
                "type" => "text",
                "value" => "",
            ],
        )
    ),
);