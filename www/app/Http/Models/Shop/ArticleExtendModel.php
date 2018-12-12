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

class ArticleExtendModel extends Model
{
    protected $table = 'article_extend';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function addArticleExtend($data)
    {
        return $this->create($data);
    }
}
