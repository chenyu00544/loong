<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\TransportRepository;
use Illuminate\Http\Request;

class TransportController extends CommonController
{
    private $transportRepository;

    public function __construct(TransportRepository $transportRepository)
    {
        parent::__construct();
        $this->transportRepository = $transportRepository;
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

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.shipping.transportAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
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
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
