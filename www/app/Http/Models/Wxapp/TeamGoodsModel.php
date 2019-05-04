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

class TeamGoodsModel extends Model
{
    protected $table = 'team_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function teamLog()
    {
        return $this->hasMany('App\Http\Models\Wxapp\TeamLogModel', 'goods_id', 'goods_id');
    }

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Wxapp\GoodsModel', 'goods_id', 'goods_id');
    }

    public function ggallery()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsGalleryModel', 'goods_id', 'goods_id');
    }

    public function goodsInfo($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['goods' => function ($query) {
                $query->select('user_id', 'goods_id', 'goods_sn', 'goods_name', 'is_real', 'is_shipping', 'is_on_sale', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'goods_number', 'sales_volume', 'goods_desc', 'desc_mobile', 'goods_type', 'goods_brief', 'model_attr', 'review_status', 'freight', 'tid', 'shipping_fee');
            }])
            ->with(['ggallery' => function ($query) {
                $query->select(['img_id', 'goods_id', 'img_original', 'img_url'])->orderBy('img_desc', 'DESC');
            }])
            ->where($where)
            ->where(['is_team' => 1])
            ->first();
    }
}
