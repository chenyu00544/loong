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

class AlismsConfigureModel extends Model
{
    protected $table = 'alisms_configure';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAlismsByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'desc')
            ->paginate($size);
    }

    public function getAlisms($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setAlisms($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAlisms($data)
    {
        return $this->create($data);
    }

    public function delAlisms($where)
    {
        return $this->where($where)
            ->delete();
    }
}
