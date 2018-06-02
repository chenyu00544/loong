@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 地区管理</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>支持快递鸟打印的快递，部分还需要申请帐号才能正常使用，请按照如下提示进行设置。</li>
                    <li><font class="red">无需申请帐号：</font>EMS、邮政快递包裹、顺丰、宅急送</li>
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
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-1">配送方式名称</th>
                            <th class="col-md-3">网址(仅供参考)</th>
                            <th class="col-md-1">保价费用</th>
                            <th class="col-md-1">货到付款</th>
                            <th class="col-md-1">快递鸟打印</th>
                            <th class="col-md-1">默认方式</th>
                            <th class="col-md-1">排序</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($shipping as $ship)
                            <tr>
                                <td>{{$ship['shipping_name']}}</td>
                                <td>{!! $ship['shipping_desc'] !!}</td>
                                <td>{{empty($ship['insure'])?0:$ship['insure']}}%</td>
                                <td>@if(empty($ship['support_cod'])) <img src="{{url('styles/images/no.png')}}" class="pointer"> @else <img src="{{url('styles/images/yes.png')}}" class="pointer"> @endif</td>
                                <td><img src="{{url('styles/images/yes.png')}}" class="pointer"></td>
                                <td><img src="{{url('styles/images/yes.png')}}" class="pointer"></td>
                                <td>{{empty($ship['shipping_order'])?0:$ship['shipping_order']}}</td>
                                <td class="text-center">
                                    @if(empty($ship['shipping_id']))
                                        <a type="button" data-code="{{$ship['outside_code']}}"
                                           class="btn btn-primary btn-install btn-sm mar-all-5">安装</a>
                                    @else
                                        <a type="button" data-id="{{$ship['shipping_id']}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">账号编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$ship['shipping_id']}}">删除</a>
                                    @endif
                                </td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
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

            //添加快递
            $('.btn-install').on('click', function () {
                var code = $(this).data('code');
                $.post(
                    "{{url('admin/express/install')}}/" + code,
                    {'_token': '{{csrf_token()}}'},
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        if (data.code == 1) {
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    }
                );
            });

            //编辑快递
            $('.btn-edit').on('click', function () {
                var id = $(this).data('id');
                layer.open({
                    type: 2,
                    area: ['500px', '380px'],
                    fixed: true, //固定
                    maxmin: true,
                    title: '编辑',
                    content: ["{{url('admin/express')}}/" + id + "/edit", 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/express/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                setTimeout(function () {
                                    location.href = location.href;
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