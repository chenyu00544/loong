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

class CartModel extends Model
{
    protected $table = 'cart';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\App\GoodsModel', 'goods_id', 'goods_id');
    }

    public function store()
    {
        return $this->hasOne('App\Http\Models\App\SellerShopInfoModel', 'ru_id', 'ru_id');
    }

    public function countCart($where)
    {
        return $this->where($where)->count();
    }

    public function getCarts($where, $column = ['*'], $whereIn = [])
    {
        $m = $this->select($column)
            ->with(['goods' => function ($query) {
                $query->select(['goods_id', 'original_img', 'shop_price', 'market_price', 'is_promote', 'promote_price',
                    'promote_start_date', 'promote_end_date']);
            }])
            ->with(['store' => function ($query) {
                $query->select(['ru_id', 'shop_name', 'shop_logo']);
            }]);
        if (!empty($whereIn)) {
            $m->whereIn('rec_id', $whereIn);
        }
        return $m->where($where)
            ->orderBy('add_time', 'DESC')
            ->get();

    }

    public function setCart($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addCart($data)
    {
        return $this->create($data);
    }
}
