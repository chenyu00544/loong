<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\UsersRealRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\UsersRealModel;

class UsersRealRepository implements UsersRealRepositoryInterface
{

    private $usersRealModel;

    public function __construct(
        UsersRealModel $usersRealModel
    )
    {
        $this->usersRealModel = $usersRealModel;
    }

    public function getUserRealsByPage($data)
    {
        $where = [];
        if (!empty($data['keywords'])) {
            $where[] = ['users.user_name', 'like', '%' . $data['keywords'] . '%'];
        }
        if (!empty($data['review_status']) && $data['review_status'] != -1) {
            $where['users_real.review_status'] = $data['review_status'];
        }
        return $this->usersRealModel->getUserRealsByPage($where);
    }

    public function getUserReal($id)
    {
        $where['real_id'] = $id;
        $re = $this->usersRealModel->getUserReal($where);
        $re->front_of_id_card = FileHandle::getImgByOssUrl($re->front_of_id_card);
        $re->reverse_of_id_card = FileHandle::getImgByOssUrl($re->reverse_of_id_card);
        return $re;
    }

    public function changes($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];

        $re = [];
        if ($data['real_type'] == 'is_examine') {
            $where = $data['ids'];
            $updata['review_status'] = $data['s_examine'];
            $re = $this->usersRealModel->setUserReals($where, $updata);
        } elseif($data['real_type'] == 'is_delete') {
            $where = $data['ids'];
            $re = $this->usersRealModel->delUserReals($where);
        }

        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function setUserReal($data, $id)
    {
        $where['real_id'] = $id;
        if(!empty($data['review_status']) && $data['review_status'] == 1){
            $data['review_content'] = '';
        }
        return $this->usersRealModel->setUserReal($where, $data);
    }

    public function delUserReal($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];

        $where['real_id'] = $id;
        $re = $this->usersRealModel->delUserReal($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}