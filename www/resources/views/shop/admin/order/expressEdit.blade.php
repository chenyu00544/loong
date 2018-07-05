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
                            <th class="col-md-2">费用</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($expresses as $express)
                            <tr>
                                <td>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="shipping_id" id="pay_id_{{$express->shipping_id}}"
                                               value="{{$express->shipping_id}}" style="margin-top: 7px"
                                               data-order_id="{{$order->order_id}}" data-shipping_name="{{$express->shipping_name}}"
                                               @if($order->shipping_id == $express->shipping_id) checked @endif> {{$express->shipping_name}}
                                    </label>
                                </td>
                                <td>
                                    <div class="wd-750 oh">{!! $express->shipping_desc !!}</div>
                                </td>
                                <td>{{$express->shipping_fee}}</td>
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
                var shipping_id = '';
                var shipping_name = '';
                var order_id = '';
                $('input[name=shipping_id]').each(function () {
                    if ($(this).is(':checked')) {
                        shipping_id = $(this).val();
                        shipping_name = $(this).data('shipping_name');
                        order_id = $(this).data('order_id');
                    }
                });
                $.post(
                    '{{url("admin/order/change")}}',
                    {
                        id: order_id,
                        type: 'shipping',
                        shipping_name: shipping_name,
                        shipping_id: shipping_id,
                        _token: '{{csrf_token()}}'
                    },
                    function (data) {
                        layer.msg(data.msg, {icon: data.code});
                        setTimeout(function () {
                            history.go(-1);
                        }, 2000);
                    }
                );
            });
        });
    </script>
@endsection
@endsection