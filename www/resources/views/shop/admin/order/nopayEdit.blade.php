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
                    <li>标识“<font class="red">*</font>”的选项为必填项，其余为选填项</li>
                    <li>添加订单流程为：选择商城已有会员-选择商品加入订单-确认订单金额-填写收货信息-添加配送方式-选择支付方式-添加发票-查看费用信息-完成。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form action="#" method="post" class="form-horizontal"
                          enctype="multipart/form-data">

                        <input type="hidden" name="order_id" value="{{$order->order_id}}">

                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>操作备注：</label>
                            <div class="col-sm-4">
                                <textarea name="action_note" id="" cols="30" rows="5" class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">退款金额：</label>
                            <div class="col-sm-7">
                                <span class="fl line-hg-30">金额：</span>
                                <input name="money_paid" type="text" class="form-control wd-80 fl input-sm" size="10" value="{{$order->money_paid}}" autocomplete="off">
                                <span class="fl line-hg-30">&nbsp;&nbsp;元&nbsp;&nbsp;运费：</span>
                                <input type="text" name="shipping_fee" value="{{$order->shipping_fee}}" id="shippingFee" size="6" class="form-control wd-80 fl input-sm" autocomplete="off">
                                <div class="col-sm-3 n-wd220">
                                    <label class="radio-inline fl">
                                        <input type="radio" name="is_shipping" value="0" checked> 不退运费
                                    </label>
                                    <label class="radio-inline fl">
                                        <input type="radio" name="is_shipping" value="1" > 退运费
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">退款方式：</label>
                            <div class="col-sm-2 wd-220">
                                <label class="radio-inline">
                                    <input type="radio" name="refund" value="1" checked> 退回用户余额
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="refund" value="2" > 生成退款申请
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="refund" value="3" > 不处理，误操作时选择此项
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label"><b>*</b>退款说明：</label>
                            <div class="col-sm-4">
                                <textarea name="refund_note" id="" cols="30" rows="5" class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 control-label">&nbsp;</div>
                            <div class="">
                                <a type="button" class="btn btn-danger btn-sure"> 确定 </a>
                                <a type="button" class="btn btn-default mar-left-20"
                                   href="javascript:history.go(-1)">返回</a>
                            </div>
                        </div>
                    </form>
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
            $('.btn-sure').on('click', function () {
                var order_id = $('input[name=order_id]').val();
                var data = $('.form-horizontal').serializeArray();
                $.post(
                    '{{url("admin/order/change")}}',
                    {
                        id: order_id,
                        type: 'operation',
                        value: 'no_pay',
                        nopay: data,
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