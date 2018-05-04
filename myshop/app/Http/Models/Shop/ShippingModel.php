<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ShippingModel extends Model
{
    protected $table = 'shipping';
    protected $primaryKey = 'shipping_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getShippingAll()
    {
        return $this->get();
    }

    public function getShipping($id, $column = ['*'])
    {
        return $this->select($column)
            ->where('shipping_id', $id)
            ->first();
    }

    public function addShip($data)
    {
        return $this->create($data);
    }

    public function changes($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function deleteShip($where)
    {
        return $this->where($where)
            ->delete();
    }
}
