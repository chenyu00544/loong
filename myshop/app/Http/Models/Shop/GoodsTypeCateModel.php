<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsTypeCateModel extends Model
{
    protected $table = 'goods_type_cate';
    protected $primaryKey = 'cate_id';
    public $timestamps = false;
    protected $guarded = [];
}
