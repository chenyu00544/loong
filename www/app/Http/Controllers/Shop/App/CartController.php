<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\GoodsRepository;
use Illuminate\Http\Request;

class CartController extends CommonController
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
        return 1;
    }

    public function addCart(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->goodsRepository->addCart($request->all(), $uid);
            if($re){
                return ['code' => 0, 'msg' => '添加成功'];
            }else{
                return ['code' => 1, 'msg' => '已经添加'];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    public function setCart(Request $request)
    {

    }

    public function delCart(Request $request)
    {

    }
}
