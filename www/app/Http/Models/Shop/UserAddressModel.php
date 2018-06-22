<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class UserAddressModel extends Model
{
    protected $table = 'user_address';
    protected $primaryKey = 'address_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getUserAddresses($where, $column =['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getUserAddress($where, $column =['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setUserAddress($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delUserAddress($where)
    {
        return $this->where($where)->delete();
    }
}
