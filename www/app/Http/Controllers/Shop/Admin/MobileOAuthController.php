<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\MobileOAuthRepository;
use Illuminate\Http\Request;

class MobileOAuthController extends CommonController
{
    private $mobileOAuthRepository;

    public function __construct(
        MobileOAuthRepository $mobileOAuthRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('mobileoauth');
        $this->mobileOAuthRepository = $mobileOAuthRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $auths = $this->mobileOAuthRepository->getMobileOAuths();
        return view('shop.admin.oauth.auth', compact('auths'));
    }

    public function addAuth($id)
    {
        return view('shop.admin.oauth.authAdd', compact('id'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.oauth.authAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["appid" => 'required', "appsecret" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->mobileOAuthRepository->addMobileOAuth($request->except('_token'));
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
        $auth = $this->mobileOAuthRepository->getMobileOAuth($id);
        return view('shop.admin.oauth.authEdit', compact('auth'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {

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
        $ver = Verifiable::Validator($request->all(), ["appid" => 'required', "appsecret" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->mobileOAuthRepository->setMobileOAuth($request->except('_token', '_method'), $id);
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
        return $this->mobileOAuthRepository->delMobileOAuth($id);
    }
}
