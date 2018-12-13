<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Home;

use Illuminate\Database\Eloquent\Model;

class ArticleModel extends Model
{
    protected $table = 'article';
    protected $primaryKey = 'article_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->belongsToMany('App\Http\Models\Shop\GoodsModel', 'article_goods', 'article_id', 'goods_id');
    }

    public function cate()
    {
        return $this->hasOne('App\Http\Models\Shop\ArticleCatModel', 'cat_id', 'cat_id');
    }

    public function ext()
    {
        return $this->hasOne('App\Http\Models\Shop\ArticleExtendModel', 'article_id', 'article_id');
    }

    public function getArticle($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->with(['goods' => function ($query) {
                $query->select(['goods.goods_id', 'goods_name', 'article_id']);
            }])
            ->with(['ext'])
            ->where($where)
            ->first();
    }
}
