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
use App\Http\Models\App\CommentLabelModel;

class CommentRepository implements CommentRepositoryInterface
{

    private $commentLabelModel;

    public function __construct(
        CommentLabelModel $commentLabelModel
    )
    {
        $this->commentLabelModel = $commentLabelModel;
    }

    public function getLabels($data)
    {
        return $this->commentLabelModel->getCommentLabels();
    }
}