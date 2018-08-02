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
use App\Repositories\AlismsRepository;
use Illuminate\Http\Request;

class AlismsController extends CommonController
{
    private $alismsRepository;

    public function __construct(
        AlismsRepository $alismsRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('alisms');
        $this->alismsRepository = $alismsRepository;
    }
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $alisms = $this->alismsRepository->getAlismsByPage();
        $sendTime = $this->alismsRepository->getSendTime();
        return view('shop.admin.sms.alisms', compact('alisms', 'sendTime'));
    }

    public function temp(Request $request)
    {
        return $this->alismsRepository->getSendTemplate($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $sendTime = $this->alismsRepository->getSendTime();
        return view('shop.admin.sms.alismsAdd', compact('sendTime'));
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
        $re = $this->alismsRepository->addAlisms($request->except('_token'));
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
        $alisms = $this->alismsRepository->getAlisms($id);
        $sendTime = $this->alismsRepository->getSendTime();
        return view('shop.admin.sms.alismsEdit', compact('alisms', 'sendTime'));
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
        $re = $this->alismsRepository->setAlisms($request->except('_token', '_method'), $id);
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
        return $this->alismsRepository->delAlisms($id);
    }
}
