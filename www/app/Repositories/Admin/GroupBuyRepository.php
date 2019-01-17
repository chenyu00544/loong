<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\GroupBuyRepositoryInterface;
use App\Facades\RedisCache;
use App\Http\Models\Shop\GoodsActivityModel;

class GroupBuyRepository implements GroupBuyRepositoryInterface
{

    private $goodsActivityModel;

    public function __construct(
        GoodsActivityModel $goodsActivityModel
    )
    {
        $this->goodsActivityModel = $goodsActivityModel;
    }

    public function getGroupBuyByPage($seller, $search = [])
    {
        if ($seller == 'seller') {
            $where = [['user_id', '<>', 0], ['act_type', '=', 1]];
        } else {
            $where = [['user_id', '=', 0], ['act_type', '=', 1]];
        }
        $res = $this->goodsActivityModel->getGoodsActivityByPage($where, ['*'], $search['keywords']);
        foreach ($res as $re) {
            $ext_info = unserialize($re->ext_info);
            foreach ($ext_info as $key => $value) {
                $re->$key = $value;
            }
        }
        return $res;
    }

    public function addGroupBuy($data, $user)
    {
        $update['goods_id'] = $data['goods_id'];
        $start_end_date = explode('～', $data['use_start_end_date']);
        $update['start_time'] = strtotime($start_end_date[0]);
        $update['end_time'] = strtotime($start_end_date[1]);
        $update['goods_name'] = $data['goods_name'];
        $update['act_name'] = $data['goods_name'];
        $update['act_type'] = 1;
        $update['user_id'] = $user->ru_id;
        $volume = [];
        foreach ($data['volume_number'] as $key => $volume_number) {
            $vol['amount'] = $data['volume_number'][$key];
            $vol['price'] = $data['volume_price'][$key];
            $volume[] = $vol;
        }
        $serialize = [
            'deposit' => $data['deposit'],
            'restrict_amount' => $data['restrict_amount'],
            'gift_integral' => $data['gift_integral'],
            'price_ladder' => $volume,
        ];
        $update['ext_info'] = serialize($serialize);
        $update['act_desc'] = $data['seller_note'];
        $update['is_new'] = $data['is_new'];
        $update['is_hot'] = $data['is_hot'];
        $update['review_status'] = 3;
        return $this->goodsActivityModel->addGoodsActivity($update);
    }

    public function getGroupBuy($id)
    {
        $where['act_id'] = $id;
        $group = $this->goodsActivityModel->getGroupBuy($where);
        $group->date_format = date(RedisCache::get('shop_config')['time_format'], $group->start_time) . '～' . date(RedisCache::get('shop_config')['time_format'], $group->end_time);
        $group->ext_info = unserialize($group->ext_info);
        return $group;
    }

    public function setGroupBuy($data, $id)
    {
        $where['act_id'] = $id;
        $start_end_date = explode('～', $data['use_start_end_date']);
        $updata['start_time'] = strtotime($start_end_date[0]);
        $updata['end_time'] = strtotime($start_end_date[1]);
        $volume = [];
        foreach ($data['volume_number'] as $key => $volume_number) {
            $vol['amount'] = $data['volume_number'][$key];
            $vol['price'] = $data['volume_price'][$key];
            $volume[] = $vol;
        }
        $serialize = [
            'deposit' => $data['deposit'],
            'restrict_amount' => $data['restrict_amount'],
            'gift_integral' => $data['gift_integral'],
            'price_ladder' => $volume,
        ];
        $updata['ext_info'] = serialize($serialize);
        $updata['act_desc'] = $data['seller_note'];
        $updata['is_new'] = $data['is_new'];
        $updata['is_hot'] = $data['is_hot'];
        return $this->goodsActivityModel->setGroupBuy($where, $updata);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['act_id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'is_finish':
                $updata['is_finished'] = 1;
                break;
        }
        $re = $this->goodsActivityModel->setGroupBuy($where, $updata);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delGroupBuy($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['act_id'] = $id;
        $re = $this->goodsActivityModel->delGroupBuy($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}