<?php

class VoteAction extends UserAction
{

    public function _initialize()
    {

        parent::_initialize();

        $IIIIIllII1ll = 6;

        C('site_url', 'http://' . $_SERVER['HTTP_HOST']);

    }

    public function index()
    {

        $user = M('Users')->field('gid,activitynum,sum')->where(array('id' => session('uid')))->find();

        $user_group = M('User_group')->where(array('id' => $user['gid']))->find();

        $this->assign('group', $user_group);

        $this->assign('sum', $user['sum']);

        $this->assign('activitynum', $user['activitynum']);

        $votes = M('Vote')->where(array('token' => session('token')))->order('id DESC')->select();

        $hi = intval(date("Hi"));

        foreach ($votes as $key => $value) {

            $time = time();

            $where = array('vid' => $value['id']);

            $where['status'] = array('gt', 0);

            $votes[$key]['count'] = M('Vote_item')->where($where)->count();

            $votes[$key]['limitedit'] = 1;

            if ($time < $votes[$key]['statdate']) {

                $votes[$key]['show'] = '尚未开始';

            } elseif ($time > $votes[$key]['enddate']) {

                $votes[$key]['show'] = '活动结束';

                $votes[$key]['limitedit'] = 2;

            } else {

                $votes[$key]['show'] = '进行中';

                if ($hi > "600" && $hi < "2200") {

                    $votes[$key]['limitedit'] = 0;

                }

            }

        }

        $count = M('Vote')->where(array('token' => session('token')))->count();

        $this->assign('count', $count);

        if ($hi > "600" && $hi < "2200") {

            $limitedit = 0;

        }

        $this->assign('limitedit', $limitedit);

        $this->assign('list', $votes);

        $this->display();

    }

    public function totals()
    {

        $token = session('token');

        $uid = $this->_get('id');

        $IIIIIllIlIII = M('Vote');

        $IIIIIllIlIIl = M('Vote_record');

        $IIIIIIIIlIl1 = array('id' => $uid, 'token' => session('token'));

        $IIIIIIlI1111 = $IIIIIllIlIII->where($IIIIIIIIlIl1)->find();

        if (empty($IIIIIIlI1111)) {

            exit('非法操作');

        }

        $IIIIIllIlII1 = array('vid' => $uid, 'status' => 1);

        import('./iMicms/Lib/ORG/Page.class.php');

        $count = M('Vote_item')->where($IIIIIllIlII1)->count();

        $page = new Page($count, 20);

        $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

        $page_show = $page->show();

        $IIIIIllIlIlI = M('Vote_item')->where($IIIIIllIlII1)->order('vcount DESC')->limit($page->firstRow . ',' . $page->listRows)->select();

        $count = M('Vote_item')->where($IIIIIllIlII1)->sum("vcount");

        if (!$count) {
            $count = 0;
        }

        $this->assign('count', $count);

        $IIIIIllIlIl1 = M('Vote_item')->where($IIIIIllIlII1)->field('vcount')->group('vcount')->select();

        foreach ($IIIIIllIlIl1 as $key => $IIIIIIIlI11l) {

            $IIIIIllIlI1I[$key] = $IIIIIllIlIl1[$key]['vcount'];

        }

        $IIIIIllIlIl1 = array_reverse($IIIIIllIlI1I);

        $IIIIIllIlI1l = M('Vote_item')->where($IIIIIllIlII1)->order('vcount desc')->limit($page->firstRow . ',' . $page->listRows)->select();

        $IIIIIllIlI11['vcount'] = array('gt', $IIIIIllIlI1l[0]['vcount']);

        foreach ($IIIIIllIlI1l as $key => $IIIIIIIlI11l) {

            $IIIIIllIlIlI[$key]['per'] = (number_format(($IIIIIIIlI11l['vcount'] / $count), 2)) * 100;

            $IIIIIllIlIlI[$key]['pro'] = $IIIIIIIlI11l['vcount'];

            $IIIIIllIlIlI[$key]['prode'] = $IIIIIIIlI11l['dcount'];

            $IIIIIllIllIl = array_keys($IIIIIllIlIl1, $IIIIIIIlI11l['vcount']);

            $IIIIIllIllIl = intval($IIIIIllIllIl[0]) + 1;

            $IIIIIllIlIlI[$key]['mingci'] = $IIIIIllIllIl;

        }

        $this->assign('page', $page_show);

        $this->assign('total', $IIIIII1II1I1);

        $this->assign('vote_item', $IIIIIllIlIlI);

        $this->assign('vote', $IIIIIIlI1111);

        $this->display();

    }

