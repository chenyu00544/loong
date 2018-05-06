<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class TransportExpressModel extends Model
{
    protected $table = 'transport_express';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getExpressAll($where = ['ru_id' => 0], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setExpress($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addExpress($data = ['ru_id' => 0])
    {
        return $this->create($data);
    }

    public function delExpress($where)
    {
        return $this->where($where)
            ->delete();
    }

}
