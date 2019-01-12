<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\BrandRepository;
use App\Repositories\App\CollectRepository;
use Illuminate\Http\Request;

class BrandController extends CommonController
{
    private $brandRepository;

    public function __construct(
        BrandRepository $brandRepository
    )
    {
        parent::__construct();
        $this->brandRepository = $brandRepository;
    }

    public function index(Request $request)
    {
        $data = $this->brandRepository->getBrandByGoodses($request->all());
        return $this->apiReturn($data);
    }
}
