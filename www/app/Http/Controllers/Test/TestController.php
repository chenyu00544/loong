<?php

namespace App\Http\Controllers\Test;

use App\Http\Controllers\Controller;
use App\Models\MoyooAnli;

class TestController extends Controller
{
    private $anli;
    public function __construct(
        MoyooAnli $anli
    )
    {
        $this->anli = $anli;
    }

    public function index()
    {
    }
}
