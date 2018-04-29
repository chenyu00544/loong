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

}