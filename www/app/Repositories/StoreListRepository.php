<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\StoreListRepositoryInterface;
use App\Http\Models\Shop\MerchantsShopInformationModel;

class StoreListRepository implements StoreListRepositoryInterface
{

    private $merchantsShopInformationModel;

    public function __construct(
        MerchantsShopInformationModel $merchantsShopInformationModel
    )
    {
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
    }

    public function getStoresByPage($search, $where= [])
    {
        return $this->merchantsShopInformationModel->getMerchantsShopsByPage($where, $search);
    }

    public function setStore($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['shop_id'] = $data['id'];
        $updata = [];
        unset($data['id']);
        switch ($data['type']){
            case 'is_street':
                $updata['is_street'] = $data['val'];
                break;
            case 'sort':
                $updata['sort_order'] = $data['val'];
                break;
            case 'is_im':
                $updata['is_im'] = $data['val'];
                break;
        }

        $re = $this->merchantsShopInformationModel->setMerchantsShopInfo($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}