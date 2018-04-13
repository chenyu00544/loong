<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\ComCateRepositoryInterface;
use App\Http\Models\shop\CategoryModel;


class ComCateRepository implements ComCateRepositoryInterface
{

    protected $categoryModel;

    public function __construct(CategoryModel $categoryModel)
    {
        $this->categoryModel = $categoryModel;
    }

    public function getComCatesForFirst()
    {
        return $this->categoryModel->getComCatesForFirst();
    }

    public function getNav($id)
    {
        return $this->categoryModel->getNav($id);
    }

    public function getNavsTop()
    {
        return $this->categoryModel->getNavsTop();
    }

    public function addNav($data)
    {
        return $this->categoryModel->addNav($data);
    }

    public function upDateNav($data, $id)
    {
        return $this->categoryModel->upDateNav($data, $id);
    }

    public function showOrOpenView($data)
    {
        if($data['type'] == 'ifshow'){
            $updata['ifshow'] = $data['isshow'];
            $where['id'] = $data['id'];
            return $this->categoryModel->setNav($updata,$where);
        }else if($data['type'] == 'opennew'){
            $updata['opennew'] = $data['isopen'];
            $where['id'] = $data['id'];
            return $this->categoryModel->setNav($updata,$where);
        }
    }

    public function changOrder($data)
    {

        if($data['order'] == ''){
            return ['code'=>5,'msg'=>'修改失败'];
        }
        $updata['vieworder'] = $data['order'];
        $where['id'] = $data['id'];
        $re = $this->categoryModel->setNav($updata,$where);
        if($re){
            return ['code'=>1,'msg'=>'修改成功'];
        }else{
            return ['code'=>5,'msg'=>'修改失败'];
        }
    }

    public function delete($id)
    {
        $where['id'] = $id;
        $re = $this->categoryModel->deleteNav($where);
        if($re){
            return ['code'=>1,'msg'=>'删除成功'];
        }else{
            return ['code'=>5,'msg'=>'删除失败'];
        }
    }

}