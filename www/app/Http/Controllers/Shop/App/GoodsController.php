<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\RedisCache;
use App\Repositories\App\GoodsRepository;
use Illuminate\Http\Request;

class GoodsController extends CommonController
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

    }

    public function detail(Request $request)
    {
        $goods_id = !empty($request->get('goods_id')) ? $request->get('goods_id') : 0;
        $data['goods_detail'] = $this->goodsRepository->getGoodsDetail($goods_id);
        return ['code' => 1, 'msg' => '', 'data' => $data];
    }
}
