<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class OrderReturnModel extends Model
{
    protected $table = 'order_return';
    protected $primaryKey = 'ret_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasMany('App\Http\Models\Wxapp\OrderReturnGoodsModel', 'ret_id', 'ret_id');
    }

    public function returnImg()
    {
        return $this->hasMany('App\Http\Models\Wxapp\OrderReturnImagesModel', 'ret_id', 'ret_id');
    }

    public function getOrderReturn($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setOrderReturn($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addOrderReturn($data)
    {
        return $this->create($data);
    }

    public function countOrderReturn($where)
    {
        return $this->where($where)->count();
    }

    public function incrementOrderReturn($where, $column = 'activation_number')
    {
        return $this->where($where)->increment($column);
    }
}
