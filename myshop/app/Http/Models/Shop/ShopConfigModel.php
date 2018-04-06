<?php

namespace App\Http\Models\Shop;

use Illuminate\Database\Eloquent\Model;

class ShopConfigModel extends Model
{
    protected $table = 'shop_config';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getConf($where = [['parent_id', '<>', '5'], ['id', '<>', '5']], $select = [])
    {
        return $this->where('type', '<>', 'hidden')->where($where)->whereNotIn('shop_group', ['seller', 'complaint_conf', 'report_conf', 'sms', 'goods', 'order_delay'])->orderBy('parent_id', 'asc')->orderBy('sort_order', 'asc')->orderBy('id', 'asc')->get();
    }

    public function setConf($where = [], $data = [])
    {
        $sql = $this;
        $re = 0;
        $re = $sql->where($where)->update($data);
        return $re;
    }

}
