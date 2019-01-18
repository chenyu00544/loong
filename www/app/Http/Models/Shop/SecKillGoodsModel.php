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

class SecKillGoodsModel extends Model
{
    protected $table = 'seckill_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Shop\GoodsModel', 'goods_id', 'goods_id');
    }

    public function getSecKillGoods($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods' => function ($query) {
                $query->select(['goods_id', 'goods_name', 'shop_price']);
            }])
            ->orderBy('id', 'DESC')
            ->get();
    }

    public function addSecKillGoods($data)
    {
        return $this->create($data);
    }

    public function setSecKillGoods($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delSecKillGoods($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {
        }
    }
}
