<?php
Route::group(['prefix' => 'admin', 'namespace' => 'Shop\Admin'], function () {
    Route::any('login', 'LoginController@login');
    Route::any('logout', 'LoginController@logout');
    Route::any('clearcache', 'LoginController@clearCache');
    Route::get('change', 'LoginController@change');
    Route::get('tool', 'LoginController@tool');
});

Route::group(['middleware' => ['admin.login'], 'prefix' => 'admin', 'namespace' => 'Shop\Admin'], function () {
    Route::get('index', 'IndexController@index');
    Route::get('info', 'IndexController@info');

    Route::post('search/dialog', 'SearchController@dialogSearch');
    Route::resource('search', 'SearchController');

    Route::get('shopconf/self', 'ShopConfController@selfShopConf');
    Route::resource('shopconf', 'ShopConfController');

    Route::post('express/install/{id}', 'ExpressController@install');
    Route::post('express/changes', 'ExpressController@changes');
    Route::resource('express', 'ExpressController');

    Route::post('transport/changes', 'TransportController@changes');
    Route::get('transport/regions/{id}/{tid}', 'TransportController@regions');
    Route::get('transport/express/{id}/{tid}', 'TransportController@express');
    Route::resource('transport', 'TransportController');

    Route::get('regions/nextregions/{id}/{tid}', 'RegionsController@nextRegions');
    Route::get('regions/addregion/{id}/{tid}', 'RegionsController@addRegion');
    Route::post('regions/changes', 'RegionsController@changes');
    Route::post('regions/nextall', 'RegionsController@nextRegionAll');
    Route::resource('regions', 'RegionsController');

    Route::post('navsetup/show/or/view', 'NavigationController@showOrView');
    Route::post('navsetup/change/order', 'NavigationController@changeOrder');
    Route::resource('navsetup', 'NavigationController');

    Route::post('goodsconf/change', 'GoodsConfigController@change');
    Route::resource('goodsconf', 'GoodsConfigController');

    Route::post('comcate/change', 'ComCateController@change');
    Route::post('comcate/getcates/{id}', 'ComCateController@getCates');
    Route::any('comcate/add/cate/{id}', 'ComCateController@addCate');
    Route::resource('comcate', 'ComCateController');

    Route::post('brand/change', 'BrandController@change');
    Route::post('brand/search', 'BrandController@search');
    Route::post('brand/firstchar', 'BrandController@getFirstChar');
    Route::resource('brand', 'BrandController');

    Route::get('goods/backto/{id}', 'GoodsController@backTo');
    Route::get('goods/del/{id}', 'GoodsController@thoroughDel');
    Route::get('goods/examine/{id}', 'GoodsController@examine');
    Route::get('goods/weight/order/{id}', 'GoodsController@weightOrder');
    Route::get('goods/cateextend/{id}', 'GoodsController@cateExtend');
    Route::get('goods/imagelibrary/{type}/{id}', 'GoodsController@imageLibrary');
    Route::get('goods/addgalleryshow', 'GoodsController@addGalleryShow');
    Route::get('goods/customattrwin/{id}/{gid}', 'GoodsController@customAttrWin');
    Route::post('goods/addgallery', 'GoodsController@addGallery');
    Route::post('goods/change', 'GoodsController@change');
    Route::post('goods/changes', 'GoodsController@changes');
    Route::post('goods/addcateext', 'GoodsController@addCateExtend');
    Route::post('goods/delcateext/{id}', 'GoodsController@delCateExtend');
    Route::post('goods/addgoodsgallery', 'GoodsController@addGoodsGallery');
    Route::post('goods/changegoodsgallery', 'GoodsController@changeGoodsGallery');
    Route::post('goods/upgoodsgallery', 'GoodsController@upGoodsGalleryPic');
    Route::post('goods/delgoodsgallery', 'GoodsController@delGoodsGalleryPic');
    Route::post('goods/getgoodsattr/{id}', 'GoodsController@getGoodsAttr');
    Route::post('goods/addgoodsattr', 'GoodsController@addGoodsAttr');
    Route::post('goods/setgoodsattr', 'GoodsController@setGoodsAttr');
    Route::post('goods/product/{id}', 'GoodsController@getGoodsByProduct');
    Route::get('goods/sku/{id}', 'GoodsController@getGoodsByProductSku');
    Route::post('goods/change/sku', 'GoodsController@changeProductSku');
    Route::post('goods/delproduct/{id}', 'GoodsController@delProduct');
    Route::post('goods/delvolumeprice/{id}', 'GoodsController@delVolumePrice');
    Route::post('goods/delfullcut/{id}', 'GoodsController@delFullCut');
    Route::resource('goods', 'GoodsController');

    Route::get('goodstype/goodstype/modal', 'GoodsTypeController@goodsTypeByModal');
    Route::post('goodstype/addgoodstype', 'GoodsTypeController@addGoodsType');
    Route::post('goodstype/gettypes/{id}', 'GoodsTypeController@getTypes');
    Route::post('goodstype/gettypescates/{id}', 'GoodsTypeController@getGoodsTypesAndCates');
    Route::resource('goodstype', 'GoodsTypeController');

    Route::get('typecate/typecate/modal', 'GoodsTypeCateController@goodsTypeCateByModal');
    Route::post('typecate/addgoods/typecate', 'GoodsTypeCateController@addGoodsTypeCate');
    Route::post('typecate/change', 'GoodsTypeCateController@change');
    Route::post('typecate/getcates/{id}', 'GoodsTypeCateController@getCates');
    Route::resource('typecate', 'GoodsTypeCateController');

    Route::get('attribute/attribut/modal', 'AttributeController@attributeModal');
    Route::post('attribute/addattribute', 'AttributeController@addAttribute');
    Route::post('attribute/getattributes/{id}', 'AttributeController@getAttributes');
    Route::resource('attribute', 'AttributeController');

    Route::resource('captcha', 'CaptchaController');

    Route::get('seo/brand', 'SeoController@brand');
    Route::get('seo/goods', 'SeoController@goods');
    Route::resource('seo', 'SeoController');

    Route::post('pay/install', 'PayConfigController@install');
    Route::post('pay/changes', 'PayConfigController@changes');
    Route::resource('pay', 'PayConfigController');

    Route::post('friend/changes', 'FriendController@changes');
    Route::resource('friend', 'FriendController');

    Route::get('gallery/galleryview/{id}', 'GalleryController@galleryView');
    Route::get('gallery/transferpic/{id}', 'GalleryController@transferGalleryPic');
    Route::get('gallery/uppicview/{id}', 'GalleryController@upPicView');
    Route::post('gallery/changes', 'GalleryController@changes');
    Route::post('gallery/getgallerys/{id}', 'GalleryController@getGallerys');
    Route::post('gallery/setgallerypic/', 'GalleryController@setGalleryPic');
    Route::post('gallery/delgallerypic', 'GalleryController@delGalleryPic');
    Route::post('gallery/upgallerypic', 'GalleryController@upGalleryPic');
    Route::post('gallery/getgallerypics', 'GalleryController@getGalleryPics');
    Route::resource('gallery', 'GalleryController');

    Route::get('users/info/{id}', 'UsersController@userInfo');
    Route::get('users/address/{id}', 'UsersController@userAddress');
    Route::get('users/userorder/{id}', 'UsersController@userOrder');
    Route::get('users/baitiao/{id}', 'UsersController@userBaitiao');
    Route::get('users/account/{id}', 'UsersController@userAccount');
    Route::post('users/changes', 'UsersController@changes');
    Route::resource('users', 'UsersController');

    Route::resource('address', 'UserAddressController');

    Route::post('userrank/changes', 'UserRankController@changes');
    Route::resource('userrank', 'UserRankController');

    Route::post('regfields/changes', 'RegFieldsController@changes');
    Route::resource('regfields', 'RegFieldsController');

    Route::post('usersreal/changes', 'UsersRealController@changes');
    Route::resource('usersreal', 'UsersRealController');

    Route::get('uaccount/recharge', 'UserAccountController@recharge');
    Route::resource('uaccount', 'UserAccountController');

    Route::post('privilege/distribution/{id}', 'PrivilegeController@distribution');
    Route::post('privilege/checkname', 'PrivilegeController@checkUserName');
    Route::resource('privilege', 'PrivilegeController');

    Route::get('database/optimize', 'DataBaseController@optimize');
    Route::post('database/runoptimize', 'DataBaseController@runOptimize');
    Route::resource('database', 'DataBaseController');

    Route::get('order/paymentedit/{id}', 'OrderController@paymentEdit');
    Route::get('order/expressedit/{id}', 'OrderController@expressEdit');
    Route::get('order/consigneeedit/{id}', 'OrderController@consigneeEdit');
    Route::get('order/otheredit/{id}', 'OrderController@otherEdit');
    Route::get('order/moneyedit/{id}', 'OrderController@moneyEdit');
    Route::get('order/feeedit/{id}', 'OrderController@feeEdit');
    Route::get('order/nopay/{id}', 'OrderController@nopayEdit');
    Route::get('order/delivery/{id}', 'OrderController@orderDelivery');
    Route::get('order/delivery/info/{id}', 'OrderController@deliveryInfo');
    Route::post('order/delivery/del/{id}', 'OrderController@deliveryDel');
    Route::post('order/delivery/change', 'OrderController@deliveryChange');
    Route::get('order/return/{id}', 'OrderController@orderReturn');
    Route::get('order/return/info/{id}', 'OrderController@returnInfo');
    Route::get('order/return/refound/{id}', 'OrderController@returnRefound');
    Route::post('order/return/change', 'OrderController@returnChange');
    Route::get('order/delivery/info/{id}', 'OrderController@deliveryInfo');
    Route::post('order/changes', 'OrderController@changes');
    Route::post('order/change', 'OrderController@change');
    Route::get('order/{fn}/{seller}/{nav}/{id}', 'OrderController@getFaatOrders');

    Route::resource('order', 'OrderController');

    Route::post('returncause/change', 'ReturnCauseController@change');
    Route::resource('returncause', 'ReturnCauseController');

    Route::get('store/privilege', 'StoreController@privilegeEdit');
    Route::post('store/searchpriv', 'StoreController@searchSellerGradesByPri');
    Route::post('store/allot/', 'StoreController@allot');
    Route::resource('store', 'StoreController');

    Route::get('sellergrade/priv', 'SellerGradeController@privilegeEdit');
    Route::post('sellergrade/change', 'SellerGradeController@change');
    Route::resource('sellergrade', 'SellerGradeController');

    Route::resource('entrycriteria', 'EntryCriteriaController');

    Route::get('storelist/real', 'StoreListController@real');
    Route::get('storelist/geo/{id}', 'StoreListController@geoModal');
    Route::get('storelist/priv/{id}', 'StoreListController@privilegeEdit');
    Route::post('storelist/priv/allot', 'StoreListController@privAllot');
    Route::get('storelist/info/{id}', 'StoreListController@info');
    Route::post('storelist/setinfo/{id}', 'StoreListController@setShopInfo');
    Route::post('storelist/change', 'StoreListController@change');
    Route::post('storelist/cate/add', 'StoreListController@addCate');
    Route::post('storelist/cate/del/{id}', 'StoreListController@delCate');
    Route::resource('storelist', 'StoreListController');

    Route::post('msp/change', 'MerchantsStepsProcessController@change');
    Route::resource('msp', 'MerchantsStepsProcessController');

    Route::resource('applyprocess', 'ApplyProcessController');

    Route::resource('sellerdomain', 'SellerDomainController');

    Route::get('ad/add/{id}', 'AdvertiseController@adAdd');
    Route::get('ad/edit/{id}/{type}', 'AdvertiseController@adEdit');
    Route::get('ad/adshow/{type}/{id}', 'AdvertiseController@adShow');
    Route::post('ad/change', 'AdvertiseController@change');
    Route::resource('ad', 'AdvertiseController');

    Route::post('adspos/change', 'AdvertisePositionController@change');
    Route::get('adspos/adstype/{id}', 'AdvertisePositionController@adsType');
    Route::resource('adspos', 'AdvertisePositionController');

    Route::resource('adstype', 'AdvertiseTypeController');

    Route::post('favourable/change', 'FavourableController@change');
    Route::resource('favourable', 'FavourableController');

    Route::post('coupons/change', 'CouponsController@change');
    Route::resource('coupons', 'CouponsController');

    Route::get('bonus/user/{id}', 'BonusController@bonusUser');
    Route::get('bonus/send/{id}', 'BonusController@send');
    Route::post('bonus/change', 'BonusController@change');
    Route::post('bonus/delbu', 'BonusController@delBonusUser');
    Route::post('bonus/adduser', 'BonusController@addBonusUser');
    Route::resource('bonus', 'BonusController');

    Route::post('satistics/getsat', 'SatisticsController@getSatistics');
    Route::post('satistics/geojson', 'SatisticsController@getGeoJson');
    Route::get('satistics/order', 'SatisticsController@order');
    Route::get('satistics/user', 'SatisticsController@user');
    Route::get('satistics/user/area', 'SatisticsController@userArea');
    Route::get('satistics/user/rank', 'SatisticsController@userRank');
    Route::get('satistics/user/consumption', 'SatisticsController@userConsumption');
    Route::get('satistics/industry', 'SatisticsController@industry');
    Route::post('satistics/order/total', 'SatisticsController@getOrderTotal');
    Route::post('satistics/amount/total', 'SatisticsController@getAmountTotal');

    Route::post('mobilenav/change', 'MobileNavigationController@change');
    Route::resource('mobilenav', 'MobileNavigationController');

    Route::get('mobileoauth/addauth/{id}', 'MobileOAuthController@addAuth');
    Route::resource('mobileoauth', 'MobileOAuthController');

    Route::resource('wechatconfig', 'WechatConfigController');
    Route::resource('wechat', 'WechatController');

    Route::resource('wxappconfig', 'WxappConfigController');
    Route::resource('wxappsession', 'WxappSessionController');
    Route::post('wxappsession/send/msg', 'WxappSessionController@sendMsg');
    Route::post('wxappsession/config/get', 'WxappSessionController@getWxappConfig');
    Route::post('wxappsession/media/upload', 'WxappSessionController@uploadMedia');

    Route::resource('sms', 'SmsController');

    Route::post('alidayu/temp', 'AlidayuController@temp');
    Route::resource('alidayu', 'AlidayuController');

    Route::post('alisms/temp', 'AlismsController@temp');
    Route::resource('alisms', 'AlismsController');

    Route::resource('oss', 'OssController');

    Route::resource('interface', 'InterfaceController');

    Route::get('dialog/merchants/brand/{id}', 'DialogController@merchantsBrand');
    Route::get('dialog/merchants/brand/edit/{id}', 'DialogController@editMerchantsBrand');
    Route::post('dialog/merchants/brand/modify', 'DialogController@modifyMerchantsBrand');
    Route::post('dialog/merchants/brand/del/{id}', 'DialogController@delMerchantsBrand');

    Route::get('dialog/goods/search/{id}', 'DialogController@goodsSearch');

    Route::resource('comment', 'CommentController');
    Route::post('comment/change', 'CommentController@change');

    Route::resource('qa', 'QuestionAnswerController');
    Route::post('qa/change', 'QuestionAnswerController@change');

    Route::resource('email', 'EmailController');
    Route::post('email/test/sendmail', 'EmailController@testSendMail');
    Route::post('email/send', 'EmailController@sendMail');

    Route::resource('cron', 'CronController');
    Route::post('cron/change', 'CronController@change');
    Route::post('cron/implement', 'CronController@implement');

    Route::resource('commentlabel', 'CommentLabelController');

    Route::resource('goodsdesc', 'GoodsDescriptionController');

    Route::post('notify/change', 'NotifyController@change');
    Route::resource('notify', 'NotifyController');

    Route::post('artcate/change', 'ArticleCateController@change');
    Route::post('artcate/getcates/{id}', 'ArticleCateController@getCates');
    Route::resource('artcate', 'ArticleCateController');

    Route::post('article/change', 'ArticleController@change');
    Route::post('article/change/goods', 'ArticleController@changeGoods');
    Route::resource('article', 'ArticleController');

    Route::post('groupbuy/change', 'GroupBuyController@change');
    Route::resource('groupbuy', 'GroupBuyController');

    Route::post('seckill/change', 'SecondKillController@change');
    Route::resource('seckill', 'SecondKillController');

    Route::resource('seckilltime', 'SecondKillTimeBucketController');

    Route::get('seckillgoods/{sid}/{stid}', 'SecondKillGoodsController@getSecGoods');
    Route::post('seckillgoods/change', 'SecondKillGoodsController@change');
    Route::resource('seckillgoods', 'SecondKillGoodsController');

    Route::post('adminuser/change/logo', 'AdminUserController@changeLogo');

    Route::get('team/info/{id}', 'TeamController@getTeamInfo');
    Route::post('team/change', 'TeamController@change');
    Route::resource('team', 'TeamController');

    Route::post('teamcate/change', 'TeamCategoryController@change');
    Route::resource('teamcate', 'TeamCategoryController');

});