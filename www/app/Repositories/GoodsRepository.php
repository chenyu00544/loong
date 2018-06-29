<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Facades\LangConfig;
use App\Facades\Pinyin;
use App\Facades\ShopConfig;
use App\Facades\Url;
use App\Http\Models\Shop\AttributeModel;
use App\Http\Models\Shop\BrandModel;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\GoodsAttrModel;
use App\Http\Models\Shop\GoodsCateModel;
use App\Http\Models\Shop\GoodsChangeLogModel;
use App\Http\Models\Shop\GoodsExtendModel;
use App\Http\Models\Shop\GoodsGalleryModel;
use App\Http\Models\Shop\GoodsModel;
use App\Http\Models\Shop\GoodsTypeModel;
use App\Http\Models\Shop\GoodsVolumePriceModel;
use App\Http\Models\Shop\MemberPriceModel;
use App\Http\Models\Shop\ProductsChangeLogModel;
use App\Http\Models\Shop\ProductsModel;
use App\Http\Models\Shop\TransportModel;
use App\Http\Models\Shop\IntelligentWeightModel;
use App\Http\Models\Shop\ShopConfigModel;
use App\Http\Models\Shop\UserRankModel;

class GoodsRepository implements GoodsRepositoryInterface
{

    private $shopConfigModel;
    private $goodsModel;
    private $goodsCateModel;
    private $goodsExtendModel;
    private $goodsGalleryModel;
    private $goodsAttrModel;
    private $goodsTypeModel;
    private $goodsVolumePriceModel;
    private $goodsChangeLogModel;
    private $brandModel;
    private $transportModel;
    private $attributeModel;
    private $intelligentWeightModel;
    private $categoryModel;
    private $productsModel;
    private $productsChangeLogModel;
    private $userRankModel;
    private $memberPriceModel;

    public function __construct(
        ShopConfigModel $shopConfigModel,
        GoodsModel $goodsModel,
        GoodsCateModel $goodsCateModel,
        GoodsGalleryModel $goodsGalleryModel,
        GoodsExtendModel $goodsExtendModel,
        GoodsAttrModel $goodsAttrModel,
        GoodsTypeModel $goodsTypeModel,
        GoodsVolumePriceModel $goodsVolumePriceModel,
        GoodsChangeLogModel $goodsChangeLogModel,
        BrandModel $brandModel,
        TransportModel $transportModel,
        AttributeModel $attributeModel,
        IntelligentWeightModel $intelligentWeightModel,
        CategoryModel $categoryModel,
        ProductsModel $productsModel,
        ProductsChangeLogModel $productsChangeLogModel,
        UserRankModel $userRankModel,
        MemberPriceModel $memberPriceModel
    )
    {
        $this->shopConfigModel = $shopConfigModel;
        $this->goodsModel = $goodsModel;
        $this->goodsCateModel = $goodsCateModel;
        $this->goodsAttrModel = $goodsAttrModel;
        $this->goodsExtendModel = $goodsExtendModel;
        $this->goodsGalleryModel = $goodsGalleryModel;
        $this->goodsTypeModel = $goodsTypeModel;
        $this->goodsVolumePriceModel = $goodsVolumePriceModel;
        $this->goodsChangeLogModel = $goodsChangeLogModel;
        $this->brandModel = $brandModel;
        $this->transportModel = $transportModel;
        $this->attributeModel = $attributeModel;
        $this->intelligentWeightModel = $intelligentWeightModel;
        $this->categoryModel = $categoryModel;
        $this->productsModel = $productsModel;
        $this->productsChangeLogModel = $productsChangeLogModel;
        $this->userRankModel = $userRankModel;
        $this->memberPriceModel = $memberPriceModel;
    }

    public function getGroupsConfig($groups)
    {
        $item_list = $this->shopConfigModel->getGroupsConfig(['shop_group' => $groups]);

        /* 整理数据 */
        $code_arr = array('shop_logo', 'no_picture', 'watermark', 'shop_slagon', 'wap_logo', 'two_code_logo', 'ectouch_qrcode', 'ecjia_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'business_logo', 'no_brand');

        $lang = LangConfig::LangAdminShopConf();
        $group_list = [];
        foreach ($item_list as $key => $item) {

            $pid = $item->parent_id;

            $item->name = isset($lang['cfg_name'][$item->code]) ? $lang['cfg_name'][$item->code] : $item->code;
            $item->desc = isset($lang['cfg_desc'][$item->code]) ? $lang['cfg_desc'][$item->code] : '';

            if ($item->code == 'sms_shop_mobile') {
                $item->url = 1;
            }

            if ($pid == 0) {
                /* 分组 */
                if ($item->type == 'group') {
                    $group_list[$item->id] = $item;
                }
            } else {
                /* 变量 */
                if (isset($group_list[$pid])) {
                    if ($item->store_range) {
                        $item['store_options'] = explode(',', $item->store_range);
                        $options = array();
                        foreach ($item->store_options as $k => $v) {
                            $options[$k] = isset($lang['cfg_range'][$item->code][$v]) ? $lang['cfg_range'][$item->code][$v] : $v;
                        }
                        $item->display_options = $options;
                    }

                    if ($item) {
                        if ($item->type == 'file' && in_array($item->code, $code_arr) && $item->value) {
                            $item->del_img = 1;

                            if (strpos($item->value, '../') === false) {
                                $item->value = "../" . $item->value;
                            }
                        } else {
                            $item->del_img = 0;
                        }
                    }

                    //设置中间变量，否则框架报错
                    $vars[$pid][] = $item;
                    $group_list[$pid]['vars'] = $vars[$pid];
                }
            }
        }

        return $group_list;
    }

    //商品分页
    public function getGoodsPage($keywords, $parame = [], $size = 10)
    {
        $goodsColumns = ['goods_id', 'goods_thumb', 'goods_name', 'user_id', 'brand_id', 'goods_type', 'goods_sn', 'shop_price', 'is_on_sale', 'is_best', 'is_new', 'is_hot', 'sort_order', 'goods_number', 'integral', 'commission_rate', 'is_promote', 'model_price', 'model_inventory', 'model_attr', 'review_status', 'review_content', 'store_best', 'store_new', 'store_hot', 'is_real', 'is_shipping', 'stages', 'goods_thumb', 'is_alone_sale', 'is_limit_buy', 'promote_end_date', 'limit_buy_end_date', 'bar_code', 'freight', 'tid'];
        $where[] = ['is_delete', '=', '0'];
        foreach ($parame as $val) {
            switch ($val) {
                case 'normal':
                    $where[] = ['review_status', '>=', '3'];
                    break;
                case 'fictitious':
                    $where[] = ['extension_code', '=', 'virtual_card'];
                    break;
                case 'examine':
                case 'noexamine':
                    $where[] = ['review_status', '=', '1'];
                    break;
                case 'examined':
                    $where[] = ['review_status', '=', '2'];
                    break;
                case 'delete':
                    $where = [['is_delete', '=', '1']];
                    break;
                case 'onsale':
                    $where[] = ['is_on_sale', '=', '1'];
                    break;
                case 'offsale':
                    $where[] = ['is_on_sale', '=', '0'];
                    break;
                case 'selfsale':
                    $where[] = ['user_id', '=', '0'];
                    break;
                case 'seller':
                    $where[] = ['user_id', '<>', '0'];
                    break;
                default:
                    break;
            }
        }

        if ($goods = $this->goodsModel->getGoodsPage($size, $where, $goodsColumns, $keywords)) {
            //品牌
            $brandColumns = ['id', 'brand_name'];
            $brandsFormat = [];
            if ($brands = $this->brandModel->getBrandsAll($brandColumns)->toArray()) {
                foreach ($brands as $bVal) {
                    $brandsFormat[$bVal['id']] = $bVal['brand_name'];
                }
            }

            //运费
            $transportColumns = ['tid', 'ru_id', 'freight_type'];
            $transportFormat = [];
            if ($transports = $this->transportModel->getTransportAll([], $transportColumns)->toArray()) {
                foreach ($transports as $tVal) {
                    $transportFormat[$tVal['tid']] = ['business_id' => $tVal['ru_id'], 'freight_type' => $tVal['freight_type']];
                }
            }

            foreach ($goods as $key => $val) {
                $goods[$key]->brand_name = !empty($brandsFormat[$val->brand_id]) ? $brandsFormat[$val->brand_id] : '暂无';
                $goods[$key]->transport = !empty($transportFormat[$val->tid]) ? $transportFormat[$val->tid] : [];
                $goods[$key]->goods_thumb = Url::getImagePath($val->goods_thumb);
                $goods[$key]->is_attr = 0;
            }
        }

        return $goods;
    }

    //单个商品
    public function getGoods($id)
    {
        $goodsColumns = ['*'];
        $req = $this->goodsModel->getGoods($id, $goodsColumns);

        //商品退货标识
        $req->goods_cause = explode(',', $req->goods_cause);
        $goodsCause = [0, 0, 0, 0];
        foreach ($goodsCause as $key => $value) {
            foreach ($req->goods_cause as $val) {
                if ($key == $val) {
                    $goodsCause[$key] = 1;
                }
            }
        }
        $req->goods_cause = $goodsCause;

        //商品扩展信息比如正品保证
        $req->goods_sever = $this->goodsExtendModel->getGoodsExtend(['goods_id' => $id]);
        $member_price = $this->memberPriceModel->getMemberPrice(['goods_id' => $id]);
        $memPrice = [];
        foreach ($member_price as $key => $value) {
            $memPrice[$value->user_rank] = $value;
        }
        $req->member_price = $memPrice;

        //手机商品详细信息图
        $req->desc_mobile_html = explode('">', $req->desc_mobile);
        $descMobileHtml = [];
        foreach ($req->desc_mobile_html as $value) {
            if ($value != '') {
                $descMobileHtml[] = $value . '">';
            }
        }
        $req->desc_mobile_html = $descMobileHtml;

        //阶梯价格
        $req->goods_volume_prices = $this->goodsVolumePriceModel->getGoodsVolumePrice(['goods_id' => $id]);

        //商品属性详细信息
        $goodsAttr = $this->goodsAttrModel->getGoodsAttrsJoinAttr(['goods_id' => $req->goods_id, 'attr_checked' => 1]);

        $goodsAttrO = [];
        $goodsAttrM = [];
        $attr_values = [];
        foreach ($goodsAttr as $value) {
            $goodsAttrArr[$value->goods_attr_id] = $value;
            $val_bat = $value;
            $attrValues = explode("\r\n", $val_bat->attr_values);
            $val_bat->attr_values = $attrValues;
            if ($value->attr_type == 0) {//唯一属性
                $goodsAttrO[] = $val_bat;
            } else {//单选属性
                $attr_values[$value->attr_id][] = $value->attr_value;
                $attr_sorts[$value->attr_id][] = $value->attr_sort;
                $goods_attr_ids[$value->attr_id][] = $value->goods_attr_id;
                $attr_img_flies[$value->attr_id][] = $value->attr_img_flie;
                $attr_gallery_flies[$value->attr_id][] = $value->attr_gallery_flie;
                $goodsAttrM[$value->attr_id] = $val_bat;
            }
        }

        foreach ($attr_values as $key => $value) {
            $i = 0;
            foreach ($goodsAttrM[$key]->attr_values as $k => $val) {
                if (in_array($val, $value)) {
                    $select[$k] = 1;

                    $attr_sorts_bak[$key][] = $attr_sorts[$key][$i];
                    $goods_attr_ids_bak[$key][] = $goods_attr_ids[$key][$i];
                    $attr_img_flies_bak[$key][] = $attr_img_flies[$key][$i];
                    $attr_gallery_flies_bak[$key][] = $attr_gallery_flies[$key][$i];
                    $i++;
                } else {
                    $select[$k] = 0;
                    $attr_sorts_bak[$key][] = 0;
                    $goods_attr_ids_bak[$key][] = 0;
                    $attr_img_flies_bak[$key][] = 0;
                    $attr_gallery_flies_bak[$key][] = 0;
                }
            }
            $goodsAttrM[$key]->attr_sorts = $attr_sorts_bak[$key];
            $goodsAttrM[$key]->goods_attr_ids = $goods_attr_ids_bak[$key];
            $goodsAttrM[$key]->attr_img_flies = $attr_img_flies_bak[$key];
            $goodsAttrM[$key]->attr_gallery_flies = $attr_gallery_flies_bak[$key];
            $goodsAttrM[$key]->selected = $select;
        }
        $req->goods_attr_o = $goodsAttrO;
        $req->goods_attr_m = $goodsAttrM;

        //商品相册
        $req->goods_gallerys = $this->goodsGalleryModel->getGoodsGallerys(['goods_id' => $id]);

        return $req;
    }

    public function getProductAndGallerys($data, $id, $ru_id = 0)
    {
        $goodsAttr = $this->goodsAttrModel->getGoodsAttrsJoinAttr(['goods_id' => $id]);
        foreach ($goodsAttr as $value) {
            $goodsAttrArr[$value->goods_attr_id] = $value;
            $goodsAttrValue[$value->attr_value] = $value->goods_attr_id;
        }
        //商品属性货品数据
        $products = [];
        if ($data['model_price'] == 0) {
            if (empty($data['attr_values'])) {
                $products = $this->productsModel->getProducts(['goods_id' => $id]);
                $re = $this->productsChangeLogModel->getProductsChangeLog(['goods_id' => $id]);
                foreach ($products as $value) {
                    $goods_attr = explode('|', $value->goods_attr);
                    foreach ($goods_attr as $k => $val) {
                        $goods_attr_names[$k] = $goodsAttrArr[$val]->attr_value;
                        $attr_ids[$k] = 'attr[' . $goodsAttrArr[$val]->attr_id . '][]';
                        $goods_attr_id[] = 'goods_attr_id[' . $val . '][]';
                    }
                    $value->goods_attr_names = $goods_attr_names;
                    $value->goods_attr = $goods_attr;
                    $value->goods_attr_id = $goods_attr_id;
                    $value->attr_ids = $attr_ids;
                }

                foreach ($re as $value) {
                    $goods_attr = explode('|', $value->goods_attr);
                    foreach ($goods_attr as $k => $val) {
                        $goods_attr_names[$k] = $goodsAttrArr[$val]->attr_value;
                        $attr_ids[$k] = 'attr[' . $goodsAttrArr[$val]->attr_id . '][]';
                        $goods_attr_id[] = 'goods_attr_id[' . $val . '][]';
                    }
                    $value->changelog_product_id = $value->product_id;
                    $value->goods_attr_names = $goods_attr_names;
                    $value->goods_attr = $goods_attr;
                    $value->goods_attr_id = $goods_attr_id;
                    $value->attr_ids = $attr_ids;
                    $products[] = $value;
                }
            } else {
                $data['attr_values'];
                $goodAttrIds = [];
                $this->goodsAttrModel->setGoodsAttr(['goods_id' => $id], ['attr_checked' => 0]);
                foreach ($data['attr_values'] as $value) {
                    foreach ($value as $val) {
                        if (!empty($goodsAttrValue[$val[0]])) {
                            $val[0] = $goodsAttrValue[$val[0]];
                            $goodAttrIds[$val[1]][] = $val[0];
                            $this->goodsAttrModel->setGoodsAttr(['goods_attr_id' => $val[0]], ['attr_checked' => 1]);
                        } else {
                            $addData['goods_id'] = $id;
                            $addData['attr_value'] = $val[0];
                            $addData['attr_id'] = $val[1];
                            $addData['attr_sort'] = 1;
                            $addData['admin_id'] = $ru_id;
                            $addData['attr_checked'] = 1;
                            $res = $this->goodsAttrModel->addGoodsAttr($addData);
                            $goodAttrIds[$val[1]][] = $res->goods_attr_id;
                        }
                    }
                }
                foreach ($goodAttrIds as $value) {
                    $goodAttrIds_bak[] = $value;
                }
                $pAttrs = Common::cartesian($goodAttrIds_bak);
                $products_bak = $this->productsModel->getProducts(['goods_id' => $id]);
                $this->productsChangeLogModel->delAll(['goods_id' => $id]);
                $products = [];
                $pAttrs_bak = [];
                foreach ($products_bak as $value) {
                    $pAttrs_bak[] = $value->goods_attr;
                    $products_bak[$value->goods_attr] = $value;
                }
                foreach ($pAttrs as $value) {
                    if (in_array($value, $pAttrs_bak)) {

                        $goods_attr = explode('|', $products_bak[$value]->goods_attr);
                        foreach ($goods_attr as $k => $val) {
                            $goods_attr_names[$k] = $goodsAttrArr[$val]->attr_value;
                            $attr_ids[$k] = 'attr[' . $goodsAttrArr[$val]->attr_id . '][]';
                            $goods_attr_id[] = 'goods_attr_id[' . $val . '][]';
                        }
                        $products_bak[$value]->goods_attr_names = $goods_attr_names;
                        $products_bak[$value]->goods_attr = $goods_attr;
                        $products_bak[$value]->goods_attr_id = $goods_attr_id;
                        $products_bak[$value]->attr_ids = $attr_ids;
                        $products[] = $products_bak[$value];
                    } else {
                        $addData = [];
                        $addData['goods_id'] = $id;
                        $addData['goods_attr'] = $value;
                        $addData['product_sn'] = '';
                        $addData['bar_code'] = '';
                        $addData['product_number'] = 0;
                        $addData['product_price'] = 0;
                        $addData['product_market_price'] = 0;
                        $addData['product_promote_price'] = 0;
                        $addData['product_warn_number'] = 0;
                        $addData['admin_id'] = $ru_id;
                        $re = $this->productsChangeLogModel->addProductsChangeLog($addData);
                        $goods_attr = explode('|', $re->goods_attr);
                        foreach ($goods_attr as $k => $val) {
                            $goods_attr_names[$k] = $goodsAttrArr[$val]->attr_value;
                            $attr_ids[$k] = 'attr[' . $goodsAttrArr[$val]->attr_id . '][]';
                            $goods_attr_id[] = 'goods_attr_id[' . $val . '][]';
                        }
                        $re->changelog_product_id = $re->product_id;
                        $re->goods_attr_names = $goods_attr_names;
                        $re->goods_attr = $goods_attr;
                        $re->goods_attr_id = $goods_attr_id;
                        $re->attr_ids = $attr_ids;
                        $products[] = $re;
                    }
                }
            }
        }
//        其他模式
//        elseif($req->model_price == 1){
//            $products = $this->productsModel->getProducts(['goods_id' => $id]);
//            foreach ($products as $value){
//
//            }
//        }elseif($req->model_price == 2){
//            $products = $this->productsModel->getProducts(['goods_id' => $id]);
//            foreach ($products as $value){
//
//            }
//        }
        $rep['products'] = $products;
        $goodsAttr = $this->goodsAttrModel->getGoodsAttrsJoinAttr(['goods_id' => $id, 'attr_type' => 1, 'attr_checked' => 1]);
        $goodsAttrM = [];
        foreach ($goodsAttr as $value) {
            $attrValues = explode("\r\n", $value->attr_values);
            $value->attr_values = $attrValues;
            $value->attr_sort_n = 'attr_sort[' . $value->attr_id . '][]';
            $value->attr_id_n = 'attr-img[' . $value->attr_id . '][]';
            $value->attr_img_flie_o = url($value->attr_img_flie);
            $goodsAttrM[$value->attr_id]['attr_name'] = $value->attr_name;
            $goodsAttrM[$value->attr_id]['values'][] = $value;
        }
        $rep['goods_attr_img'] = $goodsAttrM;
        return $rep;
    }

    //添加商品
    public function addGoods($data, $ru_id = 0)
    {
        //商品基本信息数据整理
        $goodsData['goods_sn'] = !empty($data['goods_sn']) ? trim($data['goods_sn']) : '';
        $goodsData['user_id'] = $ru_id;
        //检查货号是否重复
        $shopConf = ShopConfig::getConfigAll();
        $snPrefix = $shopConf['sn_prefix'];
        $maxGoodsId = $this->goodsModel->getMaxGoodsId() + 1;
        if ($goodsData['goods_sn']) {
            $goodsCount = $this->goodsModel->countGoods(['user_id' => $ru_id, 'goods_sn' => $goodsData['goods_sn']]);
            if ($goodsCount > 0) {
                $goodsData['goods_sn'] = $snPrefix . '_' . $maxGoodsId . $goodsData['goods_sn'];
            }
        } else {
            $goodsData['goods_sn'] = $snPrefix . '_' . $maxGoodsId . rand(10000, 99999);
        }
        $gallery_id = !empty($data['gallery_pic_id']) ? trim($data['gallery_pic_id']) : '';
        if ($gallery_id == '') {
            $goodsGallery = $this->goodsGalleryModel->getGoodsGallery(['goods_id' => 0]);
        } else {
            $goodsGallery = $this->goodsGalleryModel->getGoodsGallery(['img_id' => $gallery_id]);
        }
        if (!empty($goodsGallery)) {
            $goodsData['goods_thumb'] = $goodsGallery->thumb_url;
            $goodsData['goods_img'] = $goodsGallery->img_url;
            $goodsData['original_img'] = $goodsGallery->img_original;
        }
        $goodsData['shop_price'] = round((!empty($data['shop_price']) ? round($data['shop_price'], 2) : 0), 2);
        $goodsData['market_price'] = round((!empty($data['market_price']) ? round($data['market_price'], 2) : 0), 2);
        $goodsData['cost_price'] = round((!empty($data['cost_price']) ? round($data['cost_price'], 2) : 0), 2);

        $goodsData['goods_desc'] = !empty($data['content']) ? trim($data['content']) : '';
        $goodsData['desc_mobile'] = !empty($data['desc_mobile']) ? trim($data['desc_mobile']) : '';
        $goodsData['goods_brief'] = !empty($data['goods_brief']) ? trim($data['goods_brief']) : '';
        $goodsData['goods_weight'] = !empty($data['goods_weight']) ? $data['goods_weight'] * $data['weight_unit'] : 0;
        $goodsData['is_best'] = !empty($data['is_best']) ? 1 : 0;
        $goodsData['is_new'] = !empty($data['is_new']) ? 1 : 0;
        $goodsData['is_hot'] = !empty($data['is_hot']) ? 1 : 0;
        $goodsData['is_on_sale'] = !empty($data['is_on_sale']) ? 1 : 0;
        $goodsData['is_alone_sale'] = !empty($data['is_alone_sale']) ? 1 : 0;
        $goodsData['is_shipping'] = !empty($data['is_shipping']) ? 1 : 0;
        $goodsData['goods_number'] = !empty($data['goods_number']) ? trim($data['goods_number']) : 0;
        $goodsData['warn_number'] = !empty($data['warn_number']) ? trim($data['warn_number']) : 0;
        $goodsData['goods_type'] = !empty($data['goods_type']) ? trim($data['goods_type']) : 0;
        $goodsData['give_integral'] = !empty($data['give_integral']) ? trim($data['give_integral']) : 0;
        $goodsData['rank_integral'] = !empty($data['rank_integral']) ? trim($data['rank_integral']) : 0;
        $goodsData['integral'] = !empty($data['integral']) ? trim($data['integral']) : 0;
        $goodsData['suppliers_id'] = !empty($data['suppliers_id']) ? trim($data['suppliers_id']) : 0;
        $goodsData['commission_rate'] = !empty($data['commission_rate']) ? trim($data['commission_rate']) : 0;
        $goodsData['keywords'] = !empty($data['keywords']) ? trim($data['keywords']) : '';
        $goodsData['goods_product_tag'] = !empty($data['goods_product_tag']) ? trim($data['goods_product_tag']) : '';
        $goodsData['goods_tag'] = !empty($data['goods_tag']) ? trim($data['goods_tag']) : '';
        $goodsData['seller_note'] = !empty($data['seller_note']) ? trim($data['seller_note']) : '';
        $goodsData['extension_code'] = 'ordinary_buy';
        $goodsData['add_time'] = time();
        $goodsData['last_update'] = time();

        if (!empty($data['goods_video'])) {
            $original_mp4 = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'video';
            $goodsData['goods_video'] = FileHandle::upLoadFlie($data['goods_video'], $original_mp4);
        }

        $goodsData['is_volume'] = !empty($data['is_volume']) ? intval($data['is_volume']) : 0;

        $goodsData['is_fullcut'] = !empty($data['is_fullcut']) ? intval($data['is_fullcut']) : 0;
        $goodsData['review_status'] = 3;
        $goodsData['review_content'] = '';

        /** 微分销 **/
        $goodsData['is_distribution'] = !empty($data['is_distribution']) ? intval($data['is_distribution']) : 5;
        $goodsData['dis_commission'] = !empty($data['is_distribution']) && ($data['dis_commission'] > 0 && $data['dis_commission'] <= 100) ? intval($data['dis_commission']) : 0;

        $goodsData['goods_unit'] = !empty($data['goods_unit']) ? trim($data['goods_unit']) : '个';
        $goodsData['bar_code'] = !empty($data['bar_code']) ? trim($data['bar_code']) : '';

        $goodsData['goods_name_style'] = !empty($data['goods_name_color']) ? trim($data['goods_name_color']) : '#000000';

        $goodsData['cat_id'] = !empty($data['cat_id']) ? intval($data['cat_id']) : '';
        if (empty($data['cat_id'])) {
            //更新您最近使用的商品分类
            $goodsData['cat_id'] = !empty($data['recently_used_category']) ? intval($data['recently_used_category']) : '';
        }

        $goodsData['brand_id'] = !empty($data['brand_id']) ? intval($data['brand_id']) : '';

        //插入admin_user 常用导航分类

        //分期
//        $is_stages = !empty($data['is_stages']) ? intval($data['is_stages']) : 0;
//        if (empty($is_stages)) {
//            $stages = serialize(!empty($data['stages_num']) ? intval($data['stages_num']) : []);
//        }
        //分期费率;
//        $stages_rate = isset($data['stages_rate']) && !empty($data['stages_rate']) ? floatval($data['stages_rate']) : 0;
//        /* ecmoban模板堂  end bylu */

        $goods_model = empty($data['goods_model']) ? 0 : intval($data['goods_model']); //商品模式
        $goodsData['model_price'] = $goods_model;
        $goodsData['model_inventory'] = $goods_model;
        $goodsData['model_attr'] = $goods_model;

        //促销价格和开始结束时间
        $goodsData['is_promote'] = !empty($data['is_promote']) ? trim($data['is_promote']) : 0;
        $goodsData['promote_price'] = round((!empty($data['promote_price']) ? trim($data['promote_price']) : 0), 2);
        $promote_date = !empty($data['promote_date']) ? trim($data['promote_date']) : '';
        if ($promote_date != '' && $goodsData['is_promote'] == 1) {
            $promote_date_arr = explode('～', $promote_date);
            $goodsData['promote_start_date'] = strtotime(!empty($promote_date_arr[0]) ? trim($promote_date_arr[0]) : 0);
            $goodsData['promote_end_date'] = strtotime(!empty($promote_date_arr[1]) ? trim($promote_date_arr[1]) : 0);
        }

        //限购价格和开始结束时间
        $goodsData['is_limit_buy'] = !empty($data['is_limit_buy']) ? trim($data['is_limit_buy']) : 0;
        $goodsData['limit_buy_num'] = !empty($data['limit_buy_num']) ? trim($data['limit_buy_num']) : 0;
        $limit_buy_date = !empty($data['limit_buy_date']) ? trim($data['limit_buy_date']) : '';
        if ($limit_buy_date != '' && $goodsData['is_limit_buy'] == 1) {
            $limit_buy_date_arr = explode('～', $limit_buy_date);
            $goodsData['limit_buy_start_date'] = strtotime(!empty($limit_buy_date_arr[0]) ? trim($limit_buy_date_arr[0]) : 0);
            $goodsData['limit_buy_end_date'] = strtotime(!empty($limit_buy_date_arr[1]) ? trim($limit_buy_date_arr[1]) : 0);
        }

        //促销满减
        $goodsData['is_fullcut'] = !empty($data['is_fullcut']) ? 1 : 0;

        $goodsData['store_new'] = !empty($data['store_new']) ? 1 : 0;
        $goodsData['store_hot'] = !empty($data['store_hot']) ? 1 : 0;
        $goodsData['store_best'] = !empty($data['store_best']) ? 1 : 0;

        $goodsData['goods_name'] = !empty($data['goods_name']) ? trim($data['goods_name']) : '';

        $goodsData['pinyin_keyword'] = Pinyin::Pinyin($goodsData['goods_name'], 'UTF8');

        /* 商品运费 */
        $goodsData['freight'] = !empty($data['freight']) ? intval($data['freight']) : 0;
        $goodsData['shipping_fee'] = !empty($data['shipping_fee']) && $goodsData['freight'] == 1 ? floatval(round($data['shipping_fee'], 2)) : '0.00';
        $goodsData['tid'] = !empty($data['tid']) && $goodsData['freight'] == 2 ? intval($data['tid']) : 0;

        $goodsData['goods_cause'] = "";
        $cause = !empty($data['return_type']) ? $data['return_type'] : [];
        $goodsData['goods_cause'] = implode(',', $cause);

        $goods = $this->goodsModel->addGoods($goodsData);
        if ($goods->goods_id > 0) {
            //更新商品相册
            $this->goodsGalleryModel->setGoodsGallery(['goods_id' => 0], ['goods_id' => $goods->goods_id]);

            //更新扩展分类
            $this->goodsCateModel->setGoodsCate(['goods_id' => 0], ['goods_id' => $goods->goods_id]);

            //添加商品扩展信息
            $goodsExtend['goods_id'] = $goods->goods_id;
            $goodsExtend['is_reality'] = !empty($data['is_reality']) ? $data['is_reality'] : 0;
            $goodsExtend['is_return'] = !empty($data['is_return']) ? $data['is_return'] : 0;
            $goodsExtend['is_fast'] = !empty($data['is_fast']) ? $data['is_fast'] : 0;
            $this->goodsExtendModel->addGoodsExtend($goodsExtend);

            //添加商品会员优惠信息
            $userPrice = !empty($data['user_price']) ? $data['user_price'] : [];
            $userRank = !empty($data['user_rank']) ? $data['user_rank'] : [];
            $memberPrice['goods_id'] = $goods->goods_id;
            foreach ($userPrice as $key => $value) {
                if ($value != -1) {
                    $memberPrice['user_rank'] = $userRank[$key];
                    $memberPrice['user_price'] = $value;
                    $this->memberPriceModel->addMemberPrice($memberPrice);
                }
            }

            //添加商品唯一属性
            $attr_id_listO = !empty($data['attr_id_listO']) ? $data['attr_id_listO'] : [];
            $attr_value_list = !empty($data['attr_value_list']) ? $data['attr_value_list'] : [];
            $attrData['goods_id'] = $goods->goods_id;
            $attrData['admin_id'] = $ru_id;
            for ($i = 0; $i < count($attr_id_listO); $i++) {
                $attrData['attr_id'] = $attr_id_listO[$i];
                $attrData['attr_value'] = $attr_value_list[$i];
                $attrData['attr_sort'] = 0;
                $attrData['attr_checked'] = 1;
                $this->goodsAttrModel->addGoodsAttr($attrData);
            }

            //添加商品多选属性
            $attr_id_listM = !empty($data['attr_id_listM']) ? $data['attr_id_listM'] : [];
            $attr_value_list1 = !empty($data['attr_value_list1']) ? $data['attr_value_list1'] : [];
            $attr_sort = !empty($data['attr_sort']) ? $data['attr_sort'] : [];
            $attr_img = !empty($data['attr_img']) ? $data['attr_img'] : [];
            $attr = !empty($data['attr']) ? $data['attr'] : [];
            $original_img = 'attr_image' . DIRECTORY_SEPARATOR . 'attr_image' . DIRECTORY_SEPARATOR . 'original_img';
            $thumb_img = 'attr_image' . DIRECTORY_SEPARATOR . 'attr_image' . DIRECTORY_SEPARATOR . 'thumb_img';
            for ($i = 0; $i < count($attr_id_listM); $i++) {
                $attrData['attr_id'] = $attr_id_listM[$i];
                for ($j = 0; $j < count($attr_value_list1[$attr_id_listM[$i]]); $j++) {
                    $attrData['attr_value'] = $attr_value_list1[$attr_id_listM[$i]][$j];
                    $attrData['attr_sort'] = $attr_sort[$attr_id_listM[$i]][$j];
                    if(!empty($attr_img[$attr_id_listM[$i]][$j])){
                        $attrData['attr_img_flie'] = FileHandle::upLoadImage($attr_img[$attr_id_listM[$i]][$j], $original_img);
                        $attrData['attr_gallery_flie'] = FileHandle::upLoadThumbImage($attrData['attr_img_flie'], $thumb_img);
                    }
                    $attr_id = $this->goodsAttrModel->addGoodsAttr($attrData);
                    foreach ($attr[$attr_id_listM[$i]] as $k => $v) {
                        if ($attr[$attr_id_listM[$i]][$k] == $attr_value_list1[$attr_id_listM[$i]][$j]) {
                            $attr[$attr_id_listM[$i]][$k] = $attr_id->goods_attr_id;
                        }
                    }
                }
            }

            //添加货品
            $product_market_price = !empty($data['product_market_price']) ? $data['product_market_price'] : [];
            $product_price = !empty($data['product_price']) ? $data['product_price'] : [];
            $product_promote_price = !empty($data['product_promote_price']) ? $data['product_promote_price'] : [];
            $product_number = !empty($data['product_number']) ? $data['product_number'] : [];
            $product_warn_number = !empty($data['product_warn_number']) ? $data['product_warn_number'] : [];
            $product_sn = !empty($data['product_sn']) ? $data['product_sn'] : [];
            $product_bar_code = !empty($data['product_bar_code']) ? $data['product_bar_code'] : [];
            $product['goods_id'] = $goods->goods_id;
            $product['admin_id'] = $ru_id;
            $goods_attr = [];
            foreach ($attr as $key => $val) {
                foreach ($val as $k => $v) {
                    if (empty($goods_attr[$k])) {
                        $goods_attr[] = $v;
                    } else {
                        $goods_attr[$k] .= '|' . $v;
                    }
                }
            }
            $maxProductsId = $this->productsModel->getMaxProductsId() + 1;
            for ($i = 0; $i < count($goods_attr); $i++) {
                $maxProductsId += $i;
                $product['goods_attr'] = $goods_attr[$i];
                $product['product_sn'] = empty($product_sn[$i]) ? $goodsData['goods_sn'] . 'g_p' . $maxProductsId : $goodsData['goods_sn'] . 'g_p' . $product_sn[$i];
                $product['bar_code'] = empty($product_bar_code[$i]) ? '' : $product_bar_code[$i];
                $product['product_number'] = empty($product_number[$i]) ? 0 : $product_number[$i];
                $product['product_price'] = empty($product_price[$i]) ? 0.00 : $product_price[$i];
                $product['product_promote_price'] = empty($product_promote_price[$i]) ? 0.00 : $product_promote_price[$i];
                $product['product_market_price'] = empty($product_market_price[$i]) ? 0.00 : $product_market_price[$i];
                $product['product_warn_number'] = empty($product_warn_number[$i]) ? 1 : $product_warn_number[$i];
                $this->productsModel->addProduct($product);
            }

            //阶梯价格买多少单价为多少
            $volumePrice['goods_id'] = $goods->goods_id;
            $volumePrice['price_type'] = 1;
            $volume_number = !empty($data['volume_number']) ? $data['volume_number'] : [];
            $volume_price = !empty($data['volume_price']) ? $data['volume_price'] : [];
            if ($goodsData['is_volume'] == 1) {
                for ($i = 0; $i < count($volume_number); $i++) {
                    $volumePrice['volume_number'] = $volume_number[$i];
                    $volumePrice['volume_price'] = $volume_price[$i];
                    $this->goodsVolumePriceModel->addGoodsVolumePrice($volumePrice);
                }
            }
        }
        $rep = ['code' => 1, 'msg' => '修改成功'];
        return $rep;
    }

    //更新商品
    public function updateGoods($data, $id, $ru_id = 0)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['goods_id'] = $id;
        //商品基本信息数据整理
        $goodsData['goods_sn'] = !empty($data['goods_sn']) ? trim($data['goods_sn']) : '';
        $goodsData['user_id'] = $ru_id;
        //检查货号是否重复
        $shopConf = ShopConfig::getConfigAll();
        $snPrefix = $shopConf['sn_prefix'];
        $maxGoodsId = $this->goodsModel->getMaxGoodsId() + 1;
        if (!$goodsData['goods_sn']) {
            $goodsData['goods_sn'] = $snPrefix . '_' . $maxGoodsId . rand(10000, 99999);
        }
        $gallery_id = !empty($data['gallery_pic_id']) ? trim($data['gallery_pic_id']) : '';
        if (!empty($data['goods_gallery_id'])) {
            $gallery_id = trim($data['goods_gallery_id']);
        }
        if ($gallery_id == '') {
            $goodsGallery = $this->goodsGalleryModel->getGoodsGallery(['goods_id' => 0]);
        } else {
            $goodsGallery = $this->goodsGalleryModel->getGoodsGallery(['img_id' => $gallery_id]);
        }
        if (!empty($goodsGallery)) {
            $goodsData['goods_thumb'] = $goodsGallery->thumb_url;
            $goodsData['goods_img'] = $goodsGallery->img_url;
            $goodsData['original_img'] = $goodsGallery->img_original;
        }
        $goodsData['shop_price'] = round((!empty($data['shop_price']) ? round($data['shop_price'], 2) : 0), 2);
        $goodsData['market_price'] = round((!empty($data['market_price']) ? round($data['market_price'], 2) : 0), 2);
        $goodsData['cost_price'] = round((!empty($data['cost_price']) ? round($data['cost_price'], 2) : 0), 2);

        $goodsData['goods_desc'] = !empty($data['content']) ? trim($data['content']) : '';
        $goodsData['desc_mobile'] = !empty($data['desc_mobile']) ? trim($data['desc_mobile']) : '';
        $goodsData['goods_brief'] = !empty($data['goods_brief']) ? trim($data['goods_brief']) : '';
        $goodsData['goods_weight'] = !empty($data['goods_weight']) ? $data['goods_weight'] * $data['weight_unit'] : 0;
        $goodsData['is_best'] = !empty($data['is_best']) ? 1 : 0;
        $goodsData['is_new'] = !empty($data['is_new']) ? 1 : 0;
        $goodsData['is_hot'] = !empty($data['is_hot']) ? 1 : 0;
        $goodsData['is_on_sale'] = !empty($data['is_on_sale']) ? 1 : 0;
        $goodsData['is_alone_sale'] = !empty($data['is_alone_sale']) ? 1 : 0;
        $goodsData['is_shipping'] = !empty($data['is_shipping']) ? 1 : 0;
        $goodsData['goods_number'] = !empty($data['goods_number']) ? trim($data['goods_number']) : 0;
        $goodsData['warn_number'] = !empty($data['warn_number']) ? trim($data['warn_number']) : 0;
        $goodsData['goods_type'] = !empty($data['goods_type']) ? trim($data['goods_type']) : 0;
        $goodsData['give_integral'] = !empty($data['give_integral']) ? trim($data['give_integral']) : 0;
        $goodsData['rank_integral'] = !empty($data['rank_integral']) ? trim($data['rank_integral']) : 0;
        $goodsData['integral'] = !empty($data['integral']) ? trim($data['integral']) : 0;
        $goodsData['suppliers_id'] = !empty($data['suppliers_id']) ? trim($data['suppliers_id']) : 0;
        $goodsData['commission_rate'] = !empty($data['commission_rate']) ? trim($data['commission_rate']) : 0;
        $goodsData['keywords'] = !empty($data['keywords']) ? trim($data['keywords']) : '';
        $goodsData['goods_product_tag'] = !empty($data['goods_product_tag']) ? trim($data['goods_product_tag']) : '';
        $goodsData['goods_tag'] = !empty($data['goods_tag']) ? trim($data['goods_tag']) : '';
        $goodsData['seller_note'] = !empty($data['seller_note']) ? trim($data['seller_note']) : '';
        $goodsData['extension_code'] = 'ordinary_buy';
        $goodsData['add_time'] = time();
        $goodsData['last_update'] = time();
        $goods_video_url = !empty($data['goods_video_url']) ? trim($data['goods_video_url']) : '';
        if (!empty($data['goods_video'])) {
            $original_mp4 = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'video';
            $goodsData['goods_video'] = FileHandle::upLoadFlie($data['goods_video'], $original_mp4);
            FileHandle::deleteFile($goods_video_url);
        }

        $goodsData['is_volume'] = !empty($data['is_volume']) ? intval($data['is_volume']) : 0;
        $goodsData['is_fullcut'] = !empty($data['is_fullcut']) ? intval($data['is_fullcut']) : 0;
        $goodsData['review_status'] = 3;
        $goodsData['review_content'] = '';

        /** 微分销 **/
        $goodsData['is_distribution'] = !empty($data['is_distribution']) ? intval($data['is_distribution']) : 5;
        $goodsData['dis_commission'] = !empty($data['is_distribution']) && ($data['dis_commission'] > 0 && $data['dis_commission'] <= 100) ? intval($data['dis_commission']) : 0;

        $goodsData['goods_unit'] = !empty($data['goods_unit']) ? trim($data['goods_unit']) : '个';
        $goodsData['bar_code'] = !empty($data['bar_code']) ? trim($data['bar_code']) : '';

        $goodsData['goods_name_style'] = !empty($data['goods_name_color']) ? trim($data['goods_name_color']) : '#000000';

        $goodsData['cat_id'] = !empty($data['cat_id']) ? intval($data['cat_id']) : '';
        if (empty($data['cat_id'])) {
            //更新您最近使用的商品分类
            $goodsData['cat_id'] = !empty($data['recently_used_category']) ? intval($data['recently_used_category']) : '';
        }

        $goodsData['brand_id'] = !empty($data['brand_id']) ? intval($data['brand_id']) : '';

        //插入admin_user 常用导航分类

        //分期
//        $is_stages = !empty($data['is_stages']) ? intval($data['is_stages']) : 0;
//        if (empty($is_stages)) {
//            $stages = serialize(!empty($data['stages_num']) ? intval($data['stages_num']) : []);
//        }
        //分期费率;
//        $stages_rate = isset($data['stages_rate']) && !empty($data['stages_rate']) ? floatval($data['stages_rate']) : 0;
//        /* ecmoban模板堂  end bylu */

        $goods_model = empty($_REQUEST['goods_model']) ? 0 : intval($_REQUEST['goods_model']); //商品模式
        $goodsData['model_price'] = $goods_model;
        $goodsData['model_inventory'] = $goods_model;
        $goodsData['model_attr'] = $goods_model;

        //促销价格和开始结束时间
        $goodsData['is_promote'] = !empty($data['is_promote']) ? trim($data['is_promote']) : 0;
        $goodsData['promote_price'] = round((!empty($data['promote_price']) ? trim($data['promote_price']) : 0), 2);
        $promote_date = !empty($data['promote_date']) ? trim($data['promote_date']) : '';
        if ($promote_date != '' && $goodsData['is_promote'] == 1) {
            $promote_date_arr = explode('～', $promote_date);
            $goodsData['promote_start_date'] = strtotime(!empty($promote_date_arr[0]) ? trim($promote_date_arr[0]) : 0);
            $goodsData['promote_end_date'] = strtotime(!empty($promote_date_arr[1]) ? trim($promote_date_arr[1]) : 0);
        }

        //限购价格和开始结束时间
        $goodsData['is_limit_buy'] = !empty($data['is_limit_buy']) ? trim($data['is_limit_buy']) : 0;
        $goodsData['limit_buy_num'] = !empty($data['limit_buy_num']) ? trim($data['limit_buy_num']) : 0;
        $limit_buy_date = !empty($data['limit_buy_date']) ? trim($data['limit_buy_date']) : '';
        if ($limit_buy_date != '' && $goodsData['is_limit_buy'] == 1) {
            $limit_buy_date_arr = explode('～', $limit_buy_date);
            $goodsData['limit_buy_start_date'] = strtotime(!empty($limit_buy_date_arr[0]) ? trim($limit_buy_date_arr[0]) : 0);
            $goodsData['limit_buy_end_date'] = strtotime(!empty($limit_buy_date_arr[1]) ? trim($limit_buy_date_arr[1]) : 0);
        }

        //促销满减
        $goodsData['is_fullcut'] = !empty($data['is_fullcut']) ? 1 : 0;

        $goodsData['store_new'] = !empty($data['store_new']) ? 1 : 0;
        $goodsData['store_hot'] = !empty($data['store_hot']) ? 1 : 0;
        $goodsData['store_best'] = !empty($data['store_best']) ? 1 : 0;

        $goodsData['goods_name'] = !empty($data['goods_name']) ? trim($data['goods_name']) : '';

        $goodsData['pinyin_keyword'] = Pinyin::Pinyin($goodsData['goods_name'], 'UTF8');

        /* 商品运费 */
        $goodsData['freight'] = !empty($data['freight']) ? intval($data['freight']) : 0;
        $goodsData['shipping_fee'] = !empty($data['shipping_fee']) && $goodsData['freight'] == 1 ? floatval(round($data['shipping_fee'], 2)) : '0.00';
        $goodsData['tid'] = !empty($data['tid']) && $goodsData['freight'] == 2 ? intval($data['tid']) : 0;

        $goodsData['goods_cause'] = "";
        $cause = !empty($data['return_type']) ? $data['return_type'] : [];
        $goodsData['goods_cause'] = implode(',', $cause);
        $re = $this->goodsModel->setGoods($where, $goodsData);

        //更新商品相册
        $this->goodsGalleryModel->setGoodsGallery(['goods_id' => 0], ['goods_id' => $id]);

        //更新扩展分类
        $this->goodsCateModel->setGoodsCate(['goods_id' => 0], ['goods_id' => $id]);

        //阶梯价格买多少单价为多少
        $whereVolume['goods_id'] = $id;
        $volumePrice['price_type'] = 1;
        $volume_number = !empty($data['volume_number']) ? $data['volume_number'] : [];
        $volume_price = !empty($data['volume_price']) ? $data['volume_price'] : [];
        $volume_price_ids = !empty($data['volume_id']) ? $data['volume_id'] : [];
        if ($goodsData['is_volume'] == 1) {
            for ($i = 0; $i < count($volume_number); $i++) {
                $whereVolume['id'] = $volume_price_ids[$i];
                $volumePrice['volume_number'] = $volume_number[$i];
                $volumePrice['volume_price'] = $volume_price[$i];
                $this->goodsVolumePriceModel->setGoodsVolumePrice($whereVolume, $volumePrice);
            }
        }

        //更新商品扩展信息
        if ($this->goodsExtendModel->countGoodsExtend(['goods_id' => $id])) {
            $goodsExtend['is_reality'] = !empty($data['is_reality']) ? $data['is_reality'] : 0;
            $goodsExtend['is_return'] = !empty($data['is_return']) ? $data['is_return'] : 0;
            $goodsExtend['is_fast'] = !empty($data['is_fast']) ? $data['is_fast'] : 0;
            $this->goodsExtendModel->setGoodsExtend($where, $goodsExtend);
        } else {
            $goodsExtend['goods_id'] = $id;
            $goodsExtend['is_reality'] = !empty($data['is_reality']) ? $data['is_reality'] : 0;
            $goodsExtend['is_return'] = !empty($data['is_return']) ? $data['is_return'] : 0;
            $goodsExtend['is_fast'] = !empty($data['is_fast']) ? $data['is_fast'] : 0;
            $this->goodsExtendModel->addGoodsExtend($goodsExtend);
        }

        //更新商品会员优惠信息
        $userPrice = !empty($data['user_price']) ? $data['user_price'] : [];
        $userRank = !empty($data['user_rank']) ? $data['user_rank'] : [];
        $whereMember['goods_id'] = $id;
        foreach ($userPrice as $key => $value) {
            if ($value != -1) {
                $whereMember['user_rank'] = $userRank[$key];
                if ($this->memberPriceModel->countMemberPrice($whereMember)) {
                    $memberPrice['user_rank'] = $userRank[$key];
                    $memberPrice['user_price'] = $value;
                    $this->memberPriceModel->setMemberPrice($whereMember, $memberPrice);
                } else {
                    $memberPrice['user_rank'] = $userRank[$key];
                    $memberPrice['user_price'] = $value;
                    $this->memberPriceModel->addMemberPrice($memberPrice);
                }
            }
        }

        //更新商品单一属性
        $attr_id_listO = !empty($data['attr_id_listO']) ? $data['attr_id_listO'] : [];
        $attr_value_list = !empty($data['attr_value_list']) ? $data['attr_value_list'] : [];
        $goods_attr_id_list = !empty($data['goods_attr_id_list']) ? $data['goods_attr_id_list'] : [];
        $whereAttr['goods_id'] = $id;
        $attrData['admin_id'] = $ru_id;
        for ($i = 0; $i < count($attr_id_listO); $i++) {
            $whereAttr['goods_attr_id'] = $goods_attr_id_list[$i];
            $attrData['attr_id'] = $attr_id_listO[$i];
            $attrData['attr_value'] = $attr_value_list[$i];
            $attrData['attr_sort'] = 0;
            $attrData['attr_checked'] = 1;
            $this->goodsAttrModel->setGoodsAttr($whereAttr, $attrData);
        }

        //更新货品
        $product_id = !empty($data['product_id']) ? $data['product_id'] : [];
        $goods_attr_id = !empty($data['goods_attr_id']) ? $data['goods_attr_id'] : [];
        $product_market_price = !empty($data['product_market_price']) ? $data['product_market_price'] : [];
        $product_price = !empty($data['product_price']) ? $data['product_price'] : [];
        $product_promote_price = !empty($data['product_promote_price']) ? $data['product_promote_price'] : [];
        $product_number = !empty($data['product_number']) ? $data['product_number'] : [];
        $product_warn_number = !empty($data['product_warn_number']) ? $data['product_warn_number'] : [];
        $product_sn = !empty($data['product_sn']) ? $data['product_sn'] : [];
        $product_bar_code = !empty($data['product_bar_code']) ? $data['product_bar_code'] : [];
        $pdids = $this->productsModel->getProducts($where, ['product_id']);
        foreach ($pdids as $pid){
            if(!in_array($pid->product_id, $product_id)){
                $this->productsModel->delProduct(['product_id'=>$pid->product_id]);
            }
        }
        $maxProductsId = $this->productsModel->getMaxProductsId() + 1;
        foreach ($product_id as $key => $value){
            if($value != 0){
                $product['product_number'] = empty($product_number[$key]) ? 0 : $product_number[$key];
                $product['product_price'] = empty($product_price[$key]) ? 0.00 : $product_price[$key];
                $product['product_promote_price'] = empty($product_promote_price[$key]) ? 0.00 : $product_promote_price[$key];
                $product['product_market_price'] = empty($product_market_price[$key]) ? 0.00 : $product_market_price[$key];
                $product['product_warn_number'] = empty($product_warn_number[$key]) ? 1 : $product_warn_number[$key];
                $this->productsModel->setProduct(['product_id'=>$value], $product);
            }else{
                $goods_attr = '';
                foreach ($goods_attr_id as $k => $val){
                    $goods_attr .= $val[$key].'|';
                }
                $goods_attr = substr($goods_attr, 0, -1);
                $maxProductsId += $i;
                $product['goods_id'] = $id;
                $product['admin_id'] = $ru_id;
                $product['goods_attr'] = $goods_attr;
                $product['product_sn'] = empty($product_sn[$key]) ? $goodsData['goods_sn'] . 'g_p' . $maxProductsId : $goodsData['goods_sn'] . 'g_p' . $product_sn[$key];
                $product['bar_code'] = empty($product_bar_code[$key]) ? '' : $product_bar_code[$key];
                $product['product_number'] = empty($product_number[$key]) ? 0 : $product_number[$key];
                $product['product_price'] = empty($product_price[$key]) ? 0.00 : $product_price[$key];
                $product['product_promote_price'] = empty($product_promote_price[$key]) ? 0.00 : $product_promote_price[$key];
                $product['product_market_price'] = empty($product_market_price[$key]) ? 0.00 : $product_market_price[$key];
                $product['product_warn_number'] = empty($product_warn_number[$key]) ? 1 : $product_warn_number[$key];
                $this->productsModel->addProduct($product);
            }
        }
        $this->productsChangeLogModel->delAll($where);
        if(1){
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    //商品的状态导航
    public function getGoodsNav()
    {
        $goodsNav['normal'] = ['title' => '普通商品', 'navType' => 'normal', 'count' => $this->goodsModel->getGoodsCount(['is_delete' => 0], [], ['review_status', ['3', '5']])];
        $goodsNav['virtual'] = ['title' => '虚拟商品', 'navType' => 'fictitious', 'count' => $this->goodsModel->getGoodsCount(['extension_code' => 'virtual_card'])];
        $goodsNav['examine'] = ['title' => '审核商品', 'navType' => 'examine', 'count' => $this->goodsModel->getGoodsCount(['review_status' => 2], ['review_status' => 1])];
        $goodsNav['recovery'] = ['title' => '回收商品', 'navType' => 'delete', 'count' => $this->goodsModel->getGoodsCount(['is_delete' => 1])];
        $goodsNav['onsale'] = ['title' => '上架商品', 'navType' => 'onsale', 'count' => $this->goodsModel->getGoodsCount(['is_on_sale' => 1])];
        $goodsNav['offsale'] = ['title' => '下架商品', 'navType' => 'offsale', 'count' => $this->goodsModel->getGoodsCount(['is_on_sale' => 0])];
        return $goodsNav;
    }

    //设置商品状态
    public function setGoods($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $updata = [];
        $where['goods_id'] = $data['id'];
        switch ($data['type']) {
            case 'is_best':
                $updata['is_best'] = $data['val'];
                break;
            case 'is_new':
                $updata['is_new'] = $data['val'];
                break;
            case 'is_hot':
                $updata['is_hot'] = $data['val'];
                break;
            case 'is_on_sale':
                $updata['is_on_sale'] = $data['val'];
                break;
            case 'order':
                $updata['sort_order'] = $data['val'];
                break;
            case 'is_delete':
                $updata['is_delete'] = $data['val'];
                break;
            case 'is_examine':
                $updata['review_status'] = $data['val'];
                if ($data['val'] == 2) {
                    $updata['review_content'] = $data['review_content'];
                } else {
                    $updata['review_content'] = '';
                }
                break;
            default:
                break;
        }

        $re = $this->goodsModel->setGoods($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    //批量设置商品
    public function setGoodsMore($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $updata = [];
        $where = $data['ids'];
        switch ($data['type']) {
            case 'is_best_on':
                $updata['is_best'] = 1;
                break;
            case 'is_best_off':
                $updata['is_best'] = 0;
                break;
            case 'is_new_on':
                $updata['is_new'] = 1;
                break;
            case 'is_new_off':
                $updata['is_new'] = 0;
                break;
            case 'is_hot_on':
                $updata['is_hot'] = 1;
                break;
            case 'is_hot_off':
                $updata['is_hot'] = 0;
                break;
            case 'is_sale_on':
                $updata['is_on_sale'] = 1;
                break;
            case 'is_sale_off':
                $updata['is_on_sale'] = 0;
                break;
            case 'is_delete':
                $updata['is_delete'] = 1;
                break;
            default:
                break;
        }

        $re = $this->goodsModel->setGoodsMore($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    //商品权重排序
    public function getGoodsByWeightOrder($id)
    {
        $re = $this->intelligentWeightModel->getGoodsWeight($id);
        if ($re) {
            return $re->toArray();
        } else {
            return ['goods_number' => 0, 'return_number' => 0, 'user_number' => 0, 'goods_comment_number' => 0, 'merchants_comment_number' => 0, 'user_attention_number' => 0];
        }
    }

    //获取商品扩展分类
    public function getCateExtend($id)
    {
        $where['goods_id'] = $id;
        $re = $this->goodsCateModel->getGoodsCates($where);
        return $re;
    }

    //添加商品扩展分类
    public function addCateExtend($data)
    {
        $rep = ['code' => 0, 'msg' => '操作失败'];
        $re = $this->goodsCateModel->addGoodsCate($data);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

    //删除商品扩展分类
    public function delCateExtend($id)
    {
        $rep = ['code' => 0, 'msg' => '操作失败'];
        $where['cat_id'] = $id;
        $re = $this->goodsCateModel->delGoodsCate($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

}