<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\CommentRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Wxapp\CommentExtModel;
use App\Http\Models\Wxapp\CommentImgModel;
use App\Http\Models\Wxapp\CommentLabelModel;
use App\Http\Models\Wxapp\CommentModel;
use App\Http\Models\Wxapp\OrderInfoModel;
use App\Http\Models\Wxapp\UsersModel;

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

    public function getComments($data)
    {
        $return = [];
        if (empty($data['goods_id'])) {
            return false;
        } else {
            $goods_id = $data['goods_id'];
        }
        $column = ['comment.comment_id', 'comment_type', 'comment.id_value', 'user_name', 'content', 'order_id', 'user_id', 'parent_id', 'status',
            'comment_rank', 'comment_server', 'comment_delivery', 'add_time'];
        $page = empty($data['page']) ? 1 : $data['page'];
        switch ($data['label_id']) {
            case 0:
                $comments = $this->commentModel->getComments($goods_id, $column, $page);
                break;
            default:
                $comments = $this->commentModel->getCommentsByLabel($goods_id, $data['label_id'], $column, $page);
                break;
        }
        foreach ($comments as $comment) {
            foreach ($comment->commentImg as $commentImg) {
                $commentImg->comment_img = FileHandle::getImgByOssUrl($commentImg->comment_img);
            }
            foreach ($comment->reply as $reply){
                foreach ($reply->commentImg as $comImg) {
                    $comImg->comment_img = FileHandle::getImgByOssUrl($comImg->comment_img);
                }
                $reply->add_time_format = date(RedisCache::get('shop_config')['time_format'], $reply->add_time);
                $reply->logo = FileHandle::getImgByOssUrl($reply->user->logo);
                unset($reply->user);
            }
            $comment->logo = FileHandle::getImgByOssUrl($comment->user->logo);
            $comment->add_time_format = date(RedisCache::get('shop_config')['time_format'], $comment->add_time);
            unset($comment->user);
        }
        $return['comments'] = $comments;

        //评价统计
        $commentLabels = $this->commentLabelModel->getCommentLabels();
        foreach ($commentLabels as $commentLabel) {
            $commentLabel->count = 0;
            $commentLabel->count = $this->commentExtModel->countCommentExt(['id_value' => $goods_id, 'label_id' => $commentLabel->id]);
        }
        $return['comment_labels'] = $commentLabels;
        return $return;
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