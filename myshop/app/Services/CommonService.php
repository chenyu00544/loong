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
    public static function test()
    {
        echo 111;
    }

    public static function md5Encrypt($pass, $salt)
    {
        return md5(md5($pass) . $salt);
    }

    public static function paginate($data, $count = 1, $currentPage = 1, $size = 20)
    {
        $req['data'] = $data;
        $req['size'] = $size;
        $req['count'] = $count;
        $req['currentPage'] = $currentPage;

        //上一页和下一页
        $totalPages = ceil($count / $size);
        $prev = ($currentPage - 1) > 0 ? $currentPage - 1 : 1;
        $next = ($currentPage + 1) < $totalPages ? $currentPage + 1 : $totalPages;
        $req['prev'] = $prev;
        $req['next'] = $next;

        //中间数字分页
        $page = [];
        if ($totalPages < 9) {
            for ($i = 1; $i <= $totalPages; $i++) {
                $page[$i] = $i;
            }
        } elseif ($totalPages > 9) {
            $frontPage = [1, 2];
            if ($currentPage > 1 || $currentPage < $totalPages) {
                $middlePage = [$currentPage - 1, $currentPage, $currentPage + 1];
            } elseif ($currentPage = 1) {
                $middlePage = [$currentPage, $currentPage + 1];
            } elseif ($currentPage = $totalPages) {
                $middlePage = [$currentPage - 1, $currentPage];
            }
            $behindPage = [$totalPages - 1, $totalPages];
            $page = array_merge($frontPage, $middlePage, $behindPage);
            $page = array_unique($page);
        }
        $req['page'] = $page;
        return $req;
    }
}