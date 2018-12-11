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

    public function getNotifies($where, $page = 1, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'DESC')
            ->offset(($page - 1) * 10)
            ->limit(10)
            ->get();
    }

    public function getNotify($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('id', 'DESC')
            ->first();
    }

}
