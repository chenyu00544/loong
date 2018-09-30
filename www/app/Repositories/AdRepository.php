<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\AdRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\AdModel;
use App\Http\Models\Shop\AdPositionModel;
use App\Http\Models\Shop\AdTypeModel;

class AdRepository implements AdRepositoryInterface
{

    private $adModel;
    private $adPositionModel;
    private $adTypeModel;

    public function __construct(
        AdModel $adModel,
        AdPositionModel $adPositionModel,
        AdTypeModel $adTypeModel
    )
    {
        $this->adModel = $adModel;
        $this->adPositionModel = $adPositionModel;
        $this->adTypeModel = $adTypeModel;
    }

    public function getAdByPage($type, $search)
    {
        $where['ad_position.ad_terminal'] = $type;
        return $this->adModel->getAdByPage($where, $search);
    }

    public function getAds($type, $pid)
    {
        $where['ad_position.ad_terminal'] = $type;
        $where['ad_position.position_id'] = $pid;
        return $this->adModel->getAdByPage($where, []);
    }

    public function getAd($id)
    {
        $where['ad_id'] = $id;
        return $this->adModel->getAd($where);
    }

    public function adChange($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['ad_id'] = $data['id'];
        $updata['enabled'] = $data['val'];
        $re = $this->adModel->setAd($where, $updata);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function addAd($data)
    {
        $type = !empty($data['ad_terminal']) ? $data['ad_terminal'] : 'pc';
        foreach ($data as $key => $value) {
            if ($key == 'start_end_time') {
                $time_arr = explode('～', $value);
                $updata['start_time'] = strtotime($time_arr[0]);
                $updata['end_time'] = strtotime($time_arr[1]);
            } elseif ($key == 'ad_terminal') {
                continue;
            } elseif ($key == 'ad_img') {
                $uri = 'ad' . DIRECTORY_SEPARATOR . $type;
                $imgPath = FileHandle::upLoadImage($value, $uri);
                $updata['ad_code'] = $imgPath;
            } else {
                $updata[$key] = $value;
            }
        }
        return $this->adModel->addAd($updata);
    }

    public function setAd($data, $id)
    {
        $type = !empty($data['ad_terminal']) ? $data['ad_terminal'] : 'pc';
        $where['ad_id'] = $id;
        foreach ($data as $key => $value) {
            if ($key == 'start_end_time') {
                $time_arr = explode('～', $value);
                $updata['start_time'] = strtotime($time_arr[0]);
                $updata['end_time'] = strtotime($time_arr[1]);
            } elseif ($key == 'ad_terminal') {
                continue;
            } elseif ($key == 'ad_img') {
                $uri = 'ad' . DIRECTORY_SEPARATOR . $type;
                $imgPath = FileHandle::upLoadImage($value, $uri);
                $updata['ad_code'] = $imgPath;
            } else {
                $updata[$key] = $value;
            }
        }
        return $this->adModel->setAd($where, $updata);
    }

    public function delAd($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['ad_id'] = $id;
        $ad = $this->adModel->getAd($where);
        $re = $this->adModel->delAd($where);
        if ($re) {
            FileHandle::deleteFile($ad->ad_code);
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getAdPosByPage($type, $search)
    {
        return $this->adPositionModel->getAdPosByPage(['ad_terminal' => $type], $search);
    }

    public function getAdPoses($type)
    {
        $where['ad_terminal'] = $type;
        return $this->adPositionModel->getAdPoses($where);
    }

    public function getAdPos($id)
    {
        $where['position_id'] = $id;
        return $this->adPositionModel->getAdPos($where);
    }

    public function setAdPos($data, $id)
    {
        $where['position_id'] = $id;
        foreach ($data as $key => $value) {
            $data[$key] = trim($value);
        }
        return $this->adPositionModel->setAdPos($where, $data);
    }

    public function addAdPos($data)
    {
        foreach ($data as $key => $value) {
            $data[$key] = trim($value);
        }
        return $this->adPositionModel->addAdPos($data);
    }

    public function delAdPos($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['position_id'] = $id;
        $ads = $this->adModel->getAds($where);
        $bool = true;
        foreach ($ads as $value) {
            $re = $this->adModel->delAd(['ad_id' => $value->ad_id]);
            FileHandle::deleteFile($value->ad_code);
            if (!$re) {
                $bool = false;
            }
        }
        if ($bool) {
            $this->adPositionModel->delAdPos($where);
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }

    public function getAdTypeByPage()
    {
        return $this->adTypeModel->getAdTypeByPage();
    }

    public function getAdTypes()
    {
        return $this->adTypeModel->getAdTypes();
    }

    public function getAdType($id)
    {
        $where['id'] = $id;
        return $this->adTypeModel->getAdType($where);
    }

    public function setAdType($data, $id)
    {
        $where['id'] = $id;
        return $this->adTypeModel->setAdType($where, $data);
    }

    public function addAdType($data)
    {
        return $this->adTypeModel->addAdType($data);
    }

    public function delAdType($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $re = $this->adTypeModel->delAdType($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '操作成功';
        }
        return $req;
    }
}