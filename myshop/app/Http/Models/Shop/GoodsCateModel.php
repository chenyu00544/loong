<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsCateModel extends Model
{
    protected $table = 'goods_cate';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
