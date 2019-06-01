@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">
            <a href="javascript:history.go(-1);" class="s-back">返回</a>
            素材管理 - 上传视频
        </div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>视频素材大小: 最大2MB，格式：MP4</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" enctype="multipart/form-data"
                          action="{{url('admin/wechatmaterial/'.$material->id)}}"
                          method="post" class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <input type="hidden" name="type" value="video">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type="text" name="title" class="form-control" value="{{$material->title}}"
                                       placeholder="标题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">作者（选填)：</label>
                            <div class="col-sm-4">
                                <input type="text" name="author" class="form-control" value="{{$material->author}}"
                                       placeholder="作者">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">视频：</label>
                            <div class="col-sm-4">
                                <input type="file" name="file" multiple="multiple" accept="video/*">
                                <div>
                                    <video class="goods-video mar-left-10" id="goods_video_js" width="200" height="200"
                                           src="{{$material->file}}" controls="">
                                        <source src="" class="goods-video-js" type="video/mp4">
                                    </video>
                                </div>
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
    <script>
        $(function () {

        });
    </script>
@endsection
@endsection