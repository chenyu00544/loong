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

class SecondKillGoodsController extends CommonController
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
    }

    public function getSecGoods($sid, $stid)
    {
        $secKillGoodses = $this->secondKillRepository->getSecondKillGoodses($sid, $stid);
        return view('shop.admin.secondkill.seckillTimeGoodsList', compact('secKillGoodses', 'sid', 'stid'));
    }

    public function change(Request $request)
    {
        return $this->secondKillRepository->secondKillGoodsChange($request->all());
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $seckilltimes = $this->secondKillRepository->getSecondKillTimeBuckets();
        return view('shop.admin.secondkill.seckillTimeGoods', compact('seckilltimes', 'id'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
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
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->secondKillRepository->delSecondKillGoodses($id);
    }
}
