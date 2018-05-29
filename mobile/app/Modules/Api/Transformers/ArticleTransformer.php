<?php

namespace App\Modules\Api\Transformers;

use App\Models\Article;

class ArticleTransformer
{
    public function transform(Article $article)
    {
        return [
            'id' => $article->article_id,
            'title' => $article->title,
        ];
    }
}
