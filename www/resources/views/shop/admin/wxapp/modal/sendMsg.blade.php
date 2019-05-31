@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <input type="hidden" name="openid" value="{{$id}}">
        <div class="form-group clearfix">
            <label class="control-label col-sm-4 fl">消息类型：</label>
            <div class="col-sm-8">
                <label class="radio-inline fl">
                    <input type="radio" name="msg_type" class="examine" value="1"
                           style="margin-top:0;" checked> 文本消息
                </label>
                <label class="radio-inline fl">
                    <input type="radio" name="msg_type" class="examine" value="2"
                           style="margin-top:0;"> 图片消息
                </label>
                <label class="radio-inline fl">
                    <input type="radio" name="msg_type" class="examine" value="3"
                           style="margin-top: 0;"> 图文链接
                </label>
                <label class="radio-inline fl">
                    <input type="radio" name="msg_type" class="examine" value="4"
                           style="margin-top: 0;"> 小程序卡片消息
                </label>
            </div>
        </div>
        <div class="weight-goods-name wxapp-msg">
            <div class="form-group clearfix">
                <label class="col-sm-4 control-label fl"><font class="red">*</font>文本内容：</label>
                <div class="col-sm-4 fl">
                    <textarea type="text" name="content" class="form-control wd-300" placeholder="文本内容"
                              rows="6"></textarea>
                </div>
                <div class="col-sm-4 fl">
                    <a type="button" class="btn btn-danger btn-add-text mar-all-8">添加链接</a>
                </div>
            </div>
        </div>

        <div class="weight-goods-name wxapp-msg hidden">
            <div class="form-group clearfix">
                <label class="col-sm-4 control-label fl"><font class="red">*</font>图片消息：</label>
                <div class="col-sm-4 fl">
                    <input type="file" name="wxapp_img">
                </div>
                <div class="col-sm-4 fl">
                    <a type="button" class="btn btn-danger btn-add-img">上传</a>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-4 control-label fl"><font class="red">*</font>图片Media_id：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="media_id" class="form-control" value="" readonly placeholder="图片Media_id">
                </div>
            </div>
        </div>

        <div class="weight-goods-name wxapp-msg hidden">
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>消息标题：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="link_title" class="form-control" value="" placeholder="消息标题">
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>图文描述：</label>
                <div class="col-sm-8 fl">
                    <textarea type="text" name="link_description" class="form-control wd-300"
                              placeholder="图文描述"></textarea>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>点击跳转的链接：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="link_url" class="form-control" value="" placeholder="图文链接消息被点击后跳转的链接">
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>展示的图片链接：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="link_thumb_url" class="form-control" value="" placeholder="消息标题">
                </div>
            </div>
        </div>

        <div class="weight-goods-name wxapp-msg hidden">
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>消息标题：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="wxapp_title" class="form-control" value="" placeholder="消息标题">
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>小程序的页面路径：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="wxapp_pagepath" class="form-control" value="" placeholder="小程序的页面路径">
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-4 control-label fl"><font class="red">*</font>消息卡片的封面：</label>
                <div class="col-sm-4 fl">
                    <input type="file" name="wx_img">
                </div>
                <div class="col-sm-4 fl">
                    <a type="button" class="btn btn-danger btn-add-wxappimg">上传</a>
                </div>
            </div>
            <div class="form-group clearfix">
                <label class="col-sm-6 control-label fl"><font class="red">*</font>消息卡片的封面：</label>
                <div class="col-sm-8 fl">
                    <input type="text" name="wxapp_thumb_media_id" class="form-control" readonly value=""
                           placeholder="消息卡片的封面">
                </div>
            </div>
        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" class="goods_id" value="">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">发送</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        $(function () {
            $('input[name=msg_type]').on('click', function () {
                $('.wxapp-msg').addClass('hidden');
                switch ($(this).val()) {
                    case '1':
                        $('.wxapp-msg').each(function (k, v) {
                            if (k === 0) {
                                $(this).removeClass('hidden');
                            }
                        });
                        break;
                    case '2':
                        $('.wxapp-msg').each(function (k, v) {
                            if (k === 1) {
                                $(this).removeClass('hidden');
                            }
                        });
                        break;
                    case '3':
                        $('.wxapp-msg').each(function (k, v) {
                            if (k === 2) {
                                $(this).removeClass('hidden');
                            }
                        });
                        break;
                    case '4':
                        $('.wxapp-msg').each(function (k, v) {
                            if (k === 3) {
                                $(this).removeClass('hidden');
                            }
                        });
                        break;
                }
                parent.layer.iframeAuto(index);
            });

            //发送文本消息添加链接按钮
            $('.btn-add-text').on('click', function () {
                var content = $('textarea[name=content]').val();
                $.post(
                    '{{url("admin/wxappsession/config/get")}}',
                    {
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        content += '<a href=""' +
                            '  data-miniprogram-appid="' + data.appid + '"' +
                            '  data-miniprogram-path=""' +
                            '>点击跳转</a>';
                        $('textarea[name=content]').val(content);
                    }
                );
            });

            //上传图片文件到微信图片消息
            $('.btn-add-img').on('click', function () {
                var file = $('input[name=wxapp_img]')[0].files[0];
                var form = new FormData();
                form.append('img_file', file);
                form.append('_token', '{{csrf_token()}}');
                uploadMedia(form, function (mid) {
                    $('input[name=media_id]').val(mid);
                });
            });

            //上传图片文件到微信小程序卡片
            $('.btn-add-wxappimg').on('click', function () {
                var file = $('input[name=wx_img]')[0].files[0];
                var form = new FormData();
                form.append('img_file', file);
                form.append('_token', '{{csrf_token()}}');
                uploadMedia(form, function (mid) {
                    $('input[name=wxapp_thumb_media_id]').val(mid);
                });
            });

            $('.btn-sure').on('click', function () {
                var ids = [];
                var id = $('input[name=openid]').val();
                ids.push(id);
                var msg_type = $('input[name=msg_type]:checked').val();
                var content = $('textarea[name=content]').val();

                var media_id = $('input[name=media_id]').val();

                var link_title = $('input[name=link_title]').val();
                var link_description = $('textarea[name=link_description]').val();
                var link_url = $('input[name=link_url]').val();
                var link_thumb_url = $('input[name=link_thumb_url]').val();

                var wxapp_title = $('input[name=wxapp_title]').val();
                var wxapp_pagepath = $('input[name=wxapp_pagepath]').val();
                var wxapp_thumb_media_id = $('input[name=wxapp_thumb_media_id]').val();

                parent.$('input[name=checkboxes]').each(function () {
                    if ($(this).is(':checked')) {
                        ids.push($(this).val());
                    }
                });
                $.post(
                    '{{url("admin/wxappsession/send/msg")}}',
                    {
                        ids: ids.join(','),
                        msg_type: msg_type,
                        content: content,
                        media_id: media_id,
                        link_title: link_title,
                        link_description: link_description,
                        link_url: link_url,
                        link_thumb_url: link_thumb_url,
                        wxapp_title: wxapp_title,
                        wxapp_pagepath: wxapp_pagepath,
                        wxapp_thumb_media_id: wxapp_thumb_media_id,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        if (data.code == 1) {
                            setTimeout(function () {
                                parent.layer.close(index);
                            }, 5000);
                        }
                    }
                );
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });

        function uploadMedia(form, callback) {
            $.ajax({
                url: '{{url("admin/wxappsession/media/upload")}}',
                type: "POST",
                data: form,
                contentType: false,
                processData: false,
                success: function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    if (data.code == 1) {
                        return callback(data.data.media_id);
                    }
                }
            });
        }
    </script>
@endsection
@endsection