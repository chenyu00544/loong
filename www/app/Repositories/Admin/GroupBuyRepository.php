<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\GroupBuyRepositoryInterface;
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
            foreach ($ext_info as $key => $value){
                $re->$key = $value;
            }
        }
        return $res;
    }
}