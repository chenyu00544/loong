@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">第三方服务 - 邮件服务器设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>如果您的服务器支持 Mail 函数（具体信息请咨询您的空间提供商）。我们建议您使用系统的 Mail 函数。</li>
                    <li>当您的服务器不支持 Mail 函数的时候您也可以选用 SMTP 作为邮件服务器。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="{{url('admin/email/'.$email->id)}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件服务：</label>
                            <div class="col-sm-5">
                                <label class="radio-inline fl">
                                    <input type="radio" name="mail_service" value="0"
                                           @if($email->mail_service == 0) checked @endif> 采用服务器内置的 Mail 服务
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="mail_service" value="1"
                                           @if($email->mail_service == 1) checked @endif> 采用其他的 SMTP 服务
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件服务器是否要求加密连接(SSL)：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="smtp_ssl" value="0"
                                           @if($email->smtp_ssl == 0) checked @endif> 否
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="smtp_ssl" value="1"
                                           @if($email->smtp_ssl == 1) checked @endif> 是
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">发送邮件服务器地址(SMTP)：</label>
                            <div class="col-sm-4">
                                <input type="text" name="smtp_host" class="form-control"
                                       value="{{$email->smtp_host}}"
                                       placeholder="邮件服务器主机地址。如果本机可以发送邮件则设置为localhost">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">服务器端口：</label>
                            <div class="col-sm-4">
                                <input type="text" name="smtp_port" class="form-control"
                                       value="{{$email->smtp_port}}"
                                       placeholder="25">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件发送帐号：</label>
                            <div class="col-sm-4">
                                <input type="text" name="smtp_user" class="form-control"
                                       value="{{$email->smtp_user}}"
                                       placeholder="发送邮件所需的认证帐号，如果没有就为空着">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">帐号密码：</label>
                            <div class="col-sm-4">
                                <input type="text" name="smtp_pass" class="form-control"
                                       value="{{$email->smtp_pass}}"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件回复地址：</label>
                            <div class="col-sm-4">
                                <input type="text" name="smtp_mail" class="form-control"
                                       value="{{$email->smtp_mail}}"
                                       placeholder="">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">邮件编码：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="mail_charset" value="UTF-8"
                                           @if($email->mail_charset == 'UTF-8') checked @endif> 国际化编码（utf-8）
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="mail_charset" value="GB2312"
                                           @if($email->mail_charset == 'GB2312') checked @endif> 简体中文
                                </label>

                                <label class="radio-inline fl">
                                    <input type="radio" name="mail_charset" value="BIG5"
                                           @if($email->mail_charset == 'BIG5') checked @endif> 繁体中文
                                </label>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">测试邮件地址：</label>
                            <div class="col-sm-5">
                                <input type="text" name="test_mail_address" class="form-control wd-220 fl"
                                       value=""
                                       placeholder="">
                                <a type="button" class="btn btn-danger clearfix mar-left-20 test_mail"
                                   href="javascript:;">发送测试邮件</a>
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
            $('.test_mail').on('click', function () {
                var mail = $('input[name=test_mail_address]').val();
                $.post("{{url('admin/email/test/sendmail')}}", {
                    '_token': '{{csrf_token()}}',
                    'username': '亲，在测试哦~~',
                    'email': mail
                }, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                })
            });
        });
    </script>
@endsection
@endsection