@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="regions-wrap clearfix">
            <ul>
                @foreach($express as $val)
                    <li class="fl mar-all-5 wd120">
                        <a class="cursor sel-pro">
                            <input type="radio" name="province" value="{{$val->shipping_id}}"
                                   id="province_{{$val->shipping_id}}" class="fl dis-bk regions express"
                                   @if($val->selected) checked @endif @if($val->disabled) disabled @endif>
                            <label class="ui-label cursor" for="province_{{$val->shipping_id}}">
                                <span class="express-name">{{$val->shipping_name}}</span>
                            </label>
                        </a>
                    </li>
                @endforeach
                <li class="fl mar-all-5 wd80">
                    <a class="cursor sel-all">
                        <input type="checkbox" name="select-all" id="select-all" class="fl dis-bk regions">
                        <label class="ui-label cursor" for="select-all">
                            <span>全选</span>
                        </label>
                    </a>
                </li>
            </ul>
        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" value="{{$id}}" class="express_id">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
        <div class="weight-goods-name" style="text-align: center;"></div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        $(function () {

            //全选
            $('.sel-all').click(function () {
                if ($('input[name=select-all]').is(':checked')) {
                    $('input[type=checkbox]').each(function () {
                        if (!$(this).prop("disabled")) {
                            $(this).prop("checked", false);
                        }
                    });
                    $('.child_num').html('(0)');
                } else {
                    $('input[type=checkbox]').each(function () {
                        if (!$(this).prop("disabled")) {
                            $(this).prop("checked", true);
                        }
                    });
                    $('.region-city').each(function () {
                        var num = 0;
                        $(this).find('input').each(function () {
                            if (!$(this).prop("disabled")) {
                                num++;
                            }
                        });
                        $(this).parent().find('.child_num').html('(' + num + ')');
                    })
                }
            });

            //给父页面传值
            $('.btn-sure').on('click', function () {
                var id = $('.express_id').val();
                var expressStr = '';
                var html = '';
                $('.express').each(function () {
                    if ($(this).is(':checked')) {
                        expressStr += $(this).val() + ',';
                        var express_name = $(this).parent().find('.express-name').html();
                        html += express_name + ',';
                    }
                });
                html = html.substr(0, html.length - 1);
                expressStr = expressStr.substr(0, expressStr.length - 1);

                $.post('{{url('admin/transport/changes')}}', {
                    shipping_id: expressStr,
                    _token: '{{csrf_token()}}',
                    id: id,
                    type: 'express_update'
                }, function (data) {
                    var transInfo = parent.$('.express-info-' + id);
                    transInfo.html(html);
                    parent.layer.close(index);
                })
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });
    </script>
@endsection
@endsection