<?php

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\Verifiable;
use App\Repositories\Admin\NotifyRepository;
use Illuminate\Http\Request;

class NotifyController extends CommonController
{
    private $notifyRepository;

    public function __construct(
        NotifyRepository $notifyRepository
    )
    {
        parent::__construct();
        $this->checkPrivilege('notify');
        $this->notifyRepository = $notifyRepository;
    }

    public function index(Request $request)
    {
        $type = 'app';
        $notifies = $this->notifyRepository->getNotifiesByPage($type);
        return view('shop.admin.notify.notify', compact('type', 'notifies'));
    }

    public function change(Request $request)
    {
        return $this->notifyRepository->change($request->except('_token'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('shop.admin.notify.notifyAdd');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->notifyRepository->addNotify($request->except('_token'));
        return view('shop.admin.success');
    }

    /**
     * Display the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $type = $id;
        $notifies = $this->notifyRepository->getNotifiesByPage($type);
        return view('shop.admin.notify.notify', compact('type', 'notifies'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $notify = $this->notifyRepository->getNotify($id);
        return view('shop.admin.notify.notifyEdit', compact('notify'));
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
        $ver = Verifiable::Validator($request->all(), ["title" => 'required']);
        if (!$ver->passes()) {
            return view('shop.admin.failed');
        }
        $re = $this->notifyRepository->setNotify($request->except('_token', '_method'), $id);
        return view('shop.admin.success');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(Request $request, $id)
    {
        return $this->notifyRepository->delNotify($request->get('img'), $id);
    }
}
