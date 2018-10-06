<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CommentRepositoryInterface;
use App\Http\Models\Shop\CommentModel;

class CommentRepository implements CommentRepositoryInterface
{
    private $commentModel;

    public function __construct(
        CommentModel $commentModel
    )
    {
        $this->commentModel = $commentModel;
    }

    public function getCommentByPage($search)
    {
        return $this->commentModel->getCommentByPage([], $search);
    }

    public function getComments($id)
    {
        return $this->commentModel->getComments($id);
    }

    public function delComment($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['comment_id'] = $id;
        $re = $this->commentModel->delComment($where);
        if($re){
            $pwhere['parent_id'] = $id;
            $this->commentModel->delComment($pwhere);
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}