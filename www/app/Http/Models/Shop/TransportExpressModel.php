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

class TransportExpressModel extends Model
{
    protected $table = 'transport_express';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function shipping()
    {
        return $this->hasOne('App\Http\Models\Shop\ShippingModel', 'shipping_id', 'shipping_id');
    }

    public function getExpressAll($where = ['ru_id' => 0], $column = ['*'])
    {
        return $this->select($column)
            ->with(['shipping'])
//            ->join('shipping','shipping.shipping_id','=','transport_express.shipping_id')
            ->where($where)
            ->get();
    }

    public function setExpress($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addExpress($data = ['ru_id' => 0])
    {
        return $this->create($data);
    }

    public function delExpress($where)
    {
        return $this->where($where)
            ->delete();
    }

}
