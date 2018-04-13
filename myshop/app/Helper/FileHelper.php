<?php
namespace App\Helper;

class FileHelper{

    //判读文件类型
    public static function checkFileType($filename, $realname = '', $limit_ext_types = '')
    {
        if ($realname)
        {
            $extname = strtolower(substr($realname, strrpos($realname, '.') + 1));
        }
        else
        {
            $extname = strtolower(substr($filename, strrpos($filename, '.') + 1));
        }

        if ($limit_ext_types && stristr($limit_ext_types, '|' . $extname . '|') === false)
        {
            return '';
        }

        $str = $format = '';

        $file = @fopen($filename, 'rb');
        if ($file)
        {
            $str = @fread($file, 0x400); // 读取前 1024 个字节
            @fclose($file);
        }
        else
        {
            if (stristr($filename, base_path()) === false)
            {
                if ($extname == 'jpg' || $extname == 'jpeg' || $extname == 'gif' || $extname == 'png' || $extname == 'doc' ||
                    $extname == 'xls' || $extname == 'txt'  || $extname == 'zip' || $extname == 'rar' || $extname == 'ppt' ||
                    $extname == 'pdf' || $extname == 'rm'   || $extname == 'mid' || $extname == 'wav' || $extname == 'bmp' ||
                    $extname == 'swf' || $extname == 'chm'  || $extname == 'sql' || $extname == 'cert'|| $extname == 'pptx' ||
                    $extname == 'xlsx' || $extname == 'docx')
                {
                    $format = $extname;
                }
            }
            else
            {
                return '';
            }
        }

        if ($format == '' && strlen($str) >= 2 )
        {
            if (substr($str, 0, 4) == 'MThd' && $extname != 'txt')
            {
                $format = 'mid';
            }
            elseif (substr($str, 0, 4) == 'RIFF' && $extname == 'wav')
            {
                $format = 'wav';
            }
            elseif (substr($str ,0, 3) == "\xFF\xD8\xFF")
            {
                $format = 'jpg';
            }
            elseif (substr($str ,0, 4) == 'GIF8' && $extname != 'txt')
            {
                $format = 'gif';
            }
            elseif (substr($str ,0, 8) == "\x89\x50\x4E\x47\x0D\x0A\x1A\x0A")
            {
                $format = 'png';
            }
            elseif (substr($str ,0, 2) == 'BM' && $extname != 'txt')
            {
                $format = 'bmp';
            }
            elseif ((substr($str ,0, 3) == 'CWS' || substr($str ,0, 3) == 'FWS') && $extname != 'txt')
            {
                $format = 'swf';
            }
            elseif (substr($str ,0, 4) == "\xD0\xCF\x11\xE0")
            {   // D0CF11E == DOCFILE == Microsoft Office Document
                if (substr($str,0x200,4) == "\xEC\xA5\xC1\x00" || $extname == 'doc')
                {
                    $format = 'doc';
                }
                elseif (substr($str,0x200,2) == "\x09\x08" || $extname == 'xls')
                {
                    $format = 'xls';
                } elseif (substr($str,0x200,4) == "\xFD\xFF\xFF\xFF" || $extname == 'ppt')
                {
                    $format = 'ppt';
                }
            } elseif (substr($str ,0, 4) == "PK\x03\x04")
            {
                if (substr($str,0x200,4) == "\xEC\xA5\xC1\x00" || $extname == 'docx')
                {
                    $format = 'docx';
                }
                elseif (substr($str,0x200,2) == "\x09\x08" || $extname == 'xlsx')
                {
                    $format = 'xlsx';
                } elseif (substr($str,0x200,4) == "\xFD\xFF\xFF\xFF" || $extname == 'pptx')
                {
                    $format = 'pptx';
                }else
                {
                    $format = 'zip';
                }
            } elseif (substr($str ,0, 4) == 'Rar!' && $extname != 'txt')
            {
                $format = 'rar';
            } elseif (substr($str ,0, 4) == "\x25PDF")
            {
                $format = 'pdf';
            } elseif (substr($str ,0, 3) == "\x30\x82\x0A")
            {
                $format = 'cert';
            } elseif (substr($str ,0, 4) == 'ITSF' && $extname != 'txt')
            {
                $format = 'chm';
            } elseif (substr($str ,0, 4) == "\x2ERMF")
            {
                $format = 'rm';
            } elseif ($extname == 'sql')
            {
                $format = 'sql';
            } elseif ($extname == 'txt')
            {
                $format = 'txt';
            }
        }

        if ($limit_ext_types && stristr($limit_ext_types, '|' . $format . '|') === false)
        {
            $format = '';
        }

        return $format;
    }
}