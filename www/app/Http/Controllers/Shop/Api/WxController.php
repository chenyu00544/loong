<?php

namespace App\Http\Controllers\Shop\Api;

use App\Http\Models\Wxapp\WxappServerSessionModel;
use Illuminate\Http\Request;

class WxController extends CommonController
{
    private $wxappServerSessionModel;

    public function __construct(
        WxappServerSessionModel $wxappServerSessionModel
    )
    {
        $this->wxappServerSessionModel = $wxappServerSessionModel;
    }

    public function server(Request $request)
    {
        $re = $this->checkSignature($request->all());
        if ($re) {
            $encryptMsg = $request->all();
            $openid = $encryptMsg['openid'];
            $fromUserName = $encryptMsg['ToUserName'];

            $wxUser = $this->wxappServerSessionModel->getWxappServerSession(['open_id' => $openid]);
            if (!$wxUser) {
                $this->wxappServerSessionModel->addWxappServerSession(['open_id' => $openid, 'update_time' => time(), 'count_msg' => 0, 'create_time' => time()]);
            } else {
                if ($wxUser->update_time > (time() - 48 * 24 * 3600)) {
                    $this->wxappServerSessionModel->setWxappServerSession(['open_id' => $openid], ['update_time' => time(), 'count_msg' => 0]);
                }
            }

            $send = [
                'ToUserName' => $openid,
                'FromUserName' => $fromUserName,
                'CreateTime' => time(),
                'MsgType' => 'transfer_customer_service'
            ];
            return $send;
        } else {
            return 'failed';
        }
    }


    private function checkSignature($data)
    {
        $signature = $data['signature'];
        $timestamp = $data['timestamp'];
        $nonce = $data['nonce'];

        $token = WXAPP_SERVER;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode($tmpArr);
        $tmpStr = sha1($tmpStr);

        if ($tmpStr == $signature) {
            return true;
        } else {
            return false;
        }
    }
}
