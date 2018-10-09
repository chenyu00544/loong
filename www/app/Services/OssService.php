<?php

namespace App\Services;

use App\Facades\RedisCache;
use App\Http\Models\Shop\AliossConfigureModel;
use OSS\Core\OssException;
use OSS\OssClient;

class OssService
{
    private static $accessKeyId;
    private static $accessKeySecret;
    private static $endpoint;
    private static $bucket;
    private static $ossClient;

    public function __construct()
    {
        if (!isset(self::$ossClient)) {
            $oss = RedisCache::get('oss_config');
            if (!$oss) {
                $oss = (new AliossConfigureModel())->getAlioss(['is_use' => 1]);
                if ($oss) {
                    $oss = $oss->toArray();
                    RedisCache::set('oss_config', $oss);
                }
            }
            if ($oss) {
                $regional = substr($oss['regional'], 0, 2);
                if ($regional == 'us' || $regional == 'ap') {
                    $outside_site = 'http://' . "oss-" . $oss['regional'] . ".aliyuncs.com";
                    $inside_site = 'http://' . "oss-" . $oss['regional'] . "-internal.aliyuncs.com";
                } else {
                    $outside_site = 'http://' . "oss-cn-" . $oss['regional'] . ".aliyuncs.com";
                    $inside_site = 'http://' . "oss-cn-" . $oss['regional'] . "-internal.aliyuncs.com";
                }
                self::$accessKeyId = $oss['keyid'];
                self::$accessKeySecret = $oss['keysecret'];
                self::$endpoint = $inside_site;
                self::$bucket = $oss['bucket'];
                try {
                    self::$ossClient = new OssClient(self::$accessKeyId, self::$accessKeySecret, self::$endpoint);
                } catch (OssException $e) {
                    printf(__FUNCTION__ . ": FAILED\n");
                    printf($e->getMessage() . "\n");
                }
            }
        }
        return self::$ossClient;
    }

    public static function uploadObject($object, $filePath)
    {
        if (self::$ossClient) {
            try {
                self::$ossClient->uploadFile(self::$bucket, $object, $filePath);
            } catch (OssException $e) {
                printf(__FUNCTION__ . ": FAILED\n");
                printf($e->getMessage() . "\n");
            }
            return $object;
        }
        return 0;
    }

    public static function deleteObject($object)
    {
        self::$ossClient->deleteObject(self::$bucket, $object);
    }

    public static function existObject($object)
    {
        return self::$ossClient->doesObjectExist(self::$bucket, $object);
    }

    public static function getEndpoint()
    {
        if (self::$endpoint) {
            return self::$endpoint;
        } else {
            return url('/');
        }
    }
}
