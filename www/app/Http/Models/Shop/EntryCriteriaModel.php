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

class EntryCriteriaModel extends Model
{
    protected $table = 'entry_criteria';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getEntryCriteriasByPage($where = [], $column = ['*'], $size = 15)
    {
        return $this->select($column)
            ->where($where)
            ->paginate($size);
    }

    public function getEntryCriterias($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getEntryCriteria($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getSubEntryCriteria($pids = [0], $ids = [])
    {
        $res = $this->select('id')->whereIn('parent_id', $pids)->get();
        $arr = [];
        if ($res->count() > 0) {
            foreach ($res as $re) {
                $arr[] = $re->id;
            }
            $ids = array_merge($ids, $arr);
            return $this->getSubEntryCriteria($arr, $ids);
        }
        return $ids;
    }

    public function setEntryCriteria($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addEntryCriteria($data)
    {
        return $this->create($data);
    }

    public function delEntryCriterias($ids)
    {
        return $this->whereIn('id', $ids)->delete();
    }
}
