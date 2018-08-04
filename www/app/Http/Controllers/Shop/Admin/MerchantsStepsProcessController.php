<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 店铺设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\MerchantsRepository;
use Illuminate\Http\Request;

class MerchantsStepsProcessController extends CommonController
{

    private $merchantsRepository;

    public function __construct(
        MerchantsRepository $merchantsRepository
    )
    {
        parent::__construct();
        $this->merchantsRepository = $merchantsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $nav = 'process';
        $process = $this->merchantsRepository->getMerchantsStepsProcessByPage();
        return view('shop.admin.store.process', compact('process', 'nav'));
    }

    public function change(Request $request)
    {
        return $this->merchantsRepository->merchantsStepsProcessChange($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.store.processAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["process_title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->merchantsRepository->addMerchantsStepsProcess($request->except('_token'));
        dd($re->id);
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $msp = $this->merchantsRepository->getMerchantsStepsProcess($id);
        return view('shop.admin.store.processEdit', compact('msp'));
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
        $ver = Verifiable::Validator($request->all(), ["process_title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->merchantsRepository->setMerchantsStepsProcess($request->except('_token','_method'), $id);
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
        return $this->merchantsRepository->delMerchantsStepsProcess($id);
    }
}
