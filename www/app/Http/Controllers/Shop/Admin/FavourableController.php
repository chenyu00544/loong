<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 优惠设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\FavourableActivityRepository;
use Illuminate\Http\Request;

class FavourableController extends CommonController
{

    private $favourableActivityRepository;

    public function __construct(
        FavourableActivityRepository $favourableActivityRepository
    )
    {
        parent::__construct();
        $this->favourableActivityRepository = $favourableActivityRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $seller = 'selfsale';
        $search['keywords'] = $request->get('keywords');
        $faats = $this->favourableActivityRepository->getFavourableActivityByPage($search, $seller);
        return view('shop.admin.faat.favourableActivity', compact('seller', 'search', 'faats'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $now_date = $this->now_date;
        return view('shop.admin.faat.favourableActivityAdd', compact('now_date'));
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
        $seller = $id;
        $search['keywords'] = $request->get('keywords');
        $faats = $this->favourableActivityRepository->getFavourableActivityByPage($search, $seller);
        return view('shop.admin.faat.favourableActivity', compact('seller', 'search', 'faats'));
    }

    public function change(Request $request)
    {
        return $this->favourableActivityRepository->change($request->except('_token'));
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
        return $this->favourableActivityRepository->delFavourableActivity($id);
    }
}
