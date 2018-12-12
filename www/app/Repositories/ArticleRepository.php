<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\ArticleRepositoryInterface;
use App\Facades\FileHandle;
use App\Facades\RedisCache;
use App\Http\Models\Shop\ArticleExtendModel;
use App\Http\Models\Shop\ArticleGoodsModel;
use App\Http\Models\Shop\ArticleModel;

class ArticleRepository implements ArticleRepositoryInterface
{

    protected $articleModel;
    protected $articleExtendModel;
    protected $articleGoodsModel;

    public function __construct(
        ArticleModel $articleModel,
        ArticleExtendModel $articleExtendModel,
        ArticleGoodsModel $articleGoodsModel
    )
    {
        $this->articleModel = $articleModel;
        $this->articleExtendModel = $articleExtendModel;
        $this->articleGoodsModel = $articleGoodsModel;
    }

    public function getArticlesByPage($id = 0)
    {
        $where = [];
        if ($id > 0) {
            $where['cat_id'] = $id;
        }
        return $this->articleModel->getArticlesByPage($where);
    }

    public function getArticle($id)
    {
        $where['article_id'] = $id;
        $re = $this->articleModel->getArticle($where);
        $re->file_url_oss = FileHandle::getImgByOssUrl($re->file_url);
        return $re;
    }

    public function setArticle($data, $id)
    {
        $where['article_id'] = $id;
        dd($data);

    }

    public function addArticle($data)
    {
        $updata = [];
        $goods_ids = [];
        foreach ($data as $key => $value) {
            if ($key == 'parent_id') {
                $updata['cat_id'] = $value;
            } elseif ($key == 'link_url') {
                $updata['link'] = $value;
            } elseif ($key == 'search') {
                $goods_ids = $value;
            } elseif ($key == 'textfile') {
                $path = 'article';
                $updata['file_url'] = FileHandle::upLoadImage($value, $path);
            } else {
                $updata[$key] = $value;
            }
        }
        $re = $this->articleModel->addArticle($updata);
        $article_ext['article_id'] = $re->article_id;
        $this->articleExtendModel->addArticleExtend($article_ext);
        foreach ($goods_ids as $goods_id) {
            $article_ext['goods_id'] = $goods_id;
            $this->articleGoodsModel->addArticleGoods($article_ext);
        }
        return $re;
    }

    public function deleteArticle($id)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];
        $re = $this->articleModel->delArticle(['article_id' => $id]);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }
        return $rep;
    }

    public function changeArticle($data)
    {
        $rep = ['code' => 5, 'msg' => '修改失败'];

        $where['article_id'] = $data['id'];

        switch ($data['type']) {
            case 'is_open':
                $updata['is_open'] = $data['val'];
                break;
            default:
                break;
        }
        $re = $this->articleModel->setArticle($where, $updata);
        if ($re) {
            $rep = ['code' => 1, 'msg' => '修改成功'];
        }

        return $rep;
    }

    public function changeGoods($data)
    {
        if ($data['type'] == 'add') {
            if (!empty($data['ids'])) {
                foreach ($data['ids'] as $id) {
                    $updata['article_id'] = $data['article_id'];
                    $updata['goods_id'] = $id;
                    $this->articleGoodsModel->addArticleGoods($updata);
                }
            }
        } elseif ($data['type'] == 'remove') {
            if (!empty($data['ids'])) {
                $wherein = $data['ids'];
                $this->articleGoodsModel->removeArticleGoods($wherein, $data['article_id']);
            }
        }
    }
}