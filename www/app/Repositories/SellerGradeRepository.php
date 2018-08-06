<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\SellerGradeRepositoryInterface;
use App\Http\Models\Shop\SellerGradeModel;

class SellerGradeRepository implements SellerGradeRepositoryInterface
{

    private $sellerGradeModel;

    public function __construct(
        SellerGradeModel $sellerGradeModel
    )
    {
        $this->sellerGradeModel = $sellerGradeModel;
    }

    public function getSellerGradesByPage()
    {
        return $this->sellerGradeModel->getSellerGradesByPage();
    }
}