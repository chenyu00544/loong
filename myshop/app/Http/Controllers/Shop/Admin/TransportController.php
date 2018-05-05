<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\RegionsRepository;
use App\Repositories\TransportRepository;
use Illuminate\Http\Request;

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

    public function regions($id)
    {
        $regions = $this->regionsRepository->getRegionsRange();
        return view('shop.admin.shipping.modal.regions', compact('regions', 'id'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $transportInfo = $this->transportRepository->getTransportInfo();
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
        //
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
        $transportInfo = $this->transportRepository->getTransportInfo($id);
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
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
