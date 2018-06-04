<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsAttrModel extends Model
{
    protected $table = 'goods_attr';
    protected $primaryKey = 'goods_attr_id';
    public $timestamps = false;
    protected $guarded = [];


    public function addGoodsAttr($data)
    {
        return $this->create($data);
    }
}
