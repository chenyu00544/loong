<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class CouponsModel extends Model
{
    protected $table = 'coupons';
    protected $primaryKey = 'cou_id';
    public $timestamps = false;
    protected $guarded = [];
}