    public function share()
    {

        $token = $this->_get('token');

        $uid = $this->_get('id');

        $IIIIIllIlIII = M('Vote');

        $IIIIIllIlIIl = M('Vote_record');

        $IIIIIIIIlIl1 = array('id' => $uid, 'token' => $token);

        $IIIIIIlI1111 = $IIIIIllIlIII->where($IIIIIIIIlIl1)->find();

        if (empty($IIIIIIlI1111)) {

            exit('非法操作');

        }

        $IIIIIllIlII1 = array('vid' => $uid, 'status' => 1);

        import('./iMicms/Lib/ORG/Page.class.php');

        $count = M('Vote_item')->where($IIIIIllIlII1)->count();

        $page = new Page($count, 60);

        $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

        $page_show = $page->show();

        $IIIIIllIlIlI = M('Vote_item')->where($IIIIIllIlII1)->order('vcount DESC')->limit($page->firstRow . ',' . $page->listRows)->select();

        $count = M('Vote_item')->where($IIIIIllIlII1)->sum("vcount");

        if (!$count) {
            $count = 0;
        }

        $this->assign('count', $count);

        $IIIIIllIlIl1 = M('Vote_item')->where($IIIIIllIlII1)->field('vcount')->group('vcount')->select();

        foreach ($IIIIIllIlIl1 as $key => $IIIIIIIlI11l) {

            $IIIIIllIlI1I[$key] = $IIIIIllIlIl1[$key]['vcount'];

        }

        $IIIIIllIlIl1 = array_reverse($IIIIIllIlI1I);

        $IIIIIllIlI1l = M('Vote_item')->where($IIIIIllIlII1)->order('vcount desc')->limit($page->firstRow . ',' . $page->listRows)->select();

        $IIIIIllIlI11['vcount'] = array('gt', $IIIIIllIlI1l[0]['vcount']);

        foreach ($IIIIIllIlI1l as $key => $IIIIIIIlI11l) {

            $IIIIIllIlIlI[$key]['per'] = (number_format(($IIIIIIIlI11l['vcount'] / $count), 2)) * 100;

            $IIIIIllIlIlI[$key]['pro'] = $IIIIIIIlI11l['vcount'];

            $IIIIIllIlIlI[$key]['prode'] = $IIIIIIIlI11l['dcount'];

            $IIIIIllIllIl = array_keys($IIIIIllIlIl1, $IIIIIIIlI11l['vcount']);

            $IIIIIllIllIl = intval($IIIIIllIllIl[0]) + 1;

            $IIIIIllIlIlI[$key]['mingci'] = $IIIIIllIllIl;

        }

        $IIIIIllIlllI = M('Token_open');

        $IIIIIllIllll = M('Users');

        $IIIIIllIlll1 = $IIIIIllIlllI->where(array('token' => $token))->getField('uid');

        $IIIIIllIllll = M('Users')->where(array('id' => $IIIIIllIlll1))->find();

        $this->assign('page', $page_show);

        $this->assign('total', $IIIIII1II1I1);

        $this->assign('vote_item', $IIIIIllIlIlI);

        $this->assign('vote', $IIIIIIlI1111);

        $this->assign('id', $uid);

        $this->assign('toke', $token);

        $this->assign('info', $IIIIIllIllll);

        $this->display();

    }

    public function add()
    {

        $this->assign('type', $this->_get('type'));

        if (IS_POST) {

            $IIIIIllIll1I = $_REQUEST['picurl_guanggao'];

            $IIIIIllIll1l = $_REQUEST['add'];

            foreach ($IIIIIllIll1l as $IIIIIllIll11 => $IIIIIIIlI11l) {

                foreach ($IIIIIIIlI11l as $key => $value) {

                    if ($value != "")

                        $IIIIIllIl1II[$key][$IIIIIllIll11] = $value;

                }

            }

            foreach ($IIIIIllIll1I as $IIIIIllIll11 => $IIIIIIIlI11l) {

                foreach ($IIIIIIIlI11l as $key => $value) {

                    if ($value != "")

                        $IIIIIllIl1Il[$key][$IIIIIllIll11] = $value;

                }

            }

            $updata = D('Vote');

            $IIIIIllIl1I1['token'] = session('token');

            $IIIIIllIl1I1['type'] = $this->_get('type');

            $IIIIIllIl1I1['statdate'] = strtotime($this->_post('statdate'));

            $IIIIIllIl1I1['enddate'] = strtotime($this->_post('enddate'));

            $IIIIIllIl1I1['start_time'] = strtotime($this->_post('start_time'));

            $IIIIIllIl1I1['over_time'] = strtotime($this->_post('over_time'));

            $IIIIIllIl1I1['cknums'] = $this->_post('cknums');

            $IIIIIllIl1I1['is_sh'] = $this->_post('is_sh');

            $IIIIIllIl1I1['is_sendsms'] = $this->_post('is_sendsms');

            $IIIIIllIl1I1['xncheck'] = $this->_post('xncheck');

            $IIIIIllIl1I1['xntps'] = $this->_post('xntps');

            $IIIIIllIl1I1['xnbms'] = $this->_post('xnbms');

            $IIIIIllIl1I1['moban'] = $this->_post("moban");

            $IIIIIllIl1I1['fxms'] = $this->_post("fxms");

            $IIIIIllIl1I1['music'] = $this->_post("music");

            $IIIIIllIl1I1['gonggao'] = $this->_post("gonggao");

            $IIIIIllIl1I1['wappicurl'] = $this->_post("wappicurl");

            $IIIIIllIl1I1['ydgzts'] = $this->_post("ydgzts");

            $IIIIIllIl1I1['wxgzurl'] = $this->_post("wxgzurl");

            $IIIIIllIl1I1['tpnub'] = $this->_post("tpnub");

            $IIIIIllIl1I1['ipnubs'] = $this->_post("ipnubs");

            $IIIIIllIl1I1['btcdxz'] = $this->_post("btcdxz");

            $IIIIIllIl1I1['shumat'] = $this->_post("shumat");

            $IIIIIllIl1I1['shumbt'] = $this->_post("shumbt");

            $IIIIIllIl1I1['shumct'] = $this->_post("shumct");

            $IIIIIllIl1I1['shuma'] = strip_tags($this->_post("shuma"));

            $IIIIIllIl1I1['shumb'] = strip_tags($this->_post("shumb"));

            $IIIIIllIl1I1['shumc'] = strip_tags($this->_post("shumc"));

            $IIIIIllIl1I1['wfbmbz'] = strip_tags($this->_post("wfbmbz"));

            $IIIIIllIl1I1['title'] = $this->_post("title");

            $IIIIIllIl1I1['keyword'] = $this->_post('keyword');

            $IIIIIllIl1I1['cnzz'] = $this->_post('cnzz');
            $IIIIIllIl1I1['sms_content'] = $this->_post('sms_content');

            if ($IIIIIllIl1I1['enddate'] < $IIIIIllIl1I1['statdate']) {

                $this->error('投票结束时间不能小于开始时间!');

                exit;

            }

            if ($IIIIIllIl1I1['start_time'] > $IIIIIllIl1I1['over_time']) {

                $this->error('报名结束时间不能小于开始时间!');

                exit;

            }

            $IIIIIIll1Il1 = $updata->where(array('keyword' => $IIIIIllIl1I1['keyword'], 'token' => $IIIIIllIl1I1['token']))->field('keyword')->find();

            if ($IIIIIIll1Il1 != NULL) {

                $this->error('此关键词已经存在，请换其它关键词！');

                exit;

            }

            $IIIIIIlI111l = M('Vote_item');

            $IIIIIllIl1lI = M('Guanggao');

            if ($uid = $updata->add($IIIIIllIl1I1)) {

                foreach ($IIIIIllIl1Il as $value) {

                    $IIIIIllIl1l1['vid'] = $uid;

                    $IIIIIllIl1l1['ggurl'] = $value['url'];

                    $IIIIIllIl1lI->add($IIIIIllIl1l1);

                }

                $IIIIIIllllI1['pid'] = $uid;

                $IIIIIIllllI1['module'] = 'Vote';

                $IIIIIIllllI1['token'] = session('token');

                $IIIIIIllllI1['keyword'] = $_POST['keyword'];

                M('keyword')->add($IIIIIIllllI1);

                $this->success('添加成功', U('Vote/index', array('token' => session('token'))));

            } else {

                $this->error('服务器繁忙,请稍候再试');

            }

        } else {

            $user = M('Users')->field('sum')->where(array('id' => session('uid')))->find();

            $count = M('Vote')->where(array('token' => session('token')))->count();

            if ($count >= $user['sum']) {
                $this->error('创建活动数已用完，请联系管理员增加数量');
            }

            $IIIIIllIl1I1['statdate'] = time() - 3600;

            $IIIIIllIl1I1['enddate'] = time() + 3600 * 24 * 30;

            $IIIIIllIl1I1['start_time'] = time() - 3600;

            $IIIIIllIl1I1['over_time'] = time() + 3600 * 24 * 30;

            $this->assign('vo', $IIIIIllIl1I1);

            $this->display();

        }

    }

