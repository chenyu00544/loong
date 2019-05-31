<?php

use App\Services\Common\ConfigService;

/**
 * 字符串转换为数组，主要用于把分隔符调整到第二个参数
 * @param  string $str 要分割的字符串
 * @param  string $glue 分割符
 * @return array
 */
function str2arr($str, $glue = ',')
{
    return explode($glue, $str);
}

/**
 * 数组转换为字符串，主要用于把分隔符调整到第二个参数
 * @param  array $arr 要连接的数组
 * @param  string $glue 分割符
 * @return string
 */
function arr2str($arr, $glue = ',')
{
    return implode($glue, $arr);
}

/**
 * 数据签名认证
 * @param  array $data 被认证的数据
 * @return string       签名
 */
function data_sign($data)
{
    // 数据类型检测
    if (!is_array($data)) {
        $data = (array)$data;
    }
    ksort($data); //排序
    $code = http_build_query($data); //url编码并生成query字符串
    return sha1($code); //生成签名
}

/**
 * 格式化字节大小
 * @param  number $size 字节数
 * @param  string $delimiter 数字和单位分隔符
 * @return string            格式化后的带单位的大小
 */
function format_bytes($size, $delimiter = '')
{
    $units = ['B', 'KB', 'MB', 'GB', 'TB', 'PB'];
    for ($i = 0; $size >= 1024 && $i < 5; $i++) $size /= 1024;
    return round($size, 2) . $delimiter . $units[$i];
}

/**
 * 用户资源目录
 * @param $path
 * @return mixed
 */
function storage_public($path = '')
{
    // $path = str_replace('/storage/', '', $path);
    return \Storage::path($path);
}

/**
 * 用户资源url
 * @param $value
 * @return mixed
 */
function storage_url($value = '')
{
    $value = $value ? \Storage::url($value) : '';
    return $value;
}

/**
 * 插件目录
 * @param string $path
 * @return string
 */
function plugin_path($path = '')
{
    return app_path('Plugins/' . $path);
}

/**
 * 返回数据库配置信息
 * @param null $item
 * @return \Illuminate\Config\Repository|mixed
 */
function db_config($item = null)
{
    $type = config('database.default', 'mysql');

    $config = config('database.connections.' . $type);

    return is_null($item) ? $config : $config[$item];
}

/**
 * 是否为移动设备
 * @return mixed
 */
function is_mobile_device()
{
    $detect = new \Mobile_Detect();
    return $detect->isMobile();
}

/**
 * 检查是否是微信浏览器访问
 * @return bool
 */
function is_wechat_browser()
{
    $user_agent = strtolower(request()->userAgent());

    if (strpos($user_agent, 'micromessenger') === false) {
        return false;
    } else {
        return true;
    }
}

/**
 * 判断是否SSL协议  https://
 * @return boolean
 */
function is_ssl()
{
    return request()->isSecure();
}

/**
 * 逆地址解析(坐标位置描述)
 * @param $lat 纬度
 * @param $lng 经度
 * @param $key Key
 * @example geocoder('31.22928', '121.40966', 'XSYBZ-P2G34-3K7UB-XPFZS-TBGHT-CXB4U')
 * @dependency http://lbs.qq.com/tool/component-geolocation.html
 * @return null
 */
function geocoder($lat, $lng, $key)
{
    $url = 'https://apis.map.qq.com/ws/geocoder/v1/?';
    $url .= 'location=' . $lat . ',' . $lng;
    $url .= '&key=' . $key;

    $content = file_get_contents($url);

    $result = json_decode($content, true);

    if ($result['status'] != 0) {
        return null;
    }

    return $result['result']['address_component'];
}

/**
 * 加载函数库
 * @param array $files
 * @param string $module
 */
function load_helper($files = [], $module = '')
{
    if (!is_array($files)) {
        $files = [$files];
    }
    if (empty($module)) {
        $base_path = app_path('Helpers/');
    } else {
        $base_path = app_path('Modules/' . ucfirst($module) . '/Helpers/');
    }
    foreach ($files as $vo) {
        $helper = $base_path . $vo . '.php';
        if (file_exists($helper)) {
            require_once $helper;
        }
    }
}

