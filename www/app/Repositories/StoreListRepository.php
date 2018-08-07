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

    public function getStoreListByPage($search, $where= [])
    {
        return $this->merchantsShopInformationModel->getMerchantsShopsByPage($where, $search);
    }
}