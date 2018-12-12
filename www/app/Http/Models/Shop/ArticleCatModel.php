<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */
namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ArticleCatModel extends Model
{
    protected $table = 'article_cat';
    protected $primaryKey = 'cat_id';
    public $timestamps = false;
    protected $guarded = [];

    public function getArticleCates($id = 0, $column = ['*'])
    {
        return $this->select($column)
            ->where('parent_id', $id)
            ->orderBy('sort_order', 'DESC')
            ->get();
    }

    public function getSubArticleCates($pids = [0], $ids = [])
    {
        $res = $this->select('id')->whereIn('parent_id', $pids)->get();
        $arr = [];
        if ($res->count() > 0) {
            foreach ($res as $re) {
                $arr[] = $re->id;
            }
            $ids = array_merge($ids, $arr);
            return $this->getSubArticleCates($arr, $ids);
        }
        return $ids;
    }

    public function getArticleCate($id)
    {
        return $this->where('cat_id', $id)
            ->first();
    }

    public function getArticleCatesByIn($in, $columns = ['*'])
    {
        return $this->select($columns)
            ->whereIn('id', $in)
            ->orderBy('sort_order', 'DESC')
            ->get();
    }

    public function searchArticleCates($search, $column = ['*'])
    {
        $m = $this->select($column);
        foreach ($search as $key => $value) {
            $m->orWhere($key, 'like', '%' . $value . '%');
        }
        return $m->get();
    }

    public function getArticleParentCate($id, $cates = [])
    {
        $rep = $this->where('id', $id)->first();
        $cates[] = $rep;
        if ($rep->parent_id != 0) {
            return $this->getComParentCate($rep->parent_id, $cates);
        } else {
            return $cates;
        }
    }

    public function setArticleCate($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function upDateCate($data, $id)
    {
        return $this->where('id', $id)
            ->update($data);
    }

    public function getRank($data, $index = 0, $ranks = ['二级', '三级', '四级', '五级', '六级', '七级', '八级', '九级', '十级'])
    {
        if ($data->parent_id == 0) {
            return [$ranks[$index], $index];
        }
        $i = $index + 1;
        $re = $this->getArticleCate($data->parent_id);
        if ($re) {
            return $this->getRank($re, $i);
        } else {
            return [$ranks[$i], $i];
        }
    }

    public function addArticleCate($data)
    {
        return $this->create($data);
    }

    public function deleteArticleCate($where)
    {
        return $this->where($where)
            ->delete();
    }
}
