<?php

namespace App\Services;

use App\Helper\FileHelper;

class FileHandleService
{

    public static function upLoadImage($file, $uri)
    {
        $bool = self::checkFile($file);
        if ($bool) {
            $filename = md5(time() . rand(10000, 99999)) . '.' . $file->getClientOriginalExtension();
            $dir = base_path() . DIRECTORY_SEPARATOR . 'public' . DIRECTORY_SEPARATOR . 'upload' . DIRECTORY_SEPARATOR . $uri . DIRECTORY_SEPARATOR;

            if ($path = $file->move($dir, $filename)) {
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

}