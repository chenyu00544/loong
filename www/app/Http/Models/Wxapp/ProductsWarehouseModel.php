<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class ProductsWarehouseModel extends Model
{
    protected $table = 'products_warehouse';
    protected $primaryKey = 'product_id';
    public $timestamps = false;
    protected $guarded = [];

    public function attrs()
    {
        return $this->hasMany('App\Http\Models\Wxapp\GoodsAttrModel', 'goods_id', 'goods_id');
    }

    public function getProdcut($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getProdcutAndAttr($ids, $column = ['*'], $where = [])
    {
        if(is_array($ids)){
            sort($ids);
            $gids = implode('|', $ids);
        }else{
            $gids = $ids;
        }
        return $this->select($column)
            ->with(['attrs' => function ($query) use ($ids) {
                $query->whereIn('goods_attr_id', $ids);
            }])
            ->where($where)
            ->where('goods_attr', $gids)
            ->first();
    }
}
