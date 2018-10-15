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

class CommentLabelModel extends Model
{
    protected $table = 'comment_label';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getCommentLabels($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->get();
    }

    public function getCommentLabel($where = [], $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setCommentLabel($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function addCommentLabel($data)
    {
        return $this->create($data);
    }

    public function delCommentLabel($where)
    {
        return $this->where($where)->delete();
    }
}
