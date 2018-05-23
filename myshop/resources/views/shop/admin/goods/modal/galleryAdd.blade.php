@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #fff;">
    <div class="">
        <div class="fromlist clearfix">
            <div class="main-info">
                <div class="form-group clearfix">
                    <label class="col-xs-5 control-label text-right"><font c class="red">*</font>相册名称：</label>
                    <div class="col-xs-3">
                        <input type="text" name="album_name" class="form-control" value=""
                               placeholder="相册名称">
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-xs-5 control-label text-right">上级相册：</label>
                    <input type="hidden" name="parent_album_id" value="0">
                    <div class="col-xs-4 n-wd400 p-gallery">
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
                    <label class="col-xs-5 control-label text-right">封面：</label>
                    <div class="col-xs-4 n-wd400">
                        <input type="file" name="album_cover" class="fl">
                        <span class="show">
                            <a href="{{url('styles/images/no_image.png')}}" class="nyroModal">
                                <i class="glyphicon glyphicon-picture top5"
                                   data-tooltipimg="{{url('styles/images/no_image.png')}}" ctype="tooltip"
                                   title="tooltip"></i>
                            </a>
                        </span>
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-xs-5 control-label text-right">描述：</label>
                    <div class="col-xs-4">
                        <textarea type="text" name="album_desc" rows="5" class="form-control"
                                  placeholder="描述"></textarea>
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-xs-5 control-label text-right">排序：</label>
                    <div class="col-xs-2">
                        <input type="text" name="sort_order" class="form-control" value="50"
                               placeholder="排序">
                    </div>
                </div>
                <div class="form-group clearfix">
                    <div class="col-xs-5 control-label">&nbsp;</div>
                    <div class="">
                        <a href="javascript:;" class="btn btn-danger clearfix btn-sure"> 确定 </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);

        $(function () {
            $('.nyroModal').nyroModal();

            $('.p-gallery').on('change', '.select', function () {
                setNextCate(this)
            });

            $('.btn-sure').on('click', function () {
                parent.layer.cloak(index);
            })
        });

        function setNextCate(that) {
            var id = $(that).val();
            var parent = $(that).data('parent');
            $('input[name=parent_album_id]').val(id);
            if (id > 0 && parent == 0) {
                var html = '';
                $.post("{{url('admin/gallery/getgallerys/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.length > 0) {
                        html = '<div class="gallery-option fl mar-left-20"><select class="form-control select" data-parent="1"><option value="0">请选择</option>';
                        $.each(data, function (k, v) {
                            html += '<option value="' + v.album_id + '">' + v.album_name + '</option>';
                        })
                        html += '</select></div>';
                        $(that).parent().nextAll().remove();
                        $('.p-gallery').append(html);
                    } else {
                        $(that).parent().nextAll().remove();
                    }
                })
            } else {
                $(that).parent().nextAll().remove();
            }
        }
    </script>
@endsection
@endsection