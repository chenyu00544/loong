<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\GoodsRepository;
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
        $user_id = Verifiable::authorization($request);
        $data = $this->goodsRepository->getGoodsesByUserLike($request->all(), $user_id);
        if($data){
            $rep = ['code' => 0, 'msg' => '', 'data' => $data];
        }else{
            $rep = ['code' => 1, 'msg' => '', 'data' => $data];
        }
        return $rep;
    }

    public function detail(Request $request)
    {
        $goods_id = !empty($request->get('goods_id')) ? $request->get('goods_id') : 0;
        $device_id = !empty($request->get('device_id')) ? $request->get('device_id') : 0;
        $user_id = Verifiable::authorization($request);
        if(empty($goods_id)){
            return ['code' => 9, 'msg' => '商品ID不为空', 'data' => []];
        }
        $data = $this->goodsRepository->getGoodsDetail($goods_id, $user_id, $device_id);
        if($data){
            $rep = ['code' => 0, 'msg' => '', 'data' => $data];
        }else{
            $rep = ['code' => 1, 'msg' => '', 'data' => $data];
        }
        return $rep;
    }
}
