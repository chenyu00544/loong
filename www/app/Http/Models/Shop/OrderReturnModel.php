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

class OrderReturnModel extends Model
{
    protected $table = 'order_return';
    protected $primaryKey = 'ret_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderReturnByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('return_sn', 'like', '%'.$search['keywords'].'%');
                    $query->orWhere('order_sn', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->paginate($size);
    }

    public function getReturnOrder($where, $column = ['*'])
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
}
