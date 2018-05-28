@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #f7f7f7;">
    <div class="warpper clearfix" style="min-width: 830px">
        <div class="fromlist clearfix">
            <div class="main-info">
                <form name="typecate" action="{{url('admin/typecate')}}" method="post" class="form-horizontal"
                      enctype="multipart/form-data">
                    {{csrf_field()}}
                    <div class="form-group">
                        <label class="col-sm-4 control-label"><b>*</b>类型分类名称：</label>
                        <div class="col-sm-3">
                            <input type="text" name="cat_name" class="form-control" value=""
                                   placeholder="类型分类名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="parent_id" value="0">
                        <input type="hidden" name="level" value="1">
                        <label class="col-sm-4 control-label">上级分类：</label>
                        <div class="col-sm-8 pre-cate">
                            <div class="cate-option fl">
                                <select class="form-control select-cate" data-level="1" data-parent_id="0">
                                    <option value="0">顶级分类</option>
                                    @foreach($typeCates as $typeCate)
                                        <option value="{{$typeCate->cate_id}}">{{$typeCate->cat_name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">排序：</label>
                        <div class="col-sm-3">
                            <input type="text" name="sort_order" class="form-control" value="100"
                                   placeholder="排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 control-label">&nbsp;</div>
                        <div class="">
                            <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                            <a type="button" class="btn btn-default clearfix mar-left-20 close-win" href="javascript:;">取消</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        $(function () {
            $("body").on('change', '.select-cate', function () {
                var that = this;
                var id = $(this).val();
                var parent_id = $(this).data('parent_id');
                var level = $(this).data('level');
                $('input[name="parent_id"]').val(id);
                if (id > 0 && parent_id == 0) {
                    $('input[name="level"]').val(level + 1);
                    var html = '';
                    $.post("{{url('admin/typecate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                        if (data.code == 1) {
                            html = '<div class="cate-option fl"><select class="form-control select-cate" data-level="' + data.data[0].level + '" data-parent_id="' + id + '"><option value="0">顶级分类</option>';
                            $.each(data.data, function (k, v) {
                                html += '<option value="' + v.cate_id + '">' + v.cat_name + '</option>';
                            })
                            html += '</select></div>';
                            $(that).parent().nextAll().remove();
                            $('.pre-cate').append(html);
                        } else {
                            $(that).parent().nextAll().remove();
                        }
                    })
                } else {
                    $(that).parent().nextAll().remove();
                    $('input[name="level"]').val(level);
                    $('input[name="parent_id"]').val(parent_id);
                }
            });

            $('.close-win').click(function () {
                parent.layer.close(index);
            })
        });
    </script>
@endsection
@endsection