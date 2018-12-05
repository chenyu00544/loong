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
    private static $so;

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
            $key .= $pattern{mt_rand(0, 35)};    //生成php随机数
        }
        return $key;
    }

    public static function randNum($mun)
    {
        $min = 1;
        $max = 9;
        for ($i = 1; $i < $mun; $i++) {
            $min *= 10;
            $max *= 10;
            $max += 9;
        }
        return rand($min, $max);
    }

    //匹配字符串
    public function pregMatchAll($str, $pattern = '/\${[a-z|_\-|A-Z]*}/')
    {
        preg_match_all($pattern, $str, $dir);
        $req = [];
        foreach ($dir as $val) {
            foreach ($val as $k => $v) {
                $req[$k] = substr($v, 2, -1);
            }
        }
        return $req;
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

    //格式化价格
    public function priceFormat($price)
    {
        return '￥' . $price;
    }

    //关键字分词
    public static function scws($key)
    {
        $regex = "/\/|\～|\，|\。|\！|\？|\“|\”|\【|\】|\『|\』|\：|\；|\《|\》|\’|\‘|\ |\·|\~|\!|\@|\#|\\$|\%|\^|\&|\*|\(|\)|\_|\+|\{|\}|\:|\<|\>|\?|\[|\]|\,|\.|\/|\;|\'|\`|\-|\=|\\\|\|/";
        ini_get('scws.default.fpath');
        if (!isset(self::$so)) {
            self::$so = scws_new();
            self::$so->set_charset('utf8');
            self::$so->set_dict('/usr/local/scws/etc/dict.utf8.xdb');
            self::$so->set_rule('/usr/local/scws/etc/rules.utf8.ini');
        }
        self::$so->send_text($key);
        $keywords = [];
        while ($tmp = self::$so->get_result()) {
            foreach ($tmp as $t) {
                if (!empty($t['word'])) {
                    if (!preg_match($regex, $t['word']) && mb_strlen($t['word']) > 1) {
                        $keywords[] = $t['word'];
                    }
                }
            }
        }
        self::$so->close();
        return $keywords;
    }

    //退换货理由
    public function causeName()
    {
        return ['维修', '退货', '换货', '仅退款'];
    }
}