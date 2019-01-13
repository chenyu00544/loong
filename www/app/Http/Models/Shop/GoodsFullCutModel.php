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

class GoodsFullCutModel extends Model
{
    protected $table = 'goods_full_cut';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsFullCut($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setGoodsFullCut($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addGoodsFullCut($data)
    {
        return $this->create($data);
    }

    public function delGoodsFullCut($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {}
    }
}
