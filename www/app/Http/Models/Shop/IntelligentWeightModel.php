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

class IntelligentWeightModel extends Model
{
    protected $table = 'intelligent_weight';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsWeight($id)
    {
        return $this->where('goods_id', $id)
            ->first();
    }

    public function delGoodsWeight($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {
        }

    }
}
