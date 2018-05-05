<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class TransportModel extends Model
{
    protected $table = 'transport';
    protected $primaryKey = 'tid';
    public $timestamps = false;
    protected $guarded = [];

    public function getTransportAll($where = ['ru_id' => 0], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('tid', 'desc')
            ->get();
    }

    public function getTransport($id, $column = ['*'])
    {
        return $this->select($column)
            ->where('tid', $id)
            ->first();
    }
}
