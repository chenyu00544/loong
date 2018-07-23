@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">图片库 - 添加相册</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/gallery')}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><font c class="red">*</font>相册名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="album_name" class="form-control" value=""
                                       placeholder="链接名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上级相册：</label>
                            <input type="hidden" name="parent_album_id" value="0">
                            <div class="col-sm-4 n-wd400 p-gallery">
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
                        <div class="form-group">
                            <label class="col-sm-4 control-label">封面：</label>
                            <div class="col-sm-4 n-wd400">
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
                        <div class="form-group">
                            <label class="col-sm-4 control-label">描述：</label>
                            <div class="col-sm-4">
                                <textarea type="text" name="album_desc" rows="5" class="form-control"
                                          placeholder="描述"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-1">
                                <input type="text" name="sort_order" class="form-control" value="50"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
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
            $('.nyroModal').nyroModal();

            $('.p-gallery').on('change', '.select', function () {
                setNextCate(this)
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