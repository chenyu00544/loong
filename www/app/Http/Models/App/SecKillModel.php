<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class SecKillModel extends Model
{
    protected $table = 'seckill';
    protected $primaryKey = 'sec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getSecKill($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('add_time', 'DESC')
            ->first();
    }
}