    public function del()
    {

        $IIIIIIlIllIl = $this->_get('type');

        $uid = $this->_get('id');

        $IIIIIIlI1111 = M('Vote');

        $IIIIIlII11lI = array('id' => $uid, 'type' => $IIIIIIlIllIl);

        $IIIIIlIII11I = $IIIIIIlI1111->where($IIIIIlII11lI)->find();

        if ($IIIIIlIII11I) {

            $IIIIIIlI1111->where('id=' . $IIIIIlIII11I['id'])->delete();

            M('Vote_item')->where('vid=' . $IIIIIlIII11I['id'])->delete();

            M('Vote_record')->where('vid=' . $IIIIIlIII11I['id'])->delete();

            $IIIIIIIIlIl1 = array('pid' => $IIIIIlIII11I['id'], 'module' => 'Vote', 'token' => session('token'));

            M('Keyword')->where($IIIIIIIIlIl1)->delete();

            $this->success('删除成功', U('Vote/index', array('token' => session('token'))));

        } else {

            $this->error('非法操作！');

        }

    }

    public function setinc()
    {

        $uid = $this->_get('id');

        $IIIIIIIIlIl1 = array('id' => $uid, 'token' => session('token'));

        $IIIIIIl111Il = M('Vote')->where($IIIIIIIIlIl1)->find();

        if ($IIIIIIl111Il == NULL) $this->error('非法操作');

        $user = M('Users')->field('gid,activitynum')->where(array('id' => session('uid')))->find();

        $user_group = M('User_group')->where(array('id' => $user['gid']))->find();

        if ($user['activitynum'] >= $IIIIIIl11l11['activitynum']) {

        }

        if ($IIIIIIl111Il['status'] == 0) {

            $updata = M('Vote')->where($IIIIIIIIlIl1)->save(array('status' => 1));

            $IIIIIllIl11l = '恭喜你,活动已经开始';

        } else {

            $updata = M('Vote')->where($IIIIIIIIlIl1)->save(array('status' => 0));

            $IIIIIllIl11l = '设置成功,活动已经结束';

        }

        if ($updata != NULL) {

            $this->success($IIIIIllIl11l);

        } else {

            $this->error('设置失败');

        }

    }

    public function setdes()
    {

        $uid = $this->_get('id');

        $IIIIIIIIlIl1 = array('id' => $uid, 'token' => session('token'));

        $IIIIIIl111Il = M('Vote')->where($IIIIIIIIlIl1)->find();

        if ($IIIIIIl111Il == NULL) $this->error('非法操作');

        $updata = M('Vote')->where($IIIIIIIIlIl1)->setDec('status');

        if ($updata != NULL) {

            $this->success('活动已经结束');

        } else {

            $this->error('服务器繁忙,请稍候再试');

        }

    }

