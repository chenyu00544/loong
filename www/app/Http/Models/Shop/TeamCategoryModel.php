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

class TeamCategoryModel extends Model
{
    protected $table = 'team_category';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function subCates()
    {
        return $this->hasMany('App\Http\Models\Shop\TeamCategoryModel', 'parent_id', 'id');
    }

    public function getTeamCatesByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }

    public function getTeamCates($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getTeamCatesBySub($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->with(['subCates'])
            ->get();
    }

    public function addTeamCate($data)
    {
        return $this->create($data);
    }

    public function getTeamCate($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setTeamCate($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function delTeamCate($where)
    {
        try {
            return $this->where($where)->delete();
        } catch (\Exception $e) {
        }
    }

    public function countTeamCates($where)
    {
        return $this->where($where)->count();
    }
}
