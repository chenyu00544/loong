<?php

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
}
