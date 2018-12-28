<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\GoodsRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Facades\Pinyin;
use App\Facades\RedisCache;
use App\Http\Models\App\AttributeModel;
use App\Http\Models\App\BrandModel;
use App\Http\Models\App\BrowseGoodsModel;
use App\Http\Models\App\CartModel;
use App\Http\Models\App\CategoryModel;
use App\Http\Models\App\CateKeywordModel;
use App\Http\Models\App\CollectGoodsModel;
use App\Http\Models\App\CommentExtModel;
use App\Http\Models\App\CommentLabelModel;
use App\Http\Models\App\CommentModel;
use App\Http\Models\App\FavourableGoodsModel;
use App\Http\Models\App\GoodsAttrModel;
use App\Http\Models\App\GoodsDescriptionModel;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\ProductsModel;
use App\Http\Models\App\SearchKeywordModel;
use App\Http\Models\App\TransportModel;
use App\Http\Models\App\UsersModel;

class GoodsRepository implements GoodsRepositoryInterface
{
    private $goodsModel;
    private $goodsAttrModel;
    private $transportModel;
    private $commentModel;
    private $commentLabelModel;
    private $commentExtModel;
    private $usersModel;
    private $goodsDescriptionModel;
    private $cartModel;
    private $favourableGoodsModel;
    private $productsModel;
    private $collectGoodsModel;
    private $browseGoodsModel;
    private $searchKeywordModel;
    private $cateKeywordModel;
    private $categoryModel;
    private $attributeModel;
    private $brandModel;

    public function __construct(
        GoodsModel $goodsModel,
        GoodsAttrModel $goodsAttrModel,
        TransportModel $transportModel,
        CommentModel $commentModel,
        CommentLabelModel $commentLabelModel,
        CommentExtModel $commentExtModel,
        UsersModel $usersModel,
        GoodsDescriptionModel $goodsDescriptionModel,
        CartModel $cartModel,
        FavourableGoodsModel $favourableGoodsModel,
        ProductsModel $productsModel,
        CollectGoodsModel $collectGoodsModel,
        BrowseGoodsModel $browseGoodsModel,
        SearchKeywordModel $searchKeywordModel,
        CateKeywordModel $cateKeywordModel,
        CategoryModel $categoryModel,
        AttributeModel $attributeModel,
        BrandModel $brandModel
    )
    {
        $this->goodsModel = $goodsModel;
        $this->goodsAttrModel = $goodsAttrModel;
        $this->transportModel = $transportModel;
        $this->commentModel = $commentModel;
        $this->commentLabelModel = $commentLabelModel;
        $this->commentExtModel = $commentExtModel;
        $this->usersModel = $usersModel;
        $this->goodsDescriptionModel = $goodsDescriptionModel;
        $this->cartModel = $cartModel;
        $this->favourableGoodsModel = $favourableGoodsModel;
        $this->productsModel = $productsModel;
        $this->collectGoodsModel = $collectGoodsModel;
        $this->browseGoodsModel = $browseGoodsModel;
        $this->searchKeywordModel = $searchKeywordModel;
        $this->cateKeywordModel = $cateKeywordModel;
        $this->categoryModel = $categoryModel;
        $this->attributeModel = $attributeModel;
        $this->brandModel = $brandModel;
    }

    public function getBestGoods($page = 1)
    {
        $where = ['is_delete' => 0, 'is_best' => 1, 'is_on_sale' => 1];
        $column = ['goods_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume'];
        $goodses = $this->goodsModel->getGoodses($where, $page, $column);
        foreach ($goodses as $value) {
            $value->goods_thumb = FileHandle::getImgByOssUrl($value->goods_thumb);
            $value->goods_img = FileHandle::getImgByOssUrl($value->goods_img);
            $value->original_img = FileHandle::getImgByOssUrl($value->original_img);
            $value->market_price_format = Common::priceFormat($value->market_price);
            $value->shop_price_format = Common::priceFormat($value->shop_price);
            $value->promote_price_format = Common::priceFormat($value->promote_price);
            if ($value->gvp->count() > 0) {
                $value->volume_number = $value->gvp[0]->volume_number;
                $value->volume_price = Common::priceFormat($value->gvp[0]->volume_price);
            }
        }
        return $goodses;
    }

