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
use App\Facades\RedisCache;
use App\Http\Models\App\CommentExtModel;
use App\Http\Models\App\CommentImgModel;
use App\Http\Models\App\CommentLabelModel;
use App\Http\Models\App\CommentModel;
use App\Http\Models\App\OrderInfoModel;
use App\Http\Models\App\UsersModel;

class CommentRepository implements CommentRepositoryInterface
{

    private $commentModel;
    private $commentLabelModel;
    private $commentExtModel;
    private $commentImgModel;
    private $orderInfoModel;
    private $usersModel;

    public function __construct(
        CommentModel $commentModel,
        CommentLabelModel $commentLabelModel,
        CommentExtModel $commentExtModel,
        CommentImgModel $commentImgModel,
        OrderInfoModel $orderInfoModel,
        UsersModel $usersModel
    )
    {
        $this->commentModel = $commentModel;
        $this->commentLabelModel = $commentLabelModel;
        $this->commentExtModel = $commentExtModel;
        $this->commentImgModel = $commentImgModel;
        $this->orderInfoModel = $orderInfoModel;
        $this->usersModel = $usersModel;
    }

    public function getLabels($data)
    {
        return $this->commentLabelModel->getCommentLabels();
    }

    public function addComment($data, $uid)
    {
        $time = time();
        $order_id = empty($data['order_id']) ? 0 : $data['order_id'];
        $ure = $this->usersModel->getUser($uid);
        $goods_ids = explode(',', $data['goods_ids']);
        $img_num = 0;
        $img_n = 0;
        foreach ($goods_ids as $goods_id) {
            $comment = $this->commentModel->getCommentsByOrder(['order_id' => $order_id, 'parent_id' => 0], ['*']);
            $comment_id = empty($comment) ? 0 : $comment->comment_id;
            $ct_id = RedisCache::incrby('comment_id');
            $commentData = [
                'comment_id' => $ct_id,
                'comment_type' => $comment_id == 0 ? 0 : 2,
                'email' => $ure->email,
                'id_value' => $goods_id,
                'user_name' => $ure->nick_name,
                'content' => empty($data['content_' . $goods_id]) ? '默认好评' : $data['content_' . $goods_id],
                'comment_rank' => empty($data['goods_rank_' . $goods_id]) ? 5 : $data['goods_rank_' . $goods_id],
                'comment_server' => empty($data['service_rank_' . $goods_id]) ? 5 : $data['service_rank_' . $goods_id],
                'comment_delivery' => empty($data['shipping_rank_' . $goods_id]) ? 5 : $data['shipping_rank_' . $goods_id],
                'add_time' => $time,
                'ip_address' => empty($data['ip']) ? 0 : $data['ip'],
                'status' => 1,
                'parent_id' => $comment_id,
                'user_id' => $uid,
                'ru_id' => empty($data['ru_id']) ? 0 : $data['ru_id'],
                'order_id' => $order_id,
                'rec_id' => empty($data['rec_id']) ? 0 : $data['rec_id'],
            ];
            $re = $this->commentModel->addComment($commentData);

            $label_ids = explode(',', $data['label_ids_' . $goods_id]);
            foreach ($label_ids as $label_id) {
                $commentExtData = [
                    'comment_id' => $ct_id,
                    'label_id' => $label_id,
                    'id_value' => $goods_id,
                ];
                $this->commentExtModel->addCommentExt($commentExtData);
            }
            $img_num += $data['img_num_' . $goods_id];
            for ($i = $img_n; $i < $img_num; $i++) {
                if (!empty($data['file_' . $i])) {
                    if ($data['file_' . $i]->isValid()) {
                        $path = 'comment_img';
                        $uri = FileHandle::upLoadImage($data['file_' . $i], $path);
                        $comment_img = [
                            'user_id' => $uid,
                            'order_id' => $order_id,
                            'rec_id' => empty($data['rec_id']) ? 0 : $data['rec_id'],
                            'goods_id' => $goods_id,
                            'comment_id' => $ct_id,
                            'comment_img' => $uri
                        ];
                        $this->commentImgModel->addCommentImgs($comment_img);
                    }
                }
            }
            $img_n += $data['img_num_' . $goods_id];
        }

        $owhere['order_id'] = $order_id;
        if ($comment_id == 0) {
            $odata = [
                'comment_status' => CS_COMMENTED,
            ];
        } else {
            $odata = [
                'comment_status' => CS_REVIEW_COMMENTED,
            ];
        }
        $this->orderInfoModel->setOrder($owhere, $odata);
        return $re;
    }
}