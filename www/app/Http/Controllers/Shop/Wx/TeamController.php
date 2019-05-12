<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\OrderRepository;
use App\Repositories\Wxapp\TeamRepository;
use Illuminate\Http\Request;

class TeamController extends CommonController
{
    private $teamRepository;
    private $orderRepository;

    public function __construct(
        TeamRepository $teamRepository,
        OrderRepository $orderRepository
    )
    {
        parent::__construct();
        $this->teamRepository = $teamRepository;
        $this->orderRepository = $orderRepository;
    }

    public function teamNav(Request $request)
    {
        $res = $this->teamRepository->getTeamNav();
        return $this->apiReturn($res);
    }

    public function team(Request $request)
    {
        $res = $this->teamRepository->getTeam($request->all());
        return $this->apiReturn($res);
    }

    public function teamGoods(Request $request)
    {
        $uid = Verifiable::authorization($request);
        $res = $this->teamRepository->getTeamGoods($request->all(), $uid);
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
            $res = $this->teamRepository->setTeamBuy($request->all(), $uid);
            if (is_numeric($res)) {
                return $this->apiReturn(array(), '', $res);
            } else {
                return $this->apiReturn($res);
            }
        } else {
            return $this->apiReturn(array(), '未登陆', 10002);
        }
    }

    public function teamOrders(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $res = $this->teamRepository->getTeamOrders($request->all(), $uid);
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
        $res = $this->teamRepository->getTeamRank($request->all());
        if (is_numeric($res)) {
            return $this->apiReturn(array(), '', $res);
        } else {
            return $this->apiReturn($res);
        }
    }

    public function teamWait(Request $request)
    {
        //验证参数
        $this->validate($request, [
            'team_id' => 'required|int',  //开团id
            'user_id' => 'required|int'   //开团会员id
        ]);
        $uid = Verifiable::authorization($request);
        if ($uid <= 0) {
            return $this->apiReturn(array(), '未登陆', 10002);
        }

        $list = $this->teamRepository->teamWait($uid, $request->get('team_id'), $request->get('user_id'));

        return $this->apiReturn($list);
    }
}
