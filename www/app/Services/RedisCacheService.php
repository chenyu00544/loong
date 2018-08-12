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

    public function __construct()
    {
        if (!isset(self::$redis)) {
            self::$redis = new \Redis();
            if (!self::$redis->pconnect('127.0.0.1', 6379, 1)) {
                self::$redis = 'connect_failed';
            }
        }
        return self::$redis;
    }

    public static function get($key)
    {
        if (self::$redis != 'connect_failed') {
            $value = self::$redis->get($key);
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
        if (self::$redis != 'connect_failed') {
            if (is_object($value) || is_array($value)) {
                $value = serialize($value);
            }
            return self::$redis->set($key, $value);
        } else {
            Cache::forever($key, $value);
        }
    }

    public static function setex($key, $value, $min = 0)
    {
        if (self::$redis != 'connect_failed') {
            if (is_object($value) || is_array($value)) {
                $value = serialize($value);
            }
            return self::$redis->setex($key, $min * 60, $value);
        } else {
            Cache::put($key, $value, $min);
        }
    }

    public static function del($key)
    {
        if (self::$redis != 'connect_failed') {
            self::$redis->del($key);
        } else {
            Cache::forget($key);
        }
    }

    public static function flushdb()
    {
        if (self::$redis != 'connect_failed') {
            self::$redis->flushDB();
        } else {
            Cache::flush();
        }
    }
}