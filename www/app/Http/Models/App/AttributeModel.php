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

class AttributeModel extends Model
{
    protected $table = 'attribute';
    protected $primaryKey = 'attr_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAttrs($where = [], $whereIn = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->whereIn('cat_id', $whereIn)
            ->get();
    }
}
