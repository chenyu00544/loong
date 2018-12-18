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

class OrderReturnCauseModel extends Model
{
    protected $table = 'order_return_cause';
    protected $primaryKey = 'cause_id';
    public $timestamps = false;
    protected $guarded = [];


    public function getReturnCauses($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->get();
    }
}
