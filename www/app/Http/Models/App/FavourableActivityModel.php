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

    public function getFavourableActivitys($where, $column = ['*'])
    {
        return $this->select($column)
            ->whereIn('act_id', $where)
            ->get();
    }

    public function getFavourableActivity($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setFavourableActivity($where, $updata)
    {
        return $this->where($where)
            ->update($updata);
    }

    public function addFavourableActivity($updata)
    {
        return $this->create($updata);
    }

    public function delFavourableActivity($where)
    {
        return $this->where($where)
            ->delete();
    }

    public function delFavourableActivitys($where)
    {
        return $this->whereIn('act_id', $where)
            ->delete();
    }
}
