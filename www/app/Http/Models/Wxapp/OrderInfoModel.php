<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use function foo\func;
use Illuminate\Database\Eloquent\Model;

class OrderInfoModel extends Model
{
    protected $table = 'order_info';
    protected $primaryKey = 'order_id';
    public $timestamps = false;
    protected $guarded = [];

    public function orderGoods()
    {
        return $this->hasMany('App\Http\Models\Wxapp\OrderGoodsModel', 'order_id', 'order_id');
    }

    public function mapcountry()
    {
        return $this->hasOne('App\Http\Models\Wxapp\RegionsModel', 'region_id', 'country');
    }

    public function mapprovince()
    {
        return $this->hasOne('App\Http\Models\Wxapp\RegionsModel', 'region_id', 'province');
    }

    public function mapcity()
    {
        return $this->hasOne('App\Http\Models\Wxapp\RegionsModel', 'region_id', 'city');
    }

    public function mapdistrict()
    {
        return $this->hasOne('App\Http\Models\Wxapp\RegionsModel', 'region_id', 'district');
    }

    public function mapstreet()
    {
        return $this->hasOne('App\Http\Models\Wxapp\RegionsModel', 'region_id', 'street');
    }

    public function returnOrder()
    {
        return $this->hasMany('App\Http\Models\Wxapp\OrderReturnModel', 'order_id', 'order_id');
    }

    public function teamLog()
    {
        return $this->hasOne('App\Http\Models\Wxapp\TeamLogModel', 'team_id', 'team_id');
    }

    public function teamGoods()
    {
        return $this->hasOne('App\Http\Models\Wxapp\TeamGoodsModel', 'id', 't_id');
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
            foreach ($orderBy as $key => $value) {
                $m->orderBy($key, $value);
            }
        }
        return $m->offset($page * $size)
            ->limit($size)
            ->get();
    }

    public function getOrdersByTeam($where, $page, $size, $column = ['*'])
    {
        return $this->select($column)
            ->leftJoin('team_log', 'team_log.team_id', '=', 'order_info.team_id')
            ->with(['teamGoods'])
            ->with(['orderGoods' => function ($query) {
                $query->select(['goods_id', 'order_id'])
                    ->with(['Goods' => function ($query) {
                        $query->select(['goods_id', 'click_count', 'desc_mobile', 'goods_name', 'original_img', 'shop_price', 'is_hot', 'is_new', 'is_best', 'goods_video','goods_thumb','user_id']);
                    }]);
            }])
            ->where($where)
            ->limit($size)
            ->offset(($page - 1) * $size)
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

    /**
     * 统计该拼团已参与人数
     * @param $bargain_id
     * @return mixed
     */
    public function surplusNum($team_id = 0)
    {
        return $this->select('*')
            ->where([['team_id', '=', $team_id], ['extension_code', '=', 'team_buy']])
            ->where(function ($query) {
                $query->orWhere(['pay_status' => 2])->orWhere(['order_status' => 4]);
            })
            ->count();
    }

    /**
     * 验证是否已经参团
     * @param $bargain_id
     * @return mixed
     */
    public function teamJoin($user_id, $t_id = 0)
    {
        return $this->select('*')
            ->where('extension_id', $t_id)
            ->where('user_id', $user_id)
            ->where('extension_code', 'team_buy')
            ->count();
    }

    /**
     * 获取拼团团员信息
     * @param string $type
     * @param integer $size
     * @return mixed
     */
    public function teamUserList($team_id = 0, $page = 1, $size = 5)
    {
        $begin = ($page - 1) * $size;

        $list = $this
            ->select('order_info.add_time','order_info.team_id', 'order_info.user_id','order_info.team_parent_id','order_info.team_user_id')
            ->leftjoin('users as u', 'order_info.user_id', '=', 'u.user_id')
            ->where([['order_info.team_id', '=', $team_id],['order_info.extension_code', '=', 'team_buy']])
            ->orWhere([['order_info.pay_status', '=', 2],['order_info.order_status', '=', 4]])
            ->orderBy('order_info.add_time', 'asc')
            ->offset($begin)
            ->limit($size)
            ->get()
            ->toArray();

        return $list;
    }
}
