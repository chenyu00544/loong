<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 验证码设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\ShopConfig;
use App\Facades\Verifiable;
use Illuminate\Http\Request;

class CaptchaController extends CommonController
{

    public function __construct()
    {
        parent::__construct();
        $this->checkPrivilege('codesetup');
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $captcha = ShopConfig::getConfHidden(['captcha','captcha_width','captcha_height','captcha_font_size','captcha_length']);
        return view('shop.admin.code.captcha', compact('captcha'));
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
        $ver = Verifiable::Validator(
            $request->all(),
            [
                "captcha_width" => 'required',
                "captcha_height" => 'required',
                "captcha_font_size" => 'required',
                "captcha_length" => 'required'
            ]
        );
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = ShopConfig::setConfHidden($request->except('_token'));
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
        //
    }
}
