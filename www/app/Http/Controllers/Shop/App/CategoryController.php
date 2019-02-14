<?php

namespace App\Http\Controllers\Shop\App;

use App\Repositories\App\AdRepository;
use App\Repositories\App\CategoryRepository;
use Illuminate\Http\Request;

class CategoryController extends CommonController
{
    private $categoryRepository;
    private $adRepository;

    public function __construct(
        CategoryRepository $categoryRepository,
        AdRepository $adRepository
    )
    {
        parent::__construct();
        $this->categoryRepository = $categoryRepository;
        $this->adRepository = $adRepository;
    }

    public function index(Request $request)
    {
        $res = $this->categoryRepository->getCates();
        return $this->apiReturn($res);
    }

    public function categoryGoods(Request $request)
    {
        $res = $this->categoryRepository->getCatesByGoods($request->all());
        $res['ads'] = $this->adRepository->getAdPositionAndAds($request->get('id'));
        return $this->apiReturn($res);
    }

    public function goodsLoadMore(Request $request)
    {
        $res = $this->categoryRepository->getCatesByGoods($request->all());
        $res['ads'] = array();
        return $this->apiReturn($res);
    }
}
