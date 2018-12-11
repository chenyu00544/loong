<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\NotifyRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\App\NotifyModel;

class NotifyRepository implements NotifyRepositoryInterface
{

    private $notifyModel;

    public function __construct(
        NotifyModel $notifyModel
    )
    {
        $this->notifyModel = $notifyModel;
    }

    public function getNotifies($data)
    {
        $where['terminal'] = 'app';
        $page = empty($data['page']) ? 1 : $data['page'];
        $res = $this->notifyModel->getNotifies($where, $page);
        foreach ($res as $re) {
            $re->img = FileHandle::getImgByOssUrl($re->img);
        }
        return $res;
    }

    public function getOneNotify($data)
    {
        $re = $this->notifyModel->getNotify();
        $re->img = FileHandle::getImgByOssUrl($re->img);
        return $re;
    }
}