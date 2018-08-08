<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SellerRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\EntryCriteriaModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\SellerGradeModel;
use App\Http\Models\Shop\SellerShopInfoModel;
use App\Http\Models\Shop\ShippingModel;

class SellerRepository implements SellerRepositoryInterface
{

    private $sellerShopInfoModel;
    private $regionsModel;
    private $shippingModel;

    public function __construct(
        SellerShopInfoModel $sellerShopInfoModel,
        RegionsModel $regionsModel,
        ShippingModel $shippingModel
    )
    {
        $this->sellerShopInfoModel = $sellerShopInfoModel;
        $this->regionsModel = $regionsModel;
        $this->shippingModel = $shippingModel;
    }

    public function getSellerShopInfo($where)
    {
        $rep = $this->sellerShopInfoModel->getSellerShopInfo($where);
        $rep->shop_country = $this->regionsModel->getRegions(0, 0);
        $rep->shop_province = $this->regionsModel->getRegions(1, $rep->country ? $rep->country : 1);
        $rep->shop_city = $this->regionsModel->getRegions(2, $rep->province);
        $rep->shop_district = $this->regionsModel->getRegions(3, $rep->city);
        $rep->shippings = $this->shippingModel->getShippingAll();
        return $rep;
    }

    public function setSellerShopInfo($data, $id)
    {
        $where['id'] = $id;
        return $this->sellerShopInfoModel->setSellerShopInfo($where, $data);
    }
}