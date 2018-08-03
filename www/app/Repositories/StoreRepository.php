<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/6
 * Time: 21:02
 */

namespace App\Repositories;

use App\Contracts\StoreRepositoryInterface;
use App\Facades\LangConfig;
use App\Http\Models\Shop\ShopConfigModel;

class StoreRepository implements StoreRepositoryInterface
{

    private $shopConfigModel;

    public function __construct(
        ShopConfigModel $shopConfigModel
    )
    {
        $this->shopConfigModel = $shopConfigModel;
    }

    public function getGroupsConfig($groups)
    {
        $item_list = $this->shopConfigModel->getGroupsConfig(['shop_group' => $groups]);

        /* 整理数据 */
        $code_arr = array('shop_logo', 'no_picture', 'watermark', 'shop_slagon', 'wap_logo', 'two_code_logo', 'ectouch_qrcode', 'ecjia_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'business_logo', 'no_brand');

        $lang = LangConfig::LangAdminShopConf();
        foreach ($item_list as $key => $item) {

            $pid = $item->parent_id;

            $item->name = isset($lang['cfg_name'][$item->code]) ? $lang['cfg_name'][$item->code] : $item->code;
            $item->desc = isset($lang['cfg_desc'][$item->code]) ? $lang['cfg_desc'][$item->code] : '';

            if ($item->store_range) {
                $item['store_options'] = explode(',', $item->store_range);
                $options = array();
                foreach ($item->store_options as $k => $v) {
                    $options[$k] = isset($lang['cfg_range'][$item->code][$v]) ? $lang['cfg_range'][$item->code][$v] : $v;
                }
                $item->display_options = $options;
            }

            if ($item) {
                if ($item->type == 'file' && in_array($item->code, $code_arr) && $item->value) {
                    $item->del_img = 1;

                    if (strpos($item->value, '../') === false) {
                        $item->value = "../" . $item->value;
                    }
                } else {
                    $item->del_img = 0;
                }
            }

            //设置中间变量，否则框架报错
        }

        return $item_list;
    }

    public function setStore($data)
    {
        dd($data);
    }

}