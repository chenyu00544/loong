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
    Route::any('all/format', 'RegionsController@getAllRegions');
    Route::any('test', 'RegionsController@test');

    Route::any('captcha', 'CaptchaController@index');
});

Route::group(['prefix' => 'web', 'namespace' => 'Shop\Api'], function () {
    Route::any('captcha/{id}', 'CaptchaController@index');
});

Route::group(['prefix' => 'wx', 'namespace' => 'Shop\Api'], function () {
    Route::any('wxapp/server', 'WxController@wxappServer');
    Route::any('wxchat/server', 'WxController@wxchatServer');
});
