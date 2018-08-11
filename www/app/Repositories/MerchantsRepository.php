<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\MerchantsRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\MerchantsCategoryModel;
use App\Http\Models\Shop\MerchantsCategoryTemporarydateModel;
use App\Http\Models\Shop\MerchantsDocumentTitleModel;
use App\Http\Models\Shop\MerchantsDtFileModel;
use App\Http\Models\Shop\MerchantsPrivilegeModel;
use App\Http\Models\Shop\MerchantsShopBrandFileModel;
use App\Http\Models\Shop\MerchantsShopBrandModel;
use App\Http\Models\Shop\MerchantsStepsFieldsCententModel;
use App\Http\Models\Shop\MerchantsStepsProcessModel;
use App\Http\Models\Shop\MerchantsStepsTitleModel;
use App\Http\Models\Shop\SellerGradeModel;

class MerchantsRepository implements MerchantsRepositoryInterface
{
    private $merchantsStepsProcessModel;
    private $merchantsStepsTitleModel;
    private $merchantsStepsFieldsCententModel;
    private $sellerGradeModel;
    private $merchantsPrivilegeModel;
    private $merchantsShopBrandModel;
    private $merchantsShopBrandFileModel;
    private $merchantsCategoryTemporarydateModel;
    private $merchantsDocumentTitleModel;
    private $merchantsDtFileModel;
    private $categoryModel;

