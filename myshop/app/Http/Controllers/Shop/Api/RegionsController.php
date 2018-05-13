<?php

namespace App\Http\Controllers\Shop\Api;

use App\Facades\Token;
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

    public function enToken(){
        $userId = '123456';
        echo Token::encode();
    }

    public function deToken(){
        $userId = '123456';
    }
}
