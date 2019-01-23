<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class TeamGoodsModel extends Model
{
    protected $table = 'team_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Shop\GoodsModel', 'goods_id', 'goods_id');
    }

    public function products()
    {
        return $this->hasMany('App\Http\Models\Shop\ProductsModel', 'goods_id', 'goods_id');
    }

    public function getTeamsByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) {
                $query->join('seller_shop_info', 'seller_shop_info.ru_id', '=', 'goods.user_id')
                    ->select(['goods.goods_id', 'goods.goods_name', 'goods.is_best', 'goods.is_hot', 'goods.is_new', 'goods.user_id', 'goods.shop_price','goods.goods_sn','seller_shop_info.shop_name','seller_shop_info.ru_id']);
            }])
            ->with(['products' => function ($query) {
                $query->select(['goods_id']);
            }])
            ->paginate($size);
    }

    public function addTeam($data)
    {
        return $this->create($data);
    }
}
