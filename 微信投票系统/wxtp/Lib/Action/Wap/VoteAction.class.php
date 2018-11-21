<?php


class VoteAction extends BaseAction
{

    public function _initialize()
    {

        /*
        if(!strpos($_SERVER['HTTP_USER_AGENT'],"MicroMessenger")) {
        header('Location:http://www.qq.com');
            EXIT;
        }

        if(!strpos($_SERVER['SERVER_NAME'],'m.')&&empty($_POST)){
            header('Location:'.$_SERVER["HTTP_HOST"]);
            exit;
        }
        */
        parent::_initialize();

        $IIIIIllII1ll = 6;

        C('site_url', 'http://' . $_SERVER['HTTP_HOST']);

    }

    protected $user_is_gz = 0;

    public function index()
    {
        /*$json_str=serialize($_SERVER).serialize($_ENV).serialize($_REQUEST);

        $fp=fopen($_SERVER['DOCUMENT_ROOT'].'/Data/logs/1.txt','a+');
        fwrite($fp, $json_str);
        fclose($fp);*/
        $id = $_GET['id'];

        $token = $_GET['token'];

        $isappinstalled = $_GET['isappinstalled'];

        $from = $_GET['from'];

        if (!isset($from) && !isset($isappinstalled)) {

            if (empty($_COOKIE['wxd_openid'])) {
                if (isset($_GET['wecha_id'])) {
                    $wecha_id = $_GET['wecha_id'];

                    setcookie('wxd_openid', $wecha_id, time() + 31536000);
                    setcookie('dzp_openid', $wecha_id, time() + 31536000);
                    /*
                    setcookie('wxd_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');

                    setcookie('dzp_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');

                    */

                    $this->redirect('Wap/Vote/index', array('id' => $id, 'token' => $token));

                    exit;

                }
            } else {
                if (isset($_GET['wecha_id'])) {

                    if ($_GET['wecha_id'] != $_COOKIE['wxd_openid']) {

                        $wecha_id = $_GET['wecha_id'];

                        setcookie('wxd_openid', $wecha_id, time() + 31536000);
                        setcookie('dzp_openid', $wecha_id, time() + 31536000);
                        /*
                        setcookie('wxd_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');
                        setcookie('dzp_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');
                        */
                    }

                    $this->redirect('Wap/Vote/index', array('id' => $id, 'token' => $token));

                    exit;

                }

            }

        } else {

            $this->redirect('Wap/Vote/index', array('id' => $id, 'token' => $token));

            exit;

        }
        if ($id && empty($_GET['wecha_id'])) {

            $where_vote = array('token' => $token, 'id' => $id);

            $vote = M('Vote')->where($where_vote)->find();

            if (!$vote) {
                // $this->error("没有此活动", U('Home/Index/index'));
                return;
            }

            $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

            $user = M('Users')->where(array('id' => $token_open))->find();

            $vote_data['check'] = $vote['check'] + 1;

            M('Vote')->where($where_vote)->save($vote_data);

            $check = $vote['check'];

            if (!$check) {
                $check = 0;
            }

            $check = $check + $vote['xncheck'];

            if ($vote['start_time'] < time() && $vote['over_time'] > time()) {

                $istime = 1;

            } else {

                $istime = 0;

            }

            if ($_COOKIE['wxd_openid']) {

                $where_fusers['vid'] = $id;

                $where_fusers['status'] = array('gt', '0');

                $where_fusers['wechat_id'] = $_COOKIE['wxd_openid'];

                $vote_item = M('Vote_item')->where($where_fusers)->find();

                if ($vote_item) {

                    $ishavezp = 1;

                    $havezpid = $vote_item['id'];

                }

            }

            $vote_item_where['vid'] = $id;

            $vote_item_where['status'] = array('gt', '0');

            $vote_item_order = array('rank' => 'asc', 'id' => 'desc');

            $tpl = M('Vote_item')->where("vid={$id}")->sum('vcount') + M('Vote_item')->where("vid={$id}")->sum('dcount');

            if (empty($tpl)) {
                $tpl = 0;
            }

            $tpl = $tpl + $vote['xntps'];

            $rc = M('Vote_item')->where($vote_item_where)->count();

            if (empty($rc)) {
                $rc = 0;
            }

            $rc = $rc + $vote['xnbms'];

            import('@.ORG.Ppage');

            $page = $_GET['page'];

            $vote_items = M('Vote_item')->where($vote_item_where)->select();

            $vote_item_count = count($vote_items);

            $myzps = $user['myzps'];

            $url = C('site_url') . '/index.php?g=Wap&m=Vote&a=index&token=' . $token . '&id=' . $id . '&page={page}';

            $page_c = new PageClass($vote_item_count, $myzps, $page, $url);

            $page_limit = $page_c->page_limit;

            $myde_size = $page_c->myde_size;

            $zuopins = M('Vote_item')->where($vote_item_where)->order($vote_item_order)->limit($page_limit, $myde_size)->select();

            $page_string = $page_c->myde_writewx();

            $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

            if (count($guanggaos) > 1) {
                $ggduotu = 1;
            } else {
                $ggduotu = 0;
            }

            $wx = M('diymen_set')->where(array('token' => $token))->find();

            import('@.ORG.Jssdk');

            $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

            $signPackage = $jssdk->GetSignPackage();

            $problem_conf = M('Problem_conf')->find();

            if ($problem_conf['is_open'] == 1) {

                $this->assign('answer_num', $problem_conf['answer_num']);

                $problem_count = M('Problem')->count();

                if ($problem_count > $problem_conf['question_num']) {
                    $limt_p = rand(0, $problem_count - $problem_conf['question_num']);
                } else {
                    $limt_p = 0;
                }

                $problems = M('Problem')->limit($limt_p, $problem_conf['question_num'])->select();

                foreach ($problems as $key => $value) {
                    $answers = M('Answer')->where('pid=' . $value['id'])->select();
                    $problems[$key]['answers'] = $answers;
                }

                $this->assign('problems', $problems);
                $this->assign('problem_conf', $problem_conf);
            }

            $this->assign('signPackage', $signPackage);

            $this->assign('ggpic', $guanggaos);

            $this->assign('ggduotu', $ggduotu);

            $this->assign('page_string', $page_string);

            $this->assign('vote', $vote);

            $this->assign('zuopins', $zuopins);

            $this->assign('istime', $istime);

            $this->assign('tpl', $tpl);

            $this->assign('rc', $rc);

            $this->assign('check', $check);

            $this->assign('ishavezp', $ishavezp);

            $this->assign('user', $user);

            $this->assign('token', $token);

            $this->assign('havezpid', $havezpid);

            $this->assign('id', $id);

            $this->assign('page', $page);

            $this->display('index$tp1');

        }

    }

