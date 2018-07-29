<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/5/8
 * Time: 21:09
 */

return array(
    'index' => array(
        'home' => '首页',
        'shop' => '商城',
        'finance' => '财务',
        'system' => '系统',
    ),
    'home' => array(
        'info' => '首页',
    ),
    'shop' => array(
        'commodity' => '商品',
        'order' => '订单',
        'promotion' => '促销',
        'goods_storage' => '库存',
        'store' => '店铺',
    ),
    'finance' => array(
        'satistics' => '统计',
    ),
    'system' => array(
        'setup' => '设置',
        'advertise' => '广告',
        'users' => '会员',
        'examine' => '权限',
        'database' => '数据',
    ),


    'info' => array(
        'infocenter' => array(
            'name' => '管理中心',
            'url' => 'admin/info',
        ),
    ),


    'commodity' => array(
        'goodssetup' => array(
            'name' => '商品设置',
            'url' => 'admin/goodsconf',
        ),
        'comcate' => array(
            'name' => '商品分类',
            'url' => 'admin/comcate',
        ),
        'goods' => array(
            'name' => '商品列表',
            'url' => 'admin/goods',
        ),
        'brandlist' => array(
            'name' => '品牌管理',
            'url' => 'admin/brand',
        ),
        'goodstype' => array(
            'name' => '商品类型',
            'url' => 'admin/goodstype',
        ),
        'gallery' => array(
            'name' => '图片库管理',
            'url' => 'admin/gallery',
        ),
    ),
    'order' => array(
        'orders' => array(
            'name' => '订单列表',
            'url' => 'admin/order',
        ),
        'delivery' => array(
            'name' => '发货单列表',
            'url' => 'admin/order/delivery/selfsale',
        ),
        'back' => array(
            'name' => '退货单列表',
            'url' => 'admin/order/return/selfsale',
        ),
    ),
    'promotion' => array(
        'favourable' => array(
            'name' => '优惠活动',
            'url' => 'admin/favourable',
        ),
        'coupons' => array(
            'name' => '优惠券',
            'url' => 'admin/coupons',
        ),
        'bonus' => array(
            'name' => '红　包',
            'url' => 'admin/bonus',
        ),
    ),
    'goods_storage' => array(
        'favouable' => array(
            'name' => '仓库入口',
            'url' => 'admin/favourable',
        ),
        'favouble' => array(
            'name' => '仓库出口',
            'url' => 'admin/favourable',
        ),
    ),
    'store' => array(
        'setup' => array(
            'name' => '店铺设置',
            'url' => 'admin/store',
        ),
        'list' => array(
            'name' => '店铺列表',
            'url' => 'admin/storelist',
        ),
    ),



    'satistics' => array(
        'ordersatistics' => array(
            'name' => '订单统计',
            'url' => 'admin/satistics/order',
        ),
        'usersatistics' => array(
            'name' => '会员统计',
            'url' => 'admin/satistics/user',
        ),
        'industryatistics' => array(
            'name' => '行业统计',
            'url' => 'admin/satistics/industry',
        ),
    ),


    'setup' => array(
        'shopsetup' => array(
            'name' => '商城设置',
            'url' => 'admin/shopconf',
        ),
        'navsetup' => array(
            'name' => '自定义导航',
            'url' => 'admin/navsetup',
        ),
        'paysetup' => array(
            'name' => '支付设置',
            'url' => 'admin/pay',
        ),
        'areasetup' => array(
            'name' => '地区&快递',
            'url' => 'admin/express',
        ),
        'seosetup' => array(
            'name' => 'SEO设置',
            'url' => 'admin/seo',
        ),
        'codesetup' => array(
            'name' => '验证码设置',
            'url' => 'admin/captcha',
        ),
        'friendsetup' => array(
            'name' => '友情链接',
            'url' => 'admin/friend',
        ),
    ),
    'advertise' => array(
        'ad_list' => array(
            'name' => '广告列表',
            'url' => 'admin/ad',
        ),
        'ad_position' => array(
            'name' => '广告位置',
            'url' => 'admin/adspos',
        )
    ),
    'users' => array(
        'members' => array(
            'name' => '会员列表',
            'url' => 'admin/users',
        ),
        'account' => array(
            'name' => '提现申请',
            'url' => 'admin/uaccount',
        ),
        'regfields' => array(
            'name' => '注册项设置',
            'url' => 'admin/regfields',
        ),
    ),
    'examine' => array(
        'privilege' => array(
            'name' => '管理员列表',
            'url' => 'admin/privilege',
        ),
    ),
    'database' => array(
        'database' => array(
            'name' => '数据备份',
            'url' => 'admin/database',
        ),
        'optimize' => array(
            'name' => '数据表优化',
            'url' => 'admin/database/optimize',
        ),
    ),
);