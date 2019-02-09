@extends('shop.layouts.index')
@section('content')
    <body style="background-color: #f7f7f7;padding: 0 20px;">
    <div class="content-wrap">
        <div class="weight-goods-name clearfix">
            <div class="fl line-hg-30">
                <span>商品货号：</span><span class="title" data-model="{{$products['goods']->model_inventory}}">{{$products['goods']->goods_sn}}</span>
            </div>
            <div class="fl mar-left-10 wd250">
                <input type="text" name="keywords" value="" class="form-control input-sm max-wd-190"
                       placeholder="活动名称" autocomplete="off">
                <input type="submit" class="btn btn-primary btn-edit btn-sm mar-left-10 fr" value="查询">
            </div>
        </div>
        <div class="weight-goods-name">
            <table class="table table-bordered">
                <thead>
                <tr class="text-center">
                    <td style="width: 20%">SKU编号</td>
                    <td style="width: 20%">规格</td>
                    <td style="width: 20%">条形码</td>
                    <td style="width: 20%">价格</td>
                    <td style="width: 20%">库存</td>
                </tr>
                </thead>
                <tbody>
                @if($products['products']->count() ==0)
                    <tr class="">
                        <td class="no-records" colspan="20">没有找到任何记录</td>
                    </tr>
                @else
                    @foreach($products['products'] as $product)
                        <tr class="text-center ft-16">
                            <td>{{$product->product_sn}}</td>
                            <td>
                                @foreach($product->goods_attr as $goods_attr)
                                    {{$goods_attr->attr_value}}　
                                @endforeach
                            </td>
                            <td>N/A</td>
                            <td>
                                <input type="text" name="product" class="form-control text-center"
                                       data-type="product_price" value="{{$product->product_price}}" autocomplete="off"
                                       data-product_id="{{$product->product_id}}"
                                       placeholder="价格">
                            </td>
                            <td>
                                <input type="text" name="product" class="form-control text-center"
                                       data-type="product_number" value="{{$product->product_number}}"
                                       autocomplete="off" data-product_id="{{$product->product_id}}" placeholder="库存">
                            </td>
                        </tr>
                    @endforeach
                @endif
                </tbody>
            </table>
            <div class="page_list" style="position: absolute; bottom: 0px;">
                {{$products['products']->links()}}
            </div>
        </div>
    </div>
    </body>
@section('script')
    <script>
        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.iframeAuto(index);
        $(function () {
            $('input[name=product]').on('change', function () {
                var product_id = $(this).data('product_id');
                var type = $(this).data('type');
                var val = $(this).val();
                var model = $('.title').data('model');
                $.post("{{url('admin/goods/change/sku')}}", {
                    _token: token,
                    id: product_id,
                    type: type,
                    val: val,
                    model:model
                }, function (data) {
                    parent.layer.iframeAuto(index);
                });
            });
        });
    </script>
@endsection
@endsection