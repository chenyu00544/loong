@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">系统设置 - 支付方式</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>该页面展示了所有平台支付方式的相关信息列表。</li>
                    <li>可进行卸载或安装相应的支付方式。</li>
                    <li>安装相应支付方式后，用户购物时便可使用相应的支付方式，请谨慎卸载</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-2">支付方式名称</th>
                            <th class="col-md-5">支付方式描述</th>
                            <th class="col-md-1">费用</th>
                            <th class="col-md-1">排序</th>
                            <th class="col-md-3 text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($payConfig as $pay)
                            <tr>
                                <td>{{$pay['pay_name']}}</td>
                                <td>{!! $pay['pay_desc'] !!}</td>
                                <td>{{$pay['pay_fee']}}</td>
                                <td><input type="text" class="form-control changes" data-id="{{$pay['pay_id']}}"
                                           value="{{$pay['pay_order']}}" @if(empty($pay['install'])) disabled @endif></td>
                                <td class="text-center">
                                    @if(empty($pay['install']))
                                        <a type="button" data-code="{{$pay['pay_code']}}"
                                           class="btn btn-primary btn-install btn-sm mar-all-5">安装</a>
                                    @else
                                        <a type="button" href="{{url('admin/pay/'.$pay['pay_id'].'/edit')}}"
                                           class="btn btn-info btn-edit btn-sm mar-all-5">编辑</a>
                                        <a type="button" class="btn btn-danger btn-del btn-sm mar-all-5"
                                           data-id="{{$pay['pay_id']}}">删除</a>
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
            //编辑
            $('.changes').on('change', function () {
                var id = $(this).data('id');
                var pay_order = $(this).val();
                $.post("{{url('admin/pay/changes')}}", {
                    id: id,
                    pay_order: pay_order,
                    _token: '{{csrf_token()}}'
                }, function () {
                    layer.msg(data.msg, {icon: data.code});
                })
            });

            //安装
            $('.btn-install').on('click', function () {
                var code = $(this).data('code');
                $.post("{{url('admin/pay/install')}}", {code: code, _token: '{{csrf_token()}}'}, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    if (data.code == 1) {
                        setTimeout(function () {
                            location.href = location.href;
                        }, 1000);
                    }
                })
            });

            //删除
            $('.btn-del').on('click', function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/pay/')}}/" + Id,
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