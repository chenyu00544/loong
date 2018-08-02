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
use App\Repositories\AlidayuRepository;
use Illuminate\Http\Request;

class AlidayuController extends CommonController
{

    private $alidayuRepository;

    public function __construct(
        AlidayuRepository $alidayuRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('alidayu');
        $this->alidayuRepository = $alidayuRepository;
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $alidayu = $this->alidayuRepository->getAlidayuByPage();
        $sendTime = $this->alidayuRepository->getSendTime();
        return view('shop.admin.sms.alidayu', compact('alidayu', 'sendTime'));
    }

    public function temp(Request $request)
    {
        return $this->alidayuRepository->getSendTemplate($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $sendTime = $this->alidayuRepository->getSendTime();
        return view('shop.admin.sms.alidayuAdd', compact('sendTime'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["set_sign" => 'required', "temp_id" => 'required', "temp_content" => 'required', "send_time" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->alidayuRepository->addAlidayu($request->except('_token'));
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
        $alidayu = $this->alidayuRepository->getAlidayu($id);
        $sendTime = $this->alidayuRepository->getSendTime();
        return view('shop.admin.sms.alidayuEdit', compact('alidayu', 'sendTime'));
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
        $ver = Verifiable::Validator($request->all(), ["set_sign" => 'required', "temp_id" => 'required', "temp_content" => 'required', "send_time" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->alidayuRepository->setAlidayu($request->except('_token', '_method'), $id);
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
        return $this->alidayuRepository->delAlidayu($id);
    }
}
