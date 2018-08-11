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

class MerchantsGradeModel extends Model
{
    protected $table = 'merchants_grade';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsGrade($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setMerchantsGrade($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMerchantsGrade($data)
    {
        return $this->create($data);
    }
}
