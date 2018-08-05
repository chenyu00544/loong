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

class MerchantsStepsFieldsCententModel extends Model
{
    protected $table = 'merchants_steps_fields_centent';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function mst()
    {
        return $this->hasOne('App\Http\Models\Shop\MerchantsStepsTitleModel','tid','tid');
    }

    public function getMerchantsStepsFieldsCentent($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['mst'])
            ->where($where)
            ->first();
    }

    public function setMerchantsStepsFieldsCentent($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addMerchantsStepsFieldsCentent($data)
    {
        return $this->create($data);
    }

    public function delMerchantsStepsFieldsCentent($where)
    {
        return $this->where($where)
            ->delete();
    }
}
