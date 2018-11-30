<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\OrderRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\DeliveryGoodsModel;
use App\Http\Models\Shop\DeliveryOrderModel;
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\OrderGoodsModel;
use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Shop\OrderReturnGoodsModel;
use App\Http\Models\Shop\OrderReturnModel;
use App\Http\Models\Shop\PaymentModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\TradeSnapshotModel;
use App\Http\Models\Shop\UserAccountModel;
use App\Http\Models\Shop\UsersModel;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class OrderRepository implements OrderRepositoryInterface
{
    private $orderInfoModel;
    private $orderGoodsModel;
    private $deliveryOrderModel;
    private $deliveryGoodsModel;
    private $orderReturnModel;
    private $orderReturnGoodsModel;
    private $usersModel;
    private $userAccountModel;
    private $regionsModel;
    private $merchantsShopInformationModel;
    private $tradeSnapshotModel;
    private $paymentModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        DeliveryOrderModel $deliveryOrderModel,
        DeliveryGoodsModel $deliveryGoodsModel,
        OrderReturnModel $orderReturnModel,
        OrderReturnGoodsModel $orderReturnGoodsModel,
        UsersModel $usersModel,
        UserAccountModel $userAccountModel,
        RegionsModel $regionsModel,
        MerchantsShopInformationModel $merchantsShopInformationModel,
        TradeSnapshotModel $tradeSnapshotModel,
        PaymentModel $paymentModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->deliveryOrderModel = $deliveryOrderModel;
        $this->deliveryGoodsModel = $deliveryGoodsModel;
        $this->orderReturnModel = $orderReturnModel;
        $this->orderReturnGoodsModel = $orderReturnGoodsModel;
        $this->usersModel = $usersModel;
        $this->userAccountModel = $userAccountModel;
        $this->regionsModel = $regionsModel;
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->tradeSnapshotModel = $tradeSnapshotModel;
        $this->paymentModel = $paymentModel;
    }

    public function getOrdersByPage($search, $parame = [], $user_id = 0)
    {
        $where = [];
        $orWhere = [];
        foreach ($parame as $val) {
            switch ($val) {
                case 'selfsale':
                    $where[] = ['ru_id', '=', '0'];
                    break;
                case 'seller':
                    $where[] = ['ru_id', '<>', '0'];
                    break;
                case 'byUser':
                    $where[] = ['user_id', '=', $user_id];
                    break;
                case '0':
                    break;
                case '1':
                    $where[] = ['order_status', '=', OS_UNCONFIRMED];
                    break;
                case '2':
                    $where[] = ['pay_status', '=', PS_UNPAYED];
                    $where[] = ['order_status', '=', OS_CONFIRMED];
                    break;
                case '3':
                    $where[] = ['pay_status', '=', PS_PAYED];
                    $where[] = ['order_status', '=', OS_CONFIRMED];
                    $orWhere = [['shipping_status' => SS_UNSHIPPED], ['shipping_status' => SS_PREPARING]];
                    break;
                case '4':
                    $where[] = ['shipping_status', '=', SS_SHIPPED];
                    break;
                case '5':
                    $where[] = ['shipping_status', '=', SS_RECEIVED];
                    break;
                case '6':
                    $where[] = ['pay_status', '=', PS_PAYING];
                    break;
                case '7':
                    $where[] = ['order_status', '=', OS_CANCELED];
                    break;
                case '8':
                    $where[] = ['order_status', '=', OS_INVALID];
                    break;
                case '9':
                    $where[] = ['order_status', '=', OS_RETURNED];
                    $where[] = ['pay_status', '=', PS_PAYED];
                    $orWhere = [['shipping_status' => SS_UNSHIPPED], ['shipping_status' => SS_PREPARING]];
                    break;
                default:
                    break;
            }
        }
        $column = ['order_id', 'order_sn', 'user_id', 'order_status', 'pay_status', 'shipping_status', 'consignee', 'country', 'province', 'city', 'district', 'street', 'address', 'tel', 'mobile', 'email', 'shipping_id', 'shipping_name', 'shipping_fee', 'pay_id', 'pay_name', 'how_oos', 'goods_amount', 'cost_amount', 'order_amount', 'froms', 'coupons', 'add_time', 'extension_code', 'extension_id', 'invoice_no', 'tax', 'insure_fee', 'pay_fee', 'pack_fee', 'card_fee', 'discount'];

        $req = $this->orderInfoModel->getOrderInfoByPage($where, $orWhere, $search, $column);
        foreach ($req as $key => $value) {
            $req[$key]->province = $this->regionsModel->getRegion($value->province)->region_name;
            $req[$key]->city = $this->regionsModel->getRegion($value->city)->region_name;
            $req[$key]->district = $this->regionsModel->getRegion($value->district)->region_name;
            foreach ($value->Goods as $goods) {
                $goods->original_img = FileHandle::getImgByOssUrl($goods->original_img);
            }
        }

        return $req;
    }

    public function getDeliveryOrdersByPage($search, $parame = [])
    {
        $where = [];
        foreach ($parame as $val) {
            switch ($val) {
                case 'selfsale':
                    $where[] = ['ru_id', '=', '0'];
                    break;
                case 'seller':
                    $where[] = ['ru_id', '<>', '0'];
                    break;
                default:
                    break;
            }
        }
        $req = $this->deliveryOrderModel->getDeliveryOrderByPage($where, $search);
        foreach ($req as $key => $value) {
            $user = $this->usersModel->getUser(['user_id' => $value->user_id]);
            $req[$key]->user = $user;
            //获取商品所属店铺名称
            $merchantsShopInfo = $this->merchantsShopInformationModel->getMerchantsShopInfo(['user_id' => $value->ru_id]);
            $req[$key]->shop_info = $merchantsShopInfo;

            $order = $this->orderInfoModel->getOrderInfo(['order_id' => $value->order_id]);
            $req[$key]->order = $order;
        }
        return $req;
    }

    public function getReturnOrdersByPage($search, $parame = [])
    {
        $where = [];
        foreach ($parame as $val) {
            switch ($val) {
                case 'selfsale':
                    $where[] = ['ru_id', '=', '0'];
                    break;
                case 'seller':
                    $where[] = ['ru_id', '<>', '0'];
                    break;
                default:
                    break;
            }
        }
        $req = $this->orderReturnModel->getOrderReturnByPage($where, $search);
        foreach ($req as $key => $value) {

            $returnGoodses = $this->orderReturnGoodsModel->getOrderReturnGoodses(['ret_id' => $value->ret_id]);
            $returnNum = 0;
            foreach ($returnGoodses as $returnGoods) {
                $returnNum += $returnGoods->return_number;
            }
            $req[$key]->return_number = $returnNum;
            $user = $this->usersModel->getUser(['user_id' => $value->user_id]);
            $req[$key]->user = $user;

            $req[$key]->province = $this->regionsModel->getRegion($value->province)->region_name;
            $req[$key]->city = $this->regionsModel->getRegion($value->city)->region_name;
            $req[$key]->district = $this->regionsModel->getRegion($value->district)->region_name;
        }
        return $req;
    }

    public function getOrder($id)
    {
        $where['order_id'] = $id;
        return $this->orderInfoModel->getOrderInfo($where);
    }

    public function getDeliveryOrder($id)
    {
        $where['delivery_id'] = $id;
        return $this->deliveryOrderModel->getDeliveryOrder($where);
    }

    public function getReturnOrder($id)
    {
        $where['ret_id'] = $id;
        return $this->orderReturnModel->getReturnOrder($where);
    }

    public function getOrderGoodses($id)
    {
        $where['order_id'] = $id;
        $column = ['*'];
        $res = $this->orderGoodsModel->getOrderGoodses($where, $column);
        foreach ($res as $re) {
            $re->original_img = FileHandle::getImgByOssUrl($re->original_img);
        }
        return $res;
    }

    public function getOrderReturnGoodses($id)
    {
        $where['ret_id'] = $id;
        $column = ['*'];
        return $this->orderReturnGoodsModel->getOrderReturnGoodses($where, $column);
    }

    public function getDeliveryGoodses($id)
    {
        $where['delivery_id'] = $id;
        $column = ['*'];
        return $this->deliveryGoodsModel->getDeliveryGoodses($where, $column);
    }

    public function getSearchNav($seller)
    {
        $allOrder = $this->orderInfoModel->countOrder([], [], $seller);
        $statusOrder = $this->orderInfoModel->countOrder(['order_status' => OS_UNCONFIRMED], [], $seller);
        $statusOrder_2 = $this->orderInfoModel->countOrder(['order_status' => OS_CANCELED], [], $seller);
        $statusOrder_3 = $this->orderInfoModel->countOrder(['order_status' => OS_INVALID], [], $seller);
        $payOrder = $this->orderInfoModel->countOrder(['pay_status' => PS_UNPAYED, 'order_status' => OS_CONFIRMED], [], $seller);
        $payOrder_1 = $this->orderInfoModel->countOrder(['pay_status' => PS_PAYING], [], $seller);
//        $payOrder_2 = $this->orderInfoModel->countOrder(['pay_status' => Config::get('define.PS_PAYED')],[],$seller);
        $shippingOrder = $this->orderInfoModel->countOrder(['pay_status' => PS_PAYED, 'order_status' => OS_CONFIRMED], [['shipping_status' => SS_UNSHIPPED], ['shipping_status' => SS_PREPARING]], $seller);
        $shippingOrder_1 = $this->orderInfoModel->countOrder(['shipping_status' => SS_SHIPPED], [], $seller);
        $shippingOrder_2 = $this->orderInfoModel->countOrder(['shipping_status' => SS_RECEIVED], [], $seller);
        $returnOrder = $this->orderInfoModel->countOrder(['order_status' => OS_RETURNED, 'pay_status' => PS_PAYED], [['shipping_status' => SS_UNSHIPPED], ['shipping_status' => SS_PREPARING]], $seller);

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
                    $updata['order_status'] = OS_CONFIRMED;
                    $whereIn = $data['ids'];
                    break;
                case 'invalid':
                    $updata['order_status'] = OS_INVALID;
                    $where['pay_status'] = 0;
                    $whereIn = $data['ids'];
                    break;
                case 'cancel':
                    $updata['order_status'] = OS_CANCELED;
                    $where['pay_status'] = 0;
                    $whereIn = $data['ids'];
                    break;
                case 'remove':
                    $updata['order_status'] = OS_CANCELED;
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

    public function change($data, $admin)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $updata = [];
        $where['order_id'] = $data['id'];
        if (!empty($data['type'])) {
            switch ($data['type']) {
                case 'payment':
                    $updata['pay_id'] = $data['pay_id'];
                    $updata['pay_name'] = $data['pay_name'];
                    break;
                case 'shipping':
                    $updata['shipping_id'] = $data['shipping_id'];
                    $updata['shipping_name'] = $data['shipping_name'];
                    break;
                case 'invoice_no':
                    $updata['invoice_no'] = $data['invoice_no'];
                    $updata['shipping_status'] = 1;
                    $updata['shipping_time'] = time();
                    break;
                case 'auto_delivery_time':
                    $updata['auto_delivery_time'] = $data['auto_delivery_time'];
                    break;
                case 'consignee':
                    foreach ($data['address'] as $key => $value) {
                        if ($value['name'] != 'addresslist' && $value['name'] != 'order_id') {
                            $updata[$value['name']] = $value['value'];
                        }
                    }
                    break;
                case 'other':
                    foreach ($data['other'] as $key => $value) {
                        $updata[$value['name']] = $value['value'];
                    }
                    break;
                case 'fee':
                    foreach ($data['fee'] as $key => $value) {
                        $updata[$value['name']] = $value['value'];
                    }
                    $updata['order_amount'] = $updata['goods_amount'] + $updata['tax'] + $updata['shipping_fee'] + $updata['insure_fee'] + $updata['pay_fee'] - $updata['discount'] - $updata['surplus'] - $updata['integral_money'] - $updata['bonus'] - $updata['coupons'] - $updata['integral_money'] - $updata['money_paid'];
                    break;
                case 'operation':
                    switch ($data['value']) {
                        case 'sure':
                            $updata['order_status'] = OS_CONFIRMED;
                            break;
                        case 'pay':
                            $order = $this->orderInfoModel->getOrderInfo($where);
                            $updata['pay_status'] = PS_PAYED;
                            $updata['order_status'] = OS_CONFIRMED;
                            $updata['money_paid'] = $this->orderAmount($order);
                            $updata['order_amount'] = 0;
                            $updata['pay_time'] = time();
                            break;
                        case 'no_pay':
                            $order = $this->orderInfoModel->getOrderInfo($where);
                            $updata['pay_status'] = PS_UNPAYED;
                            $updata['money_paid'] = 0;
                            $updata['order_amount'] = $this->orderAmount($order);
                            $return_money = $order->money_paid - $order->tax;
                            $ope_type = [];
                            foreach ($data['nopay'] as $key => $value) {
                                $ope_type[$value['name']] = $value['value'];
                            }
                            //不退运费
                            if ($ope_type['is_shipping'] == 0) {
                                $return_money = $return_money - $order->shipping_fee;
                            }
                            if ($ope_type['refund'] == 1) {//退回用户余额
                                DB::beginTransaction();
                                $res = $this->usersModel->setUserMoney(['user_id' => $order->user_id], $return_money);
                                $re = $this->orderInfoModel->setOrderInfo($where, $updata);
                                if ($re && $res) {
                                    DB::commit();
                                    $req = ['code' => 1, 'msg' => '操作成功'];
                                } else {
                                    DB::rollBack();
                                }
                                return $req;
                            } elseif ($ope_type['refund'] == 2) {//生成退款申请
                                $account['user_id'] = $order->user_id;
                                $account['admin_user'] = $admin->user_name;
                                $account['amount'] = -$return_money;
                                $account['add_time'] = time();
                                $account['process_type'] = 1;
                                $payment = $this->paymentModel->getPayment([]);
                                $account['payment'] = $payment->pay_name;
                                $account['pay_id'] = $payment->pay_id;
                                DB::beginTransaction();
                                $are = $this->userAccountModel->addAccount($account);
                                $ure = $this->usersModel->setUser(['user_id' => $order->user_id], ['frozen_money' => 'frozen_money' + $return_money]);
                                $re = $this->orderInfoModel->setOrderInfo($where, $updata);
                                if ($re && $ure && $are) {
                                    DB::commit();
                                    $req = ['code' => 1, 'msg' => '操作成功'];
                                } else {
                                    DB::rollBack();
                                }
                                return $req;
                            }
                            break;
                        case 'prepare':
                            $updata['shipping_status'] = SS_PREPARING;
                            break;
                        case 'ship':
                            $updata['shipping_status'] = SS_SHIPPED_ING;
                            break;
                        case 'to_delivery':
                            $updata['shipping_status'] = SS_SHIPPED;
                            break;
                        case 'unship':
                            $updata['shipping_status'] = SS_UNSHIPPED;
                            break;
                        case 'receive':
                            $updata['shipping_status'] = SS_RECEIVED;
                            break;
                        case 'return':
                            $updata['order_status'] = OS_RETURNED;
                            break;
                        case 'cancel':
                            $updata['order_status'] = OS_CANCELED;
                            break;
                        case 'invalid':
                            $updata['order_status'] = OS_INVALID;
                            break;
                        case 'delete':
                            $order = $this->orderInfoModel->getOrderInfo($where);
                            if ($order->order_status == 2 || $order->order_status == 3) {
                                $res = $this->orderGoodsModel->delOrderGoods($where);
                                $re = $this->orderInfoModel->delOrderInfo($where);
                                if ($re) {
                                    $req = ['code' => 1, 'msg' => '操作成功'];
                                }
                            }
                            return $req;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        $re = $this->orderInfoModel->setOrderInfo($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function deliveryChange($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $updata = [];
        $where['delivery_id'] = $data['id'];
        switch ($data['type']) {
            case 'ship':
                if ($data['value'] == 'ship') {
                    $updata['status'] = 0;
                } else {
                    $updata['status'] = 2;
                }
                $updata['invoice_no'] = $data['invoice_no'];
                $updata['update_time'] = time();
                break;
            default:
                break;
        }

        $re = $this->deliveryOrderModel->setDeliveryOrder($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function returnChange($data, $admin)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $updata = [];
        $where['ret_id'] = $data['id'];
        $rorder = $this->orderReturnModel->getReturnOrder($where);
        switch ($data['type']) {
            case 'operation':
                if ($data['value'] == 'agree_apply') {//同意申请
                    $updata['agree_apply'] = 1;
                    $this->orderInfoModel->setOrderInfo(['order_id' => $rorder->order_id], ['order_status' => OS_RETURNED]);
                } elseif ($data['value'] == 'receive_goods') {//收到退换货商品
                    $updata['return_status'] = 1;
                } elseif ($data['value'] == 'refuse_apply') {//拒绝申请
                    $updata['return_status'] = 6;
                } elseif ($data['value'] == 'complete') {//完成退换货
                    $updata['return_status'] = 4;
                }
                break;
            case 'refound':
                $updata_t = [];
                $updata['actual_return'] = 0;
                foreach ($data['value'] as $value) {
                    $updata_t[$value['name']] = $value['value'];
                }
                if ($updata_t['refound_amount'] > $rorder->should_return) {
                    return $req = ['code' => 5, 'msg' => '金额大于订单金额'];
                }
                $updata['actual_return'] = $updata_t['refound_amount'];
                if ($updata_t['is_shipping'] == 1) {
                    $updata['actual_return'] += $updata_t['shipping_fee'];
                }
                $updata['refund_type'] = $updata_t['refund'];
                $updata['agree_apply'] = 1;
                $updata['refound_status'] = 1;
                $updata['return_shipping_fee'] = $updata_t['shipping_fee'];
                $updata['return_time'] = time();
                if ($updata_t['refund'] == 1) {//退回用户余额
                    DB::beginTransaction();
                    $res = $this->usersModel->setUserMoney(['user_id' => $rorder->user_id], $updata['actual_return']);
                    $re = $this->orderReturnModel->setOrderReturn($where, $updata);
                    if ($re && $res) {
                        DB::commit();
                        $this->orderInfoModel->setOrderInfo(['order_id' => $rorder->order_id], ['pay_status' => PS_REFOUND]);
                        $req = ['code' => 1, 'msg' => '操作成功'];
                    } else {
                        DB::rollBack();
                    }
                    return $req;
                } else {
                    $this->orderInfoModel->setOrderInfo(['order_id' => $rorder->order_id], ['pay_status' => PS_REFOUND]);
                }
                break;
            default:
                break;
        }
        $re = $this->orderReturnModel->setOrderReturn($where, $updata);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delOrder($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['order_id'] = $id;
        $this->orderGoodsModel->delOrderGoods($where);
        $re = $this->orderInfoModel->delOrderInfo($where);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delDelivery($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['order_id'] = $id;
        $this->deliveryGoodsModel->delDeliveryGoods($where);
        $re = $this->deliveryOrderModel->delDelivery($where);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    private function orderAmount($order)
    {
        return $order->goods_amount + $order->tax + $order->shipping_fee + $order->insure_fee + $order->pay_fee - $order->discount - $order->integral_money - $order->bonus - $order->coupons - $order->integral_money;
    }
}