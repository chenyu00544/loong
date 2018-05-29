<?php

namespace App\Repositories\Shop;

use App\Models\TouchAd;
use App\Models\Goods;
use App\Models\SellerShopinfo;
use App\Models\TouchNavSmallapp;
use App\Models\TouchAdSmallapp;
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
        $list = SellerShopinfo::select('ru_id', 'shop_name', 'shop_logo', 'shopname_audit', 'check_sellername')
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

    ///////////////////////////////////////////////////////////////

    /**
     * 获取小程序导航  会自动获取这个导航下的所有广告和轮播
     * @return array
     */

    public function getNav()
    {
        $list = TouchNavSmallapp::select('id', 'nav_type', 'goods_cate_id', 'name')
            ->where('ifshow', 1)
            ->orderby('vieworder', 'asc')
            ->limit(10)
            ->get()
            ->toArray();

        if (empty($list)) {
            $list = [];
            return $list;
        }

        return $list;
    }
    //g.is_delete = 0 AND g.is_on_sale = 1 AND g.is_alone_sale = 1
    //shop_price_formated
    public function getCateGoods()
    {
        $arr = [
            'goods_id',   //商品id
            'goods_name',   //商品名
            'shop_price',   //商品价格
            'goods_thumb',   //商品图片
            'goods_link',    //商品链接
            'goods_number',   //商品销量
            'market_price',   //商品原价格
            'sales_volume',   //商品库存
            'goods_brief',   //商品库存
        ];


        $list = Goods::select("goods_id",    //商品id
            "goods_name", //商品名称
            "shop_price",  //商品价格
            "goods_thumb", //商品图片
            "goods_number",   //商品销量
            "market_price",   //商品原价格
            "sales_volume",  //商品库存
            "goods_brief")
            ->where('is_on_sale', 1)
            ->where('is_alone_sale', 1)
            ->where('is_delete', 0)
            ->orderby('sort_order', 'asc')
            ->limit(10)
            ->get()
            ->toArray();

        if (empty($list)) {
            $list = [];
            return $list;
        }


        $data = array_map(function ($v) use ($arr) {
            foreach ($v as $ck => $cv) {
                if (!in_array($ck, $arr)) {
                    unset($v[$ck]);
                }
            }

            $v['goods_brief'] = $v['goods_brief'];
            $v['goods_thumb'] = get_image_path($v['goods_thumb']);
            $v['goods_sales'] = $v['sales_volume'];
            $v['goods_stock'] = $v['goods_number'];
            $v['market_price_formated'] = price_format($v['market_price'], false);
            $v['shop_price_formated'] = price_format($v['shop_price'], false);
            unset($v['goods_number'], $v['sales_volume']);
            return $v;
        }, $list);

        return $list;
    }


    /**
     * 获取小程序导航  会自动获取这个导航下的所有广告和轮播
     * @return array
     */

    public function getBanner($nav_id)
    {
        $list = TouchAdSmallapp::select
        ('ad_name',
            'ext_url',
            'nav_id',
            'ad_image',

            'is_show',
            'ad_title1',
            'ad_title2',
            'ad_title3',
            'button_title',
            'ad_sort',

            'ad_type',
            'add_time')
            ->where('is_show', 1)
            ->where('nav_id', $nav_id)
            ->orderby('ad_sort', 'asc')
            ->limit(10)
            ->get()
            ->toArray();

        if (empty($list)) {
            $list = [];
            return $list;
        }

        return $list;
    }


}
