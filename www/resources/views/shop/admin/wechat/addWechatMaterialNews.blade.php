@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">素材管理 - 图文添加</div>
        <div class="content">
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/wechatconfig/')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="name" class="form-control input-sm" value="" placeholder="标题">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">作者（选填)：</label>
                            <div class="col-sm-4">
                                <input type="text" name="orgid" class="form-control input-sm" value="" placeholder="作者">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">封面：</label>
                            <div class="col-sm-4">
                                <input type="file" name="appid">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示封面图片：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="1"
                                           @if(1 == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="0"
                                           @if(1 == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">摘要：</label>
                            <div class="col-sm-5">
                                <textarea name="" id="" cols="30" rows="10"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">正文：</label>
                            <div class="col-sm-4">
                                <script id="editor" name="content" type="text/plain"></script>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">原文链接：</label>
                            <div class="col-sm-4">
                                <input type="text" name="encodingaeskey" class="form-control input-sm"
                                       value="" placeholder="链接前请带上http或者https">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-5">
                                <input type="text" name="secret_key"
                                       class="form-control input-sm fl wd-300"
                                       value=""
                                       placeholder="排序">
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
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.config.js')}}"></script>
    <script type="text/javascript" src="{{url('styles/plugin/ueditor/ueditor.all.min.js')}}"></script>
    <script>
        $(function () {
            var ue = UE.getEditor('editor', {
                initialFrameHeight: 500,
                scaleEnabled: true
            });
            ue.ready(function () {
                // ue.setHeight(500);
            });
        });
    </script>
@endsection
@endsection