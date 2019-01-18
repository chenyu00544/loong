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

class SecKillModel extends Model
{
    protected $table = 'seckill';
    protected $primaryKey = 'sec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function seller()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'ru_id');
    }

    public function getSecKillByPage($where = [], $column = ['*'], $keywords = '', $size = 15)
    {
        $m = $this->select($column);
        if (!empty($where)) {
            $m->where($where);
        }
        if (!empty($keywords)) {
            $m->where('acti_title', 'like', '%' . $keywords . '%');
        }
        $m->with(['seller' => function ($query) {
            $query->select(['shop_name', 'ru_id']);
        }]);
        return $m->orderBy('sec_id', 'desc')
            ->paginate($size);
    }

    public function addSecondKill($data)
    {
        return $this->create($data);
    }

    public function getSecondKill($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setSecondKill($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delSecondKill($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {
        }
    }

    public function delSecondKills($where)
    {
        try {
            return $this->whereIn('sec_id', $where)->delete();
        } catch (\Exception $e) {
        }
    }
}
