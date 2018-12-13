<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories\Admin;

use App\Contracts\QuestionAnswerRepositoryInterface;
use App\Http\Models\Shop\QuestionAnswerModel;

class QuestionAnswerRepository implements QuestionAnswerRepositoryInterface
{

    private $questionAnswerModel;

    public function __construct(
        QuestionAnswerModel $questionAnswerModel
    )
    {
        $this->questionAnswerModel = $questionAnswerModel;
    }

    public function getQuestionAnswerByPage($search)
    {
        return $this->questionAnswerModel->getQuestionAnswerByPage([], $search);
    }

    public function getQuestionAnswers($id)
    {
        $qas = $this->questionAnswerModel->getQuestionAnswers($id);
        return $qas;
    }

    public function change($data)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        if ($data['type'] == 'status') {
            $where['qa_id'] = $data['id'];
            $update['status'] = $data['val'];
            $re = $this->questionAnswerModel->setQuestionAnswer($where, $update);
        } elseif ($data['type'] == 'allow') {
            $where = $data['ids'];
            $update['status'] = 1;
            $re = $this->questionAnswerModel->setQuestionAnswers($where, $update);
        } elseif ($data['type'] == 'deny') {
            $where = $data['ids'];
            $update['status'] = 0;
            $re = $this->questionAnswerModel->setQuestionAnswers($where, $update);
        }
        if ($re) {
            $req = ['code' => 1, 'msg' => '操作成功'];
        }
        return $req;

    }

    public function setQuestionAnswer($data, $id, $user)
    {
        $where['qa_id'] = $id;
        $updata['user_name'] = $data->get('user_name');
        $updata['email'] = $data->get('email');
        $updata['content'] = $data->get('content');
        $updata['ip_address'] = $data->getClientIp();
        $where['user_id'] = $user->user_id;
        $re = $this->questionAnswerModel->setQuestionAnswer($where, $updata);
        return $re;
    }

    public function addQuestionAnswer($data, $id, $user)
    {
        $qa = $this->questionAnswerModel->getQuestionAnswer(['qa_id' => $id]);
        $updata = [
            'id_value' => $qa->id_value,
            'email' => $user->email,
            'user_name' => $user->user_name,
            'content' => $data->get('content'),
            'add_time' => time(),
            'ip_address' => $data->getClientIp(),
            'status' => 1,
            'parent_id' => $qa->parent_id != 0 ? $qa->parent_id : $id,
            'user_id' => $user->user_id,
            'single_id' => 0,
            'order_id' => $qa->order_id,
            'rec_id' => $qa->rec_id,
            'ru_id' => $qa->ru_id
        ];
        $re = $this->questionAnswerModel->addQuestionAnswer($updata);
        return $re;
    }

    public function delQuestionAnswer($id)
    {
        $req = ['code' => 5, 'msg' => '操作失败'];
        $where['qa_id'] = $id;
        $re = $this->questionAnswerModel->getQuestionAnswer($where);
        if ($re) {
            if ($re->parent_id != 0) {
                $this->questionAnswerModel->delQuestionAnswer($where);
                $req = ['code' => 1, 'msg' => '操作成功'];
            } else {
                $res = $this->questionAnswerModel->getQuestionAnswers($id);
                foreach ($res as $qa) {
                    $pwhere[] = $qa->qa_id;
                }
                $this->questionAnswerModel->delQuestionAnswers($pwhere);
            }
        }
        return $req;
    }
}