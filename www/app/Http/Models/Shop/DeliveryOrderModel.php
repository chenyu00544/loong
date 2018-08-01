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

class DeliveryOrderModel extends Model
{
    protected $table = 'delivery_order';
    protected $primaryKey = 'delivery_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getDeliveryOrderByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('delivery_sn', 'like', '%' . $search['keywords'] . '%');
                    $query->orWhere('order_sn', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->paginate($size);
    }

    public function getDeliveryOrder($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setDeliveryOrder($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delDelivery($where)
    {
        return $this->where($where)->delete();
    }
}