    public function getGoodsDetail($goods_id, $user_id = 0, $device_id = '')
    {
        //添加浏览足迹
        $time = time();
        if ($user_id > 0 && $this->browseGoodsModel->countBrowseGoods(['user_id' => $user_id, 'goods_id' => $goods_id]) == 0) {
            $browse_data = [
                'browse_id' => RedisCache::incrby("browse_id"),
                'user_id' => $user_id,
                'add_time' => $time,
                'goods_id' => $goods_id,
                'is_attention' => 1,
            ];
            $this->browseGoodsModel->addBrowseGoods($browse_data);
        } else {
            $this->browseGoodsModel->setBrowseGoods(['user_id' => $user_id, 'goods_id' => $goods_id], ['add_time' => $time, 'is_attention' => 1]);
        }

        $where['goods_id'] = $goods_id;
        $where['is_on_sale'] = 1;
        $where['is_delete'] = 0;
        $column = [
            'goods_id', 'cat_id', 'user_id', 'goods_name', 'goods_sn', 'brand_id', 'freight',
            'goods_number', 'shop_price', 'market_price', 'promote_price', 'promote_start_date', 'promote_end_date',
            'desc_mobile', 'goods_desc', 'goods_id', 'goods_thumb', 'original_img', 'goods_img', 'is_on_sale',
            'is_delete', 'is_best', 'is_new', 'is_hot', 'is_promote', 'is_volume', 'is_fullcut',
            'goods_type', 'is_limit_buy', 'limit_buy_start_date', 'limit_buy_end_date', 'limit_buy_num', 'review_status',
            'sales_volume', 'comments_number', 'tid', 'goods_cause', 'goods_video', 'is_distribution',
            'pinyin_keyword', 'goods_brief'
        ];
        $goods_detail = $this->goodsModel->getGoodsAndExt($where, $column);
        $this->goodsModel->incrementGoodses($where, 'click_count');
        if ($goods_detail) {
            $mobile_descs = unserialize($goods_detail->desc_mobile);
            $goods_detail->mobile_descs = $mobile_descs;
            $goods_detail->goods_video = FileHandle::getImgByOssUrl($goods_detail->goods_video);
            $goods_detail->goods_thumb = FileHandle::getImgByOssUrl($goods_detail->goods_thumb);
            $goods_detail->original_img = FileHandle::getImgByOssUrl($goods_detail->original_img);
            $goods_detail->goods_img = FileHandle::getImgByOssUrl($goods_detail->goods_img);
            $goods_detail->shop_price_format = Common::priceFormat($goods_detail->shop_price);
            $goods_detail->market_price_format = Common::priceFormat($goods_detail->market_price);
            $goods_detail->promote_price_format = Common::priceFormat($goods_detail->promote_price);
            if ($user_id > 0) {
                $goods_detail->count_cart = $this->cartModel->countCart(['user_id' => $user_id]);
            } else {
                if ($device_id != '') {
                    $goods_detail->count_cart = $this->cartModel->countCart(['session_id' => $device_id]);
                } else {
                    $goods_detail->count_cart = 0;
                }
            }


            $goods_detail->collect = $this->collectGoodsModel->countCollectGoods(['goods_id' => $goods_id, 'user_id' => $user_id, 'is_attention' => 1]);

            //大型活动
            $faat = $this->favourableGoodsModel->getFaat([['goods_id' => $goods_detail->goods_id], ['brand_id' => $goods_detail->brand_id], ['cate_id' => $goods_detail->cat_id]]);
            if ($faat) {
                $faat->current_time = $time;
                $faat->min_amount = Common::priceFormat($faat->min_amount);
                if ($faat->act_type == 1) {
                    $faat->act_type_ext = Common::priceFormat($faat->act_type_ext);
                } elseif ($faat->act_type == 2) {
                    $faat->act_type_ext = ((float)$faat->act_type_ext * 10) . '';
                }
                $faat->gift = unserialize($faat->gift);
                $goods_detail->faat = $faat;
            } else {
                $goods_detail->faat = [];
            }

            //退货货标志
            $goods_cause = explode(',', $goods_detail->goods_cause);
            $causes = [];
            $causeName = Common::causeName();
            if (count($goods_cause) > 0) {
                foreach ($goods_cause as $cause) {
                    if (!empty($cause)) {
                        $gcause['cause_type'] = $cause;
                        $gcause['name'] = $causeName[$cause];
                        $causes[] = $gcause;
                    }
                }
            }
            $goods_detail->goods_cause = $causes;
            if (!empty($goods_detail->brand)) {
                $goods_detail->brand->brand_logo = FileHandle::getImgByOssUrl($goods_detail->brand->brand_logo);
            }

            foreach ($goods_detail->ggallery as $gallery) {
                $gallery->img_original = FileHandle::getImgByOssUrl($gallery->img_original);
                $gallery->img_url = FileHandle::getImgByOssUrl($gallery->img_url);
            }

            //阶梯价格
            foreach ($goods_detail->gvp as $gvp) {
                $gvp->volume_price_format = Common::priceFormat((int)$gvp->volume_price);
            }

            //满减价格
            foreach ($goods_detail->fullcut as $fullcut) {
                $fullcut->cfull_format = Common::priceFormat((int)$fullcut->cfull);
                $fullcut->creduce_format = Common::priceFormat((int)$fullcut->creduce);
            }

            //快递
            if ($goods_detail->freight == 2) {
                $twhere['tid'] = $goods_detail->tid;
                $transport = $this->transportModel->getTransport($twhere);
                $goods_detail->transport = $transport;
            }

            //评价
            $goods_detail->comments = $this->commentModel->getComments($goods_id);
            foreach ($goods_detail->comments as $comment) {
                $comment->user_logo = $comment->user->logo;
                if ($comment->user_logo != '') {
                    $comment->user_logo = FileHandle::getImgByOssUrl($comment->user_logo);
                }
                unset($comment->user);
            }

            //评价统计
            $commentLabels = $this->commentLabelModel->getCommentLabels();
            foreach ($commentLabels as $commentLabel) {
                $commentLabel->count = $this->commentExtModel->countCommentExt(['id_value' => $goods_id, 'label_id' => $commentLabel->id]);
            }
            $goods_detail->comment_label = $commentLabels;

            //品牌商品
            $goods_detail->brand_goodses = $this->goodsModel->getGoodses(['brand_id' => $goods_detail->brand_id], 1, ['goods_name', 'original_img', 'shop_price', 'is_promote', 'promote_price', 'promote_start_date', 'promote_end_date'], 15);
            foreach ($goods_detail->brand_goodses as $brand_goods) {
                $brand_goods->original_img = FileHandle::getImgByOssUrl($brand_goods->original_img);
                $brand_goods->shop_price_format = Common::priceFormat($brand_goods->shop_price);
                $brand_goods->promote_price_format = Common::priceFormat($brand_goods->promote_price);
                $brand_goods->current_time = time();
            }

            //用户地址
            $uwhere['user_id'] = $user_id;
            $user = $this->usersModel->getUserByAddress($uwhere, ['user_id', 'address_id']);
            if ($user) {
                foreach ($user->addresses as $address) {
                    $address->province_name = $address->mapprovince->region_name;
                    $address->city_name = $address->mapcity->region_name;
                    $address->district_name = $address->mapdistrict->region_name;
                    if ($address->address_id == $user->address_id) {
                        $user->default_address = $address;
                    }
                }
            }
            $goods_detail->user = $user;
            $goods_detail->goods_description = $this->goodsDescriptionModel->getGoodsDescriptions();

            //商品属性整理
            $multi_attr = [];
            $single_attr = [];
            foreach ($goods_detail->gattr as $akey => $gattr) {
                $gattr->attr_name = $gattr->attr->attr_name;
                $gattr->attr_group = $gattr->attr->attr_group;
                if ($gattr->attr->attr_type == 1) {
                    if ($gattr->attr_img_flie != '') {
                        $gattr->attr_img_flie = FileHandle::getImgByOssUrl($gattr->attr_img_flie);
                    }
                    if ($gattr->attr_gallery_flie != '') {
                        $gattr->attr_gallery_flie = FileHandle::getImgByOssUrl($gattr->attr_gallery_flie);
                    }
                    $multi_attr[$gattr->attr_id][] = $gattr;
                } else {
                    $single_attr[] = $gattr;
                }
                unset($gattr->attr);
            }
            unset($goods_detail->gattr);
            $multi = [];
            foreach ($multi_attr as $mattr) {
                $multi[] = $mattr;
            }
            $goods_detail->multi_attr = $multi;
            $goods_detail->single_attr = $single_attr;
            $goods_detail->current_time = $time;
        }
        return $goods_detail;
    }

