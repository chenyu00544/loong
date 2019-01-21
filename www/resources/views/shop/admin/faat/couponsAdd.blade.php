@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 优惠券添加</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>优惠券相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="brand" action="{{url('admin/coupons')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">优惠券类型：</label>
                            <div class="col-sm-4">
                                <select name="cou_type" class="form-control select input-sm">
                                    <option value="1">注册赠券</option>
                                    <option value="2">购物赠券</option>
                                    <option value="3">全场赠券</option>
                                    <option value="4">会员赠券</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠券名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="cou_name" class="form-control input-sm" value=""
                                       placeholder="优惠券名称" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠券标题：</label>
                            <div class="col-sm-3">
                                <input type="text" name="cou_title" class="form-control input-sm" value=""
                                       placeholder="优惠券标题" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠券起止时间：</label>
                            <div class="col-sm-4">
                                <input type="text" style="width: 300px" name="start_end_date"
                                       id="start_end_date" class="form-control input-sm"
                                       value="{{$now_date}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>优惠劵总张数：</label>
                            <div class="col-sm-2">
                                <input type="text" name="cou_total" class="form-control input-sm" value=""
                                       placeholder="总张数" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>面值：</label>
                            <div class="col-sm-2">
                                <input type="text" name="cou_money" class="form-control input-sm" value=""
                                       placeholder="面值" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>使用门槛：</label>
                            <div class="col-sm-2">
                                <input type="text" name="cou_man" class="form-control input-sm" value=""
                                       placeholder="购物满多少可使用" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">每人限领：</label>
                            <div class="col-sm-1">
                                <input type="text" name="cou_user_num" class="form-control input-sm" value="1"
                                       placeholder="每人限领" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">可使用商品：</label>
                            <div class="col-sm-4">
                                <select name="act_range" class="form-control select input-sm wd120">
                                    <option value="0">全部商品</option>
                                    <option value="1">指定分类</option>
                                    <option value="3">指定商品</option>
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
                            <label class="col-sm-4 control-label">搜索加入优惠范围：</label>
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
                        <div class="form-group cou_get_man" style="display: none;">
                            <label class="col-sm-4 control-label"><b>*</b>获取门槛：</label>
                            <div class="col-sm-2">
                                <input type="text" name="cou_get_man" class="form-control input-sm" value=""
                                       placeholder="购物满多少可获得" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group cou-rank" style="display: none;">
                            <label class="col-sm-4 control-label">参加会员：</label>
                            <div class="col-sm-8">
                                <div class="checkbox">
                                    @foreach($userRanks as $userRank)
                                        <label class="mar-right-10">
                                            <input type="checkbox" name="cou_ok_user[]"
                                                   value="{{$userRank->rank_id}}">{{$userRank->rank_name}}
                                        </label>
                                    @endforeach
                                </div>
                            </div>
                        </div>
                        <div class="form-group cou-goods-gift" style="display: none;">
                            <label class="col-sm-4 control-label">可赠券商品：</label>
                            <div class="col-sm-4">
                                <select name="act_type" class="form-control select input-sm wd120 fl">
                                    <option value="0">全部商品</option>
                                    <option value="1">指定分类</option>
                                    <option value="3">指定商品</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group cou-goods-s" style="display: none;">
                            <label class="col-sm-4 control-label">搜索加入优惠范围：</label>
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
                        <div class="form-group cou-goods-gift-val" style="display: none;">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-7">
                                <div class="checkbox bg-eee pad-bt-10 cou-gift-val-list">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">备注：</label>
                            <div class="col-sm-4">
                                <textarea name="cou_intro" id="" cols="30" rows="5" class="form-control"></textarea>
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

            //优惠券类型选择
            $('select[name=cou_type]').on('change', function () {
                $('input[name=cou_user_num]').attr('readonly', false);
                $('input[name=cou_user_num]').val(1);
                $('.cou_get_man').hide();
                $('.cou-goods-gift').hide();
                $('.cou-goods-s').hide();
                $('.cou-goods-gift-val').hide();
                $('.cou-gift-val-list').html('');
                if ($(this).val() == 1 || $(this).val() == 3) {
                    $('.cou-rank').hide();
                    $('input[name=cou_user_num]').attr('readonly', true);
                } else if ($(this).val() == 2) {
                    $('.cou-rank').show();
                    $('.cou-goods-gift').show();
                    $('.cou_get_man').show();
                } else if ($(this).val() == 4) {
                    $('.cou-rank').show();
                }
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
                $('input[name="cou_gift[]"]').each(function () {
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
                        '<input type="checkbox" name="cou_gift[]" value="' + val + '" checked>' + text + '</label>';
                    $('.rang-ext-val-list').append(html);
                }
            });

            //优惠方式选择
            $('select[name=act_type]').on('change', function () {
                $('.cou-gift-val-list').html('');
                $('.cou-goods-gift-val').hide();
                if ($(this).val() != 0) {
                    $('.cou-goods-s').show();
                } else {
                    $('.cou-goods-s').hide();
                }
            });
            //优惠方式搜索
            $('.btn-search-2').on('click', function () {
                var keywords = $('.keyword-2').val();
                var type = $('select[name=act_type]').val();
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
                $('input[name="cou_gift_1[]"]').each(function () {
                    if (val == $(this).val()) {
                        bool = true;
                    }
                });
                if (bool || val == 0) {
                    layer.msg('选择错误或已经存在', {icon: 4})
                } else {
                    var text = $('.result_val_2').find("option:selected").text();
                    $('.cou-goods-gift-val').show();
                    var html = '<label class="mar-all-10 db">' +
                        '<input type="checkbox" name="cou_gift_1[]" value="' + val + '" checked>' + text + '</label>';
                    $('.cou-gift-val-list').append(html);
                }
            });
        });
    </script>
@endsection
@endsection