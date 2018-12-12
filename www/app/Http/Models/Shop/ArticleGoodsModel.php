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

class ArticleGoodsModel extends Model
{
    protected $table = 'article_goods';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function addArticleGoods($data)
    {
        return $this->create($data);
    }

    public function removeArticleGoods($wherein, $id)
    {
        try {
            $this->where('article_id', '=', $id)
                ->whereIn('goods_id', $wherein)
                ->delete();
        } catch (\Exception $e) {
            echo $e->getMessage();
        }
    }
}
