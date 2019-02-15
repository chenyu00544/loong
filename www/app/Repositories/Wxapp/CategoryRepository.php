<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\CategoryRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Wxapp\CategoryModel;
use App\Http\Models\Wxapp\GoodsModel;

class CategoryRepository implements CategoryRepositoryInterface
{
    private $categoryModel;
    private $goodsModel;

    public function __construct(
        CategoryModel $categoryModel,
        GoodsModel $goodsModel
    )
    {
        $this->categoryModel = $categoryModel;
        $this->goodsModel = $goodsModel;
    }

    public function getCates()
    {
        if ($cate = RedisCache::get('category')) {
//            return $cate;
        }
        $column = [
            'id', 'cat_name', 'parent_id', 'sort_order', 'cat_alias_name', 'touch_icon', 'is_show'
        ];
        $where['is_show'] = 1;
        $where['parent_id'] = 0;
        $res = $this->categoryModel->getComCates($where, $column);
        $req = [];
        foreach ($res as $k => $re) {
            if ($re->subCate->count() > 0) {
                foreach ($re->subCate as $sub_cate) {
                    $sub_cate->touch_icon = FileHandle::getImgByOssUrl($sub_cate->touch_icon);
                    $title = [
                        'id' => $sub_cate->id,
                        'cat_alias_name' => $sub_cate->cat_alias_name,
                        'touch_icon' => $sub_cate->touch_icon,
                    ];
                    $sub_cate->title = $title;
                    if (!empty($sub_cate->subCate)) {
                        foreach ($sub_cate->subCate as $sub_cate2) {
                            $sub_cate2->touch_icon = FileHandle::getImgByOssUrl($sub_cate2->touch_icon);
                        }
                    }
                    if ($sub_cate->ads) {
                        foreach ($sub_cate->ads as $ad) {
                            $ad->ad_code = FileHandle::getImgByOssUrl($ad->ad_code);
                        }
                    }
                }
                $req[] = $re;
            }
        }
        RedisCache::set('category', $req);
        return $req;
    }

    public function getCatesByGoods($data)
    {
        $cateId = $data['id'];
        $cateIds = $this->getSubCates($cateId);
        $column = ['goods_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'is_hot', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume'];
        $goodses = $this->goodsModel->getGoodsesByCateIds($cateIds, $data['page'], $column);
        foreach ($goodses as $goods){
            $goods->goods_thumb = FileHandle::getImgByOssUrl($goods->goods_thumb);
            $goods->goods_img = FileHandle::getImgByOssUrl($goods->goods_img);
            $goods->original_img = FileHandle::getImgByOssUrl($goods->original_img);
            $goods->market_price_format = Common::priceFormat($goods->market_price);
            $goods->shop_price_format = Common::priceFormat($goods->shop_price);
            $goods->promote_price_format = Common::priceFormat($goods->promote_price);
            if ($goods->gvp->count() > 0) {
                $goods->volume_number = $goods->gvp[0]->volume_number;
                $goods->volume_price = Common::priceFormat($goods->gvp[0]->volume_price);
            }
        }
        $re['goods'] = $goodses;
        $column_cate = ['id', 'cat_name', 'parent_id', 'is_show', 'touch_icon', 'cat_alias_name'];
        $cates = $this->categoryModel->getSubCates(['parent_id' => $cateId, 'is_show' => 1], $column_cate);
        foreach ($cates as $cate) {
            $cate->touch_icon = FileHandle::getImgByOssUrl($cate->touch_icon);
        }
        $re['cates'] = $cates;
        return $re;
    }

    public function getSubCates($id, $ids = [])
    {
        $ids[] = $id;
        $cates = $this->categoryModel->getSubCates(['parent_id' => $id]);
        foreach ($cates as $cate) {
            $ids = $this->getSubCates($cate->id, $ids);
        }
        return $ids;
    }
}