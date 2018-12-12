<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\ArticleCateRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Shop\ArticleCatModel;

class ArticleCateRepository implements ArticleCateRepositoryInterface
{

    protected $articleCatModel;

    public function __construct(ArticleCatModel $articleCatModel)
    {
        $this->articleCatModel = $articleCatModel;
    }

    public function getArticleCates($id = 0)
    {
        return $this->articleCatModel->getArticleCates($id);
    }

    public function getArticleCate($id)
    {
        $re = $this->articleCatModel->getArticleCate($id);
        return $re;
    }

    public function addCate($data)
    {
        $updata = [];
        foreach ($data as $key => $val) {
            if($key == "touch_icon_img"){
                $path = 'category';
                $updata['touch_icon'] = FileHandle::upLoadImage($data['touch_icon_img'], $path);
            }else{
                $updata[$key] = $val ? $val : 0;
            }
        }
        RedisCache::del('category');
        return $this->articleCatModel->addArticleCate($updata);
    }

    public function upDateCate($data, $id)
    {
        if (!empty($data['touch_icon_img'])) {
            $path = 'category';
            if (!empty($data['touch_icon'])) {
                FileHandle::deleteFile($data['touch_icon']);
            }
            $data['touch_icon'] = FileHandle::upLoadImage($data['touch_icon_img'], $path);
            unset($data['touch_icon_img']);
        }
        RedisCache::del('category');
        return $this->articleCatModel->upDateCate($data, $id);
    }

    public function getParentCate($id, $parentCates = [])
    {
        $PCates = $parentCates;
        $re = $this->articleCatModel->getArticleCate($id);
        if ($re) {
            $PCates[] = $re;
        }

        if ($re && $re->parent_id != 0) {
            return $this->getParentCate($re->parent_id, $PCates);
        } else {
            krsort($PCates);
            return $PCates;
        }
    }

    public function getParentCateBySelect($id)
    {
        $c_list = $this->getParentCate($id);
        $cate = [];
        foreach ($c_list as $value) {
            $re = $this->articleCatModel->getArticleCates($value->parent_id);
            foreach ($re as $k => $val) {
                if ($val->id == $value->id) {
                    $re[$k]->select = 1;
                } else {
                    $re[$k]->select = 0;
                }
            }
            $cate[] = $re;
        }
        return $cate;
    }

    public function changOrder($data)
    {
        if ($data['order'] == '') {
            return ['code' => 5, 'msg' => '修改失败'];
        }
        $updata['sort_order'] = $data['order'];
        $where['id'] = $data['id'];
        $re = $this->articleCatModel->setArticleCate($updata, $where);
        if ($re) {
            return ['code' => 1, 'msg' => '修改成功'];
        } else {
            return ['code' => 5, 'msg' => '修改失败'];
        }
    }

    public function delete($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['id'] = $id;
        $res = $this->articleCatModel->getArticleCates($id);
        if ($res->toArray()) {
            $req['msg'] = '存在子类删除失败';
        } else {
            $re = $this->articleCatModel->deleteArticleCate($where);
            RedisCache::del('category');
            if ($re) {
                $req['code'] = 1;
                $req['msg'] = '删除成功';
            }
        }
        return $req;
    }

    public function getRank($id)
    {
        $re = $this->articleCatModel->getArticleCate($id);
        return $this->articleCatModel->getRank($re);
    }

    public function setComCate($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['id'] = $data['id'];

        switch ($data['type']) {
            case 'order':
                $updata['sort_order'] = $data['val'];
                break;
            case 'grade':
                $updata['grade'] = $data['val'];
                break;
            case 'measure_unit':
                $updata['measure_unit'] = $data['val'];
                break;
            case 'commission_rate':
                $updata['commission_rate'] = $data['val'];
                break;
            case 'isshow':
                $updata['is_show'] = $data['val'];
                break;
            case 'shownav':
                $updata['show_in_nav'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->articleCatModel->setArticleCate($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }



}