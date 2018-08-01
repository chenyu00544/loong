<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class DeliveryGoodsModel extends Model
{
    protected $table = 'delivery_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getDeliveryGoodses($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('goods','goods.goods_id','=','delivery_goods.goods_id')
            ->where($where)
            ->get();
    }

    public function delDeliveryGoods($where)
    {
        return $this->where($where)->delete();
    }
}
