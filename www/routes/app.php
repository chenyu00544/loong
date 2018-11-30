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
Route::group(['prefix' => 'app', 'namespace' => 'Shop\App'], function () {
    Route::get('/', function () {
        return [
            'status' => 'success',
            'message' => 'API version 1.'
        ];
    });
    Route::any('index', 'IndexController@index');
    Route::post('index/loadmore', 'IndexController@loadmore');
    Route::post('test', 'IndexController@test');

    Route::post('goods/detail', 'GoodsController@detail');

    Route::post('sms/send', 'SmsController@send');

    Route::post('cart/index', 'CartController@index');
    Route::post('cart/add', 'CartController@addCart');
    Route::post('cart/set', 'CartController@setCart');
    Route::post('cart/del', 'CartController@delCart');

    Route::post('login/index', 'LoginController@index');
    Route::post('login/reg', 'LoginController@register');
    Route::post('login/deviceid', 'LoginController@getDeviceId');

    Route::post('collect/goods/add', 'CollectController@collectGoods');
    Route::post('collect/goodses', 'CollectController@colloectsByGoods');
    Route::post('collect/cartgoods', 'CollectController@collectCartGoods');
    Route::post('collect/brand/add', 'CollectController@collectBrand');
    Route::post('collect/store/add', 'CollectController@collectStore');

    Route::post('user/index', 'UserController@index');
    Route::post('user/addresses', 'UserController@addresses');
    Route::post('user/address/get', 'UserController@getAddress');
    Route::post('user/address/setdef', 'UserController@setDefaultAddress');
    Route::post('user/address/add', 'UserController@addAddress');
    Route::post('user/address/set', 'UserController@setAddress');
    Route::post('user/address/del', 'UserController@delAddress');
    Route::post('user/real', 'UserController@real');
    Route::post('user/real/set', 'UserController@setReal');

    Route::post('order/index', 'OrderController@index');
    Route::post('order/get', 'OrderController@getOrder');
    Route::post('order/add', 'OrderController@addOrder');
    Route::post('order/cancel', 'OrderController@cancelOrder');

    Route::post('pay/alipay', 'PayController@aliPay');
    Route::post('pay/wxpay', 'PayController@wxPay');
    Route::post('pay/unionpay', 'PayController@unionPay');
});
