@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">第三方服务 - 快递鸟配置</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>使用快递鸟打印快递单时需要在此次页面填写配置信息。</li>
                    <li>配置快递鸟API信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="alidayu" action="{{url('admin/interface/kdniao')}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>用户ID：</label>
                            <div class="col-sm-4">
                                <input type="text" name="value[{{$kdniao['kdniao_client_id']->id}}]" class="form-control"
                                       value="{{$kdniao['kdniao_client_id']->value}}"
                                       placeholder="用户ID">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>API key：</label>
                            <div class="col-sm-4">
                                <input type="text" name="value[{{$kdniao['kdniao_appkey']->id}}]" class="form-control"
                                       value="{{$kdniao['kdniao_appkey']->value}}"
                                       placeholder="API key">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">快递鸟账号设置：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$kdniao['kdniao_account_use']->id}}]" value="0"
                                           @if($kdniao['kdniao_account_use']->value == 0) checked @endif> 由网站统一设置
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="value[{{$kdniao['kdniao_account_use']->id}}]" value="1"
                                           @if($kdniao['kdniao_account_use']->value == 1) checked @endif> 商家可自行设置
                                </label>
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

        });
    </script>
@endsection
@endsection