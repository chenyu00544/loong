<?php

namespace App\Http\Controllers\Shop\Home;

use Illuminate\Http\Request;

class IndexController extends CommonController
{

    public function index(Request $request)
    {
        return view('shop.home.index.index');
    }

    public function test(Request $request)
    {
        dd($request->file('file'));
    }
}
