<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class BonusModel extends Model
{
    protected $table = 'bonus';
    protected $primaryKey = 'bonus_id';
    public $timestamps = false;
    protected $guarded = [];

    public function BonusUser()
    {
        return $this->hasMany('App\Http\Models\Shop\BonusUserModel', 'bonus_type_id', 'bonus_id');
    }

    public function getBonusByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->with(['BonusUser' => function ($query) {
                $query->where('order_id', '0')->count();
            }])
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('type_name', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->orderBy('bonus_id', 'desc')
//            ->toSql();
            ->paginate($size);
    }
}
