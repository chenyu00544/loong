<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\PaymentRepositoryInterface;
use App\Facades\LangConfig;
use App\Http\Models\Shop\PaymentModel;

class PaymentRepository implements PaymentRepositoryInterface
{

    protected $paymentModel;

    public function __construct(PaymentModel $paymentModel)
    {
        $this->paymentModel = $paymentModel;
    }

    public function getPaymentAll()
    {
        $PayConfig = LangConfig::LangPayConf();
        $payConf = $this->paymentModel->getPaymentAll();
        $payArr = [];
        foreach ($payConf as $key => $value) {
            $payArr[$value->pay_code] = $value;
        }

        foreach ($PayConfig as $k => $val) {
            if (empty($payArr[$k])) {
                $val['install'] = false;
                $val['pay_id'] = 0;
                $val['pay_order'] = 0;
                $PayConfig[$k] = $val;
            } else {
                $val['install'] = true;
                foreach ($payArr[$k] as $n => $m) {
                    if($n == 'pay_config'){
                        $val[$n] = unserialize($m);
                    }else{
                        $val[$n] = $m;
                    }
                }
                $PayConfig[$k] = $val;
            }
        }
        return $PayConfig;
    }

    public function setPayment($data)
    {
        $where['pay_id'] = $data['id'];
        $updata['pay_order'] = $data['pay_order'];

    }

}