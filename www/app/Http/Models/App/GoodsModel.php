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

class GoodsModel extends Model
{
    protected $table = 'goods';
    protected $primaryKey = 'goods_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodses($where, $page = 1, $column = ['*'], $size = 10)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)->limit($size)
            ->get();
    }
}
