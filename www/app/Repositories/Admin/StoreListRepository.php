<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\StoreListRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\AdminUserModel;
use App\Http\Models\Shop\MerchantsCategoryTemporarydateModel;
use App\Http\Models\Shop\MerchantsDocumentTitleModel;
use App\Http\Models\Shop\MerchantsDtFileModel;
use App\Http\Models\Shop\MerchantsGradeModel;
use App\Http\Models\Shop\MerchantsPrivilegeModel;
use App\Http\Models\Shop\MerchantsShopBrandModel;
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\MerchantsStepsFieldsModel;
use App\Http\Models\Shop\MerchantsStepsProcessModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\SellerGradeModel;
use App\Http\Models\Shop\SellerShopInfoModel;

class StoreListRepository implements StoreListRepositoryInterface
{

    private $merchantsShopInformationModel;
    private $merchantsStepsProcessModel;
    private $merchantsStepsFieldsModel;
    private $merchantsGradeModel;
    private $merchantsShopBrandModel;
    private $merchantsPrivilegeModel;
    private $merchantsDtFileModel;
    private $merchantsCategoryTemporarydateModel;
    private $merchantsDocumentTitleModel;
    private $sellerShopInfoModel;
    private $sellerGradeModel;
    private $adminUserModel;
    private $regionsModel;

