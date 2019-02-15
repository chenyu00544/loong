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

class AdPositionModel extends Model
{
    protected $table = 'ad_position';
    protected $primaryKey = 'position_id';
    public $timestamps = false;
    protected $guarded = [];

    public function ads()
    {
        return $this->hasMany('App\Http\Models\Wxapp\AdModel', 'position_id', 'position_id');
    }

    public function getPositionByAds($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['ads' => function ($query) {
                $query->select(['ad_id', 'position_id', 'ad_link', 'ad_code', 'link_color'])->where([['enabled','=',1],['start_time','<',time()],['end_time','>',time()]])->orderBy('sort_order', 'DESC');
            }])
            ->where($where)
            ->orderBy('sort', 'DESC')
            ->get();
    }

    public function getPositionByAd($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['ads' => function ($query) {
                $query->select(['ad_id', 'position_id', 'ad_link', 'ad_code', 'link_color'])->where([['enabled','=',1],['start_time','<',time()],['end_time','>',time()]])->orderBy('sort_order', 'DESC');
            }])
            ->where($where)
            ->orderBy('sort', 'DESC')
            ->first();
    }
}
