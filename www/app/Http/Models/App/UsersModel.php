<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use function foo\func;
use Illuminate\Database\Eloquent\Model;

class UsersModel extends Model
{
    protected $table = 'users';
    protected $primaryKey = 'user_id';
    public $timestamps = false;
    protected $guarded = [];

    public function addresses()
    {
        return $this->hasMany('App\Http\Models\App\UserAddressModel', 'user_id', 'user_id');
    }

    public function getUserByAddress($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['addresses' => function ($query) {
                $query->select(['address_id', 'user_id', 'consignee', 'country', 'province', 'city', 'district', 'address', 'mobile'])
                    ->with(['mapprovince' => function ($query) {
                        $query->select(['region_id', 'region_name']);
                    }])
                    ->with(['mapcity' => function ($query) {
                        $query->select(['region_id', 'region_name']);
                    }])
                    ->with(['mapdistrict' => function ($query) {
                        $query->select(['region_id', 'region_name']);
                    }]);
            }])
            ->where($where)
            ->first();
    }

    public function getUser($name, $column = ['*'])
    {
        return $this->select($column)
            ->orWhere(['user_id' => $name])
            ->orWhere(['email' => $name])
            ->orWhere(['mobile_phone' => $name])
            ->first();
    }

    public function setUsers($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }
}
