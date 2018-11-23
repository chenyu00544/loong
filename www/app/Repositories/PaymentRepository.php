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
use App\Facades\RedisCache;
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
                    if ($n == 'pay_config') {
                        $val[$n] = unserialize($m);
                    } else {
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

    public function setPayment($data, $id)
    {
        $where['pay_id'] = $id;
        $column = ['pay_code', 'pay_name', 'pay_fee', 'pay_desc', 'enabled', 'is_cod', 'is_online'];
        $update = [];
        $payConf = [];
        foreach ($data as $key => $value) {
            if (in_array($key, $column)) {
                $update[$key] = $value;
            } else {
                $payConf[$key] = $value;
            }
        }
        RedisCache::set($update['pay_code'] . '_config', array_merge($update, $payConf));
        $update['pay_config'] = serialize($payConf);
        return $this->paymentModel->setPayment($where, $update);
    }

    public function addPayment($data)
    {
        $rep = ['code' => 5, 'msg' => '安装失败'];
        $PayConfig = LangConfig::LangPayConf();
        $pay = $PayConfig[$data['code']];//
        foreach ($pay as $key => $value) {
            if ($key == 'pay_config') {
                $conf = [];
                foreach ($value as $val) {
                    $conf[$val['code']] = $val['value'];
                }
                $addData[$key] = serialize($conf);
            } else {
                $addData[$key] = $value;
            }
        }

        $re = $this->paymentModel->addPayment($addData);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '安装成功'];
        }

        return $rep;
    }

    public function changes($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $where['pay_id'] = $data['id'];
        $key = '';
        if ($data['type'] == 'pay_order') {
            $key = 'pay_order';
        } elseif ($data['type'] == 'enabled') {
            $key = 'enabled';
        } elseif ($data['type'] == 'is_cod') {
            $key = 'is_cod';
        } elseif ($data['type'] == 'is_online') {
            $key = 'is_online';
        }
        $updata[$key] = $data['val'];
        $re = $this->paymentModel->setPayment($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

}