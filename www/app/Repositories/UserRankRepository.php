<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\UserRankRepositoryInterface;
use App\Http\Models\shop\UserRankModel;

class UserRankRepository implements UserRankRepositoryInterface
{

    protected $userRankModel;

    public function __construct(UserRankModel $userRankModel)
    {
        $this->userRankModel = $userRankModel;
    }

    public function getUserRanks()
    {
        return $this->userRankModel->getUserRanks([]);
    }


}