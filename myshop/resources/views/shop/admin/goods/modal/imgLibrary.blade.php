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
            <a href="javascript:;" class="btn btn-danger mar-left-10 upload-img fl"><input type="file" name="img"
                                                                                           class="upload-file fl">上传图片</a>
        </div>
        <div class="gallery-list ps-container">
            <div class="gallery-album">
                <ul class="ga-images-ul">
                    @foreach($galleryPics as $galleryPic)
                        <li data-url="" class="current">
                            <div class="img-container">
                                <img src="">
                            </div>
                            <i class="checked"></i>
                        </li>
                    @endforeach
                </ul>
                <div class="clear"></div>
            </div>
        </div>


    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        $(function () {
            $('.p-gallery').on('change', '.select', function () {
                setNextAblum(this, '{{csrf_token()}}', "{{url('admin/gallery/getgallerys/')}}/");
                var album_id = $('input[name=parent_album_id]').val();
                $.post("{{url('admin/gallery/getgallerypics')}}", {
                    '_token': '{{csrf_token()}}',
                    album_id: album_id
                }, function (data) {
                    console.log(data);
                })
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
            });

            $('.upload-img').on('change', function () {
                var form = new FormData();
                form.append('pic', $('input[name=img]')[0].files[0]);
                form.append('album_id', $('input[name=parent_album_id]').val());
                form.append('_token', '{{csrf_token()}}');
                $.ajax({
                    url: "{{url('admin/gallery/upgallerypic')}}",
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {

                    }
                });
            });

            $('input[name=parent_album_id]').on('change', function () {
                var album_id = $(this).val();
                console.log(1111);
            });
        });
    </script>
@endsection
@endsection