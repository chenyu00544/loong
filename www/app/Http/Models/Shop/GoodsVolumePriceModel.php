<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsVolumePriceModel extends Model
{
    protected $table = 'goods_volume_price';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsVolumePrice($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setGoodsVolumePrice($where, $data)
    {

    }

    public function addGoodsVolumePrice($data)
    {
        return $this->create($data);
    }
}
