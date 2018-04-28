<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\RegionsRepositoryInterface;
use App\Http\Models\Shop\RegionsModel;

class RegionsRepository implements RegionsRepositoryInterface
{

    protected $regionsModel;

    public function __construct(RegionsModel $regionsModel)
    {
        $this->regionsModel = $regionsModel;
    }

    public function getRegions($type = 0, $parent = 0)
    {
        $reArr = [];
        $arr = $this->regionsModel->getRegions($type, $parent);
        foreach ($arr as $item) {
            $re['id'] = $item->region_id;
            $re['name'] = $item->region_name;
            $reArr[] = $re;
        }
        return $reArr;
    }

    public function getArea($id)
    {
        $reArr = [
            'id' => 0,
            'name' => ''
        ];
        $arr = $this->regionsModel->getRegion($id);
        $reArr['id'] = $arr->region_id;
        $reArr['name'] = $arr->region_name;
        return $reArr;
    }

}