<?php

namespace App\Repositories\Shop;

use App\Models\TouchAd;
use App\Models\SellerShopinfo;
use App\Contracts\Repositories\Shop\ShopRepositoryInterface;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class ShopRepository implements ShopRepositoryInterface
{

    /**
     * @param id
     * @return array
     */
    public function get($id)
    {
        return $this->findBY('id', $id);
    }

    /**
     * 根据其他值找店铺信息
     * @param $key
     * @param $val
     * @return array
     */
    public function findBY($key, $val)
    {
        $list = SellerShopinfo::select('ru_id', 'shop_name', 'shop_logo', 'shopname_audit')
            ->with(['MerchantsShopInformation' => function ($query) {
                $query->select('shoprz_brandName', 'user_id', 'shopNameSuffix', 'rz_shopName');
            }])
            ->where($key, $val)
            ->get()
            ->toArray();

        if (empty($list)) {
            $list = [];
            return $list;
        }

        //
        foreach ($list as $k => $v) {
            $list[$k]['brandName'] = $v['merchants_shop_information']['shoprz_brandName'];
            $list[$k]['shopNameSuffix'] = $v['merchants_shop_information']['shopNameSuffix'];
            $list[$k]['rz_shopName'] = $v['merchants_shop_information']['rz_shopName'];

            unset($list[$k]['merchants_shop_information']);
        }

        return $list;
    }

    /**
     * 获取轮播图
     * @param $tc_type
     * @param $num
     * @return array
     */
    public function getPositions($tc_type = 'weapp', $num = 3)
    {
        $time = local_gettime();

        $ads = TouchAd::select('ad_id', 'touch_ad.position_id', 'media_type', 'ad_link', 'ad_code', 'ad_name')
            ->with(['position'])
            ->join('touch_ad_position', 'touch_ad_position.position_id', '=', 'touch_ad.position_id')
            ->where("start_time", '<=', $time)
            ->where("end_time", '>=', $time)
            ->where("enabled", 1);

        if (gettype($tc_type) == 'string') {
            $ads->where("touch_ad_position.tc_type", $tc_type);
        } else {
            $ads->where("touch_ad_position.position_id", $tc_type);
        }

        $res = $ads->orderby('ad_id', 'desc')
            ->limit($num)
            ->get()
            ->toArray();

        $res = array_map(function ($v) {
            if (!empty($v['position'])) {
                $temp = array_merge($v, $v['position']);
                unset($temp['position']);
                return $temp;
            }
        }, $res);

        return $res;
    }

    //end

    /**
     * 改写  \App\Libraries\Shop 方法
     * 查询MYSQL拼接字符串数据
     * $select_array 查询的字段
     * $select_id 查询的ID值
     * $where 查询的条件 比如（AND goods_id = '$goods_id'）
     * $table 表名称
     * $id 被查询的字段
     * $is_db 查询返回数组方式
     */
    public function get_select_find_in_set($is_db = 0, $select_id, $select_array = array(), $where = '', $table = '', $id = '', $replace = '')
    {
        if ($replace) {
            $replace = "REPLACE ($id,'$replace',',')";
        } else {
            $replace = "$id";
        }

        if ($select_array && is_array($select_array)) {
            $select = implode(',', $select_array);
        } else {
            $select = '*';
        }
        $prefix = Config::get('database.connections.mysql.prefix');

        $sql = "SELECT {$select} FROM {$prefix}{$table} WHERE find_in_set('$select_id', $replace) $where";

        //
        if ($is_db == 1) {
            //多条数组数据
            return DB::select($sql);
        } elseif ($is_db == 2) {
            //一条数组数据
            $res = DB::select($sql);
            return isset($res[0]) ? json_decode(json_encode($res[0]), 1) : array();
        } else {
            //返回某个字段的值
            $sql = trim($sql . ' LIMIT 1');

            $res = DB::select($sql);
            if ($res !== false) {
                $row = isset($res[0]) ? json_decode(json_encode($res[0]), 1) : array();
                if ($row !== false) {
                    return reset($row);
                } else {
                    return '';
                }
            } else {
                return array();
            }
        }

    }


}
