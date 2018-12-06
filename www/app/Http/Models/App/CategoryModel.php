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

class CategoryModel extends Model
{
    protected $table = 'category';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function subCate()
    {
        return $this->hasMany('App\Http\Models\App\CategoryModel', 'parent_id', 'id');
    }

    public function ads()
    {
        return $this->hasMany('App\Http\Models\App\AdModel', 'cate_id', 'id');
    }

    public function getComCates($where, $column = ['*'])
    {
        return $this->select($column)
            ->with(['subCate' => function ($query) {
                $query->select(['id', 'cat_name', 'parent_id', 'sort_order', 'cat_alias_name', 'touch_icon', 'is_show'])
                    ->with(['ads' => function ($query) {
                        $query->select(['ad_id', 'ad_link', 'ad_code', 'cate_id', 'enabled', 'start_time', 'end_time'])->where([['enabled', '=', 1], ['start_time', '<', time()], ['end_time', '>', time()]]);
                    }])
                    ->with(['subCate' => function ($query) {
                        $query->select(['id', 'cat_name', 'parent_id', 'sort_order', 'cat_alias_name', 'touch_icon', 'is_show'])
                            ->where(['is_show' => 1]);
                    }])
                    ->where(['is_show' => 1]);
            }])
            ->where($where)
            ->orderBy('sort_order', 'DESC')
            ->get();
    }

    //查询下级分类
    public function getSubCates($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }
}
