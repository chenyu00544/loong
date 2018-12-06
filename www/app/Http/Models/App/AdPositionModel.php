<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class AdPositionModel extends Model
{
    protected $table = 'ad_position';
    protected $primaryKey = 'position_id';
    public $timestamps = false;
    protected $guarded = [];

    public function ads()
    {
        return $this->hasMany('App\Http\Models\App\AdModel', 'position_id', 'position_id');
    }

    public function getPositionByAds($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['ads' => function ($query) {
                $query->select(['ad_id', 'position_id', 'ad_link', 'ad_code', 'link_color'])->where([['enabled','=',1],['start_time','<',time()],['end_time','>',time()]]);
            }])
            ->where($where)
            ->orderBy('sort', 'DESC')
            ->get();
    }
}
