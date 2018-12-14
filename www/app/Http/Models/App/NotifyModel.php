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

class NotifyModel extends Model
{
    protected $table = 'notify';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getNotifies($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'DESC')
            ->get();
    }

    public function getNotify($where = [], $column = ['*'], $orderby = 'DESC')
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', $orderby)
            ->first();
    }

    public function getNotifyGT($where, $id, $column = ['*'], $orderby = 'DESC')
    {
        return $this->select($column)
            ->where($where)
            ->where('id', '>', $id)
            ->orderBy('id', $orderby)
            ->first();
    }

}
