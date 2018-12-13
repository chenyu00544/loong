<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\SmsRepositoryInterface;
use App\Facades\ShopConfig;

class SmsRepository implements SmsRepositoryInterface
{

    public function __construct()
    {
    }

    public function getSmsConfig()
    {
        return ShopConfig::getSmsConfig();
    }

    public function setSmsConfig($data)
    {
        return ShopConfig::setConf($data, 'sms');
    }
}