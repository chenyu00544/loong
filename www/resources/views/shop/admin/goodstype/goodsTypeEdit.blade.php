@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>商品设置 - 添加类型</div>
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
                    <form name="brand" action="{{url('admin/goodstype/'.$goodsType->cat_id)}}" method="post"
                          class="form-horizontal"
                          enctype="multipart/form-data">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>商品类型名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="cat_name" class="form-control" value="{{$goodsType->cat_name}}"
                                       placeholder="商品类型名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="c_id" value="{{$goodsType->c_id}}">
                            <label class="col-sm-4 control-label">分类：</label>
                            <div class="col-sm-8 pre-cate-sel"
                                 style="@if(count($parentCates) > 0)display: block; @else display: none; @endif">
                                @foreach($parentCates as $val)
                                    @if($loop->index != 0)　>　@endif<span>{{$val['cat_name']}}</span>
                                @endforeach
                                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
                            </div>
                            <div class="col-sm-8 pre-cate" style="@if(count($parentCates) > 0)display: none;@endif">
                                <div class="cate-option fl">
                                    <select class="form-control select" data-parent_id="0">
                                        <option value="0">顶级分类</option>
                                        @foreach($typeCates as $typeCate)
                                            <option value="{{$typeCate->cate_id}}">{{$typeCate->cat_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">属性分组：</label>
                            <div class="col-sm-4 n-wd400">
                                <textarea name="attr_group" class="form-control ww" row="5"
                                          placeholder="每行一个商品属性组。排序也将按照自然顺序排序。"
                                          style="min-height:100px;"></textarea>
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
                $('input[name="c_id"]').val(0);
            });

            $('body').on('change','.select',function () {
                var that = this;
                var id = $(this).val();
                var parent_id = $(this).data('parent_id');
                $('input[name="c_id"]').val(id);
                if (id > 0) {
                    var html = '';
                    $.post("{{url('admin/typecate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                        if (data.code == 1) {
                            html = '<div class="cate-option fl"><select class="form-control select" data-parent_id="' + id + '"><option value="0">顶级分类</option>';
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
                }else {
                    $(this).parent().nextAll().remove();
                    $('input[name="c_id"]').val(parent_id);
                }
            })
        });


    </script>
@endsection
@endsection