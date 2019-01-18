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
use App\Repositories\Admin\SecondKillRepository;
use Illuminate\Http\Request;

class SecondKillTimeBucketController extends CommonController
{

    private $secondKillRepository;

    public function __construct(
        SecondKillRepository $secondKillRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('seckill');
        $this->secondKillRepository = $secondKillRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seckilltimes = $this->secondKillRepository->getSecondKillTimeBuckets();
        return view('shop.admin.secondkill.seckillTime', compact('seckilltimes'));
    }

    public function change(Request $request)
    {

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.secondkill.seckillTimeAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->secondKillRepository->addSecondKillTimeBucket($request->except('_token'));
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
        $seller = $id;
        $search['keywords'] = $request->get('keywords');
        $seckills = $this->secondKillRepository->getSecondKillByPage($seller, $search);
        return view('shop.admin.secondkill.seckill', compact('seller', 'seckills', 'search'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $seckillTime = $this->secondKillRepository->getSecondKillTimeBucket($id);
        return view('shop.admin.secondkill.seckillTimeEdit', compact('seckillTime'));
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
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->secondKillRepository->setSecondKillTimeBucket($request->except('_token', '_method'), $id);
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
        return $this->secondKillRepository->delSecondKillTimeBucket($id);
    }
}
