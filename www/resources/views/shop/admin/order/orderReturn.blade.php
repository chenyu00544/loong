@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">退货单管理 - 退货单列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/order/return/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/order/return/seller')}}">店铺</a>
                    </li>
                </ul>
            </div>
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
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <div class="fr wd250">
                        <form action="{{url('admin/order/return/'.$seller)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="订单编号">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr lh22" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="17%">编号</th>
                            <th width="8%">商家名称</th>
                            <th width="5%">类型</th>
                            <th width="12%">申请信息</th>
                            <th width="6%">应退金额</th>
                            <th width="6%">实退金额</th>
                            <th width="5%">数量</th>
                            <th width="20%">收货人</th>
                            <th width="7%">退款方式</th>
                            <th width="9%">订单状态</th>
                            <th width="5%">操作</th>
                        </tr>
                        </thead>
                        @if($orders->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($orders as $order)
                                <tr class="">
                                    <td>
                                        <div>流水号：{{$order->return_sn}}</div>
                                        <div>订单号：{{$order->order_sn}}</div>
                                    </td>
                                    <td>绿联专卖店</td>
                                    <td>@if($order->return_type == 1) 退货 @elseif($order->return_type == 2)
                                            换货 @elseif($order->return_type == 3) 退款 @endif</td>
                                    <td>
                                        <div>申请人：</div>
                                        <div>申请时间：{{date('Y-m-d H:i:s', $order->apply_time)}}</div>
                                    </td>
                                    <td>¥{{$order->should_return}}</td>
                                    <td>¥{{$order->actual_return}}</td>
                                    <td>{{$order->return_number}}</td>
                                    <td>
                                        <div>{{$order->addressee}} [TEL: {{$order->phone}}]</div>
                                        <div>{{$order->province}} {{$order->city}} {{$order->district}} {{$order->address}}</div>
                                    </td>
                                    <td>商家自选</td>
                                    <td>@if($order->return_status == 0) 由用户寄回 @elseif($order->return_status == 2)
                                            收到商品 @elseif($order->return_status == 4) 完成 @endif -
                                        <b>@if($order->refound_status == 0) 未退款 @elseif($order->refound_status == 1)
                                                已换货 @elseif($order->refound_status == 2)
                                                已维修 @elseif($order->refound_status == 3)
                                                未换货 @elseif($order->refound_status == 4)
                                                未维修 @elseif($order->refound_status == 5) 已退款 @endif</b></td>
                                    <td>
                                        <a type="button" href="{{url('admin/order/return/info/'.$order->ret_id)}}"
                                           class="btn btn-info btn-edit btn-sm">查看</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="page_list">
                        {{$orders->links()}}
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
            //批量修改
            $('.bt-batch').on('click', 'a', function () {
                var order_ids = [];
                $(".check-all-list").each(function () {
                    if ($(this).is(':checked')) {
                        order_ids.push($(this).val());
                    }
                });

                if (order_ids.length == 0) {
                    return;
                }

                var select_type = $(this).data('type');
                if (select_type == 'print') {
                    return;
                } else if (select_type == 'print_shipping') {
                    return;
                }
                if (order_ids.length > 0) {
                    $.post(
                        '{{url("admin/order/changes")}}',
                        {
                            ids: order_ids,
                            type: select_type,
                            _token: '{{csrf_token()}}'
                        },
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        }
                    );
                }
            });

            //全选
            $('input[name=all_list]').click(function () {
                var flage = $(this).is(':checked');
                $(".check-all-list").each(function () {
                    $(this).prop("checked", flage);
                    if (flage) {
                        $('.bt-batch').find('a').removeAttr('disabled');
                        $(this).parent().parent().parent().parent().addClass('current');
                        $(this).parent().parent().parent().parent().parent().addClass('current');
                    } else {
                        $('.bt-batch').find('a').attr('disabled', true);
                        $(this).parent().parent().parent().parent().removeClass('current');
                        $(this).parent().parent().parent().parent().parent().removeClass('current');
                    }
                });

            });

            $(".check-all-list").on('click', function () {
                if ($(this).is(':checked')) {
                    $('.bt-batch').find('a').removeAttr('disabled');
                    $(this).parent().parent().parent().parent().addClass('current');
                    $(this).parent().parent().parent().parent().parent().addClass('current');
                } else {
                    var bool = true;
                    $(".check-all-list").each(function () {
                        if ($(this).is(':checked')) {
                            bool = false;
                        }
                    });
                    if (bool) {
                        $('.bt-batch').find('a').attr('disabled', true);
                    }
                    $(this).parent().parent().parent().parent().removeClass('current');
                    $(this).parent().parent().parent().parent().parent().removeClass('current');
                }
            });

            //删除
            $('.btn-del').click(function () {
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/order/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            setTimeout(function () {
                                location.href = location.href;
                            }, 1000);
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection