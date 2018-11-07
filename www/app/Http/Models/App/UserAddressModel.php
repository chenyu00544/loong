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

class UserAddressModel extends Model
{
    protected $table = 'user_address';
    protected $primaryKey = 'address_id';
    public $timestamps = false;
    protected $guarded = [];

    public function mapprovince()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'province');
    }

    public function mapcity()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'city');
    }

    public function mapdistrict()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'district');
    }

    public function mapstreet()
    {
        return $this->hasOne('App\Http\Models\App\RegionsModel', 'region_id', 'street');
    }

    public function userAddresses($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['mapprovince'])
            ->with(['mapcity'])
            ->with(['mapdistrict'])
            ->with(['mapstreet'])
            ->where($where)
            ->get();
    }

    public function setAddresses($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addAddresses($data)
    {
        return $this->create($data);
    }

    public function delAddresses($where)
    {
        return $this->where($where)
            ->delete();
    }
}
