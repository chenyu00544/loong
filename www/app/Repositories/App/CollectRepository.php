<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\CollectRepositoryInterface;
use App\Http\Models\App\CollectBrandModel;
use App\Http\Models\App\CollectGoodsModel;
use App\Http\Models\App\CollectStoreModel;

class CollectRepository implements CollectRepositoryInterface
{

    private $collectGoodsModel;
    private $collectBrandModel;
    private $collectStoreModel;

    public function __construct(
        CollectGoodsModel $collectGoodsModel,
        CollectBrandModel $collectBrandModel,
        CollectStoreModel $collectStoreModel
    )
    {
        $this->collectGoodsModel = $collectGoodsModel;
        $this->collectBrandModel = $collectBrandModel;
        $this->collectStoreModel = $collectStoreModel;
    }

    public function colloectsByGoods($data, $uid)
    {
        $where['user_id'] = $uid;
        $where['is_attention'] = 1;
        $page = $data['page'];
        $res = $this->collectGoodsModel->getColloectsByGoods($where, $page);
        foreach ($res as $re) {
            if(!empty($re->goods->toArray())){
                foreach ($re->goods->toArray() as $k => $v) {
                    if ($k != 'add_time') {
                        $re->$k = $v;
                    }
                }
            }
            unset($re->goods);
        }
        return $res;
    }

    public function collectGoods($request, $uid)
    {
        $goods_ids = explode(',', $request['goods_id']);
        $data = [];
        foreach ($goods_ids as $goods_id) {
            $where['goods_id'] = $goods_id;
            $where['user_id'] = $uid;
            $re = $this->collectGoodsModel->getCollectGoods($where);
            if (!$re) {
                $data['goods_id'] = $request['goods_id'];
                $data['user_id'] = $uid;
                $data['add_time'] = time();
                $data['is_attention'] = 1;
                $this->collectGoodsModel->addCollectGoods($data);
            } else {
                if ($re->is_attention == 1) {
                    $data['is_attention'] = 0;
                } else {
                    $data['is_attention'] = 1;
                }
                $this->collectGoodsModel->setCollectGoods($where, $data);
            }
        }
        return $data;
    }

    public function collectBrand($request, $uid)
    {
        $where['brand_id'] = $request['brand_id'];
        $where['ru_id'] = $request['store_id'];
        $where['user_id'] = $uid;
        $re = $this->collectBrandModel->getCollectBrand($where);
        if (!$re) {
            $data['brand_id'] = $request['brand_id'];
            $data['ru_id'] = $request['store_id'];
            $data['user_id'] = $uid;
            $data['add_time'] = time();
            $data['is_attention'] = 1;
            return $this->collectBrandModel->addCollectBrand($data);
        } else {
            if ($re->is_attention == 1) {
                $data['is_attention'] = 0;
            } else {
                $data['is_attention'] = 1;
            }
            return $this->collectBrandModel->setCollectBrand($where, $data);
        }
    }

    public function collectStore($request, $uid)
    {
        $where['ru_id'] = $request['store_id'];
        $where['user_id'] = $uid;
        $re = $this->collectStoreModel->getCollectStore($where);
        if (!$re) {
            $data['ru_id'] = $request['store_id'];
            $data['user_id'] = $uid;
            $data['add_time'] = time();
            $data['is_attention'] = 1;
            return $this->collectStoreModel->addCollectStore($data);
        } else {
            if ($re->is_attention == 1) {
                $data['is_attention'] = 0;
            } else {
                $data['is_attention'] = 1;
            }
            return $this->collectStoreModel->setCollectStore($where, $data);
        }
    }
}