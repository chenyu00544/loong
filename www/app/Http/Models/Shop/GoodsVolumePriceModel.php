<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsVolumePriceModel extends Model
{
    protected $table = 'goods_volume_price';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
