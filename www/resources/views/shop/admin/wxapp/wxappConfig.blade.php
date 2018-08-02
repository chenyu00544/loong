@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 公众号设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>一、配置前先 注册小程序，进行微信认证, 已有微信小程序 立即登录</li>
                    <li>二、登录 微信公众号平台 后，在 设置 - 开发者设置 中，查看到微信小程序的 AppID、Appsecret，并配置填写好域名。（注意不可直接使用微信服务号或订阅号的
                        AppID、AppSecret）
                    </li>
                    <li>三、微信认证后，开通小程序微信支付。开通后，配置小程序微信支付的商户号和密钥。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    @if($wxapp)
                        <form name="conf" action="{{url('admin/wxappconfig/'.$ru_id)}}" method="post"
                              class="form-horizontal">
                            {{csrf_field()}}
                            {{method_field('PUT')}}

                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appname" class="form-control input-sm"
                                           value="{{$wxapp->wx_appname}}"
                                           placeholder="如：vcvbuy">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序AppID：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appid" class="form-control input-sm"
                                           value="{{$wxapp->wx_appid}}"
                                           placeholder="小程序AppID">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序AppSecret：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appsecret" class="form-control input-sm"
                                           value="{{$wxapp->wx_appsecret}}"
                                           placeholder="小程序AppSecret">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序微信支付商户号：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_mch_id" class="form-control input-sm"
                                           value="{{$wxapp->wx_mch_id}}"
                                           placeholder="小程序微信支付商户号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序微信支付密钥：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_mch_key" class="form-control input-sm"
                                           value="{{$wxapp->wx_mch_key}}"
                                           placeholder="小程序微信支付密钥">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Token授权密钥：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="token_secret" class="form-control input-sm fl wd-300"
                                           value="{{$wxapp->token_secret}}"
                                           placeholder="Token授权加密key（32位字符）">
                                    <a href="javascript:;" class="btn btn-sm token_secret btn-primary mar-left-20"
                                       style="padding: 5px 10px;">生成Token</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">状态：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="1"
                                               @if($wxapp->status == 1) checked @endif> 是
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="0"
                                               @if($wxapp->status == 0) checked @endif> 否
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 control-label">&nbsp;</div>
                                <div class="">
                                    <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                </div>
                            </div>
                        </form>
                    @else
                        <form name="conf" action="{{url('admin/wxappconfig')}}" method="post"
                              class="form-horizontal">
                            {{csrf_field()}}

                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appname" class="form-control input-sm" value=""
                                           placeholder="如：vcvbuy">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序AppID：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appid" class="form-control input-sm" value=""
                                           placeholder="小程序AppID">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序AppSecret：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_appsecret" class="form-control input-sm" value=""
                                           placeholder="小程序AppSecret">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序微信支付商户号：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_mch_id" class="form-control input-sm" value=""
                                           placeholder="小程序微信支付商户号">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">小程序微信支付密钥：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="wx_mch_key" class="form-control input-sm" value=""
                                           placeholder="小程序微信支付密钥">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Token授权密钥：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="token_secret" class="form-control input-sm fl wd-300"
                                           value=""
                                           placeholder="Token授权加密key（32位字符）">
                                    <a href="javascript:;" class="btn btn-sm token_secret btn-primary mar-left-20"
                                       style="padding: 5px 10px;">生成Token</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">状态：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="1" checked> 开启
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="0"> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 control-label">&nbsp;</div>
                                <div class="">
                                    <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                </div>
                            </div>
                        </form>
                    @endif
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script type="text/javascript" src="{{url('styles/plugin/jquery/jquery.md5.js')}}"></script>
    <script>
        $(function () {
            $('.token_secret').click(function () {
                var str = Date.parse(new Date()) + '';
                $('input[name=token_secret]').val($.md5(str));
            });
        });
    </script>
@endsection
@endsection