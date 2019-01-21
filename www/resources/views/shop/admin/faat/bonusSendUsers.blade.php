@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>促销管理 - 发放红包</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>发放红包相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <div class="step">
                        <div class="clearfix pad-all-15 center wd-550">
                            <div class="fl">
                                <label class="radio-inline fs-16">
                                    <input type="radio" name="user_or_rank" class="radio-20" value="1" checked>　根据会员名称发放红包
                                </label>
                            </div>
                            <div class="fl mar-left-60">
                                <label class="radio-inline fs-16">
                                    <input type="radio" name="user_or_rank" class="radio-20" value="2">　根据会员等级发放红包
                                </label>
                            </div>
                        </div>
                        <input type="hidden" name="send_user" value="4">
                        <div class="step-near clearfix" style="margin-left:1%;">
                            <input name="keywords" value="" class="form-control fl max-wd-350 input-sm"
                                   placeholder="关键字">
                            <a class="btn btn-primary btn-search mar-left-10 fl input-sm"
                               style="padding: 4px 10px;">搜索</a>
                        </div>

                        <div class="sort-info clearfix">
                            <div id="cate-add" class="clearfix">
                                <div class="clearfix">
                                    <h4 class="fl goods-s">会员列表</h4><h4 class="fl goods-s">给下列用户发放红包</h4>
                                </div>
                                <div class="sort-list sort-list-one" style="width: 45%;margin-left:1%;margin-right: 8%">
                                    <div class="sort-list-warp">
                                        <div class="category-list ps-container ps-active-y">
                                            <ul class="s-list">
                                            </ul>
                                        </div>
                                        <div class="sort-point"></div>
                                    </div>
                                </div>
                                <div class="sort-list sort-list-one" style="width: 45%;margin-right: 0%">
                                    <div class="sort-list-warp">
                                        <div class="category-list ps-container ps-active-y">
                                            <ul class="sd-list">

                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix">
                                    <div class="fl goods-s-left">
                                        <a class="btn btn-default select-all mar-left-10 fl btn-sm">全选</a>
                                        <a class="btn btn-danger select-add mar-left-10 fl btn-sm">添加</a>
                                    </div>
                                    <div class="fl goods-s-right">
                                        <a class="btn btn-default select-all-r mar-left-10 fl btn-sm">全选</a>
                                        <a class="btn btn-danger select-remove mar-left-10 fl btn-sm">移除</a>
                                    </div>
                                </div>
                                <div class="clearfix text-center pad-bt-15">
                                    <a href="javascript:;" class="btn btn-danger select-send">确认发送红包</a>
                                </div>
                                <input type="hidden" value="{{$bonus->bonus_id}}" name="bonus_id">
                            </div>
                        </div>
                    </div>
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
            $('input[name=user_or_rank]').click(function () {
                if ($(this).val() == 1) {
                    $('input[name=send_user]').val(4);
                } else {
                    $('input[name=send_user]').val(5);
                }
            });

            $('.btn-search').on('click', function () {
                var keywords = $('input[name=keywords]').val();
                if (!keywords) {
                    layer.msg('请填写关键字');
                }
                var type = $('input[name=send_user]').val();
                $.post("{{url('admin/search')}}", {
                    '_token': "{{csrf_token()}}",
                    type: type,
                    keywords: keywords
                }, function (data) {
                    var html = '';
                    for (i in data.data) {
                        html += '<li class=""><i class="sc-icon sc_icon_ok"></i>' +
                            '<a href="javascript:;" data-value="787" class="s-goods-name">' + data.data[i].name + '</a>' +
                            '<input type="hidden" name="search[]" value="' + data.data[i].id + '">' +
                            '</li>';
                    }
                    $('.s-list').html(html);
                });
            });

            $('.s-list, .sd-list').on('click', 'li', function () {
                if ($(this).hasClass('current')) {
                    $(this).removeClass('current');
                } else {
                    $(this).addClass('current');
                }
            });

            //未选全选
            $('.select-all').on('click', function () {
                var bool = true;
                $('.s-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        bool = false;
                    }
                });
                if (bool) {
                    $('.s-list').find('li').addClass('current');
                } else {
                    $('.s-list').find('li').removeClass('current');
                }
            });
            //添加
            $('.select-add').on('click', function () {
                $('.s-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        var text = $(this).find('a').html();
                        var id = $(this).find('input').val();
                        var bool = true;
                        $('.sd-list').find('li').each(function () {
                            if(id == $(this).find('a').data('value')){
                                bool = false;
                            }
                        });
                        var html = '<li class=""><i class="sc-icon sc_icon_no"></i><a href="javascript:;" data-value="' + id + '"' +
                            'class="s-goods-name">' + text + '</a></li>';
                        if(bool){
                            $('.sd-list').append(html);
                        }
                    }
                });
            });

            //已选全选
            $('.select-all-r').on('click', function () {
                var bool = true;
                $('.sd-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        bool = false;
                    }
                });
                if (bool) {
                    $('.sd-list').find('li').addClass('current');
                } else {
                    $('.sd-list').find('li').removeClass('current');
                }
            });
            //移除
            $('.select-remove').on('click', function () {
                $('.sd-list').find('li').each(function () {
                    if ($(this).hasClass('current')) {
                        $(this).remove();
                    }
                });
            });

            //发放红包
            $('.select-send').on('click', function () {
                var bonus_id = $('input[name=bonus_id]').val();
                var type = $('input[name=send_user]').val();
                var ids = [];
                $('.sd-list').find('li').each(function () {
                    ids.push($(this).find('a').data('value'));
                });
                $.post("{{url('admin/bonus/adduser')}}", {
                    '_token': "{{csrf_token()}}",
                    type: type,
                    ids: ids,
                    bonus_id: bonus_id
                }, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                })
            });
        });
    </script>
@endsection
@endsection