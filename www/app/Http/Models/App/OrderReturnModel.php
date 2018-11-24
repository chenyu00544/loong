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

class OrderReturnModel extends Model
{
    protected $table = 'order_return';
    protected $primaryKey = 'ret_id';
    public $timestamps = false;
    protected $guarded = [];

    public function countOrderReturn($where)
    {
        return $this->where($where)->count();
    }
}
