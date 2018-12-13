<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Home;

use App\Contracts\ArticleRepositoryInterface;
use App\Facades\RedisCache;
use App\Http\Models\Home\ArticleModel;

class ArticleRepository implements ArticleRepositoryInterface
{

    protected $articleModel;

    public function __construct(
        ArticleModel $articleModel
    )
    {
        $this->articleModel = $articleModel;
    }

    public function getArticleDetail($id)
    {
        $data = RedisCache::get('article_' . $id);
        if (!empty($data)) {
            return $data;
        }
        $where['article_id'] = $id;
        $re = $this->articleModel->getArticle($where);
        RedisCache::setex('article_' . $id, $re, 60 * 24);
        return $re;
    }


}