<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SearchRepositoryInterface;
use App\Http\Models\Shop\BrandModel;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\GoodsModel;
use App\Http\Models\Shop\UserRankModel;
use App\Http\Models\Shop\UsersModel;

class SearchRepository implements SearchRepositoryInterface
{
    private $categoryModel;
    private $brandModel;
    private $goodsModel;
    private $usersModel;
    private $userRankModel;

    public function __construct(
        CategoryModel $categoryModel,
        BrandModel $brandModel,
        GoodsModel $goodsModel,
        UsersModel $usersModel,
        UserRankModel $userRankModel
    )
    {
        $this->categoryModel = $categoryModel;
        $this->brandModel = $brandModel;
        $this->goodsModel = $goodsModel;
        $this->usersModel = $usersModel;
        $this->userRankModel = $userRankModel;
    }

    public function search($data)
    {
        $req = ['code' => 5, 'msg' => '没有数据'];
        $re = [];
        switch ($data['type']) {
            case 1:
                $search['cat_name'] = $data['keywords'];
                $search['keywords'] = $data['keywords'];
                $search['cat_desc'] = $data['keywords'];
                $re = $this->categoryModel->searchComCates($search);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->id;
                    $re[$key]->name = $value->cat_name;
                }
                break;
            case 2:
                $search['brand_name'] = $data['keywords'];
                $re = $this->brandModel->searchBrands($search);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->id;
                    $re[$key]->name = $value->brand_name;
                }
                break;
            case 3:
                $search['goods_name'] = $data['keywords'];
                $re = $this->goodsModel->searchGoodses($search, ['goods_id', 'goods_name']);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->goods_id;
                    $re[$key]->name = $value->goods_name;
                }
                break;
            case 4:
                $search['user_name'] = $data['keywords'];
                $re = $this->usersModel->searchUsers($search, ['user_id', 'user_name']);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->user_id;
                    $re[$key]->name = $value->user_name;
                }
                break;
            case 5:
                $search['rank_name'] = $data['keywords'];
                $re = $this->userRankModel->searchUserRanks($search, ['rank_id', 'rank_name']);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->rank_id;
                    $re[$key]->name = $value->rank_name;
                }
                break;
            default:
                break;
        }
        if ($re) {
            $req['data'] = $re;
            $req['msg'] = '';
            $req['code'] = 1;
        }
        return $req;
    }
}