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
        <div class="clearfix">
            <a href="javascript:;" class="btn btn-danger mar-left-10 lib-add fl">添加图库</a>
            <a href="javascript:;" class="btn btn-danger mar-left-10 upload-img fl">
                <input type="file" name="img" class="upload-file fl" multiple="multiple">上传图片</a>
        </div>
        <div style="overflow-y: scroll;height: 250px;">
            <div class="gallery-list ps-container">
                <div class="gallery-album">
                    <ul class="ga-images-ul">
                        @foreach($galleryPics as $galleryPic)
                            <li data-pic_id="{{$galleryPic->pic_id}}" data-exhibition_url="{{$galleryPic->pic_image}}"
                                data-original_url="{{$galleryPic->pic_file}}" class="">
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

                </ul>
            </nav>
        </div>
        <div class="clearfix text-center">
            <a href="javascript:;" class="btn btn-danger mar-left-10 btn-sm btn-sure">确定</a>
            <a href="javascript:;" class="btn btn-default mar-left-10 btn-sm btn-close">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var type = "{{$type}}";
        var goods_id = "{{$goods_id}}";

        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        $(function () {
            $('.p-gallery').on('change', '.select', function () {
                setNextAblum(this, '{{csrf_token()}}', "{{url('admin/gallery/getgallerys/')}}/");
                getgallerypics(1);
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
                var domain = "{{url('/')}}/";
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
                                html += '<li data-url="' + v.pic_id + '" class="">' +
                                    '<div class="img-container">' +
                                    '<img src="' + domain + v.pic_image + '">' +
                                    '</div>' +
                                    '<i class="checked"></i>' +
                                    '</li>';
                                $('.ga-images-ul').prepend(html);
                            });
                            parent.layer.iframeAuto(index);
                            layer.closeAll('loading');
                        }
                    });
                }
                setTimeout(function () {
                    layer.closeAll('loading');
                }, 10000);
            });

            $('.ga-images-ul').on('click', 'li', function () {
                if (type == 'main') {
                    $('.ga-images-ul li').removeClass('current');
                    $(this).addClass('current');
                } else if (type == 'webdesc' || type == 'slide') {
                    if ($(this).hasClass('current')) {
                        $(this).removeClass('current');
                    } else {
                        $(this).addClass('current');
                    }
                }
            });

            $('.pagination').on('click', '.prev', function () {
                var page = $(this).data('num');
                getgallerypics(page)
            });
            $('.pagination').on('click', '.num-page', function () {
                var page = $(this).data('num');
                getgallerypics(page)
            });
            $('.pagination').on('click', '.next', function () {
                var page = $(this).data('num');
                getgallerypics(page)
            });

            $('.btn-sure').on('click', function () {
                var pic_id = [];
                var pic_exhibition_url = [];
                var pic_original_url = [];
                $('.ga-images-ul li.current').each(function () {
                    pic_id.push($(this).data('pic_id'));
                    pic_exhibition_url.push($(this).data('exhibition_url'));
                    pic_original_url.push($(this).data('original_url'));
                });

                if (type == 'main') {
                    parent.$('input[name=gallery_pic_id]').val(pic_exhibition_url[0]);
                    parent.$('.goods-img-show').attr('src', pic_original_url[0]);
                    $.post("{{url('admin/goods/addgoodsgallery')}}", {
                        '_token': '{{csrf_token()}}',
                        pic_ids: pic_id,
                        goods_id: goods_id
                    }, function (data) {
                        if (data.code == 1) {
                            var html = '';
                            $.each(data.data, function (k, v) {
                                html += '<li id="gallery">' +
                                    '<div class="img">' +
                                    '<img src="' + v.img_original + '" width="160" height="160" id="external_img_url">' +
                                    '</div>' +
                                    '<div class="info">' +
                                    '<span class="zt red">主图</span>' +
                                    '<div class="sort">' +
                                    '<span>排序：</span>' +
                                    '<input type="text" value="50" name="old_img_desc[]"' +
                                    'class="stext form-control max-wd-50 hg25" autocomplete="off" maxlength="3">' +
                                    '<input type="hidden" value="' + v.img_id + '" name="img_id[]">' +
                                    '</div>' +
                                    '<a href="javascript:;" data-imgid="' + v.img_id + '" class="delete_img"><i class="glyphicon glyphicon-trash"></i></a>' +
                                    '</div>' +
                                    '<div class="info">' +
                                    '<input name="external_url" type="text" class="form-control max-wd-190 external_url"' +
                                    ' value="" title="" data-imgid="" placeholder="图片外部链接地址"></div>' +
                                    '</li>';
                            });
                            parent.$('#ul-pics').append(html);
                        }
                        parent.layer.close(index);
                    });
                } else if (type == 'slide') {
                    $.post("{{url('admin/goods/addgoodsgallery')}}", {
                        '_token': '{{csrf_token()}}',
                        pic_ids: pic_id,
                        goods_id: goods_id
                    }, function (data) {
                        if (data.code == 1) {
                            var html = '';
                            $.each(data.data, function (k, v) {
                                html += '<li id="gallery">' +
                                    '<div class="img">' +
                                    '<img src="' + v.img_original + '" width="160" height="160" id="external_img_url">' +
                                    '</div>' +
                                    '<div class="info">' +
                                    '<span class="zt red">主图</span>' +
                                    '<div class="sort">' +
                                    '<span>排序：</span>' +
                                    '<input type="text" value="50" name="old_img_desc[]"' +
                                    'class="stext form-control max-wd-50 hg25" autocomplete="off" maxlength="3">' +
                                    '<input type="hidden" value="' + v.img_id + '" name="img_id[]">' +
                                    '</div>' +
                                    '<a href="javascript:;" data-imgid="' + v.img_id + '" class="delete_img"><i class="glyphicon glyphicon-trash"></i></a>' +
                                    '</div>' +
                                    '<div class="info">' +
                                    '<input name="external_url" type="text" class="form-control max-wd-190 external_url"' +
                                    ' value="" title="" data-imgid="" placeholder="图片外部链接地址"></div>' +
                                    '</li>';
                            });
                            parent.$('#ul-pics').append(html);
                        }
                        parent.layer.close(index);
                    });
                } else if (type == 'webdesc') {
                    var html = '';
                    var imgs = '';
                    $.each(pic_original_url, function (k, v) {
                        imgs += '<img src="' + v + '">';
                        html += '<div class="section s-img clearfix">' +
                            '<div class="img">' +
                            '<img src="' + v + '">' +
                            '</div>' +
                            '<div class="tools">';
                        if (parent.$('.section-warp section').length == 0 && k == 0) {
                            html += '<i class="move-up glyphicon glyphicon-arrow-up disabled"></i>' +
                                '<i class="move-down glyphicon glyphicon-arrow-down"></i>';
                        } else if (k == pic_original_url.length - 1) {
                            html += '<i class="move-up glyphicon glyphicon-arrow-up"></i>' +
                                '<i class="move-down glyphicon glyphicon-arrow-down disabled"></i>';
                        } else {
                            html += '<i class="move-up glyphicon glyphicon-arrow-up"></i>' +
                                '<i class="move-down glyphicon glyphicon-arrow-down"></i>';
                        }
                        html += '<em class="move-remove"><i class="glyphicon glyphicon-remove"></i>删除</em>' +
                            '<div class="cover"></div>' +
                            '</div>' +
                            '</div>';
                    });
                    parent.$('.section-warp').append(html);
                    var desc_mobile = parent.$('input[name=desc_mobile]').val();
                    parent.$('input[name=desc_mobile]').val(desc_mobile + imgs);
                    parent.layer.close(index);
                }
            });

            $('.btn-close').on('click', function () {
                parent.layer.close(index);
            });
        });

        function getgallerypics(page) {
            var album_id = $('input[name=parent_album_id]').val();
            $.post("{{url('admin/gallery/getgallerypics')}}", {
                '_token': '{{csrf_token()}}',
                album_id: album_id,
                page: page
            }, function (data) {
                var html = '';
                $.each(data.data, function (k, v) {
                    html += '<li data-pic_id="' + v.pic_id + '" data-exhibition_url="' + v.pic_image + '" data-original_url="' + v.pic_file + '" class="">' +
                        '<div class="img-container">' +
                        '<img src="' + v.pic_image + '">' +
                        '</div>' +
                        '<i class="checked"></i>' +
                        '</li>';
                });
                $('.ga-images-ul').html(html);
                setPages(data);
                parent.layer.iframeAuto(index);
            })
        }
    </script>
@endsection
@endsection