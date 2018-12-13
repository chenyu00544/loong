<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 会员地址功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\RegionsRepository;
use App\Repositories\Admin\UserAddressRepository;
use Illuminate\Http\Request;

class UserAddressController extends CommonController
{
    private $userAddressRepository;
    private $regionsRepository;

    public function __construct(
        UserAddressRepository $userAddressRepository,
        RegionsRepository $regionsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('members');
        $this->userAddressRepository = $userAddressRepository;
        $this->regionsRepository = $regionsRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

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
        $address = $this->userAddressRepository->getUserAddress($id);
        $regions = $this->regionsRepository->getRegions(1, 1);
        $c_regions = $this->regionsRepository->getRegions(2, $address->province);
        $d_regions = $this->regionsRepository->getRegions(3, $address->city);
        return view('shop.admin.address.addressEdit', compact('address', 'regions', 'c_regions', 'd_regions'));
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
        $ver = Verifiable::Validator($request->all(), ['consignee'=>'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->userAddressRepository->setUserAddress($request->except('_token', '_method'), $id);
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
        return $this->userAddressRepository->delUserAddress($id);
    }
}
