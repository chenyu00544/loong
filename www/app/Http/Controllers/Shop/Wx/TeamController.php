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

    /**
     * 拼团子频道商品列表
     * @return mixed
     */
    public function teamNav(Request $request)
    {
        $res = $this->teamRepository->getTeamNav();
        return $this->apiReturn($res);
    }

    /**
     * 拼团频道页面
     * @return mixed
     */
    public function team(Request $request)
    {
        $res = $this->teamRepository->getTeam($request->all());
        return $this->apiReturn($res);
    }

    /**
     * 拼团商品详情
     * @param Request $request
     * @return array
     */
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

    /**
     * 拼团购买
     * @param Request $request
     * @return array
     */
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

    /**
     * 等待成团页面
     * @param Request $request
     * @return array
     */
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

    /**
     *  拼团排行商品列表
     * @return array
     */
    public function teamRanking(Request $request)
    {
        //验证参数
        $this->validate($request, [
            'page' => 'required|integer',
            'size' => 'required|integer',
            'type' => 'required|integer'
        ]);

        // 拼团排行
        $list = $this->teamRepository->teamRankingList($request->get('page'), $request->get('size'), $request->get('type'));

        return $this->apiReturn($list);
    }

    /**
     * 拼推荐商品
     * @return mixed
     */
    public function teamIsBest(Request $request)
    {
        //验证参数
        $this->validate($request, [
            'page' => 'required|integer',
            'size' => 'required|integer',
            'type' => 'required|integer',  //3
        ]);

        // 拼团排行
        $list = $this->teamRepository->teamRankingList($request->get('page'), $request->get('size'), $request->get('type'));

        return $this->apiReturn($list);
    }


    /**
     * 拼团成员
     * @return mixed
     */
    public function teamUser(Request $request)
    {
        //验证参数
        $this->validate($request, [
            'team_id' => 'required|int',  //开团id
            'page' => 'required|integer',
            'size' => 'required|integer',
        ]);

        // 拼团成员
        $list = $this->teamRepository->teamUser($request->get('team_id'), $request->get('page'), $request->get('size'));

        return $this->apiReturn($list);
    }
}
