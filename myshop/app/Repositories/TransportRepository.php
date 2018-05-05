<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\TransportRepositoryInterface;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\TransportExpressModel;
use App\Http\Models\Shop\TransportExtendModel;
use App\Http\Models\Shop\TransportModel;
use Illuminate\Support\Facades\Cache;

class TransportRepository implements TransportRepositoryInterface
{
    protected $transportModel;
    protected $transportExtendModel;
    protected $transportExpressModel;
    protected $regionsModel;

    public function __construct(
        TransportModel $transportModel,
        TransportExtendModel $transportExtendModel,
        TransportExpressModel $transportExpressModel,
        RegionsModel $regionsModel
    )
    {
        $this->transportModel = $transportModel;
        $this->transportExtendModel = $transportExtendModel;
        $this->transportExpressModel = $transportExpressModel;
        $this->regionsModel = $regionsModel;
    }

    public function getTransportAll()
    {
        return $this->transportModel->getTransportAll();
    }

    public function getTransportInfo($id = 0)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;

        $extends = $this->transportExtendModel->getExtendAll(['ru_id' => 0, 'admin_id' => $userId]);
        $regions = $this->regionsModel->getRegionsRange(1,2);
        $regionArr = [];
        foreach ($regions as $region){
            $regionArr[$region->region_id]['name'] = $region->region_name;
            $regionArr[$region->region_id]['pid'] = $region->parent_id;
        }
        foreach ($extends as $extend){
            $topAreas = explode(',', $extend->top_area_id);
            $areas = explode(',', $extend->area_id);
            foreach ($topAreas as $topArea){
                $extend->area_html .= '<p><strong>';
                $extend->area_html .= $regionArr[$topArea]['name'].':</strong>';

                foreach ($areas as $area) {
                    if($regionArr[$area]['pid'] == $topArea){
                        $extend->area_html .= $regionArr[$area]['name'].',';
                    }
                }
                $extend->area_html = substr($extend->area_html, 0, -1);
                $extend->area_html .= '</p>';
            }
        }

        $express = $this->transportExpressModel->getExpressAll(['ru_id' => 0, 'admin_id' => $userId]);
        $portInfo = $this->transportModel->getTransport($id);
        $req['extend'] = $extends;
        $req['express'] = $express;
        $req['transport'] = $portInfo;
        return $req;
    }

    public function setTransport($data)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;
        $id = 0;
        $type = '';
        $updata = [];
        foreach ($data as $key => $val){
            switch ($key){
                case 'type':
                    $type = $val;
                    break;
                case 'id':
                    $id = $val;
                    break;
                default:
                    $updata[$key] = $val;
                    break;
            }
        }
        if($type == 'area_add'){
            return $this->transportExtendModel->addExtend(['ru_id' => 0, 'admin_id' => $userId]);
        }elseif($type == 'area_del'){
            return $this->transportExtendModel->delExtend(['id' => $id]);
        }elseif($type == 'area_update'){
            return $this->transportExtendModel->setExtend(['id' => $id], $updata);
        }elseif($type == 'express_add'){
            return $this->transportExpressModel->addExpress(['ru_id' => 0, 'admin_id' => $userId]);
        }elseif($type == 'express_del'){
            return $this->transportExpressModel->delExpress(['id' => $id]);
        }
    }
}