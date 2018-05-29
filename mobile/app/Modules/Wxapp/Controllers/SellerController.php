<?php

namespace App\Modules\Wxapp\Controllers;

use App\Extensions\Form;
use App\Extensions\Wxapp;
use App\Modules\Base\Controllers\BackendSellerController;

class SellerController extends BackendSellerController
{
    // 微信对象
    protected $weObj = '';
    // 微信公众号ID
    protected $wxapp_id = 0;
    // 插件名称
    protected $plugin_name = '';
    // 商家ID
    protected $ru_id = 0;

    protected $market_type = '';  // 营销类型

    // 分页数量
    protected $page_num = 0;

    public function __construct()
    {
        parent::__construct();
        L(require(MODULE_PATH . 'Language/' . C('shop.lang') . '/wechat.php'));
        $this->assign('lang', array_change_key_case(L()));

        // 查询商家管理员
        $seller = get_admin_ru_id_seller();
        if (!empty($seller) && $seller['ru_id'] > 0) {
            $this->ru_id = $seller['ru_id'];
        }

        // 商家菜单列表
        $menu = set_seller_menu();

        $this->assign('seller_menu', $menu);
        // 当前选择菜单
        $menu_select = get_select_menu();
        $this->assign('menu_select', $menu_select);

        $condition['ru_id'] = $this->ru_id;
        $condition['default_wx'] = 0;
        $wxappinfo = $this->model->table('wxapp')->where($condition)->find();

        if (empty($wxappinfo)) {
            $data = [
                'time' => gmtime(),
                'status' => 0,
                'default_wx' => 0,
                'ru_id' => $this->ru_id
            ];
            $this->model->table('wxapp')->data($data)->add();

            $this->redirect('modify');
        }
        // 插件
        $this->plugin_name = I('get.ks', '', ['htmlspecialchars', 'trim']);
        $this->wxapp_id = $wxappinfo['id'];

        // 营销
        $this->market_type = I('get.type', '', ['htmlspecialchars', 'trim']);

        // 获取配置信息
        $this->get_config();

        // 初始化 每页分页数量
        $this->page_num = 10;
        $this->assign('page_num', $this->page_num);
        // 商家ID
        $this->assign('ru_id', $this->ru_id);
        $this->assign('seller_name', $_SESSION['seller_name']);
    }

    /**
     * 我的小程序
     */
    public function actionIndex()
    {
        $this->redirect('modify');
    }

    /**
     * 修改小程序
     */
    public function actionModify()
    {
        // 公众号设置权限
        $this->seller_admin_priv('seller_setup_wxapp');

        $condition['id'] = $this->wxapp_id;
        // 提交处理
        if (IS_POST) {
            $data = I('post.data', '', ['htmlspecialchars', 'trim']);
            // 验证数据
            $form = new Form();
            if (!$form->isEmpty($data['name'], 1)) {
                $this->message(L('must_name'), null, 2, true);
            }
            if (!$form->isEmpty($data['orgid'], 1)) {
                $this->message(L('must_id'), null, 2, true);
            }
            if (!$form->isEmpty($data['token'], 1)) {
                $this->message(L('must_token'), null, 2, true);
            }
            // 更新数据
            // 如果appsecret包含 * 跳过不保存数据库
            if (strpos($data['appsecret'], '*') == true) {
                unset($data['appsecret']);
            }
            $this->model->table('wxapp')->data($data)->where($condition)->save();
            $this->message(L('wechat_editor') . L('success'), url('modify'), 1, true);
        }
        // 查询
        $data = $this->model->table('wxapp')->where($condition)->find();
        $data['secret_key'] = isset($data['orgid']) && isset($data['appid']) ? $data['secret_key'] : '';
        $data['url'] = url('wechat/index/index', ['key' => $data['secret_key']], false, true);
        // 用*替换字符显示
        $data['appsecret'] = string_to_star($data['appsecret']);

        $this->assign('data', $data);
        $this->assign('title', ['title' => '小程序', 'subtitle' => '小程序设置']);
        // 当前位置
        $postion = ['ur_here' => L('edit_wechat')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 小程序导航
     */
    public function actionNavList()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_nav_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '小程序导航']);
        $list = $this->model->table('wxapp_nav')->where(['ru_id' => $this->ru_id])->order('vieworder asc')->select();

