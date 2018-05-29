<?php

namespace App\Modules\Api\Controllers\V2;

use App\Modules\Api\Foundation\Controller;

class HomeController extends Controller
{
    public function actionIndex()
    {
        $this->resp(['foo' => 'bar']);
    }

}