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

class TeamCategoryModel extends Model
{
    protected $table = 'team_category';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasMany('App\Http\Models\App\TeamGoodsModel', 'tc_id', 'id');
    }

    public function subCate()
    {
        return $this->hasMany('App\Http\Models\App\TeamCategoryModel', 'parent_id', 'id');
    }

    public function getTeamCates($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->get();
    }

    public function getTeamCate($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->first();
    }

    public function getTeamCatesByGoods($whereIn = [], $where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->whereIn('id', $whereIn)
            ->with(['goods' => function ($query) {
                $query->join('goods', 'goods.goods_id', '=', 'team_goods.goods_id')->select(['team_goods.tc_id','team_goods.id', 'team_goods.goods_id', 'team_goods.team_price', 'goods.goods_id', 'goods.goods_name', 'goods.original_img']);
            }])
            ->get();
    }
}
