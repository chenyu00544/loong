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
        foreach ($shipping->toArray() as $ship){
            foreach ($ship as $key => $val){
                $shipArr[$ship['shipping_code']][$key] = $val;
            }
        }

        foreach ($express['express'] as $exp){
            if(!empty($shipArr[$exp.'_express'])){
                foreach ($shipArr[$exp.'_express'] as $key => $val){
                    $shipList[$exp.'_express'][$key] = $val;
                }
            }else{
                $shipList[$exp.'_express']['shipping_code'] = $express[$exp.'_code'];
                $shipList[$exp.'_express']['shipping_name'] = $express[$exp.'_express'];
                $shipList[$exp.'_express']['shipping_desc'] = $express[$exp.'_express_desc'];
            }
        }
        return $shipList;
    }
}