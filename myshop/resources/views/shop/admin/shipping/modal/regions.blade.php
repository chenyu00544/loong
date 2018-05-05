@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="regions-wrap clearfix">
            <ul>
                @foreach($regions as $val)
                    <li class="fl mar-all-5 wd120">
                        <a class="cursor sel-pro">
                            <input type="checkbox" name="province[{{$val['id']}}]" value="{{$val['id']}}"
                                   id="province_{{$val['id']}}" class="fl dis-bk regions pro">
                            <label class="ui-label cursor" for="province_{{$val['id']}}">
                                <span>{{$val['name']}}</span>
                                <span class="green child_num">(0)</span>
                            </label>
                        </a>
                        <i class="glyphicon glyphicon-menu-down ft-12 cl-darkgray cursor show-more"></i>
                        <ul class="region-city hide" onclick="event.stopPropagation();">
                            @foreach($val['subRegion'] as $value)
                                <li class="fl mar-all-5 sel-city">
                                    <input type="checkbox" name="city[{{$value['id']}}]" value="{{$value['id']}}"
                                           id="city_{{$value['id']}}" class="fl dis-bk regions city">
                                    <label class="cursor" for="city_{{$value['id']}}">
                                        <span>{{$value['name']}}</span>
                                    </label>
                                </li>
                            @endforeach
                        </ul>
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
            <input type="hidden" value="{{$id}}" class="extend_id">
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
                        $(this).prop("checked", false);
                    });
                    $('.child_num').html('(0)');
                } else {
                    $('input[type=checkbox]').each(function () {
                        $(this).prop("checked", true);
                    });
                    $('.region-city').each(function () {
                        $(this).parent().find('.child_num').html('(' + $(this).find('input').length + ')');
                    })
                }
            });

            $('.show-more').click(function () {

                var flag = $(this).hasClass('glyphicon-menu-up');

                $('.region-city').addClass('hide');
                $('.show-more').addClass('glyphicon-menu-down');
                $('.show-more').removeClass('glyphicon-menu-up');

                if (flag) {
                    $(this).addClass('glyphicon-menu-up');
                    $(this).removeClass('glyphicon-menu-down');
                }

                if ($(this).hasClass('glyphicon-menu-down')) {
                    $(this).addClass('glyphicon-menu-up');
                    $(this).removeClass('glyphicon-menu-down');
                    $(this).parent().children('.region-city').removeClass('hide');
                } else {
                    $(this).addClass('glyphicon-menu-down');
                    $(this).removeClass('glyphicon-menu-up');
                    $(this).parent().children('.region-city').addClass('hide');
                }
                var rg = $(this).parent().children('.region-city').css('right');
                if (parseInt(rg) < 0) {
                    $(this).parent().children('.region-city').css('right', 0);
                }
                event.stopPropagation();
            });

            //选择省
            $('.sel-pro').click(function () {
                if ($(this).children('input').is(':checked')) {
                    $(this).parent().find('input[type=checkbox]').each(function () {
                        $(this).prop("checked", true);
                    });
                    $(this).find('.child_num').html('(' + ($(this).parent().find('input[type=checkbox]').length - 1) + ')');

                } else {
                    $(this).parent().find('input[type=checkbox]').each(function () {
                        $(this).prop("checked", false);
                    });
                    $(this).find('.child_num').html('(0)');
                }
            });

            //选择市
            $('.sel-city').click(function () {
                var i = 0;
                $(this).parent().find('input').each(function () {
                    if ($(this).is(':checked')) {
                        i++;
                    }
                });
                if ($(this).parent().find('input').length > 0) {
                    $(this).parent().parent().find('.pro').prop("checked", true);
                }
                $(this).parent().parent().find('.child_num').html('(' + i + ')');
            })

            $('body').click(function () {
                $('.region-city').addClass('hide');
                $('.show-more').addClass('glyphicon-menu-down');
                $('.show-more').removeClass('glyphicon-menu-up');
            });

            //给父页面传值
            $('.btn-sure').on('click', function () {
                var id = $('.extend_id').val();
                var areaStr = '';
                var topAreaStr = '';
                $('.city').each(function () {
                    if ($(this).is(':checked')) {
                        areaStr += $(this).val() + ',';
                    }
                });
                areaStr = areaStr.substr(0, areaStr.length-1);
                $('.pro').each(function () {
                    if ($(this).is(':checked')) {
                        topAreaStr += $(this).val() + ',';
                    }
                });
                topAreaStr = topAreaStr.substr(0,topAreaStr.length-1);
                $.post('{{url('admin/transport/changes')}}', {
                    area_id: areaStr,
                    top_area_id: topAreaStr,
                    _token: '{{csrf_token()}}',
                    id: id,
                    type: 'area_update'
                }, function (data) {

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