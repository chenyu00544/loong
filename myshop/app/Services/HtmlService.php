<?php
/**
 * Created by PhpStorm.
 * User: chenyu
 * Date: 2018/4/2
 * Time: 20:55
 */

namespace App\Services;

use App\Facades\Regions;

class HtmlService
{
    public static function shopConfHtml($conf = [], $lang = [])
    {
        $countryId = '';
        $provinceId = '';
        foreach ($conf as $key => $item) {
            foreach ($item->vars as $k => $var) {
                switch ($var['type']) {
                    case 'text':
                        $var['html'] = '<input type="text" name="value[' . $var['id'] . ']" class="form-control ' . $var['code'] . '" value="' . $var['value'] . '" placeholder="' . $var['name'] . '"/>';
                        $vars[$key][] = $var;
                        $conf[$key]['vars'] = $vars[$key];
                        break;
                    case 'textarea':
                        $var['html'] = '<textarea name="value[' . $var['id'] . ']" class="form-control ' . $var['code'] . '" row="5" placeholder= "' . $var['name'] . '" style="min-height:100px;">' . $var['value'] . '</textarea>';
                        $vars[$key][] = $var;
                        $conf[$key]['vars'] = $vars[$key];
                        break;
                    case 'manual':
                        if ($var['code'] == 'shop_country') {
                            $countryId = $var['value'];
                            $area = Regions::getRegion(0,0);

                            $var['html'] = '<select name="value[' . $var['id'] . ']" class="form-control ' . $var['code'] . '" value="' . $var['value'] . '"><option>国家</option>';

                            $vars[$key][] = $var;
                            $conf[$key]['vars'] = $vars[$key];

                        } elseif ($var['code'] == 'shop_province') {
                            $provinceId = $var['value'];
                            $area = Regions::getRegion(1, $countryId);
                            $var['html'] = '<select name="value[' . $var['id'] . ']" class="form-control ' . $var['code'] . '" value="' . $var['value'] . '"><option>省/直辖市</option>';

                            $vars[$key][] = $var;
                            $conf[$key]['vars'] = $vars[$key];

                        } elseif ($var['code'] == 'shop_city') {
                            $area = Regions::getRegion(2, $provinceId);

                            $var['html'] = '<select name="value[' . $var['id'] . ']" class="form-control ' . $var['code'] . '" value="' . $var['value'] . '"><option>市</option>';

                            $vars[$key][] = $var;
                            $conf[$key]['vars'] = $vars[$key];
                        }
                        foreach ($area as $item){
                            if ($item['id'] == $var['value']){
                                $var['html'] .= '<option value="' . $item['id'] . '" selected >' . $item['name'] . '</option>';
                            }else{
                                $var['html'] .= '<option value="' . $item['id'] . '" >' . $item['name'] . '</option>';
                            }
                        }
                        $var['html'] .= '</select>';
                        break;
                    case 'select':
                        foreach ($var['store_options'] as $n => $opt) {
                            $var['html'] .= '<label class="radio-inline fl">
  <input type="radio" name="value[' . $var['id'] . ']" id="value_' . $var['id'] . '_' . $n . '" value="' . $opt . '"';
                            if ($var['value'] == $opt) {
                                $var['html'] .= 'checked="true"';
                            }

                            $var['html'] .= '> ' . $var['display_options'][$n] . '
</label>';
                        }
                        $vars[$key][] = $var;
                        $conf[$key]['vars'] = $vars[$key];
                        break;
                    case 'file':
                        $var['html'] = '<input type="file" name="' . $var['code'] . '" class="fl">';
                        if ($var['value']) {
                            $var['html'] .= '<span class="img-show fl"><a href="' . $var['value'] . '" target="_blank" class="nyroModal"><i class="glyphicon glyphicon-picture top5" data-tooltipimg="' . $var['value'] . '" ectype="tooltip" data-toggle="tooltip" title="tooltip"></i></a></span>';
                        }
                        if ($var['del_img']) {
                            $var['html'] .= '<a href="shop_config.php?act=del&code=' . $var['code'] . '" class="btn btn-danger fr" style="line-height:20px;">　' . $lang['drop'] . '　</a>';
                        } else {
                            if ($var['value'] != '') {
                                $var['html'] .= '<img src="' . url('styles/images/yes.png') . '" alt="yes" class="mt10"/>';
                            } else {
                                $var['html'] .= '<img src="' . url('styles/images/no.png') . '" alt="no" class="mt10"/>';
                            }
                        }
                        $vars[$key][] = $var;
                        $conf[$key]['vars'] = $vars[$key];
                        break;
                    default:
                        break;
                }
            }
        }
        return $conf;
    }

    public function PayConfHtml($conf = [], $payInfo = [])
    {
        foreach ($conf as $key => $item) {
            switch ($item['type']){
                case 'text':
                    $item['html'] = '<input type="text" name="' . $item['code'] . '" class="form-control ' . $item['code'] . '" value="' . $payInfo[$item['code']] . '" placeholder="' . $item['name'] . '"/>';
                    break;
                case 'textarea':
                    $item['html'] = '<textarea name="' . $item['code'] . '" class="form-control ' . $item['code'] . '" row="5" placeholder= "' . $item['name'] . '" style="min-height:100px;">' . $payInfo[$item['code']] . '</textarea>';
                    break;
                case 'select':
                    $item['html'] = '<select name="' . $item['code'] . '" class="form-control ' . $item['code'] . '">';
                    foreach ($item['sel_val'] as $k => $val){
                        $item['html'] .= '<option value="'.$val;
                        if($payInfo[$item['code']] == $val){
                            $item['html'] .= ' selected ';
                        }
                        $item['html'] .= '">'.$k.'</option>';
                    }
                    $item['html'] .= '</select>';
                    break;
                default:
                    break;
            }
            $conf[$key] = $item;
        }

        return $conf;
    }
}