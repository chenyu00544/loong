@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 友情链接列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示所有友情链接信息列表。</li>
                    <li>可点击链接进入相应网页，也可进行编辑或删除友情链接。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/friend/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-3">链接名称</th>
                            <th class="col-sm-3">链接地址</th>
                            <th class="col-sm-2">链接LOGO</th>
                            <th class="col-sm-1">显示顺序</th>
                            <th class="col-sm-4 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($friends as $friend)
                            <tr>
                                <td>
                                    {{$friend->link_name}}
                                </td>
                                <td>
                                    <a href="{{$friend->link_url}}" target="_blank">{{$friend->link_url}}</a>
                                </td>
                                <td>
                                    <span class="img-show fl">
                                        <a href="{{$friend->link_logo}}" target="_blank" class="nyroModal">
                                            <i class="glyphicon glyphicon-picture top5" data-tooltipimg="{{$friend->link_logo}}" ectype="tooltip" data-toggle="tooltip" title="tooltip"></i>
                                        </a>
                                    </span>
                                </td>
                                </td>
                                <td>
                                    <input class="form-control input-sm changes" type="text" data-id="{{$friend->link_id}}"
                                           name="show_order"
                                           value="{{$friend->show_order}}">
                                </td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/friend/'.$friend->link_id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$friend->link_id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$friends->links()}}
                    </div>
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

            $('.nyroModal').nyroModal();

            $('.changes').change(function () {
                $.post(
                    '{{url("admin/friend/changes")}}',
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
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/friend/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 1000);
                            }
                        });
                    // layer.msg('的确很重要', {icon: 1});
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection