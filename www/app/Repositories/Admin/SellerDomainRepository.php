<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\SellerDomainRepositoryInterface;
use App\Http\Models\Shop\SellerDomainModel;

class SellerDomainRepository implements SellerDomainRepositoryInterface
{

    private $sellerDomainModel;

    public function __construct(
        SellerDomainModel $sellerDomainModel
    )
    {
        $this->sellerDomainModel = $sellerDomainModel;
    }

    public function getSellerDomainsByPage()
    {
        return $this->sellerDomainModel->getSellerDomainsByPage();
    }
}