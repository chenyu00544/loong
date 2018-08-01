<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 小程序
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Repositories\WxappRepository;
use Illuminate\Http\Request;

class WxappConfigController extends CommonController
{
    private $wxappRepository;

    public function __construct(
        WxappRepository $wxappRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wxappconfig');
        $this->wxappRepository = $wxappRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $weapp = $this->wxappRepository->getWeapp($this->user);
        $ru_id = $this->user->ru_id;
        return view('shop.admin.wechat.wxappConfig', compact('weapp', 'ru_id'));
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
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["appid" => 'required', "appsecret" => 'required', "orgid" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->addWechat($request->except('_token'), $this->user);
        return view('shop.admin.success');
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
        $ver = Verifiable::Validator($request->all(), ["appid" => 'required', "appsecret" => 'required', "orgid" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->setWechat($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
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
