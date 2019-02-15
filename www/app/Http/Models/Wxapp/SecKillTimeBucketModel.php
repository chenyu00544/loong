<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class SecKillTimeBucketModel extends Model
{
    protected $table = 'seckill_time_bucket';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function killGoods()
    {
        return $this->hasMany('App\Http\Models\Wxapp\SecKillGoodsModel', 'tb_id', 'id');
    }

    public function getSecKillTimeByGoods($where, $sec_id, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['killGoods' => function ($query) use ($sec_id) {
                $query->join('goods', 'goods.goods_id', '=', 'seckill_goods.goods_id')
                    ->select('seckill_goods.*', 'goods.goods_id', 'goods.goods_name', 'goods.shop_price', 'goods.market_price', 'goods.goods_thumb', 'goods.goods_img', 'goods.original_img', 'goods.is_best', 'goods.is_hot', 'goods.sales_volume')
                    ->where(['sec_id' => $sec_id]);
            }])
            ->get();
    }
}
