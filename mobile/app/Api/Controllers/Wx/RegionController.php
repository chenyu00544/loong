<?php

namespace App\Api\Controllers\Wx;

use App\Models\Region;
use Illuminate\Http\Request;
use App\Services\AuthService;
use App\Services\RegionService;
use App\Api\Controllers\Controller;

/**
 * Class RegionController
 * @package App\Api\Controllers\Wx
 */
class RegionController extends Controller
{
    private $authService;
    private $regionService;

    public function __construct(AuthService $authService, RegionService $regionService)
    {
        $this->authService = $authService;
        $this->regionService = $regionService;
    }

    /**
     * 获取下级地区列表
     * @param Request $request
     * @return mixed
     */
    public function regionList(Request $request)
    {
        $this->validate($request, [
            'id' => 'required|integer'
        ]);

        $args = $request->all();
        $list = $this->regionService->regionList($args);

        return $this->apiReturn($list);
    }


    /**
     * 微信地址映射
     * @param Request $request
     * @return mixed
     */
    public function addressMapping(Request $request)
    {
        $args = $request->all();
        $province = trim($args['province']);
        $city = trim($args['city']);
        $area = trim($args['area']);
        $result = [];
        //取得省或直辖市的ID
        if (strpos($province, '市') == true) {
            $province = str_replace('市', '', $province);
        }
        if (strpos($province, '省') == true) {
            $province = str_replace('省', '', $province);
        }
        $province_condition = [
            ['region_type' , 1],
            ['region_name' , $province]
        ];
        $province_id = Region::where($province_condition)->get()->toArray();
        $result['province_id'] = $province_id[0]['region_id'];//省id
        //取得市的ID
        if (strpos($city, '市') == true) {
            $city = str_replace('市', '', $city);
        }
        $city_condition = [
            ['region_type' , 2],
            ['region_name' , $city]
        ];
        $city_id = Region::where($city_condition)->get()->toArray();
        $result['city_id'] = $city_id[0]['region_id'];//市id
        //取得地区ID
        $area_condition = [
            ['region_type' , 3],
            ['region_name' , $area]
        ];
        $area_id = Region::where($area_condition)->get()->toArray();
        $result['area_id'] = $area_id[0]['region_id'];//市id


        return $this->apiReturn($result);
    }
}
