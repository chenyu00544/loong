<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\RedisCache;
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
        $data = RedisCache::get('search_keywords');
        if (!empty($data)) {
            return $data;
        }
        $res = $this->goodsRepository->getSearchByKeywords();
        if($res){
            RedisCache::get('search_keywords', ['code' => 0, 'msg' => '', 'data' => $res], 60);
            return ['code' => 0, 'msg' => '', 'data' => $res];
        }else{
            return ['code' => 1, 'msg' => '', 'data' => []];
        }
    }

    public function getSearchFilter(Request $request)
    {
        $res = $this->goodsRepository->filterBySearch($request->all());
        if($res){
            return ['code' => 0, 'msg' => '', 'data' => $res];
        }else{
            return ['code' => 1, 'msg' => '', 'data' => []];
        }
    }
}
