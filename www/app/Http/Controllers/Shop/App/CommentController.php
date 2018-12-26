<?php

namespace App\Http\Controllers\Shop\App;

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
}
