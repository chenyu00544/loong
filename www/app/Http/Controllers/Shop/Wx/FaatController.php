<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\BrandRepository;
use App\Repositories\Wxapp\FaatRepository;
use Illuminate\Http\Request;

class FaatController extends CommonController
{

    private $faatRepository;
    private $brandRepository;

    public function __construct(
        FaatRepository $faatRepository,
        BrandRepository $brandRepository
    )
    {
        parent::__construct();
        $this->faatRepository = $faatRepository;
        $this->brandRepository = $brandRepository;
    }

    public function index(Request $request)
    {
        $id = $request->get('id');
        $re = $this->faatRepository->getFaatByGroupId($id);
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }

    public function brand(Request $request)
    {
        $id = $request->get('id');
        $re = $this->brandRepository->getBrandsByGoods($id);
        return $this->apiReturn($re);
    }

    public function secondKill(Request $request)
    {
        $res = $this->faatRepository->getSecondKill();
        return $this->apiReturn($res);
    }

    public function groupBuy(Request $request)
    {
        $res = $this->faatRepository->getGroupBuy();
        return $this->apiReturn($res);
    }

    public function teamNav(Request $request)
    {
        $res = $this->faatRepository->getTeamNav();
        return $this->apiReturn($res);
    }

    public function team(Request $request)
    {
        $res = $this->faatRepository->getTeam($request->all());
        return $this->apiReturn($res);
    }

    public function teamGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        $res = $this->faatRepository->getTeamGoods($request->all(), $uid);
        if (is_numeric($res)) {
            return $this->apiReturn(array(),'', $res);
        } else {
            return $this->apiReturn($res);
        }
    }

    public function teamBuy(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $res = $this->faatRepository->setTeamBuy($request->all(), $uid);
            if (is_numeric($res)) {
                return $this->apiReturn(array(), '', $res);
            } else {
                return $this->apiReturn($res);
            }
        } else {
            return $this->apiReturn(array(), '未登陆', 10002);
        }
    }

    public function teamOrder(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $res = $this->faatRepository->getTeamOrder($request->all(), $uid);
            if (is_numeric($res)) {
                return $this->apiReturn(array(), '', $res);
            } else {
                return $this->apiReturn($res);
            }
        } else {
            return $this->apiReturn(array(), '未登陆', 10002);
        }
    }

    public function teamRank(Request $request)
    {
        $res = $this->faatRepository->getTeamRank($request->all());
        if (is_numeric($res)) {
            return $this->apiReturn(array(), '', $res);
        } else {
            return $this->apiReturn($res);
        }
    }
}
