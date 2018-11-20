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

class OrderInfoModel extends Model
{
    protected $table = 'order_info';
    protected $primaryKey = 'order_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Goods()
    {
        //GoodsModel前必须加App\Http\Models\Shop, 第二个参数是表名，无需前缀，第三个参数是本类的字段，第四个参数是要查找的字段
        return $this->belongsToMany('App\Http\Models\App\GoodsModel', 'order_goods', 'order_id', 'goods_id');
    }

    public function OrderGoods()
    {
        return $this->hasOne('App\Http\Models\App\OrderGoodsModel', 'order_id', 'order_id');
    }

    public function UserAddress()
    {
        return $this->hasOne('App\Http\Models\App\UserAddressModel', 'user_id', 'user_id');
    }

    public function getOrders($where, $page = 0, $column = ['*'], $size = 10)
    {
        $m = $this->select($column)
            ->with(['Goods' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->with(['OrderGoods' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->with(['UserAddress' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->where($where)->offset($page * $size)->limit($size);
        return $m->get();
    }

    public function getOrder($where, $column = ['*'])
    {
        $m = $this->select($column)
            ->with(['Goods' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->with(['OrderGoods' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->with(['UserAddress' => function ($query) {
                $query->select(['*'])->get();
            }])
            ->where($where);
        return $m->first();
    }

    public function setOrder($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addOrder($data)
    {
        return $this->create($data);
    }
}
