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

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    public function gvp()
    {
        return $this->hasMany('App\Http\Models\App\GoodsVolumePriceModel', 'goods_id', 'goods_id');
    }

    public function fullcut()
    {
        return $this->hasMany('App\Http\Models\App\GoodsFullCutModel', 'goods_id', 'goods_id');
    }

    public function faat()
    {
        return $this->belongsToMany('App\Http\Models\App\FavourableActivityModel', 'favourable_goods', 'goods_id', 'act_id');
    }

    public function qa()
    {
        return $this->hasMany('App\Http\Models\App\QuestionAnswerModel', 'id_value', 'goods_id');
    }

    public function gattr()
    {
        return $this->hasMany('App\Http\Models\App\GoodsAttrModel', 'goods_id', 'goods_id');
    }

    public function ggallery()
    {
        return $this->hasMany('App\Http\Models\App\GoodsGalleryModel', 'goods_id', 'goods_id');
    }

    public function shop()
    {
        return $this->hasOne('App\Http\Models\App\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function brand()
    {
        return $this->hasOne('App\Http\Models\App\BrandModel', 'id', 'brand_id');
    }

    public function getGoodses($where, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)->limit($size)
            ->get();
    }

    public function getGoodsAndExt($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['faat' => function ($query) {
                $query->select(['*'])->where([['start_time', '<', time()], ['end_time', '>', time()], ['review_status', '=', 3]])->orderBy('sort_order', 'DESC')->limit(1);
            }])
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->with(['fullcut'])
            ->with(['qa' => function ($query) {
                $query->where(['parent_id' => 0])->limit(2);
            }])
            ->with(['gattr' => function ($query) {
                $query->select(['goods_attr_id', 'goods_id', 'attr_id', 'color_value', 'attr_price', 'attr_value', 'attr_img_flie', 'attr_gallery_flie','attr_checked'])->where(['attr_checked' => 1])
                    ->with(['attr' => function ($query) {
                        $query->select(['attr_type', 'attr_id', 'attr_name', 'attr_group']);
                    }])
                    ->orderBy('attr_sort', 'DESC');
            }])
            ->with(['ggallery' => function ($query) {
                $query->select(['img_id', 'goods_id', 'img_original', 'img_url'])->orderBy('img_desc', 'DESC');
            }])
            ->with(['shop' => function ($query) {
            }])
            ->with(['brand' => function ($query) {
            }])
            ->where($where)
            ->first();
    }
}
