<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsTypeCateModel extends Model
{
    protected $table = 'goods_type_cate';
    protected $primaryKey = 'cate_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getTypeCates($where = [])
    {
        return $this->where($where)->get();
    }

    public function getTypeCate($id)
    {
        return $this->where('cate_id', $id)
            ->first();
    }

}
