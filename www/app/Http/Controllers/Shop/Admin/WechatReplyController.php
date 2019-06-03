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
use Illuminate\Support\Facades\View;

class WechatReplyController extends CommonController
{
    private $wechatRepository;

    public function __construct(
        WechatRepository $wechatRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wechatreply');
        View::share('backUrl', url('admin/wechatreply'));
        $this->wechatRepository = $wechatRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'subscribe';
        $subscribes = $this->wechatRepository->getWechatReplyAutos($navType);
        return view('shop.admin.wechat.wechatReply', compact('subscribes', 'navType'));
    }

    public function setWechatReplyAuto(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ['type' => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->wechatRepository->setWechatReplyAuto($request->except('_token'), $this->user);
        if($re){
            return view('shop.admin.success');
        }else{
            return view('shop.admin.failed');
        }
    }

    /**
     * Show the form for creating a new resource.
     * 关键字
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.wechat.addWechatReplyKey');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
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
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $navType = $id;
        $subscribes = $this->wechatRepository->getWechatReplyAutos($navType);
        if ($id == 'subscribe') {
            return view('shop.admin.wechat.wechatReply', compact('subscribes', 'navType'));
        } elseif ($id == 'msg') {
            return view('shop.admin.wechat.wechatReplyMsg', compact('subscribes', 'navType'));
        } else {
            return view('shop.admin.wechat.wechatReplyKey', compact('subscribes', 'navType'));
        }

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
        return view('shop.admin.wechat.setWechatReplyKey', compact('subscribe'));
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
