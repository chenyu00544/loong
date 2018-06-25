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
        return $this->regFieldsModel->getRegFields([]);
    }

    public function getRegField($id)
    {
        $where['id'] = $id;
        return $this->regFieldsModel->getRegField($where);
    }

    public function setRegField($data, $id)
    {
        $where['id'] = $id;
        return $this->regFieldsModel->setRegField($where, $data);
    }

    public function changes($data)
    {
        $req = ['code' => 5, 'msg' => '修改失败'];
        $where['id'] = $data['id'];
        $updata = [];
        if ($data['type'] == 'display') {
            $updata['display'] = $data['val'];
        } elseif ($data['type'] == 'is_need') {
            $updata['is_need'] = $data['val'];
        } else {
            $updata['dis_order'] = $data['val'];
        }
        $re = $this->regFieldsModel->setRegField($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '修改成功';
        }
        return $req;
    }

    public function addRegField($data)
    {
        $data['type'] = 1;
        return $this->regFieldsModel->addRegField($data);
    }

}