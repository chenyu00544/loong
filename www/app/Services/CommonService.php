<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;


class CommonService
{
    //md5加密
    public static function md5Encrypt($pass, $salt)
    {
        return md5(md5($pass) . $salt);
    }

    public static function randStr($mun)
    {
        $key = '';
        $pattern = '1234567890abcdefghijklmnopqrstuvwxyz';
        for ($i = 0; $i < $mun; $i++) {
            $key .= $pattern{mt_rand(0,35)};    //生成php随机数
        }
        return $key;
    }

    //分页列表
    public static function paginate($data, $count = 1, $currentPage = 1, $size = 20, $isList = true)
    {
        $req['data'] = $data;
        $req['size'] = $size;
        $req['count'] = $count;
        $req['currentPage'] = $currentPage;

        //上一页和下一页
        $totalPages = intval(ceil($count / $size));
        $prev = ($currentPage - 1) > 0 ? $currentPage - 1 : 1;
        $next = ($currentPage + 1) < $totalPages ? $currentPage + 1 : $totalPages;
        $req['prev'] = $prev;
        $req['next'] = $next;
        if ($count > 0) {
            //中间数字分页
            $page = [];
            if ($currentPage <= 7) {
                $loop_count = $totalPages < 7 ? $totalPages : 7;
                for ($i = 1; $i <= $loop_count; $i++) {
                    $page[$i] = $i;
                }
                if (($totalPages - 1) > 8) {
                    if ($isList) {
                        $page[] = '...';
                    }
                    $page[] = $totalPages - 1;
                    $page[] = $totalPages;
                }
            } elseif ($currentPage > ($totalPages - 7)) {
                $page = [1, 2];
                if (($totalPages - 6) > 3) {
                    if ($isList) {
                        $page[] = '...';
                    }
                }
                for ($i = 1; $i <= 7; $i++) {
                    $page[] = $totalPages - 7 + $i;
                }
            } else {
                $page = [1, 2];
                if ($isList) {
                    $page[] = '...';
                }
                $page[] = $currentPage - 1;
                $page[] = $currentPage;
                $page[] = $currentPage + 1;
                if ($isList) {
                    $page[] = '...';
                }
                $page[] = $totalPages - 1;
                $page[] = $totalPages;
            }
        } else {
            $page[] = 1;
        }

        $req['page'] = $page;
        return $req;
    }

    //笛卡尔积
    public static function cartesian($data, $layer = 0, $str = '', $pList = [])
    {
        $tlayer = count($data);
        if ($tlayer > $layer) {
            for ($i = 0; $i < count($data[$layer]); $i++) {
                $pList = self::cartesian($data, $layer + 1, $str . '|' . $data[$layer][$i], $pList);
            }
        } else {
            $pList[] = substr($str, 1);
            return $pList;
        }
        return $pList;
    }

    public function priceFormat($price)
    {
        return '￥'.$price;
    }
}