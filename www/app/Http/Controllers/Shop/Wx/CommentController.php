<?php

namespace App\Http\Controllers\Shop\Wx;

use App\Facades\Verifiable;
use App\Repositories\Wxapp\CommentRepository;
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

    public function getComments(Request $request)
    {
        $re = $this->commentRepository->getComments($request->all());
        return $this->apiReturn($re);
    }

    public function addComment(Request $request)
    {
        $uid = Verifiable::authorization($request);
        if ($uid != '') {
            $data = $request->all();
            $data['ip'] = $request->getClientIp();
            $re = $this->commentRepository->addComment($data, $uid);
            return $this->apiReturn($re);
        } else {
            return ['code' => 1, 'msg' => '未登陆'];
        }
    }
}