    public function __construct(
        MerchantsShopInformationModel $merchantsShopInformationModel,
        MerchantsStepsProcessModel $merchantsStepsProcessModel,
        MerchantsStepsFieldsModel $merchantsStepsFieldsModel,
        MerchantsGradeModel $merchantsGradeModel,
        MerchantsShopBrandModel $merchantsShopBrandModel,
        MerchantsPrivilegeModel $merchantsPrivilegeModel,
        MerchantsDtFileModel $merchantsDtFileModel,
        MerchantsCategoryTemporarydateModel $merchantsCategoryTemporarydateModel,
        MerchantsDocumentTitleModel $merchantsDocumentTitleModel,
        SellerShopInfoModel $sellerShopInfoModel,
        SellerGradeModel $sellerGradeModel,
        AdminUserModel $adminUserModel,
        RegionsModel $regionsModel
    )
    {
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
        $this->merchantsStepsFieldsModel = $merchantsStepsFieldsModel;
        $this->merchantsGradeModel = $merchantsGradeModel;
        $this->merchantsShopBrandModel = $merchantsShopBrandModel;
        $this->merchantsPrivilegeModel = $merchantsPrivilegeModel;
        $this->merchantsDtFileModel = $merchantsDtFileModel;
        $this->merchantsCategoryTemporarydateModel = $merchantsCategoryTemporarydateModel;
        $this->merchantsDocumentTitleModel = $merchantsDocumentTitleModel;
        $this->sellerShopInfoModel = $sellerShopInfoModel;
        $this->sellerGradeModel = $sellerGradeModel;
        $this->adminUserModel = $adminUserModel;
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

    public function getStore($id)
    {
        $uid = $id;
        $rep['msf'] = $this->merchantsStepsFieldsModel->getMerchantsStepsFields(['user_id' => $uid]);
        $regionsArr = explode(',', $rep['msf']->license_comp_adress);
        $regions = [];
        foreach ($regionsArr as $val) {
            $regions[] = $this->regionsModel->getRegion($val);
        }
        $rep['msf']->license_comp_adress = $regions;
        $regionsArr = explode(',', $rep['msf']->company_located);
        $regions = [];
        foreach ($regionsArr as $val) {
            $regions[] = $this->regionsModel->getRegion($val);
        }
        $rep['msf']->company_located = $regions;
        $regionsArr = explode(',', $rep['msf']->linked_bank_address);
        $regions = [];
        foreach ($regionsArr as $val) {
            $regions[] = $this->regionsModel->getRegion($val);
        }
        $rep['msf']->linked_bank_address = $regions;

        $rep['mg'] = $this->merchantsGradeModel->getMerchantsGrade(['ru_id' => $uid]);
        $rep['ssi'] = $this->sellerShopInfoModel->getSellerShopInfo(['ru_id' => $uid]);
        $rep['msi'] = $this->merchantsShopInformationModel->getMerchantsShopInfo(['user_id' => $uid]);
        $rep['msb'] = $this->merchantsShopBrandModel->getMerchantsShopBrands(['user_id' => $uid]);
        $rep['mdf'] = $this->merchantsDtFileModel->getMerchantsDtFiles(['user_id' => $uid]);
        $rep['mcts'] = $this->merchantsCategoryTemporarydateModel->getMerchantsCategoryTemporarydates(['user_id' => $uid]);
        $cat_ids = [];
        foreach ($rep['mcts'] as $mct) {
            $cat_ids[] = $mct->cat_id;
        }
        $rep['mdt'] = $this->merchantsDocumentTitleModel->getMerchantsDocumentTitles($cat_ids, $id);
        return $rep;
    }

    public function setStore($data, $id)
    {
        dd($data);
        if (!empty($id)) {
            $uid = $id;

            //添加店铺等级
            if ($data['merchants_audit'] == 1) {
                //店铺等级
                if (empty($data['grade_id'])) {
                    $sg = $this->sellerGradeModel->getSellerGrade(['is_default' => 1]);
                    $grade_id = $sg->id;
                } else {
                    $grade_id = $data['grade_id'];
                }
                $mgData = [
                    'year_num' => $data['year_num'],
                    'grade_id' => $grade_id
                ];
                $mg = $this->merchantsGradeModel->setMerchantsGrade(['ru_id' => $uid], $mgData);
                if (!$mg) {
                    $mgData['ru_id'] = $uid;
                    $mgData['add_time'] = time();
                    $this->merchantsGradeModel->addMerchantsGrade($mgData);
                }
            }

            $msfData = [];
            $msp = $this->merchantsStepsProcessModel->getMerchantsStepsProcessesByTitleAndContent();
            $formKey = [];
            foreach ($msp as $step) {
                foreach ($step->mst as $mst) {
                    $formKey = array_merge($formKey, explode(',', $mst->textFields));
                }
            }
            $formKey = array_filter($formKey);
            foreach ($data as $key => $value) {
                if (in_array($key, $formKey)) {
                    if (strpos($key, '_fileImg') !== false) {//处理上传的图片
                        $path = 'merchants_fields' . DIRECTORY_SEPARATOR . strtolower($key);
                        $uri = FileHandle::upLoadImage($value, $path);
                        $msfData[$key] = $uri;
                        FileHandle::deleteFile($data[substr($key, 0, -3) . '_bak']);
                    } else {
                        if ($key == 'license_comp_adress' || $key == 'company_located' || $key == 'linked_bank_address') {
                            $msfData[$key] = implode(',', $value);
                        } else {
                            $msfData[$key] = $value;
                        }
                    }
                }
            }
            $msfData['user_id'] = $uid;
            $msfData['agreement'] = 1;
            unset($msfData['legal_person_file_bak']);
            unset($msfData['license_file_bak']);
            unset($msfData['organization_file_bak']);
            unset($msfData['tax_file_bak']);
            unset($msfData['status_tax_file_bak']);
            unset($msfData['linked_bank_file_bak']);
            unset($msfData['preVendorId_file_bak']);
            $this->merchantsStepsFieldsModel->setMerchantsStepsFields(['user_id' => $uid], $msfData);

            //商家入驻后设置店铺基本信息
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


            //商家入驻时基本信息
            $shop_expireDate = explode('～', $data['shop_expireDate']);
            $authorizeFile = '';
            $shop_hypermarketFile = '';
            if ($data['subShoprz_type'] == 2) {
                $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                $authorizeFile = FileHandle::upLoadImage($data['authorizeFile'], $path);
            } elseif ($data['subShoprz_type'] == 3) {
                $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                $shop_hypermarketFile = FileHandle::upLoadImage($data['shop_hypermarketFile'], $path);
            }
            $msiData = [
                'shoprz_type' => $data['shoprz_type'],
                'subShoprz_type' => $data['subShoprz_type'],
                'shop_expireDateStart' => $shop_expireDate[0],
                'shop_expireDateEnd' => $shop_expireDate[1],
                'shop_permanent' => $data['shop_permanent'],
                'authorizeFile' => $authorizeFile,
                'shop_hypermarketFile' => $shop_hypermarketFile,
                'shop_categoryMain' => $data['shop_categoryMain'],
                'merchants_audit' => $data['merchants_audit'],
                'review_goods' => $data['review_goods'],
                'self_run' => $data['self_run'],
                'shoprz_brandName' => $data['shoprz_brandName'],
                'shop_class_keyWords' => $data['shop_class_keyWords'],
                'shopNameSuffix' => $data['shopNameSuffix'],
                'rz_shopName' => $data['rz_shopName'],
                'hopeLoginName' => $data['hopeLoginName'],
                'steps_audit' => 1,
            ];
            $msi = $this->merchantsShopInformationModel->setMerchantsShopInfo(['user_id' => $uid], $msiData);
            if (!$msi) {
                $msiData['user_id'] = $uid;
                $this->merchantsShopInformationModel->addMerchantsShopInfo($msiData);
            }

            //店铺品牌
            $msbData['user_id'] = $uid;
            if (!empty($data['bid'])) {
                $this->merchantsShopBrandModel->setMerchantsShopBrand([], $msbData, $data['bid']);
            }

            //商家入驻流程分类资质表
            if (!empty($data['dtf'])) {
                foreach ($data['dtf'] as $key => $dtf_id) {
                    $dtfwhere['dtf_id'] = $dtf_id;
                    $path = 'merchants_dtf' . DIRECTORY_SEPARATOR . $uid;
                    $permanent_file = FileHandle::upLoadImage($data['permanent_file'][$key], $path);
                    $dtfData['permanent_file'] = $permanent_file;
                    $dtfData['user_id'] = $uid;
                    if (!empty($data['cate_title_permanent'][$key]) && $data['cate_title_permanent'][$key] == 1) {
                        $dtfData['cate_title_permanent'] = $data['cate_title_permanent'][$key];
                    } else {
                        $dtfData['permanent_date'] = $data['permanent_date'][$key];
                    }
                    $this->merchantsDtFileModel->setMerchantsDtFile($dtfwhere, $dtfData);
                }
            }

            //商家入驻流程填写分类临时信息表
            $cat_id = !empty($data['cat_id']) ? $data['cat_id'] : (!empty($data['subcate']) ? $data['subcate'] : []);
            if (!empty($cat_id)) {
                foreach ($cat_id as $key => $catid) {
                    $mctwhere['cat_id'] = $catid;
                    $mctData['user_id'] = $uid;
                    $mctData['is_add'] = 1;
                    $this->merchantsCategoryTemporarydateModel->setMerchantsCategoryTemporarydate($mctwhere, $mctData);
                }
            }

            return ['code' => 1, 'msg' => '操作成功'];

        }
        return ['code' => 5, 'msg' => '操作失败'];
    }

    public function addStore($data)
    {
        if (!empty($data['user_id'])) {
            $uid = $data['user_id'];
            $fid = $this->merchantsStepsFieldsModel->getMerchantsStepsFields(['user_id' => $uid], ['fid']);
            if ($fid) {
                return ['code' => 5, 'msg' => '已经申请过'];
            } else {

                //添加店铺等级
                if ($data['merchants_audit'] == 1) {
                    //店铺等级
                    if (empty($data['grade_id'])) {
                        $sg = $this->sellerGradeModel->getSellerGrade(['is_default' => 1]);
                        $grade_id = $sg->id;
                    } else {
                        $grade_id = $data['grade_id'];
                    }
                    $mgData = [
                        'year_num' => $data['year_num'],
                        'grade_id' => $grade_id
                    ];
                    $mg = $this->merchantsGradeModel->setMerchantsGrade(['ru_id' => $uid], $mgData);
                    if (!$mg) {
                        $mgData['ru_id'] = $uid;
                        $mgData['add_time'] = time();
                        $this->merchantsGradeModel->addMerchantsGrade($mgData);
                    }
                }

                $msfData = [];
                $msp = $this->merchantsStepsProcessModel->getMerchantsStepsProcessesByTitleAndContent();
                $formKey = [];
                foreach ($msp as $step) {
                    foreach ($step->mst as $mst) {
                        $formKey = array_merge($formKey, explode(',', $mst->textFields));
                    }
                }
                $formKey = array_filter($formKey);
                foreach ($data as $key => $value) {
                    if (in_array($key, $formKey)) {
                        if (strpos($key, '_fileImg') !== false) {//处理上传的图片
                            $path = 'merchants_fields' . DIRECTORY_SEPARATOR . strtolower($key);
                            $uri = FileHandle::upLoadImage($value, $path);
                            $msfData[$key] = $uri;
                        } else {
                            if ($key == 'license_comp_adress' || $key == 'company_located' || $key == 'linked_bank_address') {
                                $msfData[$key] = implode(',', $value);
                            } else {
                                $msfData[$key] = $value;
                            }
                        }
                    }
                }
                $msfData['user_id'] = $uid;
                $msfData['agreement'] = 1;
                $this->merchantsStepsFieldsModel->addMerchantsStepsFields($msfData);

                //商家入驻后设置店铺基本信息
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


                //商家入驻时基本信息
                $shop_expireDate = explode('～', $data['shop_expireDate']);
                $authorizeFile = '';
                $shop_hypermarketFile = '';
                if ($data['subShoprz_type'] == 2) {
                    $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                    $authorizeFile = FileHandle::upLoadImage($data['authorizeFile'], $path);
                } elseif ($data['subShoprz_type'] == 3) {
                    $path = 'merchants_shop_info' . DIRECTORY_SEPARATOR . $uid;
                    $shop_hypermarketFile = FileHandle::upLoadImage($data['shop_hypermarketFile'], $path);
                }
                $msiData = [
                    'shoprz_type' => $data['shoprz_type'],
                    'subShoprz_type' => $data['subShoprz_type'],
                    'shop_expireDateStart' => $shop_expireDate[0],
                    'shop_expireDateEnd' => $shop_expireDate[1],
                    'shop_permanent' => $data['shop_permanent'],
                    'authorizeFile' => $authorizeFile,
                    'shop_hypermarketFile' => $shop_hypermarketFile,
                    'shop_categoryMain' => $data['shop_categoryMain'],
                    'merchants_audit' => $data['merchants_audit'],
                    'review_goods' => $data['review_goods'],
                    'self_run' => $data['self_run'],
                    'shoprz_brandName' => $data['shoprz_brandName'],
                    'shop_class_keyWords' => $data['shop_class_keyWords'],
                    'shopNameSuffix' => $data['shopNameSuffix'],
                    'rz_shopName' => $data['rz_shopName'],
                    'hopeLoginName' => $data['hopeLoginName'],
                    'steps_audit' => 1,
                ];
                $msi = $this->merchantsShopInformationModel->setMerchantsShopInfo(['user_id' => $uid], $msiData);
                if (!$msi) {
                    $msiData['user_id'] = $uid;
                    $this->merchantsShopInformationModel->addMerchantsShopInfo($msiData);
                }

                //店铺品牌
                $msbData['user_id'] = $uid;
                if (!empty($data['bid'])) {
                    $this->merchantsShopBrandModel->setMerchantsShopBrand([], $msbData, $data['bid']);
                }

                //商家入驻流程分类资质表
                if (!empty($data['dtf'])) {
                    foreach ($data['dtf'] as $key => $dtf_id) {
                        $dtfwhere['dtf_id'] = $dtf_id;
                        $path = 'merchants_dtf' . DIRECTORY_SEPARATOR . $uid;
                        $permanent_file = FileHandle::upLoadImage($data['permanent_file'][$key], $path);
                        $dtfData['permanent_file'] = $permanent_file;
                        $dtfData['user_id'] = $uid;
                        if (!empty($data['cate_title_permanent'][$key]) && $data['cate_title_permanent'][$key] == 1) {
                            $dtfData['cate_title_permanent'] = $data['cate_title_permanent'][$key];
                        } else {
                            $dtfData['permanent_date'] = $data['permanent_date'][$key];
                        }
                        $this->merchantsDtFileModel->setMerchantsDtFile($dtfwhere, $dtfData);
                    }
                }

                //商家入驻流程填写分类临时信息表
                $cat_id = !empty($data['cat_id']) ? $data['cat_id'] : (!empty($data['subcate']) ? $data['subcate'] : []);
                if (!empty($cat_id)) {
                    foreach ($cat_id as $key => $catid) {
                        $mctwhere['cat_id'] = $catid;
                        $mctData['user_id'] = $uid;
                        $mctData['is_add'] = 1;
                        $this->merchantsCategoryTemporarydateModel->setMerchantsCategoryTemporarydate($mctwhere, $mctData);
                    }
                }

                return ['code' => 1, 'msg' => '操作成功'];
            }
        }
        return ['code' => 5, 'msg' => '操作失败'];
    }

    public function change($data)
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