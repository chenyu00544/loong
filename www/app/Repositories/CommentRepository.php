<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CommentRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\CommentImgModel;
use App\Http\Models\Shop\CommentModel;

class CommentRepository implements CommentRepositoryInterface
{
    private $commentModel;
    private $commentImgModel;

    public function __construct(
        CommentModel $commentModel,
        CommentImgModel $commentImgModel
    )
    {
        $this->commentModel = $commentModel;
        $this->commentImgModel = $commentImgModel;
    }

    public function getCommentByPage($search)
    {
        return $this->commentModel->getCommentByPage([], $search);
    }

    public function getComments($id)
    {
        $comments = $this->commentModel->getComments($id);
        foreach ($comments as $comment) {
            foreach ($comment->commentImg as $commentImg) {
                $commentImg->comment_img = FileHandle::getImgByOssUrl($commentImg->comment_img);
            }
        }
        return $comments;
    }

    public function setComment($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        if ($data['type'] == 'status') {
            $where['comment_id'] = $data['id'];
            $update['status'] = $data['val'];
            $re = $this->commentModel->setComment($where, $update);
        } elseif ($data['type'] == 'allow') {
            $where = $data['ids'];
            $update['status'] = 1;
            $re = $this->commentModel->setComments($where, $update);
        } elseif ($data['type'] == 'deny') {
            $where = $data['ids'];
            $update['status'] = 0;
            $re = $this->commentModel->setComments($where, $update);
        }
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function modifyComments($data, $id, $user)
    {
        $comment = $this->commentModel->getComment($id);
        if ($comment->comment_type == 3) {
            $where['comment_id'] = $id;
            $updata['user_name'] = $data->get('user_name');
            $updata['email'] = $data->get('email');
            $updata['content'] = $data->get('content');
            $updata['ip_address'] = $data->getClientIp();
            $where['user_id'] = $user->user_id;
            $commentImgs = $this->commentImgModel->getCommentImgs($where);
            foreach ($commentImgs as $commentImg) {
                FileHandle::deleteFile($commentImg->comment_img);
                $this->commentImgModel->delCommentImg(['id' => $commentImg->id]);
            }
            if (!empty($data['reply-img'])) {
                foreach ($data['reply-img'] as $replyImg) {
                    $path = 'reply_img';
                    $cImgData['comment_img'] = FileHandle::upLoadImage($replyImg, $path);
                    $cImgData['user_id'] = $user->user_id;
                    $cImgData['comment_id'] = $comment->comment_id;
                    $cImgData['order_id'] = $comment->order_id;
                    $cImgData['goods_id'] = $comment->id_value;
                    $this->commentImgModel->addCommentImgs($cImgData);
                }
            }
            $re = $this->commentModel->setComment($where, $updata);
            return $re;
        } else {
            $updata = [
                'comment_type' => 3,
                'id_value' => $comment->id_value,
                'email' => $user->email,
                'user_name' => $user->user_name,
                'content' => $data->get('content'),
                'comment_rank' => $comment->comment_rank,
                'comment_server' => $comment->comment_server,
                'comment_delivery' => $comment->comment_delivery,
                'add_time' => time(),
                'ip_address' => $data->getClientIp(),
                'status' => 1,
                'parent_id' => $comment->parent_id != 0 ? $comment->parent_id : $id,
                'user_id' => $user->user_id,
                'single_id' => 0,
                'order_id' => $comment->order_id,
                'rec_id' => $comment->rec_id,
                'ru_id' => $comment->ru_id
            ];
            $re = $this->commentModel->addComment($updata);
            if (!empty($data['reply-img'])) {
                foreach ($data['reply-img'] as $replyImg) {
                    $path = 'reply_img';
                    $cImgData['comment_img'] = FileHandle::upLoadImage($replyImg, $path);
                    $cImgData['user_id'] = $user->user_id;
                    $cImgData['comment_id'] = $re->comment_id;
                    $cImgData['order_id'] = $comment->order_id;
                    $cImgData['goods_id'] = $comment->id_value;
                    $this->commentImgModel->addCommentImgs($cImgData);
                }
            }
        }
        return $re;
    }

    public function delComment($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['comment_id'] = $id;
        $re = $this->commentModel->getComment($where);
        if ($re) {
            if ($re->parent_id != 0) {
                foreach ($re->commentImg as $img) {
                    FileHandle::deleteFile($img->comment_img);
                    $this->commentImgModel->delCommentImg(['id' => $img->id]);
                }
                $this->commentModel->delComment($where);
                $req = ['code' => 1, 'msg' => '操作成功'];
            } else {
                $res = $this->commentModel->getComments($id);
                foreach ($res as $com) {
                    foreach ($com->commentImg as $img) {
                        FileHandle::deleteFile($img->comment_img);
                        $this->commentImgModel->delCommentImg(['id' => $img->id]);
                    }
                    $pwhere['comment_id'] = $com->comment_id;
                    $this->commentModel->delComment($pwhere);
                }
            }
        }
        return $req;
    }
}