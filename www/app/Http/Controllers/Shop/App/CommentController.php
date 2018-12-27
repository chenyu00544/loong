<?php

namespace App\Http\Controllers\Shop\App;

use App\Facades\Verifiable;
use App\Repositories\App\CommentRepository;
use Illuminate\Http\Request;

class CommentController extends CommonController
{
    private $commentRepository;

    public function __construct(
        CommentRepository $commentRepository
    )
    {
        $this->commentRepository = $commentRepository;
    }

    public function label(Request $request)
    {
        $data = $this->commentRepository->getLabels($request->all());
        if ($data->count() > 0) {
            return ['code' => 0, 'msg' => '', 'data' => $data];
        } else {
            return ['code' => 1, 'msg' => '', 'data' => ''];
        }
    }

    public function addComment(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $request->all();
            $data['ip'] = $request->getClientIp();
            $re = $this->commentRepository->addComment($data, $uid);
            if ($re) {
                return ['code' => 0, 'msg' => '', 'data' => $re];
            }else{
                return ['code' => 0, 'msg' => '', 'data' => []];
            }
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }
}
