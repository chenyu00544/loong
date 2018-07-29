<?php

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

    public function getRegions($type = 0, $parent = 0, $column = ['region_id', 'region_name', 'parent_id', 'region_type'])
    {
        return $this->select($column)
            ->where([['region_type', $type], ['parent_id', $parent]])
            ->get();
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
            $query->select(['pay_status','money_paid','province'])->where(['pay_status' => 2]);
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
