<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ShippingModel extends Model
{
    protected $table = 'shipping';
    protected $primaryKey = 'shipping_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getShippingAll()
    {
        return $this->get();
    }
}
