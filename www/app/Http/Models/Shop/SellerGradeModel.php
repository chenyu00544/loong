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

class SellerGradeModel extends Model
{
    protected $table = 'seller_grade';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function mpri()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsPrivilegeModel', 'grade_id', 'id');
    }

    public function getSellerGradesByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'asc')
            ->paginate($size);
    }

    public function getSellerGradesByPri($column = ['*'])
    {
        return $this->select($column)
            ->with(['mpri'])
            ->orderBy('is_default', 'desc')
            ->get();
    }

    public function getSellerGradeByPri($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['mpri'])
            ->where($where)
            ->first();
    }

    public function getSellerGrades($column = ['*'])
    {
        return $this->select($column)
            ->get();
    }

    public function getSellerGrade($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setSellerGrade($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addSellerGrade($data)
    {
        return $this->create($data);
    }

    public function delSellerGrade($where)
    {
        return $this->where($where)
            ->delete();
    }
}
