<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use Illuminate\Support\Facades\Cache;

class RedisCacheService
{
    private static $redis;

    private static function redis()
    {
        if (!self::$redis) {
            self::$redis = new \Redis();
            if (!self::$redis->pconnect('127.0.0.1', 6379, 1)) {
                self::$redis = null;
                return self::$redis;
            }
            dd(self::$redis);
        }
        return self::$redis;
    }

    public static function get($key)
    {
        if (self::redis()) {
            $value = self::redis()->get($key);
            $value_serl = @unserialize($value);
            if (is_object($value_serl) || is_array($value_serl)) {
                return $value_serl;
            }
            return $value;
        } else {
            return Cache::get($key);
        }
    }

    public static function set($key, $value)
    {
        if (self::redis()) {
            if (is_object($value) || is_array($value)) {
                $value = serialize($value);
            }
            return self::redis()->set($key, $value);
        } else {
            Cache::forever($key, $value);
        }
    }

    public static function setex($key, $value, $min = 0)
    {
        if (self::redis()) {
            if (is_object($value) || is_array($value)) {
                $value = serialize($value);
            }
            return self::redis()->setex($key, $min * 60, $value);
        } else {
            Cache::put($key, $value, $min);
        }
    }

    public static function del($key)
    {
        if (self::redis()) {
            self::redis()->del($key);
        } else {
            Cache::forget($key);
        }
    }

    public static function flushdb()
    {
        if (self::redis()) {
            self::redis()->flushDB();
        } else {
            Cache::flush();
        }
    }
}