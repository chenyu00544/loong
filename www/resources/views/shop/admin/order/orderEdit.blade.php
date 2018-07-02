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
                    <dl class="first cur" data-step="1">
                        <dt class="cursor">1</dt>
                        <dd class="s-text">提交订单</dd>
                        @if(!empty($order->add_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->add_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="" data-step="2">
                        <dt class="cursor">2</dt>
                        <dd class="s-text">支付订单</dd>
                        @if(!empty($order->pay_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->pay_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="" data-step="3">
                        <dt class="cursor">3</dt>
                        <dd class="s-text">商家发货</dd>
                        @if(!empty($order->shipping_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->shipping_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="last" data-step="4">
                        <dt class="cursor">4</dt>
                        <dd class="s-text">确认收货</dd>
                        @if(!empty($order->confirm_take_time))
                            <dd class="s-time">{{date('Y-m-d H:i:s', $order->confirm_take_time)}}</dd>
                        @endif
                    </dl>
                    <dl class="last" data-step="4">
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

                        <div class="step step-one" ectype="step" data-step="1" style="">
                            <div class="step-title">
                                <i class="ui-step"></i>
                                <h3>基本信息</h3>
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