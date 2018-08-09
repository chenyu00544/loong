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
use App\Http\Models\Shop\MerchantsShopInformationModel;
use App\Http\Models\Shop\RegionsModel;
use App\Http\Models\Shop\SellerDomainModel;
use App\Http\Models\Shop\SellerGradeModel;
use App\Http\Models\Shop\SellerQrcodeModel;
use App\Http\Models\Shop\SellerShopInfoModel;
use App\Http\Models\Shop\ShippingModel;

class SellerRepository implements SellerRepositoryInterface
{

    private $sellerShopInfoModel;
    private $regionsModel;
    private $shippingModel;
    private $sellerDomainModel;
    private $merchantsShopInformationModel;
    private $sellerQrcodeModel;

    public function __construct(
        SellerShopInfoModel $sellerShopInfoModel,
        RegionsModel $regionsModel,
        ShippingModel $shippingModel,
        SellerDomainModel $sellerDomainModel,
        MerchantsShopInformationModel $merchantsShopInformationModel,
        SellerQrcodeModel $sellerQrcodeModel
    )
    {
        $this->sellerShopInfoModel = $sellerShopInfoModel;
        $this->regionsModel = $regionsModel;
        $this->shippingModel = $shippingModel;
        $this->sellerDomainModel = $sellerDomainModel;
        $this->merchantsShopInformationModel = $merchantsShopInformationModel;
        $this->sellerQrcodeModel = $sellerQrcodeModel;
    }

    public function getSellerShopInfo($where)
    {
        $rep = $this->sellerShopInfoModel->getSellerShopInfo($where);
        $rep->shop_country = $this->regionsModel->getRegions(0, 0);
        $rep->shop_province = $this->regionsModel->getRegions(1, $rep->country ? $rep->country : 1);
        $rep->shop_city = $this->regionsModel->getRegions(2, $rep->province);
        $rep->shop_district = $this->regionsModel->getRegions(3, $rep->city);
        $rep->shippings = $this->shippingModel->getShippingAll();
        $rep->domain_name = $this->sellerDomainModel->getSellerDomain($where);
        $rep->merchants_shop = $this->merchantsShopInformationModel->getMerchantsShopInfo(['user_id' => $where['ru_id']]);
        $rep->qrcode = $this->sellerQrcodeModel->getSellerQrcode($where);
        return $rep;
    }

    public function setSellerShopInfo($data, $id)
    {
        $where['ru_id'] = $id;
        $updata['domain_name'] = $data['domain_name'];
        unset($data['domain_name']);

        $this->sellerDomainModel->setSellerDomain($where, $updata);
        $img_file = ['shop_logo', 'logo_thumb', 'street_thumb', 'brand_thumb', 'qrcode_thumb'];
        foreach ($data as $key => $value) {
            if (in_array($key, $img_file)) {
                if ($key == 'qrcode_thumb') {
                    $path = 'seller' . DIRECTORY_SEPARATOR . $key . DIRECTORY_SEPARATOR . $id;
                    $uri = FileHandle::upLoadImage($value, $path);
                    $qrdata[$key] = $uri;
                    FileHandle::deleteFile($data[$key . '_bak']);
                    $qr = $this->sellerQrcodeModel->setSellerQrcode($where, $qrdata);
                    if (!$qr) {
                        $qrdata['ru_id'] = $id;
                        $this->sellerQrcodeModel->addSellerQrcode($qrdata);
                    }
                    unset($data[$key . '_bak']);
                    unset($data[$key]);
                } else {
                    $path = 'seller' . DIRECTORY_SEPARATOR . $key . DIRECTORY_SEPARATOR . $id;
                    $uri = FileHandle::upLoadImage($value, $path);
                    $data[$key] = $uri;
                    FileHandle::deleteFile($data[$key . '_bak']);
                }
            } else {
                if (empty($value) && $key != 'qrcode_thumb_bak') {
                    unset($data[$key]);
                }
            }
        }
        unset($data['shop_logo_bak']);
        unset($data['logo_thumb_bak']);
        unset($data['street_thumb_bak']);
        unset($data['brand_thumb_bak']);
        unset($data['qrcode_thumb_bak']);
        return $this->sellerShopInfoModel->setSellerShopInfo($where, $data);
    }
}