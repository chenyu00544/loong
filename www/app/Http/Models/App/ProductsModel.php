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

class ProductsModel extends Model
{
    protected $table = 'products';
    protected $primaryKey = 'product_id';
    public $timestamps = false;
    protected $guarded = [];

    public function attrs()
    {
        return $this->hasMany('App\Http\Models\App\GoodsAttrModel', 'goods_id', 'goods_id');
    }

    public function getProdcutAndAttr($ids, $column = ['*'], $where = [])
    {
        sort($ids);
        $gids = implode('|', $ids);
        return $this->select($column)
            ->with(['attrs' => function ($query) use ($ids) {
                $query->whereIn('goods_attr_id', $ids);
            }])
            ->where($where)
            ->where('goods_attr', $gids)
            ->first();
    }
}
