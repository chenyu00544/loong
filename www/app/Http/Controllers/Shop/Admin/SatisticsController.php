<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 广告设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Regions;
use App\Repositories\OrderRepository;
use App\Repositories\SatisticsRepository;
use Illuminate\Http\Request;

class SatisticsController extends CommonController
{
    private $date;
    private $rdate;
    private $satisticsRepository;

    public function __construct(
        SatisticsRepository $satisticsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('satistics');
        $this->satisticsRepository = $satisticsRepository;
        $k = 4;
        $data = [];
        for ($i = 0; $i <= $k; $i++) {
            $data[] = date('Y-m', time() - ((86400 * 30) * ($k - $i)));
        }
        $this->date = $data;
        $this->rdate = date('Y-m-d', time() - 84600 * 7) . ' 08:00:00～' . date('Y-m-d', time() + 84600) . ' 08:00:00';
    }

    //订单统计
    public function order(Request $request)
    {
        $now_date = $this->rdate;
        $date = $this->date;
        $ordersat = $this->satisticsRepository->getSatistics(['type' => 'order', 'range' => $now_date, 'opt' => 'range']);
        $sumAmount = $this->satisticsRepository->satisticsAmount();
        $countOrder = $this->satisticsRepository->satisticsOrderNum();
        return view('shop.admin.satistics.orderSat', compact('now_date', 'date', 'ordersat', 'sumAmount', 'countOrder'));
    }

    //会员统计
    public function user(Request $request)
    {
        $usernav = 'user';
        $now_date = $this->rdate;
        $date = $this->date;
        $usersat = $this->satisticsRepository->getSatistics(['type' => 'user', 'range' => $now_date, 'opt' => 'range']);
        return view('shop.admin.satistics.usersSat', compact('now_date', 'date', 'usernav', 'usersat'));
    }

    //会员区域统计
    public function userArea(Request $request)
    {
        $usernav = 'area';
        $userareasat = $this->satisticsRepository->getSatistics(['type' => 'userarea', 'opt' => 'user']);
        return view('shop.admin.satistics.usersAreaSat', compact('usernav', 'userareasat'));
    }

    //会员区域统计
    public function userRank(Request $request)
    {
        $usernav = 'rank';
        $userranksat = $this->satisticsRepository->getSatistics(['type' => 'userrank', 'opt' => 'rank']);
        return view('shop.admin.satistics.usersRankSat', compact('usernav', 'userranksat'));
    }

    //会员消费统计
    public function userConsumption(Request $request)
    {
        $usernav = 'consumption';
        $userconsumptionsat = $this->satisticsRepository->getSatistics(['type' => 'userconsumption', 'opt' => 'consumption']);
        return view('shop.admin.satistics.usersConsumptionSat', compact('usernav', 'userconsumptionsat'));
    }

    //行业统计
    public function industry(Request $request)
    {

    }

    //ajax获取统计数据
    public function getSatistics(Request $request)
    {
        return $this->satisticsRepository->getSatistics($request->except('_token'));
    }

    public function getGeoJson(Request $request)
    {
        return Regions::getGeoJson();
    }

}
