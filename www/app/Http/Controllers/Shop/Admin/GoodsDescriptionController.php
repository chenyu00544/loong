<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\GoodsDescriptionRepository;
use Illuminate\Http\Request;

class GoodsDescriptionController extends CommonController
{
    private $goodsDescriptionRepository;

    public function __construct(
        GoodsDescriptionRepository $goodsDescriptionRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('goods_description');
        $this->goodsDescriptionRepository = $goodsDescriptionRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $descs = $this->goodsDescriptionRepository->getGoodsDescriptions();
        return view('shop.admin.goodsdesc.goodsdesc', compact('descs'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.goodsdesc.goodsdescAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->goodsDescriptionRepository->addGoodsDescription($request->except('_token'));
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

    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $desc = $this->goodsDescriptionRepository->getGoodsDescription($id);
        return view('shop.admin.goodsdesc.goodsdescEdit', compact('desc'));
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
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->goodsDescriptionRepository->setGoodsDescription($request->except('_token', '_method'), $id);
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
        return $this->goodsDescriptionRepository->delGoodsDescription($id);
    }
}
