<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsAttrRepositoryInterface;
use App\Http\Models\Shop\GoodsAttrModel;

class GoodsAttrRepository implements GoodsAttrRepositoryInterface
{

    private $goodsAttrModel;

    public function __construct(
        GoodsAttrModel $goodsAttrModel
    )
    {
        $this->goodsAttrModel = $goodsAttrModel;
    }

    public function addGoodsAttr($data)
    {
        $updata['attr_id'] = $data['attr_id'];
        $updata['goods_id'] = $data['goods_id'];
        $attr_values = $data['attr_values'];
        $req = [];
        foreach ($attr_values as $val) {
            $updata['attr_value'] = $val;
            $re = $this->goodsAttrModel->addGoodsAttr($updata);
            $req[] = $re;
        }
        return $req;
    }


}