<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\EmailRepositoryInterface;
use App\Facades\Email;
use App\Facades\RedisCache;
use App\Http\Models\Shop\EmailConfigureModel;

class EmailRepository implements EmailRepositoryInterface
{

    private $emailConfigureModel;

    public function __construct(
        EmailConfigureModel $emailConfigureModel
    )
    {
        $this->emailConfigureModel = $emailConfigureModel;
    }

    public function getEmailConfig()
    {
        return $this->emailConfigureModel->getEmailConfigure();
    }

    public function setEmailConfig($data, $id)
    {
        $update['mail_service'] = $data['mail_service'];
        $update['smtp_ssl'] = $data['smtp_ssl'];
        $update['smtp_host'] = $data['smtp_host'];
        $update['smtp_port'] = $data['smtp_port'];
        $update['smtp_user'] = $data['smtp_user'];
        $update['smtp_pass'] = $data['smtp_pass'];
        $update['smtp_mail'] = $data['smtp_mail'];
        $update['mail_charset'] = $data['mail_charset'];
        if ($id != 0) {
            $where['id'] = $id;
            $re = $this->emailConfigureModel->setEmailConfigure($where, $update);
        } else {
            $re = $this->emailConfigureModel->addEmailConfigure($update);
        }
        RedisCache::set('smtp_config', $update);
        return $re;
    }

    public function sendMail($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $params = [];
        foreach ($data['data'] as $value){
            $params[] = ['email' => $value['email'], 'username' => $value['user_name']];
        }
        $msg['content'] = $data['content'];
        $msg['subtitle'] = $data['subtitle'];
        $re = Email::sendEmail($params, $msg);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function testSendMail($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $params[] = ['email' => $data['email'], 'username' => $data['username']];
        $msg['content'] = '<div></div><p><img alt="undefined" src="https://cbu01.alicdn.com/img/ibank/2018/333/257/9274752333_573552641.jpg"/><br/></p>';
        $msg['subtitle'] = '这个是一个测试邮件';
        $re = Email::sendEmail($params, $msg);
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }
}