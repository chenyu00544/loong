<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\StoreListRepositoryInterface;
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\MerchantsStepsFieldsModel;
use App\Http\Models\Shop\MerchantsStepsProcessModel;
use App\Http\Models\Shop\RegionsModel;

class StoreListRepository implements StoreListRepositoryInterface
{

    private $merchantsShopInformationModel;
    private $merchantsStepsProcessModel;
    private $merchantsStepsFieldsModel;
    private $regionsModel;

    public function __construct(
        MerchantsShopInformationModel $merchantsShopInformationModel,
        MerchantsStepsProcessModel $merchantsStepsProcessModel,
        MerchantsStepsFieldsModel $merchantsStepsFieldsModel,
        RegionsModel $regionsModel
    )
    {
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
        $this->merchantsStepsFieldsModel = $merchantsStepsFieldsModel;
        $this->regionsModel = $regionsModel;
    }

    public function getStoresByPage($search, $where = [], $type = '')
    {
        if ($type == 'maudit') {
            $search['examine'] = 1;
        } elseif ($type == 'saudit') {
            $search['examine'] = 2;
        }
        return $this->merchantsShopInformationModel->getMerchantsShopsByPage($where, $search);
    }

    public function setStore($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['shop_id'] = $data['id'];
        $updata = [];
        unset($data['id']);
        switch ($data['type']) {
            case 'is_street':
                $updata['is_street'] = $data['val'];
                break;
            case 'sort':
                $updata['sort_order'] = $data['val'];
                break;
            case 'is_im':
                $updata['is_im'] = $data['val'];
                break;
        }

        $re = $this->merchantsShopInformationModel->setMerchantsShopInfo($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function addStore($data)
    {
        dd($data);
        if (!empty($data['user_id'])) {
            $fid = $this->merchantsStepsFieldsModel->getMerchantsStepsFields(['user_id' => $data['user_id']], ['fid']);
            if ($fid) {
                return ['code' => 5, 'msg' => '已经申请过'];
            } else {
                $msfData = [];
                $this->merchantsStepsFieldsModel->addMerchantsStepsFields($msfData);
            }
        }
    }

    public function getStepsByShopInfo()
    {
        $steps = $this->merchantsStepsProcessModel->getMerchantsStepsProcessesByTitleAndContent();
        foreach ($steps as $step) {
            foreach ($step->mst as $mst) {
                $mst->textFields = explode(',', $mst->textFields);
                $mst->fieldsDateType = explode(',', $mst->fieldsDateType);
                $mst->fieldsLength = explode(',', $mst->fieldsLength);
                $mst->fieldsNotnull = explode(',', $mst->fieldsNotnull);
                $mst->fieldsFormName = explode(',', $mst->fieldsFormName);
                $mst->fieldsCoding = explode(',', $mst->fieldsCoding);
                $mst->fieldsForm = explode('|', $mst->fieldsForm);
                $mst->fields_sort = explode(',', $mst->fields_sort);
                $mst->will_choose = explode(',', $mst->will_choose);
                $field = [];
                foreach ($mst->fieldsForm as $key => $fields) {
                    $field[$key] = [];
                    if (!empty($fields)) {
                        $arr = explode('+', $fields);
                        if (!empty($arr[0])) {
                            $form = explode(':', $arr[0]);
                            $field[$key]['type'] = $form[0];
                            if (!empty($form[1])) {
                                $field[$key]['value'] = explode(',', $form[1]);
                            }
                        }
                        $field[$key]['notic'] = $arr[1];
                    }
                    $mst->fieldsForm = $field;
                }
            }
        }
        return $steps;
    }

}