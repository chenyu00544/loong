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

class ReturnCauseModel extends Model
{
    protected $table = 'order_return_cause';
    protected $primaryKey = 'cause_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getReturnCauseByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }

    public function getReturnCauses($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getReturnCause($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setReturnCause($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addReturnCause($data)
    {
        return $this->create($data);
    }

    public function delReturnCause($where)
    {
        return $this->where($where)
            ->delete();
    }
}
