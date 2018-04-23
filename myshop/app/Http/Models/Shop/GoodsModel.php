<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsPage($size = 10, $where = [], $columns = ['*'])
    {
        $goods = $this->select($columns);
        if (!empty($where)) {
            $goods->where($where);
        }
        return $goods->orderBy('goods_id', 'desc')
            ->paginate($size);
    }

    public function getGoods($id, $columns = ['*'])
    {
        return $this->select($columns)
            ->where('goods_id',$id)
            ->first();
    }

    public function getGoodsCount($where = [], $orwhere = [], $inwhere = [])
    {
        $count = $this->where($where);
        if (!empty($orwhere)) {
            $count->orWhere($orwhere);
        }
        if (!empty($inwhere)) {
            $count->whereIn($inwhere[0],$inwhere[1]);
        }
        return $count->count();
    }

    public function setGoods($where = [], $updata = [])
    {
        return $this->where($where)
            ->update($updata);
    }
}
