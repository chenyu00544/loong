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

class CollectGoodsModel extends Model
{
    protected $table = 'collect_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Wxapp\GoodsModel', 'goods_id', 'goods_id');
    }

    public function getColloectsByGoods($where, $page = 1, $column = ['*'], $order = 'DESC', $size = 10)
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) {
                $query->select(['goods_id', 'cat_id', 'brand_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'is_hot', 'is_new', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume']);
            }])
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->orderBy('add_time', $order)
            ->get();
    }

    public function getCollectGoods($where)
    {
        return $this->where($where)->first();
    }

    public function setCollectGoods($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addCollectGoods($data)
    {
        return $this->create($data);
    }

    public function countCollectGoods($where)
    {
        return $this->where($where)->count();
    }
}
