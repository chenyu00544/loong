<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\GoodsRepositoryInterface;
use App\Http\Models\App\GoodsModel;

class GoodsRepository implements GoodsRepositoryInterface
{
    private $goodsModel;

    public function __construct(
        GoodsModel $goodsModel
    )
    {
        $this->goodsModel = $goodsModel;
    }

    public function getBestGoods($page = 1)
    {
        return $this->goodsModel->getGoodses(['is_delete' => 0, 'is_best' => 1], $page);
    }

}