    public function rank()
    {

        $id = $_GET['id'];

        $token = $_GET['token'];

        if ($id) {

            $where_vote = array('token' => $token, 'id' => $id);

            $vote = M('Vote')->where($where_vote)->find();

            if (!$vote) {
                // $this->error("没有此活动", U('Home/Index/index'));
                return;
            }

            $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

            $user = M('Users')->where(array('id' => $token_open))->find();

            $vote_data['check'] = $vote['check'] + 1;

            M('Vote')->where($where_vote)->save($vote_data);

            $check = $vote['check'];

            if (!$check) {
                $check = 0;
            }

            $check = $check + $vote['xncheck'];

            if ($vote['statdate'] < time() && $vote['enddate'] > time()) {

                $istime = 1;

            } else {

                $istime = 0;

            }

            if ($_COOKIE['wxd_openid']) {

                $where_fusers['vid'] = $id;

                $where_fusers['status'] = array('gt', '0');

                $where_fusers['wechat_id'] = $_COOKIE['wxd_openid'];

                $vote_item = M('Vote_item')->where($where_fusers)->find();

                if ($vote_item) {

                    $ishavezp = 1;

                    $havezpid = $vote_item['id'];

                }

            }

            $vote_item_where['vid'] = $id;

            $vote_item_where['status'] = array('gt', '0');

            $vote_item_order = array('vcount' => 'desc');

            $tpl = M('Vote_item')->where("vid={$id}")->sum('vcount') + M('Vote_item')->where("vid={$id}")->sum('dcount');

            if (empty($tpl)) {
                $tpl = 0;
            }

            $tpl = $tpl + $vote['xntps'];

            $rc = M('Vote_item')->where($vote_item_where)->count();

            if (empty($rc)) {
                $rc = 0;
            }

            $rc = $rc + $vote['xnbms'];

            import('@.ORG.Ppage');

            $page = $_GET['page'];

            $vote_items = M('Vote_item')->where($vote_item_where)->select();

            $vote_item_count = count($vote_items);

            $myzps = $user['myzps'];

            $url = C('site_url') . '/index.php?g=Wap&m=Vote&a=rank&token=' . $token . '&id=' . $id . '&page={page}';

            $page_c = new PageClass($vote_item_count, $myzps, $page, $url);

            $page_limit = $page_c->page_limit;

            $myde_size = $page_c->myde_size;

            $zuopins = M('Vote_item')->where($vote_item_where)->order($vote_item_order)->limit($page_limit, $myde_size)->select();

            $page_string = $page_c->myde_writewx();

            $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

            if (count($guanggaos) > 1) {
                $ggduotu = 1;
            } else {
                $ggduotu = 0;
            }

            $wx = M('diymen_set')->where(array('token' => $token))->find();

            import('@.ORG.Jssdk');

            $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

            $signPackage = $jssdk->GetSignPackage();

            $this->assign('signPackage', $signPackage);

            $this->assign('ggpic', $guanggaos);

            $this->assign('ggduotu', $ggduotu);

            $this->assign('page_string', $page_string);

            $this->assign('vote', $vote);

            $this->assign('zuopins', $zuopins);

            $this->assign('istime', $istime);

            $this->assign('tpl', $tpl);

            $this->assign('rc', $rc);

            $this->assign('check', $check);

            $this->assign('ishavezp', $ishavezp);

            $this->assign('user', $user);

            $this->assign('token', $token);

            $this->assign('havezpid', $havezpid);

            $this->assign('id', $id);

            $this->assign('page', $page);

            $this->display('rank$tp1');

        }

    }

    public function top()
    {

        $id = $_GET['id'];

        $token = $_GET['token'];

        if ($id) {

            $where_vote = array('token' => $token, 'id' => $id);

            $vote = M('Vote')->where($where_vote)->find();

            if (!$vote) {
                // $this->error("没有此活动", U('Home/Index/index'));
                return;
            }

            $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

            $user = M('Users')->where(array('id' => $token_open))->find();

            $check = $vote['check'];

            if (!$check) {
                $check = 0;
            }

            $check = $check + $vote['xncheck'];

            if ($vote['start_time'] < time() && $vote['over_time'] > time()) {

                $istime = 1;

            } else {

                $istime = 0;

            }

            if ($_COOKIE['wxd_openid']) {

                $where_fusers['vid'] = $id;

                $where_fusers['status'] = array('gt', '0');

                $where_fusers['wechat_id'] = $_COOKIE['wxd_openid'];

                $vote_item = M('Vote_item')->where($where_fusers)->find();

                if ($vote_item) {

                    $ishavezp = 1;

                    $havezpid = $vote_item['id'];

                }

            }

            $vote_item_where['vid'] = $id;

            $vote_item_where['status'] = array('gt', '0');

            $vote_item_order = array('vcount' => 'desc');

            $tpl = M('Vote_item')->where("vid={$id}")->sum('vcount') + M('Vote_item')->where("vid={$id}")->sum('dcount');

            if (empty($tpl)) {
                $tpl = 0;
            }

            $tpl = $tpl + $vote['xntps'];

            $rc = M('Vote_item')->where($vote_item_where)->count();

            if (empty($rc)) {
                $rc = 0;
            }

            $rc = $rc + $vote['xnbms'];

            $IIIII11II1Il = M('Vote_item')->where($vote_item_where)->order($vote_item_order)->limit(0, 300)->select();

            $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

            if (count($guanggaos) > 1) {
                $ggduotu = 1;
            } else {
                $ggduotu = 0;
            }

            $wx = M('diymen_set')->where(array('token' => $token))->find();

            import('@.ORG.Jssdk');

            $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

            $signPackage = $jssdk->GetSignPackage();

            $this->assign('signPackage', $signPackage);

            $this->assign('ggpic', $guanggaos);

            $this->assign('ggduotu', $ggduotu);

            $this->assign('page_string', $page_string);

            $this->assign('vote', $vote);

            $this->assign('phlist', $IIIII11II1Il);

            $this->assign('istime', $istime);

            $this->assign('tpl', $tpl);

            $this->assign('rc', $rc);

            $this->assign('check', $check);

            $this->assign('ishavezp', $ishavezp);

            $this->assign('user', $user);

            $this->assign('token', $token);

            $this->assign('havezpid', $havezpid);

            $this->assign('id', $id);

            $this->display('top$tp1');

        }

    }

