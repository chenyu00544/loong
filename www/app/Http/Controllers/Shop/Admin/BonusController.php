<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 红包设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\BonusRepository;
use Illuminate\Http\Request;

class BonusController extends CommonController
{
    private $bonusRepository;

    public function __construct(
        BonusRepository $bonusRepository
    )
    {
        parent::__construct();
        $this->bonusRepository = $bonusRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seller = 'selfsale';
        $search['keywords'] = $request->get('keywords');
        $bonuses = $this->bonusRepository->getBonusByPage($search, $seller);
        return view('shop.admin.faat.bonus', compact('seller', 'search', 'bonuses'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $now_date = $this->now_date;
        return view('shop.admin.faat.bonusAdd', compact('now_date'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["type_name" => 'required', "type_money" => 'required', "min_goods_amount" => 'required', "use_start_end_date" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->bonusRepository->addBonus($request->except('_token'), $this->user);
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
        $bonuses = $this->bonusRepository->getBonusByPage($search, $seller);
        return view('shop.admin.faat.bonus', compact('seller', 'search', 'bonuses'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $bonus = $this->bonusRepository->getBonus($id);
        return view('shop.admin.faat.bonusEdit', compact('bonus'));
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
        $ver = Verifiable::Validator($request->all(), ["type_name" => 'required', "type_money" => 'required', "min_goods_amount" => 'required', "use_start_end_date" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->bonusRepository->setBonus($request->except('_token', '_method'), $id);
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
        return $this->bonusRepository->delBonus($id);
    }
}
