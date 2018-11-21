<?php


class ProblemAction extends UserAction
{

    public function _initialize()
    {

        parent::_initialize();

    }

    public function index()
    {
        $ProblemM = M('Problem');

        $count = $ProblemM->count();
        $page = new Page($count, 15);
        $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');
        $page_show = $page->show();
        $problems = $ProblemM->order('id DESC')->limit($page->firstRow . ',' . $page->listRows)->select();

        $answer = M('Answer');
        foreach ($problems as $k => $problem) {
            $answers = $answer->where('pid=' . $problem['id'])->select();
            if (count($answers) < 5) {
                $c = 5 - count($answers);
                for ($i = 0; $i < $c; $i++) {
                    $answers[] = ['id' => 1];
                }
            } else {
                if (count($answers) > 5) {
                    unset($answers[count($answers) - 1]);
                }
            }
            $problems[$k]['answers'] = $answers;
        }
        $this->assign('count', $count);
        $this->assign('lists', $problems);
        $this->assign('page', $page_show);
        $this->display();
    }

    public function set()
    {

        $problem_conf = M('Problem_conf');

        $conf = $problem_conf->where(['id' => 1])->find();

        if (IS_POST) {
            if ($problem_conf->create()) {
                if (empty($conf)) {
                    $re = $problem_conf->add($_POST);
                    $this->success('添加成功', U('Problem/index', array('token' => $this->IIIIIIIIlIlI)));
                } else {
                    $re = $problem_conf->where('id=' . $_POST['id'])->save($_POST);
                    $this->success('修改成功', U('Problem/index', array('token' => $this->IIIIIIIIlIlI)));
                }

            } else {

                $this->error($problem_conf->getError());
            }

        } else {

            $this->assign('set', $conf);

            $this->display();

        }
    }

    public function add()
    {
        if (IS_POST) {
            $problem = M('Problem');
            $en = ['A', 'B', 'C', 'D', 'E', 'F'];
            if ($problem->create()) {
                $data['problem'] = $_POST['problem'];
                $data['answer'] = $_POST['answer'];
                $data['add_time'] = time();
                $re = $problem->add($data);
                if ($re) {
                    $answer = M('Answer');
                    $options = $_POST['option'];
                    foreach ($options as $k => $option) {
                        if (!empty($option)) {
                            $updata['alias'] = $en[$k];
                            $updata['answer'] = $option;
                            $updata['pid'] = $re;
                            $answer->add($updata);
                        }
                    }
                }
                $this->success('添加成功', U('Problem/index', array('token' => $this->IIIIIIIIlIlI)));
            } else {
                $this->error($problem->getError());
            }
        } else {
            $this->display();
        }
    }

    public function edit()
    {
        $problem = M('Problem');
        $answer = M('Answer');
        $id = $_GET['id'];
        if (IS_POST) {
            $pid = $_POST['id'];
            $en = ['A', 'B', 'C', 'D', 'E', 'F'];
            if ($problem->create()) {
                $data['problem'] = $_POST['problem'];
                $data['answer'] = $_POST['answer'];
                $data['add_time'] = time();
                $re = $problem->where('id=' . $pid)->save($data);
                if ($re) {
                    $options = $_POST['option'];
                    $aid = $_POST['aid'];
                    foreach ($options as $k => $option) {
                        if (!empty($option)) {
                            $updata['alias'] = $en[$k];
                            $updata['answer'] = $option;
                            $updata['pid'] = $pid;
                            if ($aid[$k] != 0) {
                                $answer->where('id=' . $aid[$k])->save($updata);
                            } else {
                                $answer->add($updata);
                            }
                        }
                    }
                }
                $this->success('添加成功', U('Problem/index', array('token' => $this->IIIIIIIIlIlI)));
            } else {
                $this->error($problem->getError());
            }
        } else {
            $pro = $problem->where('id=' . $id)->find();
            $ans = $answer->where('pid=' . $pro['id'])->select();
            $pro['answers'] = $ans;
            $this->assign('problem', $pro);
            $this->display();
        }
    }

    public function del()
    {
        $ids = $_POST['pid'];
        $problem = M('Problem');
        $answer = M('Answer');
        foreach ($ids as $id) {
            $problem->where('id=' . $id)->delete();
            $answer->where('pid=' . $id)->delete();
        }
        return [];
    }

    public function answer_del()
    {
        $en = ['A', 'B', 'C', 'D', 'E', 'F'];
        $id = $_POST['id'];
        $pid = $_POST['pid'];
        $answer = M('Answer');
        $re = $answer->where('id=' . $id)->delete();
        if ($re) {
            $answers = $answer->where('pid=' . $pid)->select();
            foreach ($answers as $key => $val) {
                $data['alias'] = $en[$key];
                $answer->where('id=' . $val['id'])->save($data);
                $answers[$key]['alias'] = $en[$key];;
            }
            $this->ajaxReturn($answers, 'JSON');
        }
    }
}

?>