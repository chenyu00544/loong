@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #fff;padding: 20px;">
    <div class="content-wrap">
        <div class="form-group clearfix">
            <input type="hidden" name="parent_album_id" value="{{$album->album_id}}">
            <label class="col-xs-4 control-label text-right line-hg-30">选择相册：</label>
            <div class="col-xs-6 p-gallery-sel"
                 style="@if(count($album) > 0)display: block; @else display: none; @endif">
                <span>{{$album->album_name}}</span>
                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
            </div>
            <div class="col-xs-6 n-wd400 p-gallery"
                 style="@if(count($album) == 0)display: block; @else display: none; @endif">
                <div class="gallery-option fl">
                    <select class="form-control select" data-parent="0">
                        <option value="0">请选择</option>
                        @foreach($gallerys as $gallery)
                            <option value="{{$gallery->album_id}}">{{$gallery->album_name}}</option>
                        @endforeach
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group clearfix">
            <label class="col-xs-4 control-label text-right line-hg-30">上传文件：</label>
            <div class="col-xs-6 n-wd400">
                <input type="file" name="pic">
            </div>
        </div>

        <div class="weight-goods-name" style="text-align: center;">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
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

            //重置
            $('.btn-reset').on('click', function () {
                $('.p-gallery-sel').hide();
                $('.p-gallery').show();
                $('input[name=parent_album_id]').val(0)
            });

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });

            $('.btn-sure').click(function () {
                var domain = "{{url('/')}}/";
                var form = new FormData();
                form.append('pic', $('input[name=pic]')[0].files[0]);
                form.append('album_id', $('input[name=parent_album_id]').val());
                form.append('_token', '{{csrf_token()}}');
                $.ajax({
                    url: "{{url('admin/gallery/upgallerypic')}}",
                    type: "POST",
                    data: form,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        var html = '<li class="image-wrap fl clearfix pic-id-' + data.pic_id + '">' +
                            '<div class="img-container">' +
                            '<img src="' + domain + data.pic_image + '">' +
                            '</div>' +
                            '<div class="checkbox-item">' +
                            '<input type="checkbox" name="pic-id" value="' + data.pic_id + '" class="ui-checkbox">' +
                            '</div>' +
                            '<div class="img-width" style="display: block;">' + data.pic_spec + '(' + (parseFloat(data.pic_size) / 1024).toFixed(2) + 'k)</div>' +
                            '<div class="img-handle" style="display: none;">' +
                            '<a href="javascript:;" class="t-img" data-pic_id="' + data.pic_id + '"><i class="glyphicon glyphicon-transfer"></i>转移相册</a>' +
                            '<a href="javascript:;" class="del-img" data-pic_id="' + data.pic_id + '" data-pic_image="' + data.pic_image + '" data-pic_thumb="' + data.pic_thumb + '" data-pic_file="' + data.pic_file + '"><i class="glyphicon glyphicon-trash"></i>移除</a>' +
                            '</div>' +
                            '</li>';
                        if (parent.$('.image-item').data('album_id') == data.album_id) {
                            parent.$('.image-item').prepend(html);
                        }
                        parent.layer.close(index);
                    }
                });
            });
        });

    </script>
@endsection
@endsection