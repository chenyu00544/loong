<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AttributeRepositoryInterface;
use App\Http\Models\Shop\AttributeModel;

class AttributeRepository implements AttributeRepositoryInterface
{

    private $attributeModel;

    public function __construct(AttributeModel $attributeModel)
    {
        $this->attributeModel = $attributeModel;
    }

    public function getAttributePage($id = 0)
    {
        $re = $this->attributeModel->getAttributePage($id);
        for ($i = 0; $i < count($re); $i++) {
            $re[$i]->attr_values = str_replace("\r\n", ',', $re[$i]->attr_values);
        }
        return $re;
    }

    public function getAttributes($id)
    {
        $where['cat_id'] = $id;
        $re = $this->attributeModel->getAttrs($where);
        for ($i = 0; $i < count($re); $i++) {
            if($re[$i]->attr_values != ''){
                $re[$i]->attr_values = explode(',', str_replace("\r\n", ',', $re[$i]->attr_values));
            }
        }
        return $re;
    }

    public function getAttribute($id)
    {
        return $this->attributeModel->getAttr($id);
    }

    public function addAttribute($data)
    {
        return $this->attributeModel->addAttribute($data);
    }

    public function setAttribute($data, $id)
    {
        return $this->attributeModel->setAttr($data, $id);
    }

    public function delAttribute($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['attr_id'] = $id;
        $re = $this->attributeModel->delAttribute($where);
        if ($re) {
            $req = ['code' => 1, 'msg' => '删除成功'];
        }
        return $req;
    }

}