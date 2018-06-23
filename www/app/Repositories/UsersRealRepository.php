<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UsersRealRepositoryInterface;
use App\Http\Models\Shop\UsersRealModel;

class UsersRealRepository implements UsersRealRepositoryInterface
{

    private $usersRealModel;

    public function __construct(
        UsersRealModel $usersRealModel
    )
    {
        $this->usersRealModel = $usersRealModel;
    }

    public function getUserRealsByPage()
    {
        return $this->usersRealModel->getUserRealsByPage();
    }
}