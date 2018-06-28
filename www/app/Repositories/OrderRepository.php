<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\OrderRepositoryInterface;
use App\Http\Models\Shop\OrderGoodsModel;

class OrderRepository implements OrderRepositoryInterface
{
    private $orderGoodsModel;

    public function __construct(
        OrderGoodsModel $orderGoodsModel
    )
    {
        $this->orderGoodsModel = $orderGoodsModel;
    }
}