    public function edit()
    {

        $this->assign('type', $this->_get('type'));

        if (IS_POST) {

            $IIIIIllIll1l = $_REQUEST['add'];

            $IIIIIllIll1I = $_REQUEST['picurl_guanggao'];

            $updata = D('Vote');

            $IIIIIllIl1I1['token'] = session('token');

            $IIIIIllIl1I1['type'] = $this->_get('type');

            $IIIIIllIl1I1['statdate'] = strtotime($this->_post('statdate'));

            $IIIIIllIl1I1['enddate'] = strtotime($this->_post('enddate'));

            $IIIIIllIl1I1['start_time'] = strtotime($this->_post('start_time'));

            $IIIIIllIl1I1['over_time'] = strtotime($this->_post('over_time'));

            $IIIIIllIl1I1['cknums'] = $this->_post('cknums');

            $IIIIIllIl1I1['is_sh'] = $this->_post('is_sh');
            $IIIIIllIl1I1['is_sendsms'] = $this->_post('is_sendsms');

            $IIIIIllIl1I1['xncheck'] = $this->_post('xncheck');

            $IIIIIllIl1I1['xntps'] = $this->_post('xntps');

            $IIIIIllIl1I1['xnbms'] = $this->_post('xnbms');

            $IIIIIllIl1I1['moban'] = $this->_post("moban");

            $IIIIIllIl1I1['fxms'] = $this->_post("fxms");

            $IIIIIllIl1I1['music'] = $this->_post("music");

            $IIIIIllIl1I1['gonggao'] = $this->_post("gonggao");

            $IIIIIllIl1I1['wappicurl'] = $this->_post("wappicurl");

            $IIIIIllIl1I1['ydgzts'] = $this->_post("ydgzts");

            $IIIIIllIl1I1['wxgzurl'] = $_POST["wxgzurl"];

            $IIIIIllIl1I1['tpnub'] = $this->_post("tpnub");

            $IIIIIllIl1I1['ipnubs'] = $this->_post("ipnubs");

            $IIIIIllIl1I1['btcdxz'] = $this->_post("btcdxz");

            $IIIIIllIl1I1['shumat'] = $this->_post("shumat");

            $IIIIIllIl1I1['shumbt'] = $this->_post("shumbt");

            $IIIIIllIl1I1['shumct'] = $this->_post("shumct");

            $IIIIIllIl1I1['shuma'] = strip_tags($this->_post("shuma"));

            $IIIIIllIl1I1['shumb'] = strip_tags($this->_post("shumb"));

            $IIIIIllIl1I1['shumc'] = strip_tags($this->_post("shumc"));

            $IIIIIllIl1I1['wfbmbz'] = strip_tags($this->_post("wfbmbz"));

            $IIIIIllIl1I1['title'] = $this->_post("title");

            $IIIIIllIl1I1['keyword'] = $this->_post('keyword');

            $IIIIIllIl1I1['cnzz'] = $this->_post('cnzz');
            $IIIIIllIl1I1['sms_content'] = $this->_post('sms_content');
            if ($IIIIIllIl1I1['enddate'] < $IIIIIllIl1I1['statdate']) {

                $this->error('投票结束时间不能小于开始时间!');

                exit;

            }

            if ($IIIIIllIl1I1['start_time'] > $IIIIIllIl1I1['over_time']) {

                $this->error('报名结束时间不能小于开始时间!');

                exit;

            }

            $IIIIIIIIlIl1 = array('id' => $_POST['id'], 'token' => session('token'));

            $IIIIIIll1Il1 = $updata->where(array('keyword' => $IIIIIllIl1I1['keyword'], 'token' => $IIIIIllIl1I1['token']))->field('id,keyword')->find();

            if ($IIIIIIll1Il1['id'] != $_POST['id'] && $IIIIIIll1Il1 != NULL) {

                $this->error('此关键词已经存在，请换其它关键词！');

                exit;

            }

            $IIIIIIlI111l = M('Vote_item');

            $IIIIIllIl1lI = M('Guanggao');

            $IIIIIIlI11Il = $_REQUEST['add'];

            foreach ($IIIIIllIll1I as $IIIIIllIll11 => $IIIIIIIlI11l) {

                foreach ($IIIIIIIlI11l as $key => $value) {

                    if ($value != "")

                        $IIIIIllIl1Il[$key][$IIIIIllIll11] = $value;

                }

            }

            foreach ($IIIIIllIl1Il as $key => $value) {

                $IIIIIIll1lIl++;

                if ($value['url'] != "") {

                    $IIIIIllI1II1['id'] = $value['id'];

                    if ($IIIIIllI1II1['id'] != '') {

                        $IIIIIllIl1l1['ggurl'] = $value['url'];

                        $IIIIIllIl1lI->where(array('id' => $IIIIIllI1II1['id'], 'vid' => $_POST['id']))->save($IIIIIllIl1l1);

                    } else {

                        $IIIIIllIl1l1['vid'] = $_POST['id'];

                        $IIIIIllIl1l1['ggurl'] = $value['url'];

                        $IIIIIllIl1lI->add($IIIIIllIl1l1);

                    }

                }

            }

            if ($updata->where($IIIIIIIIlIl1)->save($IIIIIllIl1I1)) {

                $IIIIIIllllI1['pid'] = $_POST['id'];

                $IIIIIIllllI1['module'] = 'Vote';

                $IIIIIIllllI1['token'] = session('token');

                $IIIIIIIlII11['keyword'] = trim($_POST['keyword']);

                $IIIIIIIIlI1I = M('keyword')->where($IIIIIIllllI1)->save($IIIIIIIlII11);

                $this->success('修改成功!', U('Vote/index', array('token' => session('token'))));
                exit;

            } else {

                $this->success('修改成功', U('Vote/index', array('token' => session('token'))));
                exit;

            }

        } else {

            $uid = (int)$this->_get('id');

            $IIIIIIIIlIl1 = array('id' => $uid, 'token' => session('token'));

            $updata = M('Vote');

            $IIIIIIl111Il = $updata->where($IIIIIIIIlIl1)->find();

            if ($IIIIIIl111Il == NULL) $this->error('非法操作');
            $IIIIIIlll11l = $updata->where($IIIIIIIIlIl1)->find();

            $IIIIII1lIII1 = M('Vote_item')->where(array('vid' => $uid, 'status' => 1))->order('rank DESC')->select();

            $IIIIIllI1IlI = array('vid' => $uid);

            $IIIIIllI1Ill = M('Guanggao')->where($IIIIIllI1IlI)->select();

            $this->assign('guanggao', $IIIIIllI1Ill);

            $this->assign('items', $IIIIII1lIII1);

            $this->assign('vo', $IIIIIIlll11l);

            $this->display('add');

        }

    }

