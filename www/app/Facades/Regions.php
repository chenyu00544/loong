<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/2
 * Time: 20:56
 */

namespace App\Facades;

use Illuminate\Support\Facades\Facade;

class Regions extends Facade
{
    protected static function getFacadeAccessor()
    {
        return 'RegionsService';
    }
}