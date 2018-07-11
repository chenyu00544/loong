@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">退货单 - 操作</div>
        <div class="content">
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" action="{{url('admin/order')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <input type="hidden" name="order_id" value="{{$rorder->ret_id}}">

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>基本信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>退货时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s', $order->add_time)}}</dd>
                                    <dt>购货人：</dt>
                                    <dd>{{$user->user_name}}</dd>
                                </dl>
                                <dl>
                                    <dt>流水号：</dt>
                                    <dd>{{$rorder->return_sn}}</dd>
                                    <dt>配送费用：</dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s', $rorder->pay_time)}}</dd>
                                    <dt>订单状态：</dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>{{$order->order_sn}}</dd>
                                    <dt>退换货配送方式：</dt>
                                    <dd>0.00</dd>
                                </dl>
                                <dl>
                                    <dt>退换货申请时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s', $rorder->apply_time)}}</dd>
                                    <dt>退换货发货单号：</dt>
                                    <dd>{{$rorder->back_invoice_no}}</dd>
                                </dl>
                                <dl>
                                    <dt></dt>
                                    <dd></dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>收货人信息</a>
                                </h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>收货人：</dt>
                                    <dd></dd>
                                    <dt>手机号码：</dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt>邮政编码：</dt>
                                    <dd></dd>
                                    <dt>问题描述：</dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt>收货地址：</dt>
                                    <dd></dd>
                                    <dt>买家留言：</dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt></dt>
                                    <dd></dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt></dt>
                                    <dd></dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                                <dl>
                                    <dt></dt>
                                    <dd></dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                            </div>
                        </div>

                        <div class="order-step clearfix">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>商品信息</h3>
                            </div>
                            <div class="order-goods">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th width="30%">商品名称 [ 品牌 ]</th>
                                        <th width="15%" class="text-center">货号</th>
                                        <th width="15%" class="text-center">货品号</th>
                                        <th width="20%" class="text-center">属性</th>
                                        <th width="10%" class="text-center">发货数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    @foreach($orderGoodses as $orderGoods)
                                        <tr>
                                            <td class="td-product">
                                                <div>
                                                    <div class="img fl">
                                                        <img width="80"
                                                             src="">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="705"
                                                               target="_blank"></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                        </tr>
                                    @endforeach
                                    <input type="hidden" name="ru_id" value="">
                                    <tr>
                                        <td colspan="12">
                                            <div class="order-total fr">
                                                <strong>合计：</strong>
                                                <span class="red fs-18"><em>¥</em></span>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="order-step clearfix">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>退货商品</h3>
                            </div>
                            <div class="order-goods">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th width="25%">商品名称 [ 品牌 ]</th>
                                        <th width="8%" class="text-center">货号</th>
                                        <th width="7%" class="text-center">货品号</th>
                                        <th width="7%" class="text-center">属性</th>
                                        <th width="7%" class="text-center">商品价格</th>
                                        <th width="6%" class="text-center">数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    @foreach($returnGoodses as $returnGoods)
                                        <tr>
                                            <td class="td-product">
                                                <div>
                                                    <div class="img fl">
                                                        <img width="80"
                                                             src="">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="705"
                                                               target="_blank"></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                            <td class="text-center"></td>
                                        </tr>
                                    @endforeach
                                    <input type="hidden" name="ru_id" value="">
                                    <tr>
                                        <td colspan="12">
                                            <div class="order-total fr">
                                                <strong>合计：</strong>
                                                <span class="red fs-18"><em>¥</em></span>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>操作信息</h3>
                            </div>
                            <div class="order-operation clearfix mar-auto wdh80">
                                <div class="item mar-top-20">
                                    <div class="step-label fl">操作备注：</div>
                                    <div class="step-value fl">
                                            <textarea class="form-control wd-750" rows="5"
                                                      name="goods_product_tag"></textarea>
                                        <div class="mar-top-20 operation">
                                            @if($order->agree_apply == 0)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="agree_apply">同意申请</a>
                                            @else
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="receive_goods">收到退回商品</a>
                                            @endif
                                            @if($order->agree_apply == 0)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="refuse_apply">拒绝</a>
                                            @endif
                                            <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                               data-ope="refound">去退款</a>
                                            <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                               data-ope="after_service">售后</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
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
            $('.invoice_no').on('click', function () {
                var invoice_no = $('input[name=invoice_no]').val();
                $(this).parent().parent().next().html('<input type="text" name="invoice_no_input" class="form-control input-sm fl mar-top-5 hg25" value="' + invoice_no + '">');
            });

            $('body').on('blur', 'input[name=invoice_no_input]', function () {
                var that = this;
                var invoice_no = $(this).val();
                var order_id = $('input[name=order_id]').val();
                $.post("{{url('admin/order/change')}}", {
                    id: order_id,
                    type: 'invoice_no',
                    invoice_no: invoice_no,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    $(that).parent().html(invoice_no);
                });
            });

            $('input[name=auto_delivery_time]').on('change', function () {
                var auto_delivery_time = $(this).val();
                var order_id = $('input[name=order_id]').val();
                $.post("{{url('admin/order/change')}}", {
                    id: order_id,
                    type: 'auto_delivery_time',
                    auto_delivery_time: auto_delivery_time,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                });
            });

            $('.order-operation').on('click', 'a', function () {
                var ru_id = $('input[name=ru_id]').val();
                var type = $(this).data('ope');
                if (type == 'no_pay' || type == 'to_delivery') {
                    return;
                }
                var order_id = $('input[name=order_id]').val();
                var goods_product_tag = $('input[name=goods_product_tag]').val();
                $.post("{{url('admin/order/change')}}", {
                    id: order_id,
                    type: 'operation',
                    value: type,
                    goods_product_tag: goods_product_tag,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    setTimeout(function () {
                        if (type == 'delete') {
                            if (ru_id > 0) {
                                location.href = "{{url('admin/order/seller')}}";
                            } else {
                                location.href = "{{url('admin/order/')}}";
                            }
                        } else {
                            location.href = "{{url('admin/order/')}}/" + order_id + "/edit";
                        }

                    }, 2000);
                });
            });
        });
    </script>
@endsection
@endsection