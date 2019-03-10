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
use Illuminate\Support\Facades\DB;

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    //阶梯价格
    public function gvp()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsVolumePriceModel', 'goods_id', 'goods_id');
    }

    //满减价格
    public function fullcut()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsFullCutModel', 'goods_id', 'goods_id');
    }

    //秒杀
    public function secKill()
    {
        return $this->hasOne('App\Http\Models\Wxapp\SecKillGoodsModel', 'goods_id', 'goods_id');
    }

    //拼团
    public function teamGoods()
    {
        return $this->hasOne('App\Http\Models\Wxapp\TeamGoodsModel', 'goods_id', 'goods_id');
    }

    //团购
    public function groupBuy()
    {
        return $this->hasOne('App\Http\Models\Wxapp\GoodsActivityModel', 'goods_id', 'goods_id');
    }

    public function qa()
    {
        return $this->hasMany('App\Http\Models\Wxapp\QuestionAnswerModel', 'id_value', 'goods_id');
    }

    public function gattr()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsAttrModel', 'goods_id', 'goods_id');
    }

    public function ggallery()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsGalleryModel', 'goods_id', 'goods_id');
    }

    public function shop()
    {
        return $this->hasOne('App\Http\Models\Wxapp\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function brand()
    {
        return $this->hasOne('App\Http\Models\Wxapp\BrandModel', 'id', 'brand_id');
    }

    public function store()
    {
        return $this->hasOne('App\Http\Models\Wxapp\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function goodsext()
    {
        return $this->hasOne('App\Http\Models\Wxapp\GoodsExtendModel', 'goods_id', 'goods_id');
    }

    public function category()
    {
        return $this->hasOne('App\Http\Models\Wxapp\CategoryModel', 'id', 'cat_id');
    }

    public function getGoodses($where, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->where($where)
            ->where(function ($query) {
                $query->orWhere('is_best', 1)
                    ->orWhere('is_hot', 1);
            })
            ->where('review_status', '>=', 3)
            ->orderBy('sort_order', 'DESC')
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function getGoodsesByCateIds($whereIn, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->whereIn('cat_id', $whereIn)
            ->where('review_status', '>=', 3)
            ->orderBy('sort_order', 'DESC')
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function getGoodsByBrandId($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->where(['is_on_sale' => 1, 'is_delete' => 0])
            ->where([['review_status', '>=', 3], ['cat_id', '<>', 0]])
            ->with(['category' => function ($query) {
                $query->select(['id', 'cat_name', 'cat_alias_name', 'is_show', 'touch_icon']);
            }])
            ->orderBy('cat_id', 'DESC')
            ->get();
    }

    public function getGoods($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getGoodsAndExt($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->with(['fullcut'])
            ->with(['qa' => function ($query) {
                $query->where(['parent_id' => 0])->limit(2);
            }])
            ->with(['gattr' => function ($query) {
                $query->select(['goods_attr_id', 'goods_id', 'attr_id', 'color_value', 'attr_price', 'attr_value', 'attr_img_flie', 'attr_gallery_flie', 'attr_checked'])->where(['attr_checked' => 1])
                    ->with(['attr' => function ($query) {
                        $query->select(['attr_type', 'attr_id', 'attr_name', 'attr_group']);
                    }])
                    ->orderBy('attr_sort', 'DESC');
            }])
            ->with(['ggallery' => function ($query) {
                $query->select(['img_id', 'goods_id', 'img_original', 'img_url'])->orderBy('img_desc', 'DESC');
            }])
            ->with(['shop' => function ($query) {
                $query->select(['shop_name', 'ru_id', 'notice']);
            }])
            ->with(['brand' => function ($query) {
            }])
            ->with(['secKill' => function ($query) {
                $time = time();
                $date = date('H:i:s', $time);
                $query->select(['seckill.*', 'seckill_goods.*', 'seckill_time_bucket.begin_time as b_time', 'seckill_time_bucket.end_time as e_time', 'seckill_time_bucket.id'])->join('seckill', 'seckill.sec_id', '=', 'seckill_goods.sec_id')->join('seckill_time_bucket', 'seckill_time_bucket.id', '=', 'seckill_goods.tb_id')->where([['seckill.review_status', '=', '3'], ['seckill.is_putaway', '=', '1'], ['seckill.start_time', '<', $time], ['seckill.end_time', '>', $time], ['seckill_time_bucket.begin_time', '<', $date], ['seckill_time_bucket.end_time', '>', $date]]);
            }])
            ->with(['teamGoods' => function ($query) {
                $query->with(['teamLog' => function ($query) {
                    $query->with(['order' => function ($query) {
                        $query->select(['team_id', 'users.user_id', 'team_parent_id', 'user_name', 'nick_name', 'logo'])->leftJoin('users', 'users.user_id', '=', 'order_info.user_id')->orderBy('team_parent_id', 'ASC');
                    }])->where(['status' => 0]);
                }])->where(['is_audit' => 2, 'is_team' => 1]);
            }])
            ->with(['groupBuy' => function ($query) {
                $query->select(['goods_id', 'review_status', 'act_type', 'start_time', 'end_time', 'is_finished', 'ext_info'])->where([['review_status', '=', '3'], ['act_type', '=', '1'], ['start_time', '<', time()], ['end_time', '>', time()], ['is_finished', '=', '0']]);
            }])
            ->where($where)
            ->where('review_status', '>=', 3)
            ->first();
    }

    public function getGoodsByOrder($where, $column = ['*'], $whereIn = [])
    {
        return $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->with(['fullcut'])
            ->with(['gattr' => function ($query) {
                $query->select(['goods_attr_id', 'goods_id', 'attr_id', 'color_value', 'attr_price', 'attr_value', 'attr_img_flie', 'attr_gallery_flie', 'attr_checked'])->where(['attr_checked' => 1])
                    ->with(['attr' => function ($query) {
                        $query->select(['attr_type', 'attr_id', 'attr_name', 'attr_group']);
                    }])
                    ->orderBy('attr_sort', 'DESC');
            }])
            ->with(['goodsext' => function ($query) {
                $query->select(['goods_id', 'is_reality', 'is_return', 'is_fast', 'extend_id']);
            }])
            ->with(['secKill' => function ($query) {
                $time = time();
                $date = date('H:i:s', $time);
                $query->select(['seckill.*', 'seckill_goods.*', 'seckill_time_bucket.begin_time as b_time', 'seckill_time_bucket.end_time as e_time', 'seckill_time_bucket.id'])->join('seckill', 'seckill.sec_id', '=', 'seckill_goods.sec_id')->join('seckill_time_bucket', 'seckill_time_bucket.id', '=', 'seckill_goods.tb_id')->where([['seckill.review_status', '=', '3'], ['seckill.is_putaway', '=', '1'], ['seckill.start_time', '<', $time], ['seckill.end_time', '>', $time], ['seckill_time_bucket.begin_time', '<', $date], ['seckill_time_bucket.end_time', '>', $date]]);
            }])
            ->with(['teamGoods' => function ($query) {
                $query->with(['teamLog' => function ($query) {
                    $query->with(['order' => function ($query) {
                        $query->select(['team_id', 'users.user_id', 'team_parent_id', 'user_name', 'nick_name', 'logo'])->leftJoin('users', 'users.user_id', '=', 'order_info.user_id')->orderBy('team_parent_id', 'ASC');
                    }])->where(['status' => 0]);
                }])->where(['is_audit' => 2, 'is_team' => 1]);
            }])
            ->with(['groupBuy' => function ($query) {
                $query->select(['act_id', 'goods_id', 'review_status', 'act_type', 'start_time', 'end_time', 'is_finished', 'ext_info'])->where([['review_status', '=', '3'], ['act_type', '=', '1'], ['start_time', '<', time()], ['end_time', '>', time()], ['is_finished', '=', '0']]);
            }])
            ->where($where)
            ->whereIn('goods_id', $whereIn)
            ->get();
    }

    public function getGoodsesBySearch($keywords = [], $where = [], $whereOr = [], $page = 1, $wherein = [], $orderby = [], $column = ['*'], $size = 20)
    {
        $m = $this->select($column)
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->with(['brand' => function ($query) {
                $query->select(['brand_name', 'id', 'brand_logo']);
            }])
            ->where($where)
            ->where('review_status', '>=', 3);

        if (count($keywords) > 0) {
            $m->where(function ($query) use ($keywords) {
                foreach ($keywords as $keyword) {
                    $query->orWhere('pinyin_keyword', 'like', '%' . $keyword . '%');
                }
            });
        }

        if (count($whereOr) > 0) {
            $m->where(function ($query) use ($whereOr) {
                foreach ($whereOr as $or) {
                    $query->orWhere($or);
                }
            });
        }

        if (count($wherein) > 0) {
            foreach ($wherein as $k => $wn) {
                $m->whereIn($k, $wn);
            }
        }

        if (count($orderby) > 0) {
            foreach ($orderby as $key => $value) {
                $m->orderBy($key, $value);
            }
        }

        return $m->orderBy('sort_order', 'DESC')
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)->limit($size)
            ->get();
    }

    public function countGoods($where)
    {
        return $this->where($where)->count();
    }

    //自增
    public function incrementGoodses($where, $column)
    {
        return $this->where($where)->increment($column, 1);
    }
}
