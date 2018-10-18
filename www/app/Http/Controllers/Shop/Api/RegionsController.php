<?php

namespace App\Http\Controllers\Shop\Api;

use App\Facades\Token;
use App\Repositories\RegionsRepository;
use GuzzleHttp\Client;
use Illuminate\Http\Request;

class RegionsController extends CommonController
{
    protected $regionsRepository;

    public function __construct(RegionsRepository $regionsRepository)
    {
        $this->regionsRepository = $regionsRepository;
    }

    public function getCountries(Request $req)
    {
        $data = $this->regionsRepository->getRegions($req->type, $req->parent);
        return $this->ApiReturn($data);
    }

    public function getGeoCoder(Request $req)
    {
        //百度
//        $uri = 'http://api.map.baidu.com/geocoder/v2/?location=';
//        $lat = $req->get('lat');
//        $long = $req->get('long');
//        $param = '&output=json&pois=1&ak=AM6MkzwHwPCyYYTTLKGUUFGQ59h5dB1d';
//        $url = $uri . $lat . ',' . $long . $param;

        //高德
        $uri = 'https://restapi.amap.com/v3/geocode/regeo?location=';
        $lat = $req->get('lat');
        $long = $req->get('long');
        $param = '&key=3633dad5cfb2eaeb8207eab9462331b9&radius=1000&extensions=all&batch=false&roadlevel=0';
        $url = $uri . $long . ',' . $lat . $param;

        $client = new Client();
        try {
            $response = $client->request('POST', $url, [
                'headers' => [
                    'Content-type' => 'application/json',
//                    'Cookie' => 'XDEBUG_SESSION=PHPSTORM',
                    "Accept" => "application/json"]
            ]);
            $content = json_decode($response->getBody()->getContents());
//            $area = mb_substr($content->result->addressComponent->district, 0, -1, "utf-8");
            $area = mb_substr($content->regeocode->addressComponent->district, 0, -1, "utf-8");
            $region = $this->regionsRepository->searchRegion($area);
            if (!$region) {
//                $area = mb_substr($content->result->addressComponent->city, 0, -1, "utf-8");
                $area = mb_substr($content->regeocode->addressComponent->city, 0, -1, "utf-8");
                $region = $this->regionsRepository->searchRegion($area);
            }
            return $this->ApiReturn($region);
        } catch (\Exception $e) {
            echo 'error:  ';
            echo $e->getMessage();
        }
    }

    public function enToken()
    {
        $userId = '123456';
        echo Token::encode();
    }

    public function deToken()
    {
        $userId = '123456';
    }
}
