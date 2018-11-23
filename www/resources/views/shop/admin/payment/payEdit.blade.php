@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">支付方式 - 编辑支付方式</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>请谨慎安装支付方式，填写相关信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" action="{{url('admin/pay/'.$payInfo->pay_id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <input type="hidden" name="pay_code" value="{{$payInfo->pay_code}}">
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>支付方式名称：</label>
                            <div class="col-sm-3 n-wd400">
                                <input type="text" class="form-control" name="pay_name" value="{{$payInfo->pay_name}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">支付方式描述：</label>
                            <div class="col-sm-3 n-wd400">
                                <textarea name="pay_desc" class="form-control" cols="30"
                                          rows="5">{{$payInfo->pay_desc}}</textarea>
                            </div>
                        </div>
                        @foreach($payInfo->pay_config as $pay)
                            <div class="form-group">
                                <label class="col-sm-4 control-label">{{$pay['name']}}：</label>
                                <div class="col-sm-3 n-wd400">
                                    {!! $pay['html'] !!}
                                </div>
                                {{--@if($pay['desc'])<div class="notic col-sm-3">{{nl2br($pay['desc'])}}</div>@endif--}}
                            </div>
                        @endforeach
                        <div class="form-group">
                            <label class="col-sm-4 control-label">支付手续费：</label>
                            <div class="col-sm-3 n-wd400">
                                <input type="text" class="form-control" name="pay_fee" value="{{$payInfo->pay_fee}}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否可用：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="enabled" value="1" @if($payInfo->enabled == 1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="enabled" value="0" @if($payInfo->enabled == 0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">货到付款：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cod" value="1" @if($payInfo->is_cod == 1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_cod" value="0" @if($payInfo->is_cod == 0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>在线支付：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_online" value="1" @if($payInfo->is_online == 1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_online" value="0" @if($payInfo->is_online == 0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="item">
                            <div class="label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　保存　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20" href="javascript:history.go(-1)" >返回</a>
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
            $('.nyroModal').nyroModal();

            $('.shop_country').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 1, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_province').html($html);
                    }
                });
            });

            $('.shop_province').change(function () {
                var parent = $(this).val();
                $.post("{{url('api/region/getCountries')}}", {type: 2, parent: parent}, function (data) {
                    if (data.data.length > 0) {
                        $html = '';
                        $.each(data.data, function (k, v) {
                            $html += '<option value="' + v.id + '">' + v.name + '</option>';
                        });
                        $('.shop_city').html($html);
                    }
                });
            })

        });
    </script>
@endsection
@endsection