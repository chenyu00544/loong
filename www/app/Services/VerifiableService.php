<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use Illuminate\Support\Facades\Validator;

class VerifiableService
{
    public static function loginVer($input, $lang, $rules = ["username" => 'required', "password" => 'required'])
    {
        $message = [
            "username.required" => $lang['username_required'],
            "password.required" => $lang['password_required'],
            "captcha.required" => $lang['captcha_required'],
        ];

        return Validator::make($input, $rules, $message);
    }

    public static function Validator($input, $rules)
    {
        $message = [];
        foreach ($rules as $key => $val) {
            $message[$key . '.' . $val] = $key . "不能为空";
        }
        return Validator::make($input, $rules, $message);
    }

    public static function authorization($request){
        if(empty($request->header()[strtolower('vcvbuy-authorization')][0])){
            return '';
        }else{
            return decrypt($request->header()[strtolower('vcvbuy-authorization')][0]);
        }
    }

}