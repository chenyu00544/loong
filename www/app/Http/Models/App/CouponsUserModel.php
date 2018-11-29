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

class CouponsUserModel extends Model
{
    protected $table = 'coupons_user';
    protected $primaryKey = 'cu_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Coupons()
    {
        return $this->hasOne('App\Http\Models\App\CouponsModel', 'cou_id', 'cou_id');
    }

    public function getCouponses($where, $column=['*'])
    {
        $m = $this->select($column)
            ->where($where)
            ->with(['Coupons']);
        return $m->get();
    }

    public function countCouponsUser($where)
    {
        return $this->where($where)
            ->count();
    }
}
