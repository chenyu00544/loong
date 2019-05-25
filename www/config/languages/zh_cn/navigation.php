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
        'phone' => '手机',
        'finance' => '财务',
        'system' => '系统',
        'third_party' => '第三方服务',
    ),

    //二级导航
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
    'phone' => array(
        'web' => '手机',
        'wechat' => '微信',
        'wxapp' => '小程序',
    ),
    'finance' => array(
        'satistics' => '统计',
    ),
    'system' => array(
        'setup' => '设置',
        'advertise' => '广告',
        'users' => '会员',
        'article' => '文章',
        'examine' => '权限',
        'database' => '数据',
        'selfshop' => '自营',
    ),
    'third_party' => array(
        'sms' => '短信',
        'file' => '文件',
        'interface' => '接口',
        'email' => '邮件',
    ),

    //三级导航
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
        'comment' => array(
            'name' => '用户评论',
            'url' => 'admin/comment',
        ),
        'qa' => array(
            'name' => '用户问答',
            'url' => 'admin/qa',
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
        'groupbuy' => array(
            'name' => '团购活动',
            'url' => 'admin/groupbuy',
        ),
        'seckill' => array(
            'name' => '秒杀活动',
            'url' => 'admin/seckill',
        ),
        'team' => array(
            'name' => '拼团活动',
            'url' => 'admin/team',
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
        'store' => array(
            'name' => '店铺设置',
            'url' => 'admin/store',
        ),
        'storelist' => array(
            'name' => '店铺列表',
            'url' => 'admin/storelist',
        ),
        'sellerdomain' => array(
            'name' => '店铺二级域名',
            'url' => 'admin/sellerdomain',
        ),
    ),


    'web' => array(
        'mobileoauth' => array(
            'name' => '授权登录',
            'url' => 'admin/mobileoauth',
        ),
        'mobilenav' => array(
            'name' => '移动导航',
            'url' => 'admin/mobilenav',
        ),
    ),
    'wechat' => array(
        'wechatconfig' => array(
            'name' => '公众号设置',
            'url' => 'admin/wechatconfig',
        ),
    ),
    'wxapp' => array(
        'wxappconfig' => array(
            'name' => '小程序设置',
            'url' => 'admin/wxappconfig',
        ),
        'wxappsession' => array(
            'name' => '小程序客服会话',
            'url' => 'admin/wxappsession',
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
        'cron' => array(
            'name' => '计划任务',
            'url' => 'admin/cron',
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
        'comment_label' => array(
            'name' => '评价标签',
            'url' => 'admin/commentlabel',
        ),
        'goods_description' => array(
            'name' => '产品说明',
            'url' => 'admin/goodsdesc',
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
        ),
        'ad_type' => array(
            'name' => '广告类型',
            'url' => 'admin/adstype',
        ),
        'notify' => array(
            'name' => '移动通知',
            'url' => 'admin/notify',
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
    'article' => array(
        'artcate' => array(
            'name' => '文章分类',
            'url' => 'admin/artcate',
        ),
        'article' => array(
            'name' => '文章列表',
            'url' => 'admin/article',
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
    'selfshop' => array(
        'self' => array(
            'name' => '自营设置',
            'url' => 'admin/shopconf/self',
        ),
    ),


    'sms' => array(
        'sms_setup' => array(
            'name' => '短信设置',
            'url' => 'admin/sms',
        ),
        'alidayu' => array(
            'name' => '大于短信',
            'url' => 'admin/alidayu',
        ),
        'alisms' => array(
            'name' => '阿里短信',
            'url' => 'admin/alisms',
        ),
    ),
    'file' => array(
        'oss' => array(
            'name' => '阿里云OSS配置',
            'url' => 'admin/oss',
        ),
    ),
    'interface' => array(
        'interface' => array(
            'name' => '快递鸟配置',
            'url' => 'admin/interface/kdniao',
        ),
    ),
    'email' => array(
        'emailsever' => array(
            'name' => '邮件服务器',
            'url' => 'admin/email',
        ),
        'sendemail' => array(
            'name' => '发送邮件',
            'url' => 'admin/email/send',
        ),
    ),
);