<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AlismsRepositoryInterface;
use App\Http\Models\Shop\AlismsConfigureModel;

class AlismsRepository implements AlismsRepositoryInterface
{

    private $alismsConfigureModel;

    public function __construct(
        AlismsConfigureModel $alismsConfigureModel
    )
    {
        $this->alismsConfigureModel = $alismsConfigureModel;
    }

    public function getAlismsByPage()
    {
        return $this->alismsConfigureModel->getAlismsByPage();
    }
}