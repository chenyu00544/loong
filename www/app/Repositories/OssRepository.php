<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\OssRepositoryInterface;
use App\Facades\RedisCache;
use App\Http\Models\Shop\AliossConfigureModel;

class OssRepository implements OssRepositoryInterface
{

    private $aliossConfigureModel;

    public function __construct(
        AliossConfigureModel $aliossConfigureModel
    )
    {
        $this->aliossConfigureModel = $aliossConfigureModel;
    }

    public function getAliossByPage($request)
    {
        $osses = $this->aliossConfigureModel->getAliossByPage();
        foreach ($osses as $oss){

            $regional = substr($oss->regional, 0, 2);
            $http = $request->server()['REQUEST_SCHEME'].'://';
            if($regional == 'us' || $regional == 'ap'){
                $outside_site = $http . $oss->bucket . ".oss-" .$oss->regional. ".aliyuncs.com";
                $inside_site = $http . $oss->bucket . ".oss-" .$oss->regional. "-internal.aliyuncs.com";
            }else{
                $outside_site = $http . $oss->bucket . ".oss-cn-" .$oss->regional. ".aliyuncs.com";
                $inside_site = $http . $oss->bucket . ".oss-cn-" .$oss->regional. "-internal.aliyuncs.com";
            }
            $oss->outside_site = $outside_site;
            $oss->inside_site = $inside_site;
            if($oss->regional == 'shanghai'){
                $oss->regional_name = '中国（上海）';
            }elseif($oss->regional == 'hangzhou'){
                $oss->regional_name = '中国（杭州）';
            }elseif($oss->regional == 'shenzhen'){
                $oss->regional_name = '中国（深圳）';
            }elseif($oss->regional == 'beijing'){
                $oss->regional_name = '中国（北京）';
            }elseif($oss->regional == 'qingdao'){
                $oss->regional_name = '中国（青岛）';
            }elseif($oss->regional == 'hongkong'){
                $oss->regional_name = '中国（香港）';
            }elseif($oss->regional == 'us-west-1'){
                $oss->regional_name = '美国(加利福尼亚州)';
            }elseif($oss->regional == 'ap-southeast-1'){
                $oss->regional_name = '亚洲(新加坡)';
            }
        }
        return $osses;
    }

    public function getAlioss($id)
    {
        $where['id'] = $id;
        return $this->aliossConfigureModel->getAlioss($where);
    }

    public function setAlioss($data, $id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $updata = $data;
        $this->aliossConfigureModel->setAlioss(['is_use'=>'1'], ['is_use'=>'0']);
        $re = $this->aliossConfigureModel->setAlioss($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        $oss = $this->getAlioss($id);
        if($oss){
            $oss = $oss->toArray();
            if($oss['is_use'] == 1){
                RedisCache::set('oss_config', $oss);
            }
        }
        return $req;
    }

    public function addAlioss($data)
    {
        return $this->aliossConfigureModel->addAlioss($data);
    }

    public function delAlioss($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->aliossConfigureModel->delAlioss($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}