<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\BrandRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Http\Models\App\BrandModel;
use App\Http\Models\App\GoodsModel;

class BrandRepository implements BrandRepositoryInterface
{

    protected $goodsModel;
    protected $brandModel;

    public function __construct(
        GoodsModel $goodsModel,
        BrandModel $brandModel
    )
    {
        $this->goodsModel = $goodsModel;
        $this->brandModel = $brandModel;
    }

    public function getBrandsByGoods($id)
    {
        $where['brand_id'] = $id;
        $column = ['goods_id', 'cat_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume'];
        $brand = $this->brandModel->getBrand(['id' => $id, 'is_show' => 1]);
        $brand->brand_logo = FileHandle::getImgByOssUrl($brand->brand_logo);
        $brand->index_img = FileHandle::getImgByOssUrl($brand->index_img);
        $brand->brand_bg = FileHandle::getImgByOssUrl($brand->brand_bg);
        $brand->brand_bg_app = FileHandle::getImgByOssUrl($brand->brand_bg_app);
        $return['brand'] = $brand;
        $res = $this->goodsModel->getGoodsByBrandId($where, $column);
        $cate = [];
        $goods = [];
        foreach ($res as $re) {
            if (!empty($re->category)) {
                $re->goods_thumb = FileHandle::getImgByOssUrl($re->goods_thumb);
                $re->goods_img = FileHandle::getImgByOssUrl($re->goods_img);
                $re->original_img = FileHandle::getImgByOssUrl($re->original_img);
                $re->shop_price_format = Common::priceFormat($re->shop_price);
                $re->market_price_format = Common::priceFormat($re->market_price);
                $re->promote_price_format = Common::priceFormat($re->promote_price);
                $re->category->touch_icon = FileHandle::getImgByOssUrl($re->category->touch_icon);
                $goods[$re->category->id][] = $re;
                $cate[$re->category->id] = $re->category;
                unset($re->category);
            }
        }
        foreach ($cate as $key => $val) {
            $cate[$key]->goods = $goods[$key];
        }
        sort($cate);
        $return['cate'] = $cate;
        return $return;
    }

    public function getBrandByGoodses($data, $orderby = [])
    {
        $where['brand_id'] = $data['brand_id'];
        $orderBy = [];
        switch ($data['type']) {
            case 'normal':
                $orderBy['column'] = 'goods_id';
                $orderBy['desc'] = 'DESC';
                break;
            case 'volume':
                $orderBy['column'] = 'sales_volume';
                $orderBy['desc'] = 'DESC';
                break;
            case 'price':
                $orderBy['column'] = 'shop_price';
                if ($data['up_down'] == 'down') {
                    $orderBy['desc'] = 'DESC';
                } else {
                    $orderBy['desc'] = 'ASC';
                }
                $orderBy['desc'] = 'DESC';
                break;
            case 'new':
                $orderBy['column'] = 'add_time';
                $orderBy['desc'] = 'DESC';
                break;
        }

        $brand = $this->brandModel->getBrandByGoodses(['id' => $data['brand_id'], 'is_show' => 1], ['*'], $orderBy);
        $brand->brand_logo = FileHandle::getImgByOssUrl($brand->brand_logo);
        $brand->index_img = FileHandle::getImgByOssUrl($brand->index_img);
        $brand->brand_bg = FileHandle::getImgByOssUrl($brand->brand_bg);
        $brand->brand_bg_app = FileHandle::getImgByOssUrl($brand->brand_bg_app);
        foreach ($brand->goods as $goods) {
            $goods->goods_thumb = FileHandle::getImgByOssUrl($goods->goods_thumb);
            $goods->goods_img = FileHandle::getImgByOssUrl($goods->goods_img);
            $goods->original_img = FileHandle::getImgByOssUrl($goods->original_img);
            $goods->goods_video = FileHandle::getImgByOssUrl($goods->goods_video);
            $goods->shop_price_format = Common::priceFormat($goods->shop_price);
            $goods->market_price_format = Common::priceFormat($goods->market_price);
            $goods->promote_price_format = Common::priceFormat($goods->promote_price);
            $goods->desc_mobile = unserialize($goods->desc_mobile);
        }
        return $brand;
    }
}