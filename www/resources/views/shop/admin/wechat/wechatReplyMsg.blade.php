@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 消息自动回复</div>
        <div class="content">
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($navType == 'subscribe') curr @endif fl">
                        <a href="{{url('admin/wechatreply/subscribe')}}">关注自动回复</a>
                    </li>
                    <li class="@if($navType == 'msg') curr @endif fl">
                        <a href="{{url('admin/wechatreply/msg')}}">消息自动回复</a>
                    </li>
                    <li class="@if($navType == 'keywords') curr @endif fl">
                        <a href="{{url('admin/wechatreply/keywords')}}">关键词自动回复</a>
                    </li>
                </ul>
            </div>
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>自动回复的类型
                        共分三种：关注自动回复、消息自动回复、关键词自动回复。回复内容可以设置为文字，图片，语音，视频。文本消息回复内容可以直接填写，长度限制1024字节（大约200字，含标点以及其他特殊字符），其他素材需要先在素材管理中添加。
                    </li>
                    <li>一、关注自动回复：即用户关注微信公众号自动回复的消息（包含重新关注）。例如：欢迎关注微信公众号！</li>
                    <li>★ 关注自动回复，不支持图文消息素材。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    @foreach($subscribes as $subscribe)
                        @if($subscribe && $loop->index == 0)
                            <form name="conf" action="{{url('admin/wechatreply/replyauto/set')}}" method="post"
                                  class="form-horizontal">
                                {{csrf_field()}}

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <ul class="nav nav-pills" role="tablist">
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-pencil fs-18"
                                                                       title="文字"
                                                                       data-model="text"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-picture fs-18"
                                                                       title="图片"
                                                                       data-model="image"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-volume-up fs-18"
                                                                       data-model="voice" title="语音"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-film fs-18"
                                                                       data-model="video" title="视频"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-list-alt fs-18"
                                                                       title="图文消息" data-model="news"></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                        @if(!empty($subscribe->media))
                                            @if($subscribe->media->type == 'news')
                                                @if(!empty($subscribe->medias))
                                                    @foreach($subscribe->medias as $media)
                                                        <li data-id="{{$media->id}}" class="pad-all-10"
                                                            style="width: 180px;height: 80px;">
                                                            <div class="img-container fl"
                                                                 style="width: 60px;height: 60px">
                                                                <img width="100%" height="100%"
                                                                     src="{{$media->file}}" controls=""/>
                                                            </div>
                                                            <div>
                                                                <h5 class="mar-bt-5 fl" style="">{{$media->title}}</h5>
                                                                <div style="float: left;overflow: hidden;width: 80px;height: 40px;">{!! $media->content !!}</div>
                                                            </div>
                                                            <i class="checked"></i>
                                                        </li>
                                                    @endforeach
                                                @else
                                                    <li data-id="{{$subscribe->media->id}}" class="pad-all-10"
                                                        style="width: 180px;height: 80px;">
                                                        <div class="img-container fl"
                                                             style="width: 60px;height: 60px">
                                                            <img width="100%" height="100%"
                                                                 src="{{$subscribe->media->file}}" controls=""/>
                                                        </div>
                                                        <div>
                                                            <h5 class="mar-bt-5 fl"
                                                                style="">{{$subscribe->media->title}}</h5>
                                                            <div style="float: left;overflow: hidden;width: 80px;height: 40px;">{!! $subscribe->media->content !!}</div>
                                                        </div>
                                                        <i class="checked"></i>
                                                    </li>
                                                @endif
                                            @elseif($subscribe->media->type == 'image')
                                                <li data-id="{{$subscribe->media->id}}" class=""
                                                    style="width: 100px;height: auto;">
                                                    <div class="img-container">
                                                        <img src="{{$subscribe->media->file}}" width="100%"
                                                             height="100%">
                                                    </div>
                                                    <i class="checked"></i>
                                                </li>
                                            @elseif($subscribe->media->type == 'voice')
                                                <li data-id="{{$subscribe->media->id}}" class=""
                                                    style="width: 100px;height: auto">
                                                    <div class="img-container">
                                                        <div class=""
                                                             style="word-break:break-all">{{$subscribe->media->file_name}}</div>
                                                        <video class="goods-video" id="voice" width="98%" height="50%"
                                                               src="{{$subscribe->media->file}}" controls="play">
                                                            <source src="" class="goods-video-js" type="video/mp4">
                                                        </video>
                                                    </div>
                                                    <i class="checked"></i>
                                                </li>
                                            @elseif($subscribe->media->type == 'video')
                                                <li data-id="{{$subscribe->media->id}}" class="" style="width: 150px;">
                                                    <div class="img-container" style="width: 150px;">
                                                        <video class="goods-video" id="video" width="98%" height="98%"
                                                               src="{{$subscribe->media->file}}" controls="">
                                                            <source src="" class="goods-video-js" type="video/mp4">
                                                        </video>
                                                    </div>
                                                    <i class="checked"></i>
                                                </li>
                                            @endif
                                        @else
                                            <textarea type="text" name="content" class="form-control"
                                                      placeholder="如：vcvbuy"
                                                      rows="10">{{$subscribe->content}}</textarea>
                                        @endif
                                    </div>
                                </div>
                                <input type="hidden" name="media_id" value="">
                                <input type="hidden" name="type" value="msg">
                                <input type="hidden" name="id" value="{{$subscribe->id}}">
                                <div class="form-group">
                                    <div class="col-sm-1 control-label"></div>
                                    <div class="">
                                        <input type="submit" value="　保存　" class="btn btn-danger clearfix">
                                    </div>
                                </div>
                            </form>
                        @else
                            <form name="conf" action="{{url('admin/wechatreply/replyauto/set')}}" method="post"
                                  class="form-horizontal">
                                {{csrf_field()}}
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <ul class="nav nav-pills" role="tablist">
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-pencil fs-18"
                                                                       title="文字"
                                                                       data-model="text"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-picture fs-18"
                                                                       title="图片"
                                                                       data-model="image"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-volume-up fs-18"
                                                                       data-model="voice" title="语音"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-film fs-18"
                                                                       data-model="video" title="视频"></a></li>
                                            <li role="presentation"><a href="javascript:;"
                                                                       class="glyphicon glyphicon-list-alt fs-18"
                                                                       title="图文消息" data-model="news"></a></li>
                                        </ul>
                                    </div>
                                    <div class="panel-body">
                                    <textarea type="text" name="content" class="form-control"
                                              placeholder="如：vcvbuy" rows="10"></textarea>
                                    </div>
                                </div>
                                <input type="hidden" name="media_id" value="">
                                <input type="hidden" name="type" value="msg">
                                <input type="hidden" name="id" value="">
                                <div class="form-group">
                                    <div class="col-sm-4 control-label">&nbsp;</div>
                                    <div class="">
                                        <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                    </div>
                                </div>
                            </form>
                        @endif
                    @endforeach
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
            $('.nav-pills a').click(function () {
                var model = $(this).data('model');
                if (model == 'text') {
                    $('.panel-body').html('<textarea type="text" name="content" class="form-control"\n' +
                        '                                              placeholder="" rows="10"></textarea>')
                } else {
                    layer.open({
                        type: 2,
                        area: ['800px', '425px'],
                        fixed: true, //不固定
                        maxmin: true,
                        title: '素材选择',
                        content: ["{{url('admin/wechatmaterial/modal/')}}/" + model],
                        success: function (layero, index) {
                            layer.iframeAuto(index)
                        }
                    });
                }
            });
        });
    </script>
@endsection
@endsection