<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\RedisCache;
use App\Repositories\App\AdRepository;
use App\Repositories\App\GoodsRepository;
use Illuminate\Http\Request;

class IndexController extends CommonController
{

    private $adRepository;
    private $goodsRepository;

    public function __construct(
        AdRepository $adRepository,
        GoodsRepository $goodsRepository
    )
    {
        parent::__construct();
        $this->adRepository = $adRepository;
        $this->goodsRepository = $goodsRepository;
    }

    public function index(Request $request)
    {

        $data = RedisCache::get('app_index_data');
        if (!empty($data)) {
            return $data;
        }
        //获取广告数据
        $data['adses'] = $this->adRepository->getAdPositionAndAds();
        //获取推荐的产品数据

        $data['goodses'] = $this->goodsRepository->getBestGoods(1);
        RedisCache::get('app_index_data', $data, 60);
        return ['code' => 1, 'msg' => '', 'data' => $data];
    }

    public function loadmore(Request $request)
    {
        $page = empty($request->get('page')) ? 1 : $request->get('page');
        $data['goodses'] = $this->goodsRepository->getBestGoods($page);
        return ['code' => 1, 'msg' => '', 'data' => $data];
    }
}
