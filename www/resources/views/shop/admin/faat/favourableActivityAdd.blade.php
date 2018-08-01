@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 优惠活动</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>优惠活动相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/favourable')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠活动名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="act_name" class="form-control input-sm" value=""
                                       placeholder="优惠活动名称" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠起止时间：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="start_end_date"
                                       id="start_end_date" class="form-control input-sm"
                                       value="{{$now_date}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>享受优惠的会员等级：</label>
                            <div class="col-sm-8">
                                <div class="checkbox">
                                    @foreach($userRanks as $userRank)
                                        <label class="mar-right-10">
                                            <input type="checkbox" name="rank[]"
                                                   value="{{$userRank->rank_id}}">{{$userRank->rank_name}}
                                        </label>
                                    @endforeach
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上传图片：</label>
                            <div class="col-sm-4">
                                <input type="file" name="activity_thumb">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">使用类型：</label>
                            <div class="col-sm-4">
                                <select name="userFav_type" class="form-control select input-sm wd120">
                                    <option value="0">自主使用</option>
                                    <option value="1">全场通用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">终端类型：</label>
                            <div class="col-sm-4">
                                <select name="terminal_type" class="form-control select input-sm wd120">
                                    <option value="all">全部通用</option>
                                    <option value="pc">PC端</option>
                                    <option value="web">WEB端</option>
                                    <option value="app">APP端</option>
                                    <option value="wxapp">微信小程序</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠范围：</label>
                            <div class="col-sm-4">
                                <select name="act_range" class="form-control select input-sm wd120">
                                    <option value="0">全部商品</option>
                                    <option value="1">以下分类</option>
                                    <option value="2">以下品牌</option>
                                    <option value="3">以下商品</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group rang-ext-val" style="display: none;">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-7">
                                <div class="checkbox bg-eee pad-bt-10 rang-ext-val-list">
                                </div>
                            </div>
                        </div>
                        <div class="form-group rang-ext" style="display: none;">
                            <label class="col-sm-4 control-label"><b>*</b>搜索并加入优惠范围：</label>
                            <div class="col-sm-8">
                                <input type="text" class="keyword-1 form-control wd-120 input-sm fl" placeholder="关键字">
                                <a href="javascript:;"
                                   class="btn btn-info input-sm btn-search-1 fl mar-left-10" style="padding: 4px 10px;">搜索</a>
                                <div class="cate-option fl">
                                    <select class="result_val_1 form-control select input-sm xwd400">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                                <a href="javascript:;" class="btn btn-info input-sm btn-add-1 fl mar-left-10"
                                   style="padding: 4px 10px;">添加</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>金额下限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="min_amount" class="form-control input-sm" value=""
                                       placeholder="金额下限">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>金额上限：</label>
                            <div class="col-sm-3">
                                <input type="text" name="max_amount" class="form-control input-sm" value="0"
                                       placeholder="金额上限">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">优惠方式：</label>
                            <div class="col-sm-4">
                                <select name="act_type" class="form-control select input-sm wd120 fl">
                                    <option value="0">享受赠品（特惠品）</option>
                                    <option value="1">享受现金减免</option>
                                    <option value="2">享受价格折扣</option>
                                </select>
                                <input type="text" name="act_type_ext"
                                       class="form-control input-sm wd-80 fl mar-left-10" value="1"
                                       placeholder="数值">
                            </div>
                        </div>
                        <div class="form-group act-type-ext">
                            <label class="col-sm-4 control-label">搜索并加入赠品（特惠品）：</label>
                            <div class="col-sm-8">
                                <input type="text" class="keyword-2 form-control wd-120 input-sm fl" placeholder="关键字">
                                <a href="javascript:;"
                                   class="btn btn-info input-sm btn-search-2 fl mar-left-10" style="padding: 4px 10px;">搜索</a>
                                <div class="cate-option fl">
                                    <select class="result_val_2 form-control select input-sm xwd400">
                                        <option value="0">请选择</option>
                                    </select>
                                </div>
                                <a href="javascript:;" class="btn btn-info input-sm btn-add-2 fl mar-left-10"
                                   style="padding: 4px 10px;">添加</a>
                            </div>
                        </div>
                        <div class="form-group act-ext-val" style="display: none;">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-6">
                                <table class="table table-hover table-bordered">
                                    <thead>
                                    <tr>
                                        <th width="80%" class="text-center">赠品（特惠品）</th>
                                        <th width="20%" class="text-center">价格</th>
                                    </tr>
                                    </thead>
                                    <tbody class="act-ext-val-list">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
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
            $('#start_end_date').daterangepicker(optionDateSet, function (start, end) {
                var s = start.format('YYYY-MM-DD HH:mm:ss');
                var e = end.format('YYYY-MM-DD HH:mm');
                var t = s + '～' + e + ':59';
                $('#start_end_date').val(t);
            });

            //优惠范围选择
            $('select[name=act_range]').on('change', function () {
                $('.rang-ext-val-list').html('');
                $('.rang-ext-val').hide();
                if ($(this).val() != 0) {
                    $('.rang-ext').show();
                } else {
                    $('.rang-ext').hide();
                }
            });
            //优惠范围搜索
            $('.btn-search-1').on('click', function () {
                var keywords = $('.keyword-1').val();
                var type = $('select[name=act_range]').val();
                if (keywords != '') {
                    $.post("{{url('admin/search')}}", {
                        keywords: keywords,
                        type: type,
                        '_token': '{{csrf_token()}}'
                    }, function (data) {
                        var html = '<option value="0">请选择</option>';
                        if (data.code == 1) {
                            for (var i in data.data) {
                                html += '<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>'
                            }
                        } else {
                            layer.msg(data.msg, {icon: data.code});
                            html += '<option value="0">没有搜索到相关记录，请重新搜索</option>'
                        }
                        $('.result_val_1').html(html)
                    })
                } else {
                    layer.msg('请输入关键字', {icon: 5});
                }
            });

            //优惠范围添加
            $('.btn-add-1').on('click', function () {
                var bool = false;
                var val = $('.result_val_1').val();
                $('input[name="act_range_ext[]"]').each(function () {
                    if (val == $(this).val()) {
                        bool = true;
                    }
                });
                $('input[name="gift_id[]"]').each(function () {
                    if (val == $(this).val()) {
                        bool = true;
                    }
                });
                if (bool || val == 0) {
                    layer.msg('选择错误或已经存在', {icon: 4})
                } else {
                    var text = $('.result_val_1').find("option:selected").text();
                    $('.rang-ext-val').show();
                    var html = '<label class="mar-all-10 db">' +
                        '<input type="checkbox" name="act_range_ext[]" value="' + val + '" checked>' + text + '</label>';
                    $('.rang-ext-val-list').append(html);
                }
            });

            //优惠方式选择
            $('select[name=act_type]').on('change', function () {
                $('.act-ext-val').hide();
                $('.act-ext-val-list').html('');

                if ($(this).val() == 0) {
                    $('.act-type-ext').show();
                    $('input[name=act_type_ext]').val(1);
                } else if ($(this).val() == 1) {
                    $('.act-type-ext').hide();
                    $('input[name=act_type_ext]').val(0);
                } else if ($(this).val() == 2) {
                    $('.act-type-ext').hide();
                    $('input[name=act_type_ext]').val(0.99);
                }
            });

            //优惠方式搜索
            $('.btn-search-2').on('click', function () {
                var keywords = $('.keyword-2').val();
                if (keywords != '') {
                    $.post("{{url('admin/search')}}", {
                        keywords: keywords,
                        type: 3,
                        '_token': '{{csrf_token()}}'
                    }, function (data) {
                        var html = '<option value="0">请选择</option>';
                        if (data.code == 1) {
                            for (var i in data.data) {
                                html += '<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>'
                            }
                        } else {
                            layer.msg(data.msg, {icon: data.code});
                            html += '<option value="0">没有搜索到相关记录，请重新搜索</option>'
                        }
                        $('.result_val_2').html(html)
                    })
                } else {
                    layer.msg('请输入关键字', {icon: 5});
                }
            });

            //优惠方式添加
            $('.btn-add-2').on('click', function () {
                var bool = false;
                var val = $('.result_val_2').val();
                $('input[name="gift_id[]"]').each(function () {
                    if (val == $(this).val()) {
                        bool = true;
                    }
                });
                $('input[name="act_range_ext[]"]').each(function () {
                    if (val == $(this).val()) {
                        bool = true;
                    }
                });
                if (bool || val == 0) {
                    layer.msg('选择错误或已经存在', {icon: 4})
                } else {
                    var text = $('.result_val_2').find("option:selected").text();
                    $('.act-ext-val').show();
                    var html = '<tr>' +
                        '<td>' +
                        '<label class="checkbox-items">' +
                        '<input type="checkbox" name="gift_id[]" class="ui-checkbox" value="' + val + '" checked>' + text +
                        '</label>' +
                        '</td>' +
                        '<td class="text-center">' +
                        '<input type="text" name="gift_price[]" class="form-control input-sm wd-80" value="0" placeholder="价格" style="display: inline-block"> ' +
                        '<input name="gift_name[]" type="hidden" value="' + text + '"> ' +
                        '</td> ' +
                        '</tr>';
                    $('.act-ext-val-list').append(html);
                }
            });
        });
    </script>
@endsection
@endsection