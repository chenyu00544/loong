<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\StoreListRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\MerchantsGradeModel;
use App\Http\Models\Shop\MerchantsShopBrandModel;
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\MerchantsStepsFieldsModel;
use App\Http\Models\Shop\MerchantsStepsProcessModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\SellerShopInfoModel;

class StoreListRepository implements StoreListRepositoryInterface
{

    private $merchantsShopInformationModel;
    private $merchantsStepsProcessModel;
    private $merchantsStepsFieldsModel;
    private $merchantsGradeModel;
    private $merchantsShopBrandModel;
    private $sellerShopInfoModel;
    private $regionsModel;

    public function __construct(
        MerchantsShopInformationModel $merchantsShopInformationModel,
        MerchantsStepsProcessModel $merchantsStepsProcessModel,
        MerchantsStepsFieldsModel $merchantsStepsFieldsModel,
        MerchantsGradeModel $merchantsGradeModel,
        MerchantsShopBrandModel $merchantsShopBrandModel,
        SellerShopInfoModel $sellerShopInfoModel,
        RegionsModel $regionsModel
    )
    {
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
        $this->merchantsStepsFieldsModel = $merchantsStepsFieldsModel;
        $this->merchantsGradeModel = $merchantsGradeModel;
        $this->merchantsShopBrandModel = $merchantsShopBrandModel;
        $this->sellerShopInfoModel = $sellerShopInfoModel;
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
            $uid = $data['user_id'];
            $fid = $this->merchantsStepsFieldsModel->getMerchantsStepsFields(['user_id' => $uid], ['fid']);
            if ($fid) {
                return ['code' => 5, 'msg' => '已经申请过'];
            } else {
                $msfData = [];

                //添加店铺等级
                if ($data['merchants_audit'] == 1) {
                    $mgData = ['grade_id' => $data['grade_id'], 'year_num' => $data['year_num'], 'ru_id' => $uid, 'add_time' => time()];
                    $this->merchantsGradeModel->addMerchantsGrade($mgData);
                }

                $msp = $this->merchantsStepsProcessModel->getMerchantsStepsProcessesByTitleAndContent();
                $formKey = [];
                foreach ($msp as $step) {
                    foreach ($step->mst as $mst) {
                        $formKey = array_merge($formKey, explode(',', $mst->textFields));
                    }
                }
                $formKey = array_filter($formKey);

                foreach ($data as $key => $value) {
                    if (in_array($key, $formKey)) {//处理上传的图片
                        if (strpos($key, '_fileImg') !== false) {
                            $path = 'merchants_fields' . DIRECTORY_SEPARATOR . str_replace('_fileImg', '', $key);
                            $uri = FileHandle::upLoadImage($value, $path);
                            $msfData[$key] = $uri;
                        } else {
                            if ($key == 'license_comp_adress' || $key == 'company_located' || $key == 'linked_bank_address') {
                                $msfData[$key] = implode(',', $value);
                            } else {
                                $msfData[$key] = $value;
                            }
                        }
                    } else {

                    }
                }
                $msfData['user_id'] = $uid;
                $msfData['agreement'] = 1;
                $this->merchantsStepsFieldsModel->addMerchantsStepsFields($msfData);
                dd($msfData);

                $ssiData = array(
                    'shopname_audit' => $data['shopname_audit'],
                    'shop_close' => $data['shop_close']
                );
                $ssi = $this->sellerShopInfoModel->setSellerShopInfo(['ru_id' => $uid], $ssiData);
                if (!$ssi) {
                    $ssiData['mobile'] = $data['contactPhone'];
                    $ssiData['seller_email'] = $data['contactEmail'];
                    $ssiData['shop_address'] = $data['company_adress'];
                    $ssiData['shop_name'] = $data['companyName'];
                    $ssiData['kf_tel'] = $data['company_contactTel'];
                    $ssiData['seller_templates'] = 'seller_default';
                    $ssiData['ru_id'] = $uid;
                    $ssiData['templates_mode'] = 1;
                    if (count($data['company_located']) >= 4) {
                        $region = $data['company_located'];
                        $ssiData['country'] = $region[0];
                        $ssiData['province'] = $region[1];
                        $ssiData['city'] = $region[2];
                        $ssiData['district'] = $region[3];
                    }
                    $this->sellerShopInfoModel->addSellerShopInfo($ssiData);
                }

                $shop_expireDate = explode('～', $data['shop_expireDate']);
                $authorizeFile = '';
                $shop_hypermarketFile = '';
                if($data['subShoprz_type'] == 2){
                    $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                    $authorizeFile = FileHandle::upLoadImage($data['authorizeFile'], $path);
                }elseif($data['subShoprz_type'] == 3){
                    $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                    $authorizeFile = FileHandle::upLoadImage($data['shop_hypermarketFile'], $path);
                }

                $msiData = [
                    'shoprz_type' => $data['shoprz_type'],
                    'subShoprz_type' => $data['subShoprz_type'],
                    'shop_expireDateStart' => $shop_expireDate[0],
                    'shop_expireDateEnd' => $shop_expireDate[1],
                    'shop_permanent' => $data['shop_permanent'],
                    'authorizeFile' => $authorizeFile,
                    'shop_hypermarketFile' => $shop_hypermarketFile,
                    'shop_categoryMain' => $data['shop_categoryMain']
                ];

                $msi = $this->merchantsShopInformationModel->setMerchantsShopInfo(['user_id' => $uid], $msiData);
                if(!$msi){
                    $msiData['user_id'] = $uid;
                }

                $msbData['user_id'] = $uid;
                if(!empty($data['bid'])){
                    $this->merchantsShopBrandModel->setMerchantsShopBrand([], $msbData, $data['bid']);
                }

                return ['code' => 1, 'msg' => '操作成功'];
            }
        }
        return ['code' => 5, 'msg' => '操作失败'];
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