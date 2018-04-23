@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;padding: 20px;">
    <div class="content-wrap">
        <div class="tip">
            <ul>
                <li>1、权重排序统计购买数量必须是会员“确认收货”后的订单。</li>
                <li>2、权重排序默认不会统计出之前的老数据，在统计过一次后会把老数据统计出来</li>
            </ul>
        </div>

        <div class="weight-goods-name">
            <span>商品名称：</span><span class="title">{{$goods->goods_name}}</span>
        </div>
        <div class="weight-goods-name">
            <table class="table table-bordered">
                <thead>
                <tr class="text-center">
                    <td style="width: 20%">商品销售数量</td>
                    <td style="width: 20%">商品退换货数量</td>
                    <td style="width: 20%">对商品评价数量</td>
                    <td style="width: 20%">收藏商品数量</td>
                    <td style="width: 20%">人工干预值</td>
                </tr>
                </thead>
                <tbody>
                <tr class="text-center ft-16">
                    <td>{{$goodsWeight['goods_number']}}</td>
                    <td>{{$goodsWeight['return_number']}}</td>
                    <td>{{$goodsWeight['goods_comment_number']}}</td>
                    <td>{{$goodsWeight['user_attention_number']}}</td>
                    <td>
                        <span class="artificial edit-artificial">{{$goods->sort_order}}</span>
                        <i class="glyphicon glyphicon-edit"></i>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
    </body>
@section('script')
    <script>
        $(function () {
            $('.edit-artificial').click(function () {
                if ($(this).find('input').length <= 0) {
                    var artificial = $(this).html();
                    var html = '<input type="text" class="form-control" autofocus="autofocus" onblur="blurDel()" style="display: inline-block;width: 60%;height: 25px;" maxlength="8" value="' + artificial + '">';
                    $(this).html(html);
                    $(this).find('input').val("").focus().val(artificial);
                }
            })
        });

        function blurDel() {
            var artificial = $('.edit-artificial').find('input').val();
            $('.edit-artificial').html(artificial);

            $.post(
                '{{url("admin/goods/change")}}',
                {
                    id: '{{$goods->goods_id}}',
                    type: 'order',
                    val: artificial,
                    _token: '{{csrf_token()}}'
                },
                function (data) {
                }
            );
        }
    </script>
@endsection
@endsection