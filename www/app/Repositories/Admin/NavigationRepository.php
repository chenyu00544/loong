<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\NavigationRepositoryInterface;
use App\Http\Models\Shop\NavigationModel;

class NavigationRepository implements NavigationRepositoryInterface
{

    protected $navigationModel;

    public function __construct(NavigationModel $navigationModel)
    {
        $this->navigationModel = $navigationModel;
    }

    public function getNavs($size = 10)
    {
        return $this->navigationModel->getNavs($size);
    }

    public function getNav($id)
    {
        return $this->navigationModel->getNav($id);
    }

    public function getNavsMenulist()
    {
        return [
            ['title' => '请选择', 'value' => '0'],
            ['title' => '商品分类', 'value' => '1'],
            ['title' => '查看购物车', 'value' => '2'],
            ['title' => '品牌专区', 'value' => '3'],
            ['title' => '团购商品', 'value' => '4'],
            ['title' => 'CMS频道', 'value' => '5'],
            ['title' => '夺宝奇兵', 'value' => '6'],
            ['title' => '用户中心', 'value' => '7'],
            ['title' => '批发市场', 'value' => '8'],
            ['title' => '优惠活动', 'value' => '9'],
            ['title' => '拍卖活动', 'value' => '10'],
            ['title' => '超值礼包', 'value' => '11'],
            ['title' => '积分商城', 'value' => '12'],
            ['title' => '提货中心', 'value' => '13'],
            ['title' => '众筹商品', 'value' => '14'],
            ['title' => '优惠券', 'value' => '15'],
            ['title' => '店铺街', 'value' => '16'],
            ['title' => '浏览历史', 'value' => '17'],
            ['title' => '意见反馈', 'value' => '18'],
            ['title' => '商家入驻', 'value' => '19'],
            ['title' => '-', 'value' => '20'],
        ];
    }

    public function addNav($data)
    {
        if($data['ctype'] != 1){
            $data['cid'] = 0;
        }
        return $this->navigationModel->addNav($data);
    }

    public function upDateNav($data, $id)
    {
        if($data['ctype'] != 1){
            $data['cid'] = 0;
        }
        return $this->navigationModel->upDateNav($data, $id);
    }

    public function showOrOpenView($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['id'] = $data['id'];
        switch ($data['type']) {
            case 'ifshow':
                $updata['ifshow'] = $data['val'];
                break;
            case 'opennew':
                $updata['opennew'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->navigationModel->setNav($updata, $where);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

    public function changeOrder($data)
    {

        if ($data['order'] == '') {
            return ['code' => 5, 'msg' => '修改失败'];
        }
        $updata['vieworder'] = $data['order'];
        $where['id'] = $data['id'];
        $re = $this->navigationModel->setNav($updata, $where);
        if ($re) {
            return ['code' => 1, 'msg' => '修改成功'];
        } else {
            return ['code' => 5, 'msg' => '修改失败'];
        }
    }

    public function delete($id)
    {
        $where['id'] = $id;
        $re = $this->navigationModel->deleteNav($where);
        if ($re) {
            return ['code' => 1, 'msg' => '删除成功'];
        } else {
            return ['code' => 5, 'msg' => '删除失败'];
        }
    }

}