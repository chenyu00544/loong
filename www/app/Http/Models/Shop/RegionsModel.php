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

class RegionsModel extends Model
{
    protected $table = 'regions';
    protected $primaryKey = 'region_id';
    public $timestamps = false;
    protected $guarded = [];

    public function Users()
    {
        return $this->belongsToMany('App\Http\Models\Shop\UsersModel', 'user_address', 'province', 'user_id');
    }

    public function Order()
    {
        return $this->hasMany('App\Http\Models\Shop\OrderInfoModel', 'province', 'region_id');
    }

    public function province()
    {
        return $this->hasMany('App\Http\Models\Shop\RegionsModel', 'parent_id', 'region_id');
    }

    public function city()
    {
        return $this->hasMany('App\Http\Models\Shop\RegionsModel', 'parent_id', 'region_id');
    }

    public function district()
    {
        return $this->hasMany('App\Http\Models\Shop\RegionsModel', 'parent_id', 'region_id');
    }

    public function street()
    {
        return $this->hasMany('App\Http\Models\Shop\RegionsModel', 'parent_id', 'region_id');
    }

    public function getRegions($parent = 0, $column = ['region_id', 'region_name', 'parent_id', 'region_type'])
    {
        return $this->select($column)
            ->where(['parent_id'=> $parent])
            ->get();
    }

    public function getAllRegionsFormat()
    {
        return $this->where(['parent_id' => 0])
            ->with(['province' => function ($query) {
                $query->select(['region_id', 'region_name', 'parent_id'])->with(['city' => function ($query) {
                    $query->select(['region_id', 'region_name', 'parent_id'])->with(['district' => function ($query) {
                        $query->select(['region_id', 'region_name', 'parent_id']);
                    }]);
                }]);
            }])
            ->get();
    }

    public function searchRegions($keywords, $column = ['*'])
    {
        return $this->select($column)
            ->where('region_name', 'like', '%' . $keywords . '%')
            ->first();
    }

    public function getRegionsRange($type_s = 0, $type_e = 0)
    {
        return $this->select('region_id', 'region_name', 'parent_id', 'region_type')
            ->where([['region_type', '>=', $type_s], ['region_type', '<=', $type_e]])
            ->orderBy('region_id', 'asc')
            ->get();
    }

    public function getRegion($id, $column = ['region_id', 'region_name'])
    {
        return $this->select($column)
            ->where('region_id', $id)
            ->first();
    }

    public function setRegion($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addRegion($data)
    {
        return $this->create($data);
    }

    public function delRegion($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function getRegionsByUser($type = 1, $parent = 1)
    {
        return $this->withCount(['Users'])
            ->where([['region_type', $type], ['parent_id', $parent]])
            ->get();
    }

    public function getRegionsBySale($type = 1, $parent = 1)
    {
        return $this->with(['Order' => function ($query) {
            $query->select(['pay_status', 'money_paid', 'province'])->where(['pay_status' => 2]);
        }])
            ->where([['region_type', $type], ['parent_id', $parent]])
            ->get();
    }

    public function getRegionsByOrder($type = 1, $parent = 1)
    {
        return $this->withCount(['Order' => function ($query) {
            $query->where(['pay_status' => 2]);
        }])
            ->where([['region_type', $type], ['parent_id', $parent]])
            ->get();
    }
}
