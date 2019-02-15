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

class FavourableActivityModel extends Model
{
    protected $table = 'favourable_activity';
    protected $primaryKey = 'act_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->belongsToMany('App\Http\Models\Wxapp\GoodsModel', 'favourable_goods', 'act_id', 'goods_id');
    }

    public function getFaats($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) {
                $query->select(['goods.goods_id', 'goods_name', 'market_price', 'shop_price', 'goods_thumb', 'goods_img', 'original_img', 'is_delete', 'is_on_sale'])
                    ->where(['is_delete' => 0, 'is_on_sale' => 1]);
            }])
            ->orderBy('sort_order', 'DESC')
            ->get();
    }
}
