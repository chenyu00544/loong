<?php

namespace App\Modules\Wechat\Controllers;

use App\Modules\Base\Controllers\BackendController;
use App\Extensions\Wxapp;

class WxappController extends BackendController
{
    public function __construct()
    {
        parent::__construct();
        L(require(MODULE_PATH . 'Language/' . C('shop.lang') . '/wechat.php'));
        $this->assign('lang', array_change_key_case(L()));

        // 获取配置信息
        $this->get_config();
        // 初始化
        $this->init_params();
    }

    /**
     * 处理公共参数
     */
    private function init_params()
    {

    }

    /**
     * 小程序设置
     */
    public function actionIndex()
    {
        // 权限
        $this->admin_priv('wxapp_config');

        // 提交处理
        if (IS_POST) {
            $id = I('id', 0, 'intval');
            $data = I('post.data', '', ['htmlspecialchars', 'trim']);
            // 验证数据
            if (empty($data['wx_appid'])) {
                $this->message(L('must_appid'), null, 2);
            }
            if (empty($data['wx_appsecret'])) {
                $this->message(L('must_appsecret'), null, 2);
            }
            if (empty($data['wx_mch_id'])) {
                $this->message(L('must_mch_id'), null, 2);
            }
            if (empty($data['wx_mch_key'])) {
                $this->message(L('must_mch_key'), null, 2);
            }
            if (empty($data['token_secret'])) {
                $this->message(L('must_token_secret'), null, 2);
            }
            // 更新数据
            if (!empty($id)) {
                // 如果 wx_appsecret 包含 * 跳过不保存数据库
                if (strpos($data['wx_appsecret'], '*') == true) {
                    unset($data['wx_appsecret']);
                }
                dao('wxapp_config')->data($data)->where(['id' => $id])->save();
            } else {
                $data['add_time'] = gmtime();
                dao('wxapp_config')->data($data)->add();
            }
            $this->message(L('wechat_editor') . L('success'), url('index'));
        }

        // 查询
        $info = dao('wxapp_config')->find();
        if (!empty($info)) {
            // 用*替换字符显示
            $info['wx_appsecret'] = string_to_star($info['wx_appsecret']);
        }

        $this->assign('data', $info);
        $this->display();
    }

    /**
     * 新增小程序
     */
    public function actionAppend()
    {

    }

    /**
     * 删除小程序
     */
    public function actionDelete()
    {
        $condition['id'] = input('id', 0, 'intval');
        dao('wxapp_config')->where($condition)->delete();
    }


    /**
     * 模板消息
     */
    public function actionTemplate()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');
        $where['is_zd'] = 0;

        $list = $this->model->table('wxapp_template')->where($where)->order('id asc')->select();
        if ($list) {
            foreach ($list as $key => $val) {
                $list[$key]['add_time'] = local_date('Y-m-d H:i', $val['add_time']);
            }
        }

