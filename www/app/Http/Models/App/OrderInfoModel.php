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

    public function orderGoods()
    {
        return $this->hasMany('App\Http\Models\App\OrderGoodsModel', 'order_id', 'order_id');
    }

    public function mapcountry()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'country');
    }

    public function mapprovince()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'province');
    }

    public function mapcity()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'city');
    }

    public function mapdistrict()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'district');
    }

    public function mapstreet()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'street');
    }

    public function returnOrder()
    {
        return $this->hasMany('App\Http\Models\App\OrderReturnModel', 'order_id', 'order_id');
    }

    public function getOrders($where, $orWhere = [], $page = 0, $column = ['*'], $orderBy = [], $size = 10)
    {
        $m = $this->select($column)
            ->with(['orderGoods' => function ($query) {
                $query->select(['*'])
                    ->with(['Goods'])
                    ->get();
            }])
            ->with(['mapcountry' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapprovince' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapcity' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapdistrict' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->where($where);
        if (!empty($orWhere)) {
            $m->where(function ($query) use ($orWhere) {
                foreach ($orWhere as $where) {
                    $query->where($where);
                }
            });
        }
        if (empty($orderBy)) {
            $m->orderBy('add_time', 'DESC');
        } else {
            foreach ($orderBy as $key => $value){
                $m->orderBy($key, $value);
            }
        }
        return $m->offset($page * $size)
            ->limit($size)
            ->get();
    }

    public function getOrder($where, $column = ['*'], $whereIn = [])
    {
        $m = $this->select($column)
            ->with(['orderGoods' => function ($query) {
                $query->select(['*'])
                    ->with(['Goods'])
                    ->get();
            }])
            ->with(['mapcountry' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapprovince' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapcity' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapdistrict' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->where($where);
        if (!empty($whereIn)) {
            $m->whereIn('order_id', $whereIn);
        }
        return $m->orderBy('add_time', 'DESC')->get();
    }

    public function getOrderToAfterSale($where, $column = ['*'], $whereIn = [])
    {
        $m = $this->select($column)
            ->with(['orderGoods' => function ($query) {
                $query->select(['*'])
                    ->with(['Goods'])
                    ->get();
            }])
            ->with(['returnOrder'])
            ->with(['mapcountry' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapprovince' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapcity' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->with(['mapdistrict' => function ($query) {
                $query->select(['region_id', 'region_name']);
            }])
            ->where($where);
        if (!empty($whereIn)) {
            $m->whereIn('order_id', $whereIn);
        }
        return $m->orderBy('add_time', 'DESC')->get();
    }

    public function setOrder($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addOrder($data)
    {
        return $this->create($data);
    }

    public function countOrder($where, $orWhere = [])
    {
        return $this->where($where)
            ->where(function ($query) use ($orWhere) {
                foreach ($orWhere as $where) {
                    $query->orWhere($where);
                }
            })->count();
    }
}
