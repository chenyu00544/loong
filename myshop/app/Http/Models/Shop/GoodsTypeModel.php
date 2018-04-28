<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class GoodsTypeModel extends Model
{
    protected $table = 'goods_type';
    protected $primaryKey = 'cat_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getGoodsTypesPage($size = 10, $where = [], $columns = ['*'], $keywords = '')
    {
        $goods = $this->select($columns);
        if (!empty($where)) {
            $goods->where($where);
        }
        if(!empty($keywords)){
            $goods->where('cat_name', 'like', '%'.$keywords.'%');
        }
        return $goods->orderBy('cat_id', 'desc')
            ->paginate($size);
    }
}
