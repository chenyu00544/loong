<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SellerGradeRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\EntryCriteriaModel;
use App\Http\Models\Shop\SellerGradeModel;

class SellerGradeRepository implements SellerGradeRepositoryInterface
{

    private $sellerGradeModel;
    private $entryCriteriaModel;

    public function __construct(
        SellerGradeModel $sellerGradeModel,
        EntryCriteriaModel $entryCriteriaModel
    )
    {
        $this->sellerGradeModel = $sellerGradeModel;
        $this->entryCriteriaModel = $entryCriteriaModel;
    }

    public function getSellerGradesByPage()
    {
        $rep = $this->sellerGradeModel->getSellerGradesByPage();
        $ec = $this->getEntryCriterias([]);
        foreach ($ec as $key => $value) {
            $ec[$value->id] = $value;
        }
        foreach ($rep as $val) {
            $val->entry_criteria = unserialize($val->entry_criteria);
            $e_c = [];
            foreach ($val->entry_criteria as $v) {
                $e_c[] = $ec[$v]->criteria_name;
            }
            $val->entry_criteria = implode(',', $e_c);
        }
        return $rep;
    }

    public function getSellerGrades()
    {
        return $this->sellerGradeModel->getSellerGrades();
    }

    public function getSellerGrade($id)
    {
        $where['id'] = $id;
        $rep = $this->sellerGradeModel->getSellerGrade($where);
        $rep->entry_criteria = unserialize($rep->entry_criteria);
        return $rep;
    }

    public function setSellerGrade($data, $id)
    {
        $where['id'] = $id;
        $updata = [];
        foreach ($data as $key => $value) {
            if ($key == 'file_url') {
                if (empty($data['file'])) {
                    $updata['grade_img'] = $value;
                }
            } elseif ($key == 'file') {
                $path = 'seller_grade';
                $uri = FileHandle::upLoadImage($value, $path);
                FileHandle::deleteFile($data['file_url']);
                $updata['grade_img'] = $uri;
            } elseif ($key == 'criteria_name') {
                $cn = [];
                foreach ($value as $val) {
                    $cn[$val] = $val;
                }
                $updata['entry_criteria'] = serialize($cn);
            } elseif ($key == 'is_default') {
                if ($value == 1) {
                    $this->sellerGradeModel->setSellerGrade([], ['is_default' => 0]);
                }
                $updata['is_default'] = $value;
            } else {
                $updata[$key] = $value;
            }
        }
        return $this->sellerGradeModel->setSellerGrade($where, $updata);
    }

    public function addSellerGrade($data)
    {
        $updata = [];
        foreach ($data as $key => $value) {
            if ($key == 'file_url') {
                if (empty($data['file'])) {
                    $updata['grade_img'] = $value;
                }
            } elseif ($key == 'file') {
                $path = 'seller_grade';
                $uri = FileHandle::upLoadImage($value, $path);
                $updata['grade_img'] = $uri;
            } elseif ($key == 'criteria_name') {
                $cn = [];
                foreach ($value as $val) {
                    $cn[$val] = $val;
                }
                $updata['entry_criteria'] = serialize($cn);
            } elseif ($key == 'is_default') {
                if ($value == 1) {
                    $this->sellerGradeModel->setSellerGrade([], ['is_default' => 0]);
                }
                $updata['is_default'] = $value;
            } else {
                $updata[$key] = $value;
            }
        }
        return $this->sellerGradeModel->addSellerGrade($updata);
    }

    public function delSellerGrade($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $img = $this->sellerGradeModel->getSellerGrade($where, ['grade_img']);
        $re = $this->sellerGradeModel->delSellerGrade($where);
        if ($re) {
            FileHandle::deleteFile($img->grade_img);
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'goods_sun':
                $updata['goods_sun'] = $data['val'];
                break;
            case 'seller_temp':
                $updata['seller_temp'] = $data['val'];
                break;
            case 'is_open':
                $updata['is_open'] = $data['val'];
                break;
        }
        $re = $this->sellerGradeModel->setSellerGrade($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function getEntryCriterias($where)
    {
        $rep = $this->entryCriteriaModel->getEntryCriterias($where);
        return $rep;
    }
}