<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\PayRepositoryInterface;
use App\Helper\WxPay;
use App\Http\Models\App\GoodsModel;
use App\Http\Models\App\OrderGoodsModel;
use App\Http\Models\App\OrderInfoModel;
use App\Http\Models\App\PaymentModel;
use App\Http\Models\App\ProductsModel;

class PayRepository implements PayRepositoryInterface
{

    private $orderInfoModel;
    private $orderGoodsModel;
    private $goodsModel;
    private $paymentModel;
    private $productsModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        PaymentModel $paymentModel,
        GoodsModel $goodsModel,
        ProductsModel $productsModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->goodsModel = $goodsModel;
        $this->paymentModel = $paymentModel;
        $this->productsModel = $productsModel;
    }

    public function aliPay($data, $uid)
    {
        if(empty($data['order_id'])){
            return 'no_order_id';
        }
        $where['order_id'] = $data['order_id'];

        $payMode = $data['pay_mode'];

        $order = $this->orderInfoModel->getOrder($where);
        if($order){

        }
    }

    public function weChatPay($data, $uid)
    {
        if(empty($data['order_id'])){
            return 'no_order_id';
        }
        $where['order_id'] = $data['order_id'];

        $payMode = $data['pay_mode'];

        $order = $this->orderInfoModel->getOrder($where);
        if($order){
            $wxpay = new WxPay();

            $code = 'wxpay';

            $config = [
                'app_id' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appid'),
                'app_secret' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appsecret'),
                'mch_key' => $this->WxappConfigRepository->getWxappConfigByCode('wx_mch_key'),
                'mch_id' => $this->WxappConfigRepository->getWxappConfigByCode('wx_mch_id'),
            ];
        }
    }

    public function unionPay($data, $uid)
    {
        if(empty($data['order_id'])){
            return 'no_order_id';
        }
        $where['order_id'] = $data['order_id'];

        $payMode = $data['pay_mode'];

        $order = $this->orderInfoModel->getOrder($where);
        if($order){

        }
    }

    public function aliNotify($data){

    }

    public function weChatNotify($data){

    }
}