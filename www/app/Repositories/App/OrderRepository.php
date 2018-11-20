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
use App\Http\Models\App\FavourableGoodsModel;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\OrderInfoModel;
use App\Http\Models\App\PaymentModel;
use App\Http\Models\App\TransportModel;
use App\Http\Models\App\UsersModel;

class OrderRepository implements OrderRepositoryInterface
{

    private $orderInfoModel;
    private $goodsModel;
    private $favourableGoodsModel;
    private $usersModel;
    private $transportModel;
    private $paymentModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        GoodsModel $goodsModel,
        FavourableGoodsModel $favourableGoodsModel,
        UsersModel $usersModel,
        TransportModel $transportModel,
        PaymentModel $paymentModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->goodsModel = $goodsModel;
        $this->favourableGoodsModel = $favourableGoodsModel;
        $this->usersModel = $usersModel;
        $this->transportModel = $transportModel;
        $this->paymentModel = $paymentModel;
    }

    public function getOrders($data, $uid)
    {
        $where['user_id'] = $uid;
        $page = $data['page'] - 1;
        $res = $this->orderInfoModel->getOrders($where, $page);
        return $res;
    }

    public function getOrder($data, $uid)
    {
        $where['user_id'] = $uid;
        $where['order_id'] = $data['order_id'];
        $re = $this->orderInfoModel->getOrder($where);
        return $re;
    }

    public function setOrder($data, $uid)
    {
        $where['user_id'] = $uid;
        return $this->orderInfoModel->setOrder($where, $data);
    }

    public function addOrder($data, $uid)
    {
        $return = [];
        if (empty($data['cart_ids'])) {
            //直接购买
            $goods_id = $data['goods_id'];
            $num = empty($data['num']) ? 1 : $data['num'];
            $goods_attr_ids = [];
            for ($i = 0; $i < count($data); $i++) {
                if (!empty($data['attr_' . $i])) {
                    $attr = explode('_', $data['attr_' . $i]);
                    $attr_ids[] = $attr[0];
                    $goods_attr_ids[] = $attr[1];
                }
            }

            if(empty($data['pay'])){
                return [];
            }

            $order['order_sn'] = date(VCVB_SNDATE, time()) . rand(100000, 999999);
            $order['user_id'] = $uid;

            $order['order_status'] = OS_UNCONFIRMED;
            $order['pay_status'] = PS_UNPAYED;
            $order['shipping_status'] = SS_UNSHIPPED;
            //用户留言
            $order['postscript'] = !empty($data['postmsg']) ? $data['postmsg'] : '';


            $pay = $this->paymentModel->getPayment(['pay_id'=>$data['pay']]);

            //用户地址
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
            $return['user'] = $user;
            $order['consignee'] = $user->default_address->consignee;
            $order['country'] = $user->default_address->country;
            $order['province'] = $user->default_address->province;
            $order['city'] = $user->default_address->city;
            $order['district'] = $user->default_address->district;
            $order['address'] = $user->default_address->address;
            $order['mobile'] = $user->default_address->mobile;
            $order['zipcode'] = $user->default_address->zipcode;
            $order['email'] = $user->default_address->email;
            $order['best_time'] = $user->default_address->best_time;
            $order['sign_building'] = $user->default_address->sign_building;

            //购买的商品信息
            $where['goods_id'] = $goods_id;
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
            $goods_detail = $this->goodsModel->getGoodsByOrder($where, $column);
            if ($goods_detail) {

                //大型活动
                $faats = $this->favourableGoodsModel->getFaat([['goods_id' => $goods_detail->goods_id], ['brand_id' => $goods_detail->brand_id], ['cate_id' => $goods_detail->cat_id]]);
                foreach ($faats as $k => $faat) {
                    if ($k == 0) {
                        $goods_detail->faat_act_id = $faat->act_id;
                        $goods_detail->faat_act_type = $faat->act_type;
                        $goods_detail->faat_act_type_ext = $faat->act_type_ext;
                        $goods_detail->faat_min_amount = $faat->min_amount;
                    }
                }

                //快递
                if ($goods_detail->freight == 2) {
                    $twhere['tid'] = $goods_detail->tid;
                    $transport = $this->transportModel->getTransportByShip($twhere);

                    $t_exp_shipping_fee = 0;
                    foreach ($transport->t_exp as $key => $t_exp) {
                        if ($key == 0) {
                            $order['shipping_id'] = $t_exp->shipping->shipping_id;
                            $order['shipping_name'] = $t_exp->shipping->shipping_name;
                            $order['shipping_code'] = $t_exp->shipping->shipping_code;
                            $t_exp_shipping_fee = $t_exp->shipping->shipping_fee;
                        }
                    }

                    //运费计算
                    $t_ext_shipping_fee = 0;
                    foreach ($transport->t_ext as $t_ext) {
                        $top_area_ids = explode(',', $t_ext->top_area_id);
                        if (in_array($user->default_address->province, $top_area_ids)) {
                            $area_ids = explode(',', $t_ext->area_id);
                            if (in_array($user->default_address->city, $area_ids)) {
                                $t_ext_shipping_fee = $t_ext->sprice;
                            }
                        }
                    }

                    $order['shipping_fee'] = $t_exp_shipping_fee + $t_ext_shipping_fee;
                } else {
                    $order['shipping_fee'] = $goods_detail->shipping_fee;
                }

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
                    }
                }
                $goods_detail->multi_attr = $multi_attr;
                unset($goods_detail->gattr);
            }
            $return['goods'][] = $goods_detail;


            dd($order);
            return $return;
        } else {
            //购物车结算购买
        }
    }
}