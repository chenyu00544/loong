<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\OrderRepositoryInterface;
use App\Facades\Common;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\App\BonusUserModel;
use App\Http\Models\App\CouponsUserModel;
use App\Http\Models\App\FavourableGoodsModel;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\OrderGoodsModel;
use App\Http\Models\App\OrderInfoModel;
use App\Http\Models\App\PaymentModel;
use App\Http\Models\App\ProductsModel;
use App\Http\Models\App\TransportModel;
use App\Http\Models\App\UsersModel;

class OrderRepository implements OrderRepositoryInterface
{

    private $orderInfoModel;
    private $orderGoodsModel;
    private $goodsModel;
    private $favourableGoodsModel;
    private $usersModel;
    private $transportModel;
    private $paymentModel;
    private $productsModel;
    private $bonusUserModel;
    private $couponsUserModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        GoodsModel $goodsModel,
        FavourableGoodsModel $favourableGoodsModel,
        UsersModel $usersModel,
        TransportModel $transportModel,
        PaymentModel $paymentModel,
        ProductsModel $productsModel,
        BonusUserModel $bonusUserModel,
        CouponsUserModel $couponsUserModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->goodsModel = $goodsModel;
        $this->favourableGoodsModel = $favourableGoodsModel;
        $this->usersModel = $usersModel;
        $this->transportModel = $transportModel;
        $this->paymentModel = $paymentModel;
        $this->productsModel = $productsModel;
        $this->bonusUserModel = $bonusUserModel;
        $this->couponsUserModel = $couponsUserModel;
    }

    public function getOrders($data, $uid)
    {
        $page = $data['page'] - 1;
        $order_orwhere = [];
        $order_where = [];
        if (!empty($data['order_type'])) {
            switch ($data['order_type']) {
                case 1:
                    //全部
                    $order_orwhere = [
                        [['order_status', '<>', OS_CANCELED]],
                        [['order_status', '<>', OS_INVALID]],
                    ];
                    $order_where = [
                        ['user_id', '=', $uid],
                    ];
                    break;
                case 2:
                    //待付款
                    $order_orwhere = [
                        [['order_status', '<>', OS_CANCELED]],
                        [['order_status', '<>', OS_INVALID]],
                        [['order_status', '<>', OS_RETURNED]],
                    ];
                    $order_where = [
                        ['pay_status', '=', PS_UNPAYED],
                        ['shipping_status', '=', SS_UNSHIPPED],
                        ['user_id', '=', $uid],
                    ];
                    break;
                case 3:
                    //待发货
                    $order_where = [
                        ['order_status', '=', OS_CONFIRMED],
                        ['pay_status', '=', PS_PAYED],
                        ['shipping_status', '=', SS_UNSHIPPED],
                        ['user_id', '=', $uid],
                    ];
                    break;
                case 4:
                    //待收货
                    $order_where = [
                        ['order_status', '=', OS_CONFIRMED],
                        ['shipping_status', '=', SS_SHIPPED],
                        ['user_id', '=', $uid],
                    ];
                    break;
                case 5:
                    //待评价
                    $order_where = [
                        ['order_status', '=', OS_CONFIRMED],
                        ['shipping_status', '=', SS_RECEIVED],
                        ['comment_status', '=', CS_UNCOMMENT],
                        ['user_id', '=', $uid],
                    ];
                    break;
                default:
                    $order_orwhere = [
                        [['order_status', '<>', OS_CANCELED]],
                        [['order_status', '<>', OS_INVALID]],
                    ];
                    $order_where = [
                        ['user_id', '=', $uid],
                    ];
            }
        }
        $res = $this->orderInfoModel->getOrders($order_where, $order_orwhere, $page, ['*']);
        foreach ($res as $re) {
            $re->add_time_date = date('Y-m-d', $re->add_time);
            $re->current_time = time();
            $re->order_id_str = (string)$re->order_id;
            foreach ($re->orderGoods as $order_goods) {
                $order_goods->goods_thumb = FileHandle::getImgByOssUrl($order_goods->Goods->goods_thumb);
                $order_goods->goods_img = FileHandle::getImgByOssUrl($order_goods->Goods->goods_img);
                $order_goods->original_img = FileHandle::getImgByOssUrl($order_goods->Goods->original_img);
                $order_goods->goods_video = FileHandle::getImgByOssUrl($order_goods->Goods->goods_video);

                $order_goods->market_price_format = Common::priceFormat($order_goods->Goods->market_price);
                $order_goods->shop_price_format = Common::priceFormat($order_goods->Goods->shop_price);
                $order_goods->is_promote = $order_goods->Goods->is_promote;
                $order_goods->promote_start_date = $order_goods->Goods->promote_start_date;
                $order_goods->promote_end_date = $order_goods->Goods->promote_end_date;
                $order_goods->promote_price_format = Common::priceFormat($order_goods->Goods->promote_price);
                $order_goods->is_on_sale = $order_goods->Goods->is_on_sale;
                $order_goods->is_delete = $order_goods->Goods->is_delete;
                $order_goods->current_time = time();
                unset($order_goods->Goods);
            }
        }
        return $res;
    }

    public function getOrder($data, $uid)
    {
        $where['user_id'] = $uid;
        $order_ids = explode(',', $data['order_id']);

        // fixme 付款方式
        $pays = $this->paymentModel->getPayments(['enabled' => 1], ['pay_id', 'pay_code', 'pay_name', 'pay_fee']);
        $return['pays'] = $pays;

        // fixme 用户地址
        $user = $this->usersModel->getUserByAddress($where, ['user_id', 'address_id']);
        $addresses = [];
        if ($user) {
            foreach ($user->addresses as $address) {
                $address->province_name = $address->mapprovince->region_name;
                $address->city_name = $address->mapcity->region_name;
                $address->district_name = $address->mapdistrict->region_name;
                if ($address->address_id == $user->address_id) {
                    $address->def = 1;
                } else {
                    $address->def = 0;
                }
                $addresses[] = $address;
            }
        }
        $return['address'] = $addresses;

        // fixme 优惠券和红包
        $cb = $this->usersModel->getUserByCB($where, ['user_id']);
        $coupons = [];
        foreach ($cb->CouponsUser as $k => $cu) {
            if (!empty($cu->Coupons)) {
                $arr = $cu->Coupons->toArray();
                foreach ($arr as $key => $value) {
                    $cu->$key = $value;
                }
                $cu->cou_start_time_format = date(RedisCache::get('shop_config')['time_format'], $cu->cou_start_time);
                $cu->cou_end_time_format = date(RedisCache::get('shop_config')['time_format'], $cu->cou_end_time);
                unset($cu->Coupons);
                $coupons[] = $cu;
            }
        }
        $return['coupons'] = $coupons;

        $bonus = [];
        foreach ($cb->BonusUser as $k => $bu) {
            if (!empty($bu->Bonus)) {
                $arr = $bu->Bonus->toArray();
                foreach ($arr as $key => $value) {
                    $bu->$key = $value;
                }
                $bu->use_start_date_format = date(RedisCache::get('shop_config')['time_format'], $bu->use_start_date);
                $bu->use_end_date_format = date(RedisCache::get('shop_config')['time_format'], $bu->use_end_date);
                unset($bu->Bonus);
                $bonus[] = $bu;
            }
        }
        $return['bonus'] = $bonus;

        // fixme 订单详情
        $column = ['*'];
        $res = $this->orderInfoModel->getOrder($where, $column, $order_ids);
        foreach ($res as $re) {
            $re->add_time_date = date('Y-m-d', $re->add_time);
            $re->current_time = time();
            $re->order_id_str = (string)$re->order_id;
            foreach ($re->OrderGoods as $order_goods) {
                $order_goods->goods_thumb = FileHandle::getImgByOssUrl($order_goods->Goods->goods_thumb);
                $order_goods->goods_img = FileHandle::getImgByOssUrl($order_goods->Goods->goods_img);
                $order_goods->original_img = FileHandle::getImgByOssUrl($order_goods->Goods->original_img);
                $order_goods->goods_video = FileHandle::getImgByOssUrl($order_goods->Goods->goods_video);

                $order_goods->market_price_format = Common::priceFormat($order_goods->Goods->market_price);
                $order_goods->shop_price_format = Common::priceFormat($order_goods->Goods->shop_price);
                $order_goods->is_promote = $order_goods->Goods->is_promote;
                $order_goods->promote_start_date = $order_goods->Goods->promote_start_date;
                $order_goods->promote_end_date = $order_goods->Goods->promote_end_date;
                $order_goods->promote_price_format = Common::priceFormat($order_goods->Goods->promote_price);
                $order_goods->is_on_sale = $order_goods->Goods->is_on_sale;
                $order_goods->is_delete = $order_goods->Goods->is_delete;
                $order_goods->bonus_type_id = $order_goods->Goods->bonus_type_id;
                $order_goods->current_time = time();
                unset($order_goods->Goods);
            }
        }
        $return['order'] = $res;
        return $return;
    }

    public function setOrder($data, $uid)
    {
        $where['user_id'] = $uid;
        $where['order_id'] = $data['order_id'];
        $updata['order_status'] = $data['order_status'];

        //付款方式
        if (!empty($data['pay'])) {
            $pay = $this->paymentModel->getPayment(['pay_id' => $data['pay']]);
        }

        return $this->orderInfoModel->setOrder($where, $updata);
    }

    public function addOrder($data, $uid)
    {
        $return = [];
        $time = time();

        // fixme 用户地址
        $uwhere['user_id'] = $uid;
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

        // fixme 终端来源
        $froms = empty($data['froms']) ? '' : trim($data['froms']);

        // fixme 直接购买
        if (!empty($data['goods_id'])) {
            $goods_id = intval($data['goods_id']);
            $num = empty($data['num']) ? 1 : intval($data['num']);
            $goods_attr_ids = [];
            for ($i = 0; $i < count($data); $i++) {
                if (!empty($data['attr_' . $i])) {
                    $attr = explode('_', $data['attr_' . $i]);
                    $attr_ids[] = $attr[0];
                    $goods_attr_ids[] = $attr[1];
                }
            }

            //购买的商品信息
            $wherein[] = $goods_id;
            $where['is_on_sale'] = 1;
            $where['is_delete'] = 0;
            $where['review_status'] = 3;
            $column = [
                'goods_id', 'cat_id', 'user_id', 'goods_name', 'goods_sn', 'brand_id', 'freight',
                'goods_number', 'shop_price', 'market_price', 'promote_price', 'promote_start_date', 'promote_end_date',
                'goods_desc', 'goods_id', 'goods_thumb', 'original_img', 'goods_img', 'is_on_sale',
                'is_delete', 'is_best', 'is_new', 'is_hot', 'is_promote', 'is_volume', 'is_fullcut',
                'goods_type', 'is_limit_buy', 'limit_buy_start_date', 'limit_buy_end_date', 'limit_buy_num', 'review_status',
                'sales_volume', 'comments_number', 'tid', 'goods_cause', 'goods_video', 'is_distribution',
                'pinyin_keyword', 'goods_brief'
            ];
            $goodses = $this->goodsModel->getGoodsByOrder($where, $column, $wherein);
            $goods_amount = [];  //商品总金额
            $discount = [];      //折扣金额
            $tax = [];           //税费
            $shipping_fee = []; //快递费用
            $order_goodses = [];

            foreach ($goodses as $goods_detail) {

                if (empty($order[$goods_detail->user_id]['order_id'])) {
                    $goods_amount[$goods_detail->user_id] = 0;
                    $discount[$goods_detail->user_id] = 0;
                    $tax[$goods_detail->user_id] = 0;
                    $order[$goods_detail->user_id] = [
                        'order_id' => RedisCache::incrby("order_id"),
                        'order_sn' => date(VCVB_SNDATE, time()) . rand(100000, 999999),
                        'user_id' => $uid,
                        'order_status' => OS_UNCONFIRMED,
                        'pay_status' => PS_UNPAYED,
                        'shipping_status' => SS_UNSHIPPED,
                        'postscript' => !empty($data['postmsg']) ? @trim($data['postmsg']) : '',    //用户留言
                        'consignee' => $user->default_address->consignee,
                        'country' => $user->default_address->country,
                        'province' => $user->default_address->province,
                        'city' => $user->default_address->city,
                        'district' => $user->default_address->district,
                        'address' => $user->default_address->address,
                        'mobile' => $user->default_address->mobile,
                        'zipcode' => $user->default_address->zipcode,
                        'email' => $user->default_address->email,
                        'best_time' => $user->default_address->best_time,
                        'sign_building' => $user->default_address->sign_building,
                        'ru_id' => $goods_detail->user_id,
                    ];
                }


                $order_goods['rec_id'] = RedisCache::incrby("order_goods_id");
                $order_goods['user_id'] = $uid;
                $order_goods['order_id'] = $order[$goods_detail->user_id]['order_id'];
                $order_goods['goods_name'] = $goods_detail->goods_name;
                $order_goods['goods_id'] = $goods_detail->goods_id;
                $order_goods['goods_sn'] = $goods_detail->goods_sn;
                $order_goods['o_goods_number'] = $num;
                $order_goods['market_price'] = $goods_detail->market_price;
                $order_goods['goods_price'] = $goods_detail->shop_price;
                $order_goods['ru_id'] = $goods_detail->user_id;
                $order_goods['is_real'] = empty($goods_detail->is_real) ? 1 : 0;
                $order_goods['extension_code'] = '';
                $shop_price = $goods_detail->shop_price;
                $g_amount = $shop_price * $num;

                //本身商品的促销活动
                if ($goods_detail->is_promote == 1 && $goods_detail->promote_start_date < $time && $goods_detail->promote_end_date > $time) {
                    $g_amount = $goods_detail->promote_price * $num;
                }

                //本身商品的阶梯价格
                if ($goods_detail->is_volume == 1) {
                    $gvp_k = -1;
                    foreach ($goods_detail->gvp as $key => $gvp) {
                        if ($num >= $gvp->volume_number) {
                            $gvp_k = $key;
                        }
                    }
                    if ($gvp_k >= 0) {
                        $v_amount = $goods_detail->gvp[$gvp_k]->volume_price * $num;
                        if ($g_amount > $v_amount) {
                            $g_amount = $v_amount;
                        }
                    }
                }

                $goods_amount[$goods_detail->user_id] += $g_amount;

                //大型活动
                $faat = $this->favourableGoodsModel->getFaat([['goods_id' => $goods_detail->goods_id], ['brand_id' => $goods_detail->brand_id], ['cate_id' => $goods_detail->cat_id]]);
                if ($faat) {
                    $goods_detail->faat_act_id = $faat->act_id;
                    $goods_detail->faat_act_type = $faat->act_type;
                    $goods_detail->faat_act_type_ext = $faat->act_type_ext;
                    $goods_detail->faat_min_amount = $faat->min_amount;
                    if ($goods_amount[$goods_detail->user_id] > $faat->min_amoun) {
                        if ($faat->act_type == 1) {
                            $discount[$goods_detail->user_id] += $faat->act_type_ext;
                        } elseif ($faat->act_type == 2) {
                            $discount[$goods_detail->user_id] += $goods_amount[$goods_detail->user_id] * (1 - $faat->act_type_ext);
                        }
                    }
                }

                //本身商品的满减活动
                if ($goods_detail->is_fullcut == 1) {
                    $fullcut_k = -1;
                    foreach ($goods_detail->fullcut as $key => $fullcut) {
                        if ($goods_amount[$goods_detail->user_id] >= $fullcut->cfull) {
                            $fullcut_k = $key;
                        }
                    }
                    if ($fullcut_k >= 0) {
                        $discount[$goods_detail->user_id] += $goods_detail->fullcut[$fullcut_k]->creduce;
                    }
                }


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
                if ($goods_detail->freight == 2) {
                    $ship = $this->transportModel->getTransportByShip(['tid' => $goods_detail->tid]);
                    $order_goods['freight'] = $goods_detail->freight;
                    $order_goods['tid'] = $goods_detail->tid;
                    foreach ($ship->t_ext as $t_ext) {
                        $area_ids = explode(',', $t_ext->area_id);
                        if (in_array($order[$goods_detail->user_id]['city'], $area_ids)) {
                            $order_goods['shipping_fee'] = $t_ext->sprice;
                            $shipping_fee[$goods_detail->user_id] = $t_ext->sprice;
                        }
                    }
                } else {
                    $order_goods['tid'] = 0;
                    $order_goods['freight'] = $goods_detail->freight;
                    $order_goods['shipping_fee'] = $goods_detail->shipping_fee;
                    $shipping_fee[$goods_detail->user_id] = $goods_detail->shipping_fee;
                }

                $this->orderGoodsModel->addOrderGoods($order_goods);

                //商品属性整理
                $goods_detail->gattr;
                $multi_attr = [];
                foreach ($goods_detail->gattr as $akey => $gattr) {
                    $gattr->attr_name = $gattr->attr->attr_name;
                    if (in_array($gattr->goods_attr_id, $goods_attr_ids)) {
                        $multi_attr[] = $gattr;
                    }
                    if ($gattr->attr->attr_group == 1 && $gattr->attr->attr_name == '税率') {
                        $goods_detail->tax = $gattr->attr_value;
                        $tax[$goods_detail->user_id] += $goods_amount[$goods_detail->user_id] * ($gattr->attr_value / 100);
                    }
                }
                $goods_detail->multi_attr = $multi_attr;
                unset($goods_detail->gattr);
                $order_goodses[$goods_detail->user_id][] = $goods_detail;

                $order[$goods_detail->user_id]['goods_amount'] = $goods_amount[$goods_detail->user_id];
                $order[$goods_detail->user_id]['order_amount'] = $goods_amount[$goods_detail->user_id] - $discount[$goods_detail->user_id] + $tax[$goods_detail->user_id];
                $order[$goods_detail->user_id]['discount'] = $discount[$goods_detail->user_id];
                $order[$goods_detail->user_id]['tax'] = $tax[$goods_detail->user_id];
                $order[$goods_detail->user_id]['shipping_fee'] = $shipping_fee[$goods_detail->user_id];

                $order[$goods_detail->user_id]['referer'] = '本站';
                $order[$goods_detail->user_id]['froms'] = $froms;
                $order[$goods_detail->user_id]['add_time'] = $time;
                $order[$goods_detail->user_id]['is_update_sale'] = 0;
            }
            foreach ($order as $key => $o) {
                $re = $this->orderInfoModel->addOrder($o);
//                $o['goodses'] = $order_goodses[$key];
                if ($re) {
                    $return['order'][] = $o['order_id'];
                }
            }
            return $return;
        } // fixme 订单再次购买
        elseif (!empty($data['order_id'])) {
            $where['order_id'] = $data['order_id'];
            $column = ['*'];
            $order = $this->orderInfoModel->getOrder($where, $column);
            if ($order->count() == 0) {
                return false;
            }
            $wherein = [];
            $goods_num = [];
            $goods_attr_ids = [];
            foreach ($order[0]->orderGoods as $o_goods) {
                $wherein[] = $o_goods->goods_id;
                $goods_num[$o_goods->goods_id] = $o_goods->o_goods_number;
                $goods_attr_ids[$o_goods->goods_id] = $o_goods->goods_attr_id;
            }
            $gwhere['is_on_sale'] = 1;
            $gwhere['is_delete'] = 0;
            $gwhere['review_status'] = 3;
            $column = [
                'goods_id', 'cat_id', 'user_id', 'goods_name', 'goods_sn', 'brand_id', 'freight',
                'goods_number', 'shop_price', 'market_price', 'promote_price', 'promote_start_date', 'promote_end_date',
                'goods_desc', 'goods_id', 'goods_thumb', 'original_img', 'goods_img', 'is_on_sale',
                'is_delete', 'is_best', 'is_new', 'is_hot', 'is_promote', 'is_volume', 'is_fullcut',
                'goods_type', 'is_limit_buy', 'limit_buy_start_date', 'limit_buy_end_date', 'limit_buy_num', 'review_status',
                'sales_volume', 'comments_number', 'tid', 'goods_cause', 'goods_video', 'is_distribution',
                'pinyin_keyword', 'goods_brief'
            ];

            if (count($wherein) == 0) {
                return false;
            }

            $goodses = $this->goodsModel->getGoodsByOrder($gwhere, $column, $wherein);
            $goods_amount = [];  //商品总金额
            $discount = [];      //折扣金额
            $tax = [];           //税费
            $shipping_fee = []; //快递费用
            $order_goodses = [];

            foreach ($goodses as $goods_detail) {
                if (empty($order_new[$goods_detail->user_id]['order_id'])) {
                    $goods_amount[$goods_detail->user_id] = 0;
                    $discount[$goods_detail->user_id] = 0;
                    $tax[$goods_detail->user_id] = 0;
                    $order_new[$goods_detail->user_id] = [
                        'order_id' => RedisCache::incrby("order_id"),
                        'order_sn' => date(VCVB_SNDATE, time()) . rand(100000, 999999),
                        'user_id' => $uid,
                        'order_status' => OS_UNCONFIRMED,
                        'pay_status' => PS_UNPAYED,
                        'shipping_status' => SS_UNSHIPPED,
                        'postscript' => !empty($data['postmsg']) ? @trim($data['postmsg']) : '',    //用户留言
                        'consignee' => $user->default_address->consignee,
                        'country' => $user->default_address->country,
                        'province' => $user->default_address->province,
                        'city' => $user->default_address->city,
                        'district' => $user->default_address->district,
                        'address' => $user->default_address->address,
                        'mobile' => $user->default_address->mobile,
                        'zipcode' => $user->default_address->zipcode,
                        'email' => $user->default_address->email,
                        'best_time' => $user->default_address->best_time,
                        'sign_building' => $user->default_address->sign_building,
                        'ru_id' => $goods_detail->user_id,
                    ];
                }

                $order_goods['rec_id'] = RedisCache::incrby("order_goods_id");
                $order_goods['user_id'] = $uid;
                $order_goods['order_id'] = $order_new[$goods_detail->user_id]['order_id'];
                $order_goods['goods_name'] = $goods_detail->goods_name;
                $order_goods['goods_id'] = $goods_detail->goods_id;
                $order_goods['goods_sn'] = $goods_detail->goods_sn;
                $order_goods['o_goods_number'] = $goods_num[$goods_detail->goods_id];
                $order_goods['market_price'] = $goods_detail->market_price;
                $order_goods['goods_price'] = $goods_detail->shop_price;
                $order_goods['ru_id'] = $goods_detail->user_id;
                $order_goods['is_real'] = empty($goods_detail->is_real) ? 1 : 0;
                $order_goods['extension_code'] = '';
                $shop_price = $goods_detail->shop_price;
                $g_amount = $shop_price * $goods_num[$goods_detail->goods_id];

                //本身商品的促销活动
                if ($goods_detail->is_promote == 1 && $goods_detail->promote_start_date < $time && $goods_detail->promote_end_date > $time) {
                    $g_amount = $goods_detail->promote_price * $goods_num[$goods_detail->goods_id];
                }

                //本身商品的阶梯价格
                if ($goods_detail->is_volume == 1) {
                    $gvp_k = -1;
                    foreach ($goods_detail->gvp as $key => $gvp) {
                        if ($goods_num[$goods_detail->goods_id] >= $gvp->volume_number) {
                            $gvp_k = $key;
                        }
                    }
                    if ($gvp_k >= 0) {
                        $v_amount = $goods_detail->gvp[$gvp_k]->volume_price * $goods_num[$goods_detail->goods_id];
                        if ($g_amount > $v_amount) {
                            $g_amount = $v_amount;
                        }
                    }
                }

                $goods_amount[$goods_detail->user_id] += $g_amount;

                //大型活动
                $faat = $this->favourableGoodsModel->getFaat([['goods_id' => $goods_detail->goods_id], ['brand_id' => $goods_detail->brand_id], ['cate_id' => $goods_detail->cat_id]]);
                if ($faat) {
                    $goods_detail->faat_act_id = $faat->act_id;
                    $goods_detail->faat_act_type = $faat->act_type;
                    $goods_detail->faat_act_type_ext = $faat->act_type_ext;
                    $goods_detail->faat_min_amount = $faat->min_amount;
                    if ($goods_amount[$goods_detail->user_id] > $faat->min_amoun) {
                        if ($faat->act_type == 1) {
                            $discount[$goods_detail->user_id] += $faat->act_type_ext;
                        } elseif ($faat->act_type == 2) {
                            $discount[$goods_detail->user_id] += $goods_amount[$goods_detail->user_id] * (1 - $faat->act_type_ext);
                        }
                    }
                }

                //本身商品的满减活动
                if ($goods_detail->is_fullcut == 1) {
                    $fullcut_k = -1;
                    foreach ($goods_detail->fullcut as $key => $fullcut) {
                        if ($goods_amount[$goods_detail->user_id] >= $fullcut->cfull) {
                            $fullcut_k = $key;
                        }
                    }
                    if ($fullcut_k >= 0) {
                        $discount[$goods_detail->user_id] += $goods_detail->fullcut[$fullcut_k]->creduce;
                    }
                }


                //商品扩展信息
                if (!empty($goods_detail->goodsext)) {
                    $order_goods['is_reality'] = $goods_detail->goodsext->is_reality;
                    $order_goods['is_return'] = $goods_detail->goodsext->is_return;
                    $order_goods['is_fast'] = $goods_detail->goodsext->is_fast;
                }

                //商品规格

                if (!empty($goods_attr_ids[$goods_detail->goods_id])) {
                    $goods_attr_id = explode(',', $goods_attr_ids[$goods_detail->goods_id]);
                    $pwhere['goods_id'] = $goods_detail->goods_id;
                    $product = $this->productsModel->getProdcutAndAttr($goods_attr_id, ['*'], $pwhere);
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
                if ($goods_detail->freight == 2) {
                    $ship = $this->transportModel->getTransportByShip(['tid' => $goods_detail->tid]);
                    if (!empty($ship)) {
                        $order_goods['freight'] = $goods_detail->freight;
                        $order_goods['tid'] = $goods_detail->tid;
                        foreach ($ship->t_ext as $t_ext) {
                            $area_ids = explode(',', $t_ext->area_id);
                            if (in_array($order_new[$goods_detail->user_id]['city'], $area_ids)) {
                                $order_goods['shipping_fee'] = $t_ext->sprice;
                                $shipping_fee[$goods_detail->user_id] = $t_ext->sprice;
                            }
                        }
                    } else {
                        $order_goods['freight'] = 0;
                        $order_goods['tid'] = 0;
                        $order_goods['shipping_fee'] = 0;
                        $shipping_fee[$goods_detail->user_id] = 0;
                    }
                } else {
                    $order_goods['tid'] = 0;
                    $order_goods['freight'] = $goods_detail->freight;
                    $order_goods['shipping_fee'] = $goods_detail->shipping_fee;
                    $shipping_fee[$goods_detail->user_id] = $goods_detail->shipping_fee;
                }

                $this->orderGoodsModel->addOrderGoods($order_goods);

                //商品属性整理
                $goods_detail->gattr;
                $multi_attr = [];
                foreach ($goods_detail->gattr as $akey => $gattr) {
                    $gattr->attr_name = $gattr->attr->attr_name;
                    if (in_array($gattr->goods_attr_id, $goods_attr_ids)) {
                        $multi_attr[] = $gattr;
                    }
                    if ($gattr->attr->attr_group == 1 && $gattr->attr->attr_name == '税率') {
                        $goods_detail->tax = $gattr->attr_value;
                        $tax[$goods_detail->user_id] += $goods_amount[$goods_detail->user_id] * ($gattr->attr_value / 100);
                    }
                }
                $goods_detail->multi_attr = $multi_attr;
                unset($goods_detail->gattr);
                $order_goodses[$goods_detail->user_id][] = $goods_detail;

                $order_new[$goods_detail->user_id]['goods_amount'] = $goods_amount[$goods_detail->user_id];
                $order_new[$goods_detail->user_id]['order_amount'] = $goods_amount[$goods_detail->user_id] - $discount[$goods_detail->user_id] + $tax[$goods_detail->user_id];
                $order_new[$goods_detail->user_id]['discount'] = $discount[$goods_detail->user_id];
                $order_new[$goods_detail->user_id]['tax'] = $tax[$goods_detail->user_id];
                $order_new[$goods_detail->user_id]['shipping_fee'] = $shipping_fee[$goods_detail->user_id];

                $order_new[$goods_detail->user_id]['referer'] = '本站';
                $order_new[$goods_detail->user_id]['froms'] = $froms;
                $order_new[$goods_detail->user_id]['add_time'] = $time;
                $order_new[$goods_detail->user_id]['is_update_sale'] = 0;
            }
            foreach ($order_new as $key => $o) {
                $re = $this->orderInfoModel->addOrder($o);
//                $o['goodses'] = $order_goodses[$key];
                if ($re) {
                    $return['order'][] = $o['order_id'];
                }
            }
            return $return;
        } // fixme 购物车结算购买
        elseif (!empty($data['cart_ids'])) {
            $f = 0;
        }
    }
}