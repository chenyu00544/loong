<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\RegionsRepository;
use App\Repositories\TransportRepository;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Cache;

class TransportController extends CommonController
{
    private $transportRepository;
    private $regionsRepository;

    public function __construct(
        TransportRepository $transportRepository,
        RegionsRepository $regionsRepository
    )
    {
        parent::__construct();
        $this->transportRepository = $transportRepository;
        $this->regionsRepository = $regionsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $typeNav = 'transport';
        $transports = $this->transportRepository->getTransportAll();
        return view('shop.admin.shipping.transport', compact('typeNav', 'transports'));
    }

    public function changes(Request $request)
    {
        return $this->transportRepository->setTransport($request->except('_token'));
    }

    public function regions($id, $tid)
    {
        $regions = $this->regionsRepository->getRegionsRange();
        $regions = $this->transportRepository->getTransportExtendByTid($id, $tid, $regions);
        return view('shop.admin.shipping.modal.regions', compact('regions', 'id'));
    }

    public function express($id, $tid)
    {
        $express = $this->transportRepository->getTransportExpressByTid($id, $tid);
        return view('shop.admin.shipping.modal.express', compact('express', 'id'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $userId = Cache::get('adminUser' . md5($ip) . $uid)->user_id;
        $transportInfo = $this->transportRepository->getTransportInfo(0, $userId);
        return view('shop.admin.shipping.transportAdd', compact('transportInfo', 'regions'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["shipping_title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->transportRepository->addTransport($request->except('_token'));
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
    public function edit(Request $request, $id)
    {
        $uid = $request->cookie('user_id');
        $ip = $request->getClientIp();
        $userId = Cache::get('adminUser' . md5($ip) . $uid)->user_id;
        $transportInfo = $this->transportRepository->getTransportInfo($id, $userId);
        return view('shop.admin.shipping.transportEdit', compact('transportInfo'));
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
        $ver = Verifiable::Validator($request->all(), ["shipping_title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->transportRepository->upDateTransport($request->except('_token', '_method'), $id);
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
        return $this->transportRepository->deleteTransport($id);
    }
}
