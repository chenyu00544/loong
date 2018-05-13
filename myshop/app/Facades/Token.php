<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:57
 */

namespace App\Facades;

use Illuminate\Support\Facades\Facade;

class Token extends Facade
{
    protected static function getFacadeAccessor()
    {
        return 'TokenService';
    }
}