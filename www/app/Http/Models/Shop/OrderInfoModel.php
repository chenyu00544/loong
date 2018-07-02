<?php

namespace App\Http\Models\Shop;

use function foo\func;
use Illuminate\Database\Eloquent\Model;

class OrderInfoModel extends Model
{
    protected $table = 'order_info';
    protected $primaryKey = 'order_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderInfoByPage($where, $orWhere, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($orWhere)) {
            $m->where(function ($query) use ($orWhere) {
                foreach ($orWhere as $value) {
                    $query->orWhere($value);
                }
            });
        }
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
//                    $query->orWhere('order_goods.goods_name', 'like', '%'.$search['keywords'].'%');
                    $query->orWhere('goods_sn', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
//        dd($m->toSql());
        return $m->paginate($size);
    }

    public function getOrderInfo($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setOrderInfo($where = [], $data, $whereIn = [])
    {
        $m = $this->where($where);
        if (!empty($whereIn)) {
            $m->whereIn('order_id', $whereIn);
        }
        return $m->update($data);
    }

    public function countOrder($where, $orWhere = [], $seller = 'selfsale')
    {
        $m = $this->where($where);

        if ($seller == 'selfsale') {
            $m->where('user_id', '=', '0');
        } else {
            $m->where('user_id', '<>', '0');
        }

        if (!empty($orWhere)) {
            $m->where(function ($query) use ($orWhere) {
                foreach ($orWhere as $value) {
                    $query->orWhere($value);
                }
            });
        }
        return $m->count();
    }

    public function delOrderInfo($where = [], $whereIn = [])
    {
        
    }
}