    public function ticket()
    {

        if (IS_POST) {

            $zid = $_POST['zid'];

            $id = $_POST['vid'];

            $token = $_POST['token'];

            if ($_COOKIE['wxd_openid']) {

                $wecha_id = $_COOKIE['wxd_openid'];

                $fuser = M('fusers')->where("openid='{$wecha_id}'")->find();

                if ($fuser && $fuser['is_gz'] == 1) {

                    $vote_item = M('Vote_item')->where(array('id' => $zid))->find();

                    if ($vote_item['status'] != 1) {

                        $return_status['status'] = 107;

                    } else {

                        $where_vote = array('token' => $token, 'id' => $id);

                        $vote = M('Vote')->where($where_vote)->find();

                        $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

                        $user = M('Users')->where(array('id' => $token_open))->find();

                        if ($vote['statdate'] > time()) {

                            $return_status['status'] = 103;

                        } elseif ($vote['enddate'] < time()) {

                            $return_status['status'] = 104;

                        } elseif (($vote['start_time'] < time()) && ($vote['over_time'] > time()) && $vote['btcdxz'] && ($vote_item['vcount'] >= $vote['btcdxz'])) {

                            $return_status['status'] = 120;

                        } else {

                            //刷票警告
                            if ($user['spxz']) {

                                $jgfen = $user['jgfen'];

                                $jgpiao = $user['jgpiao'];

                                $jgfen = time() - $jgfen * 60;

                                $where_vote_record['vid'] = $id;

                                $where_vote_record['item_id'] = $zid;

                                $where_vote_record['touch_time'] = array('gt', $jgfen);

                                $vote_record_count = M('vote_record')->where($where_vote_record)->count();

                                if ($vote_record_count > $jgpiao) {

//$IIIII11II111=M('vote_item')->where(array('id'=>$zid))->getField('wechat_id');

//                                    if (!empty($vote_item['wechat_id'])) {
//
//                                        $IIIIIIlI1Ill = '警告信息发送';
//
//                                        $this->sendtext($token, $IIIII11II111, $user['jgtext']);
//
//                                    }

                                    echo '107';

                                    exit;

                                }

                                $sdfen = $user['sdfen'];

                                $sdpiao = $user['sdpiao'];

                                $sdfen = time() - $sdfen * 60;

                                $where_vote_record['vid'] = $id;

                                $where_vote_record['item_id'] = $zid;

                                $where_vote_record['touch_time'] = array('gt', $sdfen);

                                $vote_record_count = M('vote_record')->where($where_vote_record)->count();

                                if ($vote_record_count > $sdpiao) {

//                                    $IIIII11II111 = M('vote_item')->where(array('id' => $zid))->getField('wechat_id');
//
//                                    if ($IIIII11II111) {
//
//                                        $IIIIIIlI1Ill = '锁定信息发送';
//
//                                        $this->sendtext($token, $IIIII11II111, $user['sdtext']);
//
//                                        M('vote_item')->where(array('id' => $zid))->save(array('status' => 2));
//
//                                    }

                                    echo '107';

                                    exit;

                                }

                            }

                            $ip = $this->GetIp();

                            $url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" . $ip;

                            $ipInfo = json_decode($this->api_notice_increment($url));

                            //限制区域投票
                            if ($user['xzlx'] > 0 && !empty($user['area'])) {

                                if ($ipInfo) {

                                    if ($user['xzlx'] == 1) {

                                        $province = $ipInfo->province;

                                    } elseif ($user['xzlx'] == 2) {

                                        $province = $ipInfo->city;

                                    }

                                    if (strpos($user['area'], $province) === false) {

                                        $limit_area = 0;

                                    } else {

                                        $limit_area = 1;

                                    }

                                } else {
                                    $limit_area = 1;
                                }

                            } else {

                                $limit_area = 1;

                            }

                            if ($limit_area == 1) {

                                $date = date('Y-m-d', time());

                                if ($user['tpxzmos'] == 0) {//按活动期为单位限制
                                    $tpxzmos = 1111111111;
                                } else {//按天为单位限制
                                    $tpxzmos = strtotime($date);
                                }

                                $where_fusers['touch_time'] = array('gt', $tpxzmos);

                                $where_fusers['wecha_id'] = $wecha_id;

                                $where_fusers['vid'] = $id;

                                //投票数量
                                $vote_record = M('vote_record')->where($where_fusers)->count();

                                /*if ($vote['ipnubs'] > 0) {

                                    $where_vote_item['touch_time'] = array('gt', $tpxzmos);

                                    $where_vote_item['vid'] = $id;

                                    $where_vote_item['ip'] = $ip;

                                    $vote_item = M('vote_record')->where($where_vote_item)->count();

                                    if ($vote_item < $vote['ipnubs']) {

                                        if ($vote_record < $vote['tpnub']) {

                                            if ($user['xz1p']) {

                                                $vote_record_where['touch_time'] = array('gt', $tpxzmos);

                                                $vote_record_where['vid'] = $id;

                                                $vote_record_where['item_id'] = $zid;

                                                $vote_record_where['wecha_id'] = $wecha_id;

                                                if (M('vote_record')->where($vote_record_where)->find()) {
                                                    $IIIIIIllII1I = 0;
                                                } else {
                                                    $IIIIIIllII1I = 1;
                                                }

                                            } else {

                                                $IIIIIIllII1I = 1;

                                            }

                                            if ($IIIIIIllII1I == 1) {

                                                $vote_record_data['item_id'] = $zid;

                                                $vote_record_data['vid'] = $id;

                                                $vote_record_data['wecha_id'] = $wecha_id;

                                                $vote_record_data['touch_time'] = time();

                                                $vote_record_data['token'] = $token;

                                                $vote_record_data['touched'] = 1;

                                                $vote_record_data['ip'] = $ip;

                                                $vote_record_data['area'] = $ipInfo->province . $ipInfo->city;

                                                if (M('vote_record')->add($vote_record_data)) {

                                                    $return_status['status'] = 108;

                                                    $IIIIIIllII11['vcount'] = $vote_item['vcount'] + 1;

                                                    M('vote_item')->where(array('id' => $zid))->save($IIIIIIllII11);

                                                    if ($user['tpjl']) {

                                                        $IIIIIIllIlII = M('fusers')->where("openid='{$wecha_id}'")->getField('jfnum');

                                                        M('fusers')->where("openid='{$wecha_id}'")->save(array('jfnum' => $IIIIIIllIlII + $user['tpjlnum']));

                                                    }
                                                    if (!empty($vote_item['wechat_id']) && !empty($vote['is_sendsms']) && !empty($vote['sms_content'])) {
                                                        $where_query = array('vid' => $vote_item['vid'], 'vcount' => array('gt', $IIIIIIllII11['vcount']));
                                                        $vemaxrank = M('vote_item')->where($where_query)->order('vcount desc')->max('vcount') - $IIIIIIllII11['vcount'];
                                                        $veminrank = M('vote_item')->where($where_query)->order('vcount desc')->min('vcount') - $IIIIIIllII11['vcount'];
                                                        $myrank = M('vote_item')->where($where_query)->count('id');
                                                        if ($vemaxrank < 1) $vemaxrank = 0;
                                                        if ($veminrank < 1) $veminrank = 0;
                                                        $myrank += 1;
                                                        $pares = array('frend' => $fuser['nickname'], 'vcount' => $IIIIIIllII11['vcount'], 'num' => $myrank, 'diffmaxcount' => $vemaxrank, 'diffmincount' => $veminrank, 'url' => 'http://' . $_SERVER['SERVER_NAME'] . '/index.php?g=Wap&m=Vote&a=detail&token=' . $token . '&id=' . $vote_item['vid'] . '&zid=' . $zid);
                                                        $this->prasecontent($vote['sms_content'], $pares);
                                                        $this->sendtext($token, $vote_item['wechat_id'], $vote['sms_content']);
                                                    }
                                                } else {

                                                    $return_status['status'] = 107;

                                                }

                                            } else {

                                                $return_status['status'] = 109;

                                            }

                                        } else {

                                            $return_status['status'] = 106;

                                        }

                                    } else {

                                        $return_status['status'] = 105;

                                    }

                                } else { */

                                if ($vote_record < $vote['tpnub']) {

                                    //每个微信号同作品限1票
                                    if ($user['xz1p']) {

                                        $vote_record_where['touch_time'] = array('gt', $tpxzmos);

                                        $vote_record_where['vid'] = $id;//活动id

                                        $vote_record_where['item_id'] = $zid;//作品id

                                        $vote_record_where['wecha_id'] = $wecha_id;

                                        if (M('vote_record')->where($vote_record_where)->find()) {
                                            $xz1p = 0;
                                        } else {
                                            $xz1p = 1;
                                        }

                                    } else {

                                        $xz1p = 1;

                                    }

                                    //答题投票是否开启
                                    $problem_conf = M('Problem_conf')->find();
                                    $answer_num = $_POST['answer_num'];
                                    if($answer_num < $problem_conf['answer_num']){
                                        if ($problem_conf['is_open'] == 1 && $vote_record > 0 ) {
                                            echo 999;
                                            exit;
                                        }
                                    }

                                    //每个微信号同作品限1票关闭状态
                                    if ($xz1p == 1) {

                                        $vote_record_data['item_id'] = $zid;

                                        $vote_record_data['vid'] = $id;

                                        $vote_record_data['wecha_id'] = $wecha_id;

                                        $vote_record_data['touch_time'] = time();

                                        $vote_record_data['token'] = $token;

                                        $vote_record_data['touched'] = 1;

                                        $vote_record_data['ip'] = $ip;

                                        $vote_record_data['area'] = $ipInfo->province . $ipInfo->city;

                                        if (M('vote_record')->add($vote_record_data)) {

                                            $return_status['status'] = 108;

                                            $IIIIIIllII11['vcount'] = $vote_item['vcount'] + 1;

                                            M('vote_item')->where(array('id' => $zid))->save($IIIIIIllII11);

                                            if ($user['tpjl']) {

                                                $IIIIIIllIlII = M('fusers')->where("openid='{$wecha_id}'")->getField('jfnum');

                                                M('fusers')->where("openid='{$wecha_id}'")->save(array('jfnum' => $IIIIIIllIlII + $user['tpjlnum']));

                                            }

                                            if (!empty($vote_item['wechat_id']) && !empty($vote['is_sendsms']) && !empty($vote['sms_content'])) {
                                                $where_query = array('vid' => $vote_item['vid'], 'vcount' => array('gt', $IIIIIIllII11['vcount']));
                                                $vemaxrank = M('vote_item')->where($where_query)->order('vcount desc')->max('vcount') - $IIIIIIllII11['vcount'];
                                                $veminrank = M('vote_item')->where($where_query)->order('vcount desc')->min('vcount') - $IIIIIIllII11['vcount'];
                                                $myrank = M('vote_item')->where($where_query)->count('id');
                                                if ($vemaxrank < 1) $vemaxrank = 0;
                                                if ($veminrank < 1) $veminrank = 0;
                                                $myrank += 1;
                                                $pares = array('frend' => $fuser['nickname'], 'vcount' => $IIIIIIllII11['vcount'], 'num' => $myrank, 'diffmaxcount' => $vemaxrank, 'diffmincount' => $veminrank, 'url' => 'http://' . $_SERVER['SERVER_NAME'] . '/index.php?g=Wap&m=Vote&a=detail&token=' . $token . '&id=' . $vote_item['vid'] . '&zid=' . $zid);
                                                $this->prasecontent($vote['sms_content'], $pares);
                                                $this->sendtext($token, $vote_item['wechat_id'], $vote['sms_content']);
                                            }
                                        } else {

                                            $return_status['status'] = 107;

                                        }

                                    } else {

                                        $return_status['status'] = 109;

                                    }

                                } else {

                                    $return_status['status'] = 106;

                                }

                                /* }*/

                            } else {

                                $return_status['status'] = 110;

                            }

                        }

                    }

                } else {

                    $return_status['status'] = 102;

                }

            } else {

                $return_status['status'] = 102;

            }

            echo $return_status['status'];

        }

    }

