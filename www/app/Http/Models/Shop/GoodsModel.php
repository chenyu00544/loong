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

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    public function store()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function products()
    {
        return $this->hasOne('App\Http\Models\Shop\ProductsModel', 'goods_id', 'goods_id');
    }

    public function getGoodsPage($size = 10, $where = [], $columns = ['*'], $keywords = '')
    {
        $goods = $this->select($columns);
        if (!empty($where)) {
            $goods->where($where);
        }
        if (!empty($keywords)) {
            $goods->where('goods_name', 'like', '%' . $keywords . '%');
        }
        $goods->with(['store']);
        $goods->with(['products' => function ($query) {
            $query->select(['goods_id']);
        }]);
        return $goods->orderBy('goods_id', 'desc')
            ->paginate($size);
    }

    public function getGoods($id, $columns = ['*'])
    {
        return $this->select($columns)
            ->where('goods_id', $id)
            ->first();
    }

    public function getGoodses($where, $columns = ['*'])
    {
        return $this->select($columns)
            ->where($where)
            ->get();
    }

    public function getGoodsesByIn($in, $columns = ['*'])
    {
        return $this->select($columns)
            ->whereIn('goods_id', $in)
            ->get();
    }

    public function searchGoodses($search, $column = ['*'])
    {
        $m = $this->select($column);
        $m->where(['is_delete' => 0, 'is_on_sale' => 1]);
        $m->where(function ($query) use ($search) {
            foreach ($search as $key => $value) {
                $query->orWhere($key, 'like', '%' . $value . '%');
            }
        });
        return $m->get();
    }

    public function dialogSearch($where, $search, $column = ['*'])
    {
        $m = $this->select($column);
        $m->where(['is_delete' => 0, 'is_on_sale' => 1]);
        $m->where($where);
        $m->where(function ($query) use ($search) {
            foreach ($search as $key => $value) {
                $query->where($key, 'like', '%' . $value . '%');
            }
        });
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

    public function delGoods($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {

        }
    }
}
