<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\PayRepositoryInterface;
use App\Facades\RedisCache;
use App\Helper\WxPay;
use App\Http\Models\Wxapp\GoodsModel;
use App\Http\Models\Wxapp\OrderGoodsModel;
use App\Http\Models\Wxapp\OrderInfoModel;
use App\Http\Models\Wxapp\ProductsModel;

class PayRepository implements PayRepositoryInterface
{

    private $orderInfoModel;
    private $orderGoodsModel;
    private $goodsModel;
    private $productsModel;

    public function __construct(
        OrderInfoModel $orderInfoModel,
        OrderGoodsModel $orderGoodsModel,
        GoodsModel $goodsModel,
        ProductsModel $productsModel
    )
    {
        $this->orderInfoModel = $orderInfoModel;
        $this->orderGoodsModel = $orderGoodsModel;
        $this->goodsModel = $goodsModel;
        $this->productsModel = $productsModel;
    }

    public function weChatPay($data, $uid)
    {
        if (empty($data['order_id'])) {
            return 'no_order_id';
        }
        $where['order_id'] = $data['order_id'];

        $order = $this->orderInfoModel->getOrder($where);
        if ($order) {
            $wxpay = new WxPay();

            $code = 'wxpay';

            $wx_conf = RedisCache::get('wxpay_config');
            dd($wx_conf);

            $config = [
                'app_id' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appid'),
                'app_secret' => $this->WxappConfigRepository->getWxappConfigByCode('wx_appsecret'),
                'mch_key' => $this->WxappConfigRepository->getWxappConfigByCode('wx_mch_key'),
                'mch_id' => $this->WxappConfigRepository->getWxappConfigByCode('wx_mch_id'),
            ];
        }
    }

    public function weChatNotify($data)
    {

    }
}