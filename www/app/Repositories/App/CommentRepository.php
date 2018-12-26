<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\CommentRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\App\CommentExtModel;
use App\Http\Models\App\CommentImgModel;
use App\Http\Models\App\CommentLabelModel;
use App\Http\Models\App\CommentModel;
use App\Http\Models\App\UsersModel;

class CommentRepository implements CommentRepositoryInterface
{

    private $commentModel;
    private $commentLabelModel;
    private $commentExtModel;
    private $commentImgModel;
    private $usersModel;

    public function __construct(
        CommentModel $commentModel,
        CommentLabelModel $commentLabelModel,
        CommentExtModel $commentExtModel,
        CommentImgModel $commentImgModel,
        UsersModel $usersModel
    )
    {
        $this->commentModel = $commentModel;
        $this->commentLabelModel = $commentLabelModel;
        $this->commentExtModel = $commentExtModel;
        $this->commentImgModel = $commentImgModel;
        $this->usersModel = $usersModel;
    }

    public function getLabels($data)
    {
        return $this->commentLabelModel->getCommentLabels();
    }

    public function addComment($data, $uid)
    {
        $time = time();
        $ure = $this->usersModel->getUser($uid);
        $commentData = [
            'comment_type' => 0,
            'email' => $ure->email,
            'user_name' => $ure->nick_name,
            'content' => empty($data['info']) ? '' : $data['info'],
            'comment_rank' => empty($data['goods_rank']) ? 5 : $data['goods_rank'],
            'comment_server' => empty($data['service_rank']) ? 5 : $data['service_rank'],
            'comment_delivery' => empty($data['shipping_rank']) ? 5 : $data['shipping_rank'],
            'add_time' => $time,
            'ip_address' => empty($data['ip']) ? 5 : $data['ip'],
            'status' => 1,
            'parent_id' => empty($data['comment_id']) ? 0 : $data['comment_id'],
            'user_id' => $uid,
            'ru_id' => empty($data['ru_id']) ? 0 : $data['ru_id'],
            'order_id' => empty($data['order_id']) ? 0 : $data['order_id'],
            'rec_id' => empty($data['rec_id']) ? 0 : $data['rec_id'],
        ];
        $re = $this->commentModel->addComment($commentData);

        $goods_ids = explode(',', $data['goods_ids']);
        foreach ($goods_ids as $goods_id) {
            if (!empty($data['label_ids'])) {
                $label_ids = explode(',', $data['label_ids']);
                foreach ($label_ids as $label_id) {
                    $commentExtData = [
                        'comment_id' => $re->comment_id,
                        'label_id' => $label_id,
                        'id_value' => $goods_id,
                    ];
                    $this->commentExtModel->addCommentExt($commentExtData);
                }
            }
        }

        for ($i = 0; $i < 6; $i++) {
            if (!empty($data['file_' . $i])) {
                if ($data['file_' . $i]->isValid()) {
                    $path = 'comment_img';
                    $uri = FileHandle::upLoadImage($data['file_' . $i], $path);
                    foreach ($goods_ids as $goods_id) {
                        $comment_img = [
                            'user_id' => $uid,
                            'order_id' => empty($data['order_id']) ? 0 : $data['order_id'],
                            'rec_id' => empty($data['rec_id']) ? 0 : $data['rec_id'],
                            'goods_id' => $goods_id,
                            'comment_id' => $re->comment_id,
                            'comment_img' => $uri
                        ];
                        $this->commentImgModel->addCommentImgs($comment_img);
                    }
                }
            }
        }
        return $re;
    }
}