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

    public function addFavourableActivity($data, $admin)
    {
        $inarr = ['gift_id', 'gift_price', 'gift_name'];
        $gift = [];
        foreach ($data as $key => $value) {
            if (in_array($key, $inarr)) {
                foreach ($value as $k => $val) {
                    $gift[$k][str_replace('gift_', '', $key)] = $value;
                }
                unset($data[$key]);
            }

            switch ($key) {
                case 'start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['start_time'] = strtotime($date_arr[0]);
                        $updata['end_time'] = strtotime($date_arr[1]);
                    }
                    break;
                case 'rank':
                    $updata['user_rank'] = implode(',', $value);
                    break;
                case 'act_range_ext':
                    $updata['act_range_ext'] = implode(',', $value);
                    break;
                case 'activity_thumb':
                    $updata['act_range_ext'] = implode(',', $value);
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $updata['gift'] = serialize($gift);
        $updata['user_id'] = $admin->user_id;
        $updata['sort_order'] = 50;
        $updata['review_status'] = 3;
        return $this->favourableActivityModel->addFavourableActivity($updata);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['act_id'] = $data['id'];
        switch ($data['type']) {
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