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

class AdvertisePositionController extends CommonController
{
    private $adRepository;

    public function __construct(
        AdRepository $adRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('ad_position');
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
        $adPoses = $this->adRepository->getAdPosByPage($type, $search);
        return view('shop.admin.ads.position', compact('type', 'search', 'adPoses'));
    }

    public function change(Request $request)
    {
        return $this->adRepository->setAdPos(['sort' => $request->get('sort')], $request->get('id'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $daTypes = $this->adRepository->getAdTypes();
        return view('shop.admin.ads.positionAdd', compact('daTypes'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["position_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->addAdPos($request->except('_token'));
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
        $adPoses = $this->adRepository->getAdPosByPage($type, $search);
        return view('shop.admin.ads.position', compact('type', 'search', 'adPoses'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $adspos = $this->adRepository->getAdPos($id);
        $daTypes = $this->adRepository->getAdTypes();
        return view('shop.admin.ads.positionEdit', compact('adspos', 'daTypes'));
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
        $ver = Verifiable::Validator($request->all(), ["position_name" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->adRepository->setAdPos($request->except('_token', '_method'), $id);
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
        return $this->adRepository->delAdPos($id);
    }
}
