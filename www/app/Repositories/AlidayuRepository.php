<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AlidayuRepositoryInterface;
use App\Http\Models\Shop\AlidayuConfigureModel;

class AlidayuRepository implements AlidayuRepositoryInterface
{

    private $alidayuConfigureModel;

    public function __construct(
        AlidayuConfigureModel $alidayuConfigureModel
    )
    {
        $this->alidayuConfigureModel = $alidayuConfigureModel;
    }

    public function getAlidayuByPage()
    {
        return $this->alidayuConfigureModel->getAlidayuByPage();
    }
}