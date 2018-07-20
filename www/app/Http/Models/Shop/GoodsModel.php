<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsPage($size = 10, $where = [], $columns = ['*'], $keywords = '')
    {
        $goods = $this->select($columns);
        if (!empty($where)) {
            $goods->where($where);
        }
        if (!empty($keywords)) {
            $goods->where('goods_name', 'like', '%' . $keywords . '%');
        }
        return $goods->orderBy('goods_id', 'desc')
            ->paginate($size);
    }

    public function getGoods($id, $columns = ['*'])
    {
        return $this->select($columns)
            ->where('goods_id', $id)
            ->first();
    }

    public function searchGoodses($search, $column = ['*'])
    {
        $m = $this->select($column);
        foreach ($search as $key => $value) {
            $m->orWhere($key, 'like', '%' . $value . '%');
        }
        return $m->get();
    }

    public function getGoodsCount($where = [], $orwhere = [], $inwhere = [])
    {
        $count = $this->where($where);
        if (!empty($orwhere)) {
            $count->orWhere($orwhere);
        }
        if (!empty($inwhere)) {
            $count->whereIn($inwhere[0], $inwhere[1]);
        }
        return $count->count();
    }

    public function setGoods($where = [], $updata = [])
    {
        return $this->where($where)
            ->update($updata);
    }

    public function setGoodsMore($where = [], $updata = [])
    {
        return $this->whereIn('goods_id', $where)
            ->update($updata);
    }

    public function addGoods($data)
    {
        return $this->create($data);
    }

    public function countGoods($where = [])
    {
        return $this->where($where)
            ->count();
    }

    public function getMaxGoodsId()
    {
        return $this->max('goods_id');
    }
}
