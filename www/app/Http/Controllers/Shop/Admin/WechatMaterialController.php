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

class WechatMaterialController extends CommonController
{
    private $wechatRepository;

    public function __construct(
        WechatRepository $wechatRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('wechatmaterial');
        $this->wechatRepository = $wechatRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $navType = 'news';
        $materials = $this->wechatRepository->getWechatMaterialByPage();
        return view('shop.admin.wechat.wechatMaterial', compact('materials', 'navType'));
    }


    public function addNews()
    {
        return view('shop.admin.wechat.addWechatMaterialNews');
    }

    public function setNews($id)
    {
        return view('shop.admin.wechat.setWechatMaterialNews');
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
        dd($request->except('_token'));
        $ver = Verifiable::Validator($request->all(), ["appid" => 'required']);
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
        if ($id == 'subscribe') {
            $subscribe = $this->wechatRepository->getWechatReplyAuto();
            return view('shop.admin.wechat.wechatReply', compact('subscribe', 'navType'));
        } elseif ($id == 'autoreply') {

        } else {

        }

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
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
