@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">短信管理 - 短信设置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>短信相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="sms" action="{{url('admin/sms')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">短信类型：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_type']->id}}]" class="sms_type"
                                           value="0"
                                           @if($sms['sms_type']->value == 0) checked @endif> 阿里大于
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_type']->id}}]" class="sms_type"
                                           value="1"
                                           @if($sms['sms_type']->value == 1) checked @endif> 阿里短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">商家的手机号码：</label>
                            <div class="col-sm-4">
                                <input type="text" name="value[{{$sms['sms_shop_mobile']->id}}]" class="form-control"
                                       value="{{$sms['sms_shop_mobile']->value }}"
                                       placeholder="请先注册手机短信服务再填写手机号码">
                            </div>
                        </div>
                        <div class="form-group dayu" style="@if($sms['sms_type']->value != 0) display:none; @endif">
                            <label class="col-sm-4 control-label">阿里大鱼(appKey)：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="value[{{$sms['access_key_id']->id}}]" class="form-control"
                                       value="{{$sms['access_key_id']->value }}"
                                       placeholder="appKey">
                            </div>
                        </div>
                        <div class="form-group dayu" style="@if($sms['sms_type']->value != 0) display:none; @endif">
                            <label class="col-sm-4 control-label">阿里大鱼(secretKey)：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="value[{{$sms['access_key_secret']->id}}]"
                                       class="form-control" value="{{$sms['access_key_secret']->value }}"
                                       placeholder="secretKey">
                            </div>
                        </div>
                        <div class="form-group ali" style="@if($sms['sms_type']->value != 1) display:none; @endif">
                            <label class="col-sm-4 control-label">AccessKeyID：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="value[{{$sms['ali_appkey']->id}}]"
                                       class="form-control" value="{{$sms['ali_appkey']->value }}"
                                       placeholder="AccessKeyID">
                            </div>
                        </div>
                        <div class="form-group ali" style="@if($sms['sms_type']->value != 1) display:none; @endif">
                            <label class="col-sm-4 control-label">AccessKeySecret：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="value[{{$sms['ali_secretkey']->id}}]"
                                       class="form-control" value="{{$sms['ali_secretkey']->value }}"
                                       placeholder="AccessKeySecret">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">客户下订单时是否给商家发短信：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_placed']->id}}]" value="1"
                                           @if($sms['sms_order_placed']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_placed']->id}}]" value="0"
                                           @if($sms['sms_order_placed']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">客户付款时是否给商家发短信：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_payed']->id}}]" value="1"
                                           @if($sms['sms_order_payed']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_payed']->id}}]" value="0"
                                           @if($sms['sms_order_payed']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">商家发货时是否给客户发短信：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_shipped']->id}}]" value="1"
                                           @if($sms['sms_order_shipped']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_order_shipped']->id}}]" value="0"
                                           @if($sms['sms_order_shipped']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">客户注册时是否发送短信验证码：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_signin']->id}}]" value="1"
                                           @if($sms['sms_signin']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_signin']->id}}]" value="0"
                                           @if($sms['sms_signin']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">商品降价时是否发送短信：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_price_notice']->id}}]" value="1"
                                           @if($sms['sms_price_notice']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_price_notice']->id}}]" value="0"
                                           @if($sms['sms_price_notice']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">修改入住商权限时是否发送短信：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_seller_signin']->id}}]" value="1"
                                           @if($sms['sms_seller_signin']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_seller_signin']->id}}]" value="0"
                                           @if($sms['sms_seller_signin']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发送验证码：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_code']->id}}]" value="1"
                                           @if($sms['sms_code']->value == 1) checked @endif> 发短信
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$sms['sms_code']->id}}]" value="0"
                                           @if($sms['sms_code']->value == 0) checked @endif> 不发短信
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-4 n-wd400">
                                <div class="notic">用于单个参数验证码（用户实名验证、商家实名验证等）短信发送</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
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
            $('.sms_type').click(function () {
                if ($(this).val() == 0) {
                    $('.dayu').show();
                    $('.ali').hide();
                }else{
                    $('.dayu').hide();
                    $('.ali').show();
                }
            });
        });
    </script>
@endsection
@endsection