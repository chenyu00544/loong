<?php

namespace App\Repositories\Bonus;

use App\Contracts\Repositories\Bonus\BonusTypeRepositoryInterface;
use App\Models\BonusType;
use App\Models\SellerShopinfo;
use App\Models\TouchAdWxapp;
use App\Models\UserBonus;

class BonusTypeRepository implements BonusTypeRepositoryInterface
{

    /**
     * 取得红包信息
     * @param   int $bonus_id 红包id
     * @param   string $bonus_sn 红包序列号
     * @param   array   红包信息
     */
    public function bonusInfo($bonus_id, $bonus_sn = '')
    {
        return self::join('user_bonus', 'bonus_type.type_id', '=', 'user_bonus.bonus_type_id')
            ->where('bonus_id', $bonus_id)
            ->first();
    }

    /**
     * 获取使用红包id
     * @param   array   红包信息
     */
    public function getBonusId($ad_id = [], $nav_id = 0, $ad_type = 'home')
    {
        switch ($ad_type){
            case 'home':
                $where = [['ad_type', '9'], ['show_page_type', $ad_id], ['nav_id', $nav_id]];
                break;
            case 'goods':
                $where = [['ad_type', '9'], ['show_page_type', $ad_id], ['target_goods_id', $nav_id]];
                break;
            case 'check':
                $where = [['ad_type', '9'], ['show_page_type', $ad_id], ['nav_id', $nav_id]];
                break;
            default:
                break;
        }
        return TouchAdWxapp::where($where)
            ->first();
    }

    /**
     * 取得红包信息
     * @param   array   红包信息
     */
    public function getBonusCount($data = [])
    {
        //检查是否领取过
        $re = UserBonus::where('user_id', $data['userId'])
            ->where('bonus_type_id', $data['id'])
            ->first();

        if ($re) {
            return 0;
        } else {
            return UserBonus::join('bonus_type', 'user_bonus.bonus_type_id', '=', 'bonus_type.type_id')
                ->where('user_bonus.user_id', 0)
                ->where('bonus_type.send_end_date', '>', time())
                ->where('user_bonus.bonus_type_id', $data['id'])
                ->count();
        }
    }

    public function getBonusType($id)
    {
        return BonusType::where(['type_id' => $id])->first();
    }

    public function shopInfo($id)
    {
        return SellerShopinfo::select(['ru_id', 'shop_name', 'logo_thumb'])->where(['ru_id' => $id])->first();
    }

    public function checkBonus($data = [])
    {
        //检查是否领取过
        $re = UserBonus::where('user_id', $data['userId'])
            ->where('bonus_type_id', $data['id'])
            ->first();

        if ($re) {
            //领取过
            return UserBonus::where('user_bonus.user_id', $data['userId'])
                ->join('bonus_type', 'bonus_type_id', '=', 'bonus_type.type_id')
                ->where('bonus_type_id', $re->bonus_type_id)
                ->first();
        }else{
            return false;
        }
    }

    /**
     * 获取红包
     * @param   array   红包信息
     */
    public function receiveBonus($data = [])
    {
        //检查是否领取过
        $re = UserBonus::where('user_id', $data['userId'])
            ->where('bonus_type_id', $data['id'])
            ->first();

        if (!$re) {
            //没有领取过, 检查是否有剩余红包
            $res = UserBonus::where('user_bonus.user_id', 0)
                ->join('bonus_type', 'bonus_type_id', '=', 'bonus_type.type_id')
                ->where('bonus_type_id', $data['id'])
                ->first();
            if ($res) {
                $updata['bind_time'] = time();
                $updata['user_id'] = $data['userId'];
                $result = UserBonus::where('bonus_id', $res['bonus_id'])
                    ->where('bonus_type_id', $data['id'])
                    ->update($updata);
                if ($result) {
                    return $res;
                } else {
                    return false;
                }
            } else {
                //没有红包了
                return false;
            }

        } else {
            //领取过
            return UserBonus::where('user_bonus.user_id', $data['userId'])
                ->join('bonus_type', 'bonus_type_id', '=', 'bonus_type.type_id')
                ->where('bonus_type_id', $re->bonus_type_id)
                ->first();
        }
    }

}
