<?php

namespace App\Modules\Wechat\Controllers;

use App\Modules\Base\Controllers\BackendController;
use App\Extensions\Wxapp;

class MktagController extends BackendController
{
    // 微信对象
    protected $weObj = '';
    // 微信公众号ID
    protected $wechat_id = 0;

    public function __construct()
    {
        parent::__construct();

        L(require(MODULE_PATH . 'Language/' . C('shop.lang') . '/wechat.php'));
        $this->assign('lang', array_change_key_case(L()));
        // 默认微信公众号
        $this->wechat_id = 1;

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
     * 小程序用户标签
     */
    public function actionIndex()
    {
        // 权限
        $this->admin_priv('marketing_tag');

        // 查询
        $list = dao('marketing_tag')->select();
        $this->assign('list', $list);
        $this->display();
    }

    /**
     * 新增编辑用户标签
     */
    public function actionAdd()
    {
        // 权限
        $this->admin_priv('marketing_tag');

        // 查询
        $where['id'] = I('get.id');
        $mktag = dao('marketing_tag')->where($where)->select();

        $this->assign('mktag', $mktag[0]);
        $this->display();
    }

    /**
     * 搜索用户标签
     */
    public function actionSearch()
    {
        $this->display();
    }

    public function actionUpdate()
    {
        if (I('post.id')) {
            $data['tag_name'] = I('post.tag_name');
            $data['sort_order'] = I('post.sort_order');
            $where['id'] = I('post.id');
            $re = dao('marketing_tag')->where($where)->save($data);
        } else {
            $data['tag_name'] = I('post.tag_name');
            $data['sort_order'] = I('post.sort_order');
            $re = dao('marketing_tag')->add($data);
        }
        $this->assign('url', '../mobile/index.php?r=wechat/mktag/index');
        $this->display();
    }

    /**
     * 删除用户标签
     */
    public function actionDelete()
    {
        $where['id'] = I('get.id');
        $re = dao('marketing_tag')->where($where)->delete();
        echo $re;
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
