<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsTypeRepositoryInterface;
use App\Http\Models\Shop\GoodsTypeModel;

class GoodsTypeRepository implements GoodsTypeRepositoryInterface
{

    private $goodsTypeModel;

    public function __construct(GoodsTypeModel $goodsTypeModel)
    {
        $this->goodsTypeModel = $goodsTypeModel;
    }

    public function getGoodsTypesPage($size = 10)
    {
        return $this->goodsTypeModel->getGoodsTypesPage($size);
    }

}