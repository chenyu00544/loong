<?php

namespace App\Http\Controllers\Shop\Home;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\View;

class CommonController extends Controller
{
    public $user;
    public function __construct()
    {
        View::share('v', time());
    }
}
