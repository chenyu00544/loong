<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class OrderGoodsModel extends Model
{
    protected $table = 'order_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderGoodses($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('goods','goods.goods_id','=','order_goods.goods_id')
            ->where($where)
            ->get();
    }

    public function delOrderGoods($where)
    {
        return $this->where($where)->delete();
    }
}
