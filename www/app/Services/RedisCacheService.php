<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\DB;

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

    public function incr($key)
    {
        if (self::$redis != 'connect_failed') {
            if (!self::$redis->get($key)) {
                $gid = Cache::get($key);
                self::$redis->set($key, $gid + 1);
                $guid = $gid + 1;
            } else {
                $guid = self::$redis->incr($key);
            }
            Cache::forever($key, $guid);
            return $guid;
        } else {
            $guid = Cache::get($key) + 1;
            Cache::forever($key, $guid);
            return $guid;
        }
    }

    //获取分布式自增ID SEED 然后自增
    public function incrby($key)
    {
        $seed = 1;
        if (self::$redis != 'connect_failed') {
            if (!self::$redis->get($key)) {
                $gid = Cache::get($key);
                self::$redis->set($key, $gid + $seed);
                $guid = $gid + $seed;
            } else {
                $guid = self::$redis->incrby($key, $seed);
            }
            DB::table('global_key_id')->where(['key_id' => 1])->update([$key => $guid]);
            Cache::forever($key, $guid);
            return $guid;
        } else {
            $guid = Cache::get($key) + $seed;
            Cache::forever($key, $guid);
            return $guid;
        }
    }

    //获取分布式自增ID
    public function getIncr($key)
    {
        if (self::$redis != 'connect_failed') {
            if (!self::$redis->get($key)) {
                $gid = Cache::get($key);
                self::$redis->set($key, $gid);
                $guid = $gid;
            } else {
                $guid = self::$redis->get($key);
            }
            if(!$guid){
                $re = DB::table('global_key_id')->where(['key_id' => 1])->first();
                $guid = $re->$key;
            }
            return $guid;
        } else {
            $guid = Cache::get($key);
            return $guid;
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