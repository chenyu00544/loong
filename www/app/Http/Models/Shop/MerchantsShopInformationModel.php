<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class MerchantsShopInformationModel extends Model
{
    protected $table = 'merchants_shop_information';
    protected $primaryKey = 'shop_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsShopInfo($where, $column =['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }
}
