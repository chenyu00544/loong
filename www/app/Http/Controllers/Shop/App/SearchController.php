<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\GoodsRepository;
use Illuminate\Http\Request;

class SearchController extends CommonController
{
    private $goodsRepository;

    public function __construct(
        GoodsRepository $goodsRepository
    )
    {
        parent::__construct();
        $this->goodsRepository = $goodsRepository;
    }

    public function index(Request $request)
    {
        $res = $this->goodsRepository->getSearchByGoods($request->all());
        if($res){
            return ['code' => 0, 'msg' => '', 'data' => $res];
        }else{
            return ['code' => 1, 'msg' => '', 'data' => []];
        }
    }

    public function getSearchKeywords()
    {
        $res = $this->goodsRepository->getSearchByKeywords();
        if($res){
            return ['code' => 0, 'msg' => '', 'data' => $res];
        }else{
            return ['code' => 1, 'msg' => '', 'data' => []];
        }
    }
}
