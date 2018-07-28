<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 广告设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

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
        $now_date = $this->rdate;
        $date = $this->date;
        $ordersat = $this->satisticsRepository->getSatistics(['type' => 'order', 'range' => $now_date, 'opt' => 'range']);
        $sumAmount = $this->satisticsRepository->satisticsAmount();
        $countOrder = $this->satisticsRepository->satisticsOrderNum();
        return view('shop.admin.satistics.usersSat', compact('now_date', 'date', 'ordersat', 'sumAmount', 'countOrder'));
    }

    public function getSatistics(Request $request)
    {
        return $this->satisticsRepository->getSatistics($request->except('_token'));
    }
}
