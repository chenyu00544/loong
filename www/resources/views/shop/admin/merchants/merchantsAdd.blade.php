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
                                    @foreach($mst->textFields as $key => $field)
                                        @if($mst->fieldsForm[$key]['type'] == 'radio')
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                    ：</label>
                                                <div class="col-sm-4">
                                                    @foreach($mst->fieldsForm[$key]['value'] as $k => $val)
                                                        <label class="radio-inline fl">
                                                            <input type="radio" name="{{$field}}" value="{{$val}}"
                                                                   @if($k == 0) checked @endif> {{$val}}
                                                        </label>
                                                    @endforeach
                                                </div>
                                            </div>
                                        @elseif($mst->fieldsForm[$key]['type'] == 'input')
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                    ：</label>
                                                <div class="col-sm-3">
                                                    <input type="text" name="{{$field}}" class="form-control input-sm"
                                                           value=""
                                                           placeholder="{{$mst->fieldsFormName[$key]}}"/>
                                                </div>
                                            </div>
                                        @elseif($mst->fieldsForm[$key]['type'] == 'textarea')
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                    ：</label>
                                                <div class="col-sm-3">
                                                    <textarea name="{{$field}}" class="form-control" row="5"
                                                              placeholder="{{$mst->fieldsFormName[$key]}}"></textarea>
                                                </div>
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
                                            </div>
                                        @elseif($mst->fieldsForm[$key]['type'] == 'other')
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">{{$mst->fieldsFormName[$key]}}
                                                    ：</label>
                                                <div class="col-sm-3">
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
                                                                   value=""/>
                                                        @else
                                                            <input type="text" name="{{$field}}"
                                                                   class="form-control input-sm fl wd-300"
                                                                   value=""/>
                                                            <div class="col-sm-3">
                                                                <div class="checkbox">
                                                                    <label class="mar-right-10">
                                                                        <input type="checkbox" name="rank[]" value="1"
                                                                               checked="">永久
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        @endif
                                                    @endif
                                                </div>
                                            </div>
                                        @endif
                                    @endforeach
                                </div>
                            @endforeach
                        @endforeach
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
        });
    </script>
@endsection
@endsection