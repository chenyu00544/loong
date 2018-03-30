<?php

namespace App\Http\Controllers\Shop\Api;

use App\Http\Models\Shop\RegionsModel;
use Illuminate\Http\Request;

class RegionsController extends CommonController
{
    public function getCountries(Request $req)
    {
        $regions = new RegionsModel();
        //0,0 1,1-region_id 2,2-region_id
        $countries = $regions->getRegions($req['type'], $req['parent']);
        return response()->json($countries);
    }
}
