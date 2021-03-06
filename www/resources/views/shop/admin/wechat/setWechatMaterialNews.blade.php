@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">
            <a href="{{$backUrl}}" class="s-back">返回</a>
            素材管理 - 图文添加
        </div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>图文素材：分为单图文、多图文素材。支持图片，语音，视频素材。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" enctype="multipart/form-data"
                          action="{{url('admin/wechatmaterial/'.$material->id)}}"
                          method="post" class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <input type="hidden" name="type" value="news">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="title" class="form-control"
                                       value="{{$material->title}}" placeholder="标题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">作者（选填)：</label>
                            <div class="col-sm-4">
                                <input type="text" name="author" class="form-control"
                                       value="{{$material->author}}" placeholder="作者">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">封面：</label>
                            <div class="col-sm-4">
                                <input type="file" name="image" class="fl">
                                <span class="img-show">
                                        <a href="{{$material->file}}" class="nyroModal">
                                            <i class="glyphicon glyphicon-picture top2"
                                               data-tooltipimg="{{$material->file}}"
                                               ctype="tooltip" title="tooltip"></i>
                                        </a>
                                    </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否显示封面图片：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_show" value="1"
                                           @if($material->is_show == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="is_show" value="0"
                                           @if($material->is_show == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">摘要：</label>
                            <div class="col-sm-4">
                                <textarea name="digest" id="" class="form-control">{{$material->digest}}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">正文：</label>
                            <div class="col-sm-8">
                                <script id="editor" name="content" type="text/plain">{!! $material->content !!}</script>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">原文链接：</label>
                            <div class="col-sm-4">
                                <input type="text" name="link" class="form-control"
                                       value="{{$material->link}}" placeholder="链接前请带上http或者https">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">排序：</label>
                            <div class="col-sm-4">
                                <input type="text" name="sort"
                                       class="form-control fl"
                                       value="{{$material->sort}}"
                                       placeholder="排序">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-3 control-label">&nbsp;</div>
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
            $('.nyroModal').nyroModal();

            var ue = UE.getEditor('editor', {
                initialFrameHeight: 300,
                scaleEnabled: true
            });
            ue.ready(function () {
                // ue.setHeight(500);
            });
        });
    </script>
@endsection
@endsection