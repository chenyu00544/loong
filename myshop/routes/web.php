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
    Route::get('change', 'LoginController@change');
    Route::get('tool', 'LoginController@tool');
});

Route::group(['middleware' => ['admin.login'], 'prefix' => 'admin', 'namespace' => 'Shop\Admin'], function () {
    Route::get('index', 'IndexController@index');
    Route::get('info', 'IndexController@info');

//    Route::get('shopsetup', 'ShopConfController@index');
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
    Route::post('brand/firstchar', 'BrandController@getFirstChar');
    Route::resource('brand', 'BrandController');


    Route::get('goods/backto/{id}', 'GoodsController@backTo');
    Route::get('goods/del/{id}', 'GoodsController@thoroughDel');
    Route::get('goods/examine/{id}', 'GoodsController@examine');
    Route::get('goods/weight/order/{id}', 'GoodsController@weightOrder');
    Route::get('goods/cateextend/{id}', 'GoodsController@cateExtend');
    Route::post('goods/change', 'GoodsController@change');
    Route::post('goods/changes', 'GoodsController@changes');
    Route::post('goods/addcateext', 'GoodsController@addCateExtend');
    Route::post('goods/delcateext/{id}', 'GoodsController@delCateExtend');
    Route::resource('goods', 'GoodsController');

    Route::post('goodstype/changes', 'GoodsTypeController@changes');
    Route::resource('goodstype', 'GoodsTypeController');

    Route::post('typecate/change', 'GoodsTypeCateController@change');
    Route::post('typecate/getcates/{id}', 'GoodsTypeCateController@getCates');
    Route::resource('typecate', 'GoodsTypeCateController');

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
});