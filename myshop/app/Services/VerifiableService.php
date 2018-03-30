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
    public static function loginVer($input, $lang)
    {
        $rules = [
            "username" =>'required',
            "password" =>'required',
        ];

        $message = [
            "username.required" =>$lang['username_required'],
            "password.required" =>$lang['password_required'],
        ];

        return Validator::make($input, $rules, $message);
    }

}