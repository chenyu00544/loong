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

    public function getGoods($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['faat' => function ($query) {
                $query->select(['*'])->where([['start_time', '<', time()], ['end_time', '>', time()]]);
            }])
            ->with(['gvp' => function ($query) {
                $query->where(['price_type' => 1]);
            }])
            ->with(['qa' => function ($query) {
                $query->where(['parent_id' => 0])->limit(2);
            }])
            ->with(['gattr'])
            ->where($where)
            ->first();
    }
}
