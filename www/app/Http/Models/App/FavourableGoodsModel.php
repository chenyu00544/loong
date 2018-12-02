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

class FavourableGoodsModel extends Model
{
    protected $table = 'favourable_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getFaat($wheres, $column = ['*'])
    {
        $m = $this->select($column)
            ->join('favourable_activity', 'favourable_activity.act_id', '=', 'favourable_goods.act_id')
            ->where([['start_time', '<', time()], ['end_time', '>', time()], ['review_status', '=', 3]])
            ->where(function ($query) {
                $query->orWhere('terminal_type', 'all')->orWhere('terminal_type', 'app');
            })
            ->orderBy('sort_order', 'DESC')->limit(1);
        $m->where(function ($query) use ($wheres){
            foreach ($wheres as $where) {
                $query->orWhere($where);
            }
        });

        return $m->first();
    }
}
