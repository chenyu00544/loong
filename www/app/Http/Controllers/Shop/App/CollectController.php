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

    public function collectBrand(Request $request)
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

    public function collectStore(Request $request)
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
}
