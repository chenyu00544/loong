<?php
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::group(['prefix' => 'wx', 'namespace' => 'Shop\Wx'], function () {
    Route::get('/', function () {
        return [
            'status' => 'success',
            'message' => 'API version 1.'
        ];
    });

    Route::post('version', function () {
        return [
            'code' => '0',
            'msg' => '',
            'version_num' => '1.0.0'
        ];
    });

    Route::any('index', 'IndexController@index');
    Route::post('index/loadmore', 'IndexController@loadmore');
    Route::post('index/boot/page', 'IndexController@getBootPages');
    Route::any('test', 'IndexController@test');

    Route::post('goods/index', 'GoodsController@index');
    Route::post('goods/detail', 'GoodsController@detail');
    Route::post('goods/property', 'GoodsController@property');

    Route::post('sms/send', 'SmsController@send');

    Route::post('cart/index', 'CartController@index');
    Route::post('cart/add', 'CartController@addCart');
    Route::post('cart/set', 'CartController@setCart');
    Route::post('cart/del', 'CartController@delCart');

    Route::post('login/index', 'LoginController@index');
    Route::post('login/silent', 'LoginController@silent');
    Route::post('login/reg', 'LoginController@register');
    Route::post('login/deviceid', 'LoginController@getDeviceId');
    Route::post('login/bind/phone', 'LoginController@bindPhone');

    Route::post('collect/goods/add', 'CollectController@collectGoods');
    Route::post('collect/goods/cart', 'CollectController@collectGoodsToCart');
    Route::post('collect/goodses', 'CollectController@colloectsByGoods');
    Route::post('collect/brand/add', 'CollectController@collectBrand');
    Route::post('collect/store/add', 'CollectController@collectStore');
    Route::post('collect/browse', 'CollectController@browseByGoods');
    Route::post('collect/browse/set', 'CollectController@setBowse');

    Route::post('user/index', 'UserController@index');
    Route::post('user/set', 'UserController@setUser');
    Route::post('user/addresses', 'UserController@addresses');
    Route::post('user/address/get', 'UserController@getAddress');
    Route::post('user/address/setdef', 'UserController@setDefaultAddress');
    Route::post('user/address/add', 'UserController@addAddress');
    Route::post('user/address/set', 'UserController@setAddress');
    Route::post('user/address/del', 'UserController@delAddress');
    Route::post('user/real', 'UserController@real');
    Route::post('user/real/set', 'UserController@setReal');
    Route::post('user/real/up/img', 'UserController@uploadCartImg');

    Route::post('order/index', 'OrderController@index');
    Route::post('order/get', 'OrderController@getOrder');
    Route::post('order/add', 'OrderController@addOrder');
    Route::post('order/cancel', 'OrderController@cancelOrder');
    Route::post('order/after/sale', 'OrderController@afterSaleOrder');
    Route::post('order/after/sale/orders', 'OrderController@afterSaleOrders');
    Route::post('order/return/goods', 'OrderController@returnGoods');
    Route::post('order/logistics/get', 'OrderController@getLogisticsInfo');
    Route::post('order/confirm/take', 'OrderController@confirmTakeOrder');

    Route::post('pay/wxpay', 'PayController@wxPayApp');
    Route::post('pay/wxpay/async/notify', 'PayController@asyncWxNotify');

    Route::post('category/index', 'CategoryController@index');
    Route::post('category/goods', 'CategoryController@categoryGoods');
    Route::post('category/goods/loadmore', 'CategoryController@goodsLoadMore');

    Route::post('search/index', 'SearchController@index');
    Route::post('search/filter/by', 'SearchController@getFilterBySearch');
    Route::post('search/filter/to', 'SearchController@getFilterToSearch');
    Route::post('search/keywords', 'SearchController@getSearchKeywords');
    Route::post('search/keywords/change', 'SearchController@changeKeywords');

    Route::post('notify/index', 'NotifyController@index');
    Route::post('notify/get', 'NotifyController@getNotify');
    Route::post('notify/ids', 'NotifyController@getNotifiesForIds');

    Route::post('faat/index', 'FaatController@index');
    Route::post('faat/brand', 'FaatController@brand');
    Route::post('faat/seckill', 'FaatController@secondKill');
    Route::post('faat/group/buy', 'FaatController@groupBuy');

    Route::post('team/nav', 'TeamController@teamNav');
    Route::post('team', 'TeamController@team');
    Route::post('team/rank', 'TeamController@teamRank');
    Route::post('team/orders', 'TeamController@teamOrders');
    Route::post('team/goods', 'TeamController@teamGoods');
    Route::post('team/buy', 'TeamController@teamBuy');
    Route::post('team/wait', 'TeamController@teamWait');
    Route::post('team/ranking', 'TeamController@teamRanking');
    Route::post('team/user', 'TeamController@teamUser');
    Route::post('team/best', 'TeamController@teamIsBest');

    Route::post('comment/label', 'CommentController@label');
    Route::post('comment/list', 'CommentController@getComments');
    Route::post('comment/add', 'CommentController@addComment');
    Route::post('comment/up/img', 'CommentController@uploadCommentImg');

    Route::post('brand/index', 'BrandController@index');
});
