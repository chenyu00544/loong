<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SatisticsRepositoryInterface;

class SatisticsRepository implements SatisticsRepositoryInterface
{

    public function __construct()
    {
    }

    public function getSatistics($data)
    {
        switch ($data['type']){
            case 'order':
                foreach ($data['range'] as $range){

                }
                break;
            default:
                break;
        }
    }
}