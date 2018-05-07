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
        $arr = $this->regionsModel->getRegionsRange($type_s, $type_e);
        foreach ($arr as $item) {
            if ($item->parent_id == 1) {
                $reArr[$item->region_id]['id'] = $item->region_id;
                $reArr[$item->region_id]['name'] = $item->region_name;
            } else {
                $reArr[$item->parent_id]['subRegion'][$item->region_id]['id'] = $item->region_id;
                $reArr[$item->parent_id]['subRegion'][$item->region_id]['name'] = $item->region_name;
            }

        }
        return $reArr;
    }

    public function getRegionsLevel($parent = 0, $type = 0)
    {
        $re = $this->regionsModel->getRegions($type, $parent, ['*']);
        $parent_re = $this->regionsModel->getRegion($parent, ['*']);

        foreach ($re as $region) {
            if ($region->parent_id == 0) {
                $region->parent_region = '-';
            } else {
                $region->parent_region = $parent_re->region_name;
            }
        }
        return $re;
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

    public function addRegion($data)
    {
        $updata = $data;
        return $this->regionsModel->addRegion($updata);
    }

    public function changes($data)
    {
        $req = ['code' => 5, 'msg' => '修改失败'];
        $where['region_id'] = $data['id'];
        $updata['region_name'] = $data['region_name'];
        $re = $this->regionsModel->setRegion($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '修改成功';
        }
        return $req;
    }

}