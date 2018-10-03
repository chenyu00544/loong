<?php

namespace App\Http\Controllers\Shop\Api;

use App\Facades\Captcha;

class CaptchaController extends CommonController
{

    public function __construct()
    {

    }

    public function index($id)
    {
        Captcha::entry();
    }
}
