<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/5/8
 * Time: 21:09
 */

return array(
    //一级导航
    'index' => array(
        'home' => '首页',
        'shop' => '商城',
        'order' => '订单',
        'promotion' => '促销',
        'phone' => '手机',
        'finance' => '财务',
    ),

    //二级导航
    'home' => array(
        'infocenter' => array(
            'name' => '管理中心',
            'url' => 'admin/info',
        ),
    ),


    'shop' => array(
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
        'returncause' => array(
            'name' => '退货原因',
            'url' => 'admin/returncause',
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


    'phone' => array(
        'wechat' => array(
            'name' => '公众号设置',
            'url' => 'admin/wechatconfig',
        ),
        'wxapp' => array(
            'name' => '小程序设置',
            'url' => 'admin/wxappconfig',
        ),
    ),


    'finance' => array(
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
);