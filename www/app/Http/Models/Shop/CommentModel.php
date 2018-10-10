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

class CommentModel extends Model
{
    protected $table = 'comment';
    protected $primaryKey = 'comment_id';
    public $timestamps = false;
    protected $guarded = [];

    public function goods()
    {
        return $this->hasOne('App\Http\Models\Shop\GoodsModel', 'goods_id', 'id_value');
    }

    public function article()
    {
        return $this->hasOne('App\Http\Models\Shop\ArticleModel', 'article_id', 'id_value');
    }

    public function shop()
    {
        return $this->hasOne('App\Http\Models\Shop\SellerShopInfoModel', 'ru_id', 'ru_id');
    }

    public function commentImg()
    {
        return $this->hasMany('App\Http\Models\Shop\CommentImgModel', 'comment_id', 'comment_id');
    }

    public function getCommentByPage($where = [], $search = [], $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
            ->with(['goods'])
            ->with(['article'])
            ->with(['shop'])
            ->with(['commentImg'])
            ->where($where);
        if (!empty($search['seller'])) {
            if ($search['seller'] == 'selfsale') {
                $m->where([['ru_id', '=', 0]]);
            } else {
                $m->where([['ru_id', '<>', 0]]);
            }
        }
        if (!empty($search['keywords'])) {
            $m->where('content', 'like', '%' . $search['keywords'] . '%');
        }
        return $m->orderBy('comment_id', 'desc')
            ->paginate($size);
    }

    public function getComments($id, $column = ['*'])
    {
        return $this->select($column)
            ->with(['goods'])
            ->with(['article'])
            ->with(['shop'])
            ->with(['commentImg'])
            ->orWhere(['comment_id' => $id])
            ->orWhere(['parent_id' => $id])
            ->orderBy('comment_id', 'DESC')
            ->get();
    }

    public function getComment($id, $column = ['*'])
    {
        return $this->select($column)
            ->with(['goods'])
            ->with(['article'])
            ->with(['shop'])
            ->with(['commentImg'])
            ->where(['comment_id' => $id])
            ->first();
    }

    public function setComment($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function setComments($where, $data)
    {
        return $this->whereIn('comment_id', $where)
            ->update($data);
    }

    public function addComment($data)
    {
        return $this->create($data);
    }

    public function delComment($where)
    {
        return $this->where($where)->delete();
    }
}
