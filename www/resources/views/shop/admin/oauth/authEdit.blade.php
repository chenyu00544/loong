@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>手机设置 - 第三方登录插件</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>第三方登录插件相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/mobileoauth/'.$auth->id)}}" method="post" class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>APP_ID：</label>
                            <div class="col-sm-4">
                                <input type="text" name="appid" class="form-control" value="{{$auth->appid}}"
                                       placeholder="APP_ID">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font> APP_SECRET：</label>
                            <div class="col-sm-3">
                                <input type="text" name="appsecret" class="form-control" value="{{$auth->appsecret}}"
                                       placeholder="APP_SECRET">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font class="red">*</font>排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort" class="form-control" value="{{$auth->sort}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="1" @if($auth->status ==1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="0" @if($auth->status ==0) checked @endif> 否
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