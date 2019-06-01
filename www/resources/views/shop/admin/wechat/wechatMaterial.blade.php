@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">微信设置 - 素材管理</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>图文素材：分为单图文、多图文素材。支持图片，语音，视频素材。</li>
                    <li>单图文素材添加好之后，即可将多条单图文素材组合成为一条多图文素材。</li>
                    <li>★ 注意事项：单图文素材如果经过修改，则原先添加好的多图文素材需要重新组合</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($navType == 'news') curr @endif fl">
                        <a href="{{url('admin/wechatmaterial/news')}}">图文素材</a>
                    </li>
                    <li class="@if($navType == 'image') curr @endif fl">
                        <a href="{{url('admin/wechatmaterial/image')}}">图片</a>
                    </li>
                    <li class="@if($navType == 'voice') curr @endif fl">
                        <a href="{{url('admin/wechatmaterial/voice')}}">语音</a>
                    </li>
                    <li class="@if($navType == 'video') curr @endif fl">
                        <a href="{{url('admin/wechatmaterial/video')}}">视频</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    @if($navType == 'news')
                        <div>
                            <div class="clearfix mar-bt-20">
                                <a href="{{url('admin/wechatmaterial/news/add')}}"
                                   class="btn btn-success btn-add btn-sm">添加图文</a>
                            </div>
                            <div class="mar-top-10">
                                <div class="clearfix" style="border-bottom: 1px solid #62b3ff">
                                    @foreach($materials['news'] as $news)
                                        <div class="fl col-sm-6 col-md-4 col-lg-2 mar-bt-5">
                                            <div class="news">
                                                <h4 class="mar-top-5 fs-16">{{$news->title}}</h4>
                                                <p class="mar-top-5">{{date('Y-m-d H:i:s', $news->add_time)}}</p>
                                                <div class="cover">
                                                    <img src="{{$news->file}}">
                                                </div>
                                                <p class="pad-tb-10">{!! $news->content !!}</p>

                                            </div>
                                            <div class="bg-info">
                                                <ul class="nav nav-justified" role="tablist">
                                                    <li role="presentation">
                                                        <a href="{{url('admin/wechatmaterial/news/edit/'.$news->id)}}"
                                                           title="编辑">
                                                            <span class="glyphicon glyphicon-pencil fs-18"></span>
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <span class="glyphicon glyphicon-trash fs-18 btn-del"
                                                                  data-id="{{$news->id}}"></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                            <div class="clearfix mar-bt-20">
                                <a href="{{url('admin/wechatmaterial/newses/add')}}"
                                   class="btn btn-success btn-add btn-sm">添加多图文</a>
                            </div>
                            <div class="mar-top-10">
                                <div class="common-content">

                                </div>
                            </div>
                            <div class="page_list">
                                {{$materials['news']->links()}}
                            </div>
                        </div>
                    @elseif($navType == 'image')
                        <div>
                            <div class="clearfix mar-bt-20">
                                <label for="file"
                                       class="btn btn-success btn-add btn-sm">添加图片
                                    <input data-id="0" id="file" type="file" name="image" class="input-hidden"
                                           data-type="image">
                                </label>
                            </div>
                            <div class="mar-top-10">
                                <div class="clearfix">
                                    @foreach($materials['news'] as $news)
                                        <div class="fl col-sm-6 col-md-4 col-lg-2 mar-bt-5">
                                            <div class="news">
                                                <h4 class="mar-top-5 fs-16">{{$news->title}}</h4>
                                                <p class="mar-top-5">{{date('Y-m-d H:i:s', $news->add_time)}}</p>
                                                <div class="cover">
                                                    <img src="{{$news->file}}">
                                                </div>
                                                <p class="pad-tb-10">{!! $news->content !!}</p>
                                            </div>
                                            <div class="bg-info">
                                                <ul class="nav nav-justified" role="tablist">
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <label for="img_edit_{{$news->id}}">
                                                                <input id="img_edit_{{$news->id}}" type="file" name="image"
                                                                       class="input-hidden" data-type="image" data-id="{{$news->id}}">
                                                                <span class="glyphicon glyphicon-pencil fs-18"></span>
                                                            </label>
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <span class="glyphicon glyphicon-trash fs-18 btn-del"
                                                                  data-id="{{$news->id}}"></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                            <div class="page_list">
                                {{$materials['news']->links()}}
                            </div>
                        </div>
                    @elseif($navType == 'voice')
                        <div>
                            <div class="clearfix mar-bt-20">
                                <label for="file"
                                       class="btn btn-success btn-add btn-sm">添加语音
                                    <input data-id="0" id="file" type="file" name="image" class="input-hidden"
                                           data-type="voice">
                                </label>
                            </div>
                            <div class="mar-top-10">
                                <div class="clearfix">
                                    @foreach($materials['news'] as $news)
                                        <div class="fl col-sm-6 col-md-4 col-lg-2 mar-bt-5">
                                            <div class="news">
                                                <h4 class="mar-top-5 fs-16">{{$news->title}}</h4>
                                                <p class="mar-top-5">{{date('Y-m-d H:i:s', $news->add_time)}}</p>
                                                <div class="cover">
                                                    <video class="goods-video" id="goods_video_js" width="100%" height="100%" src="{{$news->file}}" controls="">
                                                        <source src="" class="goods-video-js" type="video/mp4">
                                                    </video>
                                                </div>
                                                <p class="pad-tb-10">{!! $news->content !!}</p>
                                            </div>
                                            <div class="bg-info">
                                                <ul class="nav nav-justified" role="tablist">
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <label for="img_edit_{{$news->id}}">
                                                                <input id="img_edit_{{$news->id}}" type="file" name="image"
                                                                       class="input-hidden" data-type="voice" data-id="{{$news->id}}">
                                                                <span class="glyphicon glyphicon-pencil fs-18"></span>
                                                            </label>
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <span class="glyphicon glyphicon-trash fs-18 btn-del"
                                                                  data-id="{{$news->id}}"></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                            <div class="page_list">
                                {{$materials['news']->links()}}
                            </div>
                        </div>
                    @elseif($navType == 'video')
                        <div>
                            <div class="clearfix mar-bt-20">
                                <a href="{{url('admin/wechatmaterial/video/add')}}"
                                   class="btn btn-success btn-add btn-sm">添加视频</a>
                            </div>
                            <div class="mar-top-10">
                                <div class="clearfix">
                                    @foreach($materials['news'] as $news)
                                        <div class="fl col-sm-6 col-md-4 col-lg-2 mar-bt-5">
                                            <div class="news">
                                                <h4 class="mar-top-5 fs-16">{{$news->title}}</h4>
                                                <p class="mar-top-5">{{date('Y-m-d H:i:s', $news->add_time)}}</p>
                                                <div class="cover">
                                                    <video class="goods-video" id="goods_video_js" width="100%" height="100%" src="{{$news->file}}" controls="">
                                                        <source src="" class="goods-video-js" type="video/mp4">
                                                    </video>
                                                </div>
                                                <p class="pad-tb-10">{!! $news->content !!}</p>
                                            </div>
                                            <div class="bg-info">
                                                <ul class="nav nav-justified" role="tablist">
                                                    <li role="presentation">
                                                        <a  href="{{url('admin/wechatmaterial/video/edit/'.$news->id)}}"
                                                            title="编辑">
                                                                <span class="glyphicon glyphicon-pencil fs-18"></span>
                                                            </label>
                                                        </a>
                                                    </li>
                                                    <li role="presentation">
                                                        <a href="javascript:;" title="删除">
                                                            <span class="glyphicon glyphicon-trash fs-18 btn-del"
                                                                  data-id="{{$news->id}}"></span>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    @endforeach
                                </div>
                            </div>
                            <div class="page_list">
                                {{$materials['news']->links()}}
                            </div>
                        </div>
                    @endif
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
            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/wechatmaterial/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().parent().parent().parent().remove();
                            }
                        }
                    );
                }, function () {
                });
            });

            //添加图片
            $('input[name=image]').change(function () {
                var form = new FormData();
                var id = $(this).data('id');
                var files = $(this)[0].files;
                var type = $(this).data('type');
                for (var i = 0; i < files.length; i++) {
                    if (i === 0) {
                        form.append('file', files[i]);
                    }
                }
                form.append('id', id);
                form.append('type', type);
                form.append('_token', '{{csrf_token()}}');
                var url = '';
                if (id === 0) {
                    url = "{{url('admin/wechatmaterial')}}";
                } else {
                    url = "{{url('admin/wechatmaterial')}}/" + id;
                    form.append('_method', 'PUT');
                }
                $.ajax({
                    url: url,
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        location.href = location.href;
                    }
                });
            });
        });
    </script>
@endsection
@endsection