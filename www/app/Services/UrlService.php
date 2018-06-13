<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;


use Illuminate\Support\Facades\Config;

class UrlService
{
    public static function test()
    {
        echo 111;
    }

    public static function getImagePath($uri)
    {
        $shopConfig = Config::get('shopconfig');

        if (empty($uri)) {
            if ($shopConfig['open_oss'] == 1) {
                return $shopConfig['cdn_url'] . 'styles/images/no_image.jpg';
            } else {
                return Config::get('config')['site_domain'] . 'styles/images/no_image.png';
            }
        }

        if ($shopConfig['open_oss'] == 1) {
            return $shopConfig['cdn_url'] . '' . $uri;
        } else {
            return Config::get('config')['site_domain'] . '' . $uri;
        }
    }

}