<?php
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

//http://www.xxx.com/api/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});


Route::get('/wechat', function (Request $request) {
    return 1111;
});

Route::group(['prefix' => 'region', 'namespace' => 'Shop\Api'], function () {
    Route::any('getCountries', 'RegionsController@getCountries');
    Route::any('getProvinces', 'RegionsController@getProvinces');
    Route::any('getCities', 'RegionsController@getCities');
    Route::any('geo/coder', 'RegionsController@getGeoCoder');

    Route::any('captcha', 'CaptchaController@index');
});

Route::group(['prefix' => 'web', 'namespace' => 'Shop\Api'], function () {
    Route::any('captcha/{id}', 'CaptchaController@index');
});

Route::group(['prefix' => 'app', 'namespace' => 'Shop\App'], function () {
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

    Route::post('collect/goods', 'CollectController@collectGoods');
    Route::post('collect/brand', 'CollectController@collectBrand');
    Route::post('collect/store', 'CollectController@collectStore');
});