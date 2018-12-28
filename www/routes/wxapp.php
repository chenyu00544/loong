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
Route::group(['prefix' => 'app', 'namespace' => 'Shop\Wx'], function () {
    Route::get('/', function () {
        return [
            'status' => 'success',
            'message' => 'API version 1.'
        ];
    });

    Route::any('index', 'IndexController@index');
});
