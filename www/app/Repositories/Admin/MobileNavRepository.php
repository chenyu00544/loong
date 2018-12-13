<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\MobileNavRepositoryInterface;
use App\Facades\FileHandle;
use App\Http\Models\Shop\MobileNavModel;

class MobileNavRepository implements MobileNavRepositoryInterface
{

    private $mobileNavModel;

    public function __construct(
        MobileNavModel $mobileNavModel
    )
    {
        $this->mobileNavModel = $mobileNavModel;
    }

    public function getMobileNavByPage()
    {
        return $this->mobileNavModel->getMobileNavByPage([]);
    }

    public function getMobileNav($id)
    {
        $where['id'] = $id;
        return $this->mobileNavModel->getMobileNav($where);
    }

    public function setMobileNav($data, $id)
    {
        $where['id'] = $id;
        $updata = [];
        foreach ($data as $key => $value){
            switch ($key){
                case 'ctype':
                    if($value == 1){
                        $updata['cid'] = $data['cid'];
                    }
                    $updata[$key] = $value;
                    break;
                case 'pic':
                    if($value){
                        $path = 'nav';
                        $uri = FileHandle::upLoadImage($value, $path);
                        $updata[$key] = $uri;
                    }
                    break;
                case 'pic_bak':
                    if(!empty($data['pic'])){
                        FileHandle::deleteFile($value);
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        return $this->mobileNavModel->setMobileNav($where, $updata);
    }

    public function addMobileNav($data)
    {
        $updata = [];
        foreach ($data as $key => $value){
            switch ($key){
                case 'ctype':
                    if($value == 1){
                        $updata['cid'] = $data['cid'];
                    }
                    $updata[$key] = $value;
                    break;
                case 'pic':
                    if($value){
                        $path = 'nav';
                        $uri = FileHandle::upLoadImage($value, $path);
                        $updata[$key] = $uri;
                    }
                    break;
                default:
                    $updata[$key] = $value;
                    break;
            }
        }
        return $this->mobileNavModel->addMobileNav($updata);
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $data['id'];
        $updata = [];
        switch ($data['type']) {
            case 'ifshow':
                $updata['ifshow'] = $data['val'];
                break;
            case 'opennew':
                $updata['opennew'] = $data['val'];
                break;
            case 'order':
                $updata['vieworder'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->mobileNavModel->setMobileNav($where, $updata);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function delMobileNav($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['id'] = $id;
        $res = $this->mobileNavModel->getMobileNav($where);
        FileHandle::deleteFile($res->pic);
        $re = $this->mobileNavModel->delMobileNav($where);
        if (!empty($re)) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;
    }

    public function getNavsMenulist()
    {
        return [
            ['title' => '请选择', 'value' => '0'],
            ['title' => '商品分类', 'value' => '1'],
            ['title' => '购物车', 'value' => '2'],
            ['title' => '店铺街', 'value' => '3'],
            ['title' => '品牌街', 'value' => '4'],
            ['title' => '促销活动', 'value' => '5'],
            ['title' => '最新团购', 'value' => '6'],
            ['title' => '积分换购', 'value' => '7'],
            ['title' => '微社区', 'value' => '8'],
            ['title' => '微众筹', 'value' => '9'],
            ['title' => '拍卖活动', 'value' => '10'],
            ['title' => '超值礼包', 'value' => '11'],
            ['title' => '专题汇', 'value' => '12'],
            ['title' => '新品预售', 'value' => '13'],
            ['title' => '内容文章', 'value' => '14'],
            ['title' => '会员中心', 'value' => '15'],
        ];
    }
}