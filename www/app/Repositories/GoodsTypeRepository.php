<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsTypeRepositoryInterface;
use App\Http\Models\Shop\AttributeModel;
use App\Http\Models\Shop\GoodsTypeCateModel;
use App\Http\Models\Shop\GoodsTypeModel;

class GoodsTypeRepository implements GoodsTypeRepositoryInterface
{

    private $goodsTypeModel;
    private $goodsTypeCateModel;
    private $attributeModel;

    public function __construct(
        GoodsTypeModel $goodsTypeModel,
        GoodsTypeCateModel $goodsTypeCateModel,
        AttributeModel $attributeModel
    )
    {
        $this->goodsTypeModel = $goodsTypeModel;
        $this->goodsTypeCateModel = $goodsTypeCateModel;
        $this->attributeModel = $attributeModel;
    }

    public function getGoodsTypesPage($size = 10)
    {
        return $this->goodsTypeModel->getGoodsTypesPage($size);
    }

    public function getGoodsTypes($where)
    {
        return $this->goodsTypeModel->getGoodsTypes($where);
    }

    public function getGoodsTypesBySelect($where, $id)
    {
        $res = $this->goodsTypeModel->getGoodsTypes($where);
        foreach ($res as $re) {
            if ($re->cat_id == $id) {
                $re->select = 1;
            } else {
                $re->select = 0;
            }
        }
        return $res;
    }

    public function getGoodsType($where)
    {
        return $this->goodsTypeModel->getGoodsType($where);
    }

    public function getGoodsTypeAll($where = [])
    {
        $re = $this->goodsTypeModel->getGoodsTypes($where)->toArray();
        $req = [];
        foreach ($re as $val) {
            $req[$val['cat_id']] = $val['cat_name'];
        }
        return $req;
    }

    public function addGoodsType($data)
    {
        return $this->goodsTypeModel->addGoodsType($data);
    }

    public function setGoodsType($updata, $id)
    {
        return $this->goodsTypeModel->setGoodsType($updata, $id);
    }


    public function getTypeCates($id = 0)
    {
        $where['parent_id'] = $id;
        return $this->goodsTypeCateModel->getTypeCates($where);
    }

    public function getTypeCatesAll()
    {
        $re = $this->goodsTypeCateModel->getTypeCates()->toArray();
        $req = [];
        foreach ($re as $val) {
            $req[$val['cate_id']] = $val['cat_name'];
        }
        $req[0] = '无';
        return $req;
    }

    public function getTypeCate($id)
    {
        return $this->goodsTypeCateModel->getTypeCate($id);
    }

    public function setTypeCate($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['cate_id'] = $data['id'];

        switch ($data['type']) {
            case 'order':
                $updata['sort_order'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->goodsTypeCateModel->setTypeCate($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

    public function upDateTypeCate($data, $id)
    {
        $where['cate_id'] = $id;
        return $this->goodsTypeCateModel->setTypeCate($where, $data);
    }

    public function addTypeCate($data)
    {
        return $this->goodsTypeCateModel->addTypeCate($data);
    }

    public function getParentCate($id, $parentCates = [])
    {
        $PCates = $parentCates;
        $re = $this->goodsTypeCateModel->getTypeCate($id);
        if ($re) {
            $PCates[] = $re;
        }
        if ($re && $re->parent_id != 0) {
            return $this->getParentCate($re->parent_id, $PCates);
        } else {
            krsort($PCates);
            return $PCates;
        }
    }

    public function getGoodsTypeCateBySelect($id)
    {
        $c_list = $this->getParentCate($id);
        $cate = [];
        foreach ($c_list as $value) {
            $re = $this->goodsTypeCateModel->getTypeCates(['parent_id' => $value->parent_id]);
            foreach ($re as $k => $val) {
                if ($val->cate_id == $value->cate_id) {
                    $re[$k]->select = 1;
                } else {
                    $re[$k]->select = 0;
                }
            }
            $cate[] = $re;
        }
        return $cate;
    }

    public function delete($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['cat_id'] = $id;
        $attrWhere['cat_id'] = $id;
        $re = $this->goodsTypeModel->deleteType($where);
        $this->attributeModel->delAttribute($attrWhere);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    public function deleteCate($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['cate_id'] = $id;
        $re = $this->goodsTypeCateModel->deleteCate($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

}