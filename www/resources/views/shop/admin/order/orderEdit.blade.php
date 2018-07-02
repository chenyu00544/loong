@extends('shop.layouts.index')
@section('css')
    <link rel="stylesheet" href="{{asset('styles/plugin/bootstrap/colorpicker/bootstrap-colorpicker.min.css')}}">
@endsection
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">订单管理 - 订单信息</div>
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
                    <dl class="@if($order->shipping_status == 1) cur @endif">
                        <dt class="cursor">3</dt>
                        <dd class="s-text">商家发货</dd>
                        @if(!empty($order->shipping_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->shipping_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="last @if($order->shipping_status == 2) cur @endif">
                        <dt class="cursor">4</dt>
                        <dd class="s-text">确认收货</dd>
                        @if(!empty($order->confirm_take_time))
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

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>基本信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>2018070220185782655</dd>
                                    <dt>订单来源：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>购货人：</dt>
                                    <dd>chenyu</dd>
                                    <dt>订单状态：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>支付方式：</dt>
                                    <dd>银行汇款/转帐</dd>
                                    <dt>配送方式：</dt>
                                    <dd>顺丰速运</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>2018-07-02 20:18:01</dd>
                                    <dt>付款时间：</dt>
                                    <dd>未付款</dd>
                                </dl>
                                <dl>
                                    <dt>发货时间：</dt>
                                    <dd>未发货</dd>
                                    <dt>发货单号：</dt>
                                    <dd>未发货</dd>
                                </dl>
                                <dl>
                                    <dt>自动确认收货时间：</dt>
                                    <dd>15 天</dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>收货人信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>2018070220185782655</dd>
                                    <dt>订单来源：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>购货人：</dt>
                                    <dd>chenyu</dd>
                                    <dt>订单状态：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>支付方式：</dt>
                                    <dd>银行汇款/转帐</dd>
                                    <dt>配送方式：</dt>
                                    <dd>顺丰速运</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>2018-07-02 20:18:01</dd>
                                    <dt>付款时间：</dt>
                                    <dd>未付款</dd>
                                </dl>
                                <dl>
                                    <dt>发货时间：</dt>
                                    <dd>未发货</dd>
                                    <dt>发货单号：</dt>
                                    <dd>未发货</dd>
                                </dl>
                                <dl>
                                    <dt>自动确认收货时间：</dt>
                                    <dd>15 天</dd>
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
                                    <tr>
                                        <td class="td-product">
                                            <div>
                                                <div class="img fl">
                                                    <img width="80" src="http://localhost/images/201703/thumb_img/0_thumb_G_1490155064450.jpg">
                                                </div>
                                                <div class="product-info">
                                                    <div class="name">
                                                        <a href="705" target="_blank">绿联 数据线安卓高速单头加长手机通用华为魅族小米2a快充电器2米 铝合金外壳+纯铜芯，充电快寿命长
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-center">
                                            <a type="button" href="{{url('admin/users/info/')}}"
                                               class="btn btn-info btn-edit btn-sm mar-all-5">生成发货单</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="12">
                                            <div class="order-total fr">
                                                <strong>合计：</strong>
                                                <span class="red ft-16"><em>¥</em>18.00</span>
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
                                <h3>费用信息</h3>
                            </div>
                            <div class="section">
                                <dl>
                                    <dt>订单号：</dt>
                                    <dd>2018070220185782655</dd>
                                    <dt>订单来源：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>购货人：</dt>
                                    <dd>chenyu</dd>
                                    <dt>订单状态：</dt>
                                    <dd>PC</dd>
                                </dl>
                                <dl>
                                    <dt>支付方式：</dt>
                                    <dd>银行汇款/转帐</dd>
                                    <dt>配送方式：</dt>
                                    <dd>顺丰速运</dd>
                                </dl>
                                <dl>
                                    <dt>下单时间：</dt>
                                    <dd>2018-07-02 20:18:01</dd>
                                    <dt>付款时间：</dt>
                                    <dd>未付款</dd>
                                </dl>
                                <dl>
                                    <dt>发货时间：</dt>
                                    <dd>未发货</dd>
                                    <dt>发货单号：</dt>
                                    <dd>未发货</dd>
                                </dl>
                                <dl>
                                    <dt>自动确认收货时间：</dt>
                                    <dd>15 天</dd>
                                    <dt></dt>
                                    <dd></dd>
                                </dl>
                            </div>
                        </div>

                        <div class="order-step clearfix mar-bt-50">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>操作信息</h3>
                            </div>
                            <div class="order-operation">

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
        });
    </script>
@endsection
@endsection