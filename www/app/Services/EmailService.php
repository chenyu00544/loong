<?php

namespace App\Services;

use App\Facades\RedisCache;
use PHPMailer\PHPMailer\Exception;
use PHPMailer\PHPMailer\PHPMailer;

class EmailService
{
    private static $mail;
    private static $emailAddress;
    private static $appname;

    public function __construct()
    {
        if (!isset(self::$mail)) {
            self::$mail = new PHPMailer();
            self::$mail->SMTPAuth = true;
            self::$mail->isSMTP();
            $conf = RedisCache::get('smtp_config');
            if ($conf['mail_service'] == 1) {
                self::$mail->SMTPDebug = 0;
            } else {
                self::$mail->SMTPDebug = 1;
            }
            if ($conf['smtp_ssl'] == 1) {
                self::$mail->SMTPSecure = "ssl";
            } else {
                self::$mail->SMTPSecure = "tls";
            }
            self::$mail->CharSet = $conf['mail_charset'];
            self::$mail->Host = $conf['smtp_host'];
            self::$mail->Port = $conf['smtp_port'];
            self::$mail->Username = $conf['smtp_user'];
            self::$mail->Password = $conf['smtp_pass'];
            self::$emailAddress = $conf['smtp_user'];
            $shop_conf = RedisCache::get('shop_config');
            self::$appname = $shop_conf['shop_name'];
        }
        return self::$mail;
    }

    public static function sendEmail($params = [], $msg)
    {
        $req = false;
        if (self::$mail) {
            try {
                self::$mail->setFrom(self::$emailAddress, self::$appname);  //设置发件人
                self::$mail->Subject = $msg['subtitle'];             //邮件标题
                self::$mail->MsgHTML($msg['content']);             // html内容
                foreach ($params as $param) {
                    self:: $mail->addAddress($param['email'], $param['username']); //设置收件人
                }
                $status = self::$mail->send();
                $req = $status;
            } catch (Exception $e) {
                dd($e);
            }
            return $req;
        }
    }
}
