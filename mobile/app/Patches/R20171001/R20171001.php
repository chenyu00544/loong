<?php

namespace App\Patches\R20171001;

use App\Patches\Factory\PatchInterface;

class R20171001 implements PatchInterface
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
        $root_path = ROOT_PATH;

        /**
         * 清理原模块
         */
        $list = glob($root_path . 'app/Http/*');

        foreach ($list as $item) {
            $path = $root_path . 'app/Http/' . $item;
            if (is_dir($path) && basename($item) === 'Proxy') {
                del_dir($path);
            }
        }

        /**
         * 清理原插件
         */
        $list = [
            'connect',
            'integrates',
            'payment',
            'shipping',
        ];

        foreach ($list as $item) {
            $path = $root_path . 'app/Modules/' . $item;
            if (is_dir($path)) {
                del_dir($path);
            }
        }
    }
}
