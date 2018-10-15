<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class GoodsAttrModel extends Model
{
    protected $table = 'goods_attr';
    protected $primaryKey = 'goods_attr_id';
    public $timestamps = false;
    protected $guarded = [];
}
