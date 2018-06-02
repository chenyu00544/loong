@extends('shop.layouts.index')
@section('content')
    <body style="overflow: hidden;background-color: #f7f7f7;padding: 20px; height: auto;" class="clearfix">
    <div class="content-wrap">
        <div class="form-group clearfix">
            <label class="col-xs-3 control-label text-right">快递名称：</label>
            <div class="col-xs-9">
                <span class="title" style="white-space: nowrap;width: 350px;">{{$ship->shipping_name}}</span>
            </div>
        </div>
        <div class="form-group clearfix">
            <label class="col-xs-3 control-label text-right line-hg-30">账号：</label>
            <div class="col-xs-9">
                <input type="text" name="customer_name" class="form-control" value="{{$ship->customer_name}}"
                       style="margin-top:0;" placeholder="第三方账号">
            </div>
        </div>
        <div class="form-group clearfix">
            <label class="col-xs-3 control-label text-right line-hg-30">密码：</label>
            <div class="col-xs-9">
                <input type="text" name="customer_pwd" class="form-control" value="{{$ship->customer_pwd}}"
                       style="margin-top:0;" placeholder="第三方密码">
            </div>
        </div>
        <div class="form-group clearfix">
            <label class="col-xs-3 control-label text-right line-hg-30">月结编码：</label>
            <div class="col-xs-9">
                <input type="text" name="month_code" class="form-control" value="{{$ship->month_code}}"
                       style="margin-top:0;" placeholder="网点月结编码">
            </div>
        </div>
        <div class="form-group clearfix">
            <label class="col-xs-3 control-label text-right line-hg-30">网点标识：</label>
            <div class="col-xs-9">
                <input type="text" name="send_site" class="form-control" value="{{$ship->send_site}}"
                       style="margin-top:0;" placeholder="网点标识">
            </div>
        </div>
        <div class="weight-goods-name" style="text-align: center;">
            <input type="hidden" class="shipping_id" value="{{$ship->shipping_id}}">
            <a type="button" class="btn btn-danger btn-sure mar-all-8">确定</a>
            <a type="button" class="btn btn-default btn-close mar-all-8">取消</a>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        $(function () {

            $('.btn-sure').click(function () {
                var id = $('.shipping_id').val();
                var customer_name = $('input[name=customer_name]').val();
                var customer_pwd = $('input[name=customer_pwd]').val();
                var month_code = $('input[name=month_code]').val();
                var send_site = $('input[name=send_site]').val();
                $.post(
                    "{{url('admin/express/changes')}}",
                    {
                        '_token': '{{csrf_token()}}',
                        id: id,
                        customer_name: customer_name,
                        customer_pwd: customer_pwd,
                        month_code: month_code,
                        send_site: send_site
                    },
                    function (data) {
                        parent.layer.close(index);
                    }
                );
            })

            //关闭iframe
            $('.btn-close').click(function () {
                parent.layer.close(index);
            });
        });

    </script>
@endsection
@endsection