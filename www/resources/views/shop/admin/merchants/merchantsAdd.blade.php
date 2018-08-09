@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">店铺 - 添加店铺</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>请先选择商城已注册会员进行添加店铺操作。</li>
                    <li>请根据提示信息准确无误填写店铺信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" name="conf" action="{{url('admin/storelist')}}"
                          method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        @if(empty($id))
                            <div class="merchants-section">
                                <div class="tit"><h4>请选择会员</h4></div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">会员名称：</label>
                                    <div class="col-sm-6">
                                        <select name="user_name" class="form-control input-sm fl wd-250">
                                            <option value="0">名称</option>
                                        </select>

                                        <input type="text"
                                               class="form-control input-sm fl wd-120 mar-left-20 user_keywords"
                                               value="" placeholder="会员名称"/>
                                        <a href="javascript:;"
                                           class="btn btn-primary search btn-sm mar-left-10 fl">查询</a>
                                    </div>
                                </div>
                            </div>
                        @endif

                        @foreach($shopSteps as $shopStep)
                            @foreach($shopStep->mst as $mst)
                                <div class="merchants-section">
                                    <div class="tit"><h4>{{$mst->fields_titles}}</h4></div>
                                    @if($mst->steps_style == 0)
                                        @foreach($mst->textFields as $key => $field)
                                            @if($mst->fieldsForm[$key]['type'] == 'radio')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        @foreach($mst->fieldsForm[$key]['value'] as $k => $val)
                                                            <label class="radio-inline fl">
                                                                <input type="radio" name="{{$field}}" value="{{$val}}"
                                                                       @if($k == 0) checked @endif> {{$val}}
                                                            </label>
                                                        @endforeach
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'input')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        <input type="text" name="{{$field}}"
                                                               class="form-control input-sm"
                                                               value=""
                                                               placeholder="{{$mst->fieldsFormName[$key]}}"/>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'textarea')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                    <textarea name="{{$field}}" class="form-control" row="5"
                                                              placeholder="{{$mst->fieldsFormName[$key]}}"></textarea>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'select')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-3">
                                                        <select name="{{$field}}" class="form-control input-sm">
                                                            <option value="0">请选择</option>
                                                            @foreach($mst->fieldsForm[$key]['value'] as $k => $val)
                                                                <option value="{{$val}}">{{$val}}</option>
                                                            @endforeach
                                                        </select>
                                                    </div>
                                                    <div class="notic col-sm-6">{!! $mst->fieldsForm[$key]['notic'] !!}</div>
                                                </div>
                                            @elseif($mst->fieldsForm[$key]['type'] == 'other')
                                                <div class="form-group">
                                                    <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                        ：</label>
                                                    <div class="col-sm-6">
                                                        @if($mst->fieldsForm[$key]['value'][0] == 'dateFile')
                                                            <input type="file" name="{{$field}}"
                                                                   class=""
                                                                   value=""/>
                                                        @elseif($mst->fieldsForm[$key]['value'][0] == 'textArea')
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_country wa fl">
                                                                <option value="0">国家</option>
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_province wa fl mar-left-10">
                                                                <option value="0">省/直辖市</option>
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_city wa fl mar-left-10">
                                                                <option value="0">市</option>
                                                            </select>
                                                            <select name="{{$field}}[]"
                                                                    class="form-control input-sm shop_district wa fl mar-left-10">
                                                                <option value="0">区/县</option>
                                                            </select>
                                                        @elseif($mst->fieldsForm[$key]['value'][0] == 'dateTime')
                                                            @if($mst->fieldsForm[$key]['value'][1] == '1--30')
                                                                <input type="text" name="{{$field}}"
                                                                       class="form-control input-sm wd-180"
                                                                       value="2000-01-01"/>
                                                            @else
                                                                <input type="text" name="{{$field}}"
                                                                       class="form-control input-sm fl wd-300"
                                                                       value="2000-01-01～2099-12-31"/>
                                                                <div class="col-sm-3">
                                                                    <div class="checkbox">
                                                                        <label class="mar-right-10">
                                                                            <input type="checkbox" name="rank[]"
                                                                                   value="1">永久
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                            @endif
                                                        @endif
                                                    </div>
                                                </div>
                                            @endif
                                        @endforeach
                                    @elseif($mst->steps_style == 1)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>期望店铺类型：</label>
                                            <div class="col-sm-3">
                                                <select name="city" class="form-control input-sm shop_city">
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <select name="city" class="form-control input-sm shop_city">
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">授权有效期：</label>
                                            <div class="col-sm-6">
                                                <input type="text" name="shop_name"
                                                       class="form-control input-sm wd-300 fl"
                                                       value="" placeholder="店铺名称"/>
                                                <div class="col-sm-3">
                                                    <div class="checkbox">
                                                        <label class="mar-right-10">
                                                            <input type="checkbox" name="rank[]" value="1">永久
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">相关授权书：</label>
                                            <div class="col-sm-3">
                                                <input type="file" name="qrcode_thumb" class="fl">

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">相关授权书：</label>
                                            <div class="col-sm-3">
                                                <input type="file" name="qrcode_thumb" class="fl">

                                            </div>
                                        </div>

                                    @elseif($mst->steps_style == 2)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>主营类目：</label>
                                            <div class="col-sm-3">
                                                <select name="city" class="form-control input-sm shop_city">
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font class="red">*</font>
                                                详细类目：</label>
                                            <div class="col-sm-3">
                                                <select name="city" class="form-control input-sm shop_city">
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                    <option value="0">市</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6">
                                                <div class="checkbox">
                                                    <label class="mar-right-20 pad-all-15">
                                                        <input type="checkbox" name="rank[]" value="1" checked="">会员
                                                    </label>
                                                    <label class="mar-right-20 pad-all-15">
                                                        <input type="checkbox" name="rank[]" value="2" checked="">会员
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6">
                                                <div class="checkbox" style="padding-top: 0;">
                                                    <label class="mar-right-20 pad-all-15">
                                                        <input type="checkbox" name="rank[]" value="1" checked="">全选/反选
                                                    </label>
                                                    <a href="javascript:;" class="btn btn-info btn-sm">添加</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%" class="text-center">序号</th>
                                                        <th width="35%" class="text-center">一级类目</th>
                                                        <th width="35%" class="text-center">二级类目</th>
                                                        <th width="20%" class="text-center">操作</th>
                                                    </tr>
                                                    </thead>
                                                    @if(1)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="4" style="height: 80px;">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($stores as $store)
                                                            <tr class="">
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td class="text-center">
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger btn-del btn-sm"
                                                                       data-id="">删除</a>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font class="red">*</font>
                                                对应类目行业资质：</label>
                                            <div class="col-sm-3">
                                                <a class="skyblue lh30">《行业资质标准》</a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th width="10%" class="text-center">类目名称</th>
                                                        <th width="35%" class="text-center">资质名称</th>
                                                        <th width="35%" class="text-center">资质电子版</th>
                                                        <th width="20%" class="text-center">到期日</th>
                                                    </tr>
                                                    </thead>
                                                    @if(1)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="4">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($stores as $store)
                                                            <tr class="">
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td class="text-center">
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger btn-del btn-sm"
                                                                       data-id="">删除</a>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>
                                    @elseif($mst->steps_style == 3)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">
                                                <a href="javascript:;" class="btn btn-info btn-sm">添加品牌</a>
                                            </label>
                                            <div class="col-sm-9">
                                                <table class="table table-hover table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th width="7%" class="text-center">序号</th>
                                                        <th width="15%" class="text-center">品牌中文名称</th>
                                                        <th width="15%" class="text-center">品牌英文名称</th>
                                                        <th width="10%" class="text-center">品牌首字母</th>
                                                        <th width="10%" class="text-center">品牌LOGO</th>
                                                        <th width="14%" class="text-center">品牌类型</th>
                                                        <th width="14%" class="text-center">经营类型</th>
                                                        <th width="15%" class="text-center">操作</th>
                                                    </tr>
                                                    </thead>
                                                    @if(1)
                                                        <tbody>
                                                        <tr class="">
                                                            <td class="no-records" colspan="8">
                                                                没有找到任何记录
                                                            </td>
                                                        </tr>
                                                        </tbody>
                                                    @else
                                                        <tbody>
                                                        @foreach($stores as $store)
                                                            <tr class="">
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                                <td class="text-center">
                                                                    <a type="button" href="javascript:;"
                                                                       class="btn btn-danger btn-del btn-sm"
                                                                       data-id="">删除</a>
                                                                </td>
                                                            </tr>
                                                        @endforeach
                                                        </tbody>
                                                    @endif
                                                </table>
                                            </div>
                                        </div>
                                    @elseif($mst->steps_style == 4)
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>期望店铺类型：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label"><font
                                                            class="red">期望店铺类型</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">旗舰店命名规范：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label">店铺名称：品牌名|类目描述|旗舰店/官方旗舰店 <font class="red">(也可自定义,如：***官方旗舰店)</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"><font
                                                        class="red">*</font>店铺名称：</label>
                                            <div class="col-sm-6">
                                                <label class="control-label"><font
                                                            class="red">仅作为参考，最终已审核通过的店铺名称为准。</font></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label"></label>
                                            <div class="col-sm-6 brand-select-group">
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">选择品牌名：</label>
                                                    <div class="col-sm-6">
                                                        <select name="city" class="form-control input-sm shop_city">
                                                            <option value="0">市</option>
                                                            <option value="0">市</option>
                                                            <option value="0">市</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">类目描述关键词：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="shop_name"
                                                               class="form-control input-sm"
                                                               value="" placeholder="店铺名称"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label">选择店铺后缀：</label>
                                                    <div class="col-sm-6">
                                                        <select name="city" class="form-control input-sm shop_city">
                                                            <option value="0">市</option>
                                                            <option value="0">市</option>
                                                            <option value="0">市</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"><font
                                                                class="red">*</font>期望店铺名称：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="shop_name"
                                                               class="form-control input-sm"
                                                               value="" placeholder="店铺名称"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"><font
                                                                class="red">*</font>期望店铺登陆用户名：</label>
                                                    <div class="col-sm-5">
                                                        <input type="text" name="shop_name"
                                                               class="form-control input-sm"
                                                               value="" placeholder="店铺名称"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    @endif
                                </div>
                            @endforeach
                        @endforeach

                        <div class="merchants-section">
                            <div class="tit"><h4>管理员操作</h4></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设置是否审核其商品：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="0"> 否
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否为自营店铺：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="0"> 否
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 是
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否关闭店铺：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="0"> 关闭
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 开启
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">审核使用店铺名称：</label>
                                <div class="col-sm-2">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="0"> 未审核
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 已审核
                                    </label>
                                </div>
                                <div class="notic col-sm-4" style="line-height: 24px;"><font class="red">（店铺申请使用店铺名称类型：入驻品牌店铺名称）</font></div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">店铺信息审核：</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="0"> 未审核
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 通过
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="kf_type" value="1"> 未通过
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">店铺等级：</label>
                                <div class="col-sm-4">
                                    <select name="city" class="form-control input-sm shop_city">
                                        <option value="0">市</option>
                                        <option value="0">市</option>
                                        <option value="0">市</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">等级年限：</label>
                                <div class="col-sm-3">
                                    <input type="text" name="shop_name" class="form-control input-sm"
                                           value="" placeholder="店铺名称"/>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　"
                                       class="btn btn-danger clearfix">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 1, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_province').html($html);
                    }
                });
            });

            $('.shop_province').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 2, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_city').html($html);
                    }
                });
            });

            $('.shop_city').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 3, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_district').html($html);
                    }
                });
            });

            //搜索会员名称
            $('.search').on('click', function () {
                var keywords = $('.user_keywords').val();
                $.post("{{url('admin/search')}}", {
                    type: 4,
                    keywords: keywords,
                    '_token': '{{csrf_token()}}'
                }, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('select[name=user_name]').html($html);
                    }
                });
            });

            $('input[name=review_status]').click(function () {
                $('.review_content').hide();
                if ($(this).val() == 2) {
                    $('.review_content').show();
                }
            });

            //时间控件
            $('input[name=business_term]').daterangepicker(optionDateDay, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                var e = end.format('YYYY-MM-DD');
                var t = s + '～' + e;
                $('input[name=business_term]').val(t);
            });
            $('input[name=establish_date]').daterangepicker(optionDateSingle, function (start, end) {
                var s = start.format('YYYY-MM-DD');
                $('input[name=establish_date]').val(s);
            });
        });
    </script>
@endsection
@endsection