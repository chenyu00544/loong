<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\FavourableActivityRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\BrandModel;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\FavourableActivityModel;
use App\Http\Models\Shop\GoodsModel;

class FavourableActivityRepository implements FavourableActivityRepositoryInterface
{

    private $favourableActivityModel;
    private $categoryModel;
    private $brandModel;
    private $goodsModel;

    public function __construct(
        FavourableActivityModel $favourableActivityModel,
        CategoryModel $categoryModel,
        BrandModel $brandModel,
        GoodsModel $goodsModel
    )
    {
        $this->favourableActivityModel = $favourableActivityModel;
        $this->categoryModel = $categoryModel;
        $this->brandModel = $brandModel;
        $this->goodsModel = $goodsModel;
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

    public function getFavourableActivity($id)
    {
        $where['act_id'] = $id;
        $faat = $this->favourableActivityModel->getFavourableActivity($where);
        $act_range_ext = explode(',', $faat->act_range_ext);
        $faat->user_rank = explode(',', $faat->user_rank);
        $range_arr = [];
        switch ($faat->act_range) {
            case 1:
                $range_ext = $this->categoryModel->getComCatesByIn($act_range_ext);
                foreach ($range_ext as $key => $value) {
                    $range_arr[$key]['id'] = $value->id;
                    $range_arr[$key]['name'] = $value->cat_name;
                }
                break;
            case 2:
                $range_ext = $this->brandModel->getBrandByIn($act_range_ext);
                foreach ($range_ext as $key => $value) {
                    $range_arr[$key]['id'] = $value->id;
                    $range_arr[$key]['name'] = $value->brand_name;
                }
                break;
            case 3:
                $range_ext = $this->goodsModel->getGoodsesByIn($act_range_ext);
                foreach ($range_ext as $key => $value) {
                    $range_arr[$key]['id'] = $value->goods_id;
                    $range_arr[$key]['name'] = $value->goods_name;
                }
                break;
            default:
                break;
        }
        $faat->act_range_ext = $range_arr;
        $faat->gift = unserialize($faat->gift);
        return $faat;
    }

    public function setFavourableActivity($data, $id)
    {
        $where['act_id'] = $id;
        $inarr = ['gift_id', 'gift_price', 'gift_name'];
        $gift = [];
        foreach ($data as $key => $value) {
            if (in_array($key, $inarr)) {
                foreach ($value as $k => $val) {
                    $gift[$k][str_replace('gift_', '', $key)] = $val;
                }
                unset($data[$key]);
                continue;
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
                    if($value->isValid()){
                        $updata['activity_thumb'] = FileHandle::upLoadImage($value, 'activity');
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $updata['gift'] = serialize($gift);
        return $this->favourableActivityModel->setFavourableActivity($where, $updata);
    }

    public function addFavourableActivity($data, $admin)
    {
        $inarr = ['gift_id', 'gift_price', 'gift_name'];
        $gift = [];
        foreach ($data as $key => $value) {
            if (in_array($key, $inarr)) {
                foreach ($value as $k => $val) {
                    $gift[$k][str_replace('gift_', '', $key)] = $val;
                }
                unset($data[$key]);
                continue;
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
                    if($value->isValid()){
                        $updata['activity_thumb'] = FileHandle::upLoadImage($value, 'activity');
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $updata['gift'] = serialize($gift);
        $updata['user_id'] = $admin->ru_id;
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
                $res = $this->favourableActivityModel->getFavourableActivitys($updata);
                foreach ($res as $val){
                    FileHandle::deleteFile($val->activity_thumb);
                }
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
        $res = $this->favourableActivityModel->getFavourableActivity($where);
        $re = $this->favourableActivityModel->delFavourableActivity($where);
        if ($re) {
            FileHandle::deleteFile($res->activity_thumb);
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}