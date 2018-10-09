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

class CollectGoodsModel extends Model
{
    protected $table = 'collect_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];
}
