<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\BonusRepositoryInterface;
use App\Facades\Common;
use App\Http\Models\Shop\BonusModel;
use App\Http\Models\Shop\BonusUserModel;

class BonusRepository implements BonusRepositoryInterface
{
    private $bonusModel;
    private $bonusUserModel;

    public function __construct(
        BonusModel $bonusModel,
        BonusUserModel $bonusUserModel
    )
    {
        $this->bonusModel = $bonusModel;
        $this->bonusUserModel = $bonusUserModel;
    }

    public function getBonusByPage($search, $seller = 'selfsale')
    {
        $where = [];
        if ($seller == 'selfsale') {
            $where[] = ['user_id', '=', '0'];
        } else {
            $where[] = ['user_id', '<>', '0'];
        }
        $re = $this->bonusModel->getBonusByPage($where, $search);
        return $re;
    }

    public function getBonus($id)
    {
        $where['bonus_id'] = $id;
        return $this->bonusModel->getBonus($where);
    }

    public function setBonus($data, $id)
    {
        $where['bonus_id'] = $id;
        if (isset($data['send_type'])) {
            if ($data['send_type'] == 0 || $data['send_type'] == 3 || $data['send_type'] == 4) {
                unset($data['send_start_end_date']);
                unset($data['min_amount']);
            } elseif ($data['send_type'] == 1) {
                unset($data['min_amount']);
            }
        }
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'use_start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['use_start_date'] = strtotime($date_arr[0]);
                        $updata['use_end_date'] = strtotime($date_arr[1]);
                    }
                    break;
                case 'send_start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['send_start_date'] = strtotime($date_arr[0]);
                        $updata['send_end_date'] = strtotime($date_arr[1]);
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        return $this->bonusModel->setBonus($where, $updata);
    }

    public function addBonus($data, $admin)
    {
        $updata = [];
        if (isset($data['send_type'])) {
            if ($data['send_type'] == 0 || $data['send_type'] == 3 || $data['send_type'] == 4) {
                unset($data['send_start_end_date']);
                unset($data['min_amount']);
            } elseif ($data['send_type'] == 1) {
                unset($data['min_amount']);
            }
        }
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'use_start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['use_start_date'] = strtotime($date_arr[0]);
                        $updata['use_end_date'] = strtotime($date_arr[1]);
                    }
                    break;
                case 'send_start_end_date':
                    $date_arr = explode('～', $value);
                    if (count($date_arr) == 2) {
                        $updata['send_start_date'] = strtotime($date_arr[0]);
                        $updata['send_end_date'] = strtotime($date_arr[1]);
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        $updata['user_id'] = $admin->ru_id;
        $updata['review_status'] = 3;
        return $this->bonusModel->addBonus($updata);
    }

    public function delBonus($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['bonus_id'] = $id;
        $where_t['bonus_type_id'] = $id;
        $this->bonusUserModel->delBonusUser($where_t);
        $re = $this->bonusModel->delBonus($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function getBonusUserByPage($id)
    {
        $where['bonus_type_id'] = $id;
        return $this->bonusUserModel->getBonusUserByPage($where);
    }

    public function addBonusUser($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        if (!empty($data['bonus_sum'])) {
            $updata['bonus_type_id'] = $data['bonus_type_id'];
            for ($i = 0; $i < $data['bonus_sum']; $i++) {
                $updata['bonus_sn'] = time() . Common::randStr(6);
                $updata['bonus_password'] = Common::randStr(10);
                $re = $this->bonusUserModel->addBonusUser($updata);
            }
        }
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delBonusUser($ids)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where = $ids['ids'];
        $re = $this->bonusUserModel->delBonusUsers($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

}