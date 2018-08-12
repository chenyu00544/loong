<?php

namespace App\Http\Middleware\Shop;

use App\Facades\RedisCache;
use Closure;

class AdminLogin
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  \Closure $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $user = RedisCache::get('adminUser' . md5($ip) . $uid);
        if (!$user) {
            return redirect('admin/login');
        }
        return $next($request);
    }
}
