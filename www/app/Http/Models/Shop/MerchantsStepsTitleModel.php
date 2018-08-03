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

class MerchantsStepsTitleModel extends Model
{
    protected $table = 'merchants_steps_title';
    protected $primaryKey = 'tid';
    public $timestamps = false;
    protected $guarded = [];

    public function msp()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsStepsProcessModel','id','fields_steps');
    }

    public function getMerchantsStepsTitleByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->with(['msp'])
            ->where($where)
            ->paginate($size);
    }

    public function setMerchantsStepsTitle($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function getMerchantsStepsTitle($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addMerchantsStepsTitle($data)
    {
        return $this->create($data);
    }

    public function delMerchantsStepsTitle($where)
    {
        return $this->where($where)
            ->delete();
    }
}
