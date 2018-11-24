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

    public function OrderGoods()
    {
        return $this->hasMany('App\Http\Models\App\OrderGoodsModel', 'order_id', 'order_id');
    }

    public function getOrders($where, $page = 0, $column = ['*'], $size = 10)
    {
        $m = $this->select($column)
            ->with(['OrderGoods' => function ($query) {
                $query->select(['*'])
                    ->with(['Goods'])
                    ->get();
            }])
            ->where($where)->offset($page * $size)->limit($size);
        return $m->get();
    }

    public function getOrder($where, $column = ['*'], $whereIn = [])
    {
        $m = $this->select($column)
            ->with(['OrderGoods' => function ($query) {
                $query->select(['*'])
                    ->with(['Goods'])
                    ->get();
            }])
            ->where($where);
        if (!empty($whereIn)) {
            $m->whereIn('order_id', $whereIn);
        }
        return $m->get();
    }

    public function setOrder($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addOrder($data)
    {
        return $this->create($data);
    }

    public function countOrder($where, $orWhere=[])
    {
        return $this->where($where)->where(function($query) use ($orWhere){
            foreach ($orWhere as $where){
                $query->orWhere($where);
            }
        })->count();
    }
}
