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

                        <input type="hidden" name="order_id" value="{{$dorder->delivery_id}}">

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>基本信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>流水号：</dt>
                                    <dd>{{$dorder->delivery_sn}}</dd>
                                    <dt>订单号：</dt>
                                    <dd>{{$dorder->order_sn}}</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s',$dorder->add_time)}}</dd>
                                    <dt>购货人：</dt>
                                    <dd>{{$user->user_name}}</dd>
                                </dl>
                                <dl>
                                    <dt>发货时间：</dt>
                                    <dd>{{date('Y-m-d H:i:s',$dorder->update_time)}}</dd>
                                    <dt>配送方式：</dt>
                                    <dd>{{$dorder->shipping_name}}</dd>
                                </dl>
                                <dl>
                                    <dt>缺货处理：</dt>
                                    <dd>{{$dorder->how_oos}}</dd>
                                    <dt>是否保价：</dt>
                                    <dd>@if($dorder->insure_fee > 0) 是 @else 否 @endif</dd>
                                </dl>
                                <dl>
                                    <dt>配送费用：</dt>
                                    <dd>{{$dorder->shipping_fee}}</dd>
                                    <dt>发货单号：</dt>
                                    <dd>
                                        <input type="text" name="invoice_no"
                                               class="form-control input-sm wdh60 mar-top-5 hg25"
                                               value="{{$dorder->invoice_no}}"
                                               @if($dorder->status == 0) disabled @endif>
                                    </dd>
                                </dl>
                                <dl>
                                    <dt>保价费用：</dt>
                                    <dd>{{$dorder->insure_fee}}</dd>
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
                                    <dd>{{$dorder->consignee}}</dd>
                                    <dt>电子邮件：</dt>
                                    <dd>{{$dorder->email}}</dd>
                                </dl>
                                <dl>
                                    <dt>电话号码：</dt>
                                    <dd>{{$dorder->tel}}</dd>
                                    <dt>手机号码：</dt>
                                    <dd>{{$dorder->mobile}}</dd>
                                </dl>
                                <dl>
                                    <dt>送货时间：</dt>
                                    <dd>{{$dorder->best_time}}</dd>
                                    <dt>地址别名：</dt>
                                    <dd>{{$dorder->sign_building}}</dd>
                                </dl>
                                <dl>
                                    <dt>收货地址：</dt>
                                    <dd>[{{$province['name']}} {{$city['name']}} {{$district['name']}}
                                        ] {{$dorder->address}}</dd>
                                    <dt>邮政编码：</dt>
                                    <dd>{{$dorder->zipcode}}</dd>
                                </dl>
                                <dl>
                                    <dt>买家留言：</dt>
                                    <dd>{{$dorder->postscript}}</dd>
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
                                    @foreach($deliveryGoodses as $deliveryGoods)
                                        <tr>
                                            <td class="td-product">
                                                <div>
                                                    <div class="img fl">
                                                        <img width="80"
                                                             src="{{url($deliveryGoods->goods_thumb)}}">
                                                    </div>
                                                    <div class="product-info">
                                                        <div class="name">
                                                            <a href="705"
                                                               target="_blank">{{$deliveryGoods->goods_name}}
                                                                [{{$deliveryGoods->brand_name}}]</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="text-center">{{$deliveryGoods->goods_sn}}</td>
                                            <td class="text-center">{{$deliveryGoods->product_sn}}</td>
                                            <td class="text-center">{{$deliveryGoods->goods_attr}}</td>
                                            <td class="text-center">{{$deliveryGoods->send_number}}</td>
                                        </tr>
                                    @endforeach
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
                                            <textarea class="form-control wd-550" rows="5"
                                                      name="action_note"></textarea>
                                        <div class="mar-top-20 operation">
                                            @if($dorder->status == 2)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="ship">发货</a>
                                            @elseif($dorder->status == 0)
                                                <a href="javascript:;" class="btn btn-danger btn-sm mar-left-5"
                                                   data-ope="unship">取消发货</a>
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
    @component('shop.components.copyright',['copyright'=>''])@endcomponent
    <div style="height: 30px">　</div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.order-operation').on('click', 'a', function () {
                var type = $(this).data('ope');
                var dorder_id = $('input[name=order_id]').val();
                var invoice_no = $('input[name=invoice_no]').val();
                $.post("{{url('admin/order/delivery/change')}}", {
                    id: dorder_id,
                    type: 'ship',
                    value: type,
                    invoice_no: invoice_no,
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