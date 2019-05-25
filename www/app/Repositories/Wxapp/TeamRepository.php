<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\TeamRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Facades\ShopConfig;
use App\Http\Models\Wxapp\BrowseGoodsModel;
use App\Http\Models\Wxapp\GoodsAttrModel;
use App\Http\Models\Wxapp\OrderGoodsModel;
use App\Http\Models\Wxapp\OrderInfoModel;
use App\Http\Models\Wxapp\ProductsModel;
use App\Http\Models\Wxapp\TeamCategoryModel;
use App\Http\Models\Wxapp\TeamGoodsModel;
use App\Http\Models\Wxapp\TeamLogModel;
use App\Http\Models\Wxapp\TransportModel;
use App\Http\Models\Wxapp\UsersModel;

class TeamRepository implements TeamRepositoryInterface
{

    private $teamCategoryModel;
    private $teamGoodsModel;
    private $teamLogModel;
    private $orderInfoModel;
    private $orderGoodsModel;
    private $productsModel;
    private $usersModel;
    private $browseGoodsModel;
    private $goodsAttrModel;
    private $transportModel;

    public function __construct(
        TeamCategoryModel $teamCategoryModel,
        TeamGoodsModel $teamGoodsModel,
        TeamLogModel $teamLogModel,
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        ProductsModel $productsModel,
        UsersModel $usersModel,
        BrowseGoodsModel $browseGoodsModel,
        GoodsAttrModel $goodsAttrModel,
        TransportModel $transportModel
    )
    {
        $this->teamCategoryModel = $teamCategoryModel;
        $this->teamGoodsModel = $teamGoodsModel;
        $this->teamLogModel = $teamLogModel;
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->productsModel = $productsModel;
        $this->usersModel = $usersModel;
        $this->browseGoodsModel = $browseGoodsModel;
        $this->goodsAttrModel = $goodsAttrModel;
        $this->transportModel = $transportModel;
    }

    public function getTeamNav()
    {
        $res = $this->teamCategoryModel->getTeamCates(['parent_id' => 0]);
        foreach ($res as $re) {
            $re->tc_img = FileHandle::getImgByOssUrl($re->tc_img);
        }
        return $res;
    }

    public function getTeam($data)
    {
        $res = $this->teamCategoryModel->getTeamCates(['parent_id' => $data['id']]);

        $whereIn = [];
        $whereIn[] = $data['id'];
        foreach ($res as $re) {
            $whereIn[] = $re->id;
        }
        $goodses = $this->teamCategoryModel->getTeamCatesByGoods($whereIn);
        foreach ($goodses as $goods) {
            $goods->tc_img = FileHandle::getImgByOssUrl($goods->tc_img);
            foreach ($goods->goods as $gs) {
                $gs->original_img = FileHandle::getImgByOssUrl($gs->original_img);
                $gs->team_price_format = Common::priceFormat($gs->team_price);
            }
        }
        $team['goodses'] = $goodses;
        $team['banner'] = array();
        return $team;
    }

