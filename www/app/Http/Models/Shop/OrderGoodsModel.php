<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class OrderGoodsModel extends Model
{
    protected $table = 'order_goods';
    protected $primaryKey = 'rec_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getOrderGoodses($where, $column = ['*'])
    {
        return $this->select($column)
            ->join('goods', 'goods.goods_id', '=', 'order_goods.goods_id')
            ->where($where)
            ->get();
    }

    public function sumOrderGoodsByCate($cate)
    {
        $prefix = Config::get('database.connections.mysql.prefix');
        return DB::table('order_goods as og')->select([DB::raw("SUM({$prefix}og.o_goods_number * {$prefix}og.goods_price) AS order_fee"), DB::raw("COUNT(DISTINCT {$prefix}o.order_id) AS order_num"), DB::raw("SUM({$prefix}og.o_goods_number) AS order_goods_num")])
            ->leftJoin('order_info as o', 'o.order_id', '=', 'og.order_id')
            ->leftJoin('goods as g', 'g.goods_id', '=', 'og.goods_id')
            ->leftJoin('category as c', 'c.id', '=', 'g.cat_id')
            ->whereIn('c.id', $cate)
            ->first();
    }

    public function delOrderGoods($where)
    {
        return $this->where($where)->delete();
    }
}
