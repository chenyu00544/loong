<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\SeoRepositoryInterface;
use App\Http\Models\Shop\SeoModel;

class SeoRepository implements SeoRepositoryInterface
{

    protected $seoModel;

    public function __construct(SeoModel $seoModel)
    {
        $this->seoModel = $seoModel;
    }

    public function getSeo($where)
    {
        return $this->seoModel->getSeo($where);
    }

    public function setSeo($data, $id)
    {
        $where['id'] = $id;
        return $this->seoModel->setSeo($where, $data);
    }
}