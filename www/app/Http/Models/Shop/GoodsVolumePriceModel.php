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
        return $this->where($where)
            ->update($data);
    }

    public function addGoodsVolumePrice($data)
    {
        return $this->create($data);
    }

    public function delGoodsVolumePrice($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {}
    }
}
