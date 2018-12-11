<?php

namespace App\Http\Controllers\Shop\App;

use App\Repositories\App\NotifyRepository;
use Illuminate\Http\Request;

class NotifyController extends CommonController
{
    private $notifyRepository;
    public function __construct(
        NotifyRepository $notifyRepository
    )
    {
        parent::__construct();
        $this->notifyRepository = $notifyRepository;
    }

    public function index(Request $request)
    {
        $re = $this->notifyRepository->getNotifies($request->all());
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }

    public function getNotify(Request $request)
    {
        $re = $this->notifyRepository->getOneNotify($request->all());
        if ($re) {
            return ['code' => 0, 'msg' => '', 'data' => $re];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }
}
