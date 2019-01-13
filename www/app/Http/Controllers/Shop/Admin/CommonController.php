<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 公共功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Facades\RedisCache;
use App\Http\Models\Shop\AliossConfigureModel;
use App\Http\Models\Shop\AlismsConfigureModel;
use App\Http\Models\Shop\CronsModel;
use App\Http\Models\Shop\EmailConfigureModel;
use App\Http\Models\Shop\PaymentModel;
use App\Http\Models\Shop\ShopConfigModel;
use App\Http\Models\Shop\WechatModel;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    public $user;
    public $now_date;
    public $nav;

    public function __construct()
    {
        View::share('v', time());
//        View::share('debug', env("APP_DEBUG", false));
        View::share('debug', true);
        View::share('copyright', 'copyright © 2010-2028 vcvbuy.com,Inc.All rights reserved.');
        $this->now_date = date('Y-m-d', time()) . ' 00:00:00～' . date('Y-m-d', time()) . ' 23:59:59';
        $this->middleware(function ($request, $next) {
            $uid = $request->cookie('user_id');
            $ip = $request->getClientIp();
            $this->user = RedisCache::get('adminUser' . md5($ip) . $uid);
            if (!$this->user) {
//                $this->user = Cache::get('adminUser' . md5($ip) . $uid);
            }
            $this->privilege();
            return $next($request);
        });
    }

    public function privilege()
    {
        $this->nav = LangConfig::LangAdminNavConf();
        if (!empty($this->user) && empty($this->user->action_list['all'])) {
            foreach ($this->nav['index'] as $key => $value) {
                foreach ($this->nav[$key] as $k => $val) {
                    if (empty($this->user->action_list[$k])) {
                        unset($this->nav[$key][$k]);
                    }
                    foreach ($this->nav[$k] as $n => $m) {
                        if (empty($this->user->action_list[$n])) {
                            unset($this->nav[$k][$n]);
                        }
                    }
                }
            }
        }
        foreach ($this->nav as $key => $value) {
            if (empty($this->nav[$key])) {
                unset($this->nav[$key]);
            }
        }
        foreach ($this->nav['index'] as $key => $value) {
            if (empty($this->nav[$key])) {
                unset($this->nav['index'][$key]);
                unset($this->nav[$key]);
            }
        }
    }

    public function sellerPrivilege()
    {
        $nav = RedisCache::get('seller_nav');
        if (empty($nav)) {
            $nav = LangConfig::LangSellerNavConf();
            RedisCache::set('seller_nav', $nav);
        }
        foreach ($nav['index'] as $key => $value) {
            if (empty($nav[$key])) {
                unset($nav['index'][$key]);
                unset($nav[$key]);
            }
        }
        return $nav;
    }

    public function checkPrivilege($code)
    {
        $this->middleware(function ($request, $next) use ($code) {
            if (!empty($this->user) && empty($this->user->action_list['all'])) {
                if (!in_array($code, $this->user->action_list)) {
                    return redirect('admin/info');
                }
            }
            return $next($request);
        });
    }

    public function getTable($table, $id, $bit = 5, $seed = 20)
    {
        //fixme 分表 场景在高并发性
        return $table . '_' . sprintf('%0' . $bit . 'd', ($id >> $seed));
    }

    public function setConfCache()
    {
        //fixme 商城设置
        if (!RedisCache::get('shop_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getConf();
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('shop_config', $_conf);
        }

        //fixme 商品设置
        if (!RedisCache::get('goods_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'goods']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('goods_config', $_conf);
        }

        //fixme 店铺设置
        if (!RedisCache::get('store_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'seller']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('store_config', $_conf);
        }

        //fixme 公众号设置
        if (!RedisCache::get('wechat_config')) {
            $m = (new WechatModel);
            $conf = $m->getWechat(['ru_id' => 0]);
            $_conf = [];
            if ($conf) {
                $_conf = $conf->toArray();
            }
            RedisCache::set('wechat_config', $_conf);
        }

        //fixme 支付宝支付设置
        if (!RedisCache::get('alipay_config')) {
            $m = (new PaymentModel);
            $conf = $m->getPayment(['pay_code' => 'alipay']);
            $_conf = [];
            if ($conf) {
                $_conf = $conf->toArray();
                foreach (unserialize($conf->pay_config) as $key => $value) {
                    $_conf[$key] = $value;
                }
                unset($_conf['pay_config']);
            }
            RedisCache::set('alipay_config', $_conf);
        }

        //fixme 微信支付设置
        if (!RedisCache::get('wxpay_config')) {
            $m = (new PaymentModel);
            $conf = $m->getPayment(['pay_code' => 'wxpay']);
            $_conf = [];
            if ($conf) {
                $_conf = $conf->toArray();
                foreach (unserialize($conf->pay_config) as $key => $value) {
                    $_conf[$key] = $value;
                }
                unset($_conf['pay_config']);
            }
            RedisCache::set('wxpay_config', $_conf);
        }

        //fixme 短信设置
        if (!RedisCache::get('sms_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'sms']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('sms_config', $_conf);
        }

        //fixme 阿里云OSS配置
        if (!RedisCache::get('oss_config')) {
            $m = (new AliossConfigureModel);
            $conf = $m->getAlioss(['is_use' => 1]);
            $_conf = [];
            if ($conf) {
                $_conf = $conf->toArray();
            }
            RedisCache::set('oss_config', $_conf);
        }

        //fixme 快递鸟配置
        if (!RedisCache::get('kdniao_config')) {
            $m = (new ShopConfigModel);
            $conf = $m->getGroupsConfig(['shop_group' => 'tp_api']);
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->code] = $value->value;
            }
            RedisCache::set('kdniao_config', $_conf);
        }

        //fixme 短信类型
        if (!RedisCache::get('sms_type')) {
            $m = (new AlismsConfigureModel);
            $conf = $m->getAlismses();
            $_conf = [];
            foreach ($conf as $value) {
                $_conf[$value->send_time] = $value->toArray();
            }
            RedisCache::set('sms_type', $_conf);
        }

        //fixme 邮件配置
        if (!RedisCache::get('smtp_config')) {
            $m = (new EmailConfigureModel);
            $conf = $m->getEmailConfigure();
            $_conf = [];
            if ($conf) {
                $_conf = $conf->toArray();
            }
            RedisCache::set('smtp_config', $_conf);
        }

        //fixme 定时任务配置
        if (!RedisCache::get('cron_config')) {
            $m = (new CronsModel);
            $crons = $m->getCrons();
            $_conf = [];
            foreach ($crons as $cron) {
                $_conf[$cron->cron_id] = $cron;
            }
            RedisCache::set('cron_config', $_conf);
        }
    }

    public function success($uri, $param = '', $_uri = '')
    {
        $back_url = [
            '<div class=""><a href="' . url($uri) . '/' . $param . '">返回列表</a></div>',
            '<div class=""><a href="' . url((!empty($_uri) ? $_uri : $uri . '/create')) . '">继续添加</a></div>'
        ];
        return $back_url;
    }
}
