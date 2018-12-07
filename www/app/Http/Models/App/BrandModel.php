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

class BrandModel extends Model
{
    protected $table = 'brand';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getBrands($where = [], $whereIn = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->whereIn('id', $whereIn)
            ->get();
    }
}
