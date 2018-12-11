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

class NotifyModel extends Model
{
    protected $table = 'notify';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getNotifiesByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'DESC')
            ->paginate($size);
    }

    public function getNotify($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setNotify($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addNotify($data)
    {
        return $this->create($data);
    }

    public function delNotify($where)
    {
        return $this->where($where)
            ->delete();
    }
}
