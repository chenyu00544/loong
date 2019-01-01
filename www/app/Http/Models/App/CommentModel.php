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

    public function reply()
    {
        return $this->hasMany('App\Http\Models\App\CommentModel', 'parent_id', 'comment_id');
    }

    public function getComments($id, $column = ['*'], $page = 1, $size = 15)
    {
        return $this->select($column)
            ->with(['commentImg'])
            ->with(['user' => function ($query) {
                $query->select(['user_id', 'logo', 'nick_name']);
            }])
            ->with(['reply'=>function($query){
                $query->select(['comment_id', 'comment_type', 'id_value', 'user_name', 'content', 'order_id', 'user_id', 'parent_id', 'status',
                    'comment_rank', 'comment_server', 'comment_delivery', 'add_time'])
                    ->with(['commentImg'])
                    ->with(['user' => function ($query) {
                        $query->select(['user_id', 'logo', 'nick_name']);
                    }])
                    ->orderBy('add_time', 'DESC');
            }])
            ->where(['id_value' => $id, 'parent_id' => 0, 'status' => 1])
            ->orderBy('add_time', 'DESC')
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function getCommentsByLabel($id, $label_id, $column = ['*'], $page = 1, $size = 15)
    {
        return $this->select($column)
            ->join('comment_ext', 'comment_ext.comment_id', '=', 'comment.comment_id')
            ->with(['commentImg'])
            ->with(['user' => function ($query) {
                $query->select(['user_id', 'logo', 'nick_name']);
            }])
            ->with(['reply'=>function($query){
                $query->select(['comment_id', 'comment_type', 'id_value', 'user_name', 'content', 'order_id', 'user_id', 'parent_id', 'status',
                    'comment_rank', 'comment_server', 'comment_delivery', 'add_time'])
                    ->with(['commentImg'])
                    ->with(['user' => function ($query) {
                        $query->select(['user_id', 'logo', 'nick_name']);
                    }])
                    ->orderBy('add_time', 'DESC');
            }])
            ->where(['comment_ext.id_value' => $id, 'parent_id' => 0, 'status' => 1, 'comment_ext.label_id' => $label_id])
            ->orderBy('comment.add_time', 'DESC')
            ->offset(($page - 1) * $size)
            ->limit($size)
            ->get();
    }

    public function getCommentsByOrder($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function getComment($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->orderBy('comment_id', 'DESC')
            ->get();
    }

    public function setComment($where, $data)
    {
        return $this->where($where)
            ->update($data);
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
