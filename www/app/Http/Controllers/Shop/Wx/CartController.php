<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\GoodsRepository;
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
        $uid = Verifiable::authorization($request);
        $re = $this->goodsRepository->cartList($request->all(), $uid);
        return $this->apiReturn($re);
    }

    public function addCart(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $code = $this->goodsRepository->addCart($request->all(), $uid);
            if (is_array($code)) {
                return $this->apiReturn($code);
            } else {
                return $this->apiReturn([], '', $code);
            }
        } else {
            return $this->apiReturn([], '', 10002);
        }
    }

    public function setCart(Request $request)
    {
        $re = $this->goodsRepository->setCart($request->all());
        if ($re) {
            return $this->apiReturn($re);
        } else {
            return $this->apiReturn([], '',30003);
        }
    }

    public function delCart(Request $request)
    {
        $re = $this->goodsRepository->delCart($request->all());
        if ($re) {
            return $this->apiReturn($re);
        } else {
            return $this->apiReturn([], '',10003);
        }
    }
}
