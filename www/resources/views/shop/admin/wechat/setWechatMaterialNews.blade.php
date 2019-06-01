@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 公众号设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>一、配置前先需要申请一个微信服务号，并且通过微信认证。（认证服务号需要注意每年微信官方都需要重新认证，如果认证过期，接口功能将无法使用，具体请登录微信公众号平台了解详情）</li>
                    <li>二、网站域名 需要通过ICP备案并正确解析到空间服务器，临时域名与IP地址无法配置。</li>
                    <li>三、登录 微信公众号平台 ，获取且依次填写好 公众号名称，公众号原始ID，Appid，Appsecret，token值。</li>
                    <li>四、自定义Token值，必须为英文或数字（长度为3-32字符），如 weixintoken，并保持后台与公众号平台填写的一致。</li>
                    <li>五、复制接口地址，填写到微信公众号平台 开发=> 基本配置，服务器配置下的 URL地址，验证提交通过后，并启用。（注意仅支持80端口）</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    @if($wechat)
                        <form name="conf" action="{{url('admin/wechatconfig/'.$ru_id)}}" method="post" class="form-horizontal">
                            {{csrf_field()}}
                            {{method_field('PUT')}}
                            <div class="form-group">
                                <label class="col-sm-4 control-label">公众号名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="name" class="form-control input-sm"
                                           value="{{$wechat->name}}"
                                           placeholder="如：vcvbuy">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">公众号原始id：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="orgid" class="form-control input-sm"
                                           value="{{$wechat->orgid}}"
                                           placeholder="如：gh_845581623321">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">AppID：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="appid" class="form-control input-sm"
                                           value="{{$wechat->appid}}"
                                           placeholder="AppID微信公众号平台获取">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">AppSecret：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="appsecret" class="form-control input-sm"
                                           value="{{$wechat->appsecret}}"
                                           placeholder="AppSecret密钥微信公众号平台获取">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Token：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="token" class="form-control input-sm fl wd-300"
                                           value="{{$wechat->token}}"
                                           placeholder="自定义的Token值">
                                    <a href="javascript:;" class="btn btn-sm btn-primary token mar-left-20"
                                       style="padding: 5px 10px;">生成Token</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">EncodingAESKey：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="encodingaeskey" class="form-control input-sm"
                                           value="{{$wechat->encodingaeskey}}"
                                           placeholder="开发者手动填写或随机生成">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">商城秘钥：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="secret_key"
                                           class="form-control input-sm fl wd-300"
                                           value="{{$wechat->secret_key}}"
                                           placeholder="商城秘钥">
                                    <a href="javascript:;" class="btn btn-sm secret_key btn-primary mar-left-20"
                                       style="padding: 5px 10px;">生成密钥</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">状态：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="1"
                                               @if($wechat->status == 1) checked @endif> 是
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="0"
                                               @if($wechat->status == 0) checked @endif> 否
                                    </label>
                                </div>
                            </div>
                            <div class="form-group has-success">
                                <label class="col-sm-4 control-label">微信URL接口地址：</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control url input-sm wd-550 fl"
                                           value="{{url('mobile/wechat/'.$wechat->secret_key)}}"
                                           placeholder="http://" readonly>
                                    <a href="javascript:;" class="btn btn-sm copy-url btn-primary mar-left-10"
                                       style="padding: 5px 10px;">复制URL</a>
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
                        <form name="conf" action="{{url('admin/wechatconfig')}}" method="post" class="form-horizontal">
                            {{csrf_field()}}

                            <div class="form-group">
                                <label class="col-sm-4 control-label">公众号名称：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="name" class="form-control input-sm"
                                           value=""
                                           placeholder="如：vcvbuy">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">公众号原始id：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="orgid" class="form-control input-sm"
                                           value=""
                                           placeholder="如：gh_845581623321">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">AppID：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="appid" class="form-control input-sm"
                                           value=""
                                           placeholder="AppID微信公众号平台获取">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">AppSecret：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="appsecret" class="form-control input-sm"
                                           value=""
                                           placeholder="AppSecret密钥微信公众号平台获取">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">Token：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="token" class="form-control input-sm fl wd-300"
                                           value=""
                                           placeholder="自定义的Token值">
                                    <a href="javascript:;" class="btn btn-sm token btn-primary mar-left-20"
                                       style="padding: 5px 10px;">生成Token</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">EncodingAESKey：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="encodingaeskey" class="form-control input-sm"
                                           value=""
                                           placeholder="开发者手动填写或随机生成">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">商城秘钥：</label>
                                <div class="col-sm-5">
                                    <input type="text" name="secret_key"
                                           class="form-control input-sm fl wd-300"
                                           value=""
                                           placeholder="商城秘钥">
                                    <a href="javascript:;" class="btn btn-sm secret_key btn-primary mar-left-20"
                                       style="padding: 5px 10px;">生成密钥</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">状态：</label>
                                <div class="col-sm-4 n-wd400">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="1"> 开启
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="status" value="0" checked> 关闭
                                    </label>
                                </div>
                            </div>
                            <div class="form-group has-success">
                                <label class="col-sm-4 control-label">微信URL接口地址：</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control url input-sm wd-550 fl"
                                           value=""
                                           placeholder="http://" readonly>
                                    <a href="javascript:;" class="btn btn-sm btn-primary mar-left-10"
                                       style="padding: 5px 10px;">复制URL</a>
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
            $('.secret_key').click(function () {
                var str = Date.parse(new Date())+'';
                $('input[name=secret_key]').val($.md5(str));
            });
            $('.token').click(function () {
                var str = Date.parse(new Date())+'';
                $('input[name=token]').val($.md5(str));
            });

            // H5 复制粘贴 兼容IE8+，Chrome 45+, Firefox 43+
            var copyUrl = document.querySelector('.copy-url');
            copyUrl.onclick = function() {
                var url = $('.url').val();
                copyTextToClipboard(url);
            };
        });
    </script>
@endsection
@endsection