    public function getGoodsesByUserLike($data, $user_id = 0)
    {
        $page = empty($data['page']) ? 1 : $data['page'];
        $column = ['goods_id', 'goods_name', 'shop_price', 'market_price', 'goods_thumb', 'goods_img', 'original_img', 'is_best', 'is_hot', 'is_new', 'promote_price', 'is_promote', 'is_fullcut', 'is_volume', 'sales_volume'];
        $goodses = [];
        $gwhere = ['is_delete' => 0, 'is_best' => 1, 'is_on_sale' => 1, 'is_hot' => 1, 'is_new' => 1];
        if ($user_id > 0) {
            $where['user_id'] = $user_id;
            if ($page < 2) {
                $col_goodses = $this->browseGoodsModel->getBrowseGoodses($where, $page, ['*'], 'DESC');
                if ($col_goodses->count() > 0) {
                    foreach ($col_goodses as $col_goods) {
                        if (!empty($col_goods->goods)) {
                            $goodses[] = $col_goods->goods;
                        }
                    }
                } else {
                    $goodses = $this->goodsModel->getGoodses($gwhere, $page, $column);
                }
            } else {
                $goodses = $this->goodsModel->getGoodses($gwhere, $page, $column);
            }
        } else {
            $goodses = $this->goodsModel->getGoodses($gwhere, $page, $column);
        }
        foreach ($goodses as $goods) {
            $goods->goods_thumb = FileHandle::getImgByOssUrl($goods->goods_thumb);
            $goods->goods_img = FileHandle::getImgByOssUrl($goods->goods_img);
            $goods->original_img = FileHandle::getImgByOssUrl($goods->original_img);
            $goods->market_price_format = Common::priceFormat($goods->market_price);
            $goods->shop_price_format = Common::priceFormat($goods->shop_price);
            $goods->promote_price_format = Common::priceFormat($goods->promote_price);
        }
        return $goodses;
    }

