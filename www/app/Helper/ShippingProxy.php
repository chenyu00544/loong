<?php

namespace App\Helper;

/**
 * Class ShippingProxy
 * @package App\Http\Proxy
 */
class ShippingProxy
{
    /**
     * @var Http
     */
    private $http;

    /**
     * @var string
     */
//    private $queryExpressUrl = 'http://m.kuaidi100.com/query?type=%s&postid=%s&temp=%s&phone=';
    private $queryExpressUrl = 'http://www.kuaidi100.com/query?type=%s&postid=%s&temp=%s&phone=';
    /**
     * ShippingProxy constructor.
     */
    public function __construct()
    {
        $this->http = new Http();
    }

    /**
     * @param string $com
     * @param string $num
     * @return mixed
     */
    public function getExpress($com = '', $num = '')
    {
        $rand = '0.'.rand(1000000,9999999).rand(10000,99999).rand(10000,99999);
        $url = sprintf($this->queryExpressUrl, $com, $num, $rand);
        $respose = $this->http->doGet($url, 5, $this->defaultHeader());
        $result = json_decode($respose, true);
        if ($result['message'] === 'ok') {
            $result['error'] = 0;
            return $result;
        } else {
            return false;
        }
    }

    /**
     * 默认HTTP头
     *
     * Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36
     * Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1
     *
     * @return string
     */
    private function defaultHeader()
    {
        //User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36
        $header = "User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.%d Safari/537.%d\r\n";
        $header .= "Accept: application/json, text/javascript, */*; q=0.01\r\n";
        $header .= "Accept-language: zh-cn,zh;q=0.9\r\n";
        $header .= "Referer: http://www.kuaidi100.com/?from=openv\r\n";
        $header .= "X-Requested-With: XMLHttpRequest\r\n";
        $header .= "Host: m.kuaidi100.com\r\n";
        $header .= "Connection: keep-alive\r\n";
        $header .= "Accept-Encoding: gzip, deflate\r\n";
        $header .= "Cookie: WWWID=WWW1B20AE3E5A243AF9AC6A87635726D528; indexCompanyCode=youzhengguonei; Hm_lvt_22ea01af58ba2be0fec7c11b25e88e6c=1556158028,1556158418,1556160068; Hm_lpvt_22ea01af58ba2be0fec7c11b25e88e6c=1556160068\r\n";
//        $header = sprintf($header, time(), time() + rand(1000, 9999));
        $header = sprintf($header, rand(10,99), rand(10,99));
        return $header;
    }

}