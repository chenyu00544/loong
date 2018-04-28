<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsTransportModel extends Model
{
    protected $table = 'goods_transport';
    protected $primaryKey = 'tid';
    public $timestamps = false;
    protected $guarded = [];

    public function getTransportAll($columns = ['*'])
    {
        return $this->select($columns)
            ->get();
    }

}
