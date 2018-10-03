<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\App;

use App\Contracts\AdRepositoryInterface;

class AdRepository implements AdRepositoryInterface
{

    private $adModel;
    private $adPositionModel;

    public function __construct(

    )
    {
    }

    public function getAdPositionAndAds()
    {
        return 1;
    }
}