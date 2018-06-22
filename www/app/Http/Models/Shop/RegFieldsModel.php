<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class RegFieldsModel extends Model
{
    protected $table = 'reg_fields';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getRegFields($where =[], $column =['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getRegField($where, $column =['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setRegField($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addRegField($data)
    {
        return $this->create($data);
    }
}
