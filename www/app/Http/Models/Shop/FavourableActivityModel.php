<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class FavourableActivityModel extends Model
{
    protected $table = 'favourable_activity';
    protected $primaryKey = 'act_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getFavourableActivityByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->where($where);
        if (!empty($search)) {
            $m->where(function ($query) use ($search) {
                if (!empty($search['keywords'])) {
                    $query->orWhere('act_name', 'like', '%' . $search['keywords'] . '%');
                }
            });
        }
        return $m->paginate($size);
    }

    public function setFavourableActivity($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function delFavourableActivity($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function delFavourableActivitys($where)
    {
        return $this->whereIn('act_id',$where)
            ->delete();
    }
}
