<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use App\Http\Models\Shop\RegionsModel;
use Illuminate\Support\Facades\Config;

class RegionsService
{
    public static function getRegion($type = 0, $parent = 0)
    {
        $reArr = [];
        $arr = (new RegionsModel())->getRegions($parent);
        foreach ($arr as $item){
            $re['id'] = $item->region_id;
            $re['name'] = $item->region_name;
            $reArr[] = $re;
        }
        return $reArr;
    }

    public static function getArea($id)
    {
        $reArr = [
            'id' => 0,
            'name' => ''
        ];
        $arr = (new RegionsModel())->getRegion($id);
        $reArr['id'] = $arr->region_id;
        $reArr['name'] = $arr->region_name;
        return $reArr;
    }

    public static function getGeoJson()
    {
        return file_get_contents(base_path().'/public/geojson/china.json');
    }

}