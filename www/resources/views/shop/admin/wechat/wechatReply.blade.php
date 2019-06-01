@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 自动回复</div>
        <div class="content">
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($navType == 'subscribe') curr @endif fl">
                        <a href="{{url('admin/wechat/subscribe')}}">关注自动回复</a>
                    </li>
                    <li class="@if($navType == 'autoreply') curr @endif fl">
                        <a href="{{url('admin/wechat/autoreply')}}">消息自动回复</a>
                    </li>
                    <li class="@if($navType == 'keywords') curr @endif fl">
                        <a href="{{url('admin/wechat/recharge')}}">关键词自动回复</a>
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
                    @if($subscribe)
                        <form name="conf" action="{{url('admin/wechatreply/setsubscribe')}}" method="post"
                              class="form-horizontal">
                            {{csrf_field()}}
                            {{method_field('PUT')}}

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <ul class="nav nav-pills" role="tablist">
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-pencil fs-18" title="文字"
                                                                   data-model="text"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-picture fs-18" title="图片"
                                                                   data-model="image"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-volume-up fs-18"
                                                                   data-model="voice" title="语音"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-film fs-18"
                                                                   data-model="video" title="视频"></a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <textarea type="text" name="content" class="form-control"
                                              placeholder="如：vcvbuy" rows="10">{{$subscribe->content}}</textarea>
                                </div>
                            </div>

                            <input type="hidden" name="id" value="{{$subscribe->id}}">
                            <div class="form-group">
                                <div class="col-sm-1 control-label"></div>
                                <div class="">
                                    <input type="submit" value="　保存　" class="btn btn-danger clearfix">
                                </div>
                            </div>
                        </form>
                    @else
                        <form name="conf" action="{{url('admin/wechatreply')}}" method="post" class="form-horizontal">
                            {{csrf_field()}}
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <ul class="nav nav-pills" role="tablist">
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-pencil fs-18" title="文字"
                                                                   data-model="text"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-picture fs-18" title="图片"
                                                                   data-model="image"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-volume-up fs-18"
                                                                   data-model="voice" title="语音"></a></li>
                                        <li role="presentation"><a href="javascript:;"
                                                                   class="glyphicon glyphicon-film fs-18"
                                                                   data-model="video" title="视频"></a></li>
                                    </ul>
                                </div>
                                <div class="panel-body">
                                    <textarea type="text" name="content" class="form-control"
                                              placeholder="如：vcvbuy" rows="10"></textarea>
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
            $('.nav-pills a').click(function () {
                var model = $(this).data('model');
                if (model == 'text') {

                } else {
                    layer.open({
                        type: 2,
                        area: ['800px', '400px'],
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