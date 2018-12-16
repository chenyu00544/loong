<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 广告位置设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\AdRepository;
use Illuminate\Http\Request;

class AdvertiseTypeController extends CommonController
{

    private $adRepository;

    public function __construct(
        AdRepository $adRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('ad_type');
        $this->adRepository = $adRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $adTypes = $this->adRepository->getAdTypeByPage();
        return view('shop.admin.ads.adsType', compact('adTypes'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.ads.adsTypeAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["type" => 'required',"alias" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->addAdType($request->except('_token'));
        $back_url = $this->success('admin/adstype');
        return view('shop.admin.success', compact('back_url'));
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
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
        $adType = $this->adRepository->getAdType($id);
        return view('shop.admin.ads.adsTypeEdit', compact('adType'));
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
        $ver = Verifiable::Validator($request->all(), ["type" => 'required',"alias" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->setAdType($request->except('_token', '_method'), $id);
        $back_url = $this->success('admin/adstype');
        return view('shop.admin.success', compact('back_url'));
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->adRepository->delAdType($id);
    }
}
