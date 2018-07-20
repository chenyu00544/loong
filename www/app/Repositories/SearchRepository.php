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

class SearchRepository implements SearchRepositoryInterface
{
    private $categoryModel;
    private $brandModel;
    private $goodsModel;

    public function __construct(
        CategoryModel $categoryModel,
        BrandModel $brandModel,
        GoodsModel $goodsModel
    )
    {
        $this->categoryModel = $categoryModel;
        $this->brandModel = $brandModel;
        $this->goodsModel = $goodsModel;
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
                $re = $this->goodsModel->searchGoodses($search);
                foreach ($re as $key => $value) {
                    $re[$key]->id = $value->goods_id;
                    $re[$key]->name = $value->goods_name;
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