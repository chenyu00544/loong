<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class AdPositionModel extends Model
{
    protected $table = 'ad_position';
    protected $primaryKey = 'position_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getAdPosByPage($where, $search, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->leftJoin('merchants_shop_information', 'merchants_shop_information.user_id','=','ad_position.user_id')
            ->where($where);
        if (!empty($search['keywords'])) {
            $m->where('position_name', 'like', '%' . $search['keywords'] . '%');
        }
        return $m->orderBy('position_id', 'desc')
            ->paginate($size);
    }
}
