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

class GoodsVolumePriceModel extends Model
{
    protected $table = 'goods_volume_price';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];
}
