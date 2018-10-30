<?php

namespace App\Http\Controllers\Shop\App;

use Illuminate\Http\Request;

class CartController extends CommonController
{

    public function __construct(
    )
    {
        parent::__construct();
    }

    public function index(Request $request)
    {
        return 1;
    }

    public function addCart(Request $request)
    {

    }

    public function setCart(Request $request)
    {

    }

    public function delCart(Request $request)
    {

    }
}
