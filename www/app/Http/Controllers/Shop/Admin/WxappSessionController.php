<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 小程序
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\WxappRepository;
use Illuminate\Http\Request;

class WxappSessionController extends CommonController
{
    private $wxappRepository;

    public function __construct(
        WxappRepository $wxappRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wxappsession');
        $this->wxappRepository = $wxappRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $search['keywords'] = $request->get('keywords');
        $search['page'] = $request->get('page')?$request->get('page'):10;
        $wxappes = $this->wxappRepository->getWxappSessionByPage($request->all());
        // fixme 翻页使用
        $wxappes->appends([
            'keywords' => $request->get('keywords'),
            'page' => $request->get('page')?$request->get('page'):10
        ]);
        return view('shop.admin.wxapp.wxappSession', compact('wxappes', 'search'));
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
        $ver = Verifiable::Validator($request->all(), ["wx_appid" => 'required', "wx_appsecret" => 'required', "wx_mch_id" => 'required', "wx_mch_key" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wxappRepository->addWxapp($request->except('_token'), $this->user);
        return view('shop.admin.success');
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
        return view('shop.admin.wxapp.modal.sendMsg', compact('id'));
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
        $ver = Verifiable::Validator($request->all(), ["open_id" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wxappRepository->setWxapp($request->except('_token', '_method'), $id);
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
        $re = ['code' => 1, 'msg' => '成功'];
        return $re;
    }
}
