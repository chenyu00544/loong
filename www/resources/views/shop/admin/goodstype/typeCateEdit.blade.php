@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商品设置 - 添加编辑类型分类</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>商店相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="{{url('admin/typecate/'.$typeCate->cate_id)}}" method="post" class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>类型分类名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="cat_name" class="form-control" value="{{$typeCate->cat_name}}"
                                       placeholder="类型分类名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="parent_id" value="{{$typeCate->parent_id}}">
                            <input type="hidden" name="level" value="{{$typeCate->level}}">
                            <label class="col-sm-4 control-label">上级分类：</label>
                            <div class="col-sm-8 pre-cate-sel"
                                 style="@if(count($parentCates) > 0)display: block; @else display: none; @endif">
                                @foreach($parentCates as $val)
                                    @if($loop->index != 0)　>　@endif<span>{{$val['cat_name']}}</span>
                                @endforeach
                                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
                            </div>
                            <div class="col-sm-8 pre-cate" style="@if(count($parentCates) > 0)display: none;@endif">
                                <div class="cate-option fl">
                                    <select class="form-control select-cate" data-level="1" data-parent_id="0">
                                        <option value="0">顶级分类</option>
                                        @foreach($typeCates as $cate)
                                            <option value="{{$cate->cate_id}}">{{$cate->cat_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort_order" class="form-control" value="{{$typeCate->sort_order}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger clearfix">
                                <a type="button" class="btn btn-default clearfix mar-left-20" href="javascript:history.go(-1)">返回</a>
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
            $('.btn-reset').on('click', function () {
                $('.pre-cate-sel').hide();
                $('.pre-cate').show();
                $('input[name="parent_id"]').val(0);
                $('input[name="level"]').val(1);
            })

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
            })
        });
    </script>
@endsection
@endsection