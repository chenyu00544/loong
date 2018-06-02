<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\BrandRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\shop\BrandModel;

class BrandRepository implements BrandRepositoryInterface
{

    protected $brandModel;

    public function __construct(BrandModel $brandModel)
    {
        $this->brandModel = $brandModel;
    }

    public function getBrands($size = 10)
    {
        return $this->brandModel->getBrands($size);
    }

    public function getBrand($id)
    {
        return $this->brandModel->getBrand($id);
    }

    public function getBrandsAll()
    {
        return $this->brandModel->getBrandsAll();
    }

    public function addBrand($data)
    {
        $fileImg = ['brand_logo', 'index_img', 'brand_bg'];
        $updata = [];
        foreach ($data as $key => $val) {
            if (in_array($key, $fileImg)) {
                $path = FileHandle::upLoadImage($val, $key);
                $updata[$key] = $path ? $path : 0;
            }
            $updata[$key] = $val ? $val : 0;
        }
        return $this->brandModel->addBrand($updata);
    }

    public function upDateBrand($data, $id)
    {
        $fileImg = ['brand_logo', 'index_img', 'brand_bg'];
        $filePath = ['brand_logo_path', 'index_img_path', 'brand_bg_path'];
        $updata = [];
        foreach ($data as $key => $val) {
            if (in_array($key, $fileImg)) {
                if ($val->isValid()) {
                    FileHandle::deleteFile($data[$key . '_path']);
                    $path = FileHandle::upLoadImage($val, $key);
                    $updata[$key] = $path ? $path : 0;
                } else {
                    $updata[$key] = $val ? $val : 0;
                }
            } else {
                if (!in_array($key, $filePath)) {
                    $updata[$key] = $val ? $val : 0;
                }
            }

        }
        $where['id'] = $id;
        return $this->brandModel->upDateBrand($where, $updata);
    }

    public function setBrand($where, $updata)
    {
        return $this->brandModel->upDateBrand($where, $updata);
    }

    public function changBrand($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['id'] = $data['id'];
        $keys = array_keys($data);
        foreach ($keys as $val) {
            switch ($data['type']) {
                case 'order':
                    $updata['sort_order'] = $data['val'];
                    break;
                case 'is_recommend':
                    $updata['is_recommend'] = $data['val'];
                    break;
                case 'is_show':
                    $updata['is_show'] = $data['val'];
                    break;
                default:
                    break;
            }
        }
        $re = $this->brandModel->upDateBrand($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

    public function delete($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['id'] = $id;
        $re = $this->brandModel->deleteBrand($where);
        if ($re) {
            $req['code'] = 1;
            $req['msg'] = '删除成功';
        }
        return $req;
    }

    public function getFirstChar($str)
    {
        if (empty($str)) {
            return '';
        }
        $fchar = ord($str{0});
        if ($fchar >= ord('A') && $fchar <= ord('z')) return strtoupper($str{0});
        $s1 = iconv('UTF-8', 'gb2312', $str);
        $s2 = iconv('gb2312', 'UTF-8', $s1);
        $s = $s2 == $str ? $s1 : $str;
        $asc = ord($s{0}) * 256 + ord($s{1}) - 65536;
        if ($asc >= -20319 && $asc <= -20284) return 'A';
        if ($asc >= -20283 && $asc <= -19776) return 'B';
        if ($asc >= -19775 && $asc <= -19219) return 'C';
        if ($asc >= -19218 && $asc <= -18711) return 'D';
        if ($asc >= -18710 && $asc <= -18527) return 'E';
        if ($asc >= -18526 && $asc <= -18240) return 'F';
        if ($asc >= -18239 && $asc <= -17923) return 'G';
        if ($asc >= -17922 && $asc <= -17418) return 'H';
        if ($asc >= -17417 && $asc <= -16475) return 'J';
        if ($asc >= -16474 && $asc <= -16213) return 'K';
        if ($asc >= -16212 && $asc <= -15641) return 'L';
        if ($asc >= -15640 && $asc <= -15166) return 'M';
        if ($asc >= -15165 && $asc <= -14923) return 'N';
        if ($asc >= -14922 && $asc <= -14915) return 'O';
        if ($asc >= -14914 && $asc <= -14631) return 'P';
        if ($asc >= -14630 && $asc <= -14150) return 'Q';
        if ($asc >= -14149 && $asc <= -14091) return 'R';
        if ($asc >= -14090 && $asc <= -13319) return 'S';
        if ($asc >= -13318 && $asc <= -12839) return 'T';
        if ($asc >= -12838 && $asc <= -12557) return 'W';
        if ($asc >= -12556 && $asc <= -11848) return 'X';
        if ($asc >= -11847 && $asc <= -11056) return 'Y';
        if ($asc >= -11055 && $asc <= -10247) return 'Z';
        return null;
    }

    public function search($data, $bool = false)
    {
        $where = [];
        foreach ($data as $key => $value) {
            switch ($key) {
                case 'letter':
                    if ($value) {
                        $where['brand_first_char'] = $value;
                    }
                    break;
                case 'keywords':
                    $where = [['brand_name', 'like', '%' . $value . '%']];
                    break;
                default:
                    break;
            }
        }
        $bIn = [];
        if($bool){
            for ($i = 0; $i < 10; $i++) {
                $bIn[] = rand(1,100);
            }
            $bIn = array_unique($bIn);
            sort($bIn);
        }
        $re = $this->brandModel->getBrandsBySearch($where, $bIn);
        if ($re) {
            return $re->toArray();
        }
    }

}