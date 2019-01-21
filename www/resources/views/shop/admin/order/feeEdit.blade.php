@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>订单管理 - 订单信息</div>
        <div class="content">
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" action="{{url('admin/order')}}" method="post"
                          class="form-horizontal">
                        <input type="hidden" name="order_id" value="{{$order->order_id}}">

                        <div class="order-step clearfix mar-bt-50 order-total">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>费用信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>商品总金额：</dt>
                                    <dd>¥{{$order->goods_amount}}
                                        <input type="hidden" name="goods_amount" value="{{$order->goods_amount}}">
                                    </dd>
                                    <dt>使用余额：</dt>
                                    <dd>- ¥{{$order->surplus}}
                                        <input type="hidden" name="surplus" value="{{$order->surplus}}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>发票税额：</dt>
                                    <dd>
                                        <span class="fl">+ ¥</span>
                                        <input type="text" name="tax" value="{{$order->tax}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                    <dt>使用积分：</dt>
                                    <dd>
                                        <span class="fl">- ¥</span>
                                        <input type="text" name="integral_money" value="{{$order->integral_money}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>配送费用：</dt>
                                    <dd>
                                        <span class="fl">+ ¥</span>
                                        <input type="text" name="shipping_fee" value="{{$order->shipping_fee}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                    <dt>使用红包：</dt>
                                    <dd>- ¥{{$order->bonus}}
                                        <input type="hidden" name="bonus" value="{{$order->bonus}}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>保价费用：</dt>
                                    <dd>
                                        <span class="fl">+ ¥</span>
                                        <input type="text" name="insure_fee" value="{{$order->insure_fee}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                    <dt>使用优惠券：</dt>
                                    <dd>- ¥{{$order->coupons}}
                                        <input type="hidden" name="coupons" value="{{$order->coupons}}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>支付费用：</dt>
                                    <dd>
                                        <span class="fl">+ ¥</span>
                                        <input type="text" name="pay_fee" value="{{$order->pay_fee}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                    <dt>使用储值卡：</dt>
                                    <dd>- ¥{{$order->integral_money}}
                                        <input type="hidden" name="integral_money" value="{{$order->integral_money}}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>折扣：</dt>
                                    <dd>
                                        <span class="fl">- ¥</span>
                                        <input type="text" name="discount" value="{{$order->discount}}"
                                               class="form-control input-sm wdh60 fl mar-top-5 hg25">
                                    </dd>
                                    <dt>已付款金额</dt>
                                    <dd>- ¥{{$order->money_paid}}
                                        <input type="hidden" name="money_paid" value="{{$order->money_paid}}">
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>订单总金额：</dt>
                                    <dd>
                                        <font class="red">¥{{$order->goods_amount+$order->tax+$order->shipping_fee+$order->insure_fee+$order->pay_fee-$order->discount-$order->surplus-$order->integral_money-$order->bonus-$order->coupons-$order->integral_money}}</font>
                                    </dd>
                                    <dt>应付款金额</dt>
                                    <dd><font class="red">¥{{$order->order_amount}}</font></dd>
                                </dl>
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
                        type: 'fee',
                        fee: data,
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