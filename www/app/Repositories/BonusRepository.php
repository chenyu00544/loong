<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\BonusRepositoryInterface;
use App\Http\Models\Shop\BonusModel;

class BonusRepository implements BonusRepositoryInterface
{
    private $bonusModel;

    public function __construct(
        BonusModel $bonusModel
    )
    {
        $this->bonusModel = $bonusModel;
    }

    public function getBonusByPage($search, $seller = 'selfsale')
    {
        $where = [];
        if ($seller == 'selfsale') {
            $where[] = ['user_id', '=', '0'];
        } else {
            $where[] = ['user_id', '<>', '0'];
        }
        $re = $this->bonusModel->getBonusByPage($where, $search);
        return $re;
    }

}