    public function __construct(
        MerchantsStepsProcessModel $merchantsStepsProcessModel,
        MerchantsStepsTitleModel $merchantsStepsTitleModel,
        MerchantsStepsFieldsCententModel $merchantsStepsFieldsCententModel,
        SellerGradeModel $sellerGradeModel,
        MerchantsPrivilegeModel $merchantsPrivilegeModel,
        MerchantsShopBrandModel $merchantsShopBrandModel,
        MerchantsShopBrandFileModel $merchantsShopBrandFileModel,
        MerchantsCategoryTemporarydateModel $merchantsCategoryTemporarydateModel,
        MerchantsDocumentTitleModel $merchantsDocumentTitleModel,
        MerchantsDtFileModel $merchantsDtFileModel,
        CategoryModel $categoryModel
    )
    {
        $this->merchantsStepsProcessModel = $merchantsStepsProcessModel;
        $this->merchantsStepsTitleModel = $merchantsStepsTitleModel;
        $this->merchantsStepsFieldsCententModel = $merchantsStepsFieldsCententModel;
        $this->sellerGradeModel = $sellerGradeModel;
        $this->merchantsPrivilegeModel = $merchantsPrivilegeModel;
        $this->merchantsShopBrandModel = $merchantsShopBrandModel;
        $this->merchantsCategoryTemporarydateModel = $merchantsCategoryTemporarydateModel;
        $this->merchantsDocumentTitleModel = $merchantsDocumentTitleModel;
        $this->merchantsDtFileModel = $merchantsDtFileModel;
        $this->categoryModel = $categoryModel;
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
        switch ($data['type']) {
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
        $rep = $this->merchantsStepsTitleModel->getMerchantsStepsTitle($where);
        $rep->msfc->textFields = explode(',', $rep->msfc->textFields);
        $rep->msfc->fieldsDateType = explode(',', $rep->msfc->fieldsDateType);
        $rep->msfc->fieldsLength = explode(',', $rep->msfc->fieldsLength);
        $rep->msfc->fieldsNotnull = explode(',', $rep->msfc->fieldsNotnull);
        $rep->msfc->fieldsFormName = explode(',', $rep->msfc->fieldsFormName);
        $rep->msfc->fieldsCoding = explode(',', $rep->msfc->fieldsCoding);
        $rep->msfc->fields_sort = explode(',', $rep->msfc->fields_sort);
        $rep->msfc->will_choose = explode(',', $rep->msfc->will_choose);
        $fieldsForm = explode('|', $rep->msfc->fieldsForm);
        $merchants_form = [];
        $formName_special = [];
        $merchants_formSize = [];
        $merchants_rows = [];
        $merchants_cols = [];
        $radio_checkbox = [];
        $select = [];
        $merchants_formOther = [];
        $merchants_formOtherSize = [];
        foreach ($fieldsForm as $key => $value) {
            if (!empty($value)) {
                $fieldsArr = explode(':', $value);
                $merchants_form[$key] = $fieldsArr[0];
                $fieldsSub = explode('+', $fieldsArr[1]);
                $formName_special[$key] = $fieldsSub[1];
                if ($fieldsArr[0] == 'input') {
                    $merchants_formSize[$key] = $fieldsSub[0];
                } elseif ($fieldsArr[0] == 'textarea') {
                    $arr = explode(',', $fieldsSub[0]);
                    $merchants_rows[$key] = $arr[0];
                    $merchants_cols[$key] = $arr[1];
                } elseif ($fieldsArr[0] == 'radio' || $fieldsArr[0] == 'checkbox') {
                    $arr = explode(',', $fieldsSub[0]);
                    $radio_checkbox[$key] = $arr;
                } elseif ($fieldsArr[0] == 'select') {
                    $arr = explode(',', $fieldsSub[0]);
                    $select[$key] = $arr;
                } elseif ($fieldsArr[0] == 'other') {
                    $arr = explode(',', $fieldsSub[0]);
                    $merchants_formOther[$key] = $arr[0];
                    if ($arr[0] == 'dateTime') {
                        $merchants_formOtherSize[$key] = $arr[1];
                    }
                }
            }
        }
        $rep->msfc->merchants_form = $merchants_form;
        $rep->msfc->formName_special = $formName_special;
        $rep->msfc->merchants_formSize = $merchants_formSize;
        $rep->msfc->merchants_rows = $merchants_rows;
        $rep->msfc->merchants_cols = $merchants_cols;
        $rep->msfc->radio_checkbox = $radio_checkbox;
        $rep->msfc->select = $select;
        $rep->msfc->merchants_formOther = $merchants_formOther;
        $rep->msfc->merchants_formOtherSize = $merchants_formOtherSize;
        return $rep;
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
        $this->merchantsStepsFieldsCententModel->delMerchantsStepsFieldsCentent($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

    public function getMerchantsStepsFieldsCentent($id)
    {
        $where['id'] = $id;
        return $this->merchantsStepsFieldsCententModel->getMerchantsStepsFieldsCentent($where);
    }

    public function setMerchantsStepsFieldsCentent($data, $id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $twhere['tid'] = $data['tid'];
        $cwhere['id'] = $id;
        $tupdata = [];
        $cupdata = [];
        foreach ($data as $key => $value) {
            if (is_array($value)) {
                switch ($key) {
                    case 'merchants_date':
                        $cupdata['textFields'] = implode(',', $value);
                        $notNull = [];
                        $coding = [];
                        foreach ($value as $val) {
                            $notNull[] = 'NOT NULL';
                            $coding[] = 'UTF-8';
                        }
                        $cupdata['fieldsNotnull'] = implode(',', $notNull);
                        $cupdata['fieldsCoding'] = implode(',', $coding);
                        break;
                    case 'merchants_dateType':
                        $cupdata['fieldsDateType'] = implode(',', $value);
                        break;
                    case 'merchants_formName':
                        $cupdata['fieldsFormName'] = implode(',', $value);
                        break;
                    case 'merchants_length':
                        $cupdata['fieldsLength'] = implode(',', $value);
                        break;
                    case 'fields_sort':
                        $cupdata['fields_sort'] = implode(',', $value);
                        break;
                    case 'will_choose':
                        $cupdata['will_choose'] = implode(',', $value);
                        break;
                    case 'merchants_form':
                        $fieldsForm = '';
                        foreach ($value as $k => $v) {
                            switch ($v) {
                                case 'input':
                                    $fieldsForm .= $v . ':' . $data['merchants_formSize'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'textarea':
                                    $fieldsForm .= $v . ':' . $data['merchants_rows'][$k] . ',' . $data['merchants_cols'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'radio':
                                case 'checkbox':
                                    $fieldsForm .= $v . ':' . implode(',', $data['radio_checkbox'][$k]) . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'select':
                                    $fieldsForm .= $v . ':' . implode(',', $data['select'][$k]) . '+' . $data['formName_special'][$k] . '|';
                                    break;
                                case 'other':
                                    if ($data['merchants_formOther'][$k] == 'textArea' || $data['merchants_formOther'][$k] == 'dateFile') {
                                        $fieldsForm .= $v . ':' . $data['merchants_formOther'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    } elseif ($data['merchants_formOther'][$k] == 'dateTime') {
                                        $fieldsForm .= $v . ':' . $data['merchants_formOther'][$k] . ',' . $data['merchants_formOtherSize'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    }
                                    break;
                            }
                        }
                        $fieldsForm = substr($fieldsForm, 0, -1);
                        $cupdata['fieldsForm'] = $fieldsForm;
                        break;
                }
            } else {
                if ($key == 'fields_steps') {
                    $tupdata['fields_steps'] = intval($value);
                } else {
                    $tupdata[$key] = trim($value);
                }
            }
        }
        unset($tupdata['tid']);
        $re = $this->merchantsStepsTitleModel->setMerchantsStepsTitle($twhere, $tupdata);
        $res = $this->merchantsStepsFieldsCententModel->setMerchantsStepsFieldsCentent($cwhere, $cupdata);
        if ($re && $res) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function addMerchantsStepsFieldsCentent($data)
    {
        $tupdata = [];
        $cupdata = [];
        foreach ($data as $key => $value) {
            if (is_array($value)) {
                switch ($key) {
                    case 'merchants_date':
                        $cupdata['textFields'] = implode(',', $value);
                        $notNull = [];
                        $coding = [];
                        foreach ($value as $val) {
                            $notNull[] = 'NOT NULL';
                            $coding[] = 'UTF-8';
                        }
                        $cupdata['fieldsNotnull'] = implode(',', $notNull);
                        $cupdata['fieldsCoding'] = implode(',', $coding);
                        break;
                    case 'merchants_dateType':
                        $cupdata['fieldsDateType'] = implode(',', $value);
                        break;
                    case 'merchants_formName':
                        $cupdata['fieldsFormName'] = implode(',', $value);
                        break;
                    case 'merchants_length':
                        $cupdata['fieldsLength'] = implode(',', $value);
                        break;
                    case 'fields_sort':
                        $cupdata['fields_sort'] = implode(',', $value);
                        break;
                    case 'will_choose':
                        $cupdata['will_choose'] = implode(',', $value);
                        break;
                    case 'merchants_form':
                        $fieldsForm = '';
                        foreach ($value as $k => $v) {
                            switch ($v) {
                                case 'input':
                                    $fieldsForm .= $v . ':' . $data['merchants_formSize'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'textarea':
                                    $fieldsForm .= $v . ':' . $data['merchants_rows'][$k] . ',' . $data['merchants_cols'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'radio':
                                case 'checkbox':
                                    $fieldsForm .= $v . ':' . implode(',', $data['radio_checkbox'][$k]) . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'select':
                                    $fieldsForm .= $v . ':' . implode(',', $data['select'][$k]) . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    break;
                                case 'other':
                                    if ($data['merchants_formOther'][$k] == 'textArea' || $data['merchants_formOther'][$k] == 'file') {
                                        $fieldsForm .= $v . ':' . $data['merchants_formOther'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    } elseif ($data['merchants_formOther'][$k] == 'dateTime') {
                                        $fieldsForm .= $v . ':' . $data['merchants_formOther'][$k] . ',' . $data['merchants_formOtherSize'][$k] . '+' . ($data['formName_special'][$k] ? $data['formName_special'][$k] : ' ') . '|';
                                    }
                                    break;
                            }
                        }
                        $fieldsForm = substr($fieldsForm, 0, -1);
                        $cupdata['fieldsForm'] = $fieldsForm;
                        break;
                }
            } else {
                if ($key == 'fields_steps') {

                    $tupdata['fields_steps'] = intval($value);
                } else {
                    $tupdata[$key] = trim($value);
                }
            }
        }
        $re = $this->merchantsStepsTitleModel->addMerchantsStepsTitle($tupdata);
        if ($re) {
            $cupdata['tid'] = $re->tid;
            return $this->merchantsStepsFieldsCententModel->addMerchantsStepsFieldsCentent($cupdata);
        }
    }

    public function getSellerGradesByPri()
    {
        $rep = $this->sellerGradeModel->getSellerGradesByPri();
        foreach ($rep as $val) {
            $val->mpri->action_list = explode(',', $val->mpri->action_list);
        }
        return $rep;
    }

    public function getSellerGradeByPri($data)
    {
        $where['id'] = $data['id'];
        $rep = $this->sellerGradeModel->getSellerGradeByPri($where);
        $rep->mpri->action_list = explode(',', $rep->mpri->action_list);
        return $rep->mpri->action_list;
    }

    public function setSellerGrade($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['grade_id'] = $data['grade'];
        $code = [];
        foreach ($data as $key => $val) {
            if ($key == 'grade') {
                unset($data[$key]);
            } elseif ($key == 'initialize_allot') {
                unset($data[$key]);
            } else {
                if (!empty($val['code'])) {
                    $code[] = $val['code'];
                }
            }
        }

        $updata['action_list'] = implode(',', $code);
        $re = $this->merchantsPrivilegeModel->setMerchantsPrivilege($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function getMerchantsBrands($id)
    {
        $res = $this->merchantsShopBrandModel->getMerchantsShopBrands(['user_id' => $id]);
        $brand_type = ['请选择...', '国内品牌', '国外品牌'];
        $brand_operate_type = ['请选择...', '自有品牌', '代理品牌'];
        foreach ($res as $val) {
            $val->brand_type = $brand_type[$val->brand_type];
            $val->brand_operate_type = $brand_operate_type[$val->brand_operate_type];
        }
        return $res;
    }

    public function getMerchantsBrand($id)
    {
        return $this->merchantsShopBrandModel->getMerchantsShopBrand(['bid' => $id]);
    }

    public function setMerchantsBrand($data)
    {
    }

    public function addMerchantsBrand($data)
    {
        $updata = [];
        $bfupdata = [];
        $brand_file = ['qualification_name_input', 'qualification_img', 'expired_date', 'expired_date_permanent'];
        foreach ($data as $key => $value) {
            if ($key == 'brand_logo') {
                $path = 'merchants_brand_logo';
                $uri = FileHandle::upLoadImage($value, $path);
                $updata[$key] = $uri;
            } elseif (in_array($key, $brand_file)) {
                if ($key == 'qualification_img') {
                    $path = 'merchants_qualification_img';
                    $uri = FileHandle::upLoadImage($value, $path);
                    $bfupdata[$key] = $uri;
                } else {
                    $bfupdata[$key] = !empty($value) ? $value : 0;
                }
            } else {
                $updata[$key] = !empty($value) ? $value : 0;
            }
        }
        $re = $this->merchantsShopBrandModel->addMerchantsShopBrand($updata);
        if ($re) {
            $brand_type = ['请选择...', '国内品牌', '国外品牌'];
            $brand_operate_type = ['请选择...', '自有品牌', '代理品牌'];
            $bfupdata['bid'] = $re->bid;
            $this->merchantsShopBrandFileModel->addMerchantsShopBrandFile($bfupdata);
            $res = $this->merchantsShopBrandModel->getMerchantsShopBrands(['user_id' => $updata['user_id']]);
            foreach ($res as $val) {
                $val->brand_type = $brand_type[$val->brand_type];
                $val->brand_operate_type = $brand_operate_type[$val->brand_operate_type];
            }
            return ['code' => 1, 'msg' => '操作成功', 'data' => $res];
        }
        return ['code' => 5, 'msg' => '操作失败', 'data' => ''];
    }

    public function delMerchantsBrand($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $re = $this->merchantsShopBrandModel->getMerchantsShopBrand(['bid' => $id]);
        $ref = $this->merchantsShopBrandFileModel->getMerchantsShopBrandFile(['bid' => $id]);
        if ($re) {
            if ($ref) {
                FileHandle::deleteFile($ref->qualification_img);
                $this->merchantsShopBrandFileModel->delMerchantsShopBrandFile(['bid' => $id]);
            }
            FileHandle::deleteFile($re->brand_logo);
            $re = $this->merchantsShopBrandModel->delMerchantsBrand(['bid' => $id]);
            if ($re) {
                $rep = ['code' => 1, 'msg' => '操作成功'];
            }
        }
        return $rep;
    }

    public function getMerchantsCateTemporarydates($id)
    {
        $where['user_id'] = $id;
        $mcts = $this->merchantsCategoryTemporarydateModel->getMerchantsCategoryTemporarydates($where);
        $rep['mcts'] = $mcts;
        $cat_ids = [];
        foreach ($mcts as $mct) {
            $cat_ids[] = $mct->cat_id;
        }
        $rep['dt'] = $this->merchantsDocumentTitleModel->getMerchantsDocumentTitles($cat_ids, $id);
        return $rep;
    }

    public function addMerchantsCateTemporarydate($data)
    {
        $updata['user_id'] = empty($data['user_id']) ? 0 : $data['user_id'];
        $cates = $this->categoryModel->getComCatesByIn($data['cat_ids']);
        $pCate = $this->categoryModel->getComParentCate($cates[0]->parent_id);
        $updata['is_add'] = 0;

        $res = [];
        $mcts = $this->merchantsCategoryTemporarydateModel->getMerchantsCategoryTemporarydates(['user_id' => $updata['user_id']]);
        $cateIds = [];
        foreach ($mcts as $mct) {
            $cateIds[$mct->cat_id] = $mct->cat_id;
            $res['cate'][] = $mct;
        }

        foreach ($cates as $cate) {
            $updata['cat_id'] = $cate->id;
            $updata['parent_id'] = $cate->parent_id;
            $updata['cat_name'] = $cate->cat_name;
            $updata['parent_name'] = $pCate[0]->cat_name;

            if (empty($cateIds[$cate->id]) || $cateIds[$cate->id] != $cate->id) {
                $res['cate'][] = $this->merchantsCategoryTemporarydateModel->addMerchantsCategoryTemporarydate($updata);
            }
        }
        if ($res) {
            $res['dt'] = $this->merchantsDocumentTitleModel->getMerchantsDocumentTitles($data['cat_ids'], $updata['user_id']);
            $mdfData['user_id'] = $updata['user_id'];
            foreach ($res['dt'] as $dt){
                if(empty($dt->mdf)){
                    $mdfData['dt_id'] = $dt->dt_id;
                    $mdfData['cat_id'] = $dt->cat_id;
                    $mdfData['cate_title_permanent'] = 0;
                    $this->merchantsDtFileModel->addMerchantsDtFile($mdfData);
                }
            }
            $res['dt'] = $this->merchantsDocumentTitleModel->getMerchantsDocumentTitles($data['cat_ids'], $updata['user_id']);
            return ['code' => 1, 'msg' => '操作成功', 'data' => $res];
        }
        return ['code' => 5, 'msg' => '操作失败', 'data' => ''];
    }

    public function delMerchantsCateTemporarydate($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['ct_id'] = $id;
        $mct = $this->merchantsCategoryTemporarydateModel->getMerchantsCategoryTemporarydate($where);
        $dfwhere['user_id'] = $mct->user_id;
        $dfwhere['cat_id'] = $mct->cat_id;
        $this->merchantsDtFileModel->delMerchantsDtFile($dfwhere);
        $re = $this->merchantsCategoryTemporarydateModel->delMerchantsCategoryTemporarydate($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }
}