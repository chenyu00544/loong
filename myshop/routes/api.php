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
});