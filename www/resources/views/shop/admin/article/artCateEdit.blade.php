@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>文章分类 - 编辑文章分类</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>文章分类相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/artcate/'.$cate->cat_id)}}" method="post"
                          class="form-horizontal" enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}

                        <div class="form-group">
                            <label class="col-sm-4 control-label">文章分类名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="cat_name" class="form-control" value="{{$cate->cat_name}}"
                                       placeholder="分类名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类类型：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="cat_type">
                                    <option value="1" @if($cate->cat_type == 1) selected @endif>普通分类</option>
                                    <option value="2" @if($cate->cat_type == 2) selected @endif>系统分类</option>
                                    <option value="3" @if($cate->cat_type == 3) selected @endif>网店信息</option>
                                    <option value="4" @if($cate->cat_type == 4) selected @endif>帮助分类</option>
                                    <option value="5" @if($cate->cat_type == 5) selected @endif>网店帮助</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上级分类：</label>
                            <div class="col-sm-8 pre-cate-sel"
                                 style="@if(count($parentCates) > 0)display: block; @else display: none; @endif">
                                @foreach($parentCates as $val)
                                    @if($loop->index != 0)　>　@endif<span>{{$val['cat_name']}}</span>
                                @endforeach
                                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
                                <input type="hidden" name="parent_id" value="{{$cate->parent_id}}">
                            </div>
                            <div class="col-sm-8 pre-cate" style="@if(count($parentCates) > 0)display: none;@endif">
                                <div class="cate-option fl">
                                    <select class="form-control select"
                                            onchange="setNextCate(this)" data-parent="0">
                                        <option value="0">顶级分类</option>
                                        @foreach($cates as $cat)
                                            <option value="{{$cat->cat_id}}">{{$cat->cat_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-3">
                                <input type="text" name="sort_order" class="form-control" value="{{$cate->sort_order}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示在导航栏：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="show_in_nav" value="1"
                                           @if($cate->show_in_nav == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="show_in_nav" value="0"
                                           @if($cate->show_in_nav == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">关键字：</label>
                            <div class="col-sm-5">
                                <input type="text" name="keywords" class="form-control" value="{{$cate->keywords}}"
                                       placeholder="关键字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类描述：</label>
                            <div class="col-sm-5">
                                <textarea type="text" name="cat_desc" rows="5" class="form-control"
                                          placeholder="分类描述">{{$cate->cat_desc}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <input type="submit" value="　确定　" class="btn btn-danger">
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
            $('.btn-reset').click(function () {
                $('.pre-cate-sel').hide();
                $('.pre-cate').show();
                $('input[name="c_id"]').val(0);
            });
        });

        function setNextCate(that) {
            var id = $(that).val();
            var parent = $(that).data('parent');
            $('input[name="parent_id"]').val(id);
            if (id > 0 && parent == 0) {
                var html = '';
                $.post("{{url('admin/artcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<div class="cate-option fl"><select class="form-control select" onchange="setNextCate(this)"><option value="0">顶级分类</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.cat_id + '">' + v.cat_name + '</option>';
                        })
                        html += '</select></div>';
                        $(that).parent().nextAll().remove();
                        $('.pre-cate').append(html);
                    } else {
                        $(that).parent().nextAll().remove();
                    }
                })
            }
        }
    </script>
@endsection
@endsection