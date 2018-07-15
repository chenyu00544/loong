<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AdRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\AdModel;
use App\Http\Models\Shop\AdPositionModel;

class AdRepository implements AdRepositoryInterface
{

    private $adModel;
    private $adPositionModel;

    public function __construct(
        AdModel $adModel,
        AdPositionModel $adPositionModel
    )
    {
        $this->adModel = $adModel;
        $this->adPositionModel = $adPositionModel;
    }

    public function getAdByPage($type, $search)
    {
        $where['ad_position.ad_terminal'] = $type;
        return $this->adModel->getAdByPage($where, $search);
    }

    public function adChange($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['ad_id'] = $data['id'];
        $updata['enabled'] = $data['val'];
        $re = $this->adModel->setAd($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function delAd($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['ad_id'] = $id;
        $ad = $this->adModel->getAd($where);
        $re = $this->adModel->delAd($where);
        if ($re) {
            FileHandle::deleteFile($ad->ad_code);
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getAdPosByPage($type, $search)
    {
        return $this->adPositionModel->getAdPosByPage(['ad_terminal' => $type], $search);
    }

    public function getAdPos($id)
    {
        $where['position_id'] = $id;
        return $this->adPositionModel->getAdPos($where);
    }

    public function setAdPos($data, $id)
    {
        $where['position_id'] = $id;
        foreach ($data as $key => $value){
            $data[$key] = trim($value);
        }
        return $this->adPositionModel->setAdPos($where, $data);
    }

    public function addAdPos($data)
    {
        foreach ($data as $key => $value){
            $data[$key] = trim($value);
        }
        return $this->adPositionModel->addAdPos($data);
    }
}