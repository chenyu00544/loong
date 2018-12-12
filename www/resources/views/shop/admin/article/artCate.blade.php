@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">文章分类 - 文章分类列表</div>
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
                <div class="clearfix">
                    @if($rank[1] != 10)
                        <a href="javascript:history.go(-1)"
                           class="btn btn-default btn-add btn-sm fl">　返回　</a>
                    @endif
                    <a href="{{url('admin/artcate/create')}}"
                       class="btn btn-success btn-add btn-sm fl">　添加文章分类　</a>
                </div>

                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">级别({{$rank[0]}})</th>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-3">文章分类名称</th>
                            <th class="col-sm-1">分类类型</th>
                            <th class="col-sm-2">描述</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-1">显示在导航栏</th>
                            <th class="col-sm-3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($cates) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($cates as $cate)
                            <tr>
                                <td>
                                    <a href="{{url('admin/artcate/'.$cate->cat_id)}}" class="btn btn-primary btn-sm">下一级</a>
                                </td>
                                <td>{{$cate->cat_id}}</td>
                                <td>{{$cate->cat_name}}</td>
                                <td>
                                    @if($cate->cat_type == 1)
                                        普通分类
                                    @elseif($cate->cat_type == 2)
                                        系统分类
                                    @elseif($cate->cat_type == 3)
                                        网店信息
                                    @elseif($cate->cat_type == 4)
                                        帮助分类
                                    @elseif($cate->cat_type == 5)
                                        网店帮助
                                    @endif
                                </td>
                                <td>{{$cate->cat_desc}}</td>
                                <td>
                                    <input class="form-control input-sm chang-cate"
                                           type="text" data-id="{{$cate->cat_id}}"
                                           data-cate="order" value="{{$cate->sort_order}}">
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($cate->show_in_nav) active @endif" data-type="shownav"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$cate->cat_id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a href="{{url('admin/artcate/'.$cate->cat_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a class="btn btn-danger btn-del btn-sm" data-id="{{$cate->cat_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
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

            $('.switch').click(function () {
                var val = 0;
                if ($(this).hasClass('active')) {
                    val = 0;
                    $(this).removeClass('active');
                } else {
                    val = 1;
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();

                $.post(
                    '{{url("admin/artcate/change")}}',
                    {
                        id: id,
                        type: tag,
                        val: val,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {

                    }
                );
            });

            $('.chang-cate').change(function () {

                var data = {
                    id: $(this).data('id'),
                    type: $(this).data('cate'),
                    val: $(this).val(),
                    _token: '{{csrf_token()}}',
                };

                $.post('{{url("admin/artcate/change")}}',
                    data,
                    function (data) {
                    }
                );
            });

            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/artcate/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                $(that).parent().parent().remove();
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection