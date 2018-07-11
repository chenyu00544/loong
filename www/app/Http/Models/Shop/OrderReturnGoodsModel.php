<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class OrderReturnGoodsModel extends Model
{
    protected $table = 'order_return_goods';
    protected $primaryKey = 'rg_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderReturnGoodses($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('goods','goods.goods_id','=','order_return_goods.goods_id')
            ->where($where)
            ->get();
    }
}
