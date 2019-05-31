@extends('shop.layouts.index')
@section('content')
    <body style="overflow: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title"><a href="javascript:history.go(-1);" class="s-back">返回</a>订单管理 - 订单信息</div>
        <div class="content">
            <div class="flexilist">
                <div class="stepflex">
                    <dl class="first cur">
                        <dt class="cursor">1</dt>
                        <dd class="s-text">提交订单</dd>
                        @if(!empty($order->add_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->add_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="@if($order->pay_status == 2) cur @endif">
                        <dt class="cursor">2</dt>
                        <dd class="s-text">支付订单</dd>
                        @if($order->pay_status == 0 || $order->pay_status == 1)
                            <dd class="s-time">未付款</dd>
                        @elseif($order->pay_status == 2 && !empty($order->pay_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->pay_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="@if($order->shipping_time > 0) cur @endif">
                        <dt class="cursor">3</dt>
                        <dd class="s-text">商家发货</dd>
                        @if(!empty($order->shipping_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->shipping_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="last @if($order->shipping_status == 2) cur @endif">
                        <dt class="cursor">4</dt>
                        <dd class="s-text">确认收货</dd>
                        @if(!empty($order->confirm_take_time) && $order->shipping_status == 2)
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->confirm_take_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="last @if($order->shipping_status == 9) cur @endif">
                        <dt class="cursor">5</dt>
                        <dd class="s-text">评价</dd>
                        @if(!empty($order->pay_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->pay_time)}}</dd>
                        @endif
                    </dl>
                </div>
            </div>
            <div class="fromlist clearfix">
                <div class="main-info">
                    <form enctype="multipart/form-data" action="{{url('admin/order')}}" method="post"
                          class="form-horizontal">
                        {{csrf_field()}}

                        <input type="hidden" name="order_id" value="{{$order->order_id}}">

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>基本信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>{{$order->order_sn}}</dd>
                                    <dt>订单来源：</dt>
                                    <dd>{{$order->froms}}</dd>
                                </dl>
                                <dl>
                                    <dt>购货人：</dt>
                                    <dd>{{$user->user_name}}</dd>
                                    <dt>订单状态：</dt>
                                    <dd>
                                        @if($order->order_status == 0) 未确认
                                        @elseif($order->order_status == 1) 已确认
                                        @elseif($order->order_status == 2) 已取消
                                        @elseif($order->order_status == 3) 无效
                                        @elseif($order->order_status == 4) 退货
                                        @endif
                                        @if($order->pay_status == 0) 未付款
                                        @elseif($order->pay_status == 2) 已付款
                                        @elseif($order->pay_status == 4) 已退款
                                        @endif
                                        @if($order->shipping_status == 0) 未发货
                                        @elseif($order->shipping_status == 1) 已发货
                                        @elseif($order->shipping_status == 2) 已收货
                                        @elseif($order->shipping_status == 3) 配货中
                                        @elseif($order->shipping_status == 5) 发货中 @endif
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>支付方式：<a href="{{url('admin/order/paymentedit/'.$order->order_id)}}"
                                                class="mar-left-10"><i
                                                    class="glyphicon glyphicon-edit fs-14"></i></a></dt>
                                    <dd>{{$order->pay_name}}</dd>
                                    <dt>配送方式：<a href="{{url('admin/order/expressedit/'.$order->order_id)}}"
                                                class="mar-left-10"><i
                                                    class="glyphicon glyphicon-edit fs-14"></i></a></dt>
                                    <dd>{{$order->shipping_name}}</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s',$order->add_time)}}</dd>
                                    <dt>付款时间：</dt>
                                    <dd>@if($order->pay_time) {{date('Y-m-d H:i:s',$order->pay_time)}} @else
                                            未付款 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>发货时间：</dt>
                                    <dd>@if($order->shipping_time) {{date('Y-m-d H:i:s',$order->shipping_time)}} @else
                                            未发货 @endif</dd>
                                    <dt>发货单号：
                                        <a href="javascript:;" class="mar-left-10">
                                            <i class="glyphicon glyphicon-edit fs-14 invoice_no"></i>
                                        </a>
                                        <input type="hidden" name="invoice_no" value="{{$order->invoice_no}}">
                                    </dt>
                                    <dd>@if($order->invoice_no) {{$order->invoice_no}} @else 未发货 @endif</dd>
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
                                <h3>收货人信息<a href="{{url('admin/order/consigneeedit/'.$order->order_id)}}"
                                            class="mar-left-10"><i class="glyphicon glyphicon-edit fs-16"></i></a>
                                </h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>收货人：</dt>
                                    <dd>{{$order->consignee}}</dd>
                                    <dt>电子邮件：</dt>
                                    <dd>@if($order->email) {{$order->email}} @else 无 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>手机号码：</dt>
                                    <dd>@if($order->mobile) {{$order->mobile}} @else 无 @endif</dd>
                                    <dt>电话号码：</dt>
                                    <dd>@if($order->tel) {{$order->tel}} @else 无 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>送货时间：</dt>
                                    <dd>@if($order->shipping_dateStr) {{$order->shipping_dateStr}} @else 无 @endif</dd>
                                    <dt>地址别名：</dt>
                                    <dd>@if($order->sign_building) {{$order->sign_building}} @else 无 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>收货地址：</dt>
                                    <dd>[{{$province['name']}} {{$city['name']}} {{$district['name']}}
                                        ] {{$order->address}}</dd>
                                    <dt>邮政编码：</dt>
                                    <dd>@if($order->zipcode) {{$order->zipcode}} @else 无 @endif</dd>
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

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>其他信息<a href="{{url('admin/order/otheredit/'.$order->order_id)}}"
                                           class="mar-left-10"><i
                                                class="glyphicon glyphicon-edit fs-16"></i></a></h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>发票抬头：(企业普通发票)：</dt>
                                    <dd>{{$order->inv_payee}}</dd>
                                    <dt>发票内容：</dt>
                                    <dd>@if($order->inv_content) {{$order->inv_content}} @else 无 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>缺货处理：</dt>
                                    <dd>@if($order->how_oos) {{$order->how_oos}} @else 无 @endif</dd>
                                    <dt>识别码：</dt>
                                    <dd>@if($order->tax_id) {{$order->tax_id}} @else 无 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>卖家留言：</dt>
                                    <dd>@if($order->to_buyer) {{$order->to_buyer}} @else 无 @endif</dd>
                                    <dt>买家留言：</dt>
                                    <dd>@if($order->postscript) {{$order->postscript}} @else 无 @endif</dd>
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
                                        <th width="25%">商品名称 [ 品牌 ]</th>
                                        <th width="8%" class="text-center">仓库名称</th>
                                        <th width="7%" class="text-center">货号</th>
                                        <th width="7%" class="text-center">条形码</th>
                                        <th width="7%" class="text-center">货品号</th>
                                        <th width="6%" class="text-center">价格</th>
                                        <th width="5%" class="text-center">赠送积分</th>
                                        <th width="5%" class="text-center">数量</th>
                                        <th width="8%" class="text-center">属性</th>
                                        <th width="5%" class="text-center">库存</th>
                                        <th width="8%" class="text-center">小计</th>
                                        <th width="7%" class="text-center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    @foreach($orderGoodses as $orderGoods)
                                        <tr>
                                            <td class="td-product">
                                                <div>
                                                    <div class="img fl">
                                                        <img width="80"
                                                             src="{{$orderGoods->original_img}}">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="705"
                                                               target="_blank">{{$orderGoods->goods_name}}</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center"></td>
                                            <td class="text-center">{{$orderGoods->goods_sn}}</td>
                                            <td class="text-center"></td>
                                            <td class="text-center">{{$orderGoods->product_sn}}</td>
                                            <td class="text-center">¥{{$orderGoods->goods_price}}</td>
                                            <td class="text-center">-1</td>
                                            <td class="text-center">{{$orderGoods->o_goods_number}}</td>
                                            <td class="text-center">
                                                <div>{{$orderGoods->goods_attr}}</div>
                                            </td>
                                            <td class="text-center">{{$orderGoods->goods_number}}</td>
                                            <td class="text-center">
                                                ¥{{sprintf("%.2f",$orderGoods->o_goods_number * $orderGoods->goods_price)}}</td>
                                            <td class="text-center">
                                                <a type="button"
                                                   href="{{url('admin/order/partship/'.$order->order_id)}}"
                                                   class="btn btn-info btn-edit btn-sm mar-all-5">生成发货单</a>
                                            </td>
                                        </tr>
                                    @endforeach
                                    <input type="hidden" name="ru_id" value="{{$orderGoods->ru_id}}">
                                    <tr>
                                        <td colspan="12">
                                            <div class="order-total fr">
                                                <strong>合计：</strong>
                                                <span class="red fs-18"><em>¥</em>{{$order->goods_amount}}</span>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50 order-total">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>费用信息
                                    <a href="{{url('admin/order/feeedit/'.$order->order_id)}}"
                                       class="mar-left-10">
                                        <i class="glyphicon glyphicon-edit fs-16"></i>
                                    </a>
                                </h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>商品总金额：</dt>
                                    <dd>¥{{$order->goods_amount}}</dd>
                                    <dt>使用余额：</dt>
                                    <dd>- ¥{{$order->surplus}}</dd>
                                </dl>
                                <dl>
                                    <dt>发票税额：</dt>
                                    <dd>+ ¥{{$order->tax}}</dd>
                                    <dt>使用积分：</dt>
                                    <dd>- ¥{{$order->integral_money}}</dd>
                                </dl>
                                <dl>
                                    <dt>配送费用：</dt>
                                    <dd>+ ¥{{$order->shipping_fee}}</dd>
                                    <dt>使用红包：</dt>
                                    <dd>- ¥{{$order->bonus}}</dd>
                                </dl>
                                <dl>
                                    <dt>保价费用：</dt>
                                    <dd>+ ¥{{$order->insure_fee}}</dd>
                                    <dt>使用优惠券：</dt>
                                    <dd>- ¥{{$order->coupons}}</dd>
                                </dl>
                                <dl>
                                    <dt>支付费用：</dt>
                                    <dd>+ ¥{{$order->pay_fee}}</dd>
                                    <dt>使用储值卡：</dt>
                                    <dd>- ¥{{$order->integral_money}}</dd>
                                </dl>
                                <dl>
                                    <dt>折扣：</dt>
                                    <dd>- ¥{{$order->discount}}</dd>
                                    <dt>已付款金额</dt>
                                    <dd>- ¥{{$order->money_paid}}</dd>
                                </dl>
                                <dl>
                                    <dt>订单总金额：</dt>
                                    <dd>
                                        <font class="red">¥{{$order->goods_amount+$order->tax+$order->shipping_fee+$order->insure_fee+$order->pay_fee-$order->discount-$order->integral_money-$order->bonus-$order->coupons-$order->integral_money}}</font>
                                    </dd>
                                    <dt>应付款金额</dt>
                                    <dd><font class="red">¥{{$order->order_amount}}</font></dd>
                                </dl>
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
                                            @if($order->order_status != 2 && $order->order_status != 3)
                                                @if($order->order_status == 0)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="sure">确认</a>
                                                @endif
                                                @if($order->pay_status == 0 || $order->pay_status == 1)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="pay">付款</a>
                                                @endif
                                                @if($order->pay_status == 2 && ($order->shipping_status == 0 ||$order->shipping_status == 3))
                                                    <a href="{{url('admin/order/nopay/'.$order->order_id)}}"
                                                       class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="no_pay">设为未付款</a>
                                                @endif
                                                @if($order->shipping_status == 0)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="prepare">配货</a>
                                                @elseif($order->shipping_status == 1)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="unship">未发货</a>
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="receive">已收货</a>
                                                @elseif($order->shipping_status == 2)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="return">退货</a>
                                                @elseif($order->shipping_status == 3)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="ship">生成发货单</a>
                                                @elseif($order->shipping_status == 5)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="to_delivery">去发货</a>
                                                @endif
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="after_service">售后</a>
                                                @if($order->pay_status == 0)
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="cancel">取消</a>
                                                    <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="invalid">无效</a>
                                                @endif
                                                <a href="javascript:;" class="btn btn-primary btn-sm mar-left-5"
                                                   data-ope="order_print">打印订单</a>
                                            @else
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="delete">移除</a>
                                            @endif
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
    @component('shop.components.copyright',['copyright'=>$copyright])@endcomponent
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