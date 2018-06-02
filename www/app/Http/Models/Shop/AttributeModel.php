<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class AttributeModel extends Model
{
    protected $table = 'attribute';
    protected $primaryKey = 'attr_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAttributePage($id = 0, $size = 10)
    {
        return $this->where('cat_id', $id)
            ->orderBy('attr_id', 'desc')
            ->paginate($size);
    }

    public function getAttrs($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('attr_type', 'asc')
            ->get();
    }

    public function addAttribute($data)
    {
        return $this->create($data);
    }

    public function getAttr($id)
    {
        return $this->where('attr_id', $id)
            ->first();
    }

    public function setAttr($data, $id)
    {
        return $this->where('attr_id', $id)
            ->update($data);
    }

    public function delAttribute($where)
    {
        return $this->where($where)
            ->delete();
    }

}
