<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class TeamLogModel extends Model
{
    protected $table = 'team_log';
    protected $primaryKey = 'team_id';
    public $timestamps = false;
    protected $guarded = [];

    public function order()
    {
        return $this->hasMany('App\Http\Models\Wxapp\OrderInfoModel', 'team_id', 'team_id');
    }

    public function getTeamLog($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getTeamLogByAllInfo($goods_id, $column = ['*'], $size = 6)
    {
        return $this->select($column)
            ->select('team_log.team_id', 'team_log.goods_id', 'team_log.start_time', 'o.team_parent_id', 'tg.validity_time', 'tg.team_num')
            ->leftjoin('order_info as o', 'team_log.team_id', '=', 'o.team_id')
            ->leftjoin('team_goods as tg', 'team_log.t_id', '=', 'tg.id')
            ->where('team_log.goods_id', $goods_id)
            ->where('team_log.status', '<', 1)
            ->where('team_log.is_show', 1)
            ->where('o.extension_code', 'team_buy')
            ->where('o.team_parent_id', '>', 0)
            ->where('o.pay_status', 2)
            ->where('tg.is_team', 1)
            ->orderBy('o.add_time', 'desc')
            ->limit($size)
            ->get();
    }
}
