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

class QuestionAnswerModel extends Model
{
    protected $table = 'question_answer';
    protected $primaryKey = 'qa_id';
    public $timestamps = false;
    protected $guarded = [];

    public function user()
    {
        return $this->hasOne('App\Http\Models\Wxapp\UsersModel', 'user_id', 'user_id');
    }
}