    public function getSearchByGoods($request)
    {
        $column = ['goods_id', 'cat_id', 'goods_name', 'brand_id', 'market_price', 'shop_price', 'is_promote', 'promote_price', 'promote_start_date', 'promote_end_date', 'goods_thumb', 'goods_img', 'original_img', 'is_on_sale', 'is_delete', 'review_status', 'add_time', 'sort_order', 'pinyin_keyword', 'sales_volume', 'goods_brief', 'is_best', 'is_hot', 'is_new', 'cat_id', 'goods_type', 'pinyin_keyword'];
        $where = [
            ['is_on_sale', '=', '1'],
            ['is_delete', '=', '0'],
        ];
        $keywords = [];
        $whereIn = [];
        $page = empty($request['page']) ? 1 : $request['page'];
        if (!empty($request['keywords'])) {
            $keywords = Common::scws($request['keywords']);
            if (!in_array($request['keywords'], $keywords)) {
                $keywords[] = $request['keywords'];
            }
            foreach ($keywords as $key => $keyword) {
                $keywords[$key] = Pinyin::Pinyin($keyword, 'UTF8');
            }
            $kwhere['keyword'] = $request['keywords'];
            $re = $this->searchKeywordModel->incrementKeyword($kwhere);
            if (empty($re)) {
                $kupdata = [
                    'keyword' => $request['keywords'],
                    'pinyin' => Pinyin::Pinyin($request['keywords'], 'UTF8'),
                    'addtime' => time(),
                ];
                $this->searchKeywordModel->addKeyword($kupdata);
            }
        }

        if (!empty($request['cate_id'])) {
            $cate_id = $request['cate_id'];
            $res = $this->categoryModel->getSubCates(['parent_id' => $cate_id], ['id', 'parent_id']);
            $whereIn['cat_id'][] = $cate_id;
            foreach ($res as $re) {
                $whereIn['cat_id'][] = $re->id;
            }
            $cwhere = ['cate_id' => $cate_id];
            $re = $this->cateKeywordModel->incrementKeyword($cwhere);

            if (empty($re) && !empty($request['cate_name'])) {
                $cupdata = [
                    'cate_id' => $cate_id,
                    'cate_name' => $request['cate_name'],
                ];
                $this->cateKeywordModel->addKeyword($cupdata);
            }
        }

        $orderBy = [];
        if (!empty($request['type'])) {
            switch ($request['type']) {
                case '1':
                    $orderBy['sort_order'] = 'DESC';
                    $orderBy['is_best'] = 'DESC';
                    $orderBy['is_hot'] = 'DESC';
                    break;
                case '2':
                    if ($request['volume'] == 1) {
                        $orderBy['sales_volume'] = 'ASC';
                    } else {
                        $orderBy['sales_volume'] = 'DESC';
                    }
                    break;
                case '3':
                    if ($request['price_order'] == 1) {
                        $orderBy['shop_price'] = 'ASC';
                    } else {
                        $orderBy['shop_price'] = 'DESC';
                    }
                    break;
                case '4':
                    if ($request['price_order'] == 1) {
                        $orderBy['is_new'] = 'ASC';
                    } else {
                        $orderBy['is_new'] = 'DESC';
                    }
                    break;
            }
        }

        if (!empty($request['brand_id'])) {
            $whereIn['brand_id'] = explode(',', $request['brand_id']);
        }

        if (isset($request['goods_ids'])) {
            if (!empty($request['goods_ids'])) {
                $whereIn['goods_id'] = explode(',', $request['goods_ids']);
            } else {
                $whereIn['goods_id'] = [0];
            }
        }

        $whereOr = [];
        if (!empty($request['server_id'])) {
            switch ($request['server_id']) {
                case 1:
                    $whereOr[] = [
                        ['is_promote', '=', '1'],
                        ['promote_start_date', '<', time()],
                        ['promote_end_date', '>', time()]
                    ];
                    $whereOr[] = [['is_volume', '=', '1']];
                    $whereOr[] = [['is_fullcut', '=', '1']];
                    break;
                case 2:
                    $where[] = ['goods_number', '>', '0'];
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }

        $res = $this->goodsModel->getGoodsesBySearch($keywords, $where, $whereOr, $page, $whereIn, $orderBy, $column);
        $catId = [];
        $goodsType = [];
        $brandId = [];
        foreach ($res as $re) {
            $re->goods_thumb = FileHandle::getImgByOssUrl($re->goods_thumb);
            $re->goods_img = FileHandle::getImgByOssUrl($re->goods_img);
            $re->original_img = FileHandle::getImgByOssUrl($re->original_img);
            $re->market_price_format = Common::priceFormat($re->market_price);
            $re->shop_price_format = Common::priceFormat($re->shop_price);
            $re->promote_price_format = Common::priceFormat($re->promote_price);
            $re->current_time = time();
            if (!empty($re->brand)) {
                $re->brand_name = $re->brand->brand_name;
                $re->brand_logo = FileHandle::getImgByOssUrl($re->brand->brand_logo);
            }
            unset($re->brand);
            if ($re->cat_id > 0) {
                $catId[] = $re->cat_id;
            }

            if ($re->brand_id > 0) {
                $brandId[] = $re->brand_id;
            }

            if ($re->goods_type > 0) {
                $goodsType[] = $re->goods_type;
            }
        }
        return $res;
    }

    //fixme 从筛选条件中获取搜索结果
    public function filterToSearch($request)
    {
        $filter = [];
        foreach ($request as $attr => $val) {
            $attr = explode('_', $attr);
            if (!empty($attr[0]) && $attr[0] == 'attrid' && !empty($val)) {
                $filter[] = ['attrid' => $attr[1], 'attr_value' => explode(',', $val)];
            }
        }
        $keywords = [];
        if (!empty($request['keywords'])) {
            $keywords = Common::scws($request['keywords']);
            if (!in_array($request['keywords'], $keywords)) {
                $keywords[] = $request['keywords'];
            }
            foreach ($keywords as $key => $keyword) {
                $keywords[$key] = Pinyin::Pinyin($keyword, 'UTF8');
            }
        }

        $where = [];
        if (!empty($request['min_price'])) {
            $where[] = 'shop_price>' . $request['min_price'];
        }

        if (!empty($request['max_price'])) {
            $where[] = 'shop_price<' . $request['max_price'];
        }

        $wherein = [];
        if (!empty($request['cate_id'])) {
            $wherein['cat_id'] = $request['cate_id'];
        }

        if (!empty($request['brand_id'])) {
            $wherein['brand_id'] = $request['brand_id'];
        }

        $whereOr = '';
        if (!empty($request['server_id'])) {
            switch ($request['server_id']) {
                case 1:
                    $whereOr = 'is_promote=' . '1' . ' AND ' . 'promote_start_date<' . time() . ' AND ' . 'promote_end_date>' . time() . ' OR is_volume=1 OR is_fullcut=1 AND';
                    break;
                case 2:
                    $where[] = 'goods_number>' . '0';
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
            }
        }

        $res = $this->goodsAttrModel->getGoodsIdsByFilter($where, $wherein, $whereOr, $filter, $keywords, ['*']);
        $goods_ids['goods_id'] = [];
        $goods_ids['goods_count'] = 0;
        foreach ($res as $re) {
            $goods_ids['goods_id'][] = $re->goods_id;
        }
        if (!empty($goods_ids['goods_id'])) {
            $goods_ids['goods_count'] = count($goods_ids['goods_id']);
        }
        return $goods_ids;
    }

    //fixme 从搜索结果中获取筛选条件
    public function filterBySearch($request)
    {
        $return = [];
        $return['server']['title'] = '柠檬服务';
        $return['server']['values'] = [
            ['server_id' => 1, 'server_name' => '促销'],
            ['server_id' => 2, 'server_name' => '仅有现货'],
//            ['server_id' => 3, 'server_name' => '会员价']
        ];
        $return['price']['title'] = '价格';
        $return['price']['price_range'] = [
            ['min' => '0', 'max' => '100'],
            ['min' => '100', 'max' => '200'],
            ['min' => '200', 'max' => '300']
        ];

        if (!empty($request['goods_type'])) {
            $goodsType = explode(',', $request['goods_type']);
            $goodsType = array_filter($goodsType);
            $goodsType = array_unique($goodsType);
            $awhere['attr_group'] = 0;
            $attrs = $this->attributeModel->getAttrs($awhere, $goodsType, ['attr_id', 'cat_id', 'attr_name', 'attr_values']);
            foreach ($attrs as $attr) {
                $attr->attr_values = explode("\r\n", $attr->attr_values);
            }
            $return['attr'] = $attrs;
        }
        if (!empty($request['cat_id'])) {
            $catId = explode(',', $request['cat_id']);
            $catId = array_filter($catId);
            $catId = array_unique($catId);
            $return['cate']['title'] = '分类';
            $return['cate']['values'] = $this->categoryModel->getCates([], $catId, ['id', 'cat_name', 'cat_alias_name']);
        }
        if (!empty($request['brand_id'])) {
            $brandId = explode(',', $request['brand_id']);
            $brandId = array_filter($brandId);
            $brandId = array_unique($brandId);
            $return['brand']['title'] = '品牌';
            $return['brand']['values'] = $this->brandModel->getBrands([], $brandId, ['id', 'brand_name']);
        }

        return $return;
    }

    //fixme 获取常用搜索关键字
    public function getSearchByKeywords()
    {
        $re['keyword'] = $this->searchKeywordModel->getKeywords([], ['keyword', 'count']);
        $re['cate'] = $this->cateKeywordModel->getKeywords([], ['cate_id', 'cate_name']);
        return $re;
    }

    public function changeKeywords($request)
    {
        $this->searchKeywordModel->getKeywords([], ['keyword', 'count']);
        $count = $this->searchKeywordModel->countKeywords();
        $page = rand(1, $count / 10);
        return $this->searchKeywordModel->getKeywords([], ['keyword', 'count'], $page);
    }

    public function cartList($request, $uid)
    {
        $column = ['rec_id', 'user_id', 'goods_id', 'goods_sn', 'product_id', 'goods_attr'
            , 'goods_number', 'goods_attr_id', 'add_time', 'ru_id', 'goods_name'
        ];

        $where['user_id'] = 0;
        if ($uid != '' || !empty($uid)) {
            $where['user_id'] = $uid;
        } else {
            if (!empty($request['device_id'])) {
                $where['session_id'] = $request['device_id'];
            }
        }
        $rec_ids = [];
        if (!empty($request['rec_ids'])) {
            $rec_ids = explode(',', $request['rec_ids']);
        }
        $res = $this->cartModel->getCartsByGoods($where, $column, $rec_ids);

        $data = [];
        foreach ($res as $k => $re) {
            $arr = $re->toArray();
            foreach ($arr as $key => $value) {
                if ($key == 'goods') {
                    if ($arr['goods']['promote_end_date'] > time()) {
                        $arr['goods']['is_promote'] = '1';
                    } else {
                        $arr['goods']['is_promote'] = '0';
                    }
                    $arr['goods']['original_img'] = FileHandle::getImgByOssUrl($arr['goods']['original_img']);
                    $arr['goods']['shop_price_format'] = Common::priceFormat($arr['goods']['shop_price']);
                    $arr['goods']['market_price_format'] = Common::priceFormat($arr['goods']['market_price']);
                    $arr['goods']['promote_price_format'] = Common::priceFormat($arr['goods']['promote_price']);
                    $arr['goods']['current_time'] = time();
                } elseif ($key == 'store') {
                    $arr['store']['shop_logo'] = FileHandle::getImgByOssUrl($arr['store']['shop_logo']);
                    $data[$arr['store']['ru_id']]['store'] = $arr['store'];
                } elseif ($key == 'tax') {
                    $arr['goods']['tax'] = $value['attr_value'];
                } else {
                    $arr['goods'][$key] = $value;
                }
            }
            $data[$arr['store']['ru_id']]['goods'][] = $arr['goods'];
        }
        $data_bak = [];
        foreach ($data as $d) {
            $data_bak[] = $d;
        }
        $return['cart'] = $data_bak;
        $return['like_goods'] = $this->getGoodsesByUserLike([], $uid);
        return $return;
    }

    public function addCart($request, $uid = 0)
    {
        $uwhere = [];
        $goods_id = $request['goods_id'];
        $num = empty($request['num']) ? 1 : $request['num'];
        $session_id = !empty($request['device_id']) ? $request['device_id'] : 0;
        $where['goods_id'] = $goods_id;
        $where['goods_attr_id'] = !empty($request['goods_attr_ids']) ? $request['goods_attr_ids'] : '';
        if (!empty($uid)) {
            $count = $this->cartModel->countCart(['user_id' => $uid]);
        } else {
            $count = $this->cartModel->countCart(['session_id' => $session_id]);
        }
        if ($count < 30) {
            if ($uid != 0) {
                $where['user_id'] = $uid;
                $uwhere['user_id'] = $uid;
            } else {
                $where['session_id'] = $session_id;
                $uwhere['session_id'] = $session_id;
            }
            $count = $this->cartModel->countCart($where);
            if ($count == 0) {
                $goods = $this->goodsModel->getGoods(['goods_id' => $goods_id]);
                $goods_attr = [];
                $attr_value = [];
                if (!empty($request['goods_attr_ids'])) {
                    $goods_attr_ids = explode(',', $request['goods_attr_ids']);
                    $pwhere['goods_id'] = $goods_id;
                    $goods_attr = $this->productsModel->getProdcutAndAttr($goods_attr_ids, ['*'], $pwhere);
                    foreach ($goods_attr->attrs as $attr) {
                        $attr_value[] = $attr->attr_value;
                    }
                }
                $cart = [
                    'user_id' => $uid,
                    'session_id' => $session_id,
                    'goods_id' => $goods_id,
                    'goods_sn' => $goods->goods_sn,
                    'goods_name' => $goods->goods_name,
                    'market_price' => $goods->market_price,
                    'goods_price' => $goods->shop_price,
                    'goods_number' => $num,
                    'is_real' => $goods->is_real,
                    'goods_attr_id' => !empty($request['goods_attr_ids']) ? $request['goods_attr_ids'] : '',
                    'ru_id' => $goods->user_id,
                    'shopping_fee' => 0,
                    'warehouse_id' => 0,
                    'area_id' => 0,
                    'add_time' => time(),
                    'freight' => $goods->freight,
                    'tid' => $goods->tid,
                    'shipping_fee' => $goods->shipping_fee,
                    'take_time' => date(RedisCache::get('shop_config')['time_format'], time() + 86400 * 15),
                    'product_id' => !empty($goods_attr) ? $goods_attr->product_id : 0,
                    'goods_attr' => implode(',', $attr_value)
                ];
                $this->cartModel->addCart($cart);
            } else {
                $this->cartModel->incrementCartGoodsNumber($where);
            }

            return ['count_cart' => $this->cartModel->countCart($uwhere)];
        }
        return 0;
    }

    public function setCart($request)
    {
        $where['rec_id'] = !empty($request['rec_id']) ? $request['rec_id'] : 0;
        if (!empty($request['goods_number'])) {
            $update['goods_number'] = $request['goods_number'];
            return $this->cartModel->setCart($where, $update);
        }
    }

    public function delCart($request)
    {
        $where = explode(',', !empty($request['rec_ids']) ? $request['rec_ids'] : 0);
        return $this->cartModel->delCarts($where);
    }
}