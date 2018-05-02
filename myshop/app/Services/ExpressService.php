<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use App\Facades\LangConfig;

class ExpressService
{
    public static function expressList()
    {
        $express = LangConfig::LangExpressConf();
        return $express;
    }
}