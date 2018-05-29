<?php
//////////////////这个文件是自己创建的，作用为： 小程序模版消息主动营销 
//暂时没用。 主动营销为 https://www.missmall.com/mobile/index.php?r=wechat/wxapp/xx
//mobile\app\Modules\Wechat\Controllers

namespace App\Api\Controllers\Wx;

use Illuminate\Http\Request;

use App\Services\AuthService;
use App\Services\PaymentService;
use App\Api\Controllers\Controller;

class TemplateController extends Controller
{
    private $paymentService;
    private $authService;

    public function __construct(PaymentService $paymentService, AuthService $authService)
    {
        $this->paymentService = $paymentService;
        $this->authService = $authService;
    }

   
   
}
