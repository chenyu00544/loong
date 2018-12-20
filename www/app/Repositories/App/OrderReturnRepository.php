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

class OrderReturnRepository implements OrderRepositoryInterface
{

    private $orderInfoModel;
    private $orderReturnCauseModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderReturnCauseModel $orderReturnCauseModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderReturnCauseModel = $orderReturnCauseModel;
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
        }

        //fixme 退货原因
        $causes = $this->orderReturnCauseModel->getReturnCauses(['is_show' => 1]);
        $return['causes'] = $causes;
        $return['order'] = $res;
        return $return;
    }

    public function returnGoods($data, $uid)
    {
        $order = $this->orderInfoModel->getOrder(['order_id' => $data['order_id']]);
    }
}