<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:58
 */

namespace App\Providers;

use App\Services\FileHandleService;
use Illuminate\Support\ServiceProvider;

class FileHandleServiceProvider extends ServiceProvider
{
    /**
     * Register the application services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton('FileHandleService', function () {
            return new FileHandleService();
        });
    }
}