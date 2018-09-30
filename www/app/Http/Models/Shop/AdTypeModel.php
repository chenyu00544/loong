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

class AdTypeModel extends Model
{
    protected $table = 'ad_type';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAdTypeByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->paginate($size);
    }

    public function getAdTypes($column = ['*'])
    {
        return $this->select($column)
            ->get();
    }

    public function getAdType($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setAdType($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAdType($data)
    {
        return $this->create($data);
    }

    public function delAdType($where)
    {
        return $this->where($where)
            ->delete();
    }
}
