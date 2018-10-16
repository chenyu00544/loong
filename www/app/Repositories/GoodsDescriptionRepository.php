<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\GoodsDescriptionRepositoryInterface;
use App\Http\Models\Shop\GoodsDescriptionModel;

class GoodsDescriptionRepository implements GoodsDescriptionRepositoryInterface
{
    private $goodsDescriptionModel;

    public function __construct(
        GoodsDescriptionModel $goodsDescriptionModel
    )
    {
        $this->goodsDescriptionModel = $goodsDescriptionModel;
    }

    public function getGoodsDescriptions()
    {
        return $this->goodsDescriptionModel->getGoodsDescriptions();
    }

    public function getGoodsDescription($id)
    {
        $where['id'] = $id;
        return $this->goodsDescriptionModel->getGoodsDescription($where);
    }

    public function setGoodsDescription($data, $id)
    {
        $where['id'] = $id;
        return $this->goodsDescriptionModel->setGoodsDescription($where, $data);
    }

    public function addGoodsDescription($data)
    {
        return $this->goodsDescriptionModel->addGoodsDescription($data);
    }

    public function delGoodsDescription($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->goodsDescriptionModel->delGoodsDescription($where);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}