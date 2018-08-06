@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">短信管理 - 大于短信</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>列表页展示所有短信配置模板的信息列表。</li>
                    <li>每条信息可以进行编辑和删除操作。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/alisms/create')}}"
                       class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="col-sm-1">编号</th>
                            <th class="col-sm-1">短信签名</th>
                            <th class="col-sm-1">短信模板</th>
                            <th class="col-sm-3">发送短信的内容</th>
                            <th class="col-sm-2">添加时间</th>
                            <th class="col-sm-1">发送时机</th>
                            <th class="col-sm-2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($alisms) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @endif
                        @foreach($alisms as $ali)
                            <tr>
                                <th>{{$ali->id}}</th>
                                <td>
                                    {{$ali->set_sign}}
                                </td>
                                <td>
                                    {{$ali->temp_id}}
                                </td>
                                <td class="wsn">
                                    {{$ali->temp_content}}
                                </td>
                                <td>
                                    {{date('Y-m-d H:i:s', $ali->add_time)}}
                                </td>
                                <td>
                                    {{$sendTime[$ali->send_time]}}
                                </td>
                                <td>
                                    <a type="button" href="{{url('admin/alisms/'.$ali->id.'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm" data-id="{{$ali->id}}">删除</a>
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="page_list">
                        {{$alisms->links()}}
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
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/alisms/')}}/" + Id,
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