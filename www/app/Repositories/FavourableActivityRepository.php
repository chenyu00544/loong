<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\FavourableActivityRepositoryInterface;
use App\Http\Models\Shop\FavourableActivityModel;

class FavourableActivityRepository implements FavourableActivityRepositoryInterface
{

    private $favourableActivityModel;

    public function __construct(
        FavourableActivityModel $favourableActivityModel
    )
    {
        $this->favourableActivityModel = $favourableActivityModel;
    }

    public function getFavourableActivityByPage($search, $seller = 'selfsale')
    {
        $where = [];
        if ($seller == 'selfsale') {
            $where[] = ['user_id', '=', '0'];
        } else {
            $where[] = ['user_id', '<>', '0'];
        }
        return $this->favourableActivityModel->getFavourableActivityByPage($where, $search);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['act_id'] = $data['id'];
        switch ($data['type']){
            case 'sort_order':
                $updata['sort_order'] = $data['value'];
                break;
            case 'delete':
                $updata = $data['id'];
                $this->favourableActivityModel->delFavourableActivitys($updata);
                $req = ['code' => 1, 'msg' => '操作成功'];
                return $req;
                break;
            default:
                break;
        }
        $re = $this->favourableActivityModel->setFavourableActivity($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delFavourableActivity($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['act_id'] = $id;
        $re = $this->favourableActivityModel->delFavourableActivity($where);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}