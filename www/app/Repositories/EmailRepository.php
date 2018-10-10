<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\EmailRepositoryInterface;
use App\Facades\RedisCache;
use App\Http\Models\Shop\EmailConfigureModel;
use PHPMailer\PHPMailer\PHPMailer;

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

    public function testSendMail()
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $mail = new PHPMailer();
        try {
            $mail->isSMTP();
            $mail->SMTPDebug = 1;
            $mail->CharSet = "UTF-8";
            $mail->SMTPAuth = true;
            $mail->SMTPSecure = "ssl";
            $mail->Host = "smtp.126.com";
            $mail->Port = 25;
            $mail->Username = "vcvbuy@126.com";
            $mail->Password = "vcvbuy00544";
            $mail->setFrom("vcvbuy@126.com", "柠檬VC");
            $mail->Subject = "Test";
            $mail->MsgHTML("Thisisatest");//
            $mail->addAddress("chenyu00544@163.com", "chenyu");
//            $mail->addAddress("xxxxx_1@163.com", "leon");
            $mail->send();
            $req = ['code' => 1, 'msg' => '操作成功'];
        } catch (phpmailerException $e) {
            dd($e);
        } catch (Exception $e) {
            dd($e);
        }
        return $req;
    }
}