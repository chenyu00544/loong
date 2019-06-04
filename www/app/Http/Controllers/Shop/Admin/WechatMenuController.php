<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:公众号
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\WechatRepository;
use Illuminate\Http\Request;

class WechatMenuController extends CommonController
{
    private $wechatRepository;

    public function __construct(
        WechatRepository $wechatRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wechatmenu');
        $this->wechatRepository = $wechatRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $menus = $this->wechatRepository->getWechatMenus();
        return view('shop.admin.wechat.wechatMenu', compact('menus'));
    }

    public function change(Request $request)
    {

    }

    public function createMenu()
    {

    }

    /**
     * Show the form for creating a new resource.
     * 关键字
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $menus = $this->wechatRepository->getWechatMenus();
        return view('shop.admin.wechat.addWechatMenu', compact('menus'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["name" => 'required',"type" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->addWechat($request->except('_token'), $this->user);
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

    }

    /**
     * Show the form for editing the specified resource.
     * 关键字
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $subscribe = $this->wechatRepository->getWechatReplyAuto($id);
        return view('shop.admin.wechat.setWechatMenu', compact('subscribe'));
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

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return $this->wechatRepository->delWechatReplyAuto($id);
    }
}
