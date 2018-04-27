@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 添加编辑分类</div>
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
                    <form name="conf" action="{{url('admin/comcate/'.$cate->id)}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="cat_name" class="form-control" value="{{$cate->cat_name}}"
                                       placeholder="分类名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类别名：</label>
                            <div class="col-sm-4">
                                <input type="text" name="cat_alias_name" class="form-control"
                                       value="{{$cate->cat_alias_name}}"
                                       placeholder="分类别名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">上级分类：</label>
                            <div class="col-sm-8 pre-cate-sel"
                                 style="@if(count($parentCates) > 0)display: block;@endif">
                                @foreach($parentCates as $val)
                                    @if($loop->index != 0)　>　@endif<span>{{$val['cat_name']}}</span>
                                @endforeach
                                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
                                <input type="hidden" name="parent_id" value="{{$cate->parent_id}}">
                            </div>
                            <div class="col-sm-8 pre-cate" style="@if(count($parentCates) > 0)display: none;@endif">
                                <div class="cate-option fl">
                                    <select name="category_name[]" class="form-control select"
                                            onchange="setNextCate(this)">
                                        <option value="0">顶级分类</option>
                                        @foreach($cates as $cat)
                                            <option value="{{$cat->id}}">{{$cat->cat_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">数量单位：</label>
                            <div class="col-sm-3">
                                <input type="text" name="measure_unit" class="form-control"
                                       value="{{$cate->measure_unit}}"
                                       placeholder="数量单位">
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
                            <label class="col-sm-4 control-label">利润率%：</label>
                            <div class="col-sm-2">
                                <input type="text" name="commission_rate" class="form-control"
                                       value="{{$cate->commission_rate}}"
                                       placeholder="利润率">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="is_show" value="1"
                                           @if($cate->is_show == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="is_show" value="0"
                                           @if($cate->is_show == 0) checked @endif> 否
                                </label>
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
                            <label class="col-sm-4 control-label">是否使用顶级分类页样式：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="is_top_style" value="1"
                                           @if($cate->is_top_style == 1) checked @endif> 是
                                </label>
                                <label class="radio-inline fl ml10">
                                    <input type="radio" name="is_top_style" value="0"
                                           @if($cate->is_top_style == 0) checked @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类菜单图标：</label>
                            <div class="col-sm-8">
                                @foreach($icons['cate_icons'] as $key => $val)
                                    <label class="radio-inline fl ml10">
                                        <input type="radio" name="style_icon" value="{{$val}}"
                                               @if($cate->style_icon == $val) checked @endif > <i
                                                class="icon iconfont {{$val}}"> </i>　
                                    </label>
                                @endforeach
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">价格区间个数：</label>
                            <div class="col-sm-4">
                                <input type="text" name="grade" class="form-control" value="{{$cate->grade}}"
                                       placeholder="填0表示不做分级">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">分类的样式表文件：</label>
                            <div class="col-sm-5">
                                <input type="text" name="style" class="form-control" value="{{$cate->style}}"
                                       placeholder="文件存放在themes目录下则输入：themes/style.css">
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
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {

            $('.btn-reset').click(function () {
                $('.pre-cate-sel').hide();
                $('.pre-cate').show();
            })
        });

        function setNextCate(that) {
            var id = $(that).val();
            $('input[name="parent_id"]').val(id);
            var html = '';
            $.post("{{url('admin/comcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                if (data.code == 1) {
                    html = '<div class="cate-option fl"><select name="category_name[]" class="form-control select" onchange="setNextCate(this)"><option value="0">顶级分类</option>';
                    $.each(data.data, function (k, v) {
                        html += '<option value="' + v.id + '">' + v.cat_name + '</option>';
                    })
                    html += '</select></div>';
                    $(that).parent().nextAll().remove();
                    $('.pre-cate').append(html);
                }
            })
        }
    </script>
@endsection
@endsection