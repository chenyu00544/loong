<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;


class CommonService
{
    public static function test()
    {
        echo 111;
    }

    public static function md5Encrypt($pass, $salt)
    {
        return md5(md5($pass).$salt);
    }
}