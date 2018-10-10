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

class QuestionAnswerModel extends Model
{
    protected $table = 'question_answer';
    protected $primaryKey = 'qa_id';
    public $timestamps = false;
    protected $guarded = [];

    public function user()
    {
        return $this->hasOne('App\Http\Models\Shop\UsersModel', 'user_id', 'user_id');
    }

    public function getQuestionAnswerByPage($where = [], $search = [], $column = ['*'], $size = 15)
    {
        $m = $this->select($column)
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
        return $m->orderBy('qa_id', 'DESC')
            ->paginate($size);
    }

    public function getQuestionAnswers($id, $column = ['*'])
    {
        return $this->select($column)
            ->orWhere(['qa_id' => $id])
            ->orWhere(['parent_id' => $id])
            ->orderBy('qa_id', 'DESC')
            ->get();
    }

    public function getQuestionAnswer($where, $column = ['*'])
    {
        return $this->select($column)
            ->where($where)
            ->first();
    }

    public function setQuestionAnswer($where, $data)
    {
        return $this->where($where)
            ->update($data);
    }

    public function setQuestionAnswers($where, $data)
    {
        return $this->whereIn('qa_id', $where)
            ->update($data);
    }

    public function addQuestionAnswer($data)
    {
        return $this->create($data);
    }

    public function delQuestionAnswer($where)
    {
        return $this->where($where)->delete();
    }

    public function delQuestionAnswers($where)
    {
        return $this->whereIn('qa_id', $where)->delete();
    }
}
