<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsCateModel extends Model
{
    protected $table = 'goods_cate';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsCates($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function setGoodsCate()
    {
        
    }

    public function addGoodsCate($data)
    {
        return $this->create($data);
    }
}
