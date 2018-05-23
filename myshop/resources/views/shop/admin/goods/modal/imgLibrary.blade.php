@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;padding: 20px;">
    <div class="content-wrap">
        <input type="hidden" name="parent_album_id" value="0">
        <div class="p-gallery">
            <div class="gallery-option fl">
                <select class="form-control select" data-parent="0">
                    <option value="0">请选择</option>
                    @foreach($gallerys as $gallery)
                        <option value="{{$gallery->album_id}}">{{$gallery->album_name}}</option>
                    @endforeach
                </select>
            </div>
        </div>
        <div>
            <a href="javascript:;" class="btn btn-danger mar-left-10 lib-add fl">添加图库</a>
            <a href="javascript:;" class="btn btn-danger mar-left-10 upload-img fl">上传图片</a>
        </div>

        @foreach($galleryPics as $galleryPic)

        @endforeach
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        $(function () {
            $('.p-gallery').on('change', '.select', function () {
                setNextAblum(this, '{{csrf_token()}}', "{{url('admin/gallery/getgallerys/')}}/")
            });

            $('.lib-add').on('click', function () {
                parent.layer.open({
                    type: 2,
                    area: ['800px', '500px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '添加相册',
                    content: ["{{url('admin/goods/addgalleryshow')}}"],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            })
        });
    </script>
@endsection
@endsection