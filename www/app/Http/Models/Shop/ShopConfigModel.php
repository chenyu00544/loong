<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ShopConfigModel extends Model
{
    protected $table = 'shop_config';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getConf($where = [['parent_id', '<>', '5'], ['id', '<>', '5']], $groups = ['seller', 'complaint_conf', 'report_conf', 'sms', 'goods', 'order_delay'])
    {
        return $this->where('type', '<>', 'hidden')
            ->where($where)
            ->whereNotIn('shop_group', $groups)
            ->orderBy('parent_id', 'asc')
            ->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->get();
    }

    public function setConf($where = [], $data = [])
    {
        $re = $this->where($where)->update($data);
        return $re;
    }

    public function getGroupsConfig($where)
    {
        return $this->where($where)
            ->where('type', '<>', 'hidden')
            ->orderBy('parent_id', 'asc')
            ->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->get();
    }

    public function getHiddenConfig()
    {
        return $this->where('type', '=', 'hidden')
            ->orderBy('parent_id', 'asc')
            ->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->get();
    }

    public function getAll()
    {
        return $this->select(['id','code','value'])
            ->orderBy('sort_order', 'asc')
            ->orderBy('id', 'asc')
            ->get();
    }

}