    public function signup()
    {

        if (IS_POST) {

            $id = $_POST['id'];

            $token = $_POST['token'];

            $where_vote = array('token' => $token, 'id' => $id);

            $vote = M('Vote')->where($where_vote)->find();

            if (!$vote) {
                // $this->error("没有此活动", U('Home/Index/index'));
                return;
            }

            $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

            $user = M('Users')->where(array('id' => $token_open))->find();

            if ($vote) {

                if ($vote['start_time'] < time() && $vote['over_time'] > time()) {

                    if ($_COOKIE['wxd_openid']) {

                        $where_fusers['openid'] = $_COOKIE['wxd_openid'];

                        $fuser = M('fusers')->where($where_fusers)->find();

                        if ($fuser) {

                            if ($fuser['is_gz'] == 1) {

                                $where_vote_item['wechat_id'] = $_COOKIE['wxd_openid'];

                                $where_vote_item['vid'] = $id;

                                $vote_item = M('vote_item')->where($where_vote_item)->find();

                                if ($vote_item) {

                                    $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $vote_item['id']));

                                } else {

                                    $vote_item_data = array();

                                    $vote_item_data['vid'] = $id;

                                    $vote_item_data['wechat_id'] = $_COOKIE['wxd_openid'];

                                    $vote_item_data['item'] = strip_tags($_POST['zpname']);

                                    $vote_item_data['tourl'] = $_POST['telphone'];
                                    $vote_item_data['wechat'] = $_POST['wechat'];

                                    $vote_item_data['intro'] = strip_tags($_POST['content']);

                                    $vote_item_data['addtime'] = time();

                                    if ($vote['is_sh'] == 0) {

                                        $vote_item_data['status'] = 1;

                                    } else {

                                        $vote_item_data['status'] = 0;

                                    }

                                    if (!empty($_POST['fileup'])) {

                                        foreach ($_POST['fileup'] as $key => $value) {

                                            if ($key == 0) {

                                                $IIIII11IllIl = $this->savepic($value, $id);

                                                if ($user['tuchuang']) {

                                                    $vote_item_data['startpicurl'] = $this->tcupload($IIIII11IllIl, $user['tuaccesskey'], $user['tusecretkey'], $user['tupicid']);

                                                } else {

                                                    $vote_item_data['startpicurl'] = $IIIII11IllIl;

                                                }

                                            }

                                            if ($key == 1) {

                                                $IIIII11IllI1 = $this->savepic($value, $id);

                                                if ($user['tuchuang']) {

                                                    $vote_item_data['startpicurl2'] = $this->tcupload($IIIII11IllI1, $user['tuaccesskey'], $user['tusecretkey'], $user['tupicid']);

                                                } else {

                                                    $vote_item_data['startpicurl2'] = $IIIII11IllI1;

                                                }

                                            }

                                            if ($key == 2) {

                                                $IIIII11IlllI = $this->savepic($value, $id);

                                                if ($user['tuchuang']) {

                                                    $vote_item_data['startpicurl3'] = $this->tcupload($IIIII11IlllI, $user['tuaccesskey'], $user['tusecretkey'], $user['tupicid']);

                                                } else {

                                                    $vote_item_data['startpicurl3'] = $IIIII11IlllI;

                                                }

                                            }

                                            if ($key == 3) {

                                                $IIIII11Illll = $this->savepic($value, $id);

                                                if ($user['tuchuang']) {

                                                    $vote_item_data['startpicurl4'] = $this->tcupload($IIIII11Illll, $user['tuaccesskey'], $user['tusecretkey'], $user['tupicid']);

                                                } else {

                                                    $vote_item_data['startpicurl4'] = $IIIII11Illll;

                                                }

                                            }

                                            if ($key == 4) {

                                                $IIIII11Illl1 = $this->savepic($value, $id);

                                                if ($user['tuchuang']) {

                                                    $vote_item_data['startpicurl5'] = $this->tcupload($IIIII11Illl1, $user['tuaccesskey'], $user['tusecretkey'], $user['tupicid']);

                                                } else {

                                                    $vote_item_data['startpicurl5'] = $IIIII11Illl1;

                                                }

                                            }

                                        }

                                    }

                                    $IIIII11Ill1I = M('vote_item')->add($vote_item_data);

                                    if (!$fuser['telphone']) {

                                        $IIIII11Ill1l = array(

                                            'telphone' => addslashes($_POST['telphone']),

                                        );

                                        M('fusers')->where(array('id' => $fuser['id']))->save($IIIII11Ill1l);

                                    }

                                }

                                $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $IIIII11Ill1I));

                            } else {

                                $this->redirect('Wap/Vote/index', array('id' => $id, 'token' => $token));

                            }

                        }

                    }

                }

            }

        } else {

            $id = $_GET['id'];

            $token = $_GET['token'];

            if ($id) {

                $where_vote = array('token' => $token, 'id' => $id);

                $vote = M('Vote')->where($where_vote)->find();

                if (!$vote) {
                    // $this->error("没有此活动", U('Home/Index/index'));
                    return ;
                }

                $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

                $user = M('Users')->where(array('id' => $token_open))->find();

                if (empty($user['picnum'])) {

                    $IIIII11Il1II = 0;

                    $IIIII11Il1Il = 1;

                } else {

                    $IIIII11Il1II = $user['picnum'] - 1;

                    $IIIII11Il1Il = $user['picnum'];

                }

                $check = $vote['check'];

                if (!$check) {
                    $check = 0;
                }

                $check = $check + $vote['xncheck'];

                if ($vote['start_time'] > time()) {

                    $bmzt = 1;

                } elseif ($vote['over_time'] < time()) {

                    $bmzt = 2;

                } else {

                    if ($_COOKIE['wxd_openid']) {

                        $where_fusers['openid'] = $_COOKIE['wxd_openid'];

                        $fuser = M('fusers')->where($where_fusers)->find();

                        if ($fuser) {

                            if ($fuser['is_gz'] == 1) {

                                $where_vote_item['wechat_id'] = $_COOKIE['wxd_openid'];

                                $where_vote_item['vid'] = $id;

                                $vote_item = M('vote_item')->where($where_vote_item)->find();

                                if ($vote_item) {

                                    $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $vote_item['id']));

                                } else {

                                    $bmzt = 4;

                                }

                            } else {

                                $bmzt = 3;

                            }

                        } else {

                            $bmzt = 3;

                        }

                    } else {

                        $bmzt = 3;

                    }

                }

                $vote_item_where['vid'] = $id;

                $vote_item_where['status'] = array('gt', '0');

                $vote_item_order = array('vcount' => 'desc');

                $tpl = M('Vote_item')->where("vid={$id}")->sum('vcount') + M('Vote_item')->where("vid={$id}")->sum('dcount');

                if (empty($tpl)) {
                    $tpl = 0;
                }

                $tpl = $tpl + $vote['xntps'];

                $rc = M('Vote_item')->where($vote_item_where)->count();

                if (empty($rc)) {
                    $rc = 0;
                }

                $rc = $rc + $vote['xnbms'];

                $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

                if (count($guanggaos) > 1) {
                    $ggduotu = 1;
                } else {
                    $ggduotu = 0;
                }

                $wx = M('diymen_set')->where(array('token' => $token))->find();

                import('@.ORG.Jssdk');

                $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

                $signPackage = $jssdk->GetSignPackage();

                $this->assign('signPackage', $signPackage);

                $this->assign('ggpic', $guanggaos);

                $this->assign('ggduotu', $ggduotu);

                $this->assign('page_string', $page_string);

                $this->assign('vote', $vote);

                $this->assign('istime', $istime);

                $this->assign('tpl', $tpl);

                $this->assign('rc', $rc);

                $this->assign('check', $check);

                $this->assign('ishavezp', $ishavezp);

                $this->assign('user', $user);

                $this->assign('token', $token);

                $this->assign('havezpid', $havezpid);

                $this->assign('id', $id);

                $this->assign('xzpic', $IIIII11Il1II);

                $this->assign('picnum', $IIIII11Il1Il);

                $this->assign('bmzt', $bmzt);

                $this->display('signup$tp1');

            }

        }

    }

    public function search()
    {

        if (IS_POST) {

            $id = $_POST['id'];

            $token = $_POST['token'];

            if ($_POST['keyword'] != null && is_numeric($_POST['keyword'])) {

                $IIIII1I11Ill = intval(htmlspecialchars($_POST['keyword']));

                $IIIIII1Il1I1 = M('Vote_item')->where(array('id' => $IIIII1I11Ill))->find();

                if ($IIIIII1Il1I1) {

                    $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $IIIII1I11Ill));

                } else {

                    $where_vote_item = U('Wap/Vote/index', array('id' => $id, 'token' => $token));

                    echo "<script> alert('无此ID选手');location.href='" . $where_vote_item . "';</script>";

                }

            } else {

                $vote_item_order = array('vcount' => 'desc');

                $IIIII11Il1lI['item'] = array('like', '%' . htmlspecialchars($_POST['keyword']) . '%');

                $zuopins = M('Vote_item')->where($IIIII11Il1lI)->order($vote_item_order)->select();

                if ($zuopins) {

                    $where_vote = array('token' => $token, 'id' => $id);

                    $vote = M('Vote')->where($where_vote)->find();

                    $token_open = M('token_open')->where(array('token' => $token))->getField('uid');

                    $user = M('Users')->where(array('id' => $token_open))->find();

                    $check = $vote['check'];

                    if (!$check) {
                        $check = 0;
                    }

                    $check = $check + $vote['xncheck'];

                    if ($vote['start_time'] < time() && $vote['over_time'] > time()) {

                        $istime = 1;

                    } else {

                        $istime = 0;

                    }

                    if ($_COOKIE['wxd_openid']) {

                        $where_fusers['vid'] = $id;

                        $where_fusers['status'] = array('gt', '0');

                        $where_fusers['wechat_id'] = $_COOKIE['wxd_openid'];

                        $vote_item = M('Vote_item')->where($where_fusers)->find();

                        if ($vote_item) {

                            $ishavezp = 1;

                            $havezpid = $vote_item['id'];

                        }

                    }

                    $vote_item_where['vid'] = $id;

                    $vote_item_where['status'] = array('gt', '0');

                    $tpl = M('Vote_item')->where("vid={$id}")->sum('vcount') + M('Vote_item')->where("vid={$id}")->sum('dcount');

                    if (empty($tpl)) {
                        $tpl = 0;
                    }

                    $tpl = $tpl + $vote['xntps'];

                    $rc = M('Vote_item')->where($vote_item_where)->count();

                    if (empty($rc)) {
                        $rc = 0;
                    }

                    $rc = $rc + $vote['xnbms'];

                    $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

                    if (count($guanggaos) > 1) {
                        $ggduotu = 1;
                    } else {
                        $ggduotu = 0;
                    }

                    $wx = M('diymen_set')->where(array('token' => $token))->find();

                    import('@.ORG.Jssdk');

                    $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

                    $signPackage = $jssdk->GetSignPackage();

                    $this->assign('signPackage', $signPackage);

                    $this->assign('ggpic', $guanggaos);

                    $this->assign('ggduotu', $ggduotu);

                    $this->assign('page_string', $page_string);

                    $this->assign('vote', $vote);

                    $this->assign('zuopins', $zuopins);

                    $this->assign('istime', $istime);

                    $this->assign('tpl', $tpl);

                    $this->assign('rc', $rc);

                    $this->assign('check', $check);

                    $this->assign('ishavezp', $ishavezp);

                    $this->assign('user', $user);

                    $this->assign('token', $token);

                    $this->assign('havezpid', $havezpid);

                    $this->assign('id', $id);

                    $this->display('search$tp1');

                } else {

                    $where_vote_item = U('Wap/Vote/index', array('id' => $id, 'token' => $token));

                    echo "<script> alert('无此选手');location.href='" . $where_vote_item . "';</script>";

                }

            }

        }

    }

    public function add_vote()
    {

        $token = $this->_post('token');

        $IIIIIlIIIll1 = $this->_post('wecha_id');

        $IIIIIIl1ll11 = $this->_post('tid');

        $IIIII11Il1l1 = rtrim($this->_post('chid'), ',');

        $IIIIIIlI111I = M('Vote_record');

        $IIIII11Il11I = M('vote')->where(array('id' => $IIIIIIl1ll11))->field('votelimit')->find();

        $where_vote = array('vid' => $IIIIIIl1ll11, 'wecha_id' => $IIIIIlIIIll1, 'token' => $token);

        $IIIII1lI1lI1 = $IIIIIIlI111I->where($where_vote)->select();

        $IIIII11Il11l = count($IIIII1lI1lI1, COUNT_NORMAL);

        if ($IIIII11Il11l >= (int)$IIIII11Il11I['votelimit'] || $IIIIIlIIIll1 == '') {

            $IIIIIIIlII1l = array('success' => 0);

            echo json_encode($IIIIIIIlII1l);

            exit;

        } else {

            $IIIII11Il111 = (int)$IIIII11Il11I['votelimit'] - (int)$IIIII11Il11l - 1;

            $return_status = array('item_id' => $IIIII11Il1l1, 'token' => $token, 'vid' => $IIIIIIl1ll11, 'wecha_id' => $IIIIIlIIIll1, 'touch_time' => time(), 'touched' => 1);

            $IIIIIIIIlI1I = $IIIIIIlI111I->add($return_status);

            $IIIIII1IIlIl['id'] = array('in', $IIIII11Il1l1);

            $IIIIIIlI111l = M('Vote_item');

            $IIIIIIlI111l->where($IIIIII1IIlIl)->setInc('vcount');

            $IIIIIIIlII1l = array('success' => 1, 'token' => $token, 'wecha_id' => $IIIIIlIIIll1, 'tid' => $IIIIIIl1ll11, 'chid' => $IIIII11Il1l1, 'arrpre' => $IIIII11I1III, 'vleft' => $IIIII11Il111);

            echo json_encode($IIIIIIIlII1l);

            exit;
        }

    }

    public function detail()
    {
        $id = $_GET['id'];

        $token = $_GET['token'];

        $zid = $_GET['zid'];

        $isappinstalled = $_GET['isappinstalled'];

        $from = $_GET['from'];

//if(!isset($from) &&!isset($isappinstalled)){

        if (empty($_COOKIE['wxd_openid'])) {

            if (isset($_GET['wecha_id'])) {

                $wecha_id = $_GET['wecha_id'];

                setcookie('wxd_openid', $wecha_id, time() + 31536000);
//setcookie('wxd_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');

                $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $zid));

                exit;

            }

        } else {

            if (isset($_GET['wecha_id'])) {

                if ($_GET['wecha_id'] != $_COOKIE['wxd_openid']) {

                    $wecha_id = $_GET['wecha_id'];

                    setcookie('wxd_openid', $wecha_id, time() + 31536000);
//setcookie('wxd_openid',$wecha_id,time()+31536000,'/','.m.nckyjy.com');

                }

                $this->redirect('Wap/Vote/detail', array('id' => $id, 'token' => $token, 'zid' => $zid));

                exit;

            }

        }

        /*}else{

        $this->redirect('Wap/Vote/index',array('id'=>$id,'token'=>$token));

        exit;

        }*/

        if (empty($_GET['wecha_id'])) {

            $IIIIIllIlIII = M('Vote');

            $where_vote = array('token' => $token, 'id' => $id);

            $vote = $IIIIIllIlIII->where($where_vote)->find();

            $IIIIIllIlIlI = M('Vote_item');

            $vote_item_where['id'] = $zid;

            $return_status = $IIIIIllIlIlI->where($vote_item_where)->find();

            $IIIII1lIl111['vcount'] = array('gt', $return_status['vcount']);

            $IIIII1lIl111['vid'] = $id;

            $IIIII11I1IIl = $IIIIIllIlIlI->where($IIIII1lIl111)->count();

            $IIIII11I1IIl += 1;

            $IIIIIllIlllI = M('Token_open');

            $IIIIIllIlll1 = $IIIIIllIlllI->where(array('token' => $token))->getField('uid');

            $user = M('Users')->where(array('id' => $IIIIIllIlll1))->find();

            if ($_COOKIE['wxd_openid']) {

                $where_fusers['vid'] = $id;

                $where_fusers['status'] = array('gt', '0');

                $where_fusers['wechat_id'] = $_COOKIE['wxd_openid'];

                $vote_item = M('Vote_item')->where($where_fusers)->find();

                if ($vote_item) {

                    $ishavezp = 1;

                    $havezpid = $vote_item['id'];

                }

            }

            $guanggaos = M('guanggao')->where("vid=" . $id)->order('id desc')->select();

            if (count($guanggaos) > 1) {
                $ggduotu = 1;
            } else {
                $ggduotu = 0;
            }

            $problem_conf = M('Problem_conf')->find();

            if ($problem_conf['is_open'] == 1) {

                $this->assign('answer_num', $problem_conf['answer_num']);

                $problem_count = M('Problem')->count();

                if ($problem_count > $problem_conf['question_num']) {
                    $limt_p = rand(0, $problem_count - $problem_conf['question_num']);
                } else {
                    $limt_p = 0;
                }

                $problems = M('Problem')->limit($limt_p, $problem_conf['question_num'])->select();

                foreach ($problems as $key => $value) {
                    $answers = M('Answer')->where('pid=' . $value['id'])->select();
                    $problems[$key]['answers'] = $answers;
                }

                $this->assign('problems', $problems);
                $this->assign('problem_conf', $problem_conf);
            }

            $wx = M('diymen_set')->where(array('token' => $token))->find();

            import('@.ORG.Jssdk');

            $jssdk = new JSSDK($wx['id'], $wx['appid'], $wx['appsecret']);

            $signPackage = $jssdk->GetSignPackage();

            $this->assign('signPackage', $signPackage);

            $this->assign('ggpic', $guanggaos);

            $this->assign('ggduotu', $ggduotu);

            $this->assign('zpinfo', $return_status);

            $this->assign('vote', $vote);

            $this->assign('mingci', $IIIII11I1IIl);

            $this->assign('ishavezp', $ishavezp);

            $this->assign('havezpid', $havezpid);

            $this->assign('user', $user);

            $this->assign('token', $token);

            $this->assign('havezpid', $havezpid);

            $this->assign('id', $id);

            $this->display('detail$tp1');

        }

    }

    public function vote()
    {

        $return_status['item_id'] = htmlspecialchars($this->_post('id'));

        $return_status['vid'] = htmlspecialchars($this->_post('vid'));

        $return_status['token'] = htmlspecialchars($this->_post('token'));

        $return_status['wecha_id'] = htmlspecialchars($this->_post('wecha_id'));

        $return_status['touch_time'] = time();

        $return_status['touched'] = 1;

        $vote_item_where['vid'] = $return_status['vid'];

        $vote_item_where['wecha_id'] = $return_status['wecha_id'];

        $IIIII11I1II1 = M('Vote_record');

        if ($IIIII11I1II1->where($vote_item_where)->find()) {

            $this->ajaxReturn('', '', 1, 'json');

        } else {

            $IIIII11I1II1->add($return_status);

            $IIIIII1IIlIl['id'] = array('in', $return_status['item_id']);

            $IIIIIIlI111l = M('Vote_item');

            $IIIIIIlI111l->where($IIIIII1IIlIl)->setInc('vcount');

            $this->ajaxReturn('', '', 2, 'json');

        }

    }

    public function add_item()
    {

        $key = $this->_get('key');

        $page = intval($this->_get('page'));

        $IIIIIIIII1I1 = $this->_get('id');

        $vote_item_where['vid'] = $IIIIIIIII1I1;

        $vote_item_where['status'] = 1;

        $IIIIIllII1ll = intval(6);

        $IIIII11I1Ill = $page * $IIIIIllII1ll;

        if ($key != '' && $key != NULL) {

            if (is_numeric($key)) {

                $vote_item_where['id'] = array('like', '%' . intval(htmlspecialchars($key)) . '%');

            } else {

                $vote_item_where['item'] = array('like', '%' . htmlspecialchars($key) . '%');

            }

        }

        $IIIIIllIlIlI = M('Vote_item')->where($vote_item_where)->order(array('rank' => 'asc', 'id' => 'desc'))->limit($IIIII11I1Ill, $IIIIIllII1ll)->select();

        $IIIII11I1Il1 = '';

        foreach ($IIIIIllIlIlI as $IIIIIIIllIll => $IIIIIIIlI11l) {

            $IIIII11I1Il1 = $IIIII11I1Il1 . "  

						<li><a href=\"/index.php?g=Wap&m=Vote&a=show&token=" . $_SESSION['token'] . "&id=" . $IIIIIIIlI11l['id'] . "&wecha_id=" . $_SESSION['wecha_id'] . "&tid=" . $IIIIIIIII1I1 . "\"><img src=\"" . $IIIIIIIlI11l['startpicurl'] . "\"></a>

						<p class=\"info\">" . $IIIIIIIlI11l['item'] . "<br>选手编号：<i class=\"vote_1\">" . $IIIIIIIlI11l['id'] . "</i><br>票数：<i class=\"vote_1\">" . $IIIIIIIlI11l['vcount'] . "</i><br></p>

						<p class=\"vote\"><a href=\"/index.php?g=Wap&m=Vote&a=show&token=" . $_SESSION['token'] . "&id=" . $IIIIIIIlI11l['id'] . "&wecha_id=" . $_SESSION['wecha_id'] . "&tid=" . $IIIIIIIII1I1 . "\">详细资料</a></p></li>";

        }

        echo $IIIII11I1Il1;

    }

    public function add_rank()
    {

        $page = intval($this->_get('page'));

        $IIIIIIIII1I1 = $this->_get('id');

        $vote_item_where['vid'] = $IIIIIIIII1I1;

        $vote_item_where['status'] = 1;

        $IIIIIllII1ll = intval(6);

        $IIIII11I1Ill = $page * $IIIIIllII1ll;

        $IIIIIllIlIlI = M('Vote_item')->where($vote_item_where)->order('vcount desc')->limit($IIIII11I1Ill, $IIIIIllII1ll)->select();

        $IIIII11I1Il1 = '';

        foreach ($IIIIIllIlIlI as $IIIIIIIllIll => $IIIIIIIlI11l) {

            $IIIII11I1Il1 = $IIIII11I1Il1 . "  <div class='pp'> 

						<a href=\"/index.php?g=Wap&m=Vote&a=show&token=" . $_SESSION['token'] . "&id=" . $IIIIIIIlI11l['id'] . "&wecha_id=" . $_SESSION['wecha_id'] . "&tid=" . $IIIIIIIII1I1 . "\">

						<img src=\"" . $IIIIIIIlI11l['startpicurl'] . "\">

						

						<div class=\"tit\">" . $IIIIIIIlI11l['id'] . "号 " . $IIIIIIIlI11l['item'] . "<br />人气：<b>" . $IIIIIIIlI11l['vcount'] . "</b></div>

					</div></a>";

        }

        echo $IIIII11I1Il1;

    }

    private function savepic($IIIII11I1I11, $id)
    {

        $IIIIIIlIll11 = date('Ymd');

        if (!file_exists(($_SERVER['DOCUMENT_ROOT'] . '/uploads')) || !is_dir(($_SERVER['DOCUMENT_ROOT'] . '/uploads'))) {

            mkdir($_SERVER['DOCUMENT_ROOT'] . '/uploads');

        }

        $IIIIIIlIl1Il = $_SERVER['DOCUMENT_ROOT'] . '/uploads/vote';

        if (!file_exists($IIIIIIlIl1Il) || !is_dir($IIIIIIlIl1Il)) {

            mkdir($IIIIIIlIl1Il);

        }

        $IIIII11I1lII = ($_SERVER['DOCUMENT_ROOT'] . '/uploads/vote/') . $id;

        if (!file_exists($IIIII11I1lII) || !is_dir($IIIII11I1lII)) {

            mkdir($IIIII11I1lII);

        }

        $IIIIIIlIl1Il = (($_SERVER['DOCUMENT_ROOT'] . '/uploads/vote/') . $id) . '/' . $IIIIIIlIll11;

        if (!file_exists($IIIIIIlIl1Il) || !is_dir($IIIIIIlIl1Il)) {

            mkdir($IIIIIIlIl1Il);

        }

        $IIIIIIlIl1I1 = ((date('YmdHis') . '_') . rand(10000, 99999)) . '.jpeg';

        $IIIII11I1lIl = (((('/uploads/vote/') . $id) . '/' . $IIIIIIlIll11) . '/') . $IIIIIIlIl1I1;

        $value = $_SERVER['DOCUMENT_ROOT'] . $IIIII11I1lIl;

        $url = 'http://' . $_SERVER['HTTP_HOST'] . $IIIII11I1lIl;

        $IIIII11I1lI1 = base64_decode($IIIII11I1I11);

        $IIIII11I1llI = file_put_contents($value, $IIIII11I1lI1);

        if ($IIIII11I1llI) {

            return $url;

        }

    }

    private function tcupload($value, $IIIII11I1ll1, $IIIII11I1l1I, $IIIII11I1l1l)
    {

        import('@.ORG.TieTuKu');

        $IIIII11I1l11 = new TTKClient($IIIII11I1ll1, $IIIII11I1l1I);

        $IIIIIII11l11 = $IIIII11I1l11->uploadFile($IIIII11I1l1l, $value);

        $IIIIIII11l11 = str_replace("{", "", $IIIIIII11l11);

        $IIIIIII11l11 = str_replace("}", "", $IIIIIII11l11);

        $IIIIIII11l11 = str_replace('"', "", $IIIIIII11l11);

        $IIIIII1l1l11 = explode(',', $IIIIIII11l11);

        $IIIII11I11II = str_replace('s_url:', "", $IIIIII1l1l11[7]);

        if ($IIIII11I11II) {

            return stripslashes($IIIII11I11II);

        } else {

            return NULL;

        }

    }

    private function GetIP()
    {

        $ip = false;

        if (!empty($_SERVER["HTTP_CLIENT_IP"])) {

            $ip = $_SERVER["HTTP_CLIENT_IP"];

        }

        if (!empty($_SERVER['HTTP_X_FORWARDED_FOR'])) {

            $IIIII11I11I1 = explode(", ", $_SERVER['HTTP_X_FORWARDED_FOR']);

            if (count($IIIII11I11I1) < 2) {

                $IIIII11I11I1 = explode(",", $_SERVER['HTTP_X_FORWARDED_FOR']);

            }

            if ($ip) {
                array_unshift($IIIII11I11I1, $ip);
                $ip = FALSE;
            }

            for ($IIIIIIIllI11 = 0; $IIIIIIIllI11 < count($IIIII11I11I1); $IIIIIIIllI11++) {

                if (!eregi("^(10|172\.16|192\.168)\.", $IIIII11I11I1[$IIIIIIIllI11])) {

                    $ip = $IIIII11I11I1[$IIIIIIIllI11];

                    break;

                }

            }

        }

        return ($ip ? $ip : $_SERVER['REMOTE_ADDR']);

    }

    public function hongbao()
    {
        $user_id = '';
        $vcount = 0;
        $vote_id = isset($_GET['id']) ? trim($_GET['id']) : '';
        $token_id = isset($_GET['token']) ? trim($_GET['token']) : '';
        if ($this->user_is_gz == 1) {
            $my_items = M('vote_item')->where(array('wechat_id' => trim($_COOKIE['wxd_openid'])))->getField('vcount');
            if ($my_items) {
                $user_id = $_COOKIE['wxd_openid'];
                $vcount = intval($my_items);
            }
        }
        $this->assign('hb_user_id', $user_id);
        $this->assign('hb_vcount', $vcount);
        $this->assign('token', $token_id);
        $this->assign('id', $vote_id);
        $this->display('hongbao');
    }

    protected function Set_Is_Gz()
    {
        if ($_COOKIE['wxd_openid']) {
            //echo $_COOKIE['wxd_openid'];
            $user = M('fusers')->where(array('openid' => trim($_COOKIE['wxd_openid'])))->find();
            if ($user && $user['is_gz'] == 1) $this->user_is_gz = 1;
        }
        $this->assign('user_is_gz', $this->user_is_gz);
    }
} ?>