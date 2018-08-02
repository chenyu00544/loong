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

class AliossConfigureModel extends Model
{
    protected $table = 'alioss_configure';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAliossByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->paginate($size);
    }

    public function getAlioss($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setAlioss($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAlioss($data)
    {
        return $this->create($data);
    }

    public function delAlioss($where)
    {
        return $this->where($where)
            ->delete();
    }
}
