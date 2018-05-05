<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\TransportRepositoryInterface;
use App\Http\Models\Shop\TransportExpressModel;
use App\Http\Models\Shop\TransportExtendModel;
use App\Http\Models\Shop\TransportModel;
use Illuminate\Support\Facades\Cache;

class TransportRepository implements TransportRepositoryInterface
{
    protected $transportModel;
    protected $transportExtendModel;
    protected $transportExpressModel;

    public function __construct(
        TransportModel $transportModel,
        TransportExtendModel $transportExtendModel,
        TransportExpressModel $transportExpressModel
    )
    {
        $this->transportModel = $transportModel;
        $this->transportExtendModel = $transportExtendModel;
        $this->transportExpressModel = $transportExpressModel;
    }

    public function getTransportAll()
    {
        return $this->transportModel->getTransportAll();
    }

    public function getTransportInfo($id = 0)
    {
        $adminUser = Cache::get('adminUser');
        $userId = $adminUser->user_id;

        $extend = $this->transportExtendModel->getExtendAll(['ru_id' => 0, 'admin_id' => $userId]);
        $express = $this->transportExpressModel->getExpressAll(['ru_id' => 0, 'admin_id' => $userId]);
        $portInfo = $this->transportModel->getTransport($id);
        $req['extend'] = $extend;
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