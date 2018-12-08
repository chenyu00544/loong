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

    public function attr()
    {
        return $this->hasOne('App\Http\Models\App\AttributeModel', 'attr_id', 'attr_id');
    }

    public function getGoodsByFilter($filter, $column = ['*'])
    {
        $m = $this->select($column);
        $m->where(['attr_id' => $filter['attr_id']]);
        $m->whereIn('attr_value', $filter['attr_value']);
        return $m->get();
    }
}
