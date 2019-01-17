<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 
 */

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class SecKillGoodsRemindModel extends Model
{
    protected $table = 'seckill_goods_remind';
    protected $primaryKey = 'r_id';
    public $timestamps = false;
    protected $guarded = [];
}
