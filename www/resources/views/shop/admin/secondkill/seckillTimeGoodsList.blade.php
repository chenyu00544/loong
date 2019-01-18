@extends('shop.layouts.index')
@section('content')
    <body style="overflow-y: scroll;background-color: #f7f7f7;">
    <div class="warpper clearfix">
        <div class="title">促销管理 - 添加商品详情</div>
        <div class="content">
            <div class="tip">
                <div class="tip_title">
                    <i class="tip_icon"></i>
                    <h5>操作提示</h5>
                </div>
                <ul>
                    <li>标识<em>"*"</em>的选项为必填项，其余为选填项。</li>
                    <li>设置秒杀活动某个时间段的商品列表，对该时间段商品可进行添加/删除，修改秒杀价格、数量、限购数量等信息操作。</li>
                </ul>
            </div>
            <div class="fromlist clearfix">
                <div class="clearfix mar-bt-20">
                    <a href="javascript:;" class="btn btn-success btn-sm add_goods" data-sid="{{$sid}}" data-stid="{{$stid}}">设置商品</a>
                </div>
                <div class="main-info">
                    <table class="table table-hover table-condensed" style="margin-bottom: 2px">
                        <thead>
                        <tr>
                            <th width="5%">编号</th>
                            <th width="35%">商品名称</th>
                            <th width="15%" class="text-center">商品价格</th>
                            <th width="10%" class="text-center">秒杀价格</th>
                            <th width="10%" class="text-center">秒杀数量</th>
                            <th width="10%" class="text-center">限购数量</th>
                            <th width="15%" class="text-center">操作</th>
                        </tr>
                        </thead>
                        @if($secKillGoodses->count() == 0)
                            <tbody>
                            <tr class="">
                                <td class="no-records" colspan="20">没有找到任何记录</td>
                            </tr>
                            </tbody>
                        @else
                            <tbody>
                            @foreach($secKillGoodses as $secKillGoods)
                                <tr class="">
                                    <input type="hidden" name="goods_id" value="{{$secKillGoods->goods_id}}">
                                    <td>{{$secKillGoods->id}}</td>
                                    <td class="owt">
                                        @if(empty($secKillGoods->goods))
                                            未知商品 @else {{$secKillGoods->goods->goods_name}} @endif
                                    </td>
                                    <td class="text-center">
                                        ￥@if(empty($secKillGoods->goods))
                                            0.0 @else {{$secKillGoods->goods->shop_price}} @endif
                                    </td>
                                    <td>
                                        <input type="text" name="sec_price" value="{{$secKillGoods->sec_price}}"
                                               class="form-control input-sm text-center" data-id="{{$secKillGoods->id}}"
                                               placeholder="">
                                    </td>
                                    <td>
                                        <input type="text" name="sec_num" value="{{$secKillGoods->sec_num}}"
                                               class="form-control input-sm text-center" data-id="{{$secKillGoods->id}}"
                                               placeholder=""></td>
                                    <td>
                                        <input type="text" name="sec_limit" value="{{$secKillGoods->sec_limit}}"
                                               class="form-control input-sm text-center" data-id="{{$secKillGoods->id}}"
                                               placeholder="">
                                    </td>
                                    <td class="text-center">
                                        <a type="button" href="javascript:;"
                                           class="btn btn-danger btn-del btn-sm"
                                           data-id="{{$secKillGoods->id}}">删除</a>
                                    </td>
                                </tr>
                            @endforeach
                            </tbody>
                        @endif
                    </table>
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
            //设置图片
            $('.add_goods').click(function () {
                var ids = [];
                $('input[name="goods_id"]').each(function (k,v) {
                    ids.push($(v).val());
                });
                if(ids.length == 0){
                    ids.push(0)
                }
                layer.open({
                    type: 2,
                    area: ['900px', '540px'],
                    fixed: true, //不固定
                    maxmin: true,
                    title: '设置商品',
                    content: ["{{url('admin/dialog/goods/search/')}}" + "/" + ids.join(','), 'no'],
                    success: function (layero, index) {
                        layer.iframeAuto(index)
                    }
                });
            });
            
            
            $('input[name=sec_price]').change(function () {
                var Id = $(this).data('id');
                var val = $(this).val();
                $.post(
                    "{{url('admin/seckillgoods/change')}}",
                    {
                        id: Id,
                        value: val,
                        type: 'sec_price',
                        '_token': '{{csrf_token()}}'
                    },
                    function (data) {});
            });
            $('input[name=sec_num]').change(function () {
                var Id = $(this).data('id');
                var val = $(this).val();
                $.post(
                    "{{url('admin/seckillgoods/change')}}",
                    {
                        id: Id,
                        value: val,
                        type: 'sec_num',
                        '_token': '{{csrf_token()}}'
                    },
                    function (data) {});
            });
            $('input[name=sec_limit]').change(function () {
                var Id = $(this).data('id');
                var val = $(this).val();
                $.post(
                    "{{url('admin/seckillgoods/change')}}",
                    {
                        id: Id,
                        value: val,
                        type: 'sec_limit',
                        '_token': '{{csrf_token()}}'
                    },
                    function (data) {});
            });

            //删除
            $('.btn-del').click(function () {
                var that = this;
                var Id = $(this).data('id');
                layer.confirm('您确定要删除吗', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.post(
                        "{{url('admin/seckillgoods/')}}/" + Id,
                        {'_method': 'delete', '_token': '{{csrf_token()}}'},
                        function (data) {
                            layer.msg(data.msg, {icon: data.code});
                            if (data.code == 1) {
                                $(that).parent().parent().remove();
                            }
                        });
                }, function () {
                });
            });
        });
    </script>
@endsection
@endsection