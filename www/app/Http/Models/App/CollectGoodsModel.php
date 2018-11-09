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

class CollectGoodsModel extends Model
{
    protected $table = 'collect_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\App\GoodsModel','goods_id','goods_id');
    }

    public function getColloectsByGoods($where, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->where($where)
            ->with(['goods'])
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function getCollectGoods($where)
    {
        return $this->where($where)->first();
    }

    public function setCollectGoods($where, $data)
    {
        return $this->where($where)->update($data);
    }

    public function addCollectGoods($data)
    {
        return $this->create($data);
    }

    public function countCollectGoods($where)
    {
        return $this->where($where)->count();
    }
}
