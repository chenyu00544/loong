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

class TeamLogModel extends Model
{
    protected $table = 'team_log';
    protected $primaryKey = 'team_id';
    public $timestamps = false;
    protected $guarded = [];

    public function order()
    {
        return $this->hasMany('App\Http\Models\Shop\OrderInfoModel', 'team_id', 'team_id');
    }

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Shop\GoodsModel', 'goods_id', 'goods_id');
    }

    public function store()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'user_id');
    }

    public function getTeamLogByPage($where, $search = [], $raw = '', $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->leftJoin('goods', 'goods.goods_id', '=', 'team_log.goods_id')
            ->leftJoin('team_goods', 'team_goods.id', '=', 'team_log.t_id')
            ->where($where)
            ->with(['order' => function ($query) {
                $query->where(['extension_code' => 'team_buy'])->select(['team_id', 'team_parent_id', 'team_user_id', 'team_price', 'order_id']);
            }])
            ->with(['store' => function ($query) {
                $query->select(['shop_name', 'ru_id']);
            }]);
        if (!empty($search['keywords'])) {
            $m->where('goods_name', 'like', $search['keywords']);
        }
        if ($raw != '') {
            $m->whereRaw($raw);
        }
        return $m->paginate($size);
    }

    public function setTeamLog($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function taskTeamLog($size = 10)
    {
        $m = $this->join('team_goods', 'team_goods.id', '=', 'team_log.t_id')
            ->where(['status' => 0])->whereRaw('start_time < ' . (time() - 600) . '-validity_time*3600')->limit($size)->get();
        return $m;
    }


}
