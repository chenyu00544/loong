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
                                    <dd>{{$dorder->shipping_fee}}</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s', $rorder->pay_time)}}</dd>
                                    <dt>订单状态：</dt>
                                    <dd>
                                        @if($rorder->return_status == 0)
                                            由用户寄回
                                        @elseif($rorder->return_status == 1)
                                            收到退换货
                                        @elseif($rorder->return_status == 2)
                                            换出商品寄出[分单]
                                        @elseif($rorder->return_status == 3)
                                            换出商品寄出
                                        @elseif($rorder->return_status == 4)
                                            完成
                                        @endif
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>{{$rorder->order_sn}}</dd>
                                    <dt>退换货配送方式：</dt>
                                    <dd>{{$rorder->back_shipping_name}}</dd>
                                </dl>
                                <dl>
                                    <dt>退换货申请时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s', $rorder->apply_time)}}</dd>
                                    <dt>退换货发货单号：</dt>
                                    <dd><input type="text" name="back_invoice_no"
                                               class="form-control input-sm wdh60 mar-top-5 hg25"
                                               value="{{$rorder->back_invoice_no}}"
                                               disabled></dd>
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
                                    <dd>{{$rorder->addressee}}</dd>
                                    <dt>手机号码：</dt>
                                    <dd>{{$rorder->phone}}</dd>
                                </dl>
                                <dl>
                                    <dt>邮政编码：</dt>
                                    <dd>{{$rorder->zipcode}}</dd>
                                    <dt>问题描述：</dt>
                                    <dd>{{$rorder->return_brief}}</dd>
                                </dl>
                                <dl>
                                    <dt>收货地址：</dt>
                                    <dd>[{{$province['name'] .' '. $city['name'] . ' ' . $district['name']}}
                                        ] {{$rorder->address}}</dd>
                                    <dt>买家留言：</dt>
                                    <dd>{{$rorder->remark}}</dd>
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
                                                             src="{{url($orderGoods->goods_thumb)}}">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="{{$orderGoods->goods_id}}"
                                                               target="_blank">{{$orderGoods->goods_name}}
                                                                [{{$orderGoods->brand_name}}]</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center">{{$orderGoods->goods_sn}}</td>
                                            <td class="text-center">{{$orderGoods->product_sn}}</td>
                                            <td class="text-center">{{$orderGoods->goods_attr}}</td>
                                            <td class="text-center">{{$orderGoods->o_goods_number}}</td>
                                        </tr>
                                    @endforeach
                                    </tbody>
                                </table>
                            </div>

                        </div>

                        <div class="order-step clearfix b-b-t-e">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>退换货商品</h3>
                            </div>
                            <div class="order-goods return-order-goods fl">
                                <table class="table table-hover table-condensed">
                                    <thead>
                                    <tr>
                                        <th width="40%">商品名称 [ 品牌 ]</th>
                                        <th width="10%" class="text-center">货号</th>
                                        <th width="10%" class="text-center">货品号</th>
                                        <th width="20%" class="text-center">属性</th>
                                        <th width="12%" class="text-center">商品价格</th>
                                        <th width="8%" class="text-center">数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    @foreach($returnGoodses as $returnGoods)
                                        <tr>
                                            <td class="td-product">
                                                <div>
                                                    <div class="img fl">
                                                        <img width="80"
                                                             src="{{url($returnGoods->goods_thumb)}}">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="{{$returnGoods->goods_id}}"
                                                               target="_blank">{{$returnGoods->goods_name}}
                                                                [{{$returnGoods->brand_name}}]</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center">{{$returnGoods->goods_sn}}</td>
                                            <td class="text-center">{{$returnGoods->product_sn}}</td>
                                            <td class="text-center">{{$returnGoods->goods_attr}}</td>
                                            <td class="text-center">￥{{$returnGoods->refound}}</td>
                                            <td class="text-center">{{$returnGoods->return_number}}</td>
                                        </tr>
                                    @endforeach
                                    </tbody>
                                </table>
                            </div>

                            <div class="return-order-desc fl">
                                <div class="item">
                                    <div class="label">买家退换货原因：</div>
                                    <span class="red"> </span>
                                </div>
                                <div class="item">
                                    <div class="label">商品退款：</div>
                                    <span class="price"><em>¥</em>{{$rorder->should_return}}</span>
                                    <input type="hidden" name="refound_amount" value="{{$rorder->should_return}}">
                                </div>
                                <div class="item">
                                    <div class="label cl">退运费：</div>
                                    <span class="price"><em>¥</em>{{$rorder->return_shipping_fee}}</span>
                                    <input type="hidden" name="return_shipping_fee"
                                           value="{{$rorder->return_shipping_fee}}">
                                </div>

                                <div class="item">
                                    <div class="label cl">合计：</div>
                                    <span class="price"><em>¥</em>{{$rorder->should_return+$rorder->return_shipping_fee}}</span>
                                </div>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>操作信息</h3>
                            </div>
                            <div class="order-operation clearfix mar-auto wdh80">
                                <div class="item mar-top-20">
                                    <div class="step-label fl wd-120 text-right">操作备注：</div>
                                    <div class="step-value fl mar-left-5">
                                            <textarea class="form-control wd-550" rows="5"
                                                      name="goods_product_tag"></textarea>
                                    </div>
                                </div>
                                <div class="item mar-top-20">
                                    <div class="step-label fl wd-120 text-right">当前可执行操作：</div>
                                    <div class="operation mar-left-5">
                                        @if($rorder->return_status != 6)
                                            @if($rorder->agree_apply != 1)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="agree_apply">同意申请</a>
                                            @endif

                                            @if($rorder->return_status < 1 && $rorder->return_status >= 1 && $rorder->agree_apply)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="receive_goods">收到退回商品</a>
                                            @endif

                                            @if($rorder->return_type == 1 || $rorder->return_type == 3 && $rorder->chargeoff_status == 0 && $rorder->agree_apply)
                                                @if($rorder->return_status == 0 )
                                                    <a href="{{url('admin/order/return/refound/'.$rorder->ret_id)}}"
                                                       class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="refound">去退款</a>
                                                @endif
                                            @endif

                                            @if($rorder->return_type != 1)
                                                @if($rorder->return_status < 2 && $rorder->return_status >= 0)
                                                    <a href="javascript:;"
                                                       class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="swapped_out_single">换出商品寄出【分单】</a>
                                                    <a href="javascript:;"
                                                       class="btn btn-danger btn-sm mar-left-5"
                                                       data-ope="swapped_out">换出商品寄出</a>
                                                @endif
                                            @endif
                                            @if($rorder->return_status >= 2 && $rorder->return_status < 4)
                                                <a href="javascript:;"
                                                   class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="complete">完成退换货</a>
                                            @endif
                                        @endif
                                        @if($rorder->refound_status != 1 && $rorder->agree_apply != 1)
                                            @if($rorder->return_status != 6)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="refuse_apply">拒绝</a>
                                            @else
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="">已拒绝申请</a>
                                            @endif
                                        @endif
                                        <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                           data-ope="after_service">售后</a>
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
            $('.order-operation').on('click', 'a', function () {
                var type = $(this).data('ope');
                if (type == 'refound' || type == 'after_service' || type == '') {
                    return;
                }
                var order_id = $('input[name=order_id]').val();
                $.post("{{url('admin/order/return/change')}}", {
                    id: order_id,
                    type: 'operation',
                    value: type,
                    _token: '{{csrf_token()}}'
                }, function (data) {
                    layer.msg(data.msg, {icon: data.code});
                    setTimeout(function () {
                        location.href = location.href;
                    }, 2000);
                });
            });
        });
    </script>
@endsection
@endsection