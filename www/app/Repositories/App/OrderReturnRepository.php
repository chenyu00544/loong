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
use App\Http\Models\App\OrderInfoModel;
use App\Http\Models\App\OrderReturnCauseModel;
use App\Http\Models\App\OrderReturnGoodsModel;
use App\Http\Models\App\OrderReturnImagesModel;
use App\Http\Models\App\OrderReturnModel;
use Illuminate\Support\Facades\DB;

class OrderReturnRepository implements OrderRepositoryInterface
{

    private $orderInfoModel;
    private $orderReturnCauseModel;
    private $orderReturnModel;
    private $orderReturnImagesModel;
    private $orderReturnGoodsModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderReturnCauseModel $orderReturnCauseModel,
        OrderReturnModel $orderReturnModel,
        OrderReturnGoodsModel $orderReturnGoodsModel,
        OrderReturnImagesModel $orderReturnImagesModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderReturnCauseModel = $orderReturnCauseModel;
        $this->orderReturnModel = $orderReturnModel;
        $this->orderReturnGoodsModel = $orderReturnGoodsModel;
        $this->orderReturnImagesModel = $orderReturnImagesModel;
    }

    public function afterSaleOrders($data, $uid)
    {

    }

    public function afterSale($data, $uid)
    {
        $where['user_id'] = $uid;
        $order_ids = explode(',', $data['order_id']);

        // fixme 订单详情
        $column = ['order_id', 'main_order_id', 'order_sn', 'user_id', 'main_order_id', 'order_status', 'pay_status', 'main_order_id', 'shipping_status', 'shipping_time', 'add_time', 'auto_delivery_time', 'shipping_fee', 'goods_amount', 'insure_fee', 'pay_fee', 'pack_fee', 'card_fee', 'main_order_id', 'money_paid', 'order_amount', 'discount', 'tax', 'mobile', 'consignee', 'address', 'country', 'province', 'city', 'district'];
        $res = $this->orderInfoModel->getOrderToAfterSale($where, $column, $order_ids);
        foreach ($res as $re) {
            $re->add_time_date = date('Y-m-d', $re->add_time);
            $re->order_id_str = (string)$re->order_id;
            $re->country_name = $re->mapcountry->region_name;
            $re->province_name = $re->mapprovince->region_name;
            $re->city_name = $re->mapcity->region_name;
            $re->district_name = $re->mapdistrict->region_name;
            unset($re->mapcountry);
            unset($re->mapprovince);
            unset($re->mapcity);
            unset($re->mapdistrict);
            foreach ($re->OrderGoods as $order_goods) {
                $order_goods->goods_thumb = FileHandle::getImgByOssUrl($order_goods->Goods->goods_thumb);
                $order_goods->goods_img = FileHandle::getImgByOssUrl($order_goods->Goods->goods_img);
                $order_goods->original_img = FileHandle::getImgByOssUrl($order_goods->Goods->original_img);
                $order_goods->goods_video = FileHandle::getImgByOssUrl($order_goods->Goods->goods_video);
                $order_goods->market_price_format = Common::priceFormat($order_goods->Goods->market_price);
                $order_goods->shop_price_format = Common::priceFormat($order_goods->Goods->shop_price);
                $order_goods->promote_price_format = Common::priceFormat($order_goods->Goods->promote_price);
                $order_goods->goods_cause = $order_goods->Goods->goods_cause;
                $order_goods->is_promote = $order_goods->Goods->is_promote;
                $order_goods->promote_start_date = $order_goods->Goods->promote_start_date;
                $order_goods->promote_end_date = $order_goods->Goods->promote_end_date;
                $order_goods->is_on_sale = $order_goods->Goods->is_on_sale;
                $order_goods->is_delete = $order_goods->Goods->is_delete;
                $order_goods->bonus_type_id = $order_goods->Goods->bonus_type_id;
                $order_goods->current_time = time();
                unset($order_goods->Goods);
            }
            $return['return_order'] = $re->returnOrder;
            unset($re->returnOrder);
        }
        //fixme 退货原因
        $causes = $this->orderReturnCauseModel->getReturnCauses(['is_show' => 1]);
        $return['causes'] = $causes;
        $return['order'] = $res;

        return $return;
    }

    public function returnGoods($data, $uid)
    {
        $time = time();
        $orders = $this->orderInfoModel->getOrder(['order_id' => $data['order_id']]);
        $cause_id = $data['cause_id'];
        $cause_type = empty($data['cause_type']) ? 1 : $data['cause_type'];
        $return_brief = empty($data['return_brief']) ? '' : $data['return_brief'];
        foreach ($orders as $order) {
            $rorder = $this->orderReturnModel->getOrderReturn(['order_id' => $order->order_id]);
            if (!empty($rorder)) {
                $shop_config = RedisCache::get('shop_config');
                if ($shop_config['activation_number_type'] > $rorder->activation_number) {
                    $rdata = [
                        'agree_apply' => OR_UNAGREE,
                        'activation_number' => $rorder->activation_number + 1
                    ];
                    return $this->orderReturnModel->setOrderReturn(['order_id' => $order->order_id], $rdata);
                } else {
                    return '申请次数为0，请联系客服';
                }
            } else {
                if ($order->chargeoff_status == 0) {
                    if ($order->pay_status == 2 && $order->shipping_status == 1) {
                        $actual_return = $order->money_paid - $order->card_fee - $order->pack_fee - $order->pay_fee - $order->insure_fee - $order->shipping_fee;
                    } elseif ($order->pay_status == 2) {
                        $actual_return = $order->money_paid;
                    }

                    if ($order->pay_status == 2 && $order->shipping_status == 0) {
                        $return_type = 0;
                    } else {
                        $return_type = 1;
                    }

                    $return_order = [
                        'return_status' => RS_USER_RETURN,
                        'refound_status' => RS_NOREFOUND,
                        'agree_apply' => OR_UNAGREE,
                        'return_sn' => date(VCVB_SNDATE, time()) . rand(10000, 99999),
                        'return_type' => $return_type,
                        'user_id' => $uid,
                        'order_id' => $order->order_id,
                        'order_sn' => $order->order_sn,
                        'back' => $cause_type,
                        'cause_id' => $cause_id,
                        'apply_time' => $time,
                        'should_return' => $order->money_paid,
                        'actual_return' => $actual_return,
                        'return_brief' => $return_brief,
                        'ru_id' => $order->ru_id,
                        'chargeoff_status' => $order->chargeoff_status,
                        'country' => $order->country,
                        'province' => $order->province,
                        'city' => $order->city,
                        'district' => $order->district,
                        'street' => $order->street,
                        'addressee' => $order->consignee,
                        'phone' => $order->mobile,
                        'address' => $order->address,
                    ];
                    //退货单
                    DB::beginTransaction();
                    $rorder = $this->orderReturnModel->addOrderReturn($return_order);
                    //退货单商品
                    $rog = [];
                    foreach ($order->orderGoods as $orderGoods) {
                        $return_order_goods = [
                            'rec_id' => $orderGoods->rec_id,
                            'ret_id' => $rorder->ret_id,
                            'goods_id' => $orderGoods->goods_id,
                            'product_id' => $orderGoods->product_id,
                            'product_sn' => $orderGoods->product_sn,
                            'goods_name' => $orderGoods->goods_name,
                            'brand_name' => $orderGoods->brand_name,
                            'goods_sn' => $orderGoods->goods_sn,
                            'is_real' => $orderGoods->is_real,
                            'goods_attr' => $orderGoods->goods_attr,
                            'attr_id' => $orderGoods->goods_attr_id,
                            'return_number' => $orderGoods->o_goods_number,
                            'ru_id' => $orderGoods->ru_id,
                        ];
                        $rog = $this->orderReturnGoodsModel->addOrderReturnGoods($return_order_goods);
                    }

                    //退货商品凭证图片
                    for ($i = 0; $i < 5; $i++) {
                        if (!empty($data['file_' . $i])) {
                            if ($data['file_' . $i]->isValid()) {
                                $path = 'return_img';
                                $uri = FileHandle::upLoadImage($data['file_' . $i], $path);
                                $order_return_img = [
                                    'user_id' => $uid,
                                    'ret_id' => $rorder->ret_id,
                                    'img_file' => $uri,
                                    'add_time' => $time
                                ];
                                $this->orderReturnImagesModel->addImg($order_return_img);
                            }
                        }
                    }

                    $orderUpdate = ['order_stauts' => OS_RETURNED];
                    $this->orderInfoModel->setOrder(['order_id' => $data['order_id']], $orderUpdate);

                    if (!empty($rog) && !empty($rorder)) {
                        DB::commit();
                        return $rorder;
                    } else {
                        DB::rollBack();
                        return '申请失败';
                    }
                } else {
                    return '已出账单';
                }
            }
        }
    }
}