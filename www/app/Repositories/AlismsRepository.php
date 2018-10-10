<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AlismsRepositoryInterface;
use App\Facades\RedisCache;
use App\Http\Models\Shop\AlismsConfigureModel;

class AlismsRepository implements AlismsRepositoryInterface
{

    private $alismsConfigureModel;

    public function __construct(
        AlismsConfigureModel $alismsConfigureModel
    )
    {
        $this->alismsConfigureModel = $alismsConfigureModel;
    }

    public function getAlismsByPage()
    {
        return $this->alismsConfigureModel->getAlismsByPage();
    }

    public function getAlisms($id)
    {
        $where['id'] = $id;
        return $this->alismsConfigureModel->getAlisms($where);
    }

    public function setAlisms($data, $id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $updata = $data;
        $re = $this->alismsConfigureModel->setAlisms($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
            $res = $this->alismsConfigureModel->getAlismses();
            if ($res->count() > 0) {
                $_conf = [];
                foreach ($res as $value) {
                    $_conf[$value->send_time] = $value->toArray();
                }
                RedisCache::set('sms_type', $_conf);
            }
        }
        return $req;
    }

    public function addAlisms($data)
    {
        $data['add_time'] = time();
        return $this->alismsConfigureModel->addAlisms($data);
    }

    public function delAlisms($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->alismsConfigureModel->delAlisms($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getSendTime()
    {
        $send_time = array(
            'sms_order_placed' => '客户下单时',
            'sms_order_payed' => '客户付款时',
            'sms_order_shipped' => '商家发货时',
            'store_order_code' => '门店提货码',
            'sms_signin' => '客户注册时',
            'sms_find_signin' => '客户密码找回时',
            'sms_code' => '验证码通知',
            'sms_price_notic' => '商品降价时',
            'sms_seller_signin' => '修改商家密码时',
            'user_account_code' => '会员充值/提现时'
        );
        return $send_time;
    }

    public function getSendTemplate($temp)
    {
        $template = array(
            'sms_order_placed' => '您的订单，收货人：${consignee}，联系方式：${ordermobile}，如有错误请及时联系${server_phone}.',
            'sms_order_payed' => '您的订单，收货人：${consignee}，联系方式：${ordermobile}，已付款成功.',
            'sms_order_shipped' => '尊敬的${username}用户，您的订单已发货，收货人${consignee}，请您及时查收.',
            'store_order_code' => '尊敬的${username}用户，您的提货码是：${code}，请不要把提货码泄露给其他人，如非本人操作，可不用理会.',
            'sms_signin' => '验证码${code}，您正在注册成为${app_name}用户，感谢您的支持！',
            'sms_find_signin' => '验证码${code}，用于密码找回，如非本人操作，请及时检查账户安全',
            'sms_code' => '您的验证码是：${code}，请不要把验证码泄露给其他人，如非本人操作，可不用理会',
            'sms_price_notic' => '尊敬的${username}用户，您关注的商品${product}，赶快下单吧！',
            'sms_seller_signin' => '亲爱的${username}，您的新账号：${loginname}，新密码 ：${password}，如非本人操作，请及时核对。',
            'user_account_code' => '尊敬的${username}，您于${old_time}发出的${fmtamount}元${processtype}申请于${now_time}为审核${examine}，账户余额为：${balance}，祝您购物愉快。',
        );
        return $template[$temp['temp']];
    }
}