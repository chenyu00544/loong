<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc: 首页设置功能
 */

namespace App\Http\Controllers\Shop\Admin;

use App\Facades\RedisCache;
use App\Http\Models\Shop\CommentModel;
use App\Http\Models\Shop\GoodsModel;
use App\Http\Models\Shop\OrderInfoModel;
use App\Http\Models\Shop\SellerShopInfoModel;
use App\Http\Models\Shop\UserAccountModel;
use App\Http\Models\Shop\UsersModel;

class IndexController extends CommonController
{
    private $infoModel;
    private $commentModel;
    private $sellerShopInfoModel;
    private $usersModel;
    private $goodsModel;
    private $userAccountModel;

    public function __construct(
        OrderInfoModel $infoModel,
        CommentModel $commentModel,
        SellerShopInfoModel $sellerShopInfoModel,
        UsersModel $usersModel,
        GoodsModel $goodsModel,
        UserAccountModel $userAccountModel
    )
    {
        parent::__construct();
        $this->setConfCache();
        $this->infoModel = $infoModel;
        $this->commentModel = $commentModel;
        $this->sellerShopInfoModel = $sellerShopInfoModel;
        $this->usersModel = $usersModel;
        $this->goodsModel = $goodsModel;
        $this->userAccountModel = $userAccountModel;
    }

    public function index()
    {
        $user = $this->user;
        $navs = $this->nav;
        return view('shop.admin.index', compact('user', 'navs'));
    }

    public function info()
    {

        $now_date = strtotime(date('Y-m-d', time()));
        $yester_data = strtotime(date('Y-m-d', time() - 86400));
        $now_month = strtotime(date('Y-m', time()));

        $conf['sms'] = RedisCache::get('sms_config');
        $conf['oss'] = RedisCache::get('oss_config');
        $conf['smtp'] = RedisCache::get('smtp_config');
        $conf['alipay'] = RedisCache::get('alipay_config');
        $conf['wxpay'] = RedisCache::get('wxpay_config');
        $sys_info = $this->systemInfo();

        $info['order_money_total'] = sprintf("%.2f", $this->infoModel->sumOrder([['pay_status', '=', PS_PAYED], ['pay_time', '>', $now_date]], [], 'all'));
        $info['order_sum'] = $this->infoModel->countOrder([['add_time', '>', $now_date]], [], 'all');
        $info['comment_sum'] = $this->commentModel->countComment([['add_time', '>', $now_date]]);
        $info['seller_sum'] = $this->sellerShopInfoModel->countSellerShopInfo([]);
        $info['user_now_sum'] = $this->usersModel->countUser([['reg_time', '>', $now_date]]);
        $info['user_yester_sum'] = $this->usersModel->countUser([['reg_time', '>', $yester_data], ['reg_time', '<', $now_date]]);
        $info['user_now_month_sum'] = $this->usersModel->countUser([['reg_time', '>', $now_month]]);
        $info['user_total_sum'] = $this->usersModel->countUser([]);

        $info['goods_entity_num'] = $this->goodsModel->countGoods([['is_real', '=', 1], ['user_id', '=', 0]]);
        $info['goods_fictitious_num'] = $this->goodsModel->countGoods([['is_real', '=', 0], ['user_id', '=', 0]]);
        $info['goods_seller_entity_num'] = $this->goodsModel->countGoods([['is_real', '=', 1], ['user_id', '<>', 0]]);
        $info['goods_seller_fictitious_num'] = $this->goodsModel->countGoods([['is_real', '=', 0], ['user_id', '<>', 0]]);

        $info['order_no_confirm'] = $this->infoModel->countOrder([['order_status', '=', OS_UNCONFIRMED], ['pay_status', '=', PS_UNPAYED], ['shipping_status', '=', SS_UNSHIPPED]]);
        $info['order_no_pay'] = $this->infoModel->countOrder([['order_status', '=', OS_UNCONFIRMED], ['pay_status', '=', PS_UNPAYED], ['shipping_status', '=', SS_UNSHIPPED]]);
        $info['order_no_shipping'] = $this->infoModel->countOrder([['order_status', '=', OS_CONFIRMED], ['pay_status', '=', PS_PAYED], ['shipping_status', '=', SS_UNSHIPPED]]);
        $info['order_over_transaction'] = $this->infoModel->countOrder([['order_status', '=', OS_CONFIRMED], ['pay_status', '=', PS_PAYED], ['shipping_status', '=', SS_RECEIVED]]);
        $info['shortage_registration'] = 0;
        $info['order_part_shipping'] = 0;
        $info['user_cash_withdrawal'] = $this->userAccountModel->countUserAccount(['is_paid' => 0]);

//        dd($user_now_sum);
        return view('shop.admin.info', compact('conf', 'sys_info', 'info'));
    }
}
