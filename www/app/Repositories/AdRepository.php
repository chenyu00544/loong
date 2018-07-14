<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AdRepositoryInterface;
use App\Http\Models\Shop\AdModel;
use App\Http\Models\Shop\AdPositionModel;

class AdRepository implements AdRepositoryInterface
{

    private $adModel;
    private $adPositionModel;

    public function __construct(
        AdModel $adModel,
        AdPositionModel $adPositionModel
    )
    {
        $this->adModel = $adModel;
        $this->adPositionModel = $adPositionModel;
    }

    public function getAdByPage($type, $search)
    {
        return $this->adModel->getAdByPage($type, $search);
    }

    public function getAdPosByPage($type, $search)
    {
        return $this->adPositionModel->getAdPosByPage(['ad_terminal' => $type], $search);
    }
}