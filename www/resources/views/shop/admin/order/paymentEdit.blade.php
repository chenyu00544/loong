@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">订单设置 - 编辑订单</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>添加订单流程为：选择商城已有会员-选择商品加入订单-确认订单金额-填写收货信息-添加配送方式-选择支付方式-添加发票-查看费用信息-完成。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <table class="table table-hover table-condensed">
                        <thead>
                        <tr>
                            <th class="col-md-2">名称</th>
                            <th class="col-md-8">描述</th>
                            <th class="col-md-2">手续费</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($payment as $pay)
                            <tr>
                                <td>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="pay_id" id="pay_id_{{$pay['pay_id']}}"
                                               value="{{$pay['pay_id']}}" style="margin-top: 7px"
                                               data-order_id="{{$order->order_id}}" data-pay_name="{{$pay['pay_name']}}"
                                               @if($order->pay_id == $pay['pay_id']) checked @endif> {{$pay['pay_name']}}
                                    </label>
                                </td>
                                <td>
                                    <div class="wd-750 oh">{!! $pay['pay_desc'] !!}</div>
                                </td>
                                <td>{{$pay['pay_fee']}}</td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                    <div class="text-center">
                        <a type="button" class="btn btn-primary mar-left-20 btn-sure" href="javascript:;">确定</a>
                        <a type="button" class="btn btn-default mar-left-20" href="javascript:history.go(-1);">返回</a>
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
            $('.btn-sure').on('click', function () {
                var pay_id = '';
                var pay_name = '';
                var order_id = '';
                $('input[name=pay_id]').each(function () {
                    if ($(this).is(':checked')) {
                        pay_id = $(this).val();
                        pay_name = $(this).data('pay_name');
                        order_id = $(this).data('order_id');
                    }
                });
                $.post(
                    '{{url("admin/order/change")}}',
                    {
                        id: order_id,
                        type: 'payment',
                        pay_name: pay_name,
                        pay_id: pay_id,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        setTimeout(function () {
                            location.href = "{{url('admin/order/')}}/" + order_id + "/edit";
                        }, 2000);
                    }
                );
            });
        });
    </script>
@endsection
@endsection