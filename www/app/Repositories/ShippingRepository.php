<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\ShippingRepositoryInterface;
use App\Facades\Express;
use App\Http\Models\Shop\ShippingModel;
use PhpParser\Node\Expr\Cast\Object_;

class ShippingRepository implements ShippingRepositoryInterface
{

    private $shippingModel;

    public function __construct(ShippingModel $shippingModel)
    {
        $this->shippingModel = $shippingModel;
    }

    public function getShippingAll()
    {
        $express = Express::expressList();
        $express['express'];
        $shipping = $this->shippingModel->getShippingAll();
        foreach ($shipping->toArray() as $ship) {
            foreach ($ship as $key => $val) {
                $shipArr[$ship['shipping_code']][$key] = $val;
            }
        }

        foreach ($express['express'] as $exp) {
            if (!empty($shipArr[$exp . '_express'])) {
                foreach ($shipArr[$exp . '_express'] as $key => $val) {
                    $shipList[$exp . '_express'][$key] = $val;
                }
            } else {
                $shipList[$exp . '_express']['shipping_code'] = $exp . '_express';
                $shipList[$exp . '_express']['outside_code'] = $express[$exp . '_code'];
                $shipList[$exp . '_express']['shipping_name'] = $express[$exp . '_express'];
                $shipList[$exp . '_express']['shipping_desc'] = $express[$exp . '_express_desc'];
            }
        }
        return $shipList;
    }

    public function getShipping($id)
    {
        return $this->shippingModel->getShipping($id);
    }

    public function addShip($code)
    {
        $express = Express::expressList();
        $lower_code = strtolower($code);
        $req = ['code' => 5, 'msg' => '安装失败'];
        $data['outside_code'] = $code;
        $data['shipping_code'] = $lower_code . '_express';
        $data['shipping_name'] = $express[$lower_code . '_express'];
        $data['shipping_desc'] = $express[$lower_code . '_express_desc'];
        $re = $this->shippingModel->addShip($data);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '安装成功';
        }
        return $req;
    }

    public function changes($data)
    {
        $where = [];
        $updata = [];
        foreach ($data as $key => $val){
            switch ($key){
                case 'id':
                    $where['shipping_id'] = $val;
                    break;
                default:
                    $updata[$key] = $val;
                    break;
            }
        }
        return $this->shippingModel->changes($where, $updata);
    }

    public function deleteShip($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['shipping_id'] = $id;
        $re = $this->shippingModel->deleteShip($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }
}