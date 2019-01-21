@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">第三方服务 - 邮件发送</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>当您的服务器不支持 Mail 函数的时候您也可以选用 SMTP 作为邮件服务器。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="javascript:;" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">

                        <div class="form-group mar-top-20">
                            <label class="col-sm-3 control-label">会员名称：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control fl max-wd-350" name="keywords" value=""
                                       placeholder="会员名称">
                                <a class="btn btn-primary mar-left-10 fl search">　搜索　</a>
                            </div>
                        </div>
                        <div class="form-group mar-top-20">
                            <label class="col-sm-3 control-label">会员列表：</label>
                            <div class="col-sm-6">
                                <div class="sort-info">
                                    <div id="cate-add" class="clearfix">
                                        <div class="sort-list sort-list-one" style="width: 100%;">
                                            <div class="sort-list-warp">
                                                <div class="category-list ps-container ps-active-y">
                                                    <ul class="userlist">
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group mar-top-20">
                            <label class="col-sm-3 control-label">副标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="subtitle" class="form-control"
                                       value=""
                                       placeholder="邮件的副标题">
                            </div>
                        </div>

                        <div class="mar-top-20">
                            <script id="editor" name="content" type="text/plain"></script>
                        </div>

                        <div class="form-group mar-top-20">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <a href="javascript:;" class="btn btn-danger clearfix send-email">　确定　</a>
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
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.config.js')}}"></script>
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.all.min.js')}}"></script>
    <script>
        $(function () {
            var ue = UE.getEditor('editor', {
                initialFrameHeight: 500,
                scaleEnabled: true
            });
            ue.ready(function () {
            });

            $('.send-email').on('click', function () {
                layer.msg('加载中', {
                    icon: 16
                    ,shade: 0.01
                });
                setTimeout(function(){
                    layer.closeAll('loading');
                }, 10000);
                var data = [];
                var content = ue.getContent();
                var subtitle = $('input[name=subtitle]').val();
                $('.userlist li').each(function (k, v) {
                    if ($(this).hasClass('current')) {
                        var email = $(this).data('email') + '';
                        var user_id = $(this).data('user_id');
                        var user_name = $(this).data('user_name');
                        if (email != "") {
                            var obj = new Object();
                            obj.userid = user_id;
                            obj.email = email;
                            obj.user_name = user_name;
                            data.push(obj);
                        }
                    }
                });
                if (data.length <= 0) {
                    return;
                }
                $.post("{{url('admin/email/send')}}", {
                    _token: '{{csrf_token()}}',
                    data: data,
                    content: content,
                    subtitle: subtitle
                }, function (data) {
                    layer.closeAll('loading');
                    layer.msg(data.msg, {icon: data.code});
                })
            });
            $('.search').on('click', function () {
                var keywords = $('input[name=keywords]').val();
                $.post("{{url('admin/search')}}", {
                    _token: '{{csrf_token()}}',
                    type: 4,
                    keywords: keywords
                }, function (data) {
                    var html = "";
                    $.each(data.data, function (k, v) {
                        html += '<li data-user_id="' + v.user_id + '" data-user_name="' + v.user_name + '"  data-email="' + v.email + '" class="">' +
                            '<a href="javascript:;">' +
                            '<i class="sc-icon" style="background-position:-162px -354px;width:30px;height:30px;top:3px;right: 3px;"></i>' +
                            '会员名称：' + v.user_name + '---会员邮箱：' + v.email +
                            '</a>' +
                            '</li>';
                    })
                    $('.ps-container .userlist').html(html)
                })
            });

            $('body').on('click', '.userlist li', function () {
                if ($(this).hasClass('current')) {
                    $(this).removeClass('current');
                } else {
                    $(this).addClass('current');
                }
            });
        });
    </script>
@endsection
@endsection