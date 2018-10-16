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

class GoodsDescriptionModel extends Model
{
    protected $table = 'goods_description';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsDescriptions($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }
}
