<?php

namespace App\Http\Controllers\Shop\Api;

use App\Facades\Captcha;
use Illuminate\Http\Request;

class CaptchaController extends CommonController
{

    public function __construct()
    {

    }

    public function index(Request $request)
    {
        Captcha::entry();
    }
}
