<div class="item {{$var['code']}}" data-val="{{$var['id']}}">
    <div class="label">{{$var['name']}}：</div>
    @if($var['type'] == 'text')
        <div class="label_value">
            <input type="text" name="value[{{$var['id']}}]" class="text {{$var['code']}}" value="{{$var['value']}}"
                   autocomplete="off"/>
            <div class="form_prompt"></div>
            @if($var['desc'])<div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == 'password')
        <div class="label_value">
            <input type="password" style="display:none"/>
            <input type="password" name="value[{{$var['id']}}]" class="text" value="{{$var['value']}}"
                   autocomplete="off"/>
            <div class="form_prompt"></div>
            @if($var['desc'])
                <div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == 'textarea')
        <div class="label_value">
            <textarea class="textarea" name="value[{{$var['id']}}]" id="role_describe">{{$var['value']}}</textarea>
            <div class="form_prompt"></div>
            @if($var['desc'])
                <div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == 'select')
        <div class="label_value">
            <div class="checkbox_items">
                @foreach($var['store_options'] as $opt)
                    <div class="checkbox_item">
                        <input type="radio" name="value[{{$var['id']}}]" class="ui-radio evnet_{{$var['code']}}"
                               id="value_{{$var['id']}}_{{$loop->index}}" value="{{$opt}}"
                               @if ($var['value'] == $opt)checked="true" @endif
                               @if ($var['code'] == 'rewrite')
                               onclick="return ReWriterConfirm(this);"
                               @endif
                               @if ($var['code'] == 'smtp_ssl' && $opt == 1)
                               onclick="return confirm({{$lang['smtp_ssl_confirm']}});"
                               @endif
                               @if ($var['code'] == 'enable_gzip' && $opt == 1)
                               onclick="return confirm({{$lang['gzip_confirm']}});"
                               @endif
                               @if ($var['code'] == 'retain_original_img' && $opt == 0)
                               onclick="return confirm({{$lang['retain_original_confirm']}});"
                                @endif />
                        <label for="value_{{$var['id']}}_{{$loop->index}}"
                               class="ui-radio-label">{{$var['display_options'][$loop->index]}}</label>
                    </div>
                @endforeach
            </div>
            <div class="form_prompt"></div>
            @if ($var['desc'])
                <div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == "options")
        <div class="label_value">
            <div id="select{{$var['id']}}_" class="imitate_select select_w320">
                <div class="cite">{{$lang['please_select']}}</div>
                <ul>
                    @foreach($lang['cfg_range'][$var['code']] as $options)
                        <li><a href="javascript:;" data-value="{{$loop->index}}" class="ftx-01">{{$options}}</a></li>
                    @endforeach
                </ul>
                <input name="value[{{$var['id']}}]" type="hidden" value="{{$var['value']}}"
                       id="{{$var['id']}}__val">
            </div>
            <div class="form_prompt"></div>
            @if ($var['desc'])
                <div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == 'file')
        <div class="label_value">
            <div class="type-file-box">
                <input type="button" name="button" id="button" class="type-file-button" value=""/>
                <input type="file" class="type-file-file" name="{{$var['code']}}" size="30" data-state="imgfile" hidefocus="true" value=""/>
                @if($var['value'])
                    <span class="show">
                <a href="{{$var['value']}}" target="_blank" class="nyroModal">
                    <i class="icon icon-picture" data-tooltipimg="{{$var['value']}}" ectype="tooltip"
                       title="tooltip"></i>
                </a>
            </span>
                @endif
                <input type="text" name="textfile" class="type-file-text" id="textfield" readonly/>
            </div>
            @if($var['del_img'])
                <a href="shop_config.php?act=del&code={{$var['code']}}" class="btn red_btn h30 mr10 fl"
                   style="line-height:30px;">{{$lang['drop']}}</a>
            @else
                @if($var['value'] != '')
                    <img src="{{url('styles/admin/images/yes.gif')}}" alt="yes" class="fl mt10"/>
                @else
                    <img src="{{url('styles/admin/images/no.gif')}}" alt="no" class="fl mt10"/>
                @endif
            @endif
            <div class="form_prompt"></div>
            @if ($var['desc'])
                <div class="notic">{{nl2br($var['desc'])}}</div>@endif
        </div>
    @elseif($var['type'] == "manual")
        @if ($var['code'] == 'shop_country')
            <div class="ui-dropdown smartdropdown alien">
                <select value="{{$var['value']}}" name="value[{{$var['id']}}]" id="selCountry" class="txt" onchange="selectProvince(this)">
                    <option>国家</option>
                </select>
            </div>
        @elseif($var['code'] == 'shop_province')
            <div class="ui-dropdown smartdropdown alien">
                <select value="{{$var['value']}}" name="value[{{$var['id']}}]" id="selProvinces" class="txt"
                        onchange="selectCity(this)">
                    <option>省/直辖市</option>
                </select>
            </div>
        @elseif($var['code'] == 'shop_city')
            <div id="dlCity" class="ui-dropdown smartdropdown alien">
                <select value="{{$var['value']}}" name="value[{{$var['id']}}]" id="selCities" class="txt">
                    <option>市</option>
                </select>
            </div>
        @elseif($var['code'] == 'lang')
            <div class="label_value">
                <div id="select{$var['id']}}_{$k}" class="imitate_select select_w320">
                    <div class="cite">{{$lang['please_select']}}</div>
                    <ul>
                        @foreach($lang['lang_list'] as $options)
                            <li><a href="javascript:;" data-value="{{$options}}" class="ftx-01">{{$options}}</a></li>
                        @endforeach
                    </ul>
                    <input name="value[{{$var['id']}}]" type="hidden" value="{{$var['value']}}"
                           id="{{$var['id']}}__val">
                </div>
                <div class="form_prompt"></div>
                @if ($var['desc'])
                    <div class="notic">{{nl2br($var['desc'])}}</div>@endif
            </div>
        @elseif($var['code'] ==  'invoice_type')
            <div class="label_value">
                <table>
                    <tr>
                        <td colspan="2">
                            <div id="consumtable">
                                <p>
                                    <label class="fl mr10">{{$lang['invoice_type']}}</label>
                                    <input type="text" class="text w120" name="invoice_type[]" size="10"
                                           autocomplete="off"/>
                                    <label class="fl mr10">{{$lang['invoice_rate']}}</label>
                                    <input type="text" class="text w120" name="invoice_rate[]" size="10"/>
                                    <input type="button" onclick="addCon_amount(this)" class="button fl"
                                           value="{{$lang['add']}}" autocomplete="off"/>
                                    <span class="form_prompt ml10 fl"></span>
                                </p>
                                {{--@if($invoice_list)--}}
                                {{--@foreach($invoice_list as $invoice)--}}
                                {{--@if($invoice['type'])--}}
                                {{--<p class="mt10">--}}
                                {{--<label class="fl mr10">{{$lang['invoice_type']}}</label>--}}
                                {{--<input type="text" name="invoice_type[]" value="{{$invoice['type']}}"--}}
                                {{--class="text w120" size="10" autocomplete="off"/>--}}
                                {{--<label class="fl mr10">{{$invoice['invoice_rate']}}</label>--}}
                                {{--<input type="text" name="invoice_rate[]" value="{{$invoice['rate']}}"--}}
                                {{--size="10" class="text w120" autocomplete="off"/>--}}
                                {{--<a href='javascript:;' class='removeV' onclick='removeCon_amount(this)'><img--}}
                                {{--src='images/no.gif' title='删除'></a>--}}
                                {{--</p>--}}
                                {{--@endif--}}
                                {{--@endforeach--}}
                                {{--@endif--}}
                            </div>
                        </td>
                    </tr>
                </table>
                <div class="form_prompt"></div>
                @if ($var['desc'])
                    <div class="notic" style="padding:0px;">{{nl2br($var['desc'])}}</div>@endif
            </div>
        @endif
    @endif
</div>

