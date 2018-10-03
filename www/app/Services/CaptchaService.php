<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use Gregwar\Captcha\CaptchaBuilder;
use Gregwar\Captcha\PhraseBuilder;
use Illuminate\Support\Facades\Cache;

class CaptchaService
{
    //composer require gregwar/captcha
    //"gregwar/captcha": "1.*"
    //composer update
    public static function entry()
    {
        ob_clean();
        $phrase = new PhraseBuilder();
        // 设置验证码位数
        $code = $phrase->build(4);
        // 生成验证码图片的Builder对象，配置相应属性
        $builder = new CaptchaBuilder($code, $phrase);
        // 设置背景颜色
        $builder->setBackgroundColor(212, 251, 255);
        $builder->setMaxAngle(25);
        $builder->setMaxBehindLines(10);
        $builder->setMaxFrontLines(10);
        // 可以设置图片宽高及字体
        $builder->build($width = 120, $height = 40, $font = null);
        // 获取验证码的内容
        $phrase = $builder->getPhrase();
        Cache::put('captcha', $phrase, 30);

        //生成图片
        //清除缓存
        header("Cache-Control: no-cache, must-revalidate");
        header('Content-type: image/jpeg');
        $builder->output();
    }

    /**
     * 验证验证码是否正确
     * @access public
     * @param string $code 用户验证码
     * @param string $type 是否是ajax传值  by kong
     * @return bool 用户验证码是否正确
     */
    public static function check($code = '', $key = 'captcha', $type = '')
    {
        // 验证码不能为空
        $secode = Cache::get($key);
        if (empty($code) || empty($secode)) {
            return false;
        }

        if ($secode == $code) {
            return true;
        } else {
            return false;
        }
    }
}