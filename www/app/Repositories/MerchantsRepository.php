<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\MerchantsRepositoryInterface;
use App\Http\Models\Shop\MerchantsStepsProcessModel;

class MerchantsRepository implements MerchantsRepositoryInterface
{
    private $merchantsStepsProcessModel;

    public function __construct(
        MerchantsStepsProcessModel $merchantsStepsProcessModel
    )
    {
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
    }

    public function getMerchantsStepsProcessByPage()
    {
        return $this->merchantsStepsProcessModel->getMerchantsStepsProcessByPage([]);
    }

    public function getMerchantsStepsProcess($id)
    {
        $where['id'] = $id;
        return $this->merchantsStepsProcessModel->getMerchantsStepsProcess($where);
    }

    public function setMerchantsStepsProcess($where, $data)
    {
        return $this->merchantsStepsProcessModel->setMerchantsStepsProcess($where, $data);
    }

    public function addMerchantsStepsProcess($data)
    {
        return $this->merchantsStepsProcessModel->addMerchantsStepsProcess($data);
    }

    public function merchantsStepsProcessChange($data)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']){
            case 'is_show':
                $updata['is_show'] = $data['val'];
                break;
            case 'order':
                $updata['steps_sort'] = $data['val'];
                break;
        }
        $re = $this->merchantsStepsProcessModel->setMerchantsStepsProcess($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }
}