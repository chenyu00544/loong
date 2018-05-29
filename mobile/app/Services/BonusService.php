<?php

namespace App\Services;

use App\Repositories\Bonus\BonusTypeRepository;
use App\Repositories\Bonus\UserBonusRepository;
use App\Repositories\Store\StoreRepository;

/**
 * Class BonusService
 * @package App\Services
 */
class BonusService
{
    private $bonusTypeRepository;
    private $userBonusRepository;
    private $authService;

    public function __construct(
        BonusTypeRepository $bonusTypeRepository,
        UserBonusRepository $userBonusRepository,
        AuthService $authService
    )
    {
        $this->bonusTypeRepository = $bonusTypeRepository;
        $this->userBonusRepository = $userBonusRepository;
        $this->authService = $authService;
    }

    /**
     * 领取红包
     * @return mixed
     */
    public function receiveBonus($userId = 0, $ad_id = 0, $pos_id = 0, $ad_type = 'home')
    {
        $re = $this->bonusTypeRepository->getBonusId($ad_id, $pos_id, $ad_type);
        if ($re) {
            $data['id'] = $re->ads_bonus_id;
            $data['userId'] = $userId;
            $res = $this->bonusTypeRepository->receiveBonus($data);
            if ($res) {
                $res->use_end_date = date('Y-m-d H:i:s', $res->use_end_date);
            }
            return $res;
        } else {
            return 0;
        }
    }

    /**
     * 领取红包
     * @return mixed
     */
    public function checkBonus($userId = 0, $ad_id = 0, $pos_id = 0, $ad_type = 'home')
    {
        $re = $this->bonusTypeRepository->getBonusId($ad_id, $pos_id, $ad_type);
        if ($re) {
            $data['id'] = $re->ads_bonus_id;
            $data['userId'] = $userId;
            $res = $this->bonusTypeRepository->checkBonus($data);
            if ($res) {
                $res->use_end_date = date('Y-m-d H:i:s', $res->use_end_date);
            }
            return $res;
        } else {
            return 0;
        }
    }

    /**
     * 获取红包信息
     * @return mixed
     */
    public function getBonusInfo($userId = 0, $ad_id = 0, $pos_id = 0, $ad_type = 'home')
    {
        //获取指定bonus_type_id == 17
        $re = $this->bonusTypeRepository->getBonusId($ad_id, $pos_id, $ad_type);
        $reData['ad_info'] = [];
        $reData['bn_info'] = 0;
        if ($re) {
            $data['id'] = $re->ads_bonus_id;
            $data['userId'] = $userId;
            $url = '';
            if ($_SERVER['HTTPS'] == 'on') {
                $url = 'https://';
            } else {
                $url = 'http://';
            }
            $re->ad_image = $url . $_SERVER['HTTP_HOST'] . '/' . $re->ad_image;
            $reData['ad_info'] = $re;
            $reData['bn_info'] = $this->bonusTypeRepository->getBonusCount($data);
            $bType = $this->bonusTypeRepository->getBonusType($re->ads_bonus_id);
            $res = $this->bonusTypeRepository->shopInfo($bType->user_id);
            if (!$res->logo_thumb) {
                $res->logo_thumb = '';
            } else {
                $res->logo_thumb = get_image_path(str_replace('../', '', $res->logo_thumb));
            }
            $reData['shop_info'] = $res;
        }

        return $reData;
    }
}