/**
 * 加载语言包
 * @param array $files
 * @param string $module
 * @throws Exception
 */
function load_lang($files = [], $module = '')
{
    static $_LANG = [];
    static $lang = [];
    if (!is_array($files)) {
        $files = [$files];
    }

    $config = app(ConfigService::class);

    if (empty($module)) {
        $base_path = resource_path('lang/' . $config->getConfig('lang') . '/');
    } else {
        $base_path = app_path('Modules/' . ucfirst($module) . '/Languages/' . $config->getConfig('lang') . '/');
    }
    foreach ($files as $vo) {
        $hash = md5($vo);
        $lang[$hash] = null;
        $helper = $base_path . $vo . '.php';
        if (file_exists($helper)) {
            $lang[$hash] = require_once($helper);
            if (is_null($lang[$hash])) {
                $_LANG = array_merge($_LANG, $lang[$hash]);
            }
        }
    }
    $GLOBALS['_LANG'] = $_LANG;
}

/**
 * 输出语言包
 * @param null $key
 * @param array $replace
 * @param null $locale
 * @return array|\Illuminate\Contracts\Translation\Translator|null|string
 * @throws Exception
 */
function lang($key = null, $replace = [], $locale = null)
{
    $config = cache('shop_config');
    $lang = is_null($config) ? 'zh_cn' : $config['lang'];
    $locale = empty($locale) ? $lang : $locale;

    return trans($key, $replace, $locale);
}

/**
 * 获取和设置语言定义
 * @param string|array $name 语言变量
 * @param mixed $value 语言值或者变量
 * @return mixed
 */
function L($name = null, $value = null)
{
    static $_lang = [];
    // 空参数返回所有定义
    if (empty($name))
        return $_lang;
    // 判断语言获取(或设置)
    // 若不存在,直接返回全大写$name
    if (is_string($name)) {
        $name = strtolower($name);
        if (is_null($value)) {
            return isset($_lang[$name]) ? $_lang[$name] : $name;
        } elseif (is_array($value)) {
            // 支持变量
            $replace = array_keys($value);
            foreach ($replace as &$v) {
                $v = '{$' . $v . '}';
            }
            return str_replace($replace, $value, isset($_lang[$name]) ? $_lang[$name] : $name);
        }
        $_lang[$name] = $value; // 语言定义
        return null;
    }
    // 批量定义
    if (is_array($name)) {
        $_lang = array_merge($_lang, array_change_key_case($name, CASE_LOWER));
    }

    return null;
}

/**
 * 字符串命名风格转换
 * type 0 将Java风格转换为C的风格 1 将C风格转换为Java的风格
 * @param string $name 字符串
 * @param integer $type 转换类型
 * @param bool $ucfirst 首字母是否大写（驼峰规则）
 * @return string
 */
function parse_name($name, $type = 0, $ucfirst = true)
{
    if ($type) {
        $name = preg_replace_callback('/_([a-zA-Z])/', function ($match) {
            return strtoupper($match[1]);
        }, $name);
        return $ucfirst ? ucfirst($name) : lcfirst($name);
    } else {
        return strtolower(trim(preg_replace("/[A-Z]/", "_\\0", $name), "_"));
    }
}

/**
 * 写入日志文件
 *
 * @param string $message
 * @param array $context
 * @param string $level
 * @param string $channel
 */
function logResult($message = '', $context = [], $level = 'info', $channel = '')
{
    if ($channel) {
        \Illuminate\Support\Facades\Log::channel('.$channel.')->$level($message, $context);
    } else {
        \Illuminate\Support\Facades\Log::$level($message, $context);
    }
}

/**
 * html代码输入
 *
 * @param $str
 *
 * @return string
 */
function html_in($str)
{
    $str = trim($str);
    $str = htmlspecialchars($str);
    if (!get_magic_quotes_gpc()) {
        $str = addslashes($str);
    }
    return $str;
}

/**
 * html代码输出
 *
 * @param $str
 *
 * @return string
 */
function html_out($str)
{
    if (function_exists('htmlspecialchars_decode')) {
        $str = htmlspecialchars_decode($str);
    } else {
        $str = html_entity_decode($str);
    }
    $str = stripslashes($str);
    return $str;
}
