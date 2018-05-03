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
                    <li>商城相关信息设置，请谨慎填写信息。</li>
                </ul>
            </div>
            <div class="tabs mar-top-20">
                <ul class="fl">
                    <li class="@if($typeNav == 'express') curr @endif fl">
                        <a href="{{url('admin/express')}}">快递配置</a>
                    </li>
                    <li class="@if($typeNav == 'ship') curr @endif fl">
                        <a href="{{url('admin/ship')}}">运费管理</a>
                    </li>
                    <li class="@if($typeNav == 'area') curr @endif fl">
                        <a href="{{url('admin/area')}}">地区列表</a>
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
                                <td>{{$ship['shipping_id']}}</td>
                                <td>{{$ship['shipping_id']}}</td>
                                <td>{{$ship['shipping_id']}}</td>
                                <td>{{$ship['shipping_id']}}</td>
                                <td>{{$ship['shipping_id']}}</td>
                                <td>
                                    <img src="{{url('styles/images/yes.png')}}" class="pointer">
                                </td>
                                <td class="text-center">
                                    <a type="button" href="{{url('admin/attribute/'.$ship['shipping_id'])}}"
                                       class="btn btn-primary btn-examine btn-sm mar-all-5">属性列表</a>
                                    <a type="button" href="{{url('admin/goodstype/'.$ship['shipping_id'].'/edit')}}"
                                       class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                    <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                       data-id="{{$ship['shipping_id']}}">删除</a>
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

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/goodstype/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            if (data.code == 1) {
                                layer.msg(data.msg, {icon: data.code});
                                setTimeout(function () {
                                    location.href = location.href;
                                }, 1000);
                            } else {
                                layer.msg(data.msg, {icon: data.code});
                            }

                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection