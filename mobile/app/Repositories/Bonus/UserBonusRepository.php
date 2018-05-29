<?php

namespace App\Repositories\Bonus;

use App\Contracts\Repositories\Bonus\UserBonusRepositoryInterface;
use App\Models\BonusType;
use App\Models\UserBonus;

class UserBonusRepository implements UserBonusRepositoryInterface
{

    /**
     * 返回用户红包数量
     * @param $userId
     * @return mixed
     */
    public function getUserBonusCount($userId)
    {
        return UserBonus::where('user_id', $userId)
            ->count();
    }

    /**
     * 返回用户可用红包数组
     * @param string $user_id 用户ID;
     * @param bool|false $is_use 找出当前用户可以使用的
     * @param bool|false $total 订单总价
     * @param bool|false $cart_goods 商品信息
     * @param bool|false $user 用于区分是否会员中心里取数据(会员中心里的优惠券不能分组)
     * @return mixed 优惠券数组
     */
    public function UserBonus($user_id = '', $is_use = false, $total = '', $cart_goods = false, $user = true, $cart_ru_id = -1, $act_type = 'user')
    {
        if ($is_use && isset($total) && $cart_goods) {
            $res = [];
            foreach ($cart_goods['shop_list'] as $k => $v) {
                $res[$v['ru_id']]['order_total'] = $v['goods_price'] * $v['goods_number'];
                $res[$v['ru_id']]['seller_id'] = $v['ru_id'];
                $res[$v['ru_id']]['goods_id'] = $v['goods_id'];
                $res[$v['ru_id']]['cat_id'] = $v['cat_id'];
                $res[$v['ru_id']]['goods'][$v['goods_id']] = $v;
            }
            $res[0] = 0;
            $ruarr = array_keys($res);
            $result = UserBonus::join('bonus_type', 'user_bonus.bonus_type_id', '=', 'bonus_type.type_id')
                ->where('user_bonus.user_id','=',$user_id)
                ->where('bonus_type.use_end_date', '>', time())
                ->where('bonus_type.min_goods_amount', '<', $total)
                ->where('user_bonus.order_id', '=', '0')
                ->whereIn('bonus_type.user_id', $ruarr)
                ->get();
            $return = [];
            if ($result) {
                $result = $result->toArray();
                foreach ($result as $k => $val) {
                    if($val['user_id'] == '0' && $val['usebonus_type'] == '0'){
                        continue;
                    }
                    $result[$k]['send_start_date'] = date("Y-m-d", $val['send_start_date']);
                    $result[$k]['send_end_date'] = date("Y-m-d", $val['send_end_date']);
                    $result[$k]['use_start_date'] = date("Y-m-d", $val['use_start_date']);
                    $result[$k]['use_end_date'] = date("Y-m-d", $val['use_end_date']);
                    $return[] = $result[$k];
                }
            }

            return $return;
        }
    }

    public function getBonus($uid, $bid)
    {
        $result = BonusType::join('user_bonus', 'bonus_type.type_id', '=', 'user_bonus.bonus_type_id')
            ->where('user_bonus.user_id', '=', $uid)
            ->where('user_bonus.bonus_id', '=', $bid)
            ->where('user_bonus.order_id', '=', 0)
            ->first();
        if ($result) {
            $result = $result->toArray();
        }
        return $result;
    }

    public function setupBonus($uid, $bid, $oid = 0)
    {
        $upData = [
            'used_time' => time(),
            'order_id' => $oid,
        ];

        $result = UserBonus::where('bonus_id', $bid)
            ->where('user_id', $uid)
            ->update($upData);
        return $result;
    }
}
