<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Wxapp;

use App\Contracts\UsersRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Helper\Wxapp;
use App\Helper\WXBizDataCrypt;
use App\Http\Models\Wxapp\CartModel;
use App\Http\Models\Wxapp\CommentModel;
use App\Http\Models\Wxapp\CouponsUserModel;
use App\Http\Models\Wxapp\OrderInfoModel;
use App\Http\Models\Wxapp\OrderReturnModel;
use App\Http\Models\Wxapp\UserAddressModel;
use App\Http\Models\Wxapp\UsersModel;
use App\Http\Models\Wxapp\UsersRealModel;
use App\Http\Models\Wxapp\WechatUserModel;

class UsersRepository implements UsersRepositoryInterface
{
    private $usersModel;
    private $userAddressModel;
    private $usersRealModel;
    private $orderInfoModel;
    private $orderReturnModel;
    private $cartModel;
    private $commentModel;
    private $wechatUserModel;
    private $couponsUserModel;

    public function __construct(
        UsersModel $usersModel,
        UserAddressModel $userAddressModel,
        UsersRealModel $usersRealModel,
        OrderInfoModel $orderInfoModel,
        OrderReturnModel $orderReturnModel,
        CartModel $cartModel,
        CommentModel $commentModel,
        WechatUserModel $wechatUserModel,
        CouponsUserModel $couponsUserModel
    )
    {
        $this->usersModel = $usersModel;
        $this->userAddressModel = $userAddressModel;
        $this->usersRealModel = $usersRealModel;
        $this->orderInfoModel = $orderInfoModel;
        $this->orderReturnModel = $orderReturnModel;
        $this->cartModel = $cartModel;
        $this->commentModel = $commentModel;
        $this->wechatUserModel = $wechatUserModel;
        $this->couponsUserModel = $couponsUserModel;
    }

    public function login($userInfo, $code, $type, $ip, $device_id = '')
    {
        $wx_config = RedisCache::get("wxapp_config");

        $config = [
            'appid' => $wx_config["wx_appid"],
            'secret' => $wx_config["wx_appsecret"],
        ];
        $wxapp = new Wxapp($config);

        // $token = $wxapp->getAccessToken();
        $response = $wxapp->getOauthOrization($code);
        $token = new WXBizDataCrypt($config['appid'], $response['session_key']);
        $errCode = $token->decryptData($userInfo['encryptedData'], $userInfo['iv'], $data);

//        if ($errCode == 0) {
//            print($data . "\n");
//        } else {
//            print($errCode . "\n");
//        }

        $data = get_object_vars(json_decode($data));

        $where['openid'] = $data['openId'];
        $re = $this->wechatUserModel->getWechat($where);
        if ($re) {
            $return["openid"] = $data['openId'];
            if ($re->ect_uid > 0) {
                $return["token"] = encrypt($re->ect_uid);
            } else {
                $return["token"] = 0;
            }
        }

        // 组合数据
        $args['unionid'] = empty($data['unionId']) ? "" : $data['unionId'];
        $args['openid'] = empty($data['openId']) ? "" : $data['openId'];
        $args['nickname'] = isset($userInfo['userInfo']['nickName']) ? $userInfo['userInfo']['nickName'] : '';
        $args['sex'] = isset($userInfo['userInfo']['gender']) ? $userInfo['userInfo']['gender'] : '';
        $args['province'] = isset($userInfo['userInfo']['province']) ? $userInfo['userInfo']['province'] : '';
        $args['city'] = isset($userInfo['userInfo']['city']) ? $userInfo['userInfo']['city'] : '';
        $args['country'] = isset($userInfo['userInfo']['country']) ? $userInfo['userInfo']['country'] : '';
        $args['headimgurl'] = isset($userInfo['userInfo']['avatarUrl']) ? $userInfo['userInfo']['avatarUrl'] : '';
        $args['parent_id'] = isset($userInfo['userInfo']['uid']) ? $userInfo['userInfo']['uid'] : 0;
        $args['drp_parent_id'] = isset($userInfo['userInfo']['uid']) ? $userInfo['userInfo']['uid'] : 0;

        $return["openid"] = $data['openId'];
        if ($re) {
            $this->wechatUserModel->setWechat($where, $args);
        } else {
            $this->wechatUserModel->addWechat($args);
            $return["token"] = 0;
        }

        return $return;
    }

    public function loginSilent($data)
    {
        $wx_config = RedisCache::get("wxapp_config");

        $config = [
            'appid' => $wx_config["wx_appid"],
            'secret' => $wx_config["wx_appsecret"],
        ];
        $wxapp = new Wxapp($config);

        // $token = $wxapp->getAccessToken();
        $response = $wxapp->getOauthOrization($data['code']);
        $where['openid'] = $response['openid'];
        $re = $this->wechatUserModel->getWechat($where);
        $return["openid"] = $response['openid'];
        if ($re) {
            if ($re->ect_uid > 0) {
                $return["token"] = encrypt($re->ect_uid);
            } else {
                $return["token"] = 0;
            }
            return $return;
        }

        $args['openid'] = $response['openid'];
        if ($re) {
            $this->wechatUserModel->setWechat($where, $args);
        } else {
            $this->wechatUserModel->addWechat($args);
            $return["token"] = 0;
        }
        return $return;
    }

    public function getUserInfo($uid)
    {
        $column = ['user_id', 'server_id', 'email', 'is_email', 'nick_name', 'sex', 'birthday', 'user_money', 'frozen_money', 'bonus_money', 'pay_points',
            'rank_points', 'address_id', 'user_rank', 'mobile_phone', 'is_phone', 'credit_line', 'logo', 'qq', 'union_id'];
        $user = $this->usersModel->getUser($uid, $column);
        $user->is_real = '0';
        $user->logo = FileHandle::getImgByOssUrl($user->logo);

        if (!empty($user->real)) {
            if ($user->real->review_status == 1) {
                $user->is_real = '1';
            }
            unset($user->real);
        }

        //待付款
        $order_orwhere = [];
        $order_where = [
            ['order_status', '<>', OS_CANCELED],
            ['order_status', '<>', OS_INVALID],
            ['order_status', '<>', OS_RETURNED],
            ['order_status', '<>', OS_ONLY_REFOUND],
            ['order_status', '<>', OS_RETURNED_PART],
            ['pay_status', '=', PS_UNPAYED],
            ['shipping_status', '=', SS_UNSHIPPED],
            ['user_id', '=', $uid],
        ];

        $order_unpayed_count = $this->orderInfoModel->countOrder($order_where, $order_orwhere);
        $user->order_unpayed_count = $order_unpayed_count;

        //待发货
        $order_where = [
            ['order_status', '=', OS_CONFIRMED],
            ['pay_status', '=', PS_PAYED],
            ['shipping_status', '=', SS_UNSHIPPED],
            ['user_id', '=', $uid],
        ];
        $order_unship_count = $this->orderInfoModel->countOrder($order_where);
        $user->order_unship_count = $order_unship_count;

        //待收货
        $order_where = [
            ['order_status', '=', OS_CONFIRMED],
            ['shipping_status', '=', SS_SHIPPED],
            ['user_id', '=', $uid],
        ];
        $order_shipped_count = $this->orderInfoModel->countOrder($order_where);
        $user->order_shipped_count = $order_shipped_count;

        //待评价
        $order_where = [
            ['order_status', '=', OS_CONFIRMED],
            ['shipping_status', '=', SS_RECEIVED],
            ['comment_status', '=', CS_UNCOMMENT],
            ['user_id', '=', $uid],
        ];
        $order_comment_count = $this->orderInfoModel->countOrder($order_where);
        $user->order_comment_count = $order_comment_count;

        //退换货
        $order_where = [
            ['return_status', '<>', RS_CHANGE_END],
            ['refound_status', '=', RS_NOREFOUND],
            ['user_id', '=', $uid],
        ];
        $order_return_count = $this->orderReturnModel->countOrderReturn($order_where);
        $user->order_return_count = $order_return_count;

        //优惠券
        $coupons_where = [
            'user_id'=> $uid,
            'is_use'=> 0,
            'order_id'=> 0,
        ];
        $user->coupons_count = $this->couponsUserModel->countCouponsUser($coupons_where);

        return $user;
    }

    public function setUserInfo($data, $uid)
    {
        $where['user_id'] = $uid;
        if (!empty($data['nickname'])) {
            $updata['nick_name'] = $data['nickname'];
            $this->commentModel->setComment($where, ['user_name' => $data['nickname']]);
        } elseif (!empty($data['sex'])) {
            $updata['sex'] = $data['sex'];
        } elseif (!empty($data['file_0'])) {
            $path = 'user_logo';
            if ($data['file_0']->isValid()) {
                $uri = FileHandle::upLoadImage($data['file_0'], $path);
                $updata['logo'] = $uri;
                if (!empty($data['logo']) && strpos($uri, 'http') === false) {
                    FileHandle::deleteFile($data['logo']);
                }
            }
        } elseif (!empty($data['phone']) && !empty($data['qrcode'])) {
            if ($data['qrcode'] == RedisCache::get('code_' . $data['phone'])) {
                $updata['mobile_phone'] = $data['phone'];
            } else {
                return '验证码错误';
            }
        }
        $re = $this->usersModel->setUsers($where, $updata);
        if ($re) {
            return 0;
        } else {
            return '设置失败';
        }
    }

