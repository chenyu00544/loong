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
                            <label class="col-sm-4 control-label">发票类型：</label>
                            <div class="col-sm-3">
                                <input type="text" name="inv_type" class="form-control" value="{{$order->inv_type}}"
                                       placeholder="发票类型" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发票抬头：</label>
                            <div class="col-sm-3">
                                <input type="text" name="inv_payee" class="form-control" value="{{$order->inv_payee}}"
                                       placeholder="发票抬头" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">发票内容：</label>
                            <div class="col-sm-3">
                                <input type="text" name="inv_content" class="form-control" value="{{$order->inv_content}}"
                                       placeholder="发票内容" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">买家留言：</label>
                            <div class="col-sm-5">
                                <textarea name="postscript" class="form-control" id="" cols="30" rows="5">{{$order->postscript}}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">缺货处理：</label>
                            <div class="col-sm-3">
                                <input type="text" name="how_oos" class="form-control" value="{{$order->how_oos}}"
                                       placeholder="缺货处理" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">商家给客户的留言：</label>
                            <div class="col-sm-5">
                                <textarea name="to_buyer" class="form-control" id="" cols="30" rows="5">{{$order->to_buyer}}</textarea>
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
                        type: 'other',
                        other: data,
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