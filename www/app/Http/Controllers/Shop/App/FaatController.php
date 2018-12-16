<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\RedisCache;
use App\Repositories\App\BrandRepository;
use App\Repositories\App\FaatRepository;
use Illuminate\Http\Request;

class FaatController extends CommonController
{

    private $faatRepository;
    private $brandRepository;

    public function __construct(
        FaatRepository $faatRepository,
        BrandRepository $brandRepository
    )
    {
        parent::__construct();
        $this->faatRepository = $faatRepository;
        $this->brandRepository = $brandRepository;
    }

    public function index(Request $request)
    {
        $id = $request->get('id');
        $re = $this->faatRepository->getFaatByGroupId($id);
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }

    public function brand(Request $request)
    {
        $id = $request->get('id');
        $re = $this->brandRepository->getBrandsByGoods($id);
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }
}
