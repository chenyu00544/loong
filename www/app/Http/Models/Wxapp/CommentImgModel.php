<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;

class CommentImgModel extends Model
{
    protected $table = 'comment_img';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCommentImgs($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function addCommentImgs($data)
    {
        return $this->create($data);
    }
}
