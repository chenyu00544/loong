<?php

namespace App\Http\Models\shop;

use Illuminate\Database\Eloquent\Model;

class CategoryModel extends Model
{
    protected $table = 'category';
    protected $primaryKey = 'cat_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getComCates($id = 0)
    {
        return $this->where('parent_id', $id)
            ->orderBy('sort_order', 'asc')
            ->get();
    }

    public function getComCate($id)
    {
        return $this->where('id', $id)
            ->first();
    }

    public function getComCatesByIn($in)
    {
        return $this->whereIn('id', $in)
            ->orderBy('sort_order', 'asc')
            ->get();
    }

    public function searchComCates($search, $column = ['*'])
    {
        $m = $this->select($column);
        foreach ($search as $key => $value) {
            $m->orWhere($key, 'like', '%' . $value . '%');
        }
        return $m->get();
    }

    public function getComParentCate($id, $cates = [])
    {
        $rep = $this->where('id', $id)->first();
        $cates[] = $rep;
        if ($rep->parent_id != 0) {
            return $this->getComParentCate($rep->parent_id, $cates);
        } else {
            return $cates;
        }
    }

    public function setComCate($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function upDateCate($data, $id)
    {
        return $this->where('id', $id)
            ->update($data);
    }

    public function getRank($data, $index = 0, $ranks = ['二级', '三级', '四级', '五级', '六级', '七级', '八级', '九级', '十级'])
    {
        if ($data->parent_id == 0) {
            return [$ranks[$index], $index];
        }
        $i = $index + 1;
        $re = $this->getComCate($data->parent_id);
        if ($re) {
            return $this->getRank($re, $i);
        } else {
            return [$ranks[$i], $i];
        }
    }

    public function addCate($data)
    {
        return $this->create($data);
    }

    public function deleteCate($where)
    {
        return $this->where($where)
            ->delete();
    }
}
