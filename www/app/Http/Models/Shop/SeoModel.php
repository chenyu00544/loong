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

class SeoModel extends Model
{
    protected $table = 'seo';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getSeo($where, $column=['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setSeo($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }
}
