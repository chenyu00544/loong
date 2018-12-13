<?php

namespace App\Http\Controllers\Shop\Home\Pc;

use App\Http\Controllers\Shop\Home\CommonController;
use Illuminate\Http\Request;

class ArticleController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {

    }

    public function artDetail(Request $request)
    {
        dd($request->get("id"));
        return view('shop.home.web.article.detail');
    }
}
