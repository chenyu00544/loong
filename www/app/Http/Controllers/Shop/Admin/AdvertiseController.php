<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\AdRepository;
use Illuminate\Http\Request;

class AdvertiseController extends CommonController
{
    private $adRepository;

    public function __construct(
        AdRepository $adRepository
    )
    {
        parent::__construct();
        $this->adRepository = $adRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $type = 'pc';
        $search['keywords'] = $request->get('keywords');
        $search['advance_date'] = $request->get('advance_date');
        $ads = $this->adRepository->getAdByPage($type, $search);
        return view('shop.admin.ads.ads', compact('type', 'search', 'ads'));
    }

    public function change(Request $request)
    {
        return $this->adRepository->adChange($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
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
    public function show(Request $request, $id)
    {
        $type = $id ? $id : 'pc';
        $search['keywords'] = $request->get('keywords');
        $search['advance_date'] = $request->get('advance_date');
        $ads = $this->adRepository->getAdByPage($type, $search);
        return view('shop.admin.ads.ads', compact('type', 'search', 'ads'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
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
        return $this->adRepository->delAd($id);
    }
}
