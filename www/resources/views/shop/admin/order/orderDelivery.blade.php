@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">发货单管理 - 发货单列表</div>
        <div class="content">
            <div class="tabs mar-top-5">
                <ul class="fl">
                    <li class="@if($seller == 'selfsale') curr @endif fl">
                        <a href="{{url('admin/order/delivery/selfsale')}}">自营</a>
                    </li>
                    <li class="@if($seller == 'seller') curr @endif fl">
                        <a href="{{url('admin/order/delivery/seller')}}">店铺</a>
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
                        <form action="{{url('admin/order/delivery/'.$seller)}}" method="get">
                            {{csrf_field()}}

                            <input type="text" name="keywords" value="{{$search['keywords']}}"
                                   class="form-control input-sm max-wd-190" placeholder="订单编号">
                            <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
                        </form>
                    </div>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input type="checkbox" name="all_list" class="checkbox check-all">
                            </th>
                            <th width="10%">发货单流水号</th>
                            <th width="12%">订单号</th>
                            <th width="10%">商家名称</th>
                            <th width="12%">下单时间</th>
                            <th width="12%">收货人</th>
                            <th width="12%">发货时间</th>
                            <th width="8%">状态</th>
                            <th width="8%">操作人</th>
                            <th width="10%" class="text-center">操作</th>
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
                                        <input type="checkbox" name="checkboxes" value="{{$order->delivery_id}}"
                                               class="checkbox check-all-list fl" id="checkbox_{{$order->delivery_id}}">
                                    </td>
                                    <td>{{$order->delivery_sn}}</td>
                                    <td>{{$order->order_sn}}</td>
                                    <td>{{$order->shop_info->rz_shopName.$order->shop_info->shopNameSuffix}}</td>
                                    <td>{{date('Y-m-d H:i:s', $order->order->pay_time)}}</td>
                                    <td>{{$order->consignee}}</td>
                                    <td>{{date('Y-m-d H:i:s', $order->add_time)}}</td>
                                    <td>@if($order->status == 0) 已发货 @elseif($order->status == 1) 退货 @elseif($order->status == 2) 正常 @endif</td>
                                    <td>{{$order->action_user}}</td>
                                    <td class="text-center">
                                        <a type="button" href="{{url('admin/order/delivery/info/'.$order->delivery_id)}}" class="btn btn-info btn-edit btn-sm">查看</a>
                                        <a type="button" href="javascript:;" class="btn btn-danger btn-del btn-sm" data-id="{{$order->delivery_id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
                    <div class="clearfix bg-color-dray pad-top-4 bt-batch">
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-delete btn-sm mar-all-8"
                               disabled="disabled" data-type="delete">删除</a>
                        </div>
                        <div class="fl">
                            <a type="button" class="btn btn-primary btn-batch btn-sm mar-all-8"
                               disabled="disabled" data-type="batch">批量发货</a>
                        </div>
                    </div>
                    <div class="page_list">
                        {{$orders->links()}}
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
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/order/delivery/del/')}}/" + Id,
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