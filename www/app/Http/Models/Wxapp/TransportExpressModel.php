<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class TransportExpressModel extends Model
{
    protected $table = 'transport_express';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function shipping()
    {
        return $this->hasOne('App\Http\Models\Wxapp\ShippingModel', 'shipping_id', 'shipping_id');
    }
}