    public function getTeamGoods($data, $uid = 0)
    {
        $goods_id = $data['goods_id'];
        $team_id = $data['team_id'];
        $time = time();
        //添加浏览足迹
        if ($uid > 0 && $this->browseGoodsModel->countBrowseGoods(['user_id' => $uid, 'goods_id' => $goods_id]) == 0) {
            $browse_data = [
                'browse_id' => RedisCache::incrby("browse_id"),
                'user_id' => $uid,
                'add_time' => $time,
                'goods_id' => $goods_id,
                'is_attention' => 1,
            ];
            $this->browseGoodsModel->addBrowseGoods($browse_data);
        } else {
            $this->browseGoodsModel->setBrowseGoods(['user_id' => $uid, 'goods_id' => $goods_id], ['add_time' => $time, 'is_attention' => 1]);
        }

        $result = [
            'error' => 0,
            'user_id' => 0,
            'goods_img' => '',         //商品相册
            'goods_info' => '',        //商品信息
            'team_log' => '',          //已成功开团信息
            'new_goods' => '',         //拼团新品
            'goods_properties' => ''   // 商品属性 规格
        ];
        $result['user_id'] = $uid;

        $time = time();

        //是否收藏

        // 商品信息
        $goodsInfo = $this->teamGoodsModel->getTeamGoodsInfo(['goods_id' => $goods_id, 'is_audit' => 2, 'is_team' => 1]);
        if ($goodsInfo) {
            $mobile_descs = unserialize($goodsInfo->goods->desc_mobile);
            unset($goodsInfo->goods->desc_mobile);
            $goodsInfo->goods->mobile_descs = $mobile_descs;
            $goodsInfo->goods->goods_thumb = FileHandle::getImgByOssUrl($goodsInfo->goods->goods_thumb);
            $goodsInfo->goods->original_img = FileHandle::getImgByOssUrl($goodsInfo->goods->original_img);
            $goodsInfo->goods->goods_img = FileHandle::getImgByOssUrl($goodsInfo->goods->goods_img);
            foreach ($goodsInfo->ggallery as $gallery) {
                $gallery->img_original = FileHandle::getImgByOssUrl($gallery->img_original);
                $gallery->img_url = FileHandle::getImgByOssUrl($gallery->img_url);
            }
            //初始值
            $goodsInfo->team_id = 0;

            //验证参团活动是否结束
            if ($team_id) {
                $team_info = $this->teamLogModel->getTeamLog(['team_id' => $team_id]);
                if ($goodsInfo->is_team != 1 || $team_info->status == 1) {
                    return 30006;
                }
                $goodsInfo->team_id = $team_id;
            }

            if ($goodsInfo->goods->is_on_sale == 0) {
                return 30002;
            }
            $goodsInfo->goods->team_price_format = Common::priceFormat($goodsInfo->team_price);  //拼团价格
            $goodsInfo->goods->shop_price_format = Common::priceFormat($goodsInfo->goods->shop_price);
            $goodsInfo->goods->market_price_formated = Common::priceFormat($goodsInfo->goods->market_price);

            //商品属性整理
            $multi_attr = [];
            $single_attr = [];
            foreach ($goodsInfo->gattr as $akey => $gattr) {
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
            unset($goodsInfo->gattr);
            $multi = [];
            foreach ($multi_attr as $mattr) {
                $multi[] = $mattr;
            }
            $goodsInfo->multi_attr = $multi;
            $goodsInfo->single_attr = $single_attr;

            //库存计算
            $products = $this->productsModel->getProdcuts(['goods_id' => $goods_id]);
            $goods_number = 0;
            foreach ($products as $product) {
                $goods_number += $product->product_number;
            }
            $goodsInfo->goods_number = $goods_number;
            $result['goods_info'] = $goodsInfo;  //商品信息

            // 商家信息
            $ruId = $goodsInfo->goods->user_id;

            //获取该商品已成功开团信息
            $team_log = $this->teamLogModel->getTeamLogByAllInfo($goods_id);
            if ($team_log) {
                foreach ($team_log as $key => $val) {
                    $validity_time = $val->start_time + ($val->validity_time * 3600) + (8 * 3600);
                    $val->end_time = $validity_time; //剩余时间
                    //统计该拼团已参与人数
                    $team_num = $this->orderInfoModel->surplusNum($val->team_id);
                    $val->surplus = $val->team_num - $team_num;//还差几人
                    //用户名、头像
                    $val->user->user_name = !empty($val->user->nick_name) ? $val->user->nick_name : $val->user->user_name;
                    $val->user->logo = FileHandle::getImgByOssUrl($val->user->logo);

                    //验证是否参团
                    $val->is_team = 0;
                    $team_join = $this->orderInfoModel->teamJoin($uid, $val->team_id);
                    if ($team_join > 0) {
                        $val->is_team = 1;
                    }
                    //过滤到期的拼团
                    if ($validity_time <= $time) {
                        unset($team_log[$key]);
                    }
                }
                $result['team_log'] = $team_log;
            }

            return $result;
        } else {
            return 30006;
        }

        //获取拼团新品
//        $new_goods = $this->teamRepository->teamNewGoods('is_new', $goodsInfo['user_id']);
//        foreach ($new_goods as $key => $val) {
//            $new_goods[$key]['goods_thumb'] = get_image_path($val['goods_thumb']);
//            $new_goods[$key]['shop_price'] = price_format($val['shop_price'], true);
//            $new_goods[$key]['team_price'] = price_format($val['team_price'], true);
//        }
//        $result['new_goods'] = $new_goods;
    }

    public function setTeamBuy($data, $uid = 0)
    {
        $return = [];
        $time = time();
        $t_id = $data['t_id'];
        $team_id = $data['team_id'];

        //fixme 检查地址和身份证
        $uwhere['user_id'] = $uid;
        $user = $this->usersModel->getUserAndExt($uwhere, ['user_id', 'address_id']);
        if ($user) {
            if (!$user->address) {
                return 40002;
            }
            if (!$user->real || $user->real->review_status != 1) {
                return 50001;
            }
        }

        // fixme 终端来源
        $froms = empty($data['froms']) ? 'app' : trim($data['froms']);

        // fixme 直接购买
        if (!empty($data['goods_id'])) {
            $goods_id = intval($data['goods_id']);
            $num = empty($data['num']) ? 1 : intval($data['num']);
            $goods_attr_ids = [];
            if (!empty($data['attr_id'])) {
                $goods_attr = $this->goodsAttrModel->getGoodsAttrs([], 'goods_attr_id', $data['attr_id']);
                foreach ($goods_attr as $attr) {
                    $attr_ids[] = $attr->attr_id;
                    $goods_attr_ids[] = $attr->goods_attr_id;
                }
                // fixme 检查product商品库存
                sort($goods_attr_ids);
                $product = $this->productsModel->getProdcut(['goods_id' => $goods_id, 'goods_attr' => implode('|', $goods_attr_ids)]);
                if (!$product) {
                    rsort($goods_attr_ids);
                    $product = $this->productsModel->getProdcut(['goods_id' => $goods_id, 'goods_attr' => implode('|', $goods_attr_ids)]);
                }
                if ($num > $product->product_number) {
                    return 30003;
                }
            }
            // fixme 购买的商品信息
            $where['goods_id'] = $goods_id;
            $where['is_audit'] = 2;
            $where['is_team'] = 1;
            $goods_detail = $this->teamGoodsModel->getTeamGoodsInfo($where);
            if (!$goods_detail) {
                return 30006;
            }
            $shipping_fee = 0; //快递费用

            // fixme 检查goods商品库存
            if ($goods_detail->goods->goods_number < $num) {
                return 30003;
            }

            //fixme 限购
            if ($goods_detail->astrict_num < $num) {
                return 30004;
            }

            // fixme 检查自己是否已参与
            $order_count = $this->orderInfoModel->teamJoin($uid, $t_id);
            if ($order_count > 0) {
                return 30005;
            }

            //fixme 自己开团
            if ($team_id == 0) {
                $teamLog_data = [
                    'goods_id' => $goods_id,
                    'start_time' => $time,
                    'status' => 0,
                    'is_show' => 1,
                    't_id' => $t_id
                ];
                $team_log = $this->teamLogModel->addTeamLog($teamLog_data);
                $team_id = $team_log->team_id;
            }

            $discount = 0;     //折扣金额
            $tax = 0;          //税费
            $order = [
                'order_id' => RedisCache::incrby("order_id"),
                'order_sn' => date(VCVB_SNDATE, time()) . rand(100000, 999999),
                'user_id' => $uid,
                'order_status' => OS_UNCONFIRMED,
                'pay_status' => PS_UNPAYED,
                'shipping_status' => SS_UNSHIPPED,
                'comment_status' => CS_UNCOMMENT,
                'postscript' => !empty($data['postmsg']) ? @trim($data['postmsg']) : '',    //用户留言
                'consignee' => $user->address->consignee,
                'country' => $user->address->country,
                'province' => $user->address->province,
                'city' => $user->address->city,
                'district' => $user->address->district,
                'address' => $user->address->address,
                'mobile' => $user->address->mobile,
                'zipcode' => $user->address->zipcode,
                'email' => $user->address->email,
                'best_time' => $user->address->best_time,
                'sign_building' => $user->address->sign_building,
                'auto_delivery_time' => RedisCache::get('shop_config')['sign'],
                'ru_id' => $goods_detail->goods->user_id,
                'extension_code' => 'team_buy',
                'extension_id' => $t_id,
                'team_id' => $team_id,
                'team_parent_id' => $data['team_id'] > 0 ? 0 : $uid,
                'team_user_id' => $data['team_id'] > 0 ? $uid : 0,
                'team_price' => $goods_detail->team_price
            ];

            $order_goods['rec_id'] = RedisCache::incrby("order_goods_id");
            $order_goods['user_id'] = $uid;
            $order_goods['order_id'] = $order['order_id'];
            $order_goods['goods_name'] = $goods_detail->goods->goods_name;
            $order_goods['goods_id'] = $goods_detail->goods->goods_id;
            $order_goods['goods_sn'] = $goods_detail->goods->goods_sn;
            $order_goods['o_goods_number'] = $num;
            $order_goods['market_price'] = $goods_detail->goods->market_price;
            $order_goods['goods_price'] = $goods_detail->goods->shop_price;
            $order_goods['ru_id'] = $goods_detail->goods->user_id;
            $order_goods['is_real'] = empty($goods_detail->goods->is_real) ? 1 : 0;
            $order_goods['extension_code'] = 'team_buy';
            $goods_amount = $goods_detail->goods->shop_price * $num;
            $discount = $goods_detail->goods->shop_price - $goods_detail->team_price;

            //商品扩展信息
            if (!empty($goods_detail->goodsext)) {
                $order_goods['is_reality'] = $goods_detail->goodsext->is_reality;
                $order_goods['is_return'] = $goods_detail->goodsext->is_return;
                $order_goods['is_fast'] = $goods_detail->goodsext->is_fast;
            }


            //商品规格
            if (count($goods_attr_ids) > 0) {
                $pwhere['goods_id'] = $goods_id;
                $product = $this->productsModel->getProdcutAndAttr($goods_attr_ids, ['*'], $pwhere);
                $goods_attr = [];
                foreach ($product->attrs as $attr) {
                    $goods_attr[] = $attr->attr_value;
                }
                $order_goods['product_id'] = $product->product_id;
                $order_goods['goods_attr'] = implode(',', $goods_attr);
                $order_goods['goods_attr_id'] = $product->goods_attr;
                $order_goods['product_sn'] = $product->product_sn;
            }

            //快递
            if ($goods_detail->goods->freight == 2) {
                $ship = $this->transportModel->getTransportByShip(['tid' => $goods_detail->goods->tid]);
                $order_goods['freight'] = $goods_detail->goods->freight;
                $order_goods['tid'] = $goods_detail->goods->tid;
                foreach ($ship->t_ext as $t_ext) {
                    $area_ids = explode(',', $t_ext->area_id);
                    if (in_array($order['city'], $area_ids)) {
                        $order_goods['shipping_fee'] = $t_ext->sprice;
                        $shipping_fee = $t_ext->sprice;
                    }
                }
            } else {
                $order_goods['tid'] = 0;
                $order_goods['freight'] = $goods_detail->goods->freight;
                $order_goods['shipping_fee'] = $goods_detail->goods->shipping_fee;
                $shipping_fee = $goods_detail->goods->shipping_fee;
            }

            $this->orderGoodsModel->addOrderGoods($order_goods);

            $order['goods_amount'] = $goods_amount;
            $order['order_amount'] = $goods_amount - $discount + $tax + $shipping_fee;
            $order['discount'] = $discount;
            $order['tax'] = $tax;
            $order['shipping_fee'] = $shipping_fee;

            $order['referer'] = '本站';
            $order['froms'] = $froms;
            $order['add_time'] = $time;
            $order['is_update_sale'] = 0;
            $re = $this->orderInfoModel->addOrder($order);
            if ($re) {
                $return['order'] = $order['order_id'];
            }
            return $return;
        }
    }

    public function getTeamOrders($data, $uid)
    {
        $where['status'] = empty($data['type']) ? 0 : $data['type'];
        $page = empty($data['page']) ? 1 : $data['page'];
        $size = empty($data['size']) ? 10 : $data['size'];
        $where['user_id'] = $uid;
        $where['extension_code'] = 'team_buy';
        $where['pay_status'] = PS_PAYED;
        $where['order_status'] = OS_CONFIRMED;
        $where['extension_code'] = 'team_buy';
        $column = ['order_info.order_id', 'order_info.user_id', 'order_info.order_status', 'order_info.pay_status', 'order_info.extension_code', 'order_info.extension_id', 'order_info.team_id as o_team_id', 'team_log.*'];
        $orderInfos = $this->orderInfoModel->getOrdersByTeam($where, $page, $size, $column);
        if ($orderInfos->count() > 0) {
            foreach ($orderInfos as $orderInfo) {
                $orderInfo->surplus = $this->orderInfoModel->surplusNum($orderInfo->team_id);
                if($orderInfo->orderGoods->count() > 0){
                    $orderInfo->order_goods = $orderInfo->orderGoods[0];
                    unset($orderInfo->orderGoods);
                    $desc_mobile = unserialize($orderInfo->order_goods->Goods->desc_mobile);
                    $orderInfo->order_goods->Goods->_desc_mobile = $desc_mobile;
                    $orderInfo->order_goods->Goods->goods_video = FileHandle::getImgByOssUrl($orderInfo->order_goods->Goods->goods_video);
                    $orderInfo->order_goods->Goods->original_img = FileHandle::getImgByOssUrl($orderInfo->order_goods->Goods->original_img);
                }
            }
            return $orderInfos;
        } else {
            return 10004;
        }
    }

    public function getTeamRank($data)
    {
        $type = empty($data['type']) ? 0 : $data['type'];
        $page = empty($data['page']) ? 1 : $data['page'];
        $size = empty($data['size']) ? 10 : $data['size'];
        $where = [];
        switch ($type) {
            case 0:
                $where['is_hot'] = 1;
                break;
            case 1:
                $where['is_new'] = 1;
                break;
            case 2:
                $where['is_best'] = 1;
                break;
        }
        $teamLogs = $this->teamLogModel->getTeamLogs($where, $page, $size);
        if ($teamLogs->count() > 0) {
            foreach ($teamLogs as $key => $teamLog) {
                $teamLog->desc_mobile = unserialize($teamLog->desc_mobile);
                $teamLog->goods_video = FileHandle::getImgByOssUrl($teamLog->goods_video);
                $teamLog->original_img = FileHandle::getImgByOssUrl($teamLog->original_img);
                $teamLog->key = $key + 1;
                $teamLog->order_num = $teamLog->order->count();
            }
            return $teamLogs;
        } else {
            return 10004;
        }
    }

    /**
     * 等待成团
     * @param int $uid      会员id
     * @param int $team_id  开团id
     * @param int $user_id  开团会员id
     * @return mixed
     */
    public function teamWait($uid = 0, $team_id = 0, $user_id)
    {
        $result = [
            'team_info' => '',         //拼团信息
            'teamUser' => '',          //已成功开团信息
        ];
        $time = time();
        //订单状态
        //$order_info = $this->teamRepository->orderInfo($team_id, $user_id);

        //获取拼团信息
        $team_info = $this->teamLogModel->teamInfo($team_id);
        $user_info = $this->usersModel->getUser($team_info['team_parent_id']);
        $team_info['user_name'] = !empty($user_info->nick_name) ? $user_info->nick_name : $user_info->user_name;
        $team_info['logo'] = FileHandle::getImgByOssUrl($user_info->logo);
        $team_info['original_img'] = FileHandle::getImgByOssUrl($team_info['original_img']);
        $team_info['team_price'] = Common::priceFormat($team_info['team_price']);

        $end_time = $team_info['start_time'] + ($team_info['validity_time'] * 3600);//剩余时间
        $team_info['end_time'] =$end_time + (8 * 3600);
        $team_num = $this->orderInfoModel->surplusNum($team_info['team_id']);  //统计几人参团
        $team_info['surplus'] = $team_info['team_num'] - $team_num;//还差几人
        $team_info['bar'] = round($team_num * 100 / $team_info['team_num'], 0);//百分比

        if ($team_info['status'] != 1 && $time < $end_time && $team_info['is_team'] == 1) {//进行中
            $team_info['status'] = 0;
        } elseif (($team_info['status'] != 1 && $time > $end_time) || $team_info['is_team'] != 1) {//失败
            $team_info['status'] = 2;
        } elseif ($team_info['status'] = 1) {//成功
            $team_info['status'] = 1;
        }

        //验证是否已经参团
        $team_join = $this->orderInfoModel->teamJoin($uid, $team_id);
        if ($team_join > 0) {
            $team_info['team_join'] = 1;
        }

        $result['team_info'] = $team_info;

        //获取拼团团员信息
        $teamUser = $this->orderInfoModel->teamUserList($team_id, 1, 5);
        foreach ($teamUser as $key => $val) {
            $teamUser[$key]['user_name'] = !empty($val['nick_name']) ? $val['nick_name'] : $val['user_name'];
            $teamUser[$key]['logo'] = FileHandle::getImgByOssUrl($val['logo']);
        }
        $result['teamUser'] = $teamUser;

        return $result;
    }

    public function teamRankingList($page = 1, $size = 10, $type = 0)
    {
        $page = empty($page) ? 1 : $page;
        $arr = [
            'id',             //拼团活动id
            'goods_id',       //商品id
            'goods_name',     //商品名
            'shop_price',     //商品价格
            'goods_thumb',    //商品图片
            'team_price',     //拼团价格
            'team_num',       //几人团
            'limit_num'      //已参团人数
        ];
        $goodsList = $this->teamGoodsModel->teamRankingList($page, $size, $type);
        $list = [];
        foreach ($goodsList as $key => $val) {
            $list[$key]['key'] = $key + 1;
            $list[$key]['id'] = $val['id'];
            $list[$key]['goods_id'] = $val['goods_id'];
            $list[$key]['goods_name'] = $val['goods_name'];
            $list[$key]['shop_price'] = Common::priceFormat($val['shop_price']);
            $list[$key]['goods_thumb'] =  FileHandle::getImgByOssUrl($val['goods_thumb']);
            $list[$key]['team_price'] = Common::priceFormat($val['team_price']);
            $list[$key]['team_num'] = $val['team_num'];
            $list[$key]['limit_num'] = $val['limit_num'];
            $list[$key]['type'] = $type;
        }

        return $list;
    }

    /**
     * 拼团成员页面
     * @param int $team_id
     * @param int $page
     * @param int $size
     * @return mixed
     */
    public function teamUser($team_id = 0, $page = 1, $size = 10)
    {
        $page = empty($page) ? 1 : $page;

        //获取拼团团员信息
        $teamUser = $this->orderInfoModel->teamUserList($team_id, $page, $size);
        $timeFormat = RedisCache::get('shop_config')['time_format'];
        foreach ($teamUser as $key => $val) {
            $teamUser[$key]['add_time'] = date($timeFormat, $val['add_time']); // 时间
            $teamUser[$key]['user_name'] = !empty($val['nick_name']) ? $val['nick_name'] : $val['user_name'];
            $teamUser[$key]['logo'] = FileHandle::getImgByOssUrl($val['logo']);
        }
        return $teamUser;
    }
}