<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 快递设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\Admin\ShippingRepository;
use Illuminate\Http\Request;

class ExpressController extends CommonController
{

    private $shippingRepository;

    public function __construct(ShippingRepository $shippingRepository)
    {
        parent::__construct();
        $this->checkPrivilege('areasetup');
        $this->shippingRepository = $shippingRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

        $shipping = $this->shippingRepository->getShippingAll();
        $typeNav = 'express';
        return view('shop.admin.shipping.express', compact('shipping', 'typeNav'));
    }

    public function install($id)
    {
        return $this->shippingRepository->addShip($id);
    }

    public function changes(Request $request)
    {
        return $this->shippingRepository->changes($request->except('_token'));
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
        $ship = $this->shippingRepository->getShipping($id);
        return view('shop.admin.shipping.expressEdit', compact('ship'));
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
        return $this->shippingRepository->deleteShip($id);
    }
}
