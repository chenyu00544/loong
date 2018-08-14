<?php

namespace App\Services;

use App\Helper\FileHelper;
use Intervention\Image\Facades\Image;

class FileHandleService
{

    //上传原图
    public static function upLoadFlie($file, $uri)
    {
        $bool = $file->getClientOriginalExtension() == 'mp4' || $file->getClientOriginalExtension() == 'MP4';
        if ($bool) {
            $filename = self::mictime() . rand(10000, 99999) . '.' . $file->getClientOriginalExtension();
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;

            if ($path = $file->move($dir, $filename)) {
                return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            }
        }
        return 0;
    }

    //上传原图
    public static function upLoadImage($file, $uri)
    {
        $bool = self::checkFile($file);
        if ($bool) {

            $filename =  self::mictime() . rand(10000, 99999) . '.' . $file->getClientOriginalExtension();
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;

            if ($path = $file->move($dir, $filename)) {
                return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
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
            if ($path = imagejpeg($img, $dir . $filename)) {
                return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
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
            if ($path = imagejpeg($img, $dir . $filename)) {
                return 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR . $filename;
            }
        }
        return 0;
    }

    public static function deleteFile($file_path)
    {
        $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . $file_path;
        if (file_exists($dir)) {
            @unlink($dir);
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
        $msectime =  sprintf('%.0f', (floatval($msec) + floatval($sec)) * 1000000);
        return $msectime;
    }

}