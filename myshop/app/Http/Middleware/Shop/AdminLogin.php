<?php

namespace App\Http\Middleware\Shop;

use Closure;
use Illuminate\Support\Facades\Cache;

class AdminLogin
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
//        !session('user');
        if(!Cache::get('adminUser')){
            return redirect('admin/login');
        }
        return $next($request);
    }
}
