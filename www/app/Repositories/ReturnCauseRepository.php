<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\ReturnCauseRepositoryInterface;
use App\Http\Models\Shop\ReturnCauseModel;

class ReturnCauseRepository implements ReturnCauseRepositoryInterface
{

    private $returnCauseModel;

    public function __construct(
        ReturnCauseModel $returnCauseModel
    )
    {
        $this->returnCauseModel = $returnCauseModel;
    }

    public function getReturnCauseByPage()
    {
        return $this->returnCauseModel->getReturnCauseByPage([]);
    }

    public function getReturnCause($id)
    {
        $where['cause_id'] = $id;
        return $this->returnCauseModel->getReturnCause($where);

    }

    public function setReturnCause($data, $id)
    {
        $where['cause_id'] = $id;
        return $this->returnCauseModel->setReturnCause($where, $data);
    }

    public function change($data)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['cause_id'] = $data['id'];
        switch ($data['type']){
            case 'is_show':
                $updata['is_show'] = $data['value'];
                break;
            case 'sort':
                $updata['sort_order'] = $data['value'];
                break;
            default:
                break;
        }
        $re = $this->returnCauseModel->setReturnCause($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }

    public function addReturnCause($data)
    {
        return $this->returnCauseModel->addReturnCause($data);
    }

    public function delReturnCause($id)
    {
        $rep = ['code' => 5, 'msg' => '操作失败'];
        $where['cause_id'] = $id;
        $re = $this->returnCauseModel->delReturnCause($where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '操作成功'];
        }
        return $rep;
    }
}