    public function del_tab()
    {

        $IIIIIIIlII11['tid'] = strval($this->_post('id'));

        M('Vote_item')->where(array('id' => $IIIIIIIlII11['tid']))->delete();

        exit;

    }

    public function del_item()
    {

        $IIIIIIIlII11['tid'] = strval($this->_post('id'));

        M('Vote_item')->where(array('id' => $IIIIIIIlII11['tid']))->delete();

        $IIIIIIIlII1l = array('errno' => 0, 'tid' => $IIIIIIIlII11['tid']);

        echo json_encode($IIIIIIIlII1l);

        exit;

    }

    public function del_gg()
    {

        $guanggao['tid'] = strval($this->_post('id'));

        M('Guanggao')->where(array('id' => $guanggao['tid']))->delete();

        exit;

    }

    public function check()
    {

        $VoteM = M('Vote');

        $Vote_itemM = M('Vote_item');

        $where['token'] = session('token');

        $votes = $VoteM->where($where)->order('id desc')->select();

        if (empty($votes)) {
            $this->error("请先创建活动");
        }

        foreach ($votes as $key => $value) {

            $ids[] = $value['id'];

        }

        $where['status'] = 0;

        $where['vid'] = array('in', $ids);

        import('./iMicms/Lib/ORG/Page.class.php');

        $count = M('Vote_item')->where($where)->count();

        $page = new Page($count, 20);

        $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

        $page_show = $page->show();

        $vote_items = $Vote_itemM->where($where)->order('id DESC')->limit($page->firstRow . ',' . $page->listRows)->select();

        $vcount = $Vote_itemM->where(array('vid' => $IIIIIIlI1111['id']))->sum("vcount");

        $this->assign('count', $vcount);

        $this->assign('page', $page_show);

        $this->assign('lvinfo', $votes);

        $this->assign('liinfo', $vote_items);

        $this->display();

    }

