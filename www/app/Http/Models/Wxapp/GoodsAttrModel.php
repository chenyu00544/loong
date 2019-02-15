<?php
/**
 * Created by PhpStorm.
 * User: Administrator - chenyu
 * Date: 2018/6/22
 * Time: 16:58
 * Desc:
 */

namespace App\Http\Models\Wxapp;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\Config;
use Illuminate\Support\Facades\DB;

class GoodsAttrModel extends Model
{
    protected $table = 'goods_attr';
    protected $primaryKey = 'goods_attr_id';
    public $timestamps = false;
    protected $guarded = [];

    public function attr()
    {
        return $this->hasOne('App\Http\Models\Wxapp\AttributeModel', 'attr_id', 'attr_id');
    }

    public function getGoodsIdsByFilter($where, $wherein, $whereOr, $filter, $keywords, $column = ['*'])
    {
        $prefix = Config::get('database.connections.mysql.prefix');

        $like = '';
        foreach ($keywords as $keyword) {
            $like .= ' pinyin_keyword like "%' . $keyword . '%" OR';
        }
        if ($like != '') {
            $like = ' ('.substr($like, 0, -2) . ')';
        }

        $fw = '';
        foreach ($where as $w) {
            $fw .= $w . ' AND ';
        }

        $fwi = '';
        foreach ($wherein as $k => $wi) {
            $fwi .= $k . ' in (' . $wi . ') AND ';
        }

        if (count($filter) > 1) {
            $sql = "SELECT a.goods_id FROM ( ";
            $n = 0;
            foreach ($filter as $k => $ft) {
                $attr_id = $ft['attrid'];
                $attr_value = '';
                foreach ($ft['attr_value'] as $attr_v) {
                    $attr_value .= '"' . $attr_v . '",';
                }
                $attr_value = substr($attr_value, 0, -1);
                if ($k < count($filter) - 1) {
                    $sql .= "SELECT * FROM {$prefix}goods_attr WHERE attr_id = $attr_id AND attr_value IN ($attr_value) UNION ALL ";
                } else {
                    $sql .= "SELECT * FROM {$prefix}goods_attr WHERE attr_id = $attr_id AND attr_value IN ($attr_value)";
                }
                $n = $k;
            }
            $sql .= ")a JOIN {$prefix}goods AS g ON a.goods_id=g.goods_id WHERE $fw $fwi $whereOr $like GROUP BY a.goods_id HAVING COUNT(a.goods_id)>$n";
        } else if(count($filter) == 1){
            $sql = "";
            foreach ($filter as $k => $ft) {
                $attr_id = $ft['attrid'];
                $attr_value = '';
                foreach ($ft['attr_value'] as $attr_v) {
                    $attr_value .= '"' . $attr_v . '",';
                }
                $attr_value = substr($attr_value, 0, -1);
                $sql = "SELECT a.goods_id FROM {$prefix}goods_attr AS a JOIN {$prefix}goods AS g ON a.goods_id=g.goods_id WHERE $fw $fwi $whereOr attr_id = $attr_id AND attr_value IN ($attr_value) AND $like";
            }
        }else{
            $sql = "SELECT goods_id FROM {$prefix}goods WHERE $fw $fwi $whereOr $like";
        }
        $re = DB::select(DB::Raw($sql));
        return $re;
    }
}
