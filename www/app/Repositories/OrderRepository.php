<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\OrderRepositoryInterface;
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\OrderGoodsModel;
use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\TradeSnapshotModel;
use App\Http\Models\Shop\UsersModel;
use Illuminate\Support\Facades\Config;

class OrderRepository implements OrderRepositoryInterface
{
    private $orderInfoModel;
    private $orderGoodsModel;
    private $usersModel;
    private $regionsModel;
    private $merchantsShopInformationModel;
    private $tradeSnapshotModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        UsersModel $usersModel,
        RegionsModel $regionsModel,
        MerchantsShopInformationModel $merchantsShopInformationModel,
        TradeSnapshotModel $tradeSnapshotModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->usersModel = $usersModel;
        $this->regionsModel = $regionsModel;
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->tradeSnapshotModel = $tradeSnapshotModel;
    }

    public function getOrdersByPage($search, $parame = [])
    {
        $where = [];
        $orWhere = [];
        foreach ($parame as $val) {
            switch ($val) {
                case 'selfsale':
//                    $where[] = ['order_info.user_id', '=', '0'];
                    $where[] = ['user_id', '=', '0'];
                    break;
                case 'seller':
//                    $where[] = ['order_info.user_id', '<>', '0'];
                    $where[] = ['user_id', '<>', '0'];
                    break;
                case '0':
                    break;
                case '1':
                    $where[] = ['order_status', '=', Config::get('define.OS_UNCONFIRMED')];
                    break;
                case '2':
                    $where[] = ['pay_status', '=', Config::get('define.PS_UNPAYED')];
                    $where[] = ['order_status', '=', Config::get('define.OS_CONFIRMED')];
                    break;
                case '3':
                    $where[] = ['pay_status', '=', Config::get('define.PS_PAYED')];
                    $where[] = ['order_status', '=', Config::get('define.OS_CONFIRMED')];
                    $orWhere = [['shipping_status' => Config::get('define.SS_UNSHIPPED')], ['shipping_status' => Config::get('define.SS_PREPARING')]];
                    break;
                case '4':
                    $where[] = ['shipping_status', '=', Config::get('define.SS_SHIPPED')];
                    break;
                case '5':
                    $where[] = ['shipping_status', '=', Config::get('define.SS_RECEIVED')];
                    break;
                case '6':
                    $where[] = ['pay_status', '=', Config::get('define.PS_PAYING')];
                    break;
                case '7':
                    $where[] = ['order_status', '=', Config::get('define.OS_CANCELED')];
                    break;
                case '8':
                    $where[] = ['order_status', '=', Config::get('define.OS_INVALID')];
                    break;
                case '9':
                    $where[] = ['order_status', '=', Config::get('define.OS_RETURNED')];
                    $where[] = ['pay_status', '=', Config::get('define.PS_PAYED')];
                    $orWhere = [['shipping_status' => Config::get('define.SS_UNSHIPPED')], ['shipping_status' => Config::get('define.SS_PREPARING')]];
                    break;
                default:
                    break;
            }
        }
//        $column = ['order_info.order_id', 'order_info.order_sn', 'order_info.order_status', 'order_info.pay_status', 'order_info.shipping_status', 'order_info.consignee', 'order_info.country', 'order_info.province', 'order_info.city', 'order_info.district', 'order_info.street', 'order_info.address', 'order_info.tel', 'order_info.mobile', 'order_info.email', 'order_info.shipping_id', 'order_info.shipping_name', 'order_info.shipping_fee', 'order_info.pay_id', 'order_info.pay_name', 'order_info.how_oos', 'order_info.goods_amount', 'order_info.cost_amount', 'order_info.order_amount', 'order_info.froms', 'order_info.coupons', 'order_info.add_time', 'order_info.extension_code', 'order_info.extension_id', 'order_info.invoice_no', 'order_goods.goods_id', 'order_goods.goods_name', 'order_goods.goods_sn', 'order_goods.goods_number', 'order_goods.market_price', 'order_goods.goods_price', 'order_goods.goods_attr', 'order_goods.goods_attr_id', 'order_goods.ru_id', 'order_goods.tid', 'order_goods.stages_qishu', 'order_goods.freight', 'order_goods.warehouse_id', 'order_goods.area_id', 'order_goods.freight', 'order_goods.is_reality', 'order_goods.is_return', 'order_goods.is_fast', 'order_goods.brand_name'];
        $column = ['order_id', 'order_sn', 'user_id', 'order_status', 'pay_status', 'shipping_status', 'consignee', 'country', 'province', 'city', 'district', 'street', 'address', 'tel', 'mobile', 'email', 'shipping_id', 'shipping_name', 'shipping_fee', 'pay_id', 'pay_name', 'how_oos', 'goods_amount', 'cost_amount', 'order_amount', 'froms', 'coupons', 'add_time', 'extension_code', 'extension_id', 'invoice_no', 'tax', 'insure_fee', 'pay_fee', 'pack_fee', 'card_fee', 'discount'];

        $req = $this->orderInfoModel->getOrderInfoByPage($where, $orWhere, $search, $column);
        foreach ($req as $key => $value) {
            //获取订单商品
            $orderGoodses = $this->orderGoodsModel->getOrderGoodses(['order_id' => $value->order_id], ['order_goods.*', 'goods.goods_thumb']);
            foreach ($orderGoodses as $k => $orderGoods) {
                if ($k == 0) {
                    //获取商品所属店铺名称
                    $merchantsShopInfo = $this->merchantsShopInformationModel->getMerchantsShopInfo(['user_id' => $orderGoodses[0]->ru_id]);
                    $req[$key]->merchants_shop_info = $merchantsShopInfo;
                }
                //交易快照id
                $trade = $this->tradeSnapshotModel->getTradeSnapshot(['goods_id' => $orderGoods->goods_id]);
                if (!empty($trade)) {
                    $orderGoodses[$k]->trade_id = $trade->trade_id;
                }
            }
            $req[$key]->order_goodses = $orderGoodses;
            $user = $this->usersModel->getUser(['user_id' => $value->user_id]);
            $req[$key]->user = $user;

            $req[$key]->province = $this->regionsModel->getRegion($value->province)->region_name;
            $req[$key]->city = $this->regionsModel->getRegion($value->city)->region_name;
            $req[$key]->district = $this->regionsModel->getRegion($value->district)->region_name;
        }

        return $req;
    }

    public function getSearchNav($seller)
    {
        $allOrder = $this->orderInfoModel->countOrder([], [], $seller);
        $statusOrder = $this->orderInfoModel->countOrder(['order_status' => Config::get('define.OS_UNCONFIRMED')], [], $seller);
        $statusOrder_2 = $this->orderInfoModel->countOrder(['order_status' => Config::get('define.OS_CANCELED')], [], $seller);
        $statusOrder_3 = $this->orderInfoModel->countOrder(['order_status' => Config::get('define.OS_INVALID')], [], $seller);
        $payOrder = $this->orderInfoModel->countOrder(['pay_status' => Config::get('define.PS_UNPAYED'), 'order_status' => Config::get('define.OS_CONFIRMED')], [], $seller);
        $payOrder_1 = $this->orderInfoModel->countOrder(['pay_status' => Config::get('define.PS_PAYING')], [], $seller);
//        $payOrder_2 = $this->orderInfoModel->countOrder(['pay_status' => Config::get('define.PS_PAYED')],[],$seller);
        $shippingOrder = $this->orderInfoModel->countOrder(['pay_status' => Config::get('define.PS_PAYED'), 'order_status' => Config::get('define.OS_CONFIRMED')], [['shipping_status' => Config::get('define.SS_UNSHIPPED')], ['shipping_status' => Config::get('define.SS_PREPARING')]], $seller);
        $shippingOrder_1 = $this->orderInfoModel->countOrder(['shipping_status' => Config::get('define.SS_SHIPPED')], [], $seller);
        $shippingOrder_2 = $this->orderInfoModel->countOrder(['shipping_status' => Config::get('define.SS_RECEIVED')], [], $seller);
        $returnOrder = $this->orderInfoModel->countOrder(['order_status' => Config::get('define.OS_RETURNED'), 'pay_status' => Config::get('define.PS_PAYED')], [['shipping_status' => Config::get('define.SS_UNSHIPPED')], ['shipping_status' => Config::get('define.SS_PREPARING')]], $seller);

        $req = [
            ['navType' => '0', 'title' => '全部订单', 'count' => $allOrder],
            ['navType' => '1', 'title' => '待确认', 'count' => $statusOrder],
            ['navType' => '2', 'title' => '待付款', 'count' => $payOrder],
            ['navType' => '3', 'title' => '待发货', 'count' => $shippingOrder],
            ['navType' => '4', 'title' => '待收货', 'count' => $shippingOrder_1],
            ['navType' => '5', 'title' => '已完成', 'count' => $shippingOrder_2],
            ['navType' => '6', 'title' => '付款中', 'count' => $payOrder_1],
            ['navType' => '7', 'title' => '取消', 'count' => $statusOrder_2],
            ['navType' => '8', 'title' => '无效', 'count' => $statusOrder_3],
            ['navType' => '9', 'title' => '退货', 'count' => $returnOrder],
        ];

        return $req;
    }

    public function changes($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $updata = [];
        $whereIn = [];
        $where = [];
        $whereOr = [];
        if (!empty($data['type'])) {
            switch ($data['type']) {
                case 'confirm':
                    $updata['order_status'] = Config::get('define.OS_CONFIRMED');
                    $whereIn = $data['ids'];
                    break;
                case 'invalid':
                    $updata['order_status'] = Config::get('define.OS_INVALID');
                    $where['pay_status'] = 0;
                    $whereIn = $data['ids'];
                    break;
                case 'cancel':
                    $updata['order_status'] = Config::get('define.OS_CANCELED');
                    $where['pay_status'] = 0;
                    $whereIn = $data['ids'];
                    break;
                case 'remove':
                    $updata['order_status'] = Config::get('define.OS_CANCELED');
                    $where['pay_status'] = 0;
                    $whereOr = ['order_status' => 0, 'order_status' => 3, 'order_status' => 2];
                    $whereIn = $data['ids'];
                    break;
                default:
                    break;
            }
        }

        $re = $this->orderInfoModel->setOrderInfo($where, $updata, $whereIn);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}