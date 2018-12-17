<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class OrderGoodsModel extends Model
{
    protected $table = 'order_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Goods()
    {
        return $this->hasOne('App\Http\Models\App\GoodsModel', 'goods_id', 'goods_id');
    }

    public function addOrderGoods($data)
    {
        return $this->create($data);
    }

    public function getOrderGoodsByOrder($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('order_info', 'order_info.order_id', '=', 'order_goods.order_id')
            ->where($where)
            ->get();
    }
}
