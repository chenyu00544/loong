<?php

namespace App\Helper;

use OSS\Core\OssException;
use OSS\OssClient;

class Oss
{
    private $accessKeyId;
    private $accessKeySecret;
    private $endpoint;
    private $bucket;
    private $ossClient;

    public function __construct()
    {
        $oss = RedisCache::get('oss');
        if ($oss) {
            $this->accessKeyId = $oss['keyid'];
            $this->accessKeySecret = $oss['keysecret'];
            $this->endpoint = $oss['endpoint'];
            $this->bucket = $oss['bucket'];
            $this->ossClient = new OssClient($this->accessKeyId, $this->accessKeySecret, $this->endpoint);
        }
    }

    public function uploadObject($object, $filePath)
    {
        if ($this->ossClient) {
            try {
                $this->ossClient->uploadFile($this->bucket, $object, $filePath);
            } catch (OssException $e) {
                printf(__FUNCTION__ . ": FAILED\n");
                printf($e->getMessage() . "\n");
            }
            return $object;
        }
        return 0;
    }

    public function deleteObject($object)
    {
        $this->ossClient->deleteObject($this->bucket, $object);
    }

    public function existObject($object)
    {
        return $this->ossClient->doesObjectExist($this->bucket, $object);
    }
}
