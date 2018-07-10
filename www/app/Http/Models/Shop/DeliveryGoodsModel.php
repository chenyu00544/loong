<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class DeliveryGoodsModel extends Model
{
    protected $table = 'delivery_goods';
    protected $primaryKey = 'delivery_id';
    public $timestamps = false;
    protected $guarded = [];
}