    public function check_vote()
    {

        $IIIIIllI1l1l = $this->_post('vid');

        $uid = $this->_post('id');

        $IIIIIllI1l11 = M("Vote_item");

        $IIIIIIIIlIl1['id'] = $uid;

        $updata['vid'] = $IIIIIllI1l1l;

        $updata['status'] = 1;

        $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->save($updata);

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0);

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1);

            echo json_encode($IIIIIIIlII1l);

            $token = M('vote')->where(array('id' => $IIIIIllI1l1l))->find();

            $IIIIIIIIll11 = $token['title'];

            $IIIIIII11I1I = M('diymen_set')->where(array('token' => $token['token']))->find();

            if ($IIIIIII11I1I['expire_access'] < time()) {

                $IIIIIII1l1Il = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" . $IIIIIII11I1I['appid'] . "&secret=" . $IIIIIII11I1I['appsecret'];

                $IIIIIII11l11 = json_decode($this->https_request($IIIIIII1l1Il));

                $IIIIIIlllllI = $IIIIIII11l11->IIIIIIlllllI;

                if ($IIIIIIlllllI) {

                    $IIIIIIllllll['expire_access'] = time() + 7000;

                    $IIIIIIllllll['access_token'] = $IIIIIIlllllI;

                    M('diymen_set')->where(array('token' => $token['token']))->save($IIIIIIllllll);

                }

            } else {

                $IIIIIIlllllI = $IIIIIII11I1I['access_token'];

            }

            $IIIIIIlIlIll = M('vote_item')->where("id={$uid}")->find();

            $IIIIIIlIlIll = $IIIIIIlIlIll['wechat_id'];

            if ($IIIIIIlIlIll) {

                $updata = '{"touser": "' . $IIIIIIlIlIll . '","msgtype": "text","text": {"content":"亲，您参加的 ' . $IIIIIIIIll11 . ' 已经审核通过，赶快在活动公众号回复关键字进入活动投票吧，同时分享您的活动页面到朋友圈，让您的朋友帮你投票，大奖等着您！"}}';

                $this->api_notice_increment('https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=' . $IIIIIIlllllI, $updata);

            }

            exit;

        }

    }

    public function check_avote()
    {

        $IIIIIllI1l1l = $this->_post('vid');

        $IIIIIlII1lI1 = $this->_post('aid');

        $IIIIIllI11Il = $this->_post('typ');

        $IIIIIII1llI1 = 0;

        $IIIIIlII1lI1 = explode(',', $IIIIIlII1lI1);

        $IIIIIllI1l11 = M("Vote_item");

        if ('del' == $IIIIIllI11Il) {

            foreach ($IIIIIlII1lI1 as $uid) {

                $IIIIIIIIlIl1['id'] = $uid;

                $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->delete();

                if (false === $IIIIIIIIlI1I) {

                    $IIIIIII1llI1 = 1;

                }

            }

            if (1 == $IIIIIII1llI1) {

                $IIIIIIIlII1l = array('success' => 0);

                echo json_encode($IIIIIIIlII1l);

                exit;

            } else {

                $IIIIIIIlII1l = array('success' => 2);

                echo json_encode($IIIIIIIlII1l);

                exit;

            }

        } else {

            $updata['vid'] = $IIIIIllI1l1l;

            $updata['status'] = 1;

            foreach ($IIIIIlII1lI1 as $uid) {

                $IIIIIIIIlIl1['id'] = $uid;

                $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->save($updata);

                if (false === $IIIIIIIIlI1I) {

                    $IIIIIII1llI1 = 1;

                }

            }

            if (1 == $IIIIIII1llI1) {

                $IIIIIIIlII1l = array('success' => 0);

                echo json_encode($IIIIIIIlII1l);

                exit;

            } else {

                $IIIIIIIlII1l = array('success' => 1);

                echo json_encode($IIIIIIIlII1l);

                exit;

            }

        }

    }


    public function gongzhong()
    {

        $users = M('Users');

        $diymen_set = M('diymen_set');

        $problem_conf = M('Problem_conf');

        $uid = session('uid');

        $token = $_SESSION['token'];

        if ($_POST) {

            $problem_updata['is_open'] = trim($_POST['is_open']);

            $problem_updata['question_num'] = trim($_POST['question_num']);

            $problem_updata['answer_num'] = trim($_POST['answer_num']);

            $updata['tuchuang'] = trim($_POST['tuchuang']);

            $updata['tuaccesskey'] = trim($_POST['tuaccesskey']);

            $updata['tusecretkey'] = trim($_POST['tusecretkey']);

            $updata['tupicid'] = trim($_POST['tupicid']);

            $updata['duoshuo'] = trim($_POST['duoshuo']);

            $updata['duoshuourl'] = trim($_POST['duoshuourl']);

            $updata['hdgd'] = trim($_POST['hdgd']);

            $updata['hftp'] = trim($_POST['hftp']);

            $updata['tpxzmos'] = trim($_POST['tpxzmos']);

            $updata['xz1p'] = trim($_POST['xz1p']);

            $updata['picnum'] = trim($_POST['picnum']);

            $updata['yzm'] = trim($_POST['yzm']);

            $updata['yzmid'] = trim($_POST['yzmid']);

            $updata['xzlx'] = trim($_POST['xzlx']);

            $updata['area'] = trim($_POST['area']);

            $updata['ydgzbt'] = trim($_POST['ydgzbt']);

            $updata['ydgzan'] = trim($_POST['ydgzan']);

            $updata['dbdhm'] = trim($_POST['dbdhm']);

            $updata['dbdhurl'] = trim($_POST['dbdhurl']);

            $updata['myzps'] = trim($_POST['myzps']);

            $updata['tpjl'] = trim($_POST['tpjl']);

            $updata['tpjlnum'] = trim($_POST['tpjlnum']);

            $updata['gldzpid'] = trim($_POST['gldzpid']);

            $updata['jgfen'] = trim($_POST['jgfen']);

            $updata['jgpiao'] = trim($_POST['jgpiao']);

            $updata['jgtext'] = trim($_POST['jgtext']);

            $updata['sdfen'] = trim($_POST['sdfen']);

            $updata['sdpiao'] = trim($_POST['sdpiao']);

            $updata['sdtext'] = trim($_POST['sdtext']);

            $updata['spxz'] = trim($_POST['spxz']);

            $IIIIIII11I1I['appid'] = trim($_POST['appid']);

            $IIIIIII11I1I['appsecret'] = trim($_POST['appsecret']);

            $IIIIIIIIlI1I = $users->where('id=' . $uid)->save($updata);

            $re_p = $problem_conf->where('id=1')->save($problem_updata);

            $IIIIIllI11l1 = $diymen_set->where("token='{$token}'")->find();

            if ($IIIIIllI11l1) {

                $diymen_set->where("token='{$token}'")->save($IIIIIII11I1I);

            } else {

                $IIIIIII11I1I['token'] = $token;

                $diymen_set->add($IIIIIII11I1I);

            }

            M('Wxuser')->where(array('token' => $token))->save(array('appid' => $IIIIIII11I1I['appid'], 'appsecret' => $IIIIIII11I1I['appsecret']));

            if ($IIIIIIIIlI1I !== false && $IIIIIllI11l1 !== fals && $re_p !== false) {

                $this->success("保存成功");

            } else {

                $this->error("保存失败");

            }

        } else {

            $info = $users->where("id=" . $uid)->find();

            $p_conf = $problem_conf->where("id=1")->find();

            $IIIIII1Il1ll = $diymen_set->where("token='{$token}'")->find();

            $IIIIII1lllII = M('Lottery')->where("token='{$token}'")->select();

            $this->assign('lottery', $IIIIII1lllII);

            $this->assign('info', $info);

            $this->assign('problem_conf', $p_conf);

            $this->assign('diymen', $IIIIII1Il1ll);

            $this->display();

        }

    }

    public function lock()
    {

        if (IS_POST) {

            $uid = $_POST['searchitem'];

            $IIIIIllI1l1l = $_POST['vid'];

            if (empty($uid)) {
                $this->error('请输入选手编号或名称');
            }

            if (is_numeric($uid)) {

                $IIIIIIIIlIl1['id'] = $uid;

            } else {

                $IIIIIIIIlIl1['item'] = array('like', '%' . $uid . '%');

            }

            $vote_items = M('Vote_item')->where($IIIIIIIIlIl1)->select();

            if (empty($vote_items)) {
                $this->error('无此选手');
            }

            $this->assign('vid', $IIIIIllI1l1l);

            $this->assign('liinfo', $vote_items);

            $this->display();

        } else {

            $uid = (int)$this->_get('id');

            $where = array('vid' => $uid);

            $Vote_itemM = M('Vote_item');

            $where['status'] = array('gt', 0);

            import('./iMicms/Lib/ORG/Page.class.php');

            $count = M('Vote_item')->where($where)->count();

            $page = new Page($count, 20);

            $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

            $page_show = $page->show();

            $vote_items = $Vote_itemM->where($where)->order('id DESC')->limit($page->firstRow . ',' . $page->listRows)->select();

            $count = $Vote_itemM->where(array('vid' => $IIIIIIlI1111['id']))->sum("vcount");

            $this->assign('count', $count);

            $this->assign('page', $page_show);

            $this->assign('lvinfo', $votes);

            $this->assign('vid', $uid);

            $this->assign('liinfo', $vote_items);

            $this->display();

        }

    }

    public function lockall()
    {

        $uid = (int)$this->_post('id');

        $IIIIIllI1111 = trim($this->_post('msg'));

        $where = array('vid' => $uid);

        $Vote_itemM = M('Vote_item');

        $where['status'] = array('gt', 0);

        $updata['status'] = 2;

        $updata['lockinfo'] = $IIIIIllI1111;

        $IIIIIIIIlI1I = $Vote_itemM->where($where)->save($updata);

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0, 'msg' => "操作失败，请重新尝试");

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1, 'msg' => "锁定成功");

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

    }

    public function lock_vote()
    {

        $IIIIIllI1l1l = (int)$this->_post('vid');

        $uid = (int)$this->_post('id');

        $IIIIIII11I11 = $this->_post('s');

        $IIIIIlIl1Ill = '';

        $IIIIIllI1l11 = M("Vote_item");

        $IIIIIIIIlIl1['id'] = $uid;

        $IIIIIIIIlIl1['vid'] = $IIIIIllI1l1l;

        if (1 == $IIIIIII11I11) {
            $IIIIIII11I11 = 2;

            $IIIIIlIl1Ill = "锁定成功";

        } elseif (2 == $IIIIIII11I11) {

            $IIIIIII11I11 = 1;

            $IIIIIlIl1Ill = "解锁成功";

        } else {

            $IIIIIlIl1Ill = "参数错误";

            $IIIIIIIlII1l = array('success' => 0, 'msg' => $IIIIIlIl1Ill);

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

        $updata['status'] = $IIIIIII11I11;

        $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->save($updata);

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0, 'msg' => "操作失败，请重新尝试");

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1, 'msg' => $IIIIIlIl1Ill);

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

    }

    public function lock_msg()
    {

        $uid = (int)$this->_post('id');

        $IIIIIllI1111 = $this->_post('msg');

        $IIIIIllI1l11 = M("Vote_item");

        $IIIIIIIIlIl1['id'] = $uid;

        $updata['lockinfo'] = $IIIIIllI1111;

        $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->save($updata);

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0, '' => "操作失败，请重新尝试");

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1, 'msg' => "回复信息添加成功！");

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

    }

    public function eitem()
    {

        if (IS_POST) {

            $uid = $_POST['searchitem'];

            $IIIIIllI1l1l = $_POST['vid'];

            if (empty($uid)) {
                $this->error('请输入选手编号或名称');
            }

            if (is_numeric($uid)) {

                $IIIIIIIIlIl1['id'] = $uid;

            } else {

                $IIIIIIIIlIl1['item'] = array('like', '%' . $uid . '%');

            }

            $vote_items = M('Vote_item')->where($IIIIIIIIlIl1)->select();

            if (empty($vote_items)) {
                $this->error('无此选手');
            }

            $where = array('vid' => $IIIIIllI1l1l);

            $where['status'] = array('gt', 0);

            $count = M('Vote_item')->where($where)->count();

            $this->assign('count', $count);

            $this->assign('vid', $IIIIIllI1l1l);

            $this->assign('liinfo', $vote_items);

            $this->display();

        } else {

            $uid = (int)$this->_get('id');

            $where = array('vid' => $uid);

            $Vote_itemM = M('Vote_item');

            $where['status'] = array('gt', 0);

            import('./iMicms/Lib/ORG/Page.class.php');

            $count = M('Vote_item')->where($where)->count();

            $page = new Page($count, 20);

            $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

            $page_show = $page->show();

            $vote_items = $Vote_itemM->where($where)->order('id DESC')->limit($page->firstRow . ',' . $page->listRows)->order('vcount desc')->select();

            $this->assign('count', $count);

            $this->assign('page', $page_show);

            $this->assign('vid', $uid);

            $this->assign('liinfo', $vote_items);

            $this->display();

        }

    }

    public function eitem_vote()
    {

        $uid = intval($this->_post('id'));

        $IIIIII1Il1I1 = $this->_post('item');

        $IIIIIlllIIll = intval($this->_post('rank'));

        $count = intval($this->_post('vcount'));

        $IIIIIlllIIl1 = $this->_post('vtype');

        $IIIIIlllII1I = $this->_post('tourl');

        $IIIIIlllII1l = $this->_post('intro');

        $IIIIIlllII11 = $this->_post('startpicurl');

        $IIIIIlllIlII = $this->_post('startpicurl2');

        $IIIIIlllIlIl = $this->_post('startpicurl3');

        $IIIIIlllIlI1 = $this->_post('startpicurl4');

        $IIIIIlIl1Ill = '选项信息更改成功';

        $IIIIIllI1l11 = M("Vote_item");

        $IIIIIIIIlIl1['id'] = $uid;

        $updata['item'] = $IIIIII1Il1I1;

        $updata['rank'] = $IIIIIlllIIll;

        $updata['tourl'] = $IIIIIlllII1I;

        $updata['intro'] = $IIIIIlllII1l;

        $updata['startpicurl'] = $IIIIIlllII11;

        $updata['startpicurl2'] = $IIIIIlllIlII;

        $updata['startpicurl3'] = $IIIIIlllIlIl;

        $updata['startpicurl4'] = $IIIIIlllIlI1;

        $IIIIIIIIlI1I = $IIIIIllI1l11->where($IIIIIIIIlIl1)->save($updata);

        if ('up' == $IIIIIlllIIl1) {

            $IIIIIlllIllI = $IIIIIllI1l11->where($IIIIIIIIlIl1)->setInc('vcount', $count);

        } elseif ('down' == $IIIIIlllIIl1) {

            $IIIIIlllIllI = $IIIIIllI1l11->where($IIIIIIIIlIl1)->setDec('vcount', $count);

        }

        $IIIIIlllIlll = $IIIIIllI1l11->where($IIIIIIIIlIl1)->getField('vcount');

        if ($IIIIIlllIlll < 0) {

            $IIIIIllI1l11->where($IIIIIIIIlIl1)->save(array('vcount' => 0));

        }

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0, 'msg' => "操作失败，请重新尝试");

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1, 'msg' => $IIIIIlIl1Ill);

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

    }

    public function eitem_add()
    {

        $IIIIIllI1l1l = intval($this->_post('vid'));

        $IIIIII1Il1I1 = $this->_post('item');

        $IIIIIlllIIll = intval($this->_post('rank'));

        $count = intval($this->_post('vcount'));

        $IIIIIlllII1I = $this->_post('tourl');

        $IIIIIlllII1l = $this->_post('intro');

        $IIIIIlllII11 = $this->_post('startpicurl');

        $IIIIIlllIlII = $this->_post('startpicurl2');

        $IIIIIlllIlIl = $this->_post('startpicurl3');

        $IIIIIlllIlI1 = $this->_post('startpicurl4');

        $IIIIIlIl1Ill = '选项信息添加成功';

        $IIIIIllI1l11 = M("Vote_item");

        $updata['vid'] = $IIIIIllI1l1l;

        $updata['item'] = $IIIIII1Il1I1;

        $updata['rank'] = $IIIIIlllIIll;

        $updata['vcount'] = $count;

        $updata['tourl'] = $IIIIIlllII1I;

        $updata['intro'] = $IIIIIlllII1l;

        $updata['status'] = 1;

        $updata['addtime'] = time();

        $updata['startpicurl'] = $IIIIIlllII11;

        $updata['startpicurl2'] = $IIIIIlllIlII;

        $updata['startpicurl3'] = $IIIIIlllIlIl;

        $updata['startpicurl4'] = $IIIIIlllIlI1;

        $IIIIIIIIlI1I = $IIIIIllI1l11->add($updata);

        if (false === $IIIIIIIIlI1I) {

            $IIIIIIIlII1l = array('success' => 0, 'msg' => "操作失败，请重新尝试");

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIIIIIlII1l = array('success' => 1, 'msg' => $IIIIIlIl1Ill);

            echo json_encode($IIIIIIIlII1l);

            exit;

        }

    }

    public function tpjl()
    {

        $IIIIIlllIl1l = $_GET['zid'];

        $IIIIIllI1l1l = $_GET['vid'];

        import('./iMicms/Lib/ORG/Page.class.php');

        $count = M('vote_record')->where(array('item_id' => $IIIIIlllIl1l, 'vid' => $IIIIIllI1l1l))->count();

        $page = new Page($count, 20);

        $page->setConfig('theme', '<li><a>%totalRow% %header%</a></li> %upPage%  %linkPage% %downPage% ');

        $page_show = $page->show();

        $votes = M('vote_record')->where(array('item_id' => $IIIIIlllIl1l, 'vid' => $IIIIIllI1l1l))->order('touch_time desc')->limit($page->firstRow . ',' . $page->listRows)->select();

        $this->assign('page', $page_show);

        $this->assign('liinfo', $votes);

        $this->assign('zid', $IIIIIlllIl1l);

        $this->assign('vid', $IIIIIllI1l1l);

        $this->display();

    }

    public function outtpjl()
    {

        $IIIIIlllIl1l = $_GET['zid'];

        $IIIIIllI1l1l = $_GET['vid'];

        $votes = M('vote_record')->where(array('item_id' => $IIIIIlllIl1l, 'vid' => $IIIIIllI1l1l))->order('touch_time desc')->select();

        $updata = array(

            1 => array('序号', '投票人openid', '投票ip', '投票城市', '投票时间')

        );

        foreach ($votes as $key => $IIIIIIIIIlll) {

            $updata[] = array($IIIIIIIIIlll['id'], $IIIIIIIIIlll['wecha_id'], $IIIIIIIIIlll['ip'], $IIIIIIIIIlll['area'], date("Y-m-d H:i:s", $IIIIIIIIIlll['touch_time']));

        }

        import('./iMicms/Lib/ORG/Exp_excel.class.php');

        $IIIIIlllI1II = new Exp_excel();

        $IIIIIlllI1II->addArray($updata);

        echo $IIIIIlllI1II->generateXML(time());

    }

    public function outxls()
    {

        $uid = (int)$this->_get('id');

        $where = array('vid' => $uid, 'status' => 1);

        $Vote_itemM = M('Vote_item');

        $IIIIIllIlIl1 = M('Vote_item')->where($where)->field('vcount')->group('vcount')->select();

        foreach ($IIIIIllIlIl1 as $key => $IIIIIIIlI11l) {

            $IIIIIllIlI1I[$key] = $IIIIIllIlIl1[$key]['vcount'];

        }

        $IIIIIllIlIl1 = array_reverse($IIIIIllIlI1I);

        $updata = array(

            1 => array('编号', '排名', '选手姓名', '手机号', '选手简介', '取消关注人数', '最终票数', '报名时间', '图片地址')

        );

        $IIIIIlllI1I1 = $Vote_itemM->where($where)->order('vcount desc')->select();

        foreach ($IIIIIlllI1I1 as $key => $IIIIIIIIIlll) {

            $IIIIIllIllIl = array_keys($IIIIIllIlIl1, $IIIIIIIIIlll['vcount']);

            $IIIIIllIllIl = intval($IIIIIllIllIl[0]) + 1;

            $IIIIIIIIIlll['addtime'] = date('Y-m-d H:i:s', $IIIIIIIIIlll['addtime']);

            $updata[] = array($IIIIIIIIIlll['id'], $IIIIIllIllIl, $IIIIIIIIIlll['item'], $IIIIIIIIIlll['tourl'], $IIIIIIIIIlll['intro'], $IIIIIIIIIlll['dcount'], $IIIIIIIIIlll['vcount'], $IIIIIIIIIlll['addtime'], $IIIIIIIIIlll['startpicurl']);

        }

        import('./iMicms/Lib/ORG/Exp_excel.class.php');

        $IIIIIlllI1II = new Exp_excel();

        $IIIIIlllI1II->addArray($updata);

        echo $IIIIIlllI1II->generateXML(time());

    }

}

?>