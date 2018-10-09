<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 首页设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\RedisCache;
use App\Http\Models\Shop\AliossConfigureModel;
use App\Http\Models\Shop\PaymentModel;
use App\Http\Models\Shop\ShopConfigModel;
use App\Http\Models\Shop\WechatModel;
use App\Http\Requests;

class IndexController extends CommonController
{
    public function __construct()
    {
        parent::__construct();
    }

    public function index()
    {
        $user = session('user');
        $navs = $this->nav;
        return view('shop.admin.index', compact('user', 'navs'));
    }

    public function info()
    {
        $this->setConfCache();
        return view('shop.admin.info');
    }

    public function setConfCache()
    {
        //商城设置
        if (!RedisCache::get('shop_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getConf();
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('shop_config', $_conf);
        }
        //商品设置
        if (!RedisCache::get('goods_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'goods']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('goods_config', $_conf);
        }
        //店铺设置
        if (!RedisCache::get('store_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'seller']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('store_config', $_conf);
        }
        //公众号设置
        if (!RedisCache::get('wechat_config')) {
            $m = (new WechatModel);
            $conf = $m->getWechat(['ru_id' => 0]);
            $_conf = [];
            if($conf){
                $_conf = $conf->toArray();
            }
            RedisCache::set('wechat_config', $_conf);
        }
        //支付宝支付设置
        if (!RedisCache::get('alipay_config')) {
            $m = (new PaymentModel);
            $conf = $m->getPayment(['pay_code' => 'alipay']);
            $_conf = [];
            if($conf){
                $_conf = $conf->toArray();
                foreach (unserialize($conf->pay_config) as $key => $value){
                    $_conf[$key] = $value;
                }
                unset($_conf['pay_config']);
            }
            RedisCache::set('alipay_config', $_conf);
        }
        //微信支付设置
        if (!RedisCache::get('wxpay_config')) {
            $m = (new PaymentModel);
            $conf = $m->getPayment(['pay_code' => 'wxpay']);
            $_conf = [];
            if($conf){
                $_conf = $conf->toArray();
                foreach (unserialize($conf->pay_config) as $key => $value){
                    $_conf[$key] = $value;
                }
                unset($_conf['pay_config']);
            }
            RedisCache::set('wxpay_config', $_conf);
        }
        //短信设置
        if (!RedisCache::get('sms_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'sms']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('sms_config', $_conf);
        }
        //阿里云OSS配置
        if (!RedisCache::get('oss_config')) {
            $m = (new AliossConfigureModel);
            $conf = $m->getAlioss(['is_use' => 1]);
            $_conf = [];
            if($conf){
                $_conf = $conf->toArray();
            }
            RedisCache::set('oss_config', $_conf);
        }
        //快递鸟配置
        if (!RedisCache::get('kdniao_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'tp_api']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('kdniao_config', $_conf);
        }
    }
}
