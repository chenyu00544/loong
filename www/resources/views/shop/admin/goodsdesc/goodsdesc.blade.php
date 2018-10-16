@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 商品说明列表</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>商品说明，可进行删除和编辑。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/goodsdesc/create')}}" class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-3">ID</th>
                            <th class="col-md-3">标题</th>
                            <th class="col-md-3">说明</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        @if($descs->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($descs as $desc)
                                <tr>
                                    <td>{{$desc->id}}</td>
                                    <td>{{$desc->title}}</td>
                                    <td class="wsn">{{$desc->description}}</td>
                                    <td class="text-center">
                                        <a type="button"
                                           href="{{url('admin/goodsdesc/'.$desc->id.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$desc->id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
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
            //删除
            $('.btn-del').on('click', function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/goodsdesc/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                setTimeout(function () {
                                    $(that).parent().parent().remove();
                                }, 1000);
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection