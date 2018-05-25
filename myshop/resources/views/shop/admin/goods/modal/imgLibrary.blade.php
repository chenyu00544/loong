@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;padding: 20px;overflow-y: scroll;">
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
        <div class="clearfix">
            <a href="javascript:;" class="btn btn-danger mar-left-10 lib-add fl">添加图库</a>
<<<<<<< HEAD
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


=======
            <a href="javascript:;" class="btn btn-danger mar-left-10 upload-img fl">
                <input type="file" name="img" class="upload-file fl" multiple="multiple">上传图片</a>
        </div>
        <div class="gallery-list ps-container">
            <div class="gallery-album">
                <ul class="ga-images-ul">
                    @foreach($galleryPics as $galleryPic)
                        <li data-url="{{$galleryPic->pic_id}}" class="">
                            <div class="img-container">
                                <img src="{{$galleryPic->pic_image}}">
                            </div>
                            <i class="checked"></i>
                        </li>
                    @endforeach
                </ul>
                <div class="clear"></div>
            </div>
        </div>
        <nav aria-label="Page navigation">
            <ul class="pagination pagination-sm">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
>>>>>>> be30b524dc83549c54f63e2e2aaa4aa516744d4a
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
                    album_id: album_id,
                    page: 1
                }, function (data) {
                    var html = '';
                    $.each(data.data, function (k, v) {
                        html += '<li data-url="' + v.pic_id + '" class="">' +
                            '<div class="img-container">' +
                            '<img src="' + v.pic_image + '">' +
                            '</div>' +
                            '<i class="checked"></i>' +
                            '</li>';
                    });
                    $('.ga-images-ul').html(html);
                    parent.layer.iframeAuto(index);
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
                layer.load();
                var form = new FormData();
                var files = $('input[name=img]')[0].files;
                var album_id = $('input[name=parent_album_id]').val();
                for (var i = 0; i < files.length; i++) {
                    form.append('pic[' + i + ']', files[i]);
                }
                form.append('album_id', album_id);
                form.append('_token', '{{csrf_token()}}');
                if (album_id > 0) {
                    $.ajax({
                        url: "{{url('admin/gallery/upgallerypic')}}",
                        type: "POST",
                        data: form,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            $.each(data, function (k, v) {
                                var html = '';
                                $('.ga-images-ul').prepend(html);
                            });
                            layer.closeAll('loading');
                        }
                    });
                }
            });
        });
    </script>
@endsection
@endsection