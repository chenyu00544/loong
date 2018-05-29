<?php

namespace App\Patches\R20180111;

use App\Patches\Factory\PatchInterface;

class R20180111 implements PatchInterface
{

    /**
     * @var array
     */
    private $convert = [];

    /**
     * 提供给控制器的 接口 函数。每个版本类必须有该函数。
     */
    public function updateDatabaseOptionally()
    {
        return false;
    }

    /**
     * 提供给控制器的 接口 函数。每个版本类必须有该函数。
     */
    public function updateFiles()
    {
        del_dir(ROOT_PATH .  'vendor/phpunit');
    }
}
