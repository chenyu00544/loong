<?php

namespace App\Services;

use App\Facades\Oss;
use App\Facades\RedisCache;
use App\Helper\FileHelper;
use Mockery\Exception;
use OSS\Core\OssException;

class FileHandleService
{
    //上传文件
    public static function upLoad_file($file, $uri)
    {
        $filename = self::mictime() . rand(10000, 99999) . '.' . $file->getClientOriginalExtension();
        $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;
        $shopConf = RedisCache::get('shop_config');
        if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
            //对象存储
            //文件名称
            $object = 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            //由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt
            $filePath = $file->getRealPath();
            return Oss::uploadObject($object, $filePath);
        } else {
            if ($path = $file->move($dir, $filename)) {
                return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            }
        }
        return 0;
    }

    //上传MP4
    public static function upLoadFile($file, $uri)
    {
        $bool = $file->getClientOriginalExtension() == 'mp4' || $file->getClientOriginalExtension() == 'MP4';
        if ($bool) {
            $filename = self::mictime() . rand(10000, 99999) . '.' . $file->getClientOriginalExtension();
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;
            $shopConf = RedisCache::get('shop_config');
            if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
                //对象存储
                //文件名称
                $object = 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
                //由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt
                $filePath = $file->getRealPath();
                return Oss::uploadObject($object, $filePath);
            } else {
                if ($path = $file->move($dir, $filename)) {
                    return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
                }
            }
        }
        return 0;
    }

    //上传原图
    public static function upLoadImage($file, $uri)
    {
        $bool = self::checkFile($file);
        if ($bool) {
            $filename = self::mictime() . rand(10000, 99999) . '.' . $file->getClientOriginalExtension();
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;
            $shopConf = RedisCache::get('shop_config');
            if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
                //对象存储
                //文件名称
                $object = 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
                //由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt
                $filePath = $file->getRealPath();
                return Oss::uploadObject($object, $filePath);
            } else {
                if ($path = $file->move($dir, $filename)) {
                    return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
                }
            }
        }
        return 0;
    }

    //上传缩略图
    public static function upLoadThumbImage($sourceUri, $uri)
    {
        $sourceDir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . $sourceUri;
        $bool = file_exists($sourceDir);
        if ($bool) {
            list($width, $height, $e) = getimagesize($sourceDir);
            $ext = ['gif', 'jpg', 'png', 'swf', 'psd', 'bmp'];
            $img = self::thumpImage($sourceDir, ['width' => $width, 'height' => $height], 0, ['width' => 108, 'height' => 108], $ext[$e - 1]);

            $filename = self::mictime() . rand(10000, 99999) . '.' . $ext[1];
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;
            if (!file_exists($dir)) {
                mkdir($dir, 0777, true);
            }
            $object = 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            if ($path = imagejpeg($img, $dir . $filename)) {
                $shopConf = RedisCache::get('shop_config');
                if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
                    return Oss::uploadObject($object, $sourceDir);
                }
                return $object;
            }
        }
        return 0;
    }

    //上传展示图
    public static function upLoadExhibitionImage($sourceUri, $uri, $percent)
    {
        $sourceDir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . $sourceUri;
        $bool = file_exists($sourceDir);
        if ($bool) {
            list($width, $height, $e) = getimagesize($sourceDir);
            $ext = ['gif', 'jpg', 'png', 'swf', 'psd', 'bmp'];
            $img = self::thumpImage($sourceDir, ['width' => $width, 'height' => $height], $percent, [], $ext[$e - 1]);

            $filename = self::mictime() . rand(10000, 99999) . '.' . $ext[1];
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;
            if (!file_exists($dir)) {
                mkdir($dir, 0777, true);
            }
            $object = 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            if ($path = imagejpeg($img, $dir . $filename)) {
                $shopConf = RedisCache::get('shop_config');
                if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
                    return Oss::uploadObject($object, $sourceDir);
                }
                return $object;
            }
        }
        return 0;
    }

    //获取oss链接
    public static function getImgByOssUrl($uri)
    {
        $shopConf = RedisCache::get('shop_config');

        //uri图片链接
        if (!empty($uri) && (strpos($uri, 'http://') === false && strpos($uri, 'https://') === false && strpos($uri, 'errorImg.png') === false)) {
            if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
                $oss = RedisCache::get('oss_config');
                return $oss['endpoint'] . $uri;
            } else {
                return url($uri);
            }
        }

        //完整图片链接
        if (strtolower(substr($uri, 0, 4)) == 'http') {
            return $uri;
        }

        //没有图片链接设置默认图片
        $goodsConf = RedisCache::get('goods_config');
        $no_picture = isset($goodsConf['no_picture']) && !empty($goodsConf['no_picture']) ? str_replace("../", "", $goodsConf['no_picture']) : '';
        $url = empty($uri) ? $no_picture : $uri;
        return $goodsConf['two_code_links'] . $url;
    }

    public static function deleteFile($file_path)
    {
        $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . $file_path;
        if (file_exists($dir)) {
            @unlink($dir);
        }
        $shopConf = RedisCache::get('shop_config');
        if (!empty($shopConf['open_oss']) && $shopConf['open_oss'] == 1) {
            try {
                Oss::deleteObject($file_path);
            } catch (OssException $e) {

            }
        }
    }

    //获取图片路径
    public static function getImagesByDir($path, $uri)
    {
        $files = array();
        if (@$handle = opendir($path . $uri)) {
            while (($file = readdir($handle)) !== false) {
                //排除根目录
                if ($file != ".." && $file != ".") {
                    if (is_dir($path . $uri . "/" . $file)) {
                        //如果是子文件夹，就进行递归
                        $files[$file] = self::getImagesByDir($path, $uri . "/" . $file);
                    } else {
                        //将文件的名字存入数组；
                        $files[] = $uri . "/" . $file;
                    }

                }
            }
            closedir($handle);
            return $files;
        }
    }

    private static function checkFile($file)
    {
        /* 允许上传的文件类型 */
        $allow_file_types = '|GIF|JPG|PNG|BMP|SWF|DOC|XLS|PPT|MID|WAV|ZIP|RAR|PDF|CHM|RM|TXT|CERT|';

        $tmpName = $file->getFileName();
        $filename = $file->getClientOriginalName();

        if ($file->isValid()) {
            if (!FileHelper::checkFileType($tmpName, $filename, $allow_file_types)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private static function thumpImage($file, $imageinfo, $percent, $size = [], $ext)
    {
        if (empty($size)) {
            $new_width = $imageinfo['width'] * $percent;
            $new_height = $imageinfo['height'] * $percent;
        } else {
            $new_width = $size['width'];
            $new_height = $size['height'];
        }
        $image_thump = imagecreatetruecolor($new_width, $new_height);
        $source = '';
        switch ($ext) {
            case 'JPG':
            case 'jpg':
                $source = imagecreatefromjpeg($file);
                break;
            case 'JPEG':
            case 'jpeg':
                $source = imagecreatefromjpeg($file);
                break;
            case 'PNG':
            case 'png':
                $source = imagecreatefrompng($file);
                break;
            case 'GIF':
            case 'gif':
                $source = imagecreatefromgif($file);
                break;
            case 'BMP':
            case 'bmp':
                $source = imagecreatefromwbmp($file);
                break;
            default:
                break;
        }

        //将原图复制带图片载体上面，并且按照一定比例压缩,极大的保持了清晰度
        imagecopyresampled($image_thump, $source, 0, 0, 0, 0, $new_width, $new_height, $imageinfo['width'], $imageinfo['height']);
        return $image_thump;
    }

    public static function mictime()
    {
        list($msec, $sec) = explode(' ', microtime());
        $msectime = sprintf('%.0f', (floatval($msec) + floatval($sec)) * 1000000);
        return $msectime;
    }

}