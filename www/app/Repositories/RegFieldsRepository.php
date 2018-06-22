<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\RegFieldsRepositoryInterface;
use App\Http\Models\Shop\RegFieldsModel;

class RegFieldsRepository implements RegFieldsRepositoryInterface
{

    protected $regFieldsModel;

    public function __construct(RegFieldsModel $regFieldsModel)
    {
        $this->regFieldsModel = $regFieldsModel;
    }

    public function getRegFields()
    {
        return $this->regFieldsModel->getRegFields();
    }


}