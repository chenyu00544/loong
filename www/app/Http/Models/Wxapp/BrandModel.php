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

class BrandModel extends Model
{
    protected $table = 'brand';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsModel', 'brand_id', 'id');
    }

    public function getBrands($where = [], $whereIn = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->whereIn('id', $whereIn)
            ->get();
    }

    public function getBrand($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getBrandByGoodses($where, $column = ['*'], $orderby = [], $gwhere=[])
    {
        $m = $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) use ($orderby, $gwhere) {
                $query->select([
                    'goods_id', 'cat_id', 'user_id', 'goods_name', 'goods_sn', 'brand_id', 'freight',
                    'goods_number', 'shop_price', 'market_price', 'promote_price', 'promote_start_date', 'promote_end_date',
                    'desc_mobile', 'goods_desc', 'goods_id', 'goods_thumb', 'original_img', 'goods_img', 'is_on_sale',
                    'is_delete', 'is_best', 'is_new', 'is_hot', 'is_promote', 'is_volume', 'is_fullcut',
                    'goods_type', 'is_limit_buy', 'limit_buy_start_date', 'limit_buy_end_date', 'limit_buy_num', 'review_status',
                    'sales_volume', 'comments_number', 'tid', 'goods_cause', 'goods_video', 'is_distribution',
                    'pinyin_keyword', 'goods_brief'
                ])
                    ->where($gwhere)
                    ->orderBy($orderby['column'], $orderby['desc']);
            }]);

        return $m->first();
    }
}
