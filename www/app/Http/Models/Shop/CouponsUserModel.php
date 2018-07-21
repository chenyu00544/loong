<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class CouponsUserModel extends Model
{
    protected $table = 'coupons_user';
    protected $primaryKey = 'cu_id';
    public $timestamps = false;
    protected $guarded = [];

    public function delCouponsUser($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function delCouponsUserByIn($where)
    {
        return $this->whereIn('cou_id', $where)
            ->delete();
    }
}
