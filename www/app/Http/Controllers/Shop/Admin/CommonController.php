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
use Illuminate\Http\Request;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    public $user;
    public $now_date;
    public $nav;

    public function __construct()
    {
        View::share('v', time());
        View::share('copyright', 'copyright © 2010-2028 vcvbuy.com,Inc.All rights reserved.');
        $this->now_date = date('Y-m-d', time()) . ' 00:00:00～' . date('Y-m-d', time()) . ' 23:59:59';
        $this->middleware(function ($request, $next) {
            $uid = $request->cookie('user_id');
            $ip = $request->getClientIp();
            $this->user = Cache::get('adminUser' . md5($ip) . $uid);
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
}
