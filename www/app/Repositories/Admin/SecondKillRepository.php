<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\SecondKillRepositoryInterface;
use App\Http\Models\Shop\SecKillModel;

class SecondKillRepository implements SecondKillRepositoryInterface
{

    private $secKillModel;

    public function __construct(
        SecKillModel $secKillModel
    )
    {
        $this->secKillModel = $secKillModel;
    }

    public function getSecondKillByPage($seller, $search = [])
    {
        if ($seller == 'seller') {
            $where = [['ru_id', '<>', 0]];
        } else {
            $where = [['ru_id', '=', 0]];
        }
        $res = $this->secKillModel->getSecKillByPage($where, ['*'], $search['keywords']);
        return $res;
    }
}