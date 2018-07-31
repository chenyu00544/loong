@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 添加编辑导航栏</div>
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
                    <form name="conf" action="{{url('admin/navsetup/'.$field->id)}}" class="form-horizontal"
                          method="post">
                        {{csrf_field()}}
                        {{method_field('PUT')}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">系统内容：</label>
                            <div class="col-sm-3">
                                <select name="ctype" class="form-control">
                                    @foreach($navsTop as $nav)
                                        <option value="{{$nav['value']}}"
                                                @if($nav['value'] == $field->ctype) selected @endif>{{$nav['title']}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group  cate" style="@if($field->ctype != 1) display: none; @endif">
                            <label class="col-sm-4 control-label"></label>
                            <div class="col-sm-8 pre-cate-sel" style="@if($field->cid == 0) display: none; @endif">
                                @foreach($parentCates as $val)
                                    @if($loop->index != 0)　>　@endif<span>{{$val['cat_name']}}</span>
                                @endforeach
                                <a href="javascript:;" class="btn btn-warning btn-reset btn-sm">重置</a>
                                <input type="hidden" name="cid" value="{{$field->cid}}">
                            </div>
                            <div class="col-sm-8 pre-cate" style="@if($field->cid != 0) display: none; @endif">
                                <div class="cate-option fl">
                                    <select class="form-control select"
                                            onchange="setNextCate(this)">
                                        <option value="0">顶级分类</option>
                                        @foreach($cates as $cate)
                                            <option value="{{$cate->id}}">{{$cate->cat_name}}</option>
                                        @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">导航名称：</label>
                            <div class="col-sm-3">
                                <input type="text" name="name" class="form-control" value="{{$field->name}}"
                                       placeholder="导航名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">链接地址：</label>
                            <div class="col-sm-4 n-wd400">
                                <input type="text" name="url" class="form-control" value="{{$field->url}}"
                                       placeholder="地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-1">
                                <input type="text" name="vieworder" class="form-control" value="{{$field->vieworder}}"
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="ifshow" value="1"
                                           @if($field->ifshow ==1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="ifshow" value="0"
                                           @if($field->ifshow ==0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否新窗口：</label>
                            <div class="col-sm-4 n-wd400">
                                <label class="radio-inline fl">
                                    <input type="radio" name="opennew" value="1"
                                           @if($field->opennew ==1) checked="true" @endif> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="opennew" value="0"
                                           @if($field->opennew ==0) checked="true" @endif> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">位置：</label>
                            <div class="col-sm-2">
                                <select name="position" class="form-control">
                                    <option value="home_head" @if($field->position == 'home_head') selected @endif>
                                        首页顶部
                                    </option>
                                    <option value="home_nav" @if($field->position == 'home_nav') selected @endif>首页导航
                                    </option>
                                    <option value="bottom" @if($field->position == 'bottom') selected @endif>底部导航
                                    </option>
                                </select>
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
            $('select[name=ctype]').on('change', function () {
                if($(this).val() == 1){
                    $('.cate').show();
                }else{
                    $('.cate').hide();
                }
            });

            $('.btn-reset').click(function () {
                $('.pre-cate-sel').hide();
                $('.pre-cate').show();
                $('input[name="cid"]').val(0);
            })
        });

        function setNextCate(that) {
            var id = $(that).val();
            $('input[name="cid"]').val(id);
            if (id > 0) {
                var html = '';
                $.post("{{url('admin/comcate/getcates/')}}/" + id, {'_token': '{{csrf_token()}}'}, function (data) {
                    if (data.code == 1) {
                        html = '<div class="cate-option fl"><select class="form-control select" onchange="setNextCate(this)"><option value="0">顶级分类</option>';
                        $.each(data.data, function (k, v) {
                            html += '<option value="' + v.id + '">' + v.cat_name + '</option>';
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