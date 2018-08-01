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

class GoodsExtendModel extends Model
{
    protected $table = 'goods_extend';
    protected $primaryKey = 'extend_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsExtend($where, $columns = ['*'])
    {
        return $this->select($columns)
            ->where($where)
            ->first();
    }

    public function getGoodsExtendAll($columns = ['*'])
    {
        return $this->select($columns)
            ->get();
    }

    public function setGoodsExtend($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addGoodsExtend($data)
    {
        return $this->create($data);
    }

    public function countGoodsExtend($where)
    {
        return $this->where($where)->count();
    }
}
