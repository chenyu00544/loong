<?php

namespace App\Http\Controllers\Shop\Home\Web;

use App\Facades\RedisCache;
use App\Http\Controllers\Shop\Home\CommonController;
use App\Repositories\Home\ArticleRepository;
use Illuminate\Http\Request;

class ArticleController extends CommonController
{

    private $articleRepository;

    public function __construct(
        ArticleRepository $articleRepository
    )
    {
        parent::__construct();
        $this->articleRepository = $articleRepository;
    }

    public function index(Request $request)
    {
    }

    public function artDetail(Request $request)
    {
        $article = $this->articleRepository->getArticleDetail($request->get("id"));
        $seo = [
            'title' => $article->title,
            'keywords' => $article->keywords,
            'description' => $article->description
        ];
        $this->seo($seo);
        return view('shop.home.web.article.detail', compact('article'));
    }
}
