<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\CollectRepository;
use Illuminate\Http\Request;

class CollectController extends CommonController
{
    private $collectRepository;

    public function __construct(
        CollectRepository $collectRepository
    )
    {
        parent::__construct();
        $this->collectRepository = $collectRepository;
    }

    //fixme 获取收藏商品列表
    public function colloectsByGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->colloectsByGoods($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else{
                return ['code' => 0, 'msg' => '', 'data' => []];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 收藏商品
    public function collectGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->collectGoods($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'is_attention' => $re['is_attention']];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 从购物车内收藏
    public function collectGoodsToCart(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->collectGoodsToCart($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'is_attention' => $re['is_attention']];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 获取收藏品牌列表
    public function collectsByBrand(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->collectBrand($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '添加成功'];
            } else {
                return ['code' => 1, 'msg' => '已经添加'];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 获取收藏店铺列表
    public function collectsByStore(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->collectStore($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '添加成功'];
            } else {
                return ['code' => 1, 'msg' => '已经添加'];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 获取浏览的列表
    public function browseByGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->browseByGoods($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else{
                return ['code' => 0, 'msg' => '', 'data' => []];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }

    //fixme 设置浏览的记录
    public function setBowse(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $re = $this->collectRepository->setBowse($request->all(), $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else{
                return ['code' => 0, 'msg' => '', 'data' => []];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }
}
