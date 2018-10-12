<?php

namespace App\Http\Controllers\Shop\Home;

use Illuminate\Http\Request;

class IndexController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
    }

    public function index(Request $request)
    {
        dd(date('S', time()));
        return view('shop.home.index.index');
    }

    public function test(Request $request)
    {
        dd($request->file('file'));
    }
}