        $this->assign('list', $list);
        // 当前位置
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 编辑菜单
     */
    public function actionNavEdit()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_nav_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '添加编辑导航']);
        if (IS_POST) {
            $id = I('post.id');
            $data = I('post.data');
            $data['ru_id'] = $this->ru_id;

            // 编辑
            if (!empty($id)) {
                $this->model->table('wxapp_nav')->data($data)->where(['id' => $id])->save();
            } else {
                // 添加
                $this->model->table('wxapp_nav')->data($data)->add();
            }
            exit(json_encode(['status' => 1, 'msg' => L('menu_edit') . L('success')]));
        }
        $id = I('get.id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_nav')->where(['id' => $id])->find();
        }
        $this->assign('info', $info);
        // 当前位置
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 删除导航
     */
    public function actionNavDel()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_nav_wxapp');

        $id = I('get.id');
        if (empty($id)) {
            $this->message(L('menu_select_del'), null, 2, true);
        }
        $minfo = $this->model->table('wxapp_nav')->field('id, cid')->where(['id' => $id])->find();
        // 顶级栏目
        if ($minfo['cid'] == 0) {
            $this->model->table('wxapp_nav')->where(['id' => $minfo['id']])->delete();
        }
        $this->model->table('wxapp_nav')->where(['id' => $minfo['id']])->delete();
        $this->message(L('drop') . L('success'), url('navlist'), 1, true);
    }

    /**
     * 小程序卡式广告
     */
    public function actionCartAds()
    {
        // 小程序卡式广告
        $this->seller_admin_priv('seller_cart_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '卡式广告列表']);

        $offset = $this->pageLimit(url('cartads'), $this->page_num);

        $list = $this->model->table('wxapp_cart_ads')->where(['ru_id' => $this->ru_id])->order('vieworder asc')->select();
        foreach ($list as $key => $value) {
            $list[$key]['pic'] = get_image_path($value['pic']);
        }
        $this->assign('list', $list);
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 小程序编辑卡式广告
     */
    public function actionCartAdsEdit()
    {

        $this->seller_admin_priv('seller_cart_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '编辑卡式广告']);

        if (IS_POST) {
            $id = I('post.id');
            $data = I('post.data');
            $data['ru_id'] = $this->ru_id;
            $pic_path = I('post.file_path');

            // 封面处理
            $cover = $_FILES['pic'];
            if ($cover['name']) {
                $type = ['image/jpeg', 'image/png'];
                if (!in_array($_FILES['pic']['type'], $type)) {
                    $this->message(L('not_file_type'), null, 2, true);
                }
                $result = $this->upload('data/wxapp_adsimg/ads_img', true);
                if ($result['error'] > 0) {
                    $this->message($result['message'], null, 2, true);
                }
                $data['pic'] = $result['url'];
            } else {
                $data['pic'] = $pic_path;
            }

            // 编辑
            if (!empty($id)) {
                $this->model->table('wxapp_cart_ads')->data($data)->where(['id' => $id])->save();
            } else {
                // 添加
                $this->model->table('wxapp_cart_ads')->data($data)->add();
            }
            $this->message(L('wechat_operating') . L('success'), url('cartads'), 1, true);
        }

        $navs = $this->model->table('wxapp_nav')->where(['ru_id' => $this->ru_id])->select();
        $this->assign('navs', $navs);

        $id = I('get.id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_cart_ads')->where(['id' => $id])->find();
        }
        $info['pic_cdn'] = get_image_path($info['pic']);
        $this->assign('info', $info);
        // 当前位置
        $postion = ['ur_here' => L('sub_title')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 删除广告
     */
    public function actionCartAdsDel()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_cart_ads_wxapp');

        $id = I('get.id');
        if (empty($id)) {
            $this->message(L('menu_select_del'), null, 2, true);
        }
        $pic = $this->model->table('wxapp_cart_ads')
            ->where(['id' => $id])
            ->getField('pic');
        $this->remove($pic);
        $this->model->table('wxapp_cart_ads')->where(['id' => $id])->delete();
        $this->message(L('drop') . L('success'), url('cartads'), 1, true);
    }

    /**
     * 小程序编辑轮播广告
     */
    public function actionSlideAds()
    {
        $this->seller_admin_priv('seller_slide_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '轮播广告列表']);

        $offset = $this->pageLimit(url('slideads'), $this->page_num);

        $list = $this->model->table('wxapp_slide_ads')->where(['ru_id' => $this->ru_id])->order('vieworder asc')->select();
        foreach ($list as $key => $value) {
            $list[$key]['pic'] = get_image_path($value['pic']);
        }
        $this->assign('list', $list);
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 小程序编辑轮播广告
     */
    public function actionSlideAdsEdit()
    {
        $this->seller_admin_priv('seller_slide_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '编辑轮播广告']);

        if (IS_POST) {
            $id = I('post.id');
            $data = I('post.data');
            $data['ru_id'] = $this->ru_id;
            $pic_path = I('post.file_path');

            // 封面处理
            $cover = $_FILES['pic'];
            if ($cover['name']) {
                $type = ['image/jpeg', 'image/png'];
                if (!in_array($_FILES['pic']['type'], $type)) {
                    $this->message(L('not_file_type'), null, 2, true);
                }
                $result = $this->upload('data/wxapp_adsimg/slide_img', true);
                if ($result['error'] > 0) {
                    $this->message($result['message'], null, 2, true);
                }
                $data['pic'] = $result['url'];
            } else {
                $data['pic'] = $pic_path;
            }

            // 编辑
            if (!empty($id)) {
                $this->model->table('wxapp_slide_ads')->data($data)->where(['id' => $id])->save();
            } else {
                // 添加
                $this->model->table('wxapp_slide_ads')->data($data)->add();
            }
            $this->message(L('wechat_operating') . L('success'), url('slideads'), 1, true);
        }

        $navs = $this->model->table('wxapp_nav')->where(['ru_id' => $this->ru_id])->select();
        $this->assign('navs', $navs);

        $id = I('get.id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_slide_ads')->where(['id' => $id])->find();
        }
        $info['pic_cdn'] = get_image_path($info['pic']);
        $this->assign('info', $info);

        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 删除轮播广告
     */
    public function actionSlideAdsDel()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_slide_ads_wxapp');

        $id = I('get.id');
        if (empty($id)) {
            $this->message(L('menu_select_del'), null, 2, true);
        }
        $pic = $this->model->table('wxapp_slide_ads')
            ->where(['id' => $id])
            ->getField('pic');
        $this->remove($pic);
        $this->model->table('wxapp_slide_ads')->where(['id' => $id])->delete();
        $this->message(L('drop') . L('success'), url('slideads'), 1, true);
    }

    /**
     * 小程序弹出广告
     */
    public function actionLayerAds()
    {
        $this->seller_admin_priv('seller_layer_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '弹出广告列表']);

        $offset = $this->pageLimit(url('layerads'), $this->page_num);

        $list = $this->model->table('wxapp_layer_ads')->where(['ru_id' => $this->ru_id])->order('vieworder asc')->select();
        foreach ($list as $key => $value) {
            $list[$key]['pic'] = get_image_path($value['pic']);
        }
        $this->assign('list', $list);
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 小程序编辑弹出广告
     */
    public function actionLayerAdsEdit()
    {
        $this->seller_admin_priv('seller_layer_ads_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '编辑弹出广告']);

        if (IS_POST) {
            $id = I('post.id');
            $data = I('post.data');
            $data['ru_id'] = $this->ru_id;
            $pic_path = I('post.file_path');

            // 封面处理
            $cover = $_FILES['pic'];
            if ($cover['name']) {
                $type = ['image/jpeg', 'image/png'];
                if (!in_array($_FILES['pic']['type'], $type)) {
                    $this->message(L('not_file_type'), null, 2, true);
                }
                $result = $this->upload('data/wxapp_adsimg/layer_img', true);
                if ($result['error'] > 0) {
                    $this->message($result['message'], null, 2, true);
                }
                $data['pic'] = $result['url'];
            } else {
                $data['pic'] = $pic_path;
            }

            // 编辑
            if (!empty($id)) {
                $this->model->table('wxapp_layer_ads')->data($data)->where(['id' => $id])->save();
            } else {
                // 添加
                $this->model->table('wxapp_layer_ads')->data($data)->add();
            }
            $this->message(L('wechat_operating') . L('success'), url('layerads'), 1, true);
        }

        $navs = $this->model->table('wxapp_nav')->where(['ru_id' => $this->ru_id])->select();
        $this->assign('navs', $navs);

        $id = I('get.id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_layer_ads')->where(['id' => $id])->find();
        }
        $info['pic_cdn'] = get_image_path($info['pic']);
        $this->assign('info', $info);

        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 删除弹出广告
     */
    public function actionLayerAdsDel()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_layer_ads_wxapp');

        $id = I('get.id');
        if (empty($id)) {
            $this->message(L('menu_select_del'), null, 2, true);
        }
        $pic = $this->model->table('wxapp_layer_ads')
            ->where(['id' => $id])
            ->getField('pic');
        $this->remove($pic);
        $this->model->table('wxapp_layer_ads')->where(['id' => $id])->delete();
        $this->message(L('drop') . L('success'), url('layerads'), 1, true);
    }

    /**
     * 模板消息
     */
    public function actionWxappTempleteList()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_temp_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '主动模板消息']);
        $list = $this->model->table('wxapp_template')->where(['ru_id' => $this->ru_id])->select();

        $this->assign('list', $list);
        // 当前位置
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 编辑模板消息
     */
    public function actionWxappTempleteEdit()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_temp_wxapp');
        $this->assign('title', ['title' => '小程序', 'subtitle' => '添加模板消息']);
        if (IS_POST) {
            $id = I('post.id');
            $data = I('post.data');

            $data['ru_id'] = $this->ru_id;
            $wechat_id = $this->model->table('wxapp')->data($data)->where(['ru_id' => $this->ru_id])->getField('id');
            $data['wx_wechat_id'] = $wechat_id;
            $data['is_zd'] = 1;
            $data['add_time'] = time();
            // 编辑
            if (!empty($id)) {
                $this->model->table('wxapp_template')->data($data)->where(['id' => $id])->save();
            } else {// 添加
                $this->model->table('wxapp_template')->data($data)->add();
            }
            $this->message(L('wechat_operating') . L('success'), url('wxapptempletelist'), 1, true);
        }
        $id = I('get.id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_template')->where(['id' => $id])->find();
        }
        $this->assign('info', $info);
        //当前位置
        $postion = ['ur_here' => L('menu')];
        $this->assign('postion', $postion);
        $this->display();
    }

    /**
     * 删除模板消息
     */
    public function actionWxappTempleteDel()
    {
        // 自定义菜单权限
        $this->seller_admin_priv('seller_nav_wxapp');

        $id = I('get.id');
        if (empty($id)) {
            $this->message(L('menu_select_del'), null, 2, true);
        }
        $minfo = $this->model->table('wxapp_nav')->field('id, cid')->where(['id' => $id])->find();
        // 顶级栏目
        if ($minfo['cid'] == 0) {
            $this->model->table('wxapp_nav')->where(['id' => $minfo['id']])->delete();
        }
        $this->model->table('wxapp_nav')->where(['id' => $minfo['id']])->delete();
        $this->message(L('drop') . L('success'), url('navlist'), 1, true);
    }

    public function actionWxappSendTempleteMsg()
    {
        $this->seller_admin_priv('seller_temp_wxapp');
        if (IS_POST) {
            $where['ru_id'] = $this->ru_id;
            $wechat = $this->model->table('wxapp')->field('appid, appsecret, status')->where($where)->find();
            if (empty($wechat)) {
                $wechat = [];
            }
            if (empty($wechat['status'])) {
                exit(json_encode(['status' => 0, 'msg' => L('select_openid')]));
            }
            $config = [];
            $config['appid'] = $wechat['appid'];
            $config['secret'] = $wechat['appsecret'];
            $wxapp = new Wxapp($config);
            $this->model->table('wxapp_fromid')->data(['is_overdue'=>1])->where(['create_time_stamp < '.(time()-6*86400)])->save();
            $sql = "select * from {pre}wxapp_fromid where wxid in(select distinct(wxid) from {pre}wxapp_fromid group by wxid) and `is_use` = 0 and `is_overdue` = 0 and `ru_id` = $this->ru_id group by wxid order by `create_time_stamp` asc";
            $userFromid = $this->model->table('wxapp_fromid')->query($sql);
            $data = I('post.data');

            foreach ($userFromid as $key => $value) {
                $message_data = array();
                $message_data['touser'] = $value['wxid'];
                $message_data['form_id'] = $value['from_id'];
                $message_data['template_id'] = $data['wx_template_id'];
                $message_data['page'] = $data['url'];
                $message_data['topcolor'] = '#FF0000';

                $sendData = [];
                foreach ($data['keyword'] as $k => $val) {
                    if ($k == 0) {
                        $sendData['keyword' . ($k + 1)] = array(
                            'value' => $val,
                            'color' => "#E30000"
                        );
                    } else {
                        $sendData['keyword' . ($k + 1)] = array(
                            'value' => $val,
                            'color' => "#20277B"
                        );
                    }
                }

                $message_data['data'] = $sendData;
                $message_data['emphasis_keyword'] = 'keyword1.DATA';

                $result = $wxapp->sendTemplateMessage($message_data);

                if (!empty($result)) {
                    $where['fid'] = $userFromid[$key]['fid'];
                    $updata['is_use'] = 1;
                    $this->model->table('wxapp_fromid')->data($updata)->where($where)->save();
                    $success = true;
                }
            }
            if($success){
                exit(json_encode(array('status' => 1, 'msg' => '发送成功')));
            }else{
                exit(json_encode(array('status' => 1, 'msg' => '发送失败！')));
            }

        }
        $id = I('get.template_id');
        $info = [];
        if (!empty($id)) {
            $info = $this->model->table('wxapp_template')->where(['ru_id' => $this->ru_id, 'wx_template_id' => $id])->find();
        }

        $vars = explode(',', $info['vars']);
        $info['vars'] = $vars;
        $this->assign('info', $info);
        $this->display();
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
            'navlist',
            'navedit',
            'navdel',
            'cartads',
            'cartadsedit',
            'cartadsdel',
            'slideads',
            'slideadsedit',
            'slideadsdel',
            'layerads',
            'layeradsedit',
            'layeradsdel',
            'wxapptempletelist',
            'wxapptempleteedit',
            'wxapptempletedel',
            'wxappsendtempletemsg',
            'delete',
            'set_default'
        ];

        if (!in_array(strtolower(ACTION_NAME), $without)) {
            // 商家公众号配置信息
            $where['id'] = $this->wechat_id;
            $where['ru_id'] = $this->ru_id;
            $wxapp = $this->model->table('wxapp')->field('token, appid, appsecret, status')->where($where)->find();
            if (empty($wxapp)) {
                $wxapp = [];
            }
            if (empty($wxapp['status'])) {
                $this->message(L('open_wechat'), url('modify'), 2, true);
                exit;
            }
            $config = [];
            $config['token'] = $wxapp['token'];
            $config['appid'] = $wxapp['appid'];
            $config['appsecret'] = $wxapp['appsecret'];
        }
    }
}
