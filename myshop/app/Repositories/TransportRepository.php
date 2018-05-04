<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\TransportRepositoryInterface;
use App\Http\Models\Shop\TransportModel;

class TransportRepository implements TransportRepositoryInterface
{

    protected $transportModel;

    public function __construct(TransportModel $transportModel)
    {
        $this->transportModel = $transportModel;
    }

    public function getTransportAll()
    {
        return $this->transportModel->getTransportAll();
    }

    public function getTransportInfo($id)
    {

    }
}