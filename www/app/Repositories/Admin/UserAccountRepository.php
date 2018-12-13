<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\UserAccountRepositoryInterface;
use App\Http\Models\Shop\PaymentModel;
use App\Http\Models\Shop\UserAccountModel;
use App\Http\Models\Shop\UsersModel;
use Illuminate\Support\Facades\DB;

class UserAccountRepository implements UserAccountRepositoryInterface
{
    private $userAccountModel;
    private $paymentModel;
    private $usersModel;

    public function __construct(
        UserAccountModel $userAccountModel,
        PaymentModel $paymentModel,
        UsersModel $usersModel
    )
    {
        $this->userAccountModel = $userAccountModel;
        $this->paymentModel = $paymentModel;
        $this->usersModel = $usersModel;
    }

    public function getUserAccountsByPages($data, $type = 1)
    {
        $where['process_type'] = $type;
        $keywords = '';
        if (!empty($data['date'])) {
            $dateArr = explode('～', $data['date']);
            $where[] = ['paid_time', '>', strtotime($dateArr[0])];
            $where[] = ['paid_time', '<', strtotime($dateArr[1])];
        }
        if (!empty($data['keywords'])) {
            $keywords = '%' . $data['keywords'] . '%';
        }
        if (isset($data['is_paid']) && $data['is_paid'] != -1) {
            $where['is_paid'] = $data['is_paid'];
        }
        return $this->userAccountModel->getAccountsByPages($where, $keywords);
    }

    public function getUserAccount($id)
    {
        $where['id'] = $id;
        return $this->userAccountModel->getAccount($where);
    }

    public function setUserAccount($data, $adminUser, $id)
    {
        DB::beginTransaction();
        $user = [];
        $where['id'] = $id;
        if (!empty($data['user_id'])) {
            $user = $this->usersModel->getUser(['user_id' => $data['user_id']]);
            if ($user->count() == 0) {
                return ['code' => 1, 'msg' => '未找到会员'];
            }
        }
        $account = $this->userAccountModel->getAccount($where);
        $amount = abs($account->amount);
        if ($account->process_type == 1) {//提现
            if ($user->user_money < $amount) {
                return ['code' => 1, 'msg' => '账号余额不足'];
            }
        }
        if ($account->is_paid == 1) {
            return ['code' => 1, 'msg' => '已经完成操作'];
        }
        $updata['paid_time'] = time();
        $updata['admin_user'] = $adminUser->user_name;
        $updata['admin_note'] = !empty($data['admin_note']) ? $data['admin_note'] : '';
        $updata['user_note'] = !empty($data['user_note']) ? $data['user_note'] : '';
        $updata['is_paid'] = !empty($data['is_paid']) ? $data['is_paid'] : 0;
        $uaccount = $this->userAccountModel->setUserAccount($where, $updata);
        $commitBool = false;
        if ($updata['is_paid'] == 1) {
            if ($account->process_type == 1) {//提现
                $userData['frozen_money'] = $user->frozen_money - $amount;
                $result_user = $this->usersModel->setUser(['user_id' => $account->user_id], $userData);
                if ($uaccount && $result_user) {
                    $commitBool = true;
                }
            } else {//充值
                $userData['user_money'] = $user->user_money + $amount;
                $result_user = $this->usersModel->setUser(['user_id' => $account->user_id], $userData);
                if ($uaccount && $result_user) {
                    $commitBool = true;
                }
            }
        }

        if ($commitBool) {
            DB::commit();
            return ['code' => 0, 'msg' => '操作成功'];
        } else {
            DB::rollBack();
            return ['code' => 1, 'msg' => '操作失败'];
        }
    }

    public function addUserAccount($data, $adminUser)
    {
        $user = [];
        $payment = [];
        if (!empty($data['username'])) {
            $user = $this->usersModel->getUser(['user_name' => $data['username']]);
            if ($user->count() == 0) {
                return ['code' => 1, 'msg' => '未找到会员'];
            }
        }
        if (!empty($data['pay_id'])) {
            $payment = $this->paymentModel->getPayment(['pay_id' => $data['pay_id'], 'is_online' => 1]);
            if ($payment->count() == 0) {
                return ['code' => 1, 'msg' => '平台未设置支付账号'];
            }
        }

        $updata['user_id'] = $user->user_id;
        $updata['admin_user'] = $adminUser->user_name;
        $updata['deposit_fee'] = !empty($data['deposit_fee']) ? $data['deposit_fee'] : 0;
        $updata['add_time'] = time();
        $updata['paid_time'] = time();
        $updata['admin_note'] = !empty($data['admin_note']) ? $data['admin_note'] : '';
        $updata['user_note'] = !empty($data['user_note']) ? $data['user_note'] : '';
        $updata['process_type'] = !empty($data['process_type']) ? $data['process_type'] : 0;
        $updata['payment'] = $payment->pay_name;
        $updata['pay_id'] = $payment->pay_id;
        $updata['deposit_fee'] = $payment->pay_fee;
        $amount = !empty($data['amount']) ? $data['amount'] : 0;
        if ($updata['process_type'] == 0) {//充值
            $updata['amount'] = $amount;
        } else {
            $updata['amount'] = -$amount;
            if ($user->user_money < $amount) {
                return ['code' => 1, 'msg' => '账号余额不足'];
            }
        }
        $updata['is_paid'] = !empty($data['is_paid']) ? $data['is_paid'] : 0;
        DB::beginTransaction();
        $uaccount = $this->userAccountModel->addAccount($updata);
        $commitBool = false;
        if ($updata['is_paid'] == 1) {
            $where['user_id'] = $user->user_id;
            $userData['user_money'] = $user->user_money + $updata['amount'];
            $result_user = $this->usersModel->setUser($where, $userData);
            if ($uaccount && $result_user) {
                $commitBool = true;
            }
        } else {
            if ($updata['process_type'] == 1) {//提现
                $where['user_id'] = $user->user_id;
                $userData['user_money'] = $user->user_money - $amount;
                $userData['frozen_money'] = $user->frozen_money + $amount;
                $result_user = $this->usersModel->setUser($where, $userData);
                if ($uaccount && $result_user) {
                    $commitBool = true;
                }
            } else {
                if ($uaccount) {
                    $commitBool = true;
                }
            }
        }
        if ($commitBool) {
            DB::commit();
            return ['code' => 0, 'msg' => '操作成功'];
        } else {
            DB::rollBack();
            return ['code' => 1, 'msg' => '操作失败'];
        }
    }

    public function delAccount($id)
    {
        DB::beginTransaction();
        $commitBool = false;
        $uaccount = $this->userAccountModel->getAccount(['id' => $id]);
        if ($uaccount->is_paid == 0) {
            if ($uaccount->process_type == 0) {//充值
                $uaccount = $this->userAccountModel->delAccount(['id' => $id]);
                if ($uaccount) {
                    $commitBool = true;
                }
            } else {//提现
                $user = $this->usersModel->getUser(['user_id' => $uaccount->user_id]);
                $userData['user_money'] = $user->user_money - $uaccount->amount;
                $userData['frozen_money'] = $user->frozen_money + $uaccount->amount;
                $result_user = $this->usersModel->setUser(['user_id' => $uaccount->user_id], $userData);
                $uaccount = $this->userAccountModel->delAccount(['id' => $id]);
                if ($uaccount && $result_user) {
                    $commitBool = true;
                }
            }
        }
        if ($commitBool) {
            DB::commit();
            return ['code' => 1, 'msg' => '操作成功'];
        } else {
            DB::rollBack();
            return ['code' => 5, 'msg' => '操作失败'];
        }
    }
}