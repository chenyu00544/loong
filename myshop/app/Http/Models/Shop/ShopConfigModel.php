<?php

namespace App\Http\Models\Shop;

use App\Facades\LangConfig;
use Illuminate\Database\Eloquent\Model;

class ShopConfigModel extends Model
{
    protected $table = 'shop_config';
    protected $primaryKey = 'id';
    public $timestamps = false;
    protected $guarded = [];

    public function getConf()
    {
        $item_list = $this->where('type', '<>', 'hidden')->where([['parent_id', '<>', '5'], ['id', '<>', '5']])->whereNotIn('shop_group', ['seller', 'complaint_conf', 'report_conf', 'sms', 'goods', 'order_delay'])->orderBy('parent_id', 'asc')->orderBy('sort_order', 'asc')->orderBy('id', 'asc')->get();
        /* 整理数据 */
        $filter_item = array('sms', 'hidden', 'goods');
        $group_list = array();
        $code_arr = array('shop_logo', 'no_picture', 'watermark', 'shop_slagon', 'wap_logo', 'two_code_logo', 'ectouch_qrcode', 'ecjia_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'business_logo', 'admin_login_logo', 'admin_logo', 'seller_login_logo', 'seller_logo', 'stores_login_logo', 'stores_logo', 'order_print_logo');
        $languages = LangConfig::LangAdminShopConf();
        foreach ($item_list AS $key => $item) {
            if (!in_array($item->code, $filter_item)) {
                $pid = $item->parent_id;
                $item['name'] = isset($languages['cfg_name'][$item->code]) ? $languages['cfg_name'][$item->code] : $item->code;
                $item['desc'] = isset($languages['cfg_desc'][$item->code]) ? $languages['cfg_desc'][$item->code] : '';
                if ($item->code == 'sms_shop_mobile') {
                    $item->url = 1;
                }
                if ($pid == 0) {
                    /* 分组 */
                    if ($item->type == 'group') {
                        $group_list[$item->id] = $item;
                    }
                } else {
                    /* 变量 */
                    if (isset($group_list[$pid])) {
                        if ($item->store_range) {
                            $item['store_options'] = explode(',', $item->store_range);
                            $options = array();
                            foreach ($item->store_options as $k => $v) {
                                $options[$k] = isset($languages['cfg_range'][$item->code][$v]) ? $languages['cfg_range'][$item->code][$v] : $v;
                            }
                            $item['display_options'] = $options;
                        }

                        if ($item) {
                            if ($item['type'] == 'file' && in_array($item['code'], $code_arr) && $item['value']) {
                                $item['del_img'] = 1;

                                if (strpos($item['value'], '../') === false) {
                                    $item['value'] = "../" . $item['value'];
                                }
                            } else {
                                $item['del_img'] = 0;
                            }
                        }
                        //设置中间变量，否则框架报错
                        $vars[$pid][] = $item;
                        $group_list[$pid]['vars'] = $vars[$pid];
                    }
                }
            }
        }
        return $group_list;
    }

}
