<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\LangConfig;
use App\Facades\ShopConfig;
use App\Facades\Url;
use App\Http\Models\Shop\BrandModel;
use App\Http\Models\shop\CategoryModel;
use App\Http\Models\Shop\GoodsCateModel;
use App\Http\Models\Shop\GoodsExtendModel;
use App\Http\Models\Shop\GoodsGalleryModel;
use App\Http\Models\Shop\GoodsModel;
use App\Http\Models\Shop\TransportModel;
use App\Http\Models\Shop\IntelligentWeightModel;
use App\Http\Models\Shop\ShopConfigModel;

class GoodsRepository implements GoodsRepositoryInterface
{

    private $shopConfigModel;
    private $goodsModel;
    private $goodsCateModel;
    private $brandModel;
    private $transportModel;
    private $goodsExtendModel;
    private $intelligentWeightModel;
    private $categoryModel;
    private $goodsGalleryModel;

    public function __construct(
        ShopConfigModel $shopConfigModel,
        GoodsModel $goodsModel,
        GoodsCateModel $goodsCateModel,
        BrandModel $brandModel,
        TransportModel $transportModel,
        GoodsExtendModel $goodsExtendModel,
        IntelligentWeightModel $intelligentWeightModel,
        CategoryModel $categoryModel,
        GoodsGalleryModel $goodsGalleryModel
    )
    {
        $this->shopConfigModel = $shopConfigModel;
        $this->goodsModel = $goodsModel;
        $this->goodsCateModel = $goodsCateModel;
        $this->brandModel = $brandModel;
        $this->transportModel = $transportModel;
        $this->goodsExtendModel = $goodsExtendModel;
        $this->intelligentWeightModel = $intelligentWeightModel;
        $this->categoryModel = $categoryModel;
        $this->goodsGalleryModel = $goodsGalleryModel;
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
        $goodsColumns = ['goods_id', 'goods_thumb', 'goods_name', 'user_id', 'brand_id', 'goods_type', 'goods_sn', 'shop_price', 'is_on_sale', 'is_best', 'is_new', 'is_hot', 'sort_order', 'goods_number', 'integral', 'commission_rate', 'is_promote', 'model_price', 'model_inventory', 'model_attr', 'review_status', 'review_content', 'store_best', 'store_new', 'store_hot', 'is_real', 'is_shipping', 'stages', 'goods_thumb', 'is_alone_sale', 'is_limit_buy', 'promote_end_date', 'limit_buy_end_date', 'bar_code', 'freight', 'tid'];
        return $this->goodsModel->getGoods($id, $goodsColumns);
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
        $maxGoodsId = $this->goodsModel->getMaxGoodsId()+1;
        if ($goodsData['goods_sn']) {
            $goodsCount = $this->goodsModel->countGoods(['user_id' => $ru_id, 'goods_sn' => $goodsData['goods_sn']]);
            if ($goodsCount > 0) {
                $goodsData['goods_sn'] = $snPrefix . '_' . $maxGoodsId . rand(10000, 99999);
            }
        } else {
            $goodsData['goods_sn'] = $snPrefix . '_' . $maxGoodsId . rand(10000, 99999);
        }
        $gallery_id = !empty($data['gallery_pic_id']) ? trim($data['gallery_pic_id']) : '';
        if (!empty($data['gallery_pic_id'])) {
            $gallery_id = trim($data['gallery_pic_id']);
        }
        if (!empty($data['goods_gallery_id'])) {
            $gallery_id = trim($data['goods_gallery_id']);
        }
        $goodsGallery = $this->goodsGalleryModel->getGoodsGallery(['img_id' => $gallery_id]);
        if ($goodsGallery->count() > 0) {
            $goodsData['goods_thumb'] = $goodsGallery->thumb_url;
            $goodsData['goods_img'] = $goodsGallery->img_url;
            $goodsData['original_img'] = $goodsGallery->img_original;
        }
        $goodsData['shop_price'] = round((!empty($data['shop_price']) ? trim($data['shop_price']) : 0),2);
        $goodsData['market_price'] = round((!empty($data['market_price']) ? trim($data['market_price']) : 0),2);
        $goodsData['cost_price'] = round((!empty($data['cost_price']) ? trim($data['cost_price']) : 0),2);

        //促销价格和开始结束时间
        $goodsData['is_promote'] = !empty($data['is_promote']) ? trim($data['is_promote']) : 0;
        $goodsData['promote_price'] = round((!empty($data['promote_price']) ? trim($data['promote_price']) : 0),2);
        $goodsData['promote_start_date'] = !empty($data['promote_start_date']) ? trim($data['promote_start_date']) : 0;
        $goodsData['promote_end_date'] = !empty($data['promote_end_date']) ? trim($data['promote_end_date']) : 0;

        $goodsData['goods_weight'] = !empty($data['goods_weight']) ? $data['goods_weight'] * $data['weight_unit'] : 0;
        $goodsData['is_best'] = !empty($data['is_best']) ? 1 : 0;
        $goodsData['is_new'] = !empty($data['is_new']) ? 1 : 0;
        $goodsData['is_hot'] = !empty($data['is_hot']) ? 1 : 0;
        $goodsData['is_on_sale'] = !empty($data['is_on_sale']) ? 1 : 0;
        $goodsData['is_alone_sale'] = !empty($data['is_alone_sale']) ? 1 : 0;
        $goodsData['is_shipping'] = !empty($data['is_shipping']) ? 1 : 0;
        $goodsData['goods_number'] = !empty($data['goods_number']) ? trim($data['goods_number']) : 0;
        $goodsData['warn_number'] = !empty($data['warn_number']) ? trim($data['warn_number']) : 0;
        $goodsData['goods_type'] = !empty($data['cate_id']) ? trim($data['cate_id']) : 0;
        $goodsData['give_integral'] = !empty($data['give_integral']) ? trim($data['give_integral']) : 0;
        $goodsData['rank_integral'] = !empty($data['rank_integral']) ? trim($data['rank_integral']) : 0;
        $goodsData['integral'] = !empty($data['integral']) ? trim($data['integral']) : 0;
        $goodsData['suppliers_id'] = !empty($data['suppliers_id']) ? trim($data['suppliers_id']) : 0;
        $goodsData['commission_rate'] = !empty($data['commission_rate']) ? trim($data['commission_rate']) : 0;

        if(!empty($data['goods_video'])){
            $original_mp4 = 'gallery_album' . DIRECTORY_SEPARATOR . 'goods_gallery' . DIRECTORY_SEPARATOR . 'video';
            $goodsData['goods_video'] = FileHandle::upLoadFlie($data['goods_video'], $original_mp4);dd($goodsData['goods_video']);
        }

//        $is_volume = isset($_POST['is_volume']) && !empty($_POST['is_volume']) ? intval($_POST['is_volume']) : 0;
//        $is_fullcut = isset($_POST['is_fullcut']) && !empty($_POST['is_fullcut']) ? intval($_POST['is_fullcut']) : 0;
//
//        $review_status = isset($_POST['review_status']) ? intval($_POST['review_status']) : 5;
//        $review_content = isset($_POST['review_content']) && !empty($_POST['review_content']) ? addslashes(trim($_POST['review_content'])) : '';
//
//        /* 微分销 */
//        $is_distribution = isset($_POST['is_distribution']) ? intval($_POST['is_distribution']) : 0; //如果选择商品分销则判断分销佣金百分比是否在0-100之间 如果不是则设置无效 liu  dis
//
//        if ($is_distribution == 1) {
//            $dis_commission = ($_POST['dis_commission'] > 0 && $_POST['dis_commission'] <= 100) ? intval($_POST['dis_commission']) : 0;
//        }
//
//        $goods_unit = isset($_POST['goods_unit']) ? trim($_POST['goods_unit']) : '个';//商品单位
//
//        $bar_code = isset($_POST['bar_code']) && !empty($_POST['bar_code']) ? trim($_POST['bar_code']) : '';
//        $goods_name_style = $_POST['goods_name_color'] . '+' . $_POST['goods_name_style'];
//        $other_catids = isset($_POST['other_catids']) ? trim($_POST['other_catids']) : '';
//
//        $catgory_id = empty($_POST['cat_id']) ? '' : intval($_POST['cat_id']);
//        //常用分类 by wu
//        if (empty($catgory_id)) {
//            $catgory_id = intval($_POST['recently_used_category']);
//        }
//
//        $brand_id = empty($_POST['brand_id']) ? '' : intval($_POST['brand_id']);
//
//        //ecmoban模板堂 --zhuo
//        $store_category = !empty($_POST['store_category']) ? intval($_POST['store_category']) : 0;
//        if ($store_category > 0) {
//            $catgory_id = $store_category;
//        }
//
//        if ($is_insert){
//            insert_recently_used_category($catgory_id, $admin_id);
//        }
//
//        /* ecmoban模板堂  序列化分期送期数数据   start bylu */
//        if ($_POST['is_stages']) {
//            $stages = serialize($_POST['stages_num']); //分期期数;
//        }else{
//            $stages = '';
//        }
//
//        $stages_rate = isset($_POST['stages_rate']) && !empty($_POST['stages_rate']) ? floatval($_POST['stages_rate']) : 0; //分期费率;
//        /* ecmoban模板堂  end bylu */
//
//        $adminru = get_admin_ru_id();
//
//        $model_price = isset($_POST['model_price']) ? intval($_POST['model_price']) : 0;
//        $model_inventory = isset($_POST['model_inventory']) ? intval($_POST['model_inventory']) : 0;
//        $model_attr = isset($_POST['model_attr']) ? intval($_POST['model_attr']) : 0;
//
//        //ecmoban模板堂 --zhuo start 限购
//        $xiangou_num = !empty($_POST['xiangou_num']) ? intval($_POST['xiangou_num'])  : 0;
//        $is_xiangou = empty($xiangou_num) ? 0 : 1;
//        $xiangou_start_date = ($is_xiangou && !empty($_POST['xiangou_start_date'])) ? local_strtotime($_POST['xiangou_start_date']) : 0;
//        $xiangou_end_date = ($is_xiangou && !empty($_POST['xiangou_end_date'])) ? local_strtotime($_POST['xiangou_end_date']) : 0;
//        //ecmoban模板堂 --zhuo end 限购
//
//        //ecmoban模板堂 --zhuo start 促销满减
//        $cfull      = isset($_POST['cfull']) ? $_POST['cfull'] : array();
//        $creduce    = isset($_POST['creduce']) ? $_POST['creduce'] : array();
//        $c_id    = isset($_POST['c_id']) ? $_POST['c_id'] : array();
//
//        $sfull      = isset($_POST['sfull']) ? $_POST['sfull'] : array();
//        $sreduce    = isset($_POST['sreduce']) ? $_POST['sreduce'] : array();
//        $s_id    = isset($_POST['s_id']) ? $_POST['s_id'] : array();
//
//        $largest_amount = !empty($_POST['largest_amount']) ? trim($_POST['largest_amount'])  : 0;
//        $largest_amount = floatval($largest_amount);
//        //ecmoban模板堂 --zhuo end 促销满减
//
//        $group_number = !empty($_POST['group_number']) ? intval($_POST['group_number']) : 0;
//
//        $store_new = isset($_POST['store_new']) && !empty($_POST['store_new']) ? 1 : 0;
//        $store_hot = isset($_POST['store_hot']) && !empty($_POST['store_hot']) ? 1 : 0;
//        $store_best = isset($_POST['store_best']) && !empty($_POST['store_best']) ? 1 : 0;
//
//        $goods_name = trim($_POST['goods_name']);
//        //by guan start
//        $pin = new pin();
//        $pinyin = $pin->Pinyin($goods_name, 'UTF8');
//        //by guan end
//
//        $user_cat = !empty($_POST['user_cat']) ? intval($_POST['user_cat']) : 0;
//
//        /* 微分销 */
//        $where_drp_sql = '';
//        $where_drp_val = '';
//        if (file_exists(MOBILE_DRP)) {
//            $where_drp_sql = ", is_distribution, dis_commission";
//            $where_drp_val = ", '$is_distribution', '$dis_commission'";
//        }
//
//        /* 商品运费 by wu start */
//        $freight = empty($_POST['freight']) ? 0 : intval($_POST['freight']);
//        $shipping_fee = !empty($_POST['shipping_fee']) && $freight == 1 ? floatval($_POST['shipping_fee']) : '0.00';
//        $tid = !empty($_POST['tid']) && $freight == 2 ? intval($_POST['tid']) : 0;
//        if ($is_insert) {
//            $freight_insert_key = ", freight, shipping_fee, tid";
//            $freight_insert_val = ", '$freight', '$shipping_fee', '$tid'";
//        } else {
//            $freight_update_data = " freight = '$freight'," .
//                " shipping_fee = '$shipping_fee'," .
//                " tid = '$tid',";
//        }
//        /* 商品运费 by wu end */
//
//        $goods_cause = "";
//        $cause = !empty($_REQUEST['return_type']) ? $_REQUEST['return_type'] : 0;
//        for ($i = 0; $i < count($cause); $i++) {
//            if ($i == 0)
//                $goods_cause = $cause[$i];
//            else
//                $goods_cause = $goods_cause . "," . $cause[$i];
//        }


        dd($data);
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