<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\PaymentRepositoryInterface;
use App\Facades\Html;
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
            $payArr[$value->pay_code] = $value->toArray();
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

    public function getPayment($id)
    {
        $where['pay_id'] = $id;
        $pay = $this->paymentModel->getPayment($where);
        $PayConfig = LangConfig::LangPayConf();
        $pay->pay_config = Html::PayConfHtml($PayConfig[$pay->pay_code]['pay_config'], unserialize($pay->pay_config));
        return $pay;
    }

    public function setPayment($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['pay_id'] = $data['id'];
        $updata['pay_order'] = $data['pay_order'];
        $re = $this->paymentModel->setPayment($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

    public function addPayment($data)
    {
        $rep = ['code' => 5, 'msg' => '安装失败'];
        $PayConfig = LangConfig::LangPayConf();
        $pay = $PayConfig[$data['code']];//
        foreach ($pay as $key => $value){
            if($key == 'pay_config'){
                foreach ($value as $val){
                    $conf[$val['code']] = $val['value'];
                }
                $addData[$key] = serialize($conf);
            }else{
                $addData[$key] = $value;
            }
        }

        $re = $this->paymentModel->addPayment($addData);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '安装成功'];
        }

        return $rep;
    }
    
}