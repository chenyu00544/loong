<?php

namespace App\Http\Controllers\Shop\Api;

use App\Repositories\RegionsRepository;
use Illuminate\Http\Request;

class RegionsController extends CommonController
{
    protected $regionsRepository;
    public function __construct(RegionsRepository $regionsRepository)
    {
        $this->regionsRepository = $regionsRepository;
    }
    
    public function getCountries(Request $req)
    {
        $data = $this->regionsRepository->getRegions($req->type,$req->parent);
        return $this->ApiReturn($data);
    }
}
