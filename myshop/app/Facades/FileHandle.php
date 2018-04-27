<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/18
 * Time: 20:08
 */

namespace App\Facades;

use Illuminate\Support\Facades\Facade;

class FileHandle extends Facade
{
    protected static function getFacadeAccessor()
    {
        return 'FileHandleService';
    }
}