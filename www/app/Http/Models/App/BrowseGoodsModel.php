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

class BrowseGoodsModel extends Model
{
    protected $table = 'browse_goods';
    protected $primaryKey = 'browse_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\App\GoodsModel', 'goods_id', 'goods_id');
    }

    public function getBrowseGoodses($where, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) {
                $query->select(['goods_id', 'goods_name', 'shop_price', 'market_price', 'is_promote'
                    , 'promote_price', 'promote_start_date', 'promote_end_date', 'original_img', 'goods_thumb', 'goods_img', 'is_best', 'is_new', 'is_hot']);
            }])
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function addBrowseGoods($data)
    {
        return $this->create($data);
    }

    public function setBrowseGoods($where, $data, $whereIn = [])
    {
        $m = $this->where($where);
        if (count($whereIn) > 0) {
            $m->whereIn('browse_id', $whereIn);
        }
        return $m->update($data);
    }

    public function countBrowseGoods($where)
    {
        return $this->where($where)->count();
    }
}
