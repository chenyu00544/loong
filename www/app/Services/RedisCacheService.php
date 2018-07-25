<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

class RedisCacheService
{
    private static $redis;

    public static function test()
    {
        echo 111;
    }

    private static function redis()
    {
        if (!self::$redis) {
            self::$redis = app('redis.connection');
        }
        return self::$redis;
    }

    public static function get($key)
    {
        $value = self::redis()->get($key);
        $value_serl = @unserialize($value);
        if (is_object($value_serl) || is_array($value_serl)) {
            return $value_serl;
        }
        return $value;
    }

    public static function set($key, $value)
    {
        if (is_object($value) || is_array($value)) {
            $value = serialize($value);
        }
        return self::redis()->set($key, $value);
    }

    public static function setex($key, $value, $min = 0)
    {
        if (is_object($value) || is_array($value)) {
            $value = serialize($value);
        }
        return self::redis()->setex($key, $min * 60, $value);
    }
}