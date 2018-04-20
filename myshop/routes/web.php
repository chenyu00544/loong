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

Route::get('/', function () {
    return view('welcome');
});

Route::get('foo', function () {
    return 'Hello World';
});

/*
 **********
 *
 * shop前端路由
 *
 **********
*/
Route::group(['namespace' => 'Shop\Home'], function () {
    Route::any('/', 'IndexController@index');
    Route::any('test', 'TestController@index');
});
/*
 **********
 *
 * shop后台路由
 *
 **********
*/

Route::group(['prefix' => 'admin', 'namespace' => 'Shop\Admin'], function () {
    Route::any('login', 'LoginController@login');
    Route::get('chang', 'LoginController@chang');
    Route::get('tool', 'LoginController@tool');
});

Route::group(['middleware' => ['admin.login'], 'prefix' => 'admin', 'namespace' => 'Shop\Admin'], function () {
    Route::get('index', 'IndexController@index');
    Route::get('info', 'IndexController@info');

//    Route::get('shopsetup', 'ShopConfController@index');
    Route::resource('shopconf', 'ShopConfController');

    Route::post('navsetup/show/or/view', 'NavigationController@showOrView');
    Route::post('navsetup/chang/order', 'NavigationController@changOrder');
    Route::resource('navsetup', 'NavigationController');

    Route::post('goodsconf/chang', 'GoodsConfigController@chang');
    Route::resource('goodsconf', 'GoodsConfigController');

    Route::post('comcate/chang', 'ComCateController@chang');
    Route::post('comcate/getcates/{id}', 'ComCateController@getCates');
    Route::any('comcate/add/cate/{id}', 'ComCateController@addCate');
    Route::resource('comcate', 'ComCateController');

    Route::post('brand/chang', 'BrandController@chang');
    Route::post('brand/firstchar', 'BrandController@getFirstChar');
    Route::resource('brand', 'BrandController');
});