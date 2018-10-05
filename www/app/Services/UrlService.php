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
    public static function getImagePath($uri)
    {
        if (empty($uri)) {
            return Config::get('config')['site_domain'] . 'styles/images/no_image.png';
        }else{
            return Config::get('config')['site_domain'] . '' . $uri;
        }
    }
}