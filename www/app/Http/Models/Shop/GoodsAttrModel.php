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

class GoodsAttrModel extends Model
{
    protected $table = 'goods_attr';
    protected $primaryKey = 'goods_attr_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsAttrs($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getGoodsAttr($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getGoodsAttrsJoinAttr($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('attribute', 'attribute.attr_id', '=', 'goods_attr.attr_id')
            ->where($where)
            ->orderBy('goods_attr.attr_id', 'asc')
            ->get();
    }

    public function setGoodsAttr($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addGoodsAttr($data)
    {
        return $this->create($data);
    }
}
