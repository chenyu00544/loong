<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
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

    public function adAdd($id)
    {
        $type = $id;
        $adsposes = $this->adRepository->getAdPoses($id);
        $now_date = $this->now_date;
        return view('shop.admin.ads.adsAdd', compact('adsposes', 'now_date', 'type'));
    }

    public function adEdit($id, $ad_type)
    {
        $type = $ad_type;
        $adsposes = $this->adRepository->getAdPoses($type);
        $adInfo = $this->adRepository->getAd($id);
        return view('shop.admin.ads.adsEdit', compact('adsposes', 'type', 'adInfo'));
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
        $ver = Verifiable::Validator($request->all(), ["ad_name" => 'required', "position_id" => 'required', "ad_img" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->addAd($request->except('_token'));
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
        $ver = Verifiable::Validator($request->all(), ["ad_name" => 'required', "position_id" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->setAd($request->except('_token', '_method'), $id);
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
        return $this->adRepository->delAd($id);
    }
}
