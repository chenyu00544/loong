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
use App\Http\Models\Shop\MerchantsStepsTitleModel;

class MerchantsRepository implements MerchantsRepositoryInterface
{
    private $merchantsStepsProcessModel;
    private $merchantsStepsTitleModel;

    public function __construct(
        MerchantsStepsProcessModel $merchantsStepsProcessModel,
        MerchantsStepsTitleModel $merchantsStepsTitleModel
    )
    {
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
        $this->merchantsStepsTitleModel = $merchantsStepsTitleModel;
    }

    public function getMerchantsStepsProcessByPage()
    {
        return $this->merchantsStepsProcessModel->getMerchantsStepsProcessByPage([]);
    }

    public function getMerchantsStepsProcesses()
    {
        return $this->merchantsStepsProcessModel->getMerchantsStepsProcesses([]);
    }

    public function getMerchantsStepsProcess($id)
    {
        $where['id'] = $id;
        return $this->merchantsStepsProcessModel->getMerchantsStepsProcess($where);
    }

    public function setMerchantsStepsProcess($data, $id)
    {
        $where['id'] = $id;
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

    public function delMerchantsStepsProcess($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->merchantsStepsProcessModel->delMerchantsStepsProcess($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

    public function getMerchantsStepsTitleByPage($id)
    {
        $where['fields_steps'] = $id;
        return $this->merchantsStepsTitleModel->getMerchantsStepsTitleByPage($where);
    }

    public function getMerchantsStepsTitle($id)
    {
        $where['tid'] = $id;
        return $this->merchantsStepsTitleModel->getMerchantsStepsTitle($where);
    }

    public function setMerchantsStepsTitle($data, $id)
    {
        $where['tid'] = $id;
        return $this->merchantsStepsTitleModel->setMerchantsStepsTitle($where, $data);
    }

    public function addMerchantsStepsTitle($data)
    {
        return $this->merchantsStepsTitleModel->addMerchantsStepsTitle($data);
    }

    public function delMerchantsStepsTitle($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['tid'] = $id;
        $re = $this->merchantsStepsTitleModel->delMerchantsStepsTitle($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

}