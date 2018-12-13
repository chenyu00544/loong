<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

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

    public function addArticleCate($data)
    {
        $updata = [];
        foreach ($data as $key => $val) {
            $updata[$key] = $val ? $val : 0;
        }
        return $this->articleCatModel->addArticleCate($updata);
    }

    public function upArticleCate($data, $id)
    {
        $where['cat_id'] = $id;
        return $this->articleCatModel->setArticleCate($where, $data);
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

    public function delArticleCate($id)
    {
        $req = ['code' => 5, 'msg' => '删除失败'];
        $where['cat_id'] = $id;
        $res = $this->articleCatModel->getArticleCates($id);
        if ($res->toArray()) {
            $req['msg'] = '存在子类删除失败';
        } else {
            $re = $this->articleCatModel->deleteArticleCate($where);
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

    public function setArticleCate($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['cat_id'] = $data['id'];

        switch ($data['type']) {
            case 'order':
                $updata['sort_order'] = $data['val'];
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