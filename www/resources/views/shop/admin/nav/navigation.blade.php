@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 自定义导航栏</div>
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
                <div>
                    <a href="{{url('admin/navsetup/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">名称</th>
                            <th class="col-sm-2">是否显示</th>
                            <th class="col-sm-2">是否新窗口</th>
                            <th class="col-sm-1">排序</th>
                            <th class="col-sm-2">位置</th>
                            <th class="col-sm-4">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($navs) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($navs as $nav)
                            <tr>
                                <th>{{$nav->name}}</th>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($nav->ifshow) active @endif" data-type="ifshow"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$nav->id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="switch-wrap clearfix">
                                        <div class="switch @if($nav->opennew) active @endif" data-type="opennew"
                                             title="是">
                                            <div class="circle"></div>
                                            <input type="hidden" value="{{$nav->id}}">
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <input class="form-control input-sm chang-order" type="text" data-id="{{$nav->id}}"
                                           name="ord[]"
                                           value="{{$nav->vieworder}}">
                                </td>
                                <td>{{$nav->position}}</td>
                                <td>
                                    <a type="button" href="{{url('admin/navsetup/'.$nav->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$nav->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$navs->links()}}
                    </div>
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
                    val = 0
                    $(this).removeClass('active');
                } else {
                    val = 1
                    $(this).addClass('active');
                }

                var tag = $(this).data('type');
                var id = $(this).children('input').val();
                $.post(
                    '{{url("admin/navsetup/show/or/view")}}',
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

            $('.chang-order').change(function () {
                $.post(
                    '{{url("admin/navsetup/change/order")}}',
                    {
                        id: $(this).data('id'),
                        order: $(this).val(),
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.open({
                            title: '提示',
                            content: data.msg,
                            icon: data.code,
                            success: function (layero, index) {

                            }
                        });
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
                        "{{url('admin/navsetup/')}}/" + Id,
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