    public function userAddresses($uid)
    {
        $where['user_id'] = $uid;
        $res = $this->userAddressModel->userAddresses($where);
        $user = $this->usersModel->getUser($uid);
        foreach ($res as $re) {
            $re->country_name = $re->mapcountry->region_name;
            $re->province_name = $re->mapprovince->region_name;
            $re->city_name = $re->mapcity->region_name;
            $re->district_name = $re->mapdistrict->region_name;
            if ($re->address_id == $user->address_id) {
                $re->def = 1;
            } else {
                $re->def = 0;
            }
        }
        return $res;
    }

    public function getAddress($data)
    {
        $where['address_id'] = $data['address_id'];
        $re = $this->userAddressModel->getAddress($where);
        $re->country_name = $re->mapcountry->region_name;
        $re->province_name = $re->mapprovince->region_name;
        $re->city_name = $re->mapcity->region_name;
        $re->district_name = $re->mapdistrict->region_name;
        return $re;
    }

    public function setDefaultAddress($data, $uid)
    {
        $where['user_id'] = $uid;
        $updata['address_id'] = $data['address_id'];
        return $this->usersModel->setUsers($where, $updata);
    }

    public function setAddress($data)
    {
        $where['address_id'] = $data['address_id'];
        $updata = [];
        return $this->userAddressModel->setAddress($where, $updata);
    }

    public function addAddress($data, $uid)
    {
        $count = $this->userAddressModel->countAddress(['user_id' => $uid]);
        if ($count >= 10) {
            return 40001;
        }
        $updata['user_id'] = $uid;
        $updata['consignee'] = $data['consignee'];
        $updata['mobile'] = $data['phone'];
        $updata['country'] = $data['country'];
        $updata['province'] = $data['province'];
        $updata['city'] = $data['city'];
        $updata['district'] = $data['district'];
        $updata['address'] = $data['address'];
        $updata['update_time'] = time();
        $re = $this->userAddressModel->addAddress($updata);
        if($count == 0){
            $this->usersModel->setUsers(['user_id' => $uid], ['address_id' => $re->address_id]);
        }
        if($re){
            return 10000;
        }else{
            return 10001;
        }
    }

    public function delAddress($data, $uid)
    {
        $where['user_id'] = $uid;
        $where['address_id'] = $data['address_id'];
        return $this->userAddressModel->delAddress($where);
    }

    public function getUsersReal($uid)
    {
        $where['user_id'] = $uid;
        $explain = '根据海关规定,任何出入关口的商品都必须实名登记并且缴纳关税,根据海关规定,根据海关规定,根据海关规定根据海关规定根据海关规定，根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定根据海关规定，根据海关规定';
        $re = $this->usersRealModel->getUsersReal($where);
        if ($re) {
            $re->explain = $explain;
        } else {
            $re['explain'] = $explain;
        }
        $re->front_of_id_card = FileHandle::getImgByOssUrl($re->front_of_id_card);
        $re->reverse_of_id_card = FileHandle::getImgByOssUrl($re->reverse_of_id_card);
        return $re;
    }

    public function setUsersReal($data, $uid)
    {
        $path = 'user_card';
        $where['user_id'] = $uid;
        $re = $this->usersRealModel->getUsersReal($where);
        $updata = [];
        foreach ($data as $key => $value) {
            if ($key == 'file_0') {
                if ($value->isValid()) {
                    $uri = FileHandle::upLoadImage($value, $path);
                    $updata['front_of_id_card'] = $uri;
                    if ($re) {
                        FileHandle::deleteFile($re->front_of_id_card);
                    }
                }
            } elseif ($key == 'file_1') {
                if ($value->isValid()) {
                    $uri = FileHandle::upLoadImage($value, $path);
                    $updata['reverse_of_id_card'] = $uri;
                    if ($re) {
                        FileHandle::deleteFile($re->reverse_of_id_card);
                    }
                }
            } elseif ($key == 'card_name') {
                $updata['real_name'] = $value;
            } elseif ($key == 'card_num') {
                $updata['self_num'] = $value;
            }
        }
        $updata['add_time'] = time();
        $updata['review_status'] = 3;
        if ($re) {
            $req = $this->usersRealModel->setUsersReal($where, $updata);
        } else {
            $updata['user_id'] = $uid;
            $req = $this->usersRealModel->addUsersReal($updata);
        }
        return ['review_status' => 3];
    }
}