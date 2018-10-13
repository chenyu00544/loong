<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\CronsRepository;
use Illuminate\Http\Request;

class CronController extends CommonController
{

    private $cronsRepository;
    private $week;
    private $task;

    public function __construct(
        CronsRepository $cronsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('cron');
        $this->cronsRepository = $cronsRepository;
        $this->week = ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期七'];
        $this->task = ['order' => '订单到期处理', 'task' => '计划任务数据处理', 'notice_to_cb' => '红包优惠券到期提醒', 'notice_to_seller' => '新订单通知(商家)', 'notice_to_user' => '订单变化通知(用户)'];
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $crons = $this->cronsRepository->getCronsByPage();
        return view('shop.admin.cron.cron', compact('crons'));
    }

    public function change(Request $request)
    {
        return $this->cronsRepository->change($request->except('_token'));
    }

    //手动执行计划任务
    public function implement(Request $request)
    {
        return $this->cronsRepository->implement($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $week = $this->week;
        $task = $this->task;
        return view('shop.admin.cron.cronAdd', compact('week', 'task'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), []);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->cronsRepository->addCron($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {

    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $cron = $this->cronsRepository->getCron($id);
        $week = $this->week;
        $task = $this->task;
        return view('shop.admin.cron.cronEdit', compact('cron', 'week', 'task'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $ver = Verifiable::Validator($request->all(), []);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->cronsRepository->setCron($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {

    }
}
