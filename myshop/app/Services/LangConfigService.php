<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use Illuminate\Support\Facades\Config;

class LangConfigService
{

    public function __construct()
    {

    }

    public static function LangAdminConf()
    {
        return Config::get('languages.'.Config::get('config')['lang_type'].'.common');
    }

    public static function LangAdminNavConf()
    {
        return Config::get('languages.'.Config::get('config')['lang_type'].'.navigation');
    }

    public static function LangAdminShopConf()
    {
        return Config::get('languages.'.Config::get('config')['lang_type'].'.admin.shop_config');
    }

    public static function LangAdminCutNavConf()
    {
        return Config::get('languages.'.Config::get('config')['lang_type'].'.admin.cutnav_config');
    }

    public static function LangAdminIconsConf()
    {
        return Config::get('languages.'.Config::get('config')['lang_type'].'.icons');
    }
}