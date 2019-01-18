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

class SecKillTimeBucketModel extends Model
{
    protected $table = 'seckill_time_bucket';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getSecKillTimeBuckets($where=[], $column=['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'asc')
            ->get();
    }

    public function addSecondKillTimeBucket($data)
    {
        return $this->create($data);
    }

    public function getSecondKillTimeBucket($where, $column=['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setSecondKillTimeBucket($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delSecondKillTimeBucket($where)
    {
        try{
            return $this->where($where)->delete();
        }catch (\Exception $e){}
    }
}
