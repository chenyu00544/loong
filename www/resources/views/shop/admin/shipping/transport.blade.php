@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 地区管理</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>可以针对具体商品设置运费模板。</li>
                    <li>订单运费计算中，该运费总是优先于任何运费。</li>
                    <li>商品选择该运费只有首重，且运费固定，请根据实际需要进行设置</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($typeNav == 'express') curr @endif fl">
                        <a href="{{url('admin/express')}}">快递配置</a>
                    </li>
                    <li class="@if($typeNav == 'transport') curr @endif fl">
                        <a href="{{url('admin/transport')}}">运费管理</a>
                    </li>
                    <li class="@if($typeNav == 'regions') curr @endif fl">
                        <a href="{{url('admin/regions')}}">地区列表</a>
                    </li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div>
                    <a href="{{url('admin/transport/create')}}" class="btn btn-success btn-add btn-sm">　添加　</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-1">编号</th>
                            <th class="col-md-2">模板名称</th>
                            <th class="col-md-2">运费标题</th>
                            <th class="col-md-2">计算方式</th>
                            <th class="col-md-1">更新时间</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @if(count($transports) == 0)
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                        @else
                            @foreach($transports as $transport)
                                <tr>
                                    <td>{{$transport->tid}}</td>
                                    <td>{{$transport->title}}</td>
                                    <td>{{$transport->shipping_title}}</td>
                                    <td>@if($transport->freight_type == 0) 自定义 @else 快递模板 @endif</td>
                                    <td>{{date('Y-m-d H:i:s',$transport->update_time)}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/transport/'.$transport->tid.'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$transport->tid}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                        @endif
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
            $('.nyroModal').nyroModal();

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                var that = this;
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/transport/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code === 1) {
                                $(that).parent().parent().remove();
                                if ($('tbody tr').length === 0) {
                                    $('tbody').html('<tr class=""><td class="no-records" colspan="20">没有找到任何记录</td></tr>');
                                }
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection