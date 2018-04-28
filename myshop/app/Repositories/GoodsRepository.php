<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsRepositoryInterface;
use App\Facades\LangConfig;
use App\Facades\Url;
use App\Http\Models\Shop\BrandModel;
use App\Http\Models\Shop\GoodsCateModel;
use App\Http\Models\Shop\GoodsExtendModel;
use App\Http\Models\Shop\GoodsModel;
use App\Http\Models\Shop\GoodsTransportModel;
use App\Http\Models\Shop\IntelligentWeightModel;
use App\Http\Models\Shop\ShopConfigModel;

class GoodsRepository implements GoodsRepositoryInterface
{

    private $shopConfigModel;
    private $goodsModel;
    private $goodsCateModel;
    private $brandModel;
    private $goodsTransportModel;
    private $goodsExtendModel;
    private $intelligentWeightModel;

    public function __construct(
        ShopConfigModel $shopConfigModel,
        GoodsModel $goodsModel,
        GoodsCateModel $goodsCateModel,
        BrandModel $brandModel,
        GoodsTransportModel $goodsTransportModel,
        GoodsExtendModel $goodsExtendModel,
        IntelligentWeightModel $intelligentWeightModel
    )
    {
        $this->shopConfigModel = $shopConfigModel;
        $this->goodsModel = $goodsModel;
        $this->goodsCateModel = $goodsCateModel;
        $this->brandModel = $brandModel;
        $this->goodsTransportModel = $goodsTransportModel;
        $this->goodsExtendModel = $goodsExtendModel;
        $this->intelligentWeightModel = $intelligentWeightModel;
    }

    public function getGroupsConfig($groups)
    {
        $item_list = $this->shopConfigModel->getGroupsConfig($groups);

        /* 整理数据 */
        $code_arr = array('shop_logo', 'no_picture', 'watermark', 'shop_slagon', 'wap_logo', 'two_code_logo', 'ectouch_qrcode', 'ecjia_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'business_logo');

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
    public function getGoodsPage($keywords, $size = 10, $data = ['is_delete' => 0])
    {
        $goodsColumns = ['goods_id', 'goods_thumb', 'goods_name', 'user_id', 'brand_id', 'goods_type', 'goods_sn', 'shop_price', 'is_on_sale', 'is_best', 'is_new', 'is_hot', 'sort_order', 'goods_number', 'integral', 'commission_rate', 'is_promote', 'model_price', 'model_inventory', 'model_attr', 'review_status', 'review_content', 'store_best', 'store_new', 'store_hot', 'is_real', 'is_shipping', 'stages', 'goods_thumb', 'is_alone_sale', 'is_limit_buy', 'promote_end_date', 'limit_buy_end_date', 'bar_code', 'freight', 'tid'];
        $where = $data;
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
            if ($transports = $this->goodsTransportModel->getTransportAll($transportColumns)->toArray()) {
                foreach ($transports as $tVal) {
                    $transportFormat[$tVal['tid']] = ['business_id' => $tVal['ru_id'], 'freight_type' => $tVal['freight_type']];
                }
            }

            //商品属性
//            $sql = "SELECT ga.goods_attr_id FROM " . $GLOBALS['ecs']->table('goods_attr') . " AS ga," .
//                $GLOBALS['ecs']->table('attribute') . " AS a" .
//                " WHERE ga.goods_id = '" . $row[$i]['goods_id'] . "' AND ga.attr_id = a.attr_id AND a.attr_type <> 0";

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
        $goodsColumns = ['goods_id', 'goods_thumb', 'goods_name', 'user_id', 'brand_id', 'goods_type', 'goods_sn', 'shop_price', 'is_on_sale', 'is_best', 'is_new', 'is_hot', 'sort_order', 'goods_number', 'integral', 'commission_rate', 'is_promote', 'model_price', 'model_inventory', 'model_attr', 'review_status', 'review_content', 'store_best', 'store_new', 'store_hot', 'is_real', 'is_shipping', 'stages', 'goods_thumb', 'is_alone_sale', 'is_limit_buy', 'promote_end_date', 'limit_buy_end_date', 'bar_code', 'freight', 'tid'];
        return $this->goodsModel->getGoods($id, $goodsColumns);
    }

    //商品的状态导航
    public function getGoodsNav()
    {
        $goodsNav['normal'] = ['title' => '普通商品', 'count' => $this->goodsModel->getGoodsCount(['is_delete' => 0], [], ['review_status', ['3', '5']])];
        $goodsNav['virtual'] = ['title' => '虚拟商品', 'count' => $this->goodsModel->getGoodsCount(['extension_code' => 'virtual_card'])];
        $goodsNav['examine'] = ['title' => '审核商品', 'count' => $this->goodsModel->getGoodsCount(['review_status' => 2], ['review_status' => 1])];
        $goodsNav['recovery'] = ['title' => '回收商品', 'count' => $this->goodsModel->getGoodsCount(['is_delete' => 1])];
        $goodsNav['onsale'] = ['title' => '上架商品', 'count' => $this->goodsModel->getGoodsCount(['is_on_sale' => 1])];
        $goodsNav['offsale'] = ['title' => '下架商品', 'count' => $this->goodsModel->getGoodsCount(['is_on_sale' => 0])];
        return $goodsNav;
    }

    //设置商品
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
                }else{
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
}