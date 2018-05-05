<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class TransportExtendModel extends Model
{
    protected $table = 'transport_extend';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getExtendAll($where = ['ru_id' => 0], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setExtend($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addExtend($data = ['ru_id' => 0])
    {
        return $this->create($data);
    }

    public function delExtend($where)
    {
        return $this->where($where)
            ->delete();
    }
}
