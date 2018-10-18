<?php

namespace App\Http\Controllers\Shop\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;

class CommonController extends Controller
{
    public function ApiReturn($data = [])
    {
        return response()->json(['state' => 0, 'data' => $data]);
    }

    public function errorReturn($data = [])
    {
        return response()->json(['state' => 1, 'data' => $data]);
    }
}
