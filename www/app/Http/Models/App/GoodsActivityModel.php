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

class GoodsActivityModel extends Model
{
    protected $table = 'goods_activity';
    protected $primaryKey = 'act_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\App\GoodsModel', 'goods_id', 'goods_id');
    }

    public function getGoodsActivitys($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods'=>function($query){
                $query->select('goods_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'is_hot', 'sales_volume');
            }])
            ->get();
    }
}
