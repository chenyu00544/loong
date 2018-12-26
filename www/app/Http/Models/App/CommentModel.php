<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\App;

use Illuminate\Database\Eloquent\Model;

class CommentModel extends Model
{
    protected $table = 'comment';
    protected $primaryKey = 'comment_id';
    public $timestamps = false;
    protected $guarded = [];

    public function commentImg()
    {
        return $this->hasMany('App\Http\Models\App\CommentImgModel', 'comment_id', 'comment_id');
    }

    public function user()
    {
        return $this->hasOne('App\Http\Models\App\UsersModel', 'user_id', 'user_id');
    }

    public function getComments($id, $column = ['*'])
    {
        return $this->select($column)
            ->with(['commentImg'])
            ->with(['user'])
            ->where(['id_value' => $id, 'parent_id' => 0])
            ->orderBy('comment_id', 'DESC')
            ->limit(10)
            ->get();
    }

    public function getComment($id, $column = ['*'])
    {
        return $this->select($column)
        ->with(['commentImg'])
        ->orWhere(['comment_id' => $id])
        ->orWhere(['parent_id' => $id])
        ->orderBy('comment_id', 'DESC')
        ->get();
    }

    public function addComment($data)
    {
        return $this->create($data);
    }

    public function countComment($where)
    {
        return $this->where($where)
            ->count();
    }
}
