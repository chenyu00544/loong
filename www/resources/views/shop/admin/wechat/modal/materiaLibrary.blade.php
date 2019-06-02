@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;padding: 20px;">
    <div class="content-wrap">
        <input type="hidden" name="parent_album_id" value="0">
        <div style="overflow-y: scroll;height: 300px;">
            <div class="gallery-list ps-container">
                <div class="gallery-album">
                    <ul class="ga-images-ul">
                        @if($type == 'image')
                            @foreach($materials as $material)
                                <li data-id="{{$material->id}}" class="" style="width: 100px;height: auto;">
                                    <div class="img-container">
                                        <img src="{{$material->file}}" width="100%" height="100%">
                                    </div>
                                    <i class="checked"></i>
                                </li>
                            @endforeach
                        @elseif($type == 'voice')
                            @foreach($materials as $material)
                                <li data-id="{{$material->id}}" class="" style="width: 100px;height: auto">
                                    <div class="img-container">
                                        <div class="" style="word-break:break-all">{{$material->file_name}}</div>
                                        <video class="goods-video" id="voice" width="98%" height="50%"
                                               src="{{$material->file}}" controls="play">
                                            <source src="" class="goods-video-js" type="video/mp4">
                                        </video>
                                    </div>
                                    <i class="checked"></i>
                                </li>
                            @endforeach
                        @elseif($type == 'video')
                            @foreach($materials as $material)
                                <li data-id="{{$material->id}}" class="" style="width: 150px;">
                                    <div class="img-container" style="width: 150px;">
                                        <video class="goods-video" id="video" width="98%" height="98%"
                                               src="{{$material->file}}" controls="">
                                            <source src="" class="goods-video-js" type="video/mp4">
                                        </video>
                                    </div>
                                    <i class="checked"></i>
                                </li>
                            @endforeach
                        @elseif($type == 'news')
                            @foreach($materials as $material)
                                <li data-id="{{$material->id}}" class="pad-all-10" style="width: 180px;height: 80px;">
                                    <div class="img-container fl" style="width: 60px;height: 60px">
                                        <img width="100%" height="100%"
                                               src="{{$material->file}}" controls="" />
                                    </div>
                                    <div>
                                        <h5 class="mar-bt-5 fl" style="">{{$material->title}}</h5>
                                        <div style="float: left;overflow: hidden;width: 80px;height: 40px;">{!! $material->content !!}</div>
                                    </div>
                                    <i class="checked"></i>
                                </li>
                            @endforeach
                        @endif
                    </ul>
                    <div class="clear"></div>
                </div>
            </div>
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-sm">

                </ul>
            </nav>
        </div>
        <div class="clearfix text-center">
            <a href="javascript:;" class="btn btn-danger mar-left-10 btn-sm btn-sure">确定</a>
            <a href="javascript:;" class="btn btn-default mar-left-10 btn-sm btn-close">取消</a>
        </div>
    </div>
    <style>
        #video::-webkit-media-controls {
            display: none !important;
        }
    </style>
    </body>
@section('script')
    <script>
        var type = "{{$type}}";
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        $(function () {
            $('.ga-images-ul').on('click', 'li', function () {
                if(type == 'news'){
                    if ($(this).hasClass('current')) {
                        $(this).removeClass('current');
                    } else {
                        $(this).addClass('current');
                    }
                }else{
                    $('.ga-images-ul li').removeClass('current');
                    $(this).addClass('current');
                }
            });

            $('.btn-sure').on('click', function () {
                var src = '';
                var ids = [];
                $('.ga-images-ul li.current').each(function () {
                    ids.push($(this).data('id'));
                    src = $(this).find('img').attr('src');
                });
                parent.$('input[name=media_id]').val(ids.join(','));
                parent.$('.panel-body').html($('.ga-images-ul li.current'));
                parent.layer.close(index);
            });

            $('.btn-close').on('click', function () {
                parent.layer.close(index);
            });
        });
    </script>
@endsection
@endsection