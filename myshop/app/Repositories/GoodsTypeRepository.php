<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsTypeRepositoryInterface;
use App\Http\Models\Shop\GoodsTypeCateModel;
use App\Http\Models\Shop\GoodsTypeModel;

class GoodsTypeRepository implements GoodsTypeRepositoryInterface
{

    private $goodsTypeModel;
    private $goodsTypeCateModel;

    public function __construct(
        GoodsTypeModel $goodsTypeModel,
        GoodsTypeCateModel $goodsTypeCateModel
    )
    {
        $this->goodsTypeModel = $goodsTypeModel;
        $this->goodsTypeCateModel = $goodsTypeCateModel;
    }

    public function getGoodsTypesPage($size = 10)
    {
        return $this->goodsTypeModel->getGoodsTypesPage($size);
    }

    public function getGoodsType($id)
    {
        return  $this->goodsTypeModel->getGoodsType($id);
    }

    public function addtGoodsType($data)
    {
        return $this->goodsTypeModel->addtGoodsType($data);
    }

    public function setGoodsType($updata, $id)
    {
        return $this->goodsTypeModel->setGoodsType($updata, $id);
    }


    public function getTypeCates($id)
    {
        $where['parent_id'] = $id;
        return $this->goodsTypeCateModel->getTypeCates($where);
    }

    public function getParentCate($id, $parentCates = [])
    {
        $PCates = $parentCates;
        $re = $this->goodsTypeCateModel->getTypeCate($id);
        if($re){
            $PCates[] = $re;
        }
        if($re && $re->parent_id != 0){
            return $this->getParentCate($re->parent_id, $PCates);
        }else{
            krsort($PCates);
            return $PCates;
        }
    }

}