<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BonusModel extends Model
{
    protected $table = 'bonus';
    protected $primaryKey = 'bonus_id';
    public $timestamps = false;
    protected $guarded = [];

    public function useBonus()
    {
        return $this->hasMany('App\Http\Models\Shop\BonusUserModel', 'bonus_type_id', 'bonus_id');
    }

    public function UseBonusUser()
    {
        return $this->hasMany('App\Http\Models\Shop\BonusUserModel', 'bonus_type_id', 'bonus_id');
    }

    public function getBonusByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->withCount(['useBonus' => function ($query) {
                $query->where('order_id','<>', '0');
            }])
            ->withCount(['UseBonusUser'])
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('type_name', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->orderBy('bonus_id', 'desc')
            ->paginate($size);
    }

    public function getBonus($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setBonus($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addBonus($data)
    {
        return $this->create($data);
    }

    public function delBonus($where)
    {
        return $this->where($where)
            ->delete();
    }
}
