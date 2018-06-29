<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class OrderInfoModel extends Model
{
    protected $table = 'order_info';
    protected $primaryKey = 'order_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderInfoByPage($where, $orWhere, $keywords, $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->join('order_goods', 'order_goods.order_id','=','order_info.order_id')
            ->join('users', 'users.user_id','=','order_info.user_id')
            ->join('merchants_shop_information', 'merchants_shop_information.user_id','=','order_goods.ru_id')
            ->where($where);
        if(!empty($orWhere)){
            $m->where(function ($query) use($orWhere){
                foreach ($orWhere as $value) {
                    $query->orWhere($value);
                }
            });
        }
        if($keywords != ''){
            $m->where('order_goods.goods_name', 'like', '%'.$keywords.'%');
        }
//        dd($m->toSql());
        return $m->paginate($size);
    }

    public function countOrder($where, $orWhere = [], $seller = 'selfsale')
    {
        $m = $this->where($where);

        if($seller == 'selfsale'){
            $m->where('user_id','=','0');
        }else{
            $m->where('user_id','<>','0');
        }

        if (!empty($orWhere)) {
            $m->where(function ($query) use ($orWhere) {
                foreach ($orWhere as $value) {
                    $query->orWhere($value);
                }
            });
        }
        return $m->count();
    }
}
