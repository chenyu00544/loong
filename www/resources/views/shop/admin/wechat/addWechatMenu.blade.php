@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="{{url('admin/wechatreply')}}" class="s-back">返回</a>微信设置 - 添加自定义菜单</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>微信自定义菜单最多可添加3个一级菜单、5个二级菜单。</li>
                    <li>微信自定义菜单分为关键词click，网址view两种类型。click是响应关键词指令，view则是直接跳转URL地址（填写绝对路径）。</li>
                    <li>每次修改自定义菜单后，由于微信客户端缓存，需要24小时左右微信客户端才会显示生效。测试时可以尝试重新关注微信公众号，或者清除微信缓存。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form name="conf" action="{{url('admin/wechatreply')}}" method="post" class="form-horizontal">
                        {{csrf_field()}}
                        <div class="form-group">
                            <label class="col-sm-4 control-label">父级菜单：</label>
                            <div class="col-sm-3">
                                <select name="pid" class="form-control">
                                    <option value="0">顶级菜单</option>
                                    @foreach($menus as $m)
                                        <option value="{{$m->id}}">{{$m->name}}</option>
                                    @endforeach
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">菜单名称：</label>
                            <div class="col-sm-4">
                                <input type="text" name="name" class="form-control" value=""
                                       placeholder="菜单名称">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">菜单类型：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="click" checked> click
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="type" value="view"> view
                                </label>
                            </div>
                        </div>

                        <div class="form-group menu-type click">
                            <label class="col-sm-4 control-label">菜单关键词：</label>
                            <div class="col-sm-4">
                                <input type="text" name="key" class="form-control" value=""
                                       placeholder="菜单关键词">
                            </div>
                        </div>

                        <div class="form-group menu-type view" style="display: none;">
                            <label class="col-sm-4 control-label">外链URL：</label>
                            <div class="col-sm-4">
                                <input type="text" name="url" class="form-control" value=""
                                       placeholder="排序">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">是否显示：</label>
                            <div class="col-sm-4">
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="1" checked> 是
                                </label>
                                <label class="radio-inline fl">
                                    <input type="radio" name="status" value="0"> 否
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">排序：</label>
                            <div class="col-sm-4">
                                <input type="text" name="sort" class="form-control" value="1"
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
            $('input[name=type]').on('click', function () {
                $('.menu-type').hide();
                $('.' + $(this).val()).show();
            });
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