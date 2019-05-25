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

    public function gattr()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsAttrModel', 'goods_id', 'goods_id');
    }

    public function goodsext()
    {
        return $this->hasOne('App\Http\Models\Wxapp\GoodsExtendModel', 'goods_id', 'goods_id');
    }

    public function getTeamGoods($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getTeamGoodsInfo($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['goods' => function ($query) {
                $query->select('user_id', 'goods_id', 'goods_sn', 'goods_name', 'is_real', 'is_shipping', 'is_on_sale', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'goods_number', 'sales_volume', 'goods_desc', 'desc_mobile', 'goods_type', 'goods_brief', 'model_attr', 'review_status', 'freight', 'tid', 'shipping_fee', 'goods_desc', 'model_price');
            }])
            ->with(['goodsext' => function ($query) {
                $query->select(['goods_id', 'is_reality', 'is_return', 'is_fast', 'extend_id']);
            }])
            ->with(['ggallery' => function ($query) {
                $query->select(['img_id', 'goods_id', 'img_original', 'img_url'])->orderBy('img_desc', 'DESC');
            }])
            ->with(['gattr' => function ($query) {
                $query->select(['goods_attr_id', 'goods_id', 'attr_id', 'color_value', 'attr_price', 'attr_value', 'attr_img_flie', 'attr_gallery_flie', 'attr_checked', 'img_flag'])
                    ->where(['attr_checked' => 1])
                    ->with(['attr' => function ($query) {
                        $query->select(['attr_type', 'attr_id', 'attr_name', 'attr_group']);
                    }])
                    ->orderBy('img_flag', 'DESC')
                    ->orderBy('attr_sort', 'DESC');
            }])
            ->where($where)
            ->where(['is_team' => 1])
            ->first();
    }

    public function teamRankingList($page = 1, $size = 10, $type = 0)
    {
        $goods = $this
            ->select('g.goods_id', 'g.goods_name', 'g.shop_price','g.goods_number', 'g.sales_volume','g.goods_thumb', 'team_goods.id', 'team_goods.team_price', 'team_goods.team_num','team_goods.limit_num')
            ->leftjoin('goods as g', 'g.goods_id', '=', 'team_goods.goods_id');

        switch ($type) {
            // 热门
            case '0':
                $goods->orderBy('team_goods.limit_num', 'DESC');
                break;
            // 新品
            case '1':
                $goods->orderBy('g.add_time', 'DESC');
                break;
            // 优选
            case '2':
                $goods->where('g.is_hot', 1);
                break;
            case '3':
                $goods->where('g.is_best', 1);
                break;
        }

        $begin = ($page - 1) * $size;
        $list = $goods->where('team_goods.is_team', 1)
            ->where('team_goods.is_audit', 2)
            ->where('g.is_on_sale', 1)
            ->where('g.is_alone_sale', 1)
            ->where('g.is_delete', 0)
            ->where('g.review_status', '>', 2)
            ->offset($begin)
            ->limit($size)
            ->get()
            ->toArray();

        if ($list === null) {
            return [];
        }

        return $list;
    }
}
