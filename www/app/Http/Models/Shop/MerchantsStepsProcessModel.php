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

class MerchantsStepsProcessModel extends Model
{
    protected $table = 'merchants_steps_process';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getMerchantsStepsProcessByPage($where, $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('process_steps', 'asc')
            ->orderBy('steps_sort', 'asc')
            ->paginate($size);
    }

    public function getMerchantsStepsProcesses($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('process_steps', 'asc')
            ->orderBy('steps_sort', 'asc')
            ->get();
    }

    public function setMerchantsStepsProcess($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function getMerchantsStepsProcess($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function addMerchantsStepsProcess($data)
    {
        return $this->create($data);
    }

    public function delMerchantsStepsProcess($where)
    {
        return $this->where($where)
            ->delete();
    }
}
