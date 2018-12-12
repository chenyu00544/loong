<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 商品分类设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\LangConfig;
use App\Facades\Verifiable;
use App\Repositories\ArticleCateRepository;
use App\Repositories\ArticleRepository;
use Illuminate\Http\Request;

class ArticleController extends CommonController
{

    protected $articleRepository;
    protected $articleCateRepository;

    public function __construct(
        ArticleRepository $articleRepository,
        ArticleCateRepository $articleCateRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('articlelist');
        $this->articleRepository = $articleRepository;
        $this->articleCateRepository = $articleCateRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $articles = $this->articleRepository->getArticlesByPage(0);
        $search['keywords'] = '';
        return view('shop.admin.article.article', compact('articles', 'search'));
    }

    public function change(Request $request)
    {
        return $this->articleRepository->changeArticle($request->except('_token'));
    }

    public function changeGoods(Request $request)
    {
        return $this->articleRepository->changeGoods($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $cates = $this->articleCateRepository->getArticleCates();
        return view('shop.admin.article.articleAdd', compact('cates'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->articleRepository->addArticle($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $articles = $this->articleRepository->getArticlesByPage($id);
        $search['keywords'] = '';
        return view('shop.admin.article.article', compact('articles', 'search'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $article = $this->articleRepository->getArticle($id);
        $cates = $this->articleCateRepository->getArticleCates();
        $parentCates = $this->articleCateRepository->getParentCate($article->cat_id);
        return view('shop.admin.article.articleEdit', compact('article', 'cates', 'parentCates'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->articleRepository->setArticle($request->except('_token', '_method', 'category_name'), $id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->articleRepository->deleteArticle($id);
    }
}
