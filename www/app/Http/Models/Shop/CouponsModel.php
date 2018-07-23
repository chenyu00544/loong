<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class CouponsModel extends Model
{
    protected $table = 'coupons';
    protected $primaryKey = 'cou_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCouponsByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('cou_name', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->orderBy('cou_id', 'desc')
            ->paginate($size);
    }

    public function getCoupons($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setCoupons($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addCoupons($data)
    {
        return $this->create($data);
    }

    public function delCoupons($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function delCouponsByIn($where)
    {
        return $this->whereIn('cou_id', $where)
            ->delete();
    }
}