        $this->assign('list', $list);
        $this->display();
    }


    public function actionTemplateLogMy()
    {
        //echo "actionTemplateMy";
        // 模板消息权限
        $this->admin_priv('template');////////////这里不改，其实是send_template 这个是自己加的

        //wechat_id  这里就固定为1  存的时候也是1
        //$condition['wechat_id'] = '1';// $this->wechat_id;

        $sql =
            "
			select a.data,a.send_time,b.user_id,b.nick_name from {pre}wxapp_template_log_my as a left join {pre}users as b on a.user_id = b.user_id order by a.send_time desc limit 0,20 
		";

        $list = $this->model->query($sql);//$this->model->table('wechat_template_log_my')->order(' status desc , id asc')->select();

        //print_r($list);

        $this->assign('list', $list);

        $this->display();
    }


    public function actionTemplateSendTest()
    {
        exit;

        $allUserSql =
            "
					select   a.openid,a.nickname,a.ect_uid,f.fid,f.`from_id` from {pre}wechat_user as a left join {pre}small_app_fromid as f  on a.`openid` = f.`wxid` 
					where f.`is_use` = 0 and f.`is_overdue` = 0 GROUP BY a.`ect_uid`  order by f.`create_time`  asc  
				";


        $userList = $this->model->query($allUserSql);

        $url = $go_url;
        $sendDateTime = date("Y-m-d H:i:s");
        $sendDate = date("m月d日");

        $sendRes = false;


        $where['id'] = 1;
        $wechat = $this->model->table('wxapp_config')->field('wx_appid, wx_appsecret, status')->where($where)->find();

        //print_r($wechat);exit;

        if (empty($wechat)) {
            $wechat = [];
        }
        if (empty($wechat['status'])) {
            $this->message(L('open_wechat'), url('index'), 2);
            exit;
        }

        $config = [];
        $config['appid'] = $wechat['wx_appid'];
        $config['secret'] = $wechat['wx_appsecret'];

        $wxapp = new Wxapp($config);


        $sql = 'select * from {pre}wxapp_template 

            WHERE id = 4   limit 0,1';

        $list = $this->model->query($sql);

        //print_r($list[0]);

        //$this->assign('info', $list[0]);


        $vars = $list[0]['vars'];

        $vars_array = explode(',', $vars);

        $templateWxId = trim($list[0]['wx_template_id']);

        $templateName = $list[0]['wx_title'];


        for ($i = 0; $i < count($userList); $i++) {
            $openId = $userList[$i]['openid'];

            $userName = $userList[$i]['nickname'];

            $target_user_id = $userList[$i]['ect_uid'];

            $message_data = array();
            $message_data['touser'] = $openId;
            $message_data['template_id'] = $templateWxId;
            $message_data['page'] = $url;
            $message_data['topcolor'] = '#FF0000';

            ////获取该用户对应的可以使用的点数，如果没有，不发送。


            $message_data['form_id'] = $userList[$i]['from_id'];//"ed68e88ca4ae341f23fbd97d13924843";

            // $sendData  = array();

            /*
            $sendData =
            [
                'keyword1' => ['value' => '测试', 'color' => '#000000'],
                'keyword2' => ['value' => '测试2', 'color' => '#000000'],
                'keyword3' => ['value' => '测试3', 'color' => '#000000'],
                'keyword4' => ['value' => '测试4', 'color' => '#000000'],
                'keyword5' => ['value' => '测试5', 'color' => '#000000']
            ];
            */


            for ($x = 0; $x < count($vars_array); $x++) {
                $targetValue = I('post.' . $vars_array[$x]);

                /////通配符替换开始
                $targetValue = str_replace("{name}", $userName, $targetValue);
                $targetValue = str_replace("{now}", $sendDateTime, $targetValue);
                $targetValue = str_replace("{date}", $sendDate, $targetValue);

                //I('post.'.$vars_array[$x])  FF0000  {name}
                $sendData[$vars_array[$x]] = array(
                    'value' => $targetValue,
                    'color' => "#000000"
                );

            }

            $message_data['data'] = $sendData;
            $message_data['emphasis_keyword'] = '';

            //$rs = $this->weObj->sendTemplateMessage($message_data);

            $result = $wxapp->sendTemplateMessage($message_data);


            if (!empty($result)) {
                //把点数设置为已经使用
                $fid = $userList[$i]['fid'];

                $okSql =
                    "
							update dsc_small_app_fromid set `is_use`  = 1 where fid = '$fid'
						";

                $this->model->query($okSql);


                $msgid = '';//$result['msgid'];

                $serializeData = $templateName;//addslashes(serialize($message_data));//['data']urlencode

                $insertSql =
                    "
								insert into {pre}wxapp_template_log_my 
								(
									openid,
									data,
									url,
									status,
									msgid,
									user_id,
									send_time
								)
								values
								(
									'$openId',
									'$serializeData',
									'$url',
									1,
									'$msgid',
									'$target_user_id',
									now()
								)
							  ";

                $this->model->query($insertSql);

                $sendRes = true;

                echo "发送成功";
            }


        }
    }


    public function actionTemplateMy()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');

        $where['is_zd'] = 1;

        $list = $this->model->table('wxapp_template')->where($where)->order('id asc')->select();

        if ($list) {
            foreach ($list as $key => $val) {
                $list[$key]['add_time'] = local_date('Y-m-d H:i', $val['add_time']);
            }
        }

        //print_r($list);

        $this->assign('list', $list);
        $this->display();
    }

    ///////////////////////////自定义发送模版消息
    ////////https://www.missmall.com/mobile/index.php?r=wechat/wxapp/send_template
    /*
    public function actionSendTemplate()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');
         $this->display();

    }
    */

    public function actionSendTemplateMy()
    {
        $this->admin_priv('template');

        if (IS_POST) {
            $id = I('post.id');

            $sql = 'select * from {pre}wxapp_template 

            WHERE id = ' . $id . '   limit 0,1';

            $list = $this->model->query($sql);

            //print_r($list[0]);

            //$this->assign('info', $list[0]);


            $vars = $list[0]['vars'];

            $vars_array = explode(',', $vars);

            $templateWxId = trim($list[0]['wx_template_id']);

            $templateName = $list[0]['wx_title'];

            //$randList = I('get.rand_list');
            //$endTime = I('get.end_time');
            $send_group = I('post.send_group');

            $send_date_s = I('post.send_start_date');

            $send_date_e = I('post.send_end_date');

            $go_url = I('post.go_url');

            /////过期处理
            $overdueSql =
                "
				update dsc_small_app_fromid set `is_overdue`  = 1 where DATE_SUB(CURDATE(), INTERVAL 6 DAY) >= date(`create_time`) and `is_use`  = 0 and `is_overdue`  = 0
			";

            $this->model->query($overdueSql);


            $sql = "";
            if (empty($send_group))//正好是0
            {
                //distinct
                $allUserSql =
                    "
					select   a.openid,a.nickname,a.ect_uid,f.fid,f.`from_id` from {pre}wechat_user as a left join {pre}small_app_fromid as f  on a.`openid` = f.`wxid` 
					where f.`is_use` = 0 and f.`is_overdue` = 0 GROUP BY a.`ect_uid`  order by f.`create_time`  asc  
				";
            } else {
                $getAllRankSql =
                    "
					select  min_points, max_points, special_rank from {pre}user_rank where rank_id = '$send_group' 
				";

                $rankList = $this->model->query($getAllRankSql);

                //print_r($rankList);

                $whereRankSql = "";
                //循环，然后组装sql
                for ($i = 0; $i < count($rankList); $i++) {
                    $min_points = $rankList[$i]['min_points'];
                    $max_points = $rankList[$i]['max_points'];
                    $whereRankSql .= " and b.rank_points >= " . intval($min_points);
                    $whereRankSql .= " and b.rank_points < " . intval($max_points);
                }

                //distinct
                $allUserSql =
                    "select  a.openid,a.nickname,a.ect_uid,f.fid,f.`from_id` from {pre}wechat_user as a 
					left join {pre}users as b on a.ect_uid = b.user_id   left join {pre}small_app_fromid as f  on a.`openid` = f.`wxid` 
					where f.`is_use` = 0 and f.`is_overdue` = 0 $whereRankSql GROUP BY a.`ect_uid`   order by f.`create_time`  asc  
				";

            }

            $userList = $this->model->query($allUserSql);

            $url = $go_url;
            $sendDateTime = date("Y-m-d H:i:s");
            $sendDate = date("m月d日");

            $sendRes = false;


            $where['id'] = 1;
            $wechat = $this->model->table('wxapp_config')->field('wx_appid, wx_appsecret, status')->where($where)->find();

            //print_r($wechat);exit;

            if (empty($wechat)) {
                $wechat = [];
            }
            if (empty($wechat['status'])) {
                $this->message(L('open_wechat'), url('index'), 2);
                exit;
            }

            $config = [];
            $config['appid'] = $wechat['wx_appid'];
            $config['secret'] = $wechat['wx_appsecret'];

            $wxapp = new Wxapp($config);


            for ($i = 0; $i < count($userList); $i++) {
                $openId = $userList[$i]['openid'];

                $userName = $userList[$i]['nickname'];

                $target_user_id = $userList[$i]['ect_uid'];

                $message_data = array();
                $message_data['touser'] = $openId;
                $message_data['template_id'] = $templateWxId;
                $message_data['page'] = $url;
                $message_data['topcolor'] = '#FF0000';

                ////获取该用户对应的可以使用的点数，如果没有，不发送。


                $message_data['form_id'] = $userList[$i]['from_id'];//"ed68e88ca4ae341f23fbd97d13924843";

                // $sendData  = array();

                /*
                $sendData =
                [
                    'keyword1' => ['value' => '测试', 'color' => '#000000'],
                    'keyword2' => ['value' => '测试2', 'color' => '#000000'],
                    'keyword3' => ['value' => '测试3', 'color' => '#000000'],
                    'keyword4' => ['value' => '测试4', 'color' => '#000000'],
                    'keyword5' => ['value' => '测试5', 'color' => '#000000']
                ];
                */


                for ($x = 0; $x < count($vars_array); $x++) {
                    $targetValue = I('post.' . $vars_array[$x]);

                    /////通配符替换开始
                    $targetValue = str_replace("{name}", $userName, $targetValue);
                    $targetValue = str_replace("{now}", $sendDateTime, $targetValue);
                    $targetValue = str_replace("{date}", $sendDate, $targetValue);

                    //I('post.'.$vars_array[$x])  FF0000  {name}
                    if($x == 0){
                        $sendData[$vars_array[$x]] = array(
                            'value' => $targetValue,
                            'color' => "#E30000"
                        );
                    }else{
                        $sendData[$vars_array[$x]] = array(
                            'value' => $targetValue,
                            'color' => "#20277B"
                        );
                    }
                }

                $message_data['data'] = $sendData;
                $message_data['emphasis_keyword'] = 'keyword1.DATA';

                //$rs = $this->weObj->sendTemplateMessage($message_data);

                $result = $wxapp->sendTemplateMessage($message_data);

                if (!empty($result)) {
                    //把点数设置为已经使用
                    $fid = $userList[$i]['fid'];

                    $okSql =
                        "
							update dsc_small_app_fromid set `is_use`  = 1 where fid = '$fid'
						";

                    $this->model->query($okSql);


                    $msgid = '';//$result['msgid'];

                    $serializeData = $templateName;//addslashes(serialize($message_data));//['data']urlencode

                    $insertSql =
                        "
								insert into {pre}wxapp_template_log_my 
								(
									openid,
									data,
									url,
									status,
									msgid,
									user_id,
									send_time
								)
								values
								(
									'$openId',
									'$serializeData',
									'$url',
									1,
									'$msgid',
									'$target_user_id',
									now()
								)
							  ";

                    $this->model->query($insertSql);

                    $sendRes = true;
                }

            }


            if ($sendRes) {
                exit(json_encode(array('status' => 1, 'msg' => '发送成功')));
                exit;
            } else {
                if (count($userList) > 0) {
                    exit(json_encode(array('status' => 1, 'msg' => '发送失败！')));
                    exit;
                } else {
                    exit(json_encode(array('status' => 1, 'msg' => '找不到符合条件的用户，发送失败！')));
                    exit;
                }

            }

        }


        $id = I('get.id');


        if (!empty($id)) {
            $sql = 'select * from {pre}wxapp_template  
            WHERE id = ' . $id . '   limit 0,1';

            $list = $this->model->query($sql);

            //print_r($list[0]);

            $this->assign('info', $list[0]);


            $vars = $list[0]['vars'];

            $vars_array = explode(',', $vars);


            $this->assign('vars_array', $vars_array);

            //获取所有的用户组
            $sql = 'select rank_id,rank_name from {pre}user_rank   ';

            $list = $this->model->query($sql);

            //print_r($list[0]);

            $this->assign('user_rank_list', $list);

            $this->assign('now_time', date('Y-m-d H:i:s'), time());


            $this->display();

        }

    }


    public function actionSendTemplate()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');

        $where['id'] = 1;
        $wechat = $this->model->table('wxapp_config')->field('wx_appid, wx_appsecret, status')->where($where)->find();

        //print_r($wechat);exit;

        if (empty($wechat)) {
            $wechat = [];
        }
        if (empty($wechat['status'])) {
            $this->message(L('open_wechat'), url('index'), 2);
            exit;
        }

        $config = [];
        $config['appid'] = $wechat['wx_appid'];
        $config['secret'] = $wechat['wx_appsecret'];

        $wxapp = new Wxapp($config);

        //echo "111";

        // $timeFormat = $shopconfig->getShopConfigByCode('time_format');

        // $end_time = $team_info['start_time'] + ($team_info['validity_time'] * 3600);//剩余时间

        // price_format($team_info['team_price'], true)

        $pushData =
            [
                'keyword1' => ['value' => '商品名称', 'color' => '#000000'],
                'keyword2' => ['value' => '商品名称2222', 'color' => '#000000'],
                'keyword3' => ['value' => '121212', 'color' => '#000000'],
                'keyword4' => ['value' => '333333', 'color' => '#000000']
            ];

        //$url = 'pages/group/wait?objectId='. $order['team_id'] . '&user_id='. $order['user_id'];
        $url = 'pages/index/index';

        //apilog($url);

        //    $this->authService->wxappPushTemplate('AT0541', $pushData, $url, $order['user_id'], $form_id);

        $template = '';//$this->WxappConfigRepository->getTemplateInfo($code);

        if (true)//$template['status'] == 1
        {
            $user = "";//$this->userRepository->getUserOpenid($uid);
            $openid = "oeJD20CeDDdHznKmL6j1WKhXS-YA";//$user['openid'];
            $data['touser'] = $openid;
            $data['template_id'] = 'mdQqtDkFHAX0SdgyJ3FJ2ko7YOb4fNDZ5_SMZEQtn3o';//$template['wx_template_id'];
            $data['page'] = $url;
            $data['form_id'] = "ed68e88ca4ae341f23fbd97d13924843";//" wx".date('Ymdhis');//$form_id;

            echo $data['form_id'];

            $data['data'] = $pushData;
            $data['emphasis_keyword'] = '';
            $result = $wxapp->sendTemplateMessage($data);

            if (empty($result)) {
                echo "失败！！";
            } else {
                echo "成功！！";
            }
        } else {
            echo "失败！！";
        }
        /* */
    }


    /**
     * 编辑模板消息
     */
    public function actionEditTemplate()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');

        if (IS_AJAX) {
            $id = I('post.id');
            $data = I('post.data', '', ['htmlspecialchars', 'trim']);
            if ($id) {
                $condition['id'] = $id;

                $data['add_time'] = gmtime();
                $this->model->table('wxapp_template')->data($data)->where($condition)->save();
                exit(json_encode(['status' => 1]));
            } else {
                exit(json_encode(['status' => 0, 'msg' => L('template_edit_fail')]));
            }
        }
        $id = I('get.id', 0, 'intval');
        if ($id) {
            $condition['id'] = $id;
            $template = $this->model->table('wxapp_template')->where($condition)->find();
            $this->assign('template', $template);
        }

        $this->display();
    }

    /**
     * 启用或禁止模板消息
     */
    public function actionSwitch()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');

        $id = I('get.id', 0, 'intval');
        $status = I('get.status', 0, 'intval');
        if (empty($id)) {
            $this->message(L('empty'), null, 2);
        }
        $condition['id'] = $id;

        $data = [];
        $data['add_time'] = gmtime();

        // 启用模板消息
        if ($status == 1) {
            // 模板ID为空
            $template = $this->model->table('wxapp_template')->field('wx_template_id, wx_code, wx_keyword_id')->where($condition)->find();

            if (empty($template['wx_template_id'])) {
                $wx_keyword_id = explode(',', $template['wx_keyword_id']);
                $template_id = $this->weObj->wxaddTemplateMessage($template['wx_code'], $wx_keyword_id);
                // 已经存在模板ID
                if ($template_id) {
                    $data['wx_template_id'] = $template_id;
                    $this->model->table('wxapp_template')->data($data)->where($condition)->save();
                } else {
                    $this->message($this->weObj->errMsg, null, 2);
                }
            }
            // 重新启用 更新状态status
            $data['status'] = 1;
            $this->model->table('wxapp_template')->data($data)->where($condition)->save();
        } else {
            // 禁用 更新状态status
            $data['status'] = 0;
            $this->model->table('wxapp_template')->data($data)->where($condition)->save();
        }
        $this->redirect('template');
    }

    /**
     * 重置模板消息
     * @return
     */
    public function actionResetTemplate()
    {
        // 模板消息权限
        $this->admin_priv('wxapp_template');

        if (IS_AJAX) {
            $json_result = ['error' => 0, 'msg' => '', 'url' => ''];

            $id = I('get.id', 0, 'intval');
            if (!empty($id)) {
                $condition['id'] = $id;
                $template = dao('wxapp_template')->field('wx_template_id')->where($condition)->find();
                if (!empty($template['wx_template_id'])) {
                    $rs = $this->weObj->wxDelTemplate($template['wx_template_id']);
                    if (empty($rs)) {
                        $json_result['msg'] = L('errcode') . $this->weObj->errCode . L('errmsg') . $this->weObj->errMsg;
                        exit(json_encode($json_result));
                    }
                    dao('wxapp_template')->data(['wx_template_id' => '', 'status' => 0])->where(['id' => $id])->save();
                    $json_result['msg'] = '重置成功！';
                    exit(json_encode($json_result));
                }
            }
            $json_result['error'] = 1;
            $json_result['msg'] = '重置失败！';
            exit(json_encode($json_result));
        }
    }

    /**
     * 获取配置信息
     */
    private function get_config()
    {
        $without = [
            'index',
            'append',
            'modify',
            'delete',
            'set_default'
        ];

        if (!in_array(strtolower(ACTION_NAME), $without)) {
            // 公众号配置信息
            $where['id'] = 1;
            $wechat = $this->model->table('wxapp_config')->field('wx_appid, wx_appsecret, status')->where($where)->find();
            if (empty($wechat)) {
                $wechat = [];
            }
            if (empty($wechat['status'])) {
                $this->message(L('open_wechat'), url('index'), 2);
                exit;
            }
            $config = [];
            $config['appid'] = $wechat['wx_appid'];
            $config['secret'] = $wechat['wx_appsecret'];
            $this->weObj = new Wxapp($config);
            $this->assign('type', $wechat['type']);
        }
    }

}
