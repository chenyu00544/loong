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

class GoodsActivityModel extends Model
{
    protected $table = 'goods_activity';
    protected $primaryKey = 'act_id';
    public $timestamps = false;
    protected $guarded = [];

    public function seller()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function getGoodsActivityByPage($where = [], $column = ['*'], $keywords = '', $size = 15)
    {
        $m = $this->select($column);
        if (!empty($where)) {
            $m->where($where);
        }
        if (!empty($keywords)) {
            $m->where('act_name', 'like', '%' . $keywords . '%');
        }
        $m->with(['seller'=>function($query){
            $query->select(['shop_name','ru_id']);
        }]);
        return $m->orderBy('act_id', 'desc')
            ->paginate($size);
    }
}
