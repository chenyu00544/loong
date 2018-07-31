<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\MobileNavRepositoryInterface;
use App\Http\Models\Shop\MobileNavModel;

class MobileNavRepository implements MobileNavRepositoryInterface
{

    private $mobileNavModel;

    public function __construct(
        MobileNavModel $mobileNavModel
    )
    {
        $this->mobileNavModel = $mobileNavModel;
    }

    public function getMoveNavByPage()
    {
        return $this->mobileNavModel->getMoveNavByPage([]);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'ifshow':
                $updata['ifshow'] = $data['val'];
                break;
            case 'opennew':
                $updata['opennew'] = $data['val'];
                break;
            case 'order':
                $updata['vieworder'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->mobileNavModel->setMoveNav($where, $updata);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delMoveNav($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->mobileNavModel->delMoveNav($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}