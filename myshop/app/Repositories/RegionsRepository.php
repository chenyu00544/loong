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

    public function getRegionsRange($type_s = 1, $type_e = 2)
    {
        $reArr = [];
        $arr = $this->regionsModel->getRegionsRange($type_s,$type_e);
        foreach ($arr as $item) {
            if($item->parent_id == 1){
                $reArr[$item->region_id]['id'] = $item->region_id;
                $reArr[$item->region_id]['name'] = $item->region_name;
            }else{
                $reArr[$item->parent_id]['subRegion'][$item->region_id]['id'] = $item->region_id;
                $reArr[$item->parent_id]['subRegion'][$item->region_id]['name'] = $item->region_name;
            }

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