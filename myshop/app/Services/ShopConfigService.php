<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/3/17
 * Time: 21:54
 */

namespace App\Services;

use App\Facades\LangConfig;
use App\Helper\FileHelper;
use App\Http\Models\Shop\ShopConfigModel;

class ShopConfigService
{
    public static function getConf()
    {
        $item_list = (new ShopConfigModel)->getConf();

        $filter_item = array('sms', 'hidden', 'goods');
        $group_list = array();
        $code_arr = array('shop_logo', 'no_picture', 'watermark', 'shop_slagon', 'wap_logo', 'two_code_logo', 'web_qrcode', 'bl_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'business_logo', 'admin_login_logo', 'admin_logo', 'seller_login_logo', 'seller_logo', 'stores_login_logo', 'stores_logo', 'order_print_logo');
        $languages = LangConfig::LangAdminShopConf();
        foreach ($item_list as $key => $item) {
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

    public static function setConf($data, $groups = 'shop')
    {

        $m = (new ShopConfigModel);
        /* 保存变量值 */
        if($groups != 'shop'){
            $item_list = $m->getGroupsConfig($groups);
        }else{
            $item_list = $m->getConf();
        }

        $arr = array();
        foreach ($item_list as $item) {
            $arr[$item['id']] = $item['value'];
        }

        foreach ($data['value'] as $key => $val) {
            if ($val !== null && $val !== $arr[$key]) {
                $where['id'] = $key;
                $update['value'] = trim($val);
                $m->setConf($where, $update);
            }
        }

        /* 处理上传文件 */
        $where['type'] = 'file';
        /* 允许上传的文件类型 */
        $allow_file_types = '|GIF|JPG|PNG|BMP|SWF|DOC|XLS|PPT|MID|WAV|ZIP|RAR|PDF|CHM|RM|TXT|CERT|';
        $item_list = $m->getConf($where);
        $file_list = array();
        foreach ($item_list as $item) {
            $file_list[$item['code']] = $item;
        }

        foreach ($data as $code => $file) {
            if ($code == '_token' || $code == 'value') {
                continue;
            }

            $tmpName = $file->getFileName();
            $ext = $file->getClientOriginalExtension();
            $filename = $file->getClientOriginalName();

            if ($file->isValid()) {
                /* 检查上传的文件类型是否合法 */
                if (!FileHelper::checkFileType($tmpName, $filename, $allow_file_types)) {
                    dd($ext);
                } else {
                    $code_arr = array('bl_qrcode', 'web_qrcode', 'index_down_logo', 'site_commitment', 'user_login_logo', 'login_logo_pic', 'admin_login_logo', 'admin_logo', 'seller_login_logo', 'seller_logo', 'stores_login_logo', 'stores_logo', 'order_print_logo', 'shop_logo', 'business_logo', 'watermark', 'wap_logo', 'two_code_logo', 'no_picture', 'no_brand');
                    if (in_array($code, $code_arr)) {

                        $dir = base_path() . '/' . $file_list[$code]['store_dir'];
                        $file_name = $code . "." . $ext;
                        if (file_exists($file_name)) {
                            @unlink($file_name);
                        }

                        /* 判断是否上传成功 */
                        if ($path = $file->move($dir, $file_name)) {
                            $where['code'] = $code;
                            $update['value'] = 'styles/images/upload/' . $file_name;
                            $m->setConf($where, $update);
                        }
                    }
                }
            }
        }
        return true;
    }
}