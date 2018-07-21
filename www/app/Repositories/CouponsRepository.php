<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\CouponsRepositoryInterface;
use App\Http\Models\Shop\CouponsModel;

class CouponsRepository implements CouponsRepositoryInterface
{

    private $couponsModel;

    public function __construct(
        CouponsModel $couponsModel
    )
    